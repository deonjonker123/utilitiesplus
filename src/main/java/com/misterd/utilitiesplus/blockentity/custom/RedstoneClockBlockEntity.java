package com.misterd.utilitiesplus.blockentity.custom;

import com.misterd.utilitiesplus.blockentity.UPBlockEntities;
import com.misterd.utilitiesplus.config.UPConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class RedstoneClockBlockEntity extends BlockEntity {

    private int interval;
    private int tickCounter = 0;

    public RedstoneClockBlockEntity(BlockPos pos, BlockState state) {
        super(UPBlockEntities.REDSTONE_CLOCK_BE, pos, state);
        this.interval = UPConfig.get().redstoneClockInterval;
    }

    public int getInterval() { return interval; }

    public void resetInterval() {
        interval = UPConfig.get().redstoneClockInterval;
        tickCounter = 0;
        setChanged();
    }

    public void adjustInterval(boolean increase) {
        if (increase) {
            interval = Math.min(300, interval + 10);
        } else {
            interval = Math.max(2, interval - 10);
        }
        tickCounter = 0;
        setChanged();
    }

    public boolean onTick() {
        tickCounter++;
        if (tickCounter >= interval) {
            tickCounter = 0;
            return true;
        }
        return false;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("Interval", interval);
        output.putInt("TickCounter", tickCounter);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        interval = input.getIntOr("Interval", UPConfig.get().redstoneClockInterval);
        tickCounter = input.getIntOr("TickCounter", 0);
    }
}