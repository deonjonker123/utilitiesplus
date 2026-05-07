package com.misterd.utilitiesplus.mixin;

import com.misterd.utilitiesplus.component.UPDataComponents;
import com.misterd.utilitiesplus.item.UPItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Villager.class)
public abstract class VillagerInteractMixin {

    @Inject(
            method = "mobInteract",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onMobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack stack = player.getItemInHand(hand);
        if (!stack.is(UPItems.VILLAGER_CATCHER)) return;
        if (stack.has(UPDataComponents.VILLAGER_DATA)) return;
        cir.setReturnValue(InteractionResult.PASS);
    }
}