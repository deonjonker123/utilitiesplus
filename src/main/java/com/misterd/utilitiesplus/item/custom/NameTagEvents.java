package com.misterd.utilitiesplus.item.custom;

import com.misterd.utilitiesplus.gui.custom.NameTagScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class NameTagEvents {

    public static void register() {
        UseItemCallback.EVENT.register((player, level, hand) -> {
            if (hand != InteractionHand.MAIN_HAND) return InteractionResult.PASS;
            if (!player.getItemInHand(hand).is(Items.NAME_TAG)) return InteractionResult.PASS;

            if (level.isClientSide()) {
                openScreen(player.getItemInHand(hand));
            }

            return InteractionResult.SUCCESS;
        });
    }

    @Environment(EnvType.CLIENT)
    private static void openScreen(ItemStack stack) {
        Minecraft.getInstance().setScreen(new NameTagScreen(stack));
    }
}