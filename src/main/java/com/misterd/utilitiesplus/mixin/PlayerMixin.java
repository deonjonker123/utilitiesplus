package com.misterd.utilitiesplus.mixin;

import com.misterd.utilitiesplus.event.custom.PlayerFenceJumpEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class PlayerMixin {
    @Inject(at = @At("TAIL"), method = "jumpFromGround()V")
    private void jumpoverfences_jumpFromGround(CallbackInfo info) {
        if ((Object) this instanceof Player player) {
            PlayerFenceJumpEvent.EVENT.invoker().jump(player);
        }
    }
}
