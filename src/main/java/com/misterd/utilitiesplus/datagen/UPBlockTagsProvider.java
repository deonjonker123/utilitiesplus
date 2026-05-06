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
                .add(UPBlocks.SAWBENCH)
                .add(UPBlocks.ACACIA_VERTICAL_SLAB)
                .add(UPBlocks.BAMBOO_VERTICAL_SLAB)
                .add(UPBlocks.BIRCH_VERTICAL_SLAB)
                .add(UPBlocks.CHERRY_VERTICAL_SLAB)
                .add(UPBlocks.CRIMSON_VERTICAL_SLAB)
                .add(UPBlocks.DARK_OAK_VERTICAL_SLAB)
                .add(UPBlocks.JUNGLE_VERTICAL_SLAB)
                .add(UPBlocks.MANGROVE_VERTICAL_SLAB)
                .add(UPBlocks.OAK_VERTICAL_SLAB)
                .add(UPBlocks.PALE_OAK_VERTICAL_SLAB)
                .add(UPBlocks.SPRUCE_VERTICAL_SLAB)
                .add(UPBlocks.WARPED_VERTICAL_SLAB)
                .add(UPBlocks.ACACIA_BEAM)
                .add(UPBlocks.BAMBOO_BEAM)
                .add(UPBlocks.BIRCH_BEAM)
                .add(UPBlocks.CHERRY_BEAM)
                .add(UPBlocks.CRIMSON_BEAM)
                .add(UPBlocks.DARK_OAK_BEAM)
                .add(UPBlocks.JUNGLE_BEAM)
                .add(UPBlocks.MANGROVE_BEAM)
                .add(UPBlocks.OAK_BEAM)
                .add(UPBlocks.PALE_OAK_BEAM)
                .add(UPBlocks.SPRUCE_BEAM)
                .add(UPBlocks.WARPED_BEAM);

        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(UPBlocks.HARVESTER)
                .add(UPBlocks.KILN)
                .add(UPBlocks.FILTERED_HOPPER)
                .add(UPBlocks.FAST_HOPPER);
    }
}
