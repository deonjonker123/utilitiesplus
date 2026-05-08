package com.misterd.utilitiesplus.network;

import com.misterd.utilitiesplus.UtilitiesPlus;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Items;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

public record NameTagNamePacket(String name) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<NameTagNamePacket> TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "name_tag_name"));

    public static final StreamCodec<ByteBuf, NameTagNamePacket> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8, NameTagNamePacket::name,
                    NameTagNamePacket::new);

    @Override
    public CustomPacketPayload.Type<NameTagNamePacket> type() {
        return TYPE;
    }

    public static void handle(NameTagNamePacket packet, ServerPlayer player) {
        ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (!stack.is(Items.NAME_TAG)) return;
        if (packet.name().isBlank()) return;

        stack.set(DataComponents.CUSTOM_NAME,
                Component.literal(packet.name()));
    }
}