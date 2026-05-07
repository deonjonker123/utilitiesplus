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

        valueLookupBuilder(UPTags.Items.ANIMAL_FEED)
                .add(Items.CARROT)
                .add(Items.POTATO)
                .add(Items.BEETROOT)
                .add(Items.GOLDEN_APPLE)
                .add(Items.GOLDEN_CARROT)
                .add(Items.ENCHANTED_GOLDEN_APPLE)
                .add(Items.WHEAT)
                .add(Items.WHEAT_SEEDS)
                .add(Items.PUMPKIN_SEEDS)
                .add(Items.MELON_SEEDS)
                .add(Items.BEETROOT_SEEDS)
                .add(Items.TORCHFLOWER_SEEDS)
                .add(Items.PITCHER_POD)
                .add(Items.HAY_BLOCK)
                .add(Items.SEAGRASS)
                .add(Items.BAMBOO)
                .add(Items.CACTUS)
                .add(Items.WARPED_FUNGUS)
                .add(Items.CRIMSON_FUNGUS)
                .add(Items.SLIME_BALL)
                .add(Items.SPIDER_EYE)

                .add(Items.DANDELION)
                .add(Items.POPPY)
                .add(Items.BLUE_ORCHID)
                .add(Items.ALLIUM)
                .add(Items.AZURE_BLUET)
                .add(Items.ORANGE_TULIP)
                .add(Items.PINK_TULIP)
                .add(Items.RED_TULIP)
                .add(Items.ORANGE_TULIP)
                .add(Items.WHITE_TULIP)
                .add(Items.OXEYE_DAISY)
                .add(Items.CORNFLOWER)
                .add(Items.LILY_OF_THE_VALLEY)
                .add(Items.TORCHFLOWER)
                .add(Items.SUNFLOWER)
                .add(Items.LILAC)
                .add(Items.ROSE_BUSH)
                .add(Items.PEONY)
                .add(Items.OPEN_EYEBLOSSOM)
                .add(Items.CLOSED_EYEBLOSSOM);
    }
}