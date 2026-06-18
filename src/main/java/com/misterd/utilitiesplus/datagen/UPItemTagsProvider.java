package com.misterd.utilitiesplus.datagen;

import com.misterd.utilitiesplus.block.UPBlocks;
import com.misterd.utilitiesplus.util.UPTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class UPItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {

    public UPItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    private static ResourceKey<Item> key(Item item) {
        return item.builtInRegistryHolder().key();
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        tag(UPTags.Items.BARREL_BLOCK_ITEMS)
                .add(key(UPBlocks.ACACIA_BARREL.asItem()))
                .add(key(UPBlocks.BIRCH_BARREL.asItem()))
                .add(key(UPBlocks.CHERRY_BARREL.asItem()))
                .add(key(UPBlocks.CRIMSON_BARREL.asItem()))
                .add(key(UPBlocks.DARK_OAK_BARREL.asItem()))
                .add(key(UPBlocks.JUNGLE_BARREL.asItem()))
                .add(key(UPBlocks.MANGROVE_BARREL.asItem()))
                .add(key(UPBlocks.OAK_BARREL.asItem()))
                .add(key(UPBlocks.PALE_OAK_BARREL.asItem()))
                .add(key(UPBlocks.SPRUCE_BARREL.asItem()))
                .add(key(UPBlocks.WARPED_BARREL.asItem()));

        tag(UPTags.Items.KILN_SMELTABLES)
                .add(key(Items.CLAY))
                .add(key(Items.CLAY_BALL))
                .addAll(Items.DYED_TERRACOTTA.map(UPItemTagsProvider::key).asList())
                .add(key(Items.TERRACOTTA))
                .add(key(Items.STONE))
                .add(key(Items.COBBLESTONE))
                .add(key(Items.SAND))
                .add(key(Items.RED_SAND));

        tag(UPTags.Items.ANIMAL_FEED)
                .add(key(Items.CARROT))
                .add(key(Items.POTATO))
                .add(key(Items.BEETROOT))
                .add(key(Items.GOLDEN_APPLE))
                .add(key(Items.GOLDEN_CARROT))
                .add(key(Items.ENCHANTED_GOLDEN_APPLE))
                .add(key(Items.WHEAT))
                .add(key(Items.WHEAT_SEEDS))
                .add(key(Items.PUMPKIN_SEEDS))
                .add(key(Items.MELON_SEEDS))
                .add(key(Items.BEETROOT_SEEDS))
                .add(key(Items.TORCHFLOWER_SEEDS))
                .add(key(Items.PITCHER_POD))
                .add(key(Items.HAY_BLOCK))
                .add(key(Items.SEAGRASS))
                .add(key(Items.BAMBOO))
                .add(key(Items.CACTUS))
                .add(key(Items.WARPED_FUNGUS))
                .add(key(Items.CRIMSON_FUNGUS))
                .add(key(Items.SLIME_BALL))
                .add(key(Items.SPIDER_EYE))

                .add(key(Items.DANDELION))
                .add(key(Items.POPPY))
                .add(key(Items.BLUE_ORCHID))
                .add(key(Items.ALLIUM))
                .add(key(Items.AZURE_BLUET))
                .add(key(Items.ORANGE_TULIP))
                .add(key(Items.PINK_TULIP))
                .add(key(Items.RED_TULIP))
                .add(key(Items.ORANGE_TULIP))
                .add(key(Items.WHITE_TULIP))
                .add(key(Items.OXEYE_DAISY))
                .add(key(Items.CORNFLOWER))
                .add(key(Items.LILY_OF_THE_VALLEY))
                .add(key(Items.TORCHFLOWER))
                .add(key(Items.SUNFLOWER))
                .add(key(Items.LILAC))
                .add(key(Items.ROSE_BUSH))
                .add(key(Items.PEONY))
                .add(key(Items.OPEN_EYEBLOSSOM))
                .add(key(Items.CLOSED_EYEBLOSSOM));

        tag(UPTags.Items.SAWBENCH_INPUTS)
                .forceAddTag(ItemTags.LOGS)
                .forceAddTag(ItemTags.PLANKS)
                .forceAddTag(ItemTags.BAMBOO_BLOCKS)
                .add(key(Items.BAMBOO_MOSAIC));
    }
}