package com.misterd.utilitiesplus.datagen;

import com.misterd.utilitiesplus.block.UPBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class UPBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public UPBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    private static ResourceKey<Block> key(Block block) {
        return block.builtInRegistryHolder().key();
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(key(UPBlocks.ACACIA_BARREL))
                .add(key(UPBlocks.BIRCH_BARREL))
                .add(key(UPBlocks.CHERRY_BARREL))
                .add(key(UPBlocks.CRIMSON_BARREL))
                .add(key(UPBlocks.DARK_OAK_BARREL))
                .add(key(UPBlocks.JUNGLE_BARREL))
                .add(key(UPBlocks.MANGROVE_BARREL))
                .add(key(UPBlocks.OAK_BARREL))
                .add(key(UPBlocks.PALE_OAK_BARREL))
                .add(key(UPBlocks.SPRUCE_BARREL))
                .add(key(UPBlocks.WARPED_BARREL))
                .add(key(UPBlocks.SAWBENCH))
                .add(key(UPBlocks.ACACIA_VERTICAL_SLAB))
                .add(key(UPBlocks.BAMBOO_VERTICAL_SLAB))
                .add(key(UPBlocks.BIRCH_VERTICAL_SLAB))
                .add(key(UPBlocks.CHERRY_VERTICAL_SLAB))
                .add(key(UPBlocks.CRIMSON_VERTICAL_SLAB))
                .add(key(UPBlocks.DARK_OAK_VERTICAL_SLAB))
                .add(key(UPBlocks.JUNGLE_VERTICAL_SLAB))
                .add(key(UPBlocks.MANGROVE_VERTICAL_SLAB))
                .add(key(UPBlocks.OAK_VERTICAL_SLAB))
                .add(key(UPBlocks.PALE_OAK_VERTICAL_SLAB))
                .add(key(UPBlocks.SPRUCE_VERTICAL_SLAB))
                .add(key(UPBlocks.WARPED_VERTICAL_SLAB))
                .add(key(UPBlocks.ACACIA_BEAM))
                .add(key(UPBlocks.BAMBOO_BEAM))
                .add(key(UPBlocks.BIRCH_BEAM))
                .add(key(UPBlocks.CHERRY_BEAM))
                .add(key(UPBlocks.CRIMSON_BEAM))
                .add(key(UPBlocks.DARK_OAK_BEAM))
                .add(key(UPBlocks.JUNGLE_BEAM))
                .add(key(UPBlocks.MANGROVE_BEAM))
                .add(key(UPBlocks.OAK_BEAM))
                .add(key(UPBlocks.PALE_OAK_BEAM))
                .add(key(UPBlocks.SPRUCE_BEAM))
                .add(key(UPBlocks.WARPED_BEAM))
                .add(key(UPBlocks.FEEDING_TROUGH))
                .add(key(UPBlocks.NEST_BOX));

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(key(UPBlocks.LANTERN_BRACKET))
                .add(key(UPBlocks.HARVESTER))
                .add(key(UPBlocks.KILN))
                .add(key(UPBlocks.REDSTONE_CLOCK))
                .add(key(UPBlocks.FILTERED_HOPPER))
                .add(key(UPBlocks.FAN))
                .add(key(UPBlocks.FAST_HOPPER));
    }
}