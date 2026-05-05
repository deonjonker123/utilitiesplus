package com.misterd.utilitiesplus.blockentity;

import com.misterd.utilitiesplus.UtilitiesPlus;
import com.misterd.utilitiesplus.block.UPBlocks;
import com.misterd.utilitiesplus.blockentity.custom.KilnBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class UPBlockEntities {

    public static final BlockEntityType<KilnBlockEntity> KILN_BE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "kiln_be"),
                    FabricBlockEntityTypeBuilder.create(KilnBlockEntity::new, UPBlocks.KILN).build());

    public static void register() {}
}