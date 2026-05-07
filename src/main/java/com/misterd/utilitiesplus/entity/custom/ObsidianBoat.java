package com.misterd.utilitiesplus.entity.custom;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ObsidianBoat extends Boat {

    public int passengerFireTicksOnBoard = 0;

    public ObsidianBoat(EntityType<? extends Boat> type, Level level, Supplier<Item> dropItem) {
        super(type, level, dropItem);
    }

    @Override
    protected @Nullable AABB modifyPassengerFluidInteractionBox(AABB passengerBox) {
        return null;
    }

    @Override
    public void igniteForTicks(int ticks) {}

    @Override
    public void setRemainingFireTicks(int ticks) {}

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    protected void addPassenger(Entity passenger) {
        super.addPassenger(passenger);
        passengerFireTicksOnBoard = passenger.getRemainingFireTicks();
    }

    @Override
    protected double rideHeight(EntityDimensions dimensions) {
        return dimensions.height() / 3.0;
    }
}