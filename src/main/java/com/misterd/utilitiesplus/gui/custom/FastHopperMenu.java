package com.misterd.utilitiesplus.gui.custom;

import com.misterd.utilitiesplus.blockentity.custom.FastHopperBlockEntity;
import com.misterd.utilitiesplus.gui.UPMenuTypes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class FastHopperMenu extends AbstractContainerMenu {

    private static final int SLOT_FILTER = 0;
    private static final int SLOT_INV_START = 1;
    private static final int SLOT_INV_END = 6;
    private static final int PLAYER_INV_START = 6;
    private static final int PLAYER_HB_END = 42;

    private final Container container;

    public FastHopperMenu(int id, Inventory inv) {
        this(id, inv, new SimpleContainer(FastHopperBlockEntity.SIZE));
    }

    public FastHopperMenu(int id, Inventory inv, Container container) {
        super(UPMenuTypes.FAST_HOPPER_MENU, id);
        this.container = container;
        checkContainerSize(container, FastHopperBlockEntity.SIZE);

        addSlot(new Slot(container, SLOT_FILTER, 26, 20) {
            @Override
            public int getMaxStackSize() { return 1; }
        });

        for (int i = 0; i < 5; i++) {
            addSlot(new Slot(container, SLOT_INV_START + i, 62 + i * 18, 20));
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

        if (index < SLOT_INV_END) {
            if (!moveItemStackTo(stack, PLAYER_INV_START, PLAYER_HB_END, true)) return ItemStack.EMPTY;
        } else {
            if (!moveItemStackTo(stack, SLOT_INV_START, SLOT_INV_END, false)) return ItemStack.EMPTY;
        }

        if (stack.isEmpty()) source.setByPlayer(ItemStack.EMPTY);
        else source.setChanged();

        return copy;
    }

    @Override
    public boolean stillValid(Player player) {
        if (container instanceof FastHopperBlockEntity be) {
            return stillValid(ContainerLevelAccess.create(be.getLevel(), be.getBlockPos()), player, be.getBlockState().getBlock());
        }
        return true;
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