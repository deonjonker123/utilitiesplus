package com.misterd.utilitiesplus.blockentity.custom;

import com.misterd.utilitiesplus.blockentity.UPBlockEntities;
import com.misterd.utilitiesplus.config.UPConfig;
import com.misterd.utilitiesplus.gui.custom.HarvesterMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class HarvesterBlockEntity extends BlockEntity implements WorldlyContainer, MenuProvider {

    public static final int SLOT_HOE = 0;
    public static final int SLOT_FUEL = 1;
    private static final int SIZE = 2;

    private static final int[] SLOTS_TOP = {SLOT_HOE};
    private static final int[] SLOTS_SIDE = {SLOT_FUEL};
    private static final int[] SLOTS_BOTTOM = {};

    private final NonNullList<ItemStack> inventory = NonNullList.withSize(SIZE, ItemStack.EMPTY);

    int litTime = 0;
    int litDuration = 0;
    int checkTimer = 0;

    private final ContainerData data = new ContainerData() {
        @Override
        public int get(int i) {
            return switch (i) {
                case 0 -> litTime;
                case 1 -> litDuration;
                default -> 0;
            };
        }

        @Override
        public void set(int i, int v) {
            switch (i) {
                case 0 -> litTime = v;
                case 1 -> litDuration = v;
            }
        }

        @Override
        public int getCount() { return 2; }
    };

    public HarvesterBlockEntity(BlockPos pos, BlockState state) {
        super(UPBlockEntities.HARVESTER_BE, pos, state);
    }

    public ContainerData getData() { return data; }

    public static void tick(Level level, BlockPos pos, BlockState state, HarvesterBlockEntity be) {
        if (!(level instanceof ServerLevel serverLevel)) return;

        boolean wasLit = be.isLit();
        boolean dirty = false;

        if (be.isLit()) {
            be.litTime--;
            dirty = true;
        }

        ItemStack hoe = be.inventory.get(SLOT_HOE);
        ItemStack fuel = be.inventory.get(SLOT_FUEL);

        if (!hoe.isEmpty() && !be.isLit() && !fuel.isEmpty()) {
            int burnDuration = serverLevel.fuelValues().burnDuration(fuel);
            if (burnDuration > 0) {
                be.litDuration = burnDuration;
                be.litTime = burnDuration;
                dirty = true;
                fuel.shrink(1);
                if (fuel.isEmpty()) {
                    ItemStackTemplate remainder = fuel.getItem().getCraftingRemainder();
                    be.inventory.set(SLOT_FUEL, remainder != null ? remainder.create() : ItemStack.EMPTY);
                }
            }
        }

        if (be.isLit() && !hoe.isEmpty()) {
            be.checkTimer++;
            if (be.checkTimer >= UPConfig.get().harvesterCheckInterval) {
                be.checkTimer = 0;
                boolean harvested = be.harvestCrops(serverLevel, pos, state);
                if (harvested && UPConfig.get().harvesterHoeTakesDamage) {
                    hoe.hurtAndBreak(1, serverLevel, null, item -> {
                        be.inventory.set(SLOT_HOE, ItemStack.EMPTY);
                    });
                }
                dirty = true;
            }
        } else {
            be.checkTimer = 0;
        }

        if (wasLit != be.isLit()) {
            state = state.setValue(BlockStateProperties.LIT, be.isLit());
            level.setBlock(pos, state, 3);
            dirty = true;
        }

        if (dirty) be.setChanged();
    }

    private boolean harvestCrops(ServerLevel level, BlockPos pos, BlockState state) {
        Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        int area = UPConfig.get().harvesterWorkArea;
        int half = area / 2;

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        boolean harvested = false;

        for (int dx = -half; dx <= half; dx++) {
            for (int dz = 1; dz <= area; dz++) {
                BlockPos targetPos = getOffsetPos(pos, facing, dx, dz);
                mutable.set(targetPos);

                BlockState cropState = level.getBlockState(mutable);
                Block cropBlock = cropState.getBlock();

                if (!(cropBlock instanceof CropBlock crop)) continue;
                if (!crop.isMaxAge(cropState)) continue;

                List<ItemStack> drops = Block.getDrops(cropState, level, mutable, null);
                BlockState resetState = crop.getStateForAge(0);
                level.setBlock(mutable, resetState, 3);

                ItemStack seedToReplant = findSeed(level, mutable.immutable(), drops, crop);

                for (ItemStack drop : drops) {
                    if (!drop.isEmpty()) {
                        if (seedToReplant != null && !seedToReplant.isEmpty() && ItemStack.isSameItem(drop, seedToReplant)) {
                            seedToReplant = null;
                            drop.shrink(1);
                            if (drop.isEmpty()) continue;
                        }
                        Block.popResource(level, mutable, drop);
                    }
                }
                harvested = true;
            }
        }
        return harvested;
    }

    private BlockPos getOffsetPos(BlockPos origin, Direction facing, int dx, int dz) {
        return switch (facing) {
            case NORTH -> origin.offset(dx, 0, -dz);
            case SOUTH -> origin.offset(-dx, 0, dz);
            case EAST -> origin.offset(dz, 0, dx);
            case WEST -> origin.offset(-dz, 0, -dx);
            default -> origin.offset(dx, 0, dz);
        };
    }

    @Nullable
    private ItemStack findSeed(ServerLevel level, BlockPos pos, List<ItemStack> drops, CropBlock crop) {
        List<ItemStack> seedDrops = Block.getDrops(crop.getStateForAge(0), level, pos, null);
        for (ItemStack seedDrop : seedDrops) {
            for (ItemStack drop : drops) {
                if (ItemStack.isSameItem(drop, seedDrop)) return drop;
            }
        }
        return null;
    }

    public boolean isLit() { return litTime > 0; }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.utilitiesplus.harvester");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new HarvesterMenu(id, inv, this, data);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, inventory);
        output.putInt("LitTime", litTime);
        output.putInt("LitDuration", litDuration);
        output.putInt("CheckTimer", checkTimer);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, inventory);
        litTime = input.getIntOr("LitTime", 0);
        litDuration = input.getIntOr("LitDuration", 0);
        checkTimer = input.getIntOr("CheckTimer", 0);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public int getContainerSize() { return SIZE; }

    @Override
    public boolean isEmpty() {
        return inventory.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getItem(int slot) { return inventory.get(slot); }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        return ContainerHelper.removeItem(inventory, slot, amount);
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
    public void clearContent() { inventory.clear(); }

    @Override
    public int[] getSlotsForFace(Direction dir) {
        return SLOTS_SIDE;
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction dir) {
        return canPlaceItem(slot, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction dir) {
        return false;
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        if (slot == SLOT_HOE) return stack.is(ItemTags.HOES);
        if (slot == SLOT_FUEL) return level != null && level instanceof ServerLevel sl && sl.fuelValues().burnDuration(stack) > 0;
        return false;
    }
}