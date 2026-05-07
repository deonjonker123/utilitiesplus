package com.misterd.utilitiesplus.gui.custom;

import com.misterd.utilitiesplus.blockentity.custom.NestBoxBlockEntity;
import com.misterd.utilitiesplus.gui.UPMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class NestBoxMenu extends AbstractContainerMenu {

    private static final int SLOT_INV_START = 9;
    private static final int SLOT_INV_END = 36;
    private static final int SLOT_HB_START = 36;
    private static final int SLOT_HB_END = 45;

    public final NestBoxBlockEntity blockEntity;
    private final Level level;

    public NestBoxMenu(int id, Inventory inv, BlockPos pos) {
        this(id, inv, (NestBoxBlockEntity) inv.player.level().getBlockEntity(pos));
    }

    public NestBoxMenu(int id, Inventory inv, NestBoxBlockEntity be) {
        super(UPMenuTypes.NEST_BOX_MENU, id);
        this.blockEntity = be;
        this.level = inv.player.level();

        for (int i = 0; i < 9; i++) {
            addSlot(new Slot(be, i, 8 + i * 18, 20) {
                @Override
                public boolean mayPlace(ItemStack stack) { return false; }

                @Override
                public boolean mayPickup(Player player) { return true; }
            });
        }

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot source = slots.get(index);
        if (source == null || !source.hasItem()) return ItemStack.EMPTY;

        ItemStack stack = source.getItem();
        ItemStack copy = stack.copy();

        if (index < 9) {
            if (!moveItemStackTo(stack, SLOT_INV_START, SLOT_HB_END, false)) return ItemStack.EMPTY;
        } else {
            if (!moveItemStackTo(stack, 0, 9, false)) return ItemStack.EMPTY;
        }

        if (stack.isEmpty()) source.set(ItemStack.EMPTY);
        else source.setChanged();

        return copy;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, blockEntity.getBlockState().getBlock());
    }

    private void addPlayerInventory(Inventory inv) {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 9; col++)
                addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 51 + row * 18));
    }

    private void addPlayerHotbar(Inventory inv) {
        for (int col = 0; col < 9; col++)
            addSlot(new Slot(inv, col, 8 + col * 18, 109));
    }
}