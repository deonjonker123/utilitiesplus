package com.misterd.utilitiesplus.datagen;

import com.misterd.utilitiesplus.block.UPBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class UPLootTableProvider extends FabricBlockLootSubProvider {
    public UPLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        dropSelf(UPBlocks.ACACIA_BARREL);
        dropSelf(UPBlocks.BIRCH_BARREL);
        dropSelf(UPBlocks.CHERRY_BARREL);
        dropSelf(UPBlocks.CRIMSON_BARREL);
        dropSelf(UPBlocks.DARK_OAK_BARREL);
        dropSelf(UPBlocks.JUNGLE_BARREL);
        dropSelf(UPBlocks.MANGROVE_BARREL);
        dropSelf(UPBlocks.OAK_BARREL);
        dropSelf(UPBlocks.PALE_OAK_BARREL);
        dropSelf(UPBlocks.SPRUCE_BARREL);
        dropSelf(UPBlocks.WARPED_BARREL);
        dropSelf(UPBlocks.SAWBENCH);

        dropSelf(UPBlocks.HARVESTER);
        dropSelf(UPBlocks.KILN);
    }
}
