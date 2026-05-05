package com.misterd.utilitiesplus.entity.client;

import com.misterd.utilitiesplus.UtilitiesPlus;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

public class UPModelLayerLocations {
    public static final ModelLayerLocation OBSIDIAN_BOAT =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "boat/obsidian_boat"), "main");

    public static final ModelLayerLocation OBSIDIAN_CHEST_BOAT =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "chest_boat/obsidian_boat"), "main");
}
