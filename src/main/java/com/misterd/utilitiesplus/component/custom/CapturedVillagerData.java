package com.misterd.utilitiesplus.component.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.Optional;

public record CapturedVillagerData(CompoundTag nbt, String professionName, Optional<String> customName) {

    public static final Codec<CapturedVillagerData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            CompoundTag.CODEC.fieldOf("nbt").forGetter(CapturedVillagerData::nbt),
            Codec.STRING.fieldOf("professionName").forGetter(CapturedVillagerData::professionName),
            Codec.STRING.optionalFieldOf("customName").forGetter(CapturedVillagerData::customName)
    ).apply(instance, CapturedVillagerData::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, CapturedVillagerData> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.COMPOUND_TAG, CapturedVillagerData::nbt,
                    ByteBufCodecs.STRING_UTF8, CapturedVillagerData::professionName,
                    ByteBufCodecs.optional(ByteBufCodecs.STRING_UTF8), CapturedVillagerData::customName,
                    CapturedVillagerData::new
            );
}