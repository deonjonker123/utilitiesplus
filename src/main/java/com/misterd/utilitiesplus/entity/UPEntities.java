package com.misterd.utilitiesplus.entity;

import com.misterd.utilitiesplus.UtilitiesPlus;
import com.misterd.utilitiesplus.entity.custom.ObsidianBoat;
import com.misterd.utilitiesplus.entity.custom.ObsidianChestBoat;
import com.misterd.utilitiesplus.item.UPItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class UPEntities {

    public static final ResourceKey<EntityType<?>> OBSIDIAN_BOAT_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "obsidian_boat"));

    public static final ResourceKey<EntityType<?>> OBSIDIAN_CHEST_BOAT_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "obsidian_chest_boat"));

    public static final EntityType<ObsidianBoat> OBSIDIAN_BOAT = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "obsidian_boat"),
            EntityType.Builder.<ObsidianBoat>of((entityType, level) -> new ObsidianBoat(entityType, level, () -> UPItems.OBSIDIAN_BOAT), MobCategory.MISC)
                    .sized(1.375F, 0.5625F).clientTrackingRange(10).fireImmune().build(OBSIDIAN_BOAT_KEY));

    public static final EntityType<ObsidianChestBoat> OBSIDIAN_CHEST_BOAT = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "obsidian_chest_boat"),
            EntityType.Builder.<ObsidianChestBoat>of((entityType, level) -> new ObsidianChestBoat(entityType, level, () -> UPItems.OBSIDIAN_CHEST_BOAT), MobCategory.MISC)
                    .sized(1.375F, 0.5625F).clientTrackingRange(10).fireImmune().build(OBSIDIAN_CHEST_BOAT_KEY));

    public static void register() {}
}