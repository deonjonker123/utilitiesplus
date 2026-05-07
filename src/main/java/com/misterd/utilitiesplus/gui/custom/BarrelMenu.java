package com.misterd.utilitiesplus.gui.custom;

import com.misterd.utilitiesplus.blockentity.custom.BarrelBlockEntity;
import com.misterd.utilitiesplus.blockentity.custom.BarrelBlockEntity.Tier;
import com.misterd.utilitiesplus.gui.UPMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class BarrelMenu extends AbstractContainerMenu {

    private static final int SLOT_UPGRADE = 0;
    private static final int PLAYER_INV_START = 1;
    private static final int PLAYER_INV_END = 28;
    private static final int PLAYER_HB_START = 28;
    private static final int PLAYER_HB_END = 37;

    public final BarrelBlockEntity blockEntity;

    public BarrelMenu(int id, Inventory inv, BlockPos pos) {
        this(id, inv, (BarrelBlockEntity) inv.player.level().getBlockEntity(pos));
    }

    public BarrelMenu(int id, Inventory inv, BarrelBlockEntity be) {
        super(UPMenuTypes.BARREL_MENU, id);
        this.blockEntity = be;

        // Upgrade slot — restricted placement and removal
        addSlot(new Slot(be, BarrelBlockEntity.SLOT_UPGRADE, 152, 61) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                Tier incoming = Tier.fromUpgradeItem(stack.getItem());
                if (incoming == null) return false;
                // Only allow placing a higher tier than current
                return incoming.getLevel() > be.getTier().getLevel();
            }

            @Override
            public boolean mayPickup(Player player) {
                // Can only remove the upgrade if the stored count fits in the tier below
                return be.canRemoveUpgrade(be.getTier());
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                be.removeUpgrade();
                super.onTake(player, stack);
            }

            @Override
            public void set(ItemStack stack) {
                if (!stack.isEmpty()) {
                    be.setUpgradeFromMenu(stack);
                }
                super.set(stack);
            }

            @Override
            public int getMaxStackSize() { return 1; }
        });

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot source = slots.get(index);
        if (source == null || !source.hasItem()) return ItemStack.EMPTY;

        ItemStack stack = source.getItem();
        ItemStack copy = stack.copy();

        if (index == SLOT_UPGRADE) {
            // Shift-click upgrade out to player inventory
            if (!moveItemStackTo(stack, PLAYER_INV_START, PLAYER_HB_END, true)) return ItemStack.EMPTY;
        } else {
            // Shift-click from player inventory — try to place into upgrade slot if it's an upgrade item
            if (Tier.fromUpgradeItem(stack.getItem()) != null) {
                if (!moveItemStackTo(stack, SLOT_UPGRADE, SLOT_UPGRADE + 1, false)) return ItemStack.EMPTY;
            } else {
                return ItemStack.EMPTY;
            }
        }

        if (stack.isEmpty()) source.setByPlayer(ItemStack.EMPTY);
        else source.setChanged();

        return copy;
    }

    @Override
    public boolean stillValid(Player player) {
        return blockEntity.stillValid(player);
    }

    private void addPlayerInventory(Inventory inv) {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 9; col++)
                addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 94 + row * 18));
    }

    private void addPlayerHotbar(Inventory inv) {
        for (int col = 0; col < 9; col++)
            addSlot(new Slot(inv, col, 8 + col * 18, 152));
    }
}