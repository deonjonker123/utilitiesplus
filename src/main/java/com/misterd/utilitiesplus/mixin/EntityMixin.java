package com.misterd.utilitiesplus.mixin;

import com.misterd.utilitiesplus.entity.custom.ObsidianBoat;
import com.misterd.utilitiesplus.entity.custom.ObsidianChestBoat;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Inject(method = "lavaIgnite", at = @At("HEAD"), cancellable = true)
    private void cancelLavaIgnite(CallbackInfo ci) {
        Entity self = (Entity) (Object) this;
        if (self.getVehicle() instanceof ObsidianBoat || self.getVehicle() instanceof ObsidianChestBoat) {
            ci.cancel();
        }
    }

    @Inject(method = "lavaHurt", at = @At("HEAD"), cancellable = true)
    private void cancelLavaHurt(CallbackInfo ci) {
        Entity self = (Entity) (Object) this;
        if (self.getVehicle() instanceof ObsidianBoat || self.getVehicle() instanceof ObsidianChestBoat) {
            ci.cancel();
        }
    }
}