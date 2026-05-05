package com.misterd.utilitiesplus.gui.custom;

import com.misterd.utilitiesplus.blockentity.custom.KilnBlockEntity;
import com.misterd.utilitiesplus.gui.UPMenuTypes;
import com.misterd.utilitiesplus.util.UPTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class KilnMenu extends AbstractContainerMenu {

    private static final int SLOT_INPUT = 0;
    private static final int SLOT_FUEL = 1;
    private static final int SLOT_OUTPUT = 2;
    private static final int PLAYER_INV_START = 3;
    private static final int PLAYER_INV_END = 30;
    private static final int PLAYER_HB_START = 30;
    private static final int PLAYER_HB_END = 39;

    public final KilnBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public KilnMenu(int id, Inventory inv, BlockPos pos) {
        this(id, inv, (KilnBlockEntity) inv.player.level().getBlockEntity(pos), new SimpleContainerData(4));
    }

    public KilnMenu(int id, Inventory inv, KilnBlockEntity be, ContainerData data) {
        super(UPMenuTypes.KILN_MENU, id);
        this.blockEntity = be;
        this.level = inv.player.level();
        this.data = data;

        addSlot(new Slot(be, SLOT_INPUT, 56, 17) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(UPTags.Items.KILN_SMELTABLES);
            }
        });
        addSlot(new Slot(be, SLOT_FUEL, 56, 53));
        addSlot(new Slot(be, SLOT_OUTPUT, 116, 35));

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        addDataSlots(data);
    }

    public int getLitTime() { return data.get(0); }
    public int getLitDuration() { return data.get(1); }
    public int getCookingProgress() { return data.get(2); }
    public int getCookingTotalTime() { return data.get(3); }

    public boolean isLit() { return getLitDuration() > 0 && getLitTime() > 0; }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot source = slots.get(index);
        if (source == null || !source.hasItem()) return ItemStack.EMPTY;

        ItemStack stack = source.getItem();
        ItemStack copy = stack.copy();

        if (index == SLOT_OUTPUT) {
            if (!moveItemStackTo(stack, PLAYER_INV_START, PLAYER_HB_END, true)) return ItemStack.EMPTY;
            if (player.level() instanceof ServerLevel serverLevel) {
                blockEntity.awardExperience(serverLevel, player.position());
            }
        } else if (index < PLAYER_INV_START) {
            if (!moveItemStackTo(stack, PLAYER_INV_START, PLAYER_HB_END, false)) return ItemStack.EMPTY;
        } else {
            if (!moveItemStackTo(stack, SLOT_INPUT, SLOT_INPUT + 1, false) &&
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