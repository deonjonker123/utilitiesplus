package com.misterd.utilitiesplus.gui.custom;

import com.misterd.utilitiesplus.gui.UPMenuTypes;
import com.misterd.utilitiesplus.util.UPTags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class FeedingTroughMenu extends AbstractContainerMenu {
    private static final int FEED_SLOT = 0;
    private static final int PLAYER_INV_START = 1;
    private static final int PLAYER_HB_END = 36;

    private final Container inventory;

    public FeedingTroughMenu (int id, Inventory inv, BlockPos pos) {
        this(id, inv, inv.player.level().getBlockEntity(pos));
    }

    public FeedingTroughMenu (int id, Inventory inv, BlockEntity be) {
        super(UPMenuTypes.FEEDING_TROUGH_MENU, id);
        this.inventory = ((Container) be);

        addSlot(new Slot(inventory, FEED_SLOT, 80, 20) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(UPTags.Items.ANIMAL_FEED);
            }
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

        if (index == FEED_SLOT) {
            if (!moveItemStackTo(stack, PLAYER_INV_START, PLAYER_HB_END + 1, false))
                return ItemStack.EMPTY;
        } else {
            if (!moveItemStackTo(stack, FEED_SLOT, FEED_SLOT + 1, false))
                return ItemStack.EMPTY;
        }

        if (stack.isEmpty()) source.setByPlayer(ItemStack.EMPTY);
        else source.setChanged();

        return copy;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.inventory.stillValid(player);
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 51 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 109));
        }
    }
}