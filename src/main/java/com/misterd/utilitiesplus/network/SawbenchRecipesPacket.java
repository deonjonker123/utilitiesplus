package com.misterd.utilitiesplus.network;

import com.misterd.utilitiesplus.UtilitiesPlus;
import com.misterd.utilitiesplus.gui.custom.SawbenchMenu;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public record SawbenchRecipesPacket(List<ItemStack> results) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<SawbenchRecipesPacket> TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "sawbench_recipes"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SawbenchRecipesPacket> STREAM_CODEC =
            StreamCodec.composite(
                    ItemStack.STREAM_CODEC.apply(ByteBufCodecs.list()), SawbenchRecipesPacket::results,
                    SawbenchRecipesPacket::new
            );

    @Override
    public CustomPacketPayload.Type<SawbenchRecipesPacket> type() {
        return TYPE;
    }

    public static void handle(SawbenchRecipesPacket packet, ClientPlayNetworking.Context context) {
        context.client().execute(() -> {
            if (context.client().player.containerMenu instanceof SawbenchMenu menu) {
                menu.setClientRecipes(packet.results());
            }
        });
    }
}