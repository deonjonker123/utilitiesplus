package com.misterd.utilitiesplus.gui.custom;

import com.misterd.utilitiesplus.blockentity.custom.HarvesterBlockEntity;
import com.misterd.utilitiesplus.gui.UPMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HarvesterMenu extends AbstractContainerMenu {

    private static final int SLOT_HOE = 0;
    private static final int SLOT_FUEL = 1;
    private static final int PLAYER_INV_START = 2;
    private static final int PLAYER_INV_END = 29;
    private static final int PLAYER_HB_START = 29;
    private static final int PLAYER_HB_END = 38;

    public final HarvesterBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public HarvesterMenu(int id, Inventory inv, BlockPos pos) {
        this(id, inv, (HarvesterBlockEntity) inv.player.level().getBlockEntity(pos), new SimpleContainerData(2));
    }

    public HarvesterMenu(int id, Inventory inv, HarvesterBlockEntity be, ContainerData data) {
        super(UPMenuTypes.HARVESTER_MENU, id);
        this.blockEntity = be;
        this.level = inv.player.level();
        this.data = data;

        addSlot(new Slot(be, SLOT_HOE, 80, 17) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ItemTags.HOES);
            }

            @Override
            public int getMaxStackSize() { return 1; }
        });

        addSlot(new Slot(be, SLOT_FUEL, 80, 37) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return level.fuelValues().isFuel(stack);
            }
        });

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        addDataSlots(data);
    }

    public int getLitTime() { return data.get(0); }
    public int getLitDuration() { return data.get(1); }
    public boolean isLit() { return getLitDuration() > 0 && getLitTime() > 0; }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot source = slots.get(index);
        if (source == null || !source.hasItem()) return ItemStack.EMPTY;

        ItemStack stack = source.getItem();
        ItemStack copy = stack.copy();

        if (index < PLAYER_INV_START) {
            if (!moveItemStackTo(stack, PLAYER_INV_START, PLAYER_HB_END, false)) return ItemStack.EMPTY;
        } else {
            if (!moveItemStackTo(stack, SLOT_HOE, SLOT_HOE + 1, false) &&
                    !moveItemStackTo(stack, SLOT_FUEL, SLOT_FUEL + 1, false)) {
                if (index < PLAYER_HB_START) {
                    if (!moveItemStackTo(stack, PLAYER_HB_START, PLAYER_HB_END, false)) return ItemStack.EMPTY;
                } else {
                    if (!moveItemStackTo(stack, PLAYER_INV_START, PLAYER_INV_END, false)) return ItemStack.EMPTY;
                }
            }
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
                addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
    }

    private void addPlayerHotbar(Inventory inv) {
        for (int col = 0; col < 9; col++)
            addSlot(new Slot(inv, col, 8 + col * 18, 142));
    }
}