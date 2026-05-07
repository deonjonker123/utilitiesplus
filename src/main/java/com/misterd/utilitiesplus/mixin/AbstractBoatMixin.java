package com.misterd.utilitiesplus.mixin;

import com.misterd.utilitiesplus.entity.custom.ObsidianBoat;
import com.misterd.utilitiesplus.entity.custom.ObsidianChestBoat;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBoat.class)
public abstract class AbstractBoatMixin {

    @Shadow
    private double waterLevel;

    private boolean isObsidianBoat() {
        return (Object) this instanceof ObsidianBoat || (Object) this instanceof ObsidianChestBoat;
    }

    @Redirect(
            method = "checkInWater",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/material/FluidState;is(Lnet/minecraft/tags/TagKey;)Z")
    )
    private boolean redirectCheckInWater(FluidState fluidState, TagKey<Fluid> tag) {
        if (isObsidianBoat()) return fluidState.is(FluidTags.LAVA);
        return fluidState.is(tag);
    }

    @Redirect(
            method = "checkInWater",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/material/FluidState;getHeight(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)F")
    )
    private float redirectCheckInWaterHeight(FluidState fluidState, BlockGetter level, BlockPos pos) {
        if (isObsidianBoat() && fluidState.is(FluidTags.LAVA)) return 1.0f;
        return fluidState.getHeight(level, pos);
    }

    @Inject(method = "checkInWater", at = @At("RETURN"))
    private void adjustWaterLevel(CallbackInfoReturnable<Boolean> cir) {
        if (isObsidianBoat() && cir.getReturnValue()) {
            this.waterLevel += 0.125;
        }
    }

    @Redirect(
            method = "isUnderwater",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/material/FluidState;is(Lnet/minecraft/tags/TagKey;)Z")
    )
    private boolean redirectIsUnderwater(FluidState fluidState, TagKey<Fluid> tag) {
        if (isObsidianBoat()) return fluidState.is(FluidTags.LAVA);
        return fluidState.is(tag);
    }

    @Redirect(
            method = "getWaterLevelAbove",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/material/FluidState;is(Lnet/minecraft/tags/TagKey;)Z")
    )
    private boolean redirectGetWaterLevelAbove(FluidState fluidState, TagKey<Fluid> tag) {
        if (isObsidianBoat()) return fluidState.is(FluidTags.LAVA);
        return fluidState.is(tag);
    }

    @Redirect(
            method = "getWaterLevelAbove",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/material/FluidState;getHeight(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)F")
    )
    private float redirectGetWaterLevelAboveHeight(FluidState fluidState, BlockGetter level, BlockPos pos) {
        if (isObsidianBoat() && fluidState.is(FluidTags.LAVA)) return 1.0f;
        return fluidState.getHeight(level, pos);
    }
}