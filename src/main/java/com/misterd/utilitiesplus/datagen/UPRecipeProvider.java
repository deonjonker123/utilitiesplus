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
                        .define('P', Items.ACACIA_PLANKS).define('B', Items.BARREL)
                        .define('D', Items.ACACIA_SLAB)
                        .unlockedBy("has_acaia_planks", has(Items.ACACIA_PLANKS)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.BIRCH_BARREL)
                        .pattern("PDP").pattern("PBP").pattern("PDP")
                        .define('P', Items.BIRCH_PLANKS).define('B', Items.BARREL)
                        .define('D', Items.BIRCH_SLAB)
                        .unlockedBy("has_birch_planks", has(Items.BIRCH_PLANKS)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.CHERRY_BARREL)
                        .pattern("PDP").pattern("PBP").pattern("PDP")
                        .define('P', Items.CHERRY_PLANKS).define('B', Items.BARREL)
                        .define('D', Items.CHERRY_SLAB)
                        .unlockedBy("has_cherry_planks", has(Items.CHERRY_PLANKS)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.CRIMSON_BARREL)
                        .pattern("PDP").pattern("PBP").pattern("PDP")
                        .define('P', Items.CRIMSON_PLANKS).define('B', Items.BARREL)
                        .define('D', Items.CRIMSON_SLAB)
                        .unlockedBy("has_crimson_stem", has(Items.CRIMSON_STEM)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.DARK_OAK_BARREL)
                        .pattern("PDP").pattern("PBP").pattern("PDP")
                        .define('P', Items.DARK_OAK_PLANKS).define('B', Items.BARREL)
                        .define('D', Items.DARK_OAK_SLAB)
                        .unlockedBy("has_dark_oak_planks", has(Items.DARK_OAK_PLANKS)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.JUNGLE_BARREL)
                        .pattern("PDP").pattern("PBP").pattern("PDP")
                        .define('P', Items.JUNGLE_PLANKS).define('B', Items.BARREL)
                        .define('D', Items.JUNGLE_SLAB)
                        .unlockedBy("has_jungle_planks", has(Items.JUNGLE_PLANKS)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.MANGROVE_BARREL)
                        .pattern("PDP").pattern("PBP").pattern("PDP")
                        .define('P', Items.MANGROVE_PLANKS).define('B', Items.BARREL)
                        .define('D', Items.MANGROVE_SLAB)
                        .unlockedBy("has_mangrove_planks", has(Items.MANGROVE_PLANKS)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.OAK_BARREL)
                        .pattern("PDP").pattern("PBP").pattern("PDP")
                        .define('P', Items.OAK_PLANKS).define('B', Items.BARREL)
                        .define('D', Items.OAK_SLAB)
                        .unlockedBy("has_oak_planks", has(Items.OAK_PLANKS)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.OAK_BARREL)
                        .pattern("PDP").pattern("PBP").pattern("PDP")
                        .define('P', ItemTags.PLANKS).define('B', Items.BARREL)
                        .define('D', ItemTags.WOODEN_SLABS)
                        .unlockedBy("has_oak_planks", has(Items.OAK_PLANKS))
                        .save(output, "utilitiesplus:oak_barrel_from_any_wood");

                shaped(RecipeCategory.MISC, UPBlocks.PALE_OAK_BARREL)
                        .pattern("PDP").pattern("PBP").pattern("PDP")
                        .define('P', Items.PALE_OAK_PLANKS).define('B', Items.BARREL)
                        .define('D', Items.PALE_OAK_SLAB)
                        .unlockedBy("has_pale_oak_planks", has(Items.PALE_OAK_PLANKS)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.SPRUCE_BARREL)
                        .pattern("PDP").pattern("PBP").pattern("PDP")
                        .define('P', Items.SPRUCE_PLANKS).define('B', Items.BARREL)
                        .define('D', Items.SPRUCE_SLAB)
                        .unlockedBy("has_spruce_planks", has(Items.SPRUCE_PLANKS)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.WARPED_BARREL)
                        .pattern("PDP").pattern("PBP").pattern("PDP")
                        .define('P', Items.WARPED_PLANKS).define('B', Items.BARREL)
                        .define('D', Items.WARPED_SLAB)
                        .unlockedBy("has_warped_planks", has(Items.WARPED_PLANKS)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.HARVESTER)
                        .pattern("PPP").pattern("PHP").pattern("SSS")
                        .define('P', ItemTags.PLANKS).define('H', Items.DIAMOND_HOE)
                        .define('S', Items.COBBLESTONE)
                        .unlockedBy("has_diamond_hoe", has(Items.DIAMOND_HOE)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.KILN)
                        .pattern("BBB").pattern("BFB").pattern("SSS")
                        .define('B', Items.BRICK).define('F', Items.FURNACE)
                        .define('S', Items.SMOOTH_STONE)
                        .unlockedBy("has_brick", has(Items.BRICK)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.REDSTONE_CLOCK)
                        .pattern("PPP").pattern("PFP").pattern("SSS")
                        .define('P', ItemTags.PLANKS).define('F', Items.OBSERVER)
                        .define('S', Items.STONE)
                        .unlockedBy("has_redstone", has(Items.REDSTONE)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.HOPPER_DUCT, 4)
                        .pattern(" I ").pattern("I#I").pattern(" I ")
                        .define('I', Items.IRON_INGOT).define('#', Items.COPPER_INGOT)
                        .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT)).save(output);

                shaped(RecipeCategory.MISC, UPItems.COPPER_UPGRADE)
                        .pattern("IBI").pattern("B#B").pattern("III")
                        .define('#', UPTags.Items.BARREL_BLOCK_ITEMS).define('I', Items.COPPER_INGOT)
                        .define('B', Items.COPPER_BLOCK)
                        .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT)).save(output);

                shaped(RecipeCategory.MISC, UPItems.IRON_UPGRADE)
                        .pattern("IBI").pattern("B#B").pattern("III")
                        .define('#', UPTags.Items.BARREL_BLOCK_ITEMS).define('I', Items.IRON_INGOT)
                        .define('B', Items.IRON_BLOCK)
                        .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT)).save(output);

                shaped(RecipeCategory.MISC, UPItems.GOLD_UPGRADE)
                        .pattern("IBI").pattern("B#B").pattern("III")
                        .define('#', UPTags.Items.BARREL_BLOCK_ITEMS).define('I', Items.GOLD_INGOT)
                        .define('B', Items.GOLD_BLOCK)
                        .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT)).save(output);

                shaped(RecipeCategory.MISC, UPItems.DIAMOND_UPGRADE)
                        .pattern("IBI").pattern("B#B").pattern("III")
                        .define('#', UPTags.Items.BARREL_BLOCK_ITEMS).define('I', Items.DIAMOND)
                        .define('B', Items.DIAMOND_BLOCK)
                        .unlockedBy("has_diamond", has(Items.DIAMOND)).save(output);

                shaped(RecipeCategory.MISC, UPItems.VILLAGER_CATCHER)
                        .pattern("LIL").pattern("IEI").pattern("LIL")
                        .define('L', ItemTags.LOGS).define('I', Items.IRON_BARS)
                        .define('E', Items.EMERALD_BLOCK)
                        .unlockedBy("has_emerald", has(Items.EMERALD)).save(output);
            }
        };
        
    }

    @Override
    public String getName() {
        return "UtilitiesPlus Recipes";
    }
}
