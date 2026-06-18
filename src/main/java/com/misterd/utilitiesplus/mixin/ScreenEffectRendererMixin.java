package com.misterd.utilitiesplus.mixin;

import com.misterd.utilitiesplus.entity.custom.ObsidianBoat;
import com.misterd.utilitiesplus.entity.custom.ObsidianChestBoat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenEffectRenderer.class)
public class ScreenEffectRendererMixin {

    @Shadow private Minecraft minecraft;

    @Inject(method = "submit", at = @At("HEAD"), cancellable = true)
    private void cancelFireOverlay(boolean isFirstPerson, boolean isSleeping, float partialTicks, SubmitNodeCollector submitNodeCollector, boolean hideGui, CallbackInfo ci) {
        Player player = this.minecraft.player;
        boolean inObsidianBoat = player.getVehicle() instanceof ObsidianBoat || player.getVehicle() instanceof ObsidianChestBoat;
        if (inObsidianBoat && !player.isOnFire()) {
            ci.cancel();
        }
    }
}