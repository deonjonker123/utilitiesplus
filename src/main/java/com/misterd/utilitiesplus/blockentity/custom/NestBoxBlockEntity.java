package com.misterd.utilitiesplus.blockentity.custom;

import com.misterd.utilitiesplus.blockentity.UPBlockEntities;
import com.misterd.utilitiesplus.config.UPConfig;
import com.misterd.utilitiesplus.gui.custom.NestBoxMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class NestBoxBlockEntity extends BlockEntity implements WorldlyContainer, MenuProvider {

    public static final int SIZE = 9;
    private static final int[] SLOTS = {0, 1, 2, 3, 4, 5, 6, 7, 8};

    private final NonNullList<ItemStack> inventory = NonNullList.withSize(SIZE, ItemStack.EMPTY);

    public NestBoxBlockEntity(BlockPos pos, BlockState state) {
        super(UPBlockEntities.NEST_BOX_BE, pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, NestBoxBlockEntity be) {
        if (!(level instanceof ServerLevel)) return;

        Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        int area = UPConfig.get().nestBoxCollectionArea;
        int half = area / 2;

        AABB searchBox = getSearchBox(pos, facing, half);

        List<ItemEntity> eggs = level.getEntitiesOfClass(ItemEntity.class, searchBox,
                e -> e.getItem().is(Items.EGG));

        for (ItemEntity egg : eggs) {
            ItemStack eggStack = egg.getItem();
            for (int i = 0; i < SIZE; i++) {
                ItemStack slot = be.inventory.get(i);
                if (slot.isEmpty()) {
                    be.inventory.set(i, eggStack.copy());
                    egg.discard();
                    be.setChanged();
                    break;
                } else if (slot.is(Items.EGG) && slot.getCount() < slot.getMaxStackSize()) {
                    int space = slot.getMaxStackSize() - slot.getCount();
                    int take = Math.min(space, eggStack.getCount());
                    slot.grow(take);
                    eggStack.shrink(take);
                    if (eggStack.isEmpty()) egg.discard();
                    be.setChanged();
                    break;
                }
            }
        }
    }

    private static AABB getSearchBox(BlockPos pos, Direction facing, int half) {
        int depth = UPConfig.get().nestBoxCollectionArea;
        return switch (facing) {
            case NORTH -> new AABB(
                    pos.getX() - half, pos.getY() - 1, pos.getZ() - depth,
                    pos.getX() + half + 1, pos.getY() + 2, pos.getZ() + 1);
            case SOUTH -> new AABB(
                    pos.getX() - half, pos.getY() - 1, pos.getZ(),
                    pos.getX() + half + 1, pos.getY() + 2, pos.getZ() + depth + 1);
            case EAST -> new AABB(
                    pos.getX(), pos.getY() - 1, pos.getZ() - half,
                    pos.getX() + depth + 1, pos.getY() + 2, pos.getZ() + half + 1);
            case WEST -> new AABB(
                    pos.getX() - depth, pos.getY() - 1, pos.getZ() - half,
                    pos.getX() + 1, pos.getY() + 2, pos.getZ() + half + 1);
            default -> new AABB(pos);
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.utilitiesplus.nest_box");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new NestBoxMenu(id, inv, this);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public int getContainerSize() {
        return SIZE;
    }

    @Override
    public boolean isEmpty() {
        return inventory.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getItem(int slot) {
        return inventory.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack result = ContainerHelper.removeItem(inventory, slot, amount);
        if (!result.isEmpty()) setChanged();
        return result;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(inventory, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        inventory.set(slot, stack);
        setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return player.distanceToSqr(Vec3.atCenterOf(worldPosition)) <= 64.0;
    }

    @Override
    public void clearContent() {
        inventory.clear();
    }

    @Override
    public int[] getSlotsForFace(Direction dir) {
        if (dir == Direction.DOWN) return SLOTS;
        return new int[]{};
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction dir) {
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction dir) {
        return dir == Direction.DOWN;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, inventory);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, inventory);
    }
}