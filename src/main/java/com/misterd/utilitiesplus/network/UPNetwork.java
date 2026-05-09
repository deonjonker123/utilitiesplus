package com.misterd.utilitiesplus.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class UPNetwork {

    public static void register() {
        PayloadTypeRegistry.serverboundPlay().register(
                NameTagNamePacket.TYPE,
                NameTagNamePacket.STREAM_CODEC);

        PayloadTypeRegistry.clientboundPlay().register(
                SawbenchRecipesPacket.TYPE,
                SawbenchRecipesPacket.STREAM_CODEC);

        ServerPlayNetworking.registerGlobalReceiver(
                NameTagNamePacket.TYPE,
                (packet, context) -> NameTagNamePacket.handle(packet, context.player()));
    }

    public static void registerClient() {
        ClientPlayNetworking.registerGlobalReceiver(
                SawbenchRecipesPacket.TYPE,
                SawbenchRecipesPacket::handle);
    }
}