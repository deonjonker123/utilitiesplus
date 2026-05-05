package com.misterd.utilitiesplus.datagen;

import com.misterd.utilitiesplus.block.UPBlocks;
import com.misterd.utilitiesplus.util.UPTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;

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
                .add(UPBlocks.PALE_OAK_BARREL.asItem())
                .add(UPBlocks.SPRUCE_BARREL.asItem())
                .add(UPBlocks.WARPED_BARREL.asItem());

        valueLookupBuilder(UPTags.Items.KILN_SMELTABLES)
                .add(Items.CLAY)
                .add(Items.CLAY_BALL)
                .add(Items.TERRACOTTA)
                .add(Items.WHITE_TERRACOTTA)
                .add(Items.ORANGE_TERRACOTTA)
                .add(Items.MAGENTA_TERRACOTTA)
                .add(Items.LIGHT_BLUE_TERRACOTTA)
                .add(Items.YELLOW_TERRACOTTA)
                .add(Items.LIME_TERRACOTTA)
                .add(Items.PINK_TERRACOTTA)
                .add(Items.GRAY_TERRACOTTA)
                .add(Items.LIGHT_GRAY_TERRACOTTA)
                .add(Items.CYAN_TERRACOTTA)
                .add(Items.PURPLE_TERRACOTTA)
                .add(Items.BLUE_TERRACOTTA)
                .add(Items.BROWN_TERRACOTTA)
                .add(Items.GREEN_TERRACOTTA)
                .add(Items.RED_TERRACOTTA)
                .add(Items.BLACK_TERRACOTTA)
                .add(Items.STONE)
                .add(Items.COBBLESTONE)
                .add(Items.SAND)
                .add(Items.RED_SAND);
    }
}