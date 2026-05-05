package com.misterd.utilitiesplus.datagen;

import com.misterd.utilitiesplus.block.UPBlocks;
import com.misterd.utilitiesplus.util.UPTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class UPItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {

    public UPItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        valueLookupBuilder(UPTags.Items.BARREL_BLOCK_ITEMS)
                .add(UPBlocks.ACACIA_BARREL.asItem())
                .add(UPBlocks.BIRCH_BARREL.asItem())
                .add(UPBlocks.CHERRY_BARREL.asItem())
                .add(UPBlocks.CRIMSON_BARREL.asItem())
                .add(UPBlocks.DARK_OAK_BARREL.asItem())
                .add(UPBlocks.JUNGLE_BARREL.asItem())
                .add(UPBlocks.MANGROVE_BARREL.asItem())
                .add(UPBlocks.OAK_BARREL.asItem())
                .add(UPBlocks.SPRUCE_BARREL.asItem())
                .add(UPBlocks.WARPED_BARREL.asItem());
    }
}
