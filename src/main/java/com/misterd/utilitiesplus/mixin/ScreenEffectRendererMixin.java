package com.misterd.utilitiesplus.mixin;

import com.misterd.utilitiesplus.entity.custom.ObsidianBoat;
import com.misterd.utilitiesplus.entity.custom.ObsidianChestBoat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.state.level.PlayerRenderState;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenEffectRenderer.class)
public class ScreenEffectRendererMixin {

    @Inject(method = "submit", at = @At("HEAD"), cancellable = true)
    private void cancelFireOverlay(float partialTicks, SubmitNodeCollector submitNodeCollector, PlayerRenderState playerRenderState, CameraRenderState cameraRenderState, boolean hideGui, CallbackInfo ci) {
        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        boolean inObsidianBoat = player.getVehicle() instanceof ObsidianBoat
                || player.getVehicle() instanceof ObsidianChestBoat;

        if (inObsidianBoat && !playerRenderState.isOnFire) {
            ci.cancel();
        }
    }
}