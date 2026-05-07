package com.misterd.utilitiesplus.component.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

public record BarrelData(ItemStack storedType, int storedCount, int tierLevel) {

    public static final BarrelData EMPTY = new BarrelData(ItemStack.EMPTY, 0, 0);

    public static final Codec<BarrelData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ItemStack.OPTIONAL_CODEC.fieldOf("storedType").forGetter(BarrelData::storedType),
            Codec.INT.fieldOf("storedCount").forGetter(BarrelData::storedCount),
            Codec.INT.fieldOf("tierLevel").forGetter(BarrelData::tierLevel)
    ).apply(instance, BarrelData::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, BarrelData> STREAM_CODEC =
            StreamCodec.composite(
                    ItemStack.OPTIONAL_STREAM_CODEC, BarrelData::storedType,
                    ByteBufCodecs.INT, BarrelData::storedCount,
                    ByteBufCodecs.INT, BarrelData::tierLevel,
                    BarrelData::new
            );
}