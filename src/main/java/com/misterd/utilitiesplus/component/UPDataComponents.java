package com.misterd.utilitiesplus.component;

import com.misterd.utilitiesplus.UtilitiesPlus;
import com.misterd.utilitiesplus.component.custom.CapturedVillagerData;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class UPDataComponents {

    public static final DataComponentType<CapturedVillagerData> VILLAGER_DATA =
            Registry.register(
                    BuiltInRegistries.DATA_COMPONENT_TYPE,
                    Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "villager_data"),
                    DataComponentType.<CapturedVillagerData>builder()
                            .persistent(CapturedVillagerData.CODEC)
                            .networkSynchronized(CapturedVillagerData.STREAM_CODEC)
                            .build()
            );

    public static void register() {}
}