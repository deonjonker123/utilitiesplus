package com.misterd.utilitiesplus.blockentity.custom;

import com.misterd.utilitiesplus.blockentity.UPBlockEntities;
import com.misterd.utilitiesplus.component.custom.BarrelData;
import com.misterd.utilitiesplus.config.UPConfig;
import com.misterd.utilitiesplus.gui.custom.BarrelMenu;
import com.misterd.utilitiesplus.item.UPItems;
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
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

public class BarrelBlockEntity extends BlockEntity implements WorldlyContainer, MenuProvider {

    public enum Tier {
        BASE(0),
        COPPER(1),
        IRON(2),
        GOLD(3),
        DIAMOND(4);

        private final int level;

        Tier(int level) { this.level = level; }

        public int getLevel() { return level; }

        public int getCapacity() {
            int cap = UPConfig.get().storageBarrelBaseCapacity;
            for (int i = 0; i < level; i++) cap *= 2;
            return cap;
        }

        public @Nullable Tier prev() {
            return level == 0 ? null : values()[level - 1];
        }

        public @Nullable Tier next() {
            return level == values().length - 1 ? null : values()[level + 1];
        }

        public static @Nullable Tier fromUpgradeItem(Item item) {
            if (item == UPItems.COPPER_UPGRADE) return COPPER;
            if (item == UPItems.IRON_UPGRADE) return IRON;
            if (item == UPItems.GOLD_UPGRADE) return GOLD;
            if (item == UPItems.DIAMOND_UPGRADE) return DIAMOND;
            return null;
        }

        public @Nullable Item toUpgradeItem() {
            return switch (this) {
                case COPPER -> UPItems.COPPER_UPGRADE;
                case IRON -> UPItems.IRON_UPGRADE;
                case GOLD -> UPItems.GOLD_UPGRADE;
                case DIAMOND -> UPItems.DIAMOND_UPGRADE;
                default -> null;
            };
        }

        public static Tier fromLevel(int level) {
            for (Tier t : values()) if (t.level == level) return t;
            return BASE;
        }
    }

    public static final int SLOT_UPGRADE = 0;
    public static final int SLOT_BULK_OUT = 1;
    public static final int SLOT_BULK_IN = 2;
    public static final int SIZE = 3;

    private static final int[] SLOTS_OUT = {SLOT_BULK_OUT};
    private static final int[] SLOTS_IN = {SLOT_BULK_IN};

    private final NonNullList<ItemStack> inventory = NonNullList.withSize(SIZE, ItemStack.EMPTY);

    private ItemStack storedType = ItemStack.EMPTY;
    private int storedCount = 0;
    private Tier tier = Tier.BASE;

    public BarrelBlockEntity(BlockPos pos, BlockState state) {
        super(UPBlockEntities.BARREL_BE, pos, state);
    }

    public Tier getTier() {
        return tier;
    }

    public ItemStack getStoredType() {
        return storedType;
    }

    public int getStoredCount() {
        return storedCount;
    }

    public int getCapacity() {
        return tier.getCapacity();
    }

    public boolean isFull() {
        return storedCount >= getCapacity();
    }

    public boolean canAccept(ItemStack stack) {
        if (stack.isEmpty()) return false;
        if (storedCount == 0) return true;
        return ItemStack.isSameItemSameComponents(storedType, stack);
    }

    public int insert(ItemStack stack) {
        if (!canAccept(stack) || isFull()) return 0;
        if (storedCount == 0) storedType = stack.copyWithCount(1);
        int space = getCapacity() - storedCount;
        int toInsert = Math.min(stack.getCount(), space);
        storedCount += toInsert;
        stack.shrink(toInsert);
        setChanged();
        return toInsert;
    }

    public ItemStack extract(int amount) {
        if (storedCount == 0 || storedType.isEmpty()) return ItemStack.EMPTY;
        int toExtract = Math.min(amount, storedCount);
        ItemStack result = storedType.copyWithCount(toExtract);
        storedCount -= toExtract;
        if (storedCount == 0) storedType = ItemStack.EMPTY;
        setChanged();
        return result;
    }

    public boolean applyUpgrade(Player player, ItemStack heldStack) {
        Tier incoming = Tier.fromUpgradeItem(heldStack.getItem());
        if (incoming == null) return false;
        if (incoming.getLevel() <= tier.getLevel()) return false;

        Item oldItem = tier.toUpgradeItem();
        if (oldItem != null) {
            ItemStack returnStack = new ItemStack(oldItem);
            if (!player.getInventory().add(returnStack)) {
                player.drop(returnStack, false);
            }
        }

        tier = incoming;
        inventory.set(SLOT_UPGRADE, new ItemStack(incoming.toUpgradeItem()));
        heldStack.shrink(1);
        setChanged();
        return true;
    }

    public boolean canRemoveUpgrade(Tier targetTier) {
        Tier below = targetTier.prev();
        int belowCap = below == null ? UPConfig.get().storageBarrelBaseCapacity : below.getCapacity();
        return storedCount <= belowCap;
    }

    public void removeUpgrade() {
        tier = tier.prev() == null ? Tier.BASE : tier.prev();
        inventory.set(SLOT_UPGRADE, ItemStack.EMPTY);
        setChanged();
    }

    public boolean setUpgradeFromMenu(ItemStack stack) {
        Tier incoming = Tier.fromUpgradeItem(stack.getItem());
        if (incoming == null) return false;
        if (incoming.getLevel() <= tier.getLevel()) return false;
        tier = incoming;
        inventory.set(SLOT_UPGRADE, stack.copyWithCount(1));
        setChanged();
        return true;
    }

    public void restoreFromData(BarrelData data) {
        storedType = data.storedType().copyWithCount(1);
        storedCount = data.storedCount();
        tier = Tier.fromLevel(data.tierLevel());
        Item upgradeItem = tier.toUpgradeItem();
        inventory.set(SLOT_UPGRADE, upgradeItem != null ? new ItemStack(upgradeItem) : ItemStack.EMPTY);
    }

    @Override
    public int[] getSlotsForFace(Direction dir) {
        return dir == Direction.DOWN ? SLOTS_OUT : SLOTS_IN;
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction dir) {
        if (slot != SLOT_BULK_IN) return false;
        return canAccept(stack) && !isFull();
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction dir) {
        if (slot != SLOT_BULK_OUT) return false;
        return storedCount > 0;
    }

    @Override
    public int getContainerSize() { return SIZE; }

    @Override
    public boolean isEmpty() {
        return storedCount == 0 && inventory.get(SLOT_UPGRADE).isEmpty();
    }

    @Override
    public ItemStack getItem(int slot) {
        if (slot == SLOT_UPGRADE) return inventory.get(SLOT_UPGRADE);
        if (slot == SLOT_BULK_OUT && storedCount > 0)
            return storedType.copyWithCount(Math.min(storedCount, storedType.getMaxStackSize()));
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        if (slot == SLOT_UPGRADE) return ContainerHelper.removeItem(inventory, SLOT_UPGRADE, amount);
        if (slot == SLOT_BULK_OUT) return extract(amount);
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        if (slot == SLOT_UPGRADE) return ContainerHelper.takeItem(inventory, SLOT_UPGRADE);
        if (slot == SLOT_BULK_OUT) return extract(storedCount);
        return ItemStack.EMPTY;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        if (slot == SLOT_UPGRADE) {
            inventory.set(SLOT_UPGRADE, stack);
            if (stack.isEmpty()) {
                tier = Tier.BASE;
            } else {
                Tier incoming = Tier.fromUpgradeItem(stack.getItem());
                if (incoming != null) tier = incoming;
            }
            setChanged();
            return;
        }
        if (slot == SLOT_BULK_IN && !stack.isEmpty()) {
            insert(stack);
            return;
        }
        if (slot == SLOT_BULK_OUT) {
            if (stack.isEmpty()) {
                storedCount = 0;
                storedType = ItemStack.EMPTY;
            } else {
                storedType = stack.copyWithCount(1);
                storedCount = stack.getCount();
            }
            setChanged();
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent() {
        inventory.clear();
        storedType = ItemStack.EMPTY;
        storedCount = 0;
        tier = Tier.BASE;
        setChanged();
    }

    public int getComparatorOutput() {
        if (getCapacity() == 0) return 0;
        return storedCount == 0 ? 0 : (int) (((float) storedCount / getCapacity()) * 14) + 1;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, inventory);
        output.putInt("StoredCount", storedCount);
        output.putInt("TierLevel", tier.getLevel());
        if (!storedType.isEmpty()) {
            output.store("StoredType", ItemStack.CODEC, storedType);
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, inventory);
        storedCount = input.getIntOr("StoredCount", 0);
        tier = Tier.fromLevel(input.getIntOr("TierLevel", 0));
        storedType = input.read("StoredType", ItemStack.CODEC).orElse(ItemStack.EMPTY);
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if (level != null && !level.isClientSide()) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

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
        return Component.translatable("block.utilitiesplus.barrel");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new BarrelMenu(id, inv, this);
    }
}