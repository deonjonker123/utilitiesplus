package com.misterd.utilitiesplus.datagen;

import com.misterd.utilitiesplus.block.UPBlocks;
import com.misterd.utilitiesplus.item.UPItems;
import com.misterd.utilitiesplus.util.UPTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class UPRecipeProvider extends FabricRecipeProvider {
    public UPRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        return new RecipeProvider(provider, recipeOutput) {
            @Override
            public void buildRecipes() {

                shaped(RecipeCategory.MISC, UPBlocks.ACACIA_BARREL)
                        .pattern("PDP").pattern("PBP").pattern("PDP")
                        .define('P', Items.ACACIA_PLANKS).define('B', Items.BARREL).define('D', Items.ACACIA_SLAB)
                        .unlockedBy("has_acacia_planks", has(Items.ACACIA_PLANKS)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.BIRCH_BARREL)
                        .pattern("PDP").pattern("PBP").pattern("PDP")
                        .define('P', Items.BIRCH_PLANKS).define('B', Items.BARREL).define('D', Items.BIRCH_SLAB)
                        .unlockedBy("has_birch_planks", has(Items.BIRCH_PLANKS)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.CHERRY_BARREL)
                        .pattern("PDP").pattern("PBP").pattern("PDP")
                        .define('P', Items.CHERRY_PLANKS).define('B', Items.BARREL).define('D', Items.CHERRY_SLAB)
                        .unlockedBy("has_cherry_planks", has(Items.CHERRY_PLANKS)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.CRIMSON_BARREL)
                        .pattern("PDP").pattern("PBP").pattern("PDP")
                        .define('P', Items.CRIMSON_PLANKS).define('B', Items.BARREL).define('D', Items.CRIMSON_SLAB)
                        .unlockedBy("has_crimson_stem", has(Items.CRIMSON_STEM)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.DARK_OAK_BARREL)
                        .pattern("PDP").pattern("PBP").pattern("PDP")
                        .define('P', Items.DARK_OAK_PLANKS).define('B', Items.BARREL).define('D', Items.DARK_OAK_SLAB)
                        .unlockedBy("has_dark_oak_planks", has(Items.DARK_OAK_PLANKS)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.JUNGLE_BARREL)
                        .pattern("PDP").pattern("PBP").pattern("PDP")
                        .define('P', Items.JUNGLE_PLANKS).define('B', Items.BARREL).define('D', Items.JUNGLE_SLAB)
                        .unlockedBy("has_jungle_planks", has(Items.JUNGLE_PLANKS)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.MANGROVE_BARREL)
                        .pattern("PDP").pattern("PBP").pattern("PDP")
                        .define('P', Items.MANGROVE_PLANKS).define('B', Items.BARREL).define('D', Items.MANGROVE_SLAB)
                        .unlockedBy("has_mangrove_planks", has(Items.MANGROVE_PLANKS)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.OAK_BARREL)
                        .pattern("PDP").pattern("PBP").pattern("PDP")
                        .define('P', Items.OAK_PLANKS).define('B', Items.BARREL).define('D', Items.OAK_SLAB)
                        .unlockedBy("has_oak_planks", has(Items.OAK_PLANKS)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.OAK_BARREL)
                        .pattern("PDP").pattern("PBP").pattern("PDP")
                        .define('P', ItemTags.PLANKS).define('B', Items.BARREL).define('D', ItemTags.WOODEN_SLABS)
                        .unlockedBy("has_oak_planks", has(Items.OAK_PLANKS))
                        .save(output, "utilitiesplus:oak_barrel_from_any_wood");

                shaped(RecipeCategory.MISC, UPBlocks.PALE_OAK_BARREL)
                        .pattern("PDP").pattern("PBP").pattern("PDP")
                        .define('P', Items.PALE_OAK_PLANKS).define('B', Items.BARREL).define('D', Items.PALE_OAK_SLAB)
                        .unlockedBy("has_pale_oak_planks", has(Items.PALE_OAK_PLANKS)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.SPRUCE_BARREL)
                        .pattern("PDP").pattern("PBP").pattern("PDP")
                        .define('P', Items.SPRUCE_PLANKS).define('B', Items.BARREL).define('D', Items.SPRUCE_SLAB)
                        .unlockedBy("has_spruce_planks", has(Items.SPRUCE_PLANKS)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.WARPED_BARREL)
                        .pattern("PDP").pattern("PBP").pattern("PDP")
                        .define('P', Items.WARPED_PLANKS).define('B', Items.BARREL).define('D', Items.WARPED_SLAB)
                        .unlockedBy("has_warped_planks", has(Items.WARPED_PLANKS)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.HARVESTER)
                        .pattern("PPP").pattern("PHP").pattern("SSS")
                        .define('P', ItemTags.PLANKS).define('H', Items.DIAMOND_HOE).define('S', Items.COBBLESTONE)
                        .unlockedBy("has_diamond_hoe", has(Items.DIAMOND_HOE)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.KILN)
                        .pattern("BBB").pattern("BFB").pattern("SSS")
                        .define('B', Items.BRICK).define('F', Items.FURNACE).define('S', Items.SMOOTH_STONE)
                        .unlockedBy("has_brick", has(Items.BRICK)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.SAWBENCH)
                        .pattern(" I ").pattern("LLL")
                        .define('L', ItemTags.LOGS).define('I', Items.IRON_INGOT)
                        .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT)).save(output);

                shaped(RecipeCategory.MISC, UPItems.COPPER_UPGRADE)
                        .pattern("IBI").pattern("B#B").pattern("III")
                        .define('#', UPTags.Items.BARREL_BLOCK_ITEMS).define('I', Items.COPPER_INGOT).define('B', Items.COPPER_BLOCK)
                        .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT)).save(output);

                shaped(RecipeCategory.MISC, UPItems.IRON_UPGRADE)
                        .pattern("IBI").pattern("B#B").pattern("III")
                        .define('#', UPTags.Items.BARREL_BLOCK_ITEMS).define('I', Items.IRON_INGOT).define('B', Items.IRON_BLOCK)
                        .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT)).save(output);

                shaped(RecipeCategory.MISC, UPItems.GOLD_UPGRADE)
                        .pattern("IBI").pattern("B#B").pattern("III")
                        .define('#', UPTags.Items.BARREL_BLOCK_ITEMS).define('I', Items.GOLD_INGOT).define('B', Items.GOLD_BLOCK)
                        .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT)).save(output);

                shaped(RecipeCategory.MISC, UPItems.DIAMOND_UPGRADE)
                        .pattern("IBI").pattern("B#B").pattern("III")
                        .define('#', UPTags.Items.BARREL_BLOCK_ITEMS).define('I', Items.DIAMOND).define('B', Items.DIAMOND_BLOCK)
                        .unlockedBy("has_diamond", has(Items.DIAMOND)).save(output);

                shaped(RecipeCategory.MISC, UPItems.VILLAGER_CATCHER)
                        .pattern("LIL").pattern("IEI").pattern("LIL")
                        .define('L', ItemTags.LOGS).define('I', Items.IRON_BARS).define('E', Items.EMERALD_BLOCK)
                        .unlockedBy("has_emerald", has(Items.EMERALD)).save(output);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.STRIPPED_OAK_LOG, Items.OAK_LOG);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.STRIPPED_OAK_WOOD, Items.OAK_WOOD);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.STRIPPED_SPRUCE_LOG, Items.SPRUCE_LOG);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.STRIPPED_SPRUCE_WOOD, Items.SPRUCE_WOOD);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.STRIPPED_BIRCH_LOG, Items.BIRCH_LOG);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.STRIPPED_BIRCH_WOOD, Items.BIRCH_WOOD);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.STRIPPED_JUNGLE_LOG, Items.JUNGLE_LOG);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.STRIPPED_JUNGLE_WOOD, Items.JUNGLE_WOOD);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.STRIPPED_ACACIA_LOG, Items.ACACIA_LOG);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.STRIPPED_ACACIA_WOOD, Items.ACACIA_WOOD);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.STRIPPED_DARK_OAK_LOG, Items.DARK_OAK_LOG);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.STRIPPED_DARK_OAK_WOOD, Items.DARK_OAK_WOOD);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.STRIPPED_MANGROVE_LOG, Items.MANGROVE_LOG);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.STRIPPED_MANGROVE_WOOD, Items.MANGROVE_WOOD);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.STRIPPED_CHERRY_LOG, Items.CHERRY_LOG);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.STRIPPED_CHERRY_WOOD, Items.CHERRY_WOOD);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.STRIPPED_PALE_OAK_LOG, Items.PALE_OAK_LOG);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.STRIPPED_PALE_OAK_WOOD, Items.PALE_OAK_WOOD);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.STRIPPED_CRIMSON_STEM, Items.CRIMSON_STEM);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.STRIPPED_CRIMSON_HYPHAE, Items.CRIMSON_HYPHAE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.STRIPPED_WARPED_STEM, Items.WARPED_STEM);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.STRIPPED_WARPED_HYPHAE, Items.WARPED_HYPHAE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.STRIPPED_BAMBOO_BLOCK, Items.BAMBOO_BLOCK);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.OAK_PLANKS, Items.OAK_LOG, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.OAK_PLANKS, Items.OAK_WOOD, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.OAK_PLANKS, Items.STRIPPED_OAK_LOG, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.OAK_PLANKS, Items.STRIPPED_OAK_WOOD, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.SPRUCE_PLANKS, Items.SPRUCE_LOG, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.SPRUCE_PLANKS, Items.SPRUCE_WOOD, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.SPRUCE_PLANKS, Items.STRIPPED_SPRUCE_LOG, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.SPRUCE_PLANKS, Items.STRIPPED_SPRUCE_WOOD, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.BIRCH_PLANKS, Items.BIRCH_LOG, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.BIRCH_PLANKS, Items.BIRCH_WOOD, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.BIRCH_PLANKS, Items.STRIPPED_BIRCH_LOG, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.BIRCH_PLANKS, Items.STRIPPED_BIRCH_WOOD, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.JUNGLE_PLANKS, Items.JUNGLE_LOG, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.JUNGLE_PLANKS, Items.JUNGLE_WOOD, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.JUNGLE_PLANKS, Items.STRIPPED_JUNGLE_LOG, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.JUNGLE_PLANKS, Items.STRIPPED_JUNGLE_WOOD, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.ACACIA_PLANKS, Items.ACACIA_LOG, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.ACACIA_PLANKS, Items.ACACIA_WOOD, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.ACACIA_PLANKS, Items.STRIPPED_ACACIA_LOG, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.ACACIA_PLANKS, Items.STRIPPED_ACACIA_WOOD, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.DARK_OAK_PLANKS, Items.DARK_OAK_LOG, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.DARK_OAK_PLANKS, Items.DARK_OAK_WOOD, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.DARK_OAK_PLANKS, Items.STRIPPED_DARK_OAK_LOG, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.DARK_OAK_PLANKS, Items.STRIPPED_DARK_OAK_WOOD, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.MANGROVE_PLANKS, Items.MANGROVE_LOG, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.MANGROVE_PLANKS, Items.MANGROVE_WOOD, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.MANGROVE_PLANKS, Items.STRIPPED_MANGROVE_LOG, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.MANGROVE_PLANKS, Items.STRIPPED_MANGROVE_WOOD, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.CHERRY_PLANKS, Items.CHERRY_LOG, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.CHERRY_PLANKS, Items.CHERRY_WOOD, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.CHERRY_PLANKS, Items.STRIPPED_CHERRY_LOG, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.CHERRY_PLANKS, Items.STRIPPED_CHERRY_WOOD, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.PALE_OAK_PLANKS, Items.PALE_OAK_LOG, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.PALE_OAK_PLANKS, Items.PALE_OAK_WOOD, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.PALE_OAK_PLANKS, Items.STRIPPED_PALE_OAK_LOG, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.PALE_OAK_PLANKS, Items.STRIPPED_PALE_OAK_WOOD, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.CRIMSON_PLANKS, Items.CRIMSON_STEM, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.CRIMSON_PLANKS, Items.CRIMSON_HYPHAE, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.CRIMSON_PLANKS, Items.STRIPPED_CRIMSON_STEM, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.CRIMSON_PLANKS, Items.STRIPPED_CRIMSON_HYPHAE, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.WARPED_PLANKS, Items.WARPED_STEM, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.WARPED_PLANKS, Items.WARPED_HYPHAE, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.WARPED_PLANKS, Items.STRIPPED_WARPED_STEM, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.WARPED_PLANKS, Items.STRIPPED_WARPED_HYPHAE, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.BAMBOO_PLANKS, Items.BAMBOO_BLOCK, 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.BAMBOO_PLANKS, Items.STRIPPED_BAMBOO_BLOCK, 4);

                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.OAK_LOG, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.OAK_WOOD, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.STRIPPED_OAK_LOG, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.STRIPPED_OAK_WOOD, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.SPRUCE_LOG, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.SPRUCE_WOOD, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.STRIPPED_SPRUCE_LOG, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.STRIPPED_SPRUCE_WOOD, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.BIRCH_LOG, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.BIRCH_WOOD, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.STRIPPED_BIRCH_LOG, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.STRIPPED_BIRCH_WOOD, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.JUNGLE_LOG, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.JUNGLE_WOOD, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.STRIPPED_JUNGLE_LOG, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.STRIPPED_JUNGLE_WOOD, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.ACACIA_LOG, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.ACACIA_WOOD, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.STRIPPED_ACACIA_LOG, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.STRIPPED_ACACIA_WOOD, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.DARK_OAK_LOG, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.DARK_OAK_WOOD, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.STRIPPED_DARK_OAK_LOG, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.STRIPPED_DARK_OAK_WOOD, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.MANGROVE_LOG, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.MANGROVE_WOOD, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.STRIPPED_MANGROVE_LOG, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.STRIPPED_MANGROVE_WOOD, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.CHERRY_LOG, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.CHERRY_WOOD, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.STRIPPED_CHERRY_LOG, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.STRIPPED_CHERRY_WOOD, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.PALE_OAK_LOG, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.PALE_OAK_WOOD, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.STRIPPED_PALE_OAK_LOG, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.STRIPPED_PALE_OAK_WOOD, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.CRIMSON_STEM, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.CRIMSON_HYPHAE, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.STRIPPED_CRIMSON_STEM, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.STRIPPED_CRIMSON_HYPHAE, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.WARPED_STEM, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.WARPED_HYPHAE, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.STRIPPED_WARPED_STEM, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.STRIPPED_WARPED_HYPHAE, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.BAMBOO_BLOCK, 16);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.STRIPPED_BAMBOO_BLOCK, 16);

                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.OAK_PLANKS, 4);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.SPRUCE_PLANKS, 4);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.BIRCH_PLANKS, 4);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.JUNGLE_PLANKS, 4);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.ACACIA_PLANKS, 4);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.DARK_OAK_PLANKS, 4);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.MANGROVE_PLANKS, 4);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.CHERRY_PLANKS, 4);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.PALE_OAK_PLANKS, 4);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.CRIMSON_PLANKS, 4);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.WARPED_PLANKS, 4);
                stonecutterResultFromBase(RecipeCategory.MISC, Items.STICK, Items.BAMBOO_PLANKS, 4);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.OAK_SLAB, Items.OAK_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.OAK_STAIRS, Items.OAK_PLANKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.OAK_FENCE, Items.OAK_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.OAK_FENCE_GATE, Items.OAK_PLANKS);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.SPRUCE_SLAB, Items.SPRUCE_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.SPRUCE_STAIRS, Items.SPRUCE_PLANKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.SPRUCE_FENCE, Items.SPRUCE_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.SPRUCE_FENCE_GATE, Items.SPRUCE_PLANKS);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.BIRCH_SLAB, Items.BIRCH_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.BIRCH_STAIRS, Items.BIRCH_PLANKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BIRCH_FENCE, Items.BIRCH_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.BIRCH_FENCE_GATE, Items.BIRCH_PLANKS);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.JUNGLE_SLAB, Items.JUNGLE_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.JUNGLE_STAIRS, Items.JUNGLE_PLANKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.JUNGLE_FENCE, Items.JUNGLE_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.JUNGLE_FENCE_GATE, Items.JUNGLE_PLANKS);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.ACACIA_SLAB, Items.ACACIA_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.ACACIA_STAIRS, Items.ACACIA_PLANKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.ACACIA_FENCE, Items.ACACIA_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.ACACIA_FENCE_GATE, Items.ACACIA_PLANKS);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.DARK_OAK_SLAB, Items.DARK_OAK_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.DARK_OAK_STAIRS, Items.DARK_OAK_PLANKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.DARK_OAK_FENCE, Items.DARK_OAK_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.DARK_OAK_FENCE_GATE, Items.DARK_OAK_PLANKS);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.MANGROVE_SLAB, Items.MANGROVE_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.MANGROVE_STAIRS, Items.MANGROVE_PLANKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.MANGROVE_FENCE, Items.MANGROVE_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.MANGROVE_FENCE_GATE, Items.MANGROVE_PLANKS);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.CHERRY_SLAB, Items.CHERRY_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.CHERRY_STAIRS, Items.CHERRY_PLANKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHERRY_FENCE, Items.CHERRY_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.CHERRY_FENCE_GATE, Items.CHERRY_PLANKS);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.PALE_OAK_SLAB, Items.PALE_OAK_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.PALE_OAK_STAIRS, Items.PALE_OAK_PLANKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.PALE_OAK_FENCE, Items.PALE_OAK_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.PALE_OAK_FENCE_GATE, Items.PALE_OAK_PLANKS);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.CRIMSON_SLAB, Items.CRIMSON_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.CRIMSON_STAIRS, Items.CRIMSON_PLANKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CRIMSON_FENCE, Items.CRIMSON_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.CRIMSON_FENCE_GATE, Items.CRIMSON_PLANKS);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.WARPED_SLAB, Items.WARPED_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.WARPED_STAIRS, Items.WARPED_PLANKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.WARPED_FENCE, Items.WARPED_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.WARPED_FENCE_GATE, Items.WARPED_PLANKS);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.BAMBOO_SLAB, Items.BAMBOO_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Items.BAMBOO_STAIRS, Items.BAMBOO_PLANKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BAMBOO_FENCE, Items.BAMBOO_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.BAMBOO_FENCE_GATE, Items.BAMBOO_PLANKS);

                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.OAK_DOOR, Items.OAK_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.OAK_TRAPDOOR, Items.OAK_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.OAK_PRESSURE_PLATE, Items.OAK_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.OAK_BUTTON, Items.OAK_PLANKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.OAK_SIGN, Items.OAK_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.OAK_HANGING_SIGN, Items.OAK_PLANKS);
                stonecutterResultFromBase(RecipeCategory.TRANSPORTATION, Items.OAK_BOAT, Items.OAK_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.SPRUCE_DOOR, Items.SPRUCE_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.SPRUCE_TRAPDOOR, Items.SPRUCE_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.SPRUCE_PRESSURE_PLATE, Items.SPRUCE_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.SPRUCE_BUTTON, Items.SPRUCE_PLANKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.SPRUCE_SIGN, Items.SPRUCE_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.SPRUCE_HANGING_SIGN, Items.SPRUCE_PLANKS);
                stonecutterResultFromBase(RecipeCategory.TRANSPORTATION, Items.SPRUCE_BOAT, Items.SPRUCE_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.BIRCH_DOOR, Items.BIRCH_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.BIRCH_TRAPDOOR, Items.BIRCH_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.BIRCH_PRESSURE_PLATE, Items.BIRCH_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.BIRCH_BUTTON, Items.BIRCH_PLANKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BIRCH_SIGN, Items.BIRCH_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BIRCH_HANGING_SIGN, Items.BIRCH_PLANKS);
                stonecutterResultFromBase(RecipeCategory.TRANSPORTATION, Items.BIRCH_BOAT, Items.BIRCH_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.JUNGLE_DOOR, Items.JUNGLE_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.JUNGLE_TRAPDOOR, Items.JUNGLE_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.JUNGLE_PRESSURE_PLATE, Items.JUNGLE_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.JUNGLE_BUTTON, Items.JUNGLE_PLANKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.JUNGLE_SIGN, Items.JUNGLE_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.JUNGLE_HANGING_SIGN, Items.JUNGLE_PLANKS);
                stonecutterResultFromBase(RecipeCategory.TRANSPORTATION, Items.JUNGLE_BOAT, Items.JUNGLE_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.ACACIA_DOOR, Items.ACACIA_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.ACACIA_TRAPDOOR, Items.ACACIA_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.ACACIA_PRESSURE_PLATE, Items.ACACIA_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.ACACIA_BUTTON, Items.ACACIA_PLANKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.ACACIA_SIGN, Items.ACACIA_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.ACACIA_HANGING_SIGN, Items.ACACIA_PLANKS);
                stonecutterResultFromBase(RecipeCategory.TRANSPORTATION, Items.ACACIA_BOAT, Items.ACACIA_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.DARK_OAK_DOOR, Items.DARK_OAK_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.DARK_OAK_TRAPDOOR, Items.DARK_OAK_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.DARK_OAK_PRESSURE_PLATE, Items.DARK_OAK_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.DARK_OAK_BUTTON, Items.DARK_OAK_PLANKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.DARK_OAK_SIGN, Items.DARK_OAK_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.DARK_OAK_HANGING_SIGN, Items.DARK_OAK_PLANKS);
                stonecutterResultFromBase(RecipeCategory.TRANSPORTATION, Items.DARK_OAK_BOAT, Items.DARK_OAK_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.MANGROVE_DOOR, Items.MANGROVE_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.MANGROVE_TRAPDOOR, Items.MANGROVE_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.MANGROVE_PRESSURE_PLATE, Items.MANGROVE_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.MANGROVE_BUTTON, Items.MANGROVE_PLANKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.MANGROVE_SIGN, Items.MANGROVE_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.MANGROVE_HANGING_SIGN, Items.MANGROVE_PLANKS);
                stonecutterResultFromBase(RecipeCategory.TRANSPORTATION, Items.MANGROVE_BOAT, Items.MANGROVE_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.CHERRY_DOOR, Items.CHERRY_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.CHERRY_TRAPDOOR, Items.CHERRY_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.CHERRY_PRESSURE_PLATE, Items.CHERRY_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.CHERRY_BUTTON, Items.CHERRY_PLANKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHERRY_SIGN, Items.CHERRY_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHERRY_HANGING_SIGN, Items.CHERRY_PLANKS);
                stonecutterResultFromBase(RecipeCategory.TRANSPORTATION, Items.CHERRY_BOAT, Items.CHERRY_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.PALE_OAK_DOOR, Items.PALE_OAK_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.PALE_OAK_TRAPDOOR, Items.PALE_OAK_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.PALE_OAK_PRESSURE_PLATE, Items.PALE_OAK_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.PALE_OAK_BUTTON, Items.PALE_OAK_PLANKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.PALE_OAK_SIGN, Items.PALE_OAK_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.PALE_OAK_HANGING_SIGN, Items.PALE_OAK_PLANKS);
                stonecutterResultFromBase(RecipeCategory.TRANSPORTATION, Items.PALE_OAK_BOAT, Items.PALE_OAK_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.CRIMSON_DOOR, Items.CRIMSON_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.CRIMSON_TRAPDOOR, Items.CRIMSON_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.CRIMSON_PRESSURE_PLATE, Items.CRIMSON_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.CRIMSON_BUTTON, Items.CRIMSON_PLANKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CRIMSON_SIGN, Items.CRIMSON_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CRIMSON_HANGING_SIGN, Items.CRIMSON_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.WARPED_DOOR, Items.WARPED_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.WARPED_TRAPDOOR, Items.WARPED_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.WARPED_PRESSURE_PLATE, Items.WARPED_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.WARPED_BUTTON, Items.WARPED_PLANKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.WARPED_SIGN, Items.WARPED_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.WARPED_HANGING_SIGN, Items.WARPED_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.BAMBOO_DOOR, Items.BAMBOO_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.BAMBOO_TRAPDOOR, Items.BAMBOO_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.BAMBOO_PRESSURE_PLATE, Items.BAMBOO_PLANKS);
                stonecutterResultFromBase(RecipeCategory.REDSTONE, Items.BAMBOO_BUTTON, Items.BAMBOO_PLANKS);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BAMBOO_SIGN, Items.BAMBOO_PLANKS, 2);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BAMBOO_HANGING_SIGN, Items.BAMBOO_PLANKS);
                stonecutterResultFromBase(RecipeCategory.TRANSPORTATION, Items.BAMBOO_RAFT, Items.BAMBOO_PLANKS);

                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.OAK_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.OAK_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.OAK_BARREL, Items.OAK_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.OAK_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.OAK_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.OAK_BARREL, Items.OAK_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.STRIPPED_OAK_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.STRIPPED_OAK_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.OAK_BARREL, Items.STRIPPED_OAK_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.STRIPPED_OAK_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.STRIPPED_OAK_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.OAK_BARREL, Items.STRIPPED_OAK_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.SPRUCE_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.SPRUCE_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.SPRUCE_BARREL, Items.SPRUCE_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.SPRUCE_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.SPRUCE_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.SPRUCE_BARREL, Items.SPRUCE_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.STRIPPED_SPRUCE_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.STRIPPED_SPRUCE_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.SPRUCE_BARREL, Items.STRIPPED_SPRUCE_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.STRIPPED_SPRUCE_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.STRIPPED_SPRUCE_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.SPRUCE_BARREL, Items.STRIPPED_SPRUCE_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.BIRCH_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.BIRCH_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.BIRCH_BARREL, Items.BIRCH_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.BIRCH_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.BIRCH_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.BIRCH_BARREL, Items.BIRCH_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.STRIPPED_BIRCH_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.STRIPPED_BIRCH_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.BIRCH_BARREL, Items.STRIPPED_BIRCH_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.STRIPPED_BIRCH_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.STRIPPED_BIRCH_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.BIRCH_BARREL, Items.STRIPPED_BIRCH_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.JUNGLE_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.JUNGLE_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.JUNGLE_BARREL, Items.JUNGLE_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.JUNGLE_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.JUNGLE_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.JUNGLE_BARREL, Items.JUNGLE_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.STRIPPED_JUNGLE_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.STRIPPED_JUNGLE_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.JUNGLE_BARREL, Items.STRIPPED_JUNGLE_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.STRIPPED_JUNGLE_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.STRIPPED_JUNGLE_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.JUNGLE_BARREL, Items.STRIPPED_JUNGLE_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.ACACIA_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.ACACIA_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.ACACIA_BARREL, Items.ACACIA_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.ACACIA_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.ACACIA_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.ACACIA_BARREL, Items.ACACIA_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.STRIPPED_ACACIA_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.STRIPPED_ACACIA_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.ACACIA_BARREL, Items.STRIPPED_ACACIA_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.STRIPPED_ACACIA_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.STRIPPED_ACACIA_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.ACACIA_BARREL, Items.STRIPPED_ACACIA_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.DARK_OAK_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.DARK_OAK_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.DARK_OAK_BARREL, Items.DARK_OAK_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.DARK_OAK_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.DARK_OAK_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.DARK_OAK_BARREL, Items.DARK_OAK_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.STRIPPED_DARK_OAK_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.STRIPPED_DARK_OAK_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.DARK_OAK_BARREL, Items.STRIPPED_DARK_OAK_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.STRIPPED_DARK_OAK_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.STRIPPED_DARK_OAK_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.DARK_OAK_BARREL, Items.STRIPPED_DARK_OAK_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.MANGROVE_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.MANGROVE_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.MANGROVE_BARREL, Items.MANGROVE_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.MANGROVE_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.MANGROVE_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.MANGROVE_BARREL, Items.MANGROVE_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.STRIPPED_MANGROVE_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.STRIPPED_MANGROVE_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.MANGROVE_BARREL, Items.STRIPPED_MANGROVE_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.STRIPPED_MANGROVE_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.STRIPPED_MANGROVE_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.MANGROVE_BARREL, Items.STRIPPED_MANGROVE_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.CHERRY_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.CHERRY_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.CHERRY_BARREL, Items.CHERRY_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.CHERRY_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.CHERRY_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.CHERRY_BARREL, Items.CHERRY_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.STRIPPED_CHERRY_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.STRIPPED_CHERRY_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.CHERRY_BARREL, Items.STRIPPED_CHERRY_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.STRIPPED_CHERRY_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.STRIPPED_CHERRY_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.CHERRY_BARREL, Items.STRIPPED_CHERRY_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.PALE_OAK_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.PALE_OAK_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.PALE_OAK_BARREL, Items.PALE_OAK_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.PALE_OAK_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.PALE_OAK_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.PALE_OAK_BARREL, Items.PALE_OAK_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.STRIPPED_PALE_OAK_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.STRIPPED_PALE_OAK_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.PALE_OAK_BARREL, Items.STRIPPED_PALE_OAK_LOG);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.STRIPPED_PALE_OAK_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.STRIPPED_PALE_OAK_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.PALE_OAK_BARREL, Items.STRIPPED_PALE_OAK_WOOD);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.CRIMSON_STEM);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.CRIMSON_STEM);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.CRIMSON_BARREL, Items.CRIMSON_STEM);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.CRIMSON_HYPHAE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.CRIMSON_HYPHAE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.CRIMSON_BARREL, Items.CRIMSON_HYPHAE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.STRIPPED_CRIMSON_STEM);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.STRIPPED_CRIMSON_STEM);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.CRIMSON_BARREL, Items.STRIPPED_CRIMSON_STEM);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.STRIPPED_CRIMSON_HYPHAE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.STRIPPED_CRIMSON_HYPHAE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.CRIMSON_BARREL, Items.STRIPPED_CRIMSON_HYPHAE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.WARPED_STEM);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.WARPED_STEM);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.WARPED_BARREL, Items.WARPED_STEM);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.WARPED_HYPHAE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.WARPED_HYPHAE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.WARPED_BARREL, Items.WARPED_HYPHAE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.STRIPPED_WARPED_STEM);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.STRIPPED_WARPED_STEM);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.WARPED_BARREL, Items.STRIPPED_WARPED_STEM);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.STRIPPED_WARPED_HYPHAE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.STRIPPED_WARPED_HYPHAE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, UPBlocks.WARPED_BARREL, Items.STRIPPED_WARPED_HYPHAE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.BAMBOO_BLOCK);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.BAMBOO_BLOCK);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.CHEST, Items.STRIPPED_BAMBOO_BLOCK);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, Items.BARREL, Items.STRIPPED_BAMBOO_BLOCK);
            }
        };
    }

    @Override
    public String getName() {
        return "UtilitiesPlus Recipes";
    }
}