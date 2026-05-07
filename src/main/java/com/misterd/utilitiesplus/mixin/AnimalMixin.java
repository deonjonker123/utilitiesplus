package com.misterd.utilitiesplus.mixin;

import com.misterd.utilitiesplus.util.FeedingTroughBreedingFlag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.animal.Animal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Animal.class)
public abstract class AnimalMixin {

    @Inject(
            method = "finalizeSpawnChildFromBreeding",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"
            ),
            cancellable = true
    )
    private void suppressTroughBreedingXP(ServerLevel level, Animal partner, AgeableMob offspring, CallbackInfo ci) {
        Animal self = (Animal) (Object) this;
        if (FeedingTroughBreedingFlag.isMarked(self) || FeedingTroughBreedingFlag.isMarked(partner)) {
            FeedingTroughBreedingFlag.clear(self);
            FeedingTroughBreedingFlag.clear(partner);
            ci.cancel();
        }
    }
}