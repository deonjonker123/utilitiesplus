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

    @Inject(
            method = "renderScreenEffect",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ScreenEffectRenderer;renderFire(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;)V"),
            cancellable = true
    )
    private void cancelFireOverlay(boolean isFirstPerson, boolean isSleeping, float partialTicks, SubmitNodeCollector submitNodeCollector, boolean hideGui, CallbackInfo ci) {
        Player player = this.minecraft.player;
        boolean inObsidianBoat = player.getVehicle() instanceof ObsidianBoat || player.getVehicle() instanceof ObsidianChestBoat;
        if (inObsidianBoat && !player.isOnFire()) {
            ci.cancel();
        }
        System.out.println("fire ticks: " + player.getRemainingFireTicks() + " inBoat: " + inObsidianBoat + " isOnFire: " + player.isOnFire());
    }
}