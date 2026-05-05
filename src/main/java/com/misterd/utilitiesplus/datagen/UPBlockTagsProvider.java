package com.misterd.utilitiesplus.datagen;

import com.misterd.utilitiesplus.block.UPBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class UPBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public UPBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
                .add(UPBlocks.ACACIA_BARREL)
                .add(UPBlocks.BIRCH_BARREL)
                .add(UPBlocks.CHERRY_BARREL)
                .add(UPBlocks.CRIMSON_BARREL)
                .add(UPBlocks.DARK_OAK_BARREL)
                .add(UPBlocks.JUNGLE_BARREL)
                .add(UPBlocks.MANGROVE_BARREL)
                .add(UPBlocks.OAK_BARREL)
                .add(UPBlocks.PALE_OAK_BARREL)
                .add(UPBlocks.SPRUCE_BARREL)
                .add(UPBlocks.WARPED_BARREL)
                .add(UPBlocks.SAWBENCH);

        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(UPBlocks.HARVESTER)
                .add(UPBlocks.KILN);
    }
}
