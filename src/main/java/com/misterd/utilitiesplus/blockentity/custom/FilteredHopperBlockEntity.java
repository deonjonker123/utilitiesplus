package com.misterd.utilitiesplus.blockentity.custom;

import com.misterd.utilitiesplus.blockentity.UPBlockEntities;
import com.misterd.utilitiesplus.gui.custom.FilteredHopperMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class FilteredHopperBlockEntity extends BlockEntity implements WorldlyContainer, MenuProvider {

    public static final int SLOT_FILTER = 0;
    public static final int SLOT_INV_START = 1;
    public static final int SIZE = 6;
    public static final int MOVE_ITEM_SPEED = 8;

    private static final int[] SLOTS_TOP = {1, 2, 3, 4, 5};
    private static final int[] SLOTS_SIDE = {1, 2, 3, 4, 5};
    private static final int[] SLOTS_BOTTOM = {1, 2, 3, 4, 5};

    private final NonNullList<ItemStack> items = NonNullList.withSize(SIZE, ItemStack.EMPTY);
    private int cooldownTime = -1;
    private long tickedGameTime;
    private Direction facing;

    public FilteredHopperBlockEntity(BlockPos pos, BlockState state) {
        super(UPBlockEntities.FILTERED_HOPPER_BE, pos, state);
        this.facing = state.getValue(BlockStateProperties.FACING_HOPPER);
    }

    @Override
    public void setBlockState(BlockState state) {
        super.setBlockState(state);
        this.facing = state.getValue(BlockStateProperties.FACING_HOPPER);
    }

    public static void pushItemsTick(Level level, BlockPos pos, BlockState state, FilteredHopperBlockEntity be) {
        --be.cooldownTime;
        be.tickedGameTime = level.getGameTime();
        if (!be.isOnCooldown()) {
            be.setCooldown(0);
            tryMoveItems(level, pos, state, be);
        }
    }

    private static void tryMoveItems(Level level, BlockPos pos, BlockState state, FilteredHopperBlockEntity be) {
        if (level.isClientSide()) return;
        if (be.isOnCooldown()) return;
        if (!state.getValue(BlockStateProperties.ENABLED)) return;

        boolean changed = false;
        if (!be.isEmpty()) {
            changed = ejectItems(level, pos, be);
        }
        if (!be.inventoryFull()) {
            changed |= suckInItems(level, be);
        }
        if (changed) {
            be.setCooldown(MOVE_ITEM_SPEED);
            setChanged(level, pos, state);
        }
    }

    private static boolean ejectItems(Level level, BlockPos pos, FilteredHopperBlockEntity be) {
        Container target = getContainerAt(level, pos.relative(be.facing));
        if (target == null) return false;

        Direction intoFace = be.facing.getOpposite();
        if (isFullContainer(target, intoFace)) return false;

        ItemStack filter = be.items.get(SLOT_FILTER);

        for (int slot = SLOT_INV_START; slot < SIZE; slot++) {
            ItemStack stack = be.getItem(slot);
            if (stack.isEmpty()) continue;
            if (!filter.isEmpty() && !ItemStack.isSameItem(stack, filter)) continue;

            int originalCount = stack.getCount();
            ItemStack remainder = addItem(be, target, be.removeItem(slot, 1), intoFace);
            if (remainder.isEmpty()) {
                target.setChanged();
                return true;
            }
            stack.setCount(originalCount);
            if (originalCount == 1) be.setItem(slot, stack);
        }

        return false;
    }

    private static boolean suckInItems(Level level, FilteredHopperBlockEntity be) {
        BlockPos above = be.worldPosition.above();
        BlockState aboveState = level.getBlockState(above);
        Container source = getContainerAt(level, above);

        if (source != null) {
            Direction dir = Direction.DOWN;
            int[] slots = source instanceof WorldlyContainer wc ? wc.getSlotsForFace(dir) : createFlatSlots(source.getContainerSize());
            for (int slot : slots) {
                if (tryTakeFromSlot(be, source, slot, dir)) return true;
            }
            return false;
        }

        for (ItemEntity entity : getItemsAbove(level, be)) {
            if (addItemFromEntity(be, entity)) return true;
        }

        return false;
    }

    private static boolean tryTakeFromSlot(FilteredHopperBlockEntity be, Container source, int slot, Direction dir) {
        ItemStack stack = source.getItem(slot);
        if (stack.isEmpty()) return false;
        if (!canTakeFrom(be, source, stack, slot, dir)) return false;

        ItemStack filter = be.items.get(SLOT_FILTER);
        if (!filter.isEmpty() && !ItemStack.isSameItem(stack, filter)) return false;

        int originalCount = stack.getCount();
        ItemStack remainder = addItem(source, be, source.removeItem(slot, 1), null);
        if (remainder.isEmpty()) {
            source.setChanged();
            return true;
        }
        stack.setCount(originalCount);
        if (originalCount == 1) source.setItem(slot, stack);
        return false;
    }

    private static boolean addItemFromEntity(FilteredHopperBlockEntity be, ItemEntity entity) {
        ItemStack filter = be.items.get(SLOT_FILTER);
        if (!filter.isEmpty() && !ItemStack.isSameItem(entity.getItem(), filter)) return false;

        ItemStack copy = entity.getItem().copy();
        ItemStack remainder = addItem(null, be, copy, null);
        if (remainder.isEmpty()) {
            entity.setItem(ItemStack.EMPTY);
            entity.discard();
            return true;
        }
        entity.setItem(remainder);
        return false;
    }

    public static void entityInside(Level level, BlockPos pos, BlockState state, Entity entity, FilteredHopperBlockEntity be) {
        if (entity instanceof ItemEntity itemEntity && !itemEntity.getItem().isEmpty()) {
            AABB suckBox = new AABB(0.0, 0.5, 0.0, 1.0, 1.0, 1.0);
            if (entity.getBoundingBox().move(-pos.getX(), -pos.getY(), -pos.getZ()).intersects(suckBox)) {
                tryMoveItems(level, pos, state, be);
            }
        }
    }

    private static ItemStack addItem(@Nullable Container from, Container to, ItemStack stack, @Nullable Direction dir) {
        if (to instanceof WorldlyContainer wc && dir != null) {
            int[] slots = wc.getSlotsForFace(dir);
            for (int i = 0; i < slots.length && !stack.isEmpty(); i++) {
                stack = tryMoveIntoSlot(from, to, stack, slots[i], dir);
            }
            return stack;
        }
        for (int i = 0; i < to.getContainerSize() && !stack.isEmpty(); i++) {
            stack = tryMoveIntoSlot(from, to, stack, i, dir);
        }
        return stack;
    }

    private static ItemStack tryMoveIntoSlot(@Nullable Container from, Container to, ItemStack stack, int slot, @Nullable Direction dir) {
        ItemStack current = to.getItem(slot);
        if (!canPlaceIn(to, stack, slot, dir)) return stack;

        if (current.isEmpty()) {
            to.setItem(slot, stack);
            return ItemStack.EMPTY;
        }
        if (canMerge(current, stack)) {
            int space = stack.getMaxStackSize() - current.getCount();
            int count = Math.min(stack.getCount(), space);
            if (count > 0) {
                stack.shrink(count);
                current.grow(count);
                to.setChanged();
            }
        }
        return stack;
    }

    private static boolean canPlaceIn(Container container, ItemStack stack, int slot, @Nullable Direction dir) {
        if (!container.canPlaceItem(slot, stack)) return false;
        if (container instanceof WorldlyContainer wc) return wc.canPlaceItemThroughFace(slot, stack, dir);
        return true;
    }

    private static boolean canTakeFrom(Container into, Container from, ItemStack stack, int slot, Direction dir) {
        if (!from.canTakeItem(into, slot, stack)) return false;
        if (from instanceof WorldlyContainer wc) return wc.canTakeItemThroughFace(slot, stack, dir);
        return true;
    }

    private static boolean canMerge(ItemStack a, ItemStack b) {
        return a.getCount() < a.getMaxStackSize() && ItemStack.isSameItemSameComponents(a, b);
    }

    private boolean inventoryFull() {
        for (int i = SLOT_INV_START; i < SIZE; i++) {
            ItemStack s = items.get(i);
            if (s.isEmpty() || s.getCount() < s.getMaxStackSize()) return false;
        }
        return true;
    }

    private static boolean isFullContainer(Container container, Direction dir) {
        int[] slots = container instanceof WorldlyContainer wc ? wc.getSlotsForFace(dir) : createFlatSlots(container.getContainerSize());
        for (int slot : slots) {
            ItemStack s = container.getItem(slot);
            if (s.getCount() < s.getMaxStackSize()) return false;
        }
        return true;
    }

    private static int[] createFlatSlots(int size) {
        int[] slots = new int[size];
        for (int i = 0; i < size; i++) slots[i] = i;
        return slots;
    }

    private static List<ItemEntity> getItemsAbove(Level level, FilteredHopperBlockEntity be) {
        AABB box = new AABB(
                be.worldPosition.getX(), be.worldPosition.getY() + 1, be.worldPosition.getZ(),
                be.worldPosition.getX() + 1, be.worldPosition.getY() + 2, be.worldPosition.getZ() + 1);
        return level.getEntitiesOfClass(ItemEntity.class, box, EntitySelector.ENTITY_STILL_ALIVE);
    }

    @Nullable
    private static Container getContainerAt(Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (state.hasBlockEntity()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof Container container) {
                if (container instanceof ChestBlockEntity && state.getBlock() instanceof ChestBlock chest) {
                    return ChestBlock.getContainer(chest, state, level, pos, true);
                }
                return container;
            }
        }
        List<Entity> entities = level.getEntities((Entity) null,
                new AABB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1),
                EntitySelector.CONTAINER_ENTITY_SELECTOR);
        return entities.isEmpty() ? null : (Container) entities.get(level.getRandom().nextInt(entities.size()));
    }

    private void setCooldown(int time) { this.cooldownTime = time; }
    private boolean isOnCooldown() { return this.cooldownTime > 0; }

    @Override
    public int[] getSlotsForFace(Direction dir) {
        return dir == Direction.DOWN ? SLOTS_BOTTOM : dir == Direction.UP ? SLOTS_TOP : SLOTS_SIDE;
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction dir) {
        if (slot == SLOT_FILTER) return false;
        ItemStack filter = items.get(SLOT_FILTER);
        if (!filter.isEmpty() && !ItemStack.isSameItem(stack, filter)) return false;
        return true;
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction dir) {
        return slot != SLOT_FILTER;
    }

    @Override public int getContainerSize() { return SIZE; }

    @Override
    public boolean isEmpty() {
        for (int i = SLOT_INV_START; i < SIZE; i++) if (!items.get(i).isEmpty()) return false;
        return true;
    }

    @Override public ItemStack getItem(int slot) {
        return items.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack result = ContainerHelper.removeItem(items, slot, amount);
        if (!result.isEmpty()) setChanged();
        return result;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(items, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        if (slot == SLOT_FILTER && stack.getCount() > 1) stack.setCount(1);
        items.set(slot, stack);
        setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent() {
        items.clear();
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, items);
        output.putInt("TransferCooldown", cooldownTime);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, items);
        cooldownTime = input.getIntOr("TransferCooldown", -1);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.utilitiesplus.filtered_hopper");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new FilteredHopperMenu(id, inv, this);
    }
}