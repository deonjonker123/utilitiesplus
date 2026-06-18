package com.misterd.utilitiesplus.datagen;

import com.misterd.utilitiesplus.UtilitiesPlus;
import com.misterd.utilitiesplus.block.UPBlocks;
import com.misterd.utilitiesplus.item.UPItems;
import com.misterd.utilitiesplus.recipe.custom.SawbenchRecipe;
import com.misterd.utilitiesplus.util.UPTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.WeatheringCopper;

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
                        .define('#', UPTags.Items.BARREL_BLOCK_ITEMS)
                        .define('I', Items.COPPER_INGOT)
                        .define('B', Items.COPPER_BLOCK.weathering().pick(WeatheringCopper.WeatherState.UNAFFECTED))
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

                shaped(RecipeCategory.MISC, UPBlocks.FILTERED_HOPPER)
                        .pattern("ICI").pattern("IHI").pattern("QIQ")
                        .define('I', Items.IRON_INGOT).define('C', Items.COMPARATOR).define('H', Items.HOPPER).define('Q', Items.QUARTZ)
                        .unlockedBy("has_hopper", has(Items.HOPPER)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.FAST_HOPPER)
                        .pattern("ICI").pattern("IHI").pattern("CIC")
                        .define('I', Items.COPPER_INGOT).define('C', Items.DIAMOND).define('H', UPBlocks.FILTERED_HOPPER)
                        .unlockedBy("has_filtered_hopper", has(UPBlocks.FILTERED_HOPPER)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.FAN)
                        .pattern("CIC").pattern("CFC").pattern("CRC")
                        .define('C', Items.COPPER_INGOT).define('I',Items.IRON_BARS ).define('R', Items.REDSTONE_BLOCK).define('F', Items.BLAST_FURNACE)
                        .unlockedBy("has_redstone", has(Items.REDSTONE)).save(output);

                shaped(RecipeCategory.MISC, UPItems.VILLAGER_CATCHER)
                        .pattern("LIL").pattern("IEI").pattern("LIL")
                        .define('L', ItemTags.LOGS).define('I', Items.IRON_BARS).define('E', Items.EMERALD_BLOCK)
                        .unlockedBy("has_emerald", has(Items.EMERALD)).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, UPBlocks.OAK_VERTICAL_SLAB, 3)
                        .pattern("#").pattern("#").pattern("#")
                        .define('#', Items.OAK_PLANKS)
                        .unlockedBy("has_oak_planks", has(Items.OAK_PLANKS)).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, UPBlocks.SPRUCE_VERTICAL_SLAB, 3)
                        .pattern("#").pattern("#").pattern("#")
                        .define('#', Items.SPRUCE_PLANKS)
                        .unlockedBy("has_spruce_planks", has(Items.SPRUCE_PLANKS)).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, UPBlocks.BIRCH_VERTICAL_SLAB, 3)
                        .pattern("#").pattern("#").pattern("#")
                        .define('#', Items.BIRCH_PLANKS)
                        .unlockedBy("has_birch_planks", has(Items.BIRCH_PLANKS)).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, UPBlocks.JUNGLE_VERTICAL_SLAB, 3)
                        .pattern("#").pattern("#").pattern("#")
                        .define('#', Items.JUNGLE_PLANKS)
                        .unlockedBy("has_jungle_planks", has(Items.JUNGLE_PLANKS)).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, UPBlocks.ACACIA_VERTICAL_SLAB, 3)
                        .pattern("#").pattern("#").pattern("#")
                        .define('#', Items.ACACIA_PLANKS)
                        .unlockedBy("has_acacia_planks", has(Items.ACACIA_PLANKS)).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, UPBlocks.DARK_OAK_VERTICAL_SLAB, 3)
                        .pattern("#").pattern("#").pattern("#")
                        .define('#', Items.DARK_OAK_PLANKS)
                        .unlockedBy("has_dark_oak_planks", has(Items.DARK_OAK_PLANKS)).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, UPBlocks.MANGROVE_VERTICAL_SLAB, 3)
                        .pattern("#").pattern("#").pattern("#")
                        .define('#', Items.MANGROVE_PLANKS)
                        .unlockedBy("has_mangrove_planks", has(Items.MANGROVE_PLANKS)).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, UPBlocks.CHERRY_VERTICAL_SLAB, 3)
                        .pattern("#").pattern("#").pattern("#")
                        .define('#', Items.CHERRY_PLANKS)
                        .unlockedBy("has_cherry_planks", has(Items.CHERRY_PLANKS)).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, UPBlocks.PALE_OAK_VERTICAL_SLAB, 3)
                        .pattern("#").pattern("#").pattern("#")
                        .define('#', Items.PALE_OAK_PLANKS)
                        .unlockedBy("has_pale_oak_planks", has(Items.PALE_OAK_PLANKS)).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, UPBlocks.CRIMSON_VERTICAL_SLAB, 3)
                        .pattern("#").pattern("#").pattern("#")
                        .define('#', Items.CRIMSON_PLANKS)
                        .unlockedBy("has_crimson_planks", has(Items.CRIMSON_PLANKS)).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, UPBlocks.WARPED_VERTICAL_SLAB, 3)
                        .pattern("#").pattern("#").pattern("#")
                        .define('#', Items.WARPED_PLANKS)
                        .unlockedBy("has_warped_planks", has(Items.WARPED_PLANKS)).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, UPBlocks.BAMBOO_VERTICAL_SLAB, 3)
                        .pattern("#").pattern("#").pattern("#")
                        .define('#', Items.BAMBOO_PLANKS)
                        .unlockedBy("has_bamboo_planks", has(Items.BAMBOO_PLANKS)).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, UPBlocks.ACACIA_BEAM, 3)
                        .pattern("S").pattern("P").pattern("S")
                        .define('S', UPBlocks.ACACIA_VERTICAL_SLAB).define('P', Items.ACACIA_PLANKS)
                        .unlockedBy("has_acacia_planks", has(Items.ACACIA_PLANKS)).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, UPBlocks.BAMBOO_BEAM, 3)
                        .pattern("S").pattern("P").pattern("S")
                        .define('S', UPBlocks.BAMBOO_VERTICAL_SLAB).define('P', Items.BAMBOO_PLANKS)
                        .unlockedBy("has_bamboo_planks", has(Items.BAMBOO_PLANKS)).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, UPBlocks.BIRCH_BEAM, 3)
                        .pattern("S").pattern("P").pattern("S")
                        .define('S', UPBlocks.BIRCH_VERTICAL_SLAB).define('P', Items.BIRCH_PLANKS)
                        .unlockedBy("has_birch_planks", has(Items.BIRCH_PLANKS)).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, UPBlocks.CHERRY_BEAM, 3)
                        .pattern("S").pattern("P").pattern("S")
                        .define('S', UPBlocks.CHERRY_VERTICAL_SLAB).define('P', Items.CHERRY_PLANKS)
                        .unlockedBy("has_cherry_planks", has(Items.CHERRY_PLANKS)).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, UPBlocks.CRIMSON_BEAM, 3)
                        .pattern("S").pattern("P").pattern("S")
                        .define('S', UPBlocks.CRIMSON_VERTICAL_SLAB).define('P', Items.CRIMSON_PLANKS)
                        .unlockedBy("has_crimson_planks", has(Items.CRIMSON_PLANKS)).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, UPBlocks.DARK_OAK_BEAM, 3)
                        .pattern("S").pattern("P").pattern("S")
                        .define('S', UPBlocks.DARK_OAK_VERTICAL_SLAB).define('P', Items.DARK_OAK_PLANKS)
                        .unlockedBy("has_dark_oak_planks", has(Items.DARK_OAK_PLANKS)).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, UPBlocks.JUNGLE_BEAM, 3)
                        .pattern("S").pattern("P").pattern("S")
                        .define('S', UPBlocks.JUNGLE_VERTICAL_SLAB).define('P', Items.JUNGLE_PLANKS)
                        .unlockedBy("has_jungle_planks", has(Items.JUNGLE_PLANKS)).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, UPBlocks.MANGROVE_BEAM, 3)
                        .pattern("S").pattern("P").pattern("S")
                        .define('S', UPBlocks.MANGROVE_VERTICAL_SLAB).define('P', Items.MANGROVE_PLANKS)
                        .unlockedBy("has_mangrove_planks", has(Items.MANGROVE_PLANKS)).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, UPBlocks.OAK_BEAM, 3)
                        .pattern("S").pattern("P").pattern("S")
                        .define('S', UPBlocks.OAK_VERTICAL_SLAB).define('P', Items.OAK_PLANKS)
                        .unlockedBy("has_oak_planks", has(Items.OAK_PLANKS)).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, UPBlocks.PALE_OAK_BEAM, 3)
                        .pattern("S").pattern("P").pattern("S")
                        .define('S', UPBlocks.PALE_OAK_VERTICAL_SLAB).define('P', Items.PALE_OAK_PLANKS)
                        .unlockedBy("has_pale_oak_planks", has(Items.PALE_OAK_PLANKS)).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, UPBlocks.SPRUCE_BEAM, 3)
                        .pattern("S").pattern("P").pattern("S")
                        .define('S', UPBlocks.SPRUCE_VERTICAL_SLAB).define('P', Items.SPRUCE_PLANKS)
                        .unlockedBy("has_spruce_planks", has(Items.SPRUCE_PLANKS)).save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, UPBlocks.WARPED_BEAM, 3)
                        .pattern("S").pattern("P").pattern("S")
                        .define('S', UPBlocks.WARPED_VERTICAL_SLAB).define('P', Items.WARPED_PLANKS)
                        .unlockedBy("has_warped_planks", has(Items.WARPED_PLANKS)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.FEEDING_TROUGH)
                        .pattern("LHL").pattern("LLL")
                        .define('L', ItemTags.LOGS).define('H', Items.HAY_BLOCK)
                        .unlockedBy("has_hay_block", has(Items.HAY_BLOCK)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.NEST_BOX)
                        .pattern("LPL").pattern("LHL").pattern("LLL")
                        .define('L', ItemTags.LOGS).define('H', Items.HAY_BLOCK).define('P', ItemTags.PLANKS)
                        .unlockedBy("has_hay_block", has(Items.HAY_BLOCK)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.REDSTONE_CLOCK)
                        .pattern("SCS").pattern("RIR").pattern("S#S")
                        .define('S', ItemTags.PLANKS).define('R', Items.REDSTONE_TORCH)
                        .define('C', Items.COMPARATOR).define('I', Items.COPPER_INGOT).define('#', Items.REPEATER)
                        .unlockedBy("has_redstone", has(Items.REDSTONE)).save(output);

                shaped(RecipeCategory.MISC, UPBlocks.CHARCOAL_BLOCK)
                        .pattern("CCC").pattern("CCC").pattern("CCC")
                        .define('C', Items.CHARCOAL)
                        .unlockedBy("has_charcoal", has(Items.CHARCOAL)).save(output);

                shapeless(RecipeCategory.MISC, UPItems.CHARCOAL_BIT, 8)
                        .requires(Items.CHARCOAL)
                        .unlockedBy(getHasName(Items.COAL), has(Items.COAL)).save(output);

                shapeless(RecipeCategory.MISC, UPItems.COAL_BIT, 8)
                        .requires(Items.COAL)
                        .unlockedBy(getHasName(Items.COAL), has(Items.COAL)).save(output);

                shapeless(RecipeCategory.MISC, Items.CHARCOAL)
                        .requires(UPItems.CHARCOAL_BIT, 8)
                        .unlockedBy(getHasName(UPItems.CHARCOAL_BIT), has(UPItems.CHARCOAL_BIT)).save(output, "charcoal_from_bits");

                shapeless(RecipeCategory.MISC, Items.COAL)
                        .requires(UPItems.COAL_BIT, 8)
                        .unlockedBy(getHasName(UPItems.COAL_BIT), has(UPItems.COAL_BIT)).save(output, "coal_from_bits");

                shaped(RecipeCategory.MISC, UPItems.OBSIDIAN_BOAT)
                        .pattern("O O").pattern("OOO")
                        .define('O', Items.OBSIDIAN)
                        .unlockedBy("has_obsidian", has(Items.OBSIDIAN)).save(output);

                shapeless(RecipeCategory.MISC, UPItems.OBSIDIAN_CHEST_BOAT)
                        .requires(UPItems.OBSIDIAN_BOAT).requires(Items.CHEST)
                        .unlockedBy(getHasName(UPItems.OBSIDIAN_BOAT), has(UPItems.OBSIDIAN_BOAT)).save(output);

                shaped(RecipeCategory.MISC, UPItems.TROWEL)
                        .pattern("  I").pattern("SI ")
                        .define('S', Items.STICK).define('I', Items.IRON_INGOT)
                        .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT)).save(output);

                sawbench(Items.OAK_PLANKS, 4, Items.OAK_LOG, output);
                sawbench(Items.OAK_PLANKS, 4, Items.OAK_WOOD, output);
                sawbench(Items.OAK_PLANKS, 4, Items.STRIPPED_OAK_LOG, output);
                sawbench(Items.OAK_PLANKS, 4, Items.STRIPPED_OAK_WOOD, output);
                sawbench(Items.SPRUCE_PLANKS, 4, Items.SPRUCE_LOG, output);
                sawbench(Items.SPRUCE_PLANKS, 4, Items.SPRUCE_WOOD, output);
                sawbench(Items.SPRUCE_PLANKS, 4, Items.STRIPPED_SPRUCE_LOG, output);
                sawbench(Items.SPRUCE_PLANKS, 4, Items.STRIPPED_SPRUCE_WOOD, output);
                sawbench(Items.BIRCH_PLANKS, 4, Items.BIRCH_LOG, output);
                sawbench(Items.BIRCH_PLANKS, 4, Items.BIRCH_WOOD, output);
                sawbench(Items.BIRCH_PLANKS, 4, Items.STRIPPED_BIRCH_LOG, output);
                sawbench(Items.BIRCH_PLANKS, 4, Items.STRIPPED_BIRCH_WOOD, output);
                sawbench(Items.JUNGLE_PLANKS, 4, Items.JUNGLE_LOG, output);
                sawbench(Items.JUNGLE_PLANKS, 4, Items.JUNGLE_WOOD, output);
                sawbench(Items.JUNGLE_PLANKS, 4, Items.STRIPPED_JUNGLE_LOG, output);
                sawbench(Items.JUNGLE_PLANKS, 4, Items.STRIPPED_JUNGLE_WOOD, output);
                sawbench(Items.ACACIA_PLANKS, 4, Items.ACACIA_LOG, output);
                sawbench(Items.ACACIA_PLANKS, 4, Items.ACACIA_WOOD, output);
                sawbench(Items.ACACIA_PLANKS, 4, Items.STRIPPED_ACACIA_LOG, output);
                sawbench(Items.ACACIA_PLANKS, 4, Items.STRIPPED_ACACIA_WOOD, output);
                sawbench(Items.DARK_OAK_PLANKS, 4, Items.DARK_OAK_LOG, output);
                sawbench(Items.DARK_OAK_PLANKS, 4, Items.DARK_OAK_WOOD, output);
                sawbench(Items.DARK_OAK_PLANKS, 4, Items.STRIPPED_DARK_OAK_LOG, output);
                sawbench(Items.DARK_OAK_PLANKS, 4, Items.STRIPPED_DARK_OAK_WOOD, output);
                sawbench(Items.MANGROVE_PLANKS, 4, Items.MANGROVE_LOG, output);
                sawbench(Items.MANGROVE_PLANKS, 4, Items.MANGROVE_WOOD, output);
                sawbench(Items.MANGROVE_PLANKS, 4, Items.STRIPPED_MANGROVE_LOG, output);
                sawbench(Items.MANGROVE_PLANKS, 4, Items.STRIPPED_MANGROVE_WOOD, output);
                sawbench(Items.CHERRY_PLANKS, 4, Items.CHERRY_LOG, output);
                sawbench(Items.CHERRY_PLANKS, 4, Items.CHERRY_WOOD, output);
                sawbench(Items.CHERRY_PLANKS, 4, Items.STRIPPED_CHERRY_LOG, output);
                sawbench(Items.CHERRY_PLANKS, 4, Items.STRIPPED_CHERRY_WOOD, output);
                sawbench(Items.PALE_OAK_PLANKS, 4, Items.PALE_OAK_LOG, output);
                sawbench(Items.PALE_OAK_PLANKS, 4, Items.PALE_OAK_WOOD, output);
                sawbench(Items.PALE_OAK_PLANKS, 4, Items.STRIPPED_PALE_OAK_LOG, output);
                sawbench(Items.PALE_OAK_PLANKS, 4, Items.STRIPPED_PALE_OAK_WOOD, output);
                sawbench(Items.CRIMSON_PLANKS, 4, Items.CRIMSON_STEM, output);
                sawbench(Items.CRIMSON_PLANKS, 4, Items.CRIMSON_HYPHAE, output);
                sawbench(Items.CRIMSON_PLANKS, 4, Items.STRIPPED_CRIMSON_STEM, output);
                sawbench(Items.CRIMSON_PLANKS, 4, Items.STRIPPED_CRIMSON_HYPHAE, output);
                sawbench(Items.WARPED_PLANKS, 4, Items.WARPED_STEM, output);
                sawbench(Items.WARPED_PLANKS, 4, Items.WARPED_HYPHAE, output);
                sawbench(Items.WARPED_PLANKS, 4, Items.STRIPPED_WARPED_STEM, output);
                sawbench(Items.WARPED_PLANKS, 4, Items.STRIPPED_WARPED_HYPHAE, output);
                sawbench(Items.BAMBOO_PLANKS, 4, Items.BAMBOO_BLOCK, output);
                sawbench(Items.BAMBOO_PLANKS, 4, Items.STRIPPED_BAMBOO_BLOCK, output);

                sawbench(Items.STICK, 4, Items.OAK_PLANKS, output);
                sawbench(Items.STICK, 4, Items.SPRUCE_PLANKS, output);
                sawbench(Items.STICK, 4, Items.BIRCH_PLANKS, output);
                sawbench(Items.STICK, 4, Items.JUNGLE_PLANKS, output);
                sawbench(Items.STICK, 4, Items.ACACIA_PLANKS, output);
                sawbench(Items.STICK, 4, Items.DARK_OAK_PLANKS, output);
                sawbench(Items.STICK, 4, Items.MANGROVE_PLANKS, output);
                sawbench(Items.STICK, 4, Items.CHERRY_PLANKS, output);
                sawbench(Items.STICK, 4, Items.PALE_OAK_PLANKS, output);
                sawbench(Items.STICK, 4, Items.CRIMSON_PLANKS, output);
                sawbench(Items.STICK, 4, Items.WARPED_PLANKS, output);
                sawbench(Items.STICK, 4, Items.BAMBOO_PLANKS, output);

                sawbench(Items.LADDER, 2, Items.OAK_PLANKS, output);
                sawbench(Items.LADDER, 2, Items.SPRUCE_PLANKS, output);
                sawbench(Items.LADDER, 2, Items.BIRCH_PLANKS, output);
                sawbench(Items.LADDER, 2, Items.JUNGLE_PLANKS, output);
                sawbench(Items.LADDER, 2, Items.ACACIA_PLANKS, output);
                sawbench(Items.LADDER, 2, Items.DARK_OAK_PLANKS, output);
                sawbench(Items.LADDER, 2, Items.MANGROVE_PLANKS, output);
                sawbench(Items.LADDER, 2, Items.CHERRY_PLANKS, output);
                sawbench(Items.LADDER, 2, Items.PALE_OAK_PLANKS, output);
                sawbench(Items.LADDER, 2, Items.CRIMSON_PLANKS, output);
                sawbench(Items.LADDER, 2, Items.WARPED_PLANKS, output);
                sawbench(Items.LADDER, 2, Items.BAMBOO_PLANKS, output);

                sawbench(Items.OAK_SLAB, 2, Items.OAK_PLANKS, output);
                sawbench(Items.OAK_STAIRS, 1, Items.OAK_PLANKS, output);
                sawbench(Items.OAK_FENCE, 1, Items.OAK_PLANKS, output);
                sawbench(Items.OAK_FENCE_GATE, 1, Items.OAK_PLANKS, output);

                sawbench(Items.SPRUCE_SLAB, 2, Items.SPRUCE_PLANKS, output);
                sawbench(Items.SPRUCE_STAIRS, 1, Items.SPRUCE_PLANKS, output);
                sawbench(Items.SPRUCE_FENCE, 1, Items.SPRUCE_PLANKS, output);
                sawbench(Items.SPRUCE_FENCE_GATE, 1, Items.SPRUCE_PLANKS, output);

                sawbench(Items.BIRCH_SLAB, 2, Items.BIRCH_PLANKS, output);
                sawbench(Items.BIRCH_STAIRS, 1, Items.BIRCH_PLANKS, output);
                sawbench(Items.BIRCH_FENCE, 1, Items.BIRCH_PLANKS, output);
                sawbench(Items.BIRCH_FENCE_GATE, 1, Items.BIRCH_PLANKS, output);

                sawbench(Items.JUNGLE_SLAB, 2, Items.JUNGLE_PLANKS, output);
                sawbench(Items.JUNGLE_STAIRS, 1, Items.JUNGLE_PLANKS, output);
                sawbench(Items.JUNGLE_FENCE, 1, Items.JUNGLE_PLANKS, output);
                sawbench(Items.JUNGLE_FENCE_GATE, 1, Items.JUNGLE_PLANKS, output);

                sawbench(Items.ACACIA_SLAB, 2, Items.ACACIA_PLANKS, output);
                sawbench(Items.ACACIA_STAIRS, 1, Items.ACACIA_PLANKS, output);
                sawbench(Items.ACACIA_FENCE, 1, Items.ACACIA_PLANKS, output);
                sawbench(Items.ACACIA_FENCE_GATE, 1, Items.ACACIA_PLANKS, output);

                sawbench(Items.DARK_OAK_SLAB, 2, Items.DARK_OAK_PLANKS, output);
                sawbench(Items.DARK_OAK_STAIRS, 1, Items.DARK_OAK_PLANKS, output);
                sawbench(Items.DARK_OAK_FENCE, 1, Items.DARK_OAK_PLANKS, output);
                sawbench(Items.DARK_OAK_FENCE_GATE, 1, Items.DARK_OAK_PLANKS, output);

                sawbench(Items.MANGROVE_SLAB, 2, Items.MANGROVE_PLANKS, output);
                sawbench(Items.MANGROVE_STAIRS, 1, Items.MANGROVE_PLANKS, output);
                sawbench(Items.MANGROVE_FENCE, 1, Items.MANGROVE_PLANKS, output);
                sawbench(Items.MANGROVE_FENCE_GATE, 1, Items.MANGROVE_PLANKS, output);

                sawbench(Items.CHERRY_SLAB, 2, Items.CHERRY_PLANKS, output);
                sawbench(Items.CHERRY_STAIRS, 1, Items.CHERRY_PLANKS, output);
                sawbench(Items.CHERRY_FENCE, 1, Items.CHERRY_PLANKS, output);
                sawbench(Items.CHERRY_FENCE_GATE, 1, Items.CHERRY_PLANKS, output);

                sawbench(Items.PALE_OAK_SLAB, 2, Items.PALE_OAK_PLANKS, output);
                sawbench(Items.PALE_OAK_STAIRS, 1, Items.PALE_OAK_PLANKS, output);
                sawbench(Items.PALE_OAK_FENCE, 1, Items.PALE_OAK_PLANKS, output);
                sawbench(Items.PALE_OAK_FENCE_GATE, 1, Items.PALE_OAK_PLANKS, output);

                sawbench(Items.CRIMSON_SLAB, 2, Items.CRIMSON_PLANKS, output);
                sawbench(Items.CRIMSON_STAIRS, 1, Items.CRIMSON_PLANKS, output);
                sawbench(Items.CRIMSON_FENCE, 1, Items.CRIMSON_PLANKS, output);
                sawbench(Items.CRIMSON_FENCE_GATE, 1, Items.CRIMSON_PLANKS, output);

                sawbench(Items.WARPED_SLAB, 2, Items.WARPED_PLANKS, output);
                sawbench(Items.WARPED_STAIRS, 1, Items.WARPED_PLANKS, output);
                sawbench(Items.WARPED_FENCE, 1, Items.WARPED_PLANKS, output);
                sawbench(Items.WARPED_FENCE_GATE, 1, Items.WARPED_PLANKS, output);

                sawbench(Items.BAMBOO_SLAB, 2, Items.BAMBOO_PLANKS, output);
                sawbench(Items.BAMBOO_STAIRS, 1, Items.BAMBOO_PLANKS, output);
                sawbench(Items.BAMBOO_FENCE, 1, Items.BAMBOO_PLANKS, output);
                sawbench(Items.BAMBOO_FENCE_GATE, 1, Items.BAMBOO_PLANKS, output);
                sawbench(Items.BAMBOO_MOSAIC_SLAB, 2, Items.BAMBOO_MOSAIC, output);
                sawbench(Items.BAMBOO_MOSAIC_STAIRS, 1, Items.BAMBOO_MOSAIC, output);

                sawbench(Items.STICK, 16, Items.OAK_LOG, output);
                sawbench(Items.STICK, 16, Items.OAK_WOOD, output);
                sawbench(Items.STICK, 16, Items.STRIPPED_OAK_LOG, output);
                sawbench(Items.STICK, 16, Items.STRIPPED_OAK_WOOD, output);
                sawbench(Items.STICK, 16, Items.SPRUCE_LOG, output);
                sawbench(Items.STICK, 16, Items.SPRUCE_WOOD, output);
                sawbench(Items.STICK, 16, Items.STRIPPED_SPRUCE_LOG, output);
                sawbench(Items.STICK, 16, Items.STRIPPED_SPRUCE_WOOD, output);
                sawbench(Items.STICK, 16, Items.BIRCH_LOG, output);
                sawbench(Items.STICK, 16, Items.BIRCH_WOOD, output);
                sawbench(Items.STICK, 16, Items.STRIPPED_BIRCH_LOG, output);
                sawbench(Items.STICK, 16, Items.STRIPPED_BIRCH_WOOD, output);
                sawbench(Items.STICK, 16, Items.JUNGLE_LOG, output);
                sawbench(Items.STICK, 16, Items.JUNGLE_WOOD, output);
                sawbench(Items.STICK, 16, Items.STRIPPED_JUNGLE_LOG, output);
                sawbench(Items.STICK, 16, Items.STRIPPED_JUNGLE_WOOD, output);
                sawbench(Items.STICK, 16, Items.ACACIA_LOG, output);
                sawbench(Items.STICK, 16, Items.ACACIA_WOOD, output);
                sawbench(Items.STICK, 16, Items.STRIPPED_ACACIA_LOG, output);
                sawbench(Items.STICK, 16, Items.STRIPPED_ACACIA_WOOD, output);
                sawbench(Items.STICK, 16, Items.DARK_OAK_LOG, output);
                sawbench(Items.STICK, 16, Items.DARK_OAK_WOOD, output);
                sawbench(Items.STICK, 16, Items.STRIPPED_DARK_OAK_LOG, output);
                sawbench(Items.STICK, 16, Items.STRIPPED_DARK_OAK_WOOD, output);
                sawbench(Items.STICK, 16, Items.MANGROVE_LOG, output);
                sawbench(Items.STICK, 16, Items.MANGROVE_WOOD, output);
                sawbench(Items.STICK, 16, Items.STRIPPED_MANGROVE_LOG, output);
                sawbench(Items.STICK, 16, Items.STRIPPED_MANGROVE_WOOD, output);
                sawbench(Items.STICK, 16, Items.CHERRY_LOG, output);
                sawbench(Items.STICK, 16, Items.CHERRY_WOOD, output);
                sawbench(Items.STICK, 16, Items.STRIPPED_CHERRY_LOG, output);
                sawbench(Items.STICK, 16, Items.STRIPPED_CHERRY_WOOD, output);
                sawbench(Items.STICK, 16, Items.PALE_OAK_LOG, output);
                sawbench(Items.STICK, 16, Items.PALE_OAK_WOOD, output);
                sawbench(Items.STICK, 16, Items.STRIPPED_PALE_OAK_LOG, output);
                sawbench(Items.STICK, 16, Items.STRIPPED_PALE_OAK_WOOD, output);
                sawbench(Items.STICK, 16, Items.CRIMSON_STEM, output);
                sawbench(Items.STICK, 16, Items.CRIMSON_HYPHAE, output);
                sawbench(Items.STICK, 16, Items.STRIPPED_CRIMSON_STEM, output);
                sawbench(Items.STICK, 16, Items.STRIPPED_CRIMSON_HYPHAE, output);
                sawbench(Items.STICK, 16, Items.WARPED_STEM, output);
                sawbench(Items.STICK, 16, Items.WARPED_HYPHAE, output);
                sawbench(Items.STICK, 16, Items.STRIPPED_WARPED_STEM, output);
                sawbench(Items.STICK, 16, Items.STRIPPED_WARPED_HYPHAE, output);
                sawbench(Items.STICK, 16, Items.BAMBOO_BLOCK, output);
                sawbench(Items.STICK, 16, Items.STRIPPED_BAMBOO_BLOCK, output);

                sawbench(Items.OAK_DOOR, 1, Items.OAK_PLANKS, output);
                sawbench(Items.OAK_TRAPDOOR, 2, Items.OAK_PLANKS, output);
                sawbench(Items.OAK_PRESSURE_PLATE, 1, Items.OAK_PLANKS, output);
                sawbench(Items.OAK_BUTTON, 1, Items.OAK_PLANKS, output);
                sawbench(Items.OAK_SIGN, 2, Items.OAK_PLANKS, output);
                sawbench(Items.OAK_HANGING_SIGN, 1, Items.OAK_PLANKS, output);
                sawbench(Items.OAK_BOAT, 1, Items.OAK_PLANKS, output);

                sawbench(Items.SPRUCE_DOOR, 1, Items.SPRUCE_PLANKS, output);
                sawbench(Items.SPRUCE_TRAPDOOR, 2, Items.SPRUCE_PLANKS, output);
                sawbench(Items.SPRUCE_PRESSURE_PLATE, 1, Items.SPRUCE_PLANKS, output);
                sawbench(Items.SPRUCE_BUTTON, 1, Items.SPRUCE_PLANKS, output);
                sawbench(Items.SPRUCE_SIGN, 2, Items.SPRUCE_PLANKS, output);
                sawbench(Items.SPRUCE_HANGING_SIGN, 1, Items.SPRUCE_PLANKS, output);
                sawbench(Items.SPRUCE_BOAT, 1, Items.SPRUCE_PLANKS, output);

                sawbench(Items.BIRCH_DOOR, 1, Items.BIRCH_PLANKS, output);
                sawbench(Items.BIRCH_TRAPDOOR, 2, Items.BIRCH_PLANKS, output);
                sawbench(Items.BIRCH_PRESSURE_PLATE, 1, Items.BIRCH_PLANKS, output);
                sawbench(Items.BIRCH_BUTTON, 1, Items.BIRCH_PLANKS, output);
                sawbench(Items.BIRCH_SIGN, 2, Items.BIRCH_PLANKS, output);
                sawbench(Items.BIRCH_HANGING_SIGN, 1, Items.BIRCH_PLANKS, output);
                sawbench(Items.BIRCH_BOAT, 1, Items.BIRCH_PLANKS, output);

                sawbench(Items.JUNGLE_DOOR, 1, Items.JUNGLE_PLANKS, output);
                sawbench(Items.JUNGLE_TRAPDOOR, 2, Items.JUNGLE_PLANKS, output);
                sawbench(Items.JUNGLE_PRESSURE_PLATE, 1, Items.JUNGLE_PLANKS, output);
                sawbench(Items.JUNGLE_BUTTON, 1, Items.JUNGLE_PLANKS, output);
                sawbench(Items.JUNGLE_SIGN, 2, Items.JUNGLE_PLANKS, output);
                sawbench(Items.JUNGLE_HANGING_SIGN, 1, Items.JUNGLE_PLANKS, output);
                sawbench(Items.JUNGLE_BOAT, 1, Items.JUNGLE_PLANKS, output);

                sawbench(Items.ACACIA_DOOR, 1, Items.ACACIA_PLANKS, output);
                sawbench(Items.ACACIA_TRAPDOOR, 2, Items.ACACIA_PLANKS, output);
                sawbench(Items.ACACIA_PRESSURE_PLATE, 1, Items.ACACIA_PLANKS, output);
                sawbench(Items.ACACIA_BUTTON, 1, Items.ACACIA_PLANKS, output);
                sawbench(Items.ACACIA_SIGN, 2, Items.ACACIA_PLANKS, output);
                sawbench(Items.ACACIA_HANGING_SIGN, 1, Items.ACACIA_PLANKS, output);
                sawbench(Items.ACACIA_BOAT, 1, Items.ACACIA_PLANKS, output);

                sawbench(Items.DARK_OAK_DOOR, 1, Items.DARK_OAK_PLANKS, output);
                sawbench(Items.DARK_OAK_TRAPDOOR, 2, Items.DARK_OAK_PLANKS, output);
                sawbench(Items.DARK_OAK_PRESSURE_PLATE, 1, Items.DARK_OAK_PLANKS, output);
                sawbench(Items.DARK_OAK_BUTTON, 1, Items.DARK_OAK_PLANKS, output);
                sawbench(Items.DARK_OAK_SIGN, 2, Items.DARK_OAK_PLANKS, output);
                sawbench(Items.DARK_OAK_HANGING_SIGN, 1, Items.DARK_OAK_PLANKS, output);
                sawbench(Items.DARK_OAK_BOAT, 1, Items.DARK_OAK_PLANKS, output);

                sawbench(Items.MANGROVE_DOOR, 1, Items.MANGROVE_PLANKS, output);
                sawbench(Items.MANGROVE_TRAPDOOR, 2, Items.MANGROVE_PLANKS, output);
                sawbench(Items.MANGROVE_PRESSURE_PLATE, 1, Items.MANGROVE_PLANKS, output);
                sawbench(Items.MANGROVE_BUTTON, 1, Items.MANGROVE_PLANKS, output);
                sawbench(Items.MANGROVE_SIGN, 2, Items.MANGROVE_PLANKS, output);
                sawbench(Items.MANGROVE_HANGING_SIGN, 1, Items.MANGROVE_PLANKS, output);
                sawbench(Items.MANGROVE_BOAT, 1, Items.MANGROVE_PLANKS, output);

                sawbench(Items.CHERRY_DOOR, 1, Items.CHERRY_PLANKS, output);
                sawbench(Items.CHERRY_TRAPDOOR, 2, Items.CHERRY_PLANKS, output);
                sawbench(Items.CHERRY_PRESSURE_PLATE, 1, Items.CHERRY_PLANKS, output);
                sawbench(Items.CHERRY_BUTTON, 1, Items.CHERRY_PLANKS, output);
                sawbench(Items.CHERRY_SIGN, 2, Items.CHERRY_PLANKS, output);
                sawbench(Items.CHERRY_HANGING_SIGN, 1, Items.CHERRY_PLANKS, output);
                sawbench(Items.CHERRY_BOAT, 1, Items.CHERRY_PLANKS, output);

                sawbench(Items.PALE_OAK_DOOR, 1, Items.PALE_OAK_PLANKS, output);
                sawbench(Items.PALE_OAK_TRAPDOOR, 2, Items.PALE_OAK_PLANKS, output);
                sawbench(Items.PALE_OAK_PRESSURE_PLATE, 1, Items.PALE_OAK_PLANKS, output);
                sawbench(Items.PALE_OAK_BUTTON, 1, Items.PALE_OAK_PLANKS, output);
                sawbench(Items.PALE_OAK_SIGN, 2, Items.PALE_OAK_PLANKS, output);
                sawbench(Items.PALE_OAK_HANGING_SIGN, 1, Items.PALE_OAK_PLANKS, output);
                sawbench(Items.PALE_OAK_BOAT, 1, Items.PALE_OAK_PLANKS, output);

                sawbench(Items.CRIMSON_DOOR, 1, Items.CRIMSON_PLANKS, output);
                sawbench(Items.CRIMSON_TRAPDOOR, 2, Items.CRIMSON_PLANKS, output);
                sawbench(Items.CRIMSON_PRESSURE_PLATE, 1, Items.CRIMSON_PLANKS, output);
                sawbench(Items.CRIMSON_BUTTON, 1, Items.CRIMSON_PLANKS, output);
                sawbench(Items.CRIMSON_SIGN, 2, Items.CRIMSON_PLANKS, output);
                sawbench(Items.CRIMSON_HANGING_SIGN, 1, Items.CRIMSON_PLANKS, output);

                sawbench(Items.WARPED_DOOR, 1, Items.WARPED_PLANKS, output);
                sawbench(Items.WARPED_TRAPDOOR, 2, Items.WARPED_PLANKS, output);
                sawbench(Items.WARPED_PRESSURE_PLATE, 1, Items.WARPED_PLANKS, output);
                sawbench(Items.WARPED_BUTTON, 1, Items.WARPED_PLANKS, output);
                sawbench(Items.WARPED_SIGN, 2, Items.WARPED_PLANKS, output);
                sawbench(Items.WARPED_HANGING_SIGN, 1, Items.WARPED_PLANKS, output);

                sawbench(Items.BAMBOO_DOOR, 1, Items.BAMBOO_PLANKS, output);
                sawbench(Items.BAMBOO_TRAPDOOR, 2, Items.BAMBOO_PLANKS, output);
                sawbench(Items.BAMBOO_PRESSURE_PLATE, 1, Items.BAMBOO_PLANKS, output);
                sawbench(Items.BAMBOO_BUTTON, 1, Items.BAMBOO_PLANKS, output);
                sawbench(Items.BAMBOO_SIGN, 2, Items.BAMBOO_PLANKS, output);
                sawbench(Items.BAMBOO_HANGING_SIGN, 1, Items.BAMBOO_PLANKS, output);
                sawbench(Items.BAMBOO_RAFT, 1, Items.BAMBOO_PLANKS, output);

                sawbench(Items.CHEST, 1, Items.OAK_LOG, output);
                sawbench(Items.STRIPPED_OAK_LOG, 1, Items.OAK_LOG, output);
                sawbench(Items.BARREL, 1, Items.OAK_LOG, output);
                sawbench(UPBlocks.OAK_BARREL, 1, Items.OAK_LOG, output);
                sawbench(Items.CHEST, 1, Items.OAK_WOOD, output);
                sawbench(Items.STRIPPED_OAK_WOOD, 1, Items.OAK_WOOD, output);
                sawbench(Items.BARREL, 1, Items.OAK_WOOD, output);
                sawbench(UPBlocks.OAK_BARREL, 1, Items.OAK_WOOD, output);
                sawbench(Items.CHEST, 1, Items.STRIPPED_OAK_LOG, output);
                sawbench(Items.BARREL, 1, Items.STRIPPED_OAK_LOG, output);
                sawbench(UPBlocks.OAK_BARREL, 1, Items.STRIPPED_OAK_LOG, output);
                sawbench(Items.CHEST, 1, Items.STRIPPED_OAK_WOOD, output);
                sawbench(Items.BARREL, 1, Items.STRIPPED_OAK_WOOD, output);
                sawbench(UPBlocks.OAK_BARREL, 1, Items.STRIPPED_OAK_WOOD, output);

                sawbench(Items.CHEST, 1, Items.SPRUCE_LOG, output);
                sawbench(Items.STRIPPED_SPRUCE_LOG, 1, Items.SPRUCE_LOG, output);
                sawbench(Items.BARREL, 1, Items.SPRUCE_LOG, output);
                sawbench(UPBlocks.SPRUCE_BARREL, 1, Items.SPRUCE_LOG, output);
                sawbench(Items.CHEST, 1, Items.SPRUCE_WOOD, output);
                sawbench(Items.STRIPPED_SPRUCE_WOOD, 1, Items.SPRUCE_WOOD, output);
                sawbench(Items.BARREL, 1, Items.SPRUCE_WOOD, output);
                sawbench(UPBlocks.SPRUCE_BARREL, 1, Items.SPRUCE_WOOD, output);
                sawbench(Items.CHEST, 1, Items.STRIPPED_SPRUCE_LOG, output);
                sawbench(Items.BARREL, 1, Items.STRIPPED_SPRUCE_LOG, output);
                sawbench(UPBlocks.SPRUCE_BARREL, 1, Items.STRIPPED_SPRUCE_LOG, output);
                sawbench(Items.CHEST, 1, Items.STRIPPED_SPRUCE_WOOD, output);
                sawbench(Items.BARREL, 1, Items.STRIPPED_SPRUCE_WOOD, output);
                sawbench(UPBlocks.SPRUCE_BARREL, 1, Items.STRIPPED_SPRUCE_WOOD, output);

                sawbench(Items.CHEST, 1, Items.BIRCH_LOG, output);
                sawbench(Items.STRIPPED_BIRCH_LOG, 1, Items.BIRCH_LOG, output);
                sawbench(Items.BARREL, 1, Items.BIRCH_LOG, output);
                sawbench(UPBlocks.BIRCH_BARREL, 1, Items.BIRCH_LOG, output);
                sawbench(Items.CHEST, 1, Items.BIRCH_WOOD, output);
                sawbench(Items.STRIPPED_BIRCH_WOOD, 1, Items.BIRCH_WOOD, output);
                sawbench(Items.BARREL, 1, Items.BIRCH_WOOD, output);
                sawbench(UPBlocks.BIRCH_BARREL, 1, Items.BIRCH_WOOD, output);
                sawbench(Items.CHEST, 1, Items.STRIPPED_BIRCH_LOG, output);
                sawbench(Items.BARREL, 1, Items.STRIPPED_BIRCH_LOG, output);
                sawbench(UPBlocks.BIRCH_BARREL, 1, Items.STRIPPED_BIRCH_LOG, output);
                sawbench(Items.CHEST, 1, Items.STRIPPED_BIRCH_WOOD, output);
                sawbench(Items.BARREL, 1, Items.STRIPPED_BIRCH_WOOD, output);
                sawbench(UPBlocks.BIRCH_BARREL, 1, Items.STRIPPED_BIRCH_WOOD, output);

                sawbench(Items.CHEST, 1, Items.JUNGLE_LOG, output);
                sawbench(Items.STRIPPED_JUNGLE_LOG, 1, Items.JUNGLE_LOG, output);
                sawbench(Items.BARREL, 1, Items.JUNGLE_LOG, output);
                sawbench(UPBlocks.JUNGLE_BARREL, 1, Items.JUNGLE_LOG, output);
                sawbench(Items.CHEST, 1, Items.JUNGLE_WOOD, output);
                sawbench(Items.STRIPPED_JUNGLE_WOOD, 1, Items.JUNGLE_WOOD, output);
                sawbench(Items.BARREL, 1, Items.JUNGLE_WOOD, output);
                sawbench(UPBlocks.JUNGLE_BARREL, 1, Items.JUNGLE_WOOD, output);
                sawbench(Items.CHEST, 1, Items.STRIPPED_JUNGLE_LOG, output);
                sawbench(Items.BARREL, 1, Items.STRIPPED_JUNGLE_LOG, output);
                sawbench(UPBlocks.JUNGLE_BARREL, 1, Items.STRIPPED_JUNGLE_LOG, output);
                sawbench(Items.CHEST, 1, Items.STRIPPED_JUNGLE_WOOD, output);
                sawbench(Items.BARREL, 1, Items.STRIPPED_JUNGLE_WOOD, output);
                sawbench(UPBlocks.JUNGLE_BARREL, 1, Items.STRIPPED_JUNGLE_WOOD, output);

                sawbench(Items.CHEST, 1, Items.ACACIA_LOG, output);
                sawbench(Items.STRIPPED_ACACIA_LOG, 1, Items.ACACIA_LOG, output);
                sawbench(Items.BARREL, 1, Items.ACACIA_LOG, output);
                sawbench(UPBlocks.ACACIA_BARREL, 1, Items.ACACIA_LOG, output);
                sawbench(Items.CHEST, 1, Items.ACACIA_WOOD, output);
                sawbench(Items.STRIPPED_ACACIA_WOOD, 1, Items.ACACIA_WOOD, output);
                sawbench(Items.BARREL, 1, Items.ACACIA_WOOD, output);
                sawbench(UPBlocks.ACACIA_BARREL, 1, Items.ACACIA_WOOD, output);
                sawbench(Items.CHEST, 1, Items.STRIPPED_ACACIA_LOG, output);
                sawbench(Items.BARREL, 1, Items.STRIPPED_ACACIA_LOG, output);
                sawbench(UPBlocks.ACACIA_BARREL, 1, Items.STRIPPED_ACACIA_LOG, output);
                sawbench(Items.CHEST, 1, Items.STRIPPED_ACACIA_WOOD, output);
                sawbench(Items.BARREL, 1, Items.STRIPPED_ACACIA_WOOD, output);
                sawbench(UPBlocks.ACACIA_BARREL, 1, Items.STRIPPED_ACACIA_WOOD, output);

                sawbench(Items.CHEST, 1, Items.DARK_OAK_LOG, output);
                sawbench(Items.STRIPPED_DARK_OAK_LOG, 1, Items.DARK_OAK_LOG, output);
                sawbench(Items.BARREL, 1, Items.DARK_OAK_LOG, output);
                sawbench(UPBlocks.DARK_OAK_BARREL, 1, Items.DARK_OAK_LOG, output);
                sawbench(Items.CHEST, 1, Items.DARK_OAK_WOOD, output);
                sawbench(Items.STRIPPED_DARK_OAK_WOOD, 1, Items.DARK_OAK_WOOD, output);
                sawbench(Items.BARREL, 1, Items.DARK_OAK_WOOD, output);
                sawbench(UPBlocks.DARK_OAK_BARREL, 1, Items.DARK_OAK_WOOD, output);
                sawbench(Items.CHEST, 1, Items.STRIPPED_DARK_OAK_LOG, output);
                sawbench(Items.BARREL, 1, Items.STRIPPED_DARK_OAK_LOG, output);
                sawbench(UPBlocks.DARK_OAK_BARREL, 1, Items.STRIPPED_DARK_OAK_LOG, output);
                sawbench(Items.CHEST, 1, Items.STRIPPED_DARK_OAK_WOOD, output);
                sawbench(Items.BARREL, 1, Items.STRIPPED_DARK_OAK_WOOD, output);
                sawbench(UPBlocks.DARK_OAK_BARREL, 1, Items.STRIPPED_DARK_OAK_WOOD, output);

                sawbench(Items.CHEST, 1, Items.MANGROVE_LOG, output);
                sawbench(Items.STRIPPED_MANGROVE_LOG, 1, Items.MANGROVE_LOG, output);
                sawbench(Items.BARREL, 1, Items.MANGROVE_LOG, output);
                sawbench(UPBlocks.MANGROVE_BARREL, 1, Items.MANGROVE_LOG, output);
                sawbench(Items.CHEST, 1, Items.MANGROVE_WOOD, output);
                sawbench(Items.STRIPPED_MANGROVE_WOOD, 1, Items.MANGROVE_WOOD, output);
                sawbench(Items.BARREL, 1, Items.MANGROVE_WOOD, output);
                sawbench(UPBlocks.MANGROVE_BARREL, 1, Items.MANGROVE_WOOD, output);
                sawbench(Items.CHEST, 1, Items.STRIPPED_MANGROVE_LOG, output);
                sawbench(Items.BARREL, 1, Items.STRIPPED_MANGROVE_LOG, output);
                sawbench(UPBlocks.MANGROVE_BARREL, 1, Items.STRIPPED_MANGROVE_LOG, output);
                sawbench(Items.CHEST, 1, Items.STRIPPED_MANGROVE_WOOD, output);
                sawbench(Items.BARREL, 1, Items.STRIPPED_MANGROVE_WOOD, output);
                sawbench(UPBlocks.MANGROVE_BARREL, 1, Items.STRIPPED_MANGROVE_WOOD, output);

                sawbench(Items.CHEST, 1, Items.CHERRY_LOG, output);
                sawbench(Items.STRIPPED_CHERRY_LOG, 1, Items.CHERRY_LOG, output);
                sawbench(Items.BARREL, 1, Items.CHERRY_LOG, output);
                sawbench(UPBlocks.CHERRY_BARREL, 1, Items.CHERRY_LOG, output);
                sawbench(Items.CHEST, 1, Items.CHERRY_WOOD, output);
                sawbench(Items.STRIPPED_CHERRY_WOOD, 1, Items.CHERRY_WOOD, output);
                sawbench(Items.BARREL, 1, Items.CHERRY_WOOD, output);
                sawbench(UPBlocks.CHERRY_BARREL, 1, Items.CHERRY_WOOD, output);
                sawbench(Items.CHEST, 1, Items.STRIPPED_CHERRY_LOG, output);
                sawbench(Items.BARREL, 1, Items.STRIPPED_CHERRY_LOG, output);
                sawbench(UPBlocks.CHERRY_BARREL, 1, Items.STRIPPED_CHERRY_LOG, output);
                sawbench(Items.CHEST, 1, Items.STRIPPED_CHERRY_WOOD, output);
                sawbench(Items.BARREL, 1, Items.STRIPPED_CHERRY_WOOD, output);
                sawbench(UPBlocks.CHERRY_BARREL, 1, Items.STRIPPED_CHERRY_WOOD, output);

                sawbench(Items.CHEST, 1, Items.PALE_OAK_LOG, output);
                sawbench(Items.STRIPPED_PALE_OAK_LOG, 1, Items.PALE_OAK_LOG, output);
                sawbench(Items.BARREL, 1, Items.PALE_OAK_LOG, output);
                sawbench(UPBlocks.PALE_OAK_BARREL, 1, Items.PALE_OAK_LOG, output);
                sawbench(Items.CHEST, 1, Items.PALE_OAK_WOOD, output);
                sawbench(Items.STRIPPED_PALE_OAK_WOOD, 1, Items.PALE_OAK_WOOD, output);
                sawbench(Items.BARREL, 1, Items.PALE_OAK_WOOD, output);
                sawbench(UPBlocks.PALE_OAK_BARREL, 1, Items.PALE_OAK_WOOD, output);
                sawbench(Items.CHEST, 1, Items.STRIPPED_PALE_OAK_LOG, output);
                sawbench(Items.BARREL, 1, Items.STRIPPED_PALE_OAK_LOG, output);
                sawbench(UPBlocks.PALE_OAK_BARREL, 1, Items.STRIPPED_PALE_OAK_LOG, output);
                sawbench(Items.CHEST, 1, Items.STRIPPED_PALE_OAK_WOOD, output);
                sawbench(Items.BARREL, 1, Items.STRIPPED_PALE_OAK_WOOD, output);
                sawbench(UPBlocks.PALE_OAK_BARREL, 1, Items.STRIPPED_PALE_OAK_WOOD, output);

                sawbench(Items.CHEST, 1, Items.CRIMSON_STEM, output);
                sawbench(Items.STRIPPED_CRIMSON_STEM, 1, Items.CRIMSON_STEM, output);
                sawbench(Items.BARREL, 1, Items.CRIMSON_STEM, output);
                sawbench(UPBlocks.CRIMSON_BARREL, 1, Items.CRIMSON_STEM, output);
                sawbench(Items.CHEST, 1, Items.CRIMSON_HYPHAE, output);
                sawbench(Items.STRIPPED_CRIMSON_HYPHAE, 1, Items.CRIMSON_HYPHAE, output);
                sawbench(Items.BARREL, 1, Items.CRIMSON_HYPHAE, output);
                sawbench(UPBlocks.CRIMSON_BARREL, 1, Items.CRIMSON_HYPHAE, output);
                sawbench(Items.CHEST, 1, Items.STRIPPED_CRIMSON_STEM, output);
                sawbench(Items.BARREL, 1, Items.STRIPPED_CRIMSON_STEM, output);
                sawbench(UPBlocks.CRIMSON_BARREL, 1, Items.STRIPPED_CRIMSON_STEM, output);
                sawbench(Items.CHEST, 1, Items.STRIPPED_CRIMSON_HYPHAE, output);
                sawbench(Items.BARREL, 1, Items.STRIPPED_CRIMSON_HYPHAE, output);
                sawbench(UPBlocks.CRIMSON_BARREL, 1, Items.STRIPPED_CRIMSON_HYPHAE, output);

                sawbench(Items.CHEST, 1, Items.WARPED_STEM, output);
                sawbench(Items.STRIPPED_WARPED_STEM, 1, Items.WARPED_STEM, output);
                sawbench(Items.BARREL, 1, Items.WARPED_STEM, output);
                sawbench(UPBlocks.WARPED_BARREL, 1, Items.WARPED_STEM, output);
                sawbench(Items.CHEST, 1, Items.WARPED_HYPHAE, output);
                sawbench(Items.STRIPPED_WARPED_HYPHAE, 1, Items.WARPED_HYPHAE, output);
                sawbench(Items.BARREL, 1, Items.WARPED_HYPHAE, output);
                sawbench(UPBlocks.WARPED_BARREL, 1, Items.WARPED_HYPHAE, output);
                sawbench(Items.CHEST, 1, Items.STRIPPED_WARPED_STEM, output);
                sawbench(Items.BARREL, 1, Items.STRIPPED_WARPED_STEM, output);
                sawbench(UPBlocks.WARPED_BARREL, 1, Items.STRIPPED_WARPED_STEM, output);
                sawbench(Items.CHEST, 1, Items.STRIPPED_WARPED_HYPHAE, output);
                sawbench(Items.BARREL, 1, Items.STRIPPED_WARPED_HYPHAE, output);
                sawbench(UPBlocks.WARPED_BARREL, 1, Items.STRIPPED_WARPED_HYPHAE, output);

                sawbench(Items.CHEST, 1, Items.BAMBOO_BLOCK, output);
                sawbench(Items.STRIPPED_BAMBOO_BLOCK, 1, Items.BAMBOO_BLOCK, output);
                sawbench(Items.BARREL, 1, Items.BAMBOO_BLOCK, output);
                sawbench(Items.CHEST, 1, Items.STRIPPED_BAMBOO_BLOCK, output);
                sawbench(Items.BARREL, 1, Items.STRIPPED_BAMBOO_BLOCK, output);

                sawbench(UPBlocks.OAK_VERTICAL_SLAB, 2, Items.OAK_PLANKS, output);
                sawbench(UPBlocks.SPRUCE_VERTICAL_SLAB, 2, Items.SPRUCE_PLANKS, output);
                sawbench(UPBlocks.BIRCH_VERTICAL_SLAB, 2, Items.BIRCH_PLANKS, output);
                sawbench(UPBlocks.JUNGLE_VERTICAL_SLAB, 2, Items.JUNGLE_PLANKS, output);
                sawbench(UPBlocks.ACACIA_VERTICAL_SLAB, 2, Items.ACACIA_PLANKS, output);
                sawbench(UPBlocks.DARK_OAK_VERTICAL_SLAB, 2, Items.DARK_OAK_PLANKS, output);
                sawbench(UPBlocks.MANGROVE_VERTICAL_SLAB, 2, Items.MANGROVE_PLANKS, output);
                sawbench(UPBlocks.CHERRY_VERTICAL_SLAB, 2, Items.CHERRY_PLANKS, output);
                sawbench(UPBlocks.PALE_OAK_VERTICAL_SLAB, 2, Items.PALE_OAK_PLANKS, output);
                sawbench(UPBlocks.CRIMSON_VERTICAL_SLAB, 2, Items.CRIMSON_PLANKS, output);
                sawbench(UPBlocks.WARPED_VERTICAL_SLAB, 2, Items.WARPED_PLANKS, output);
                sawbench(UPBlocks.BAMBOO_VERTICAL_SLAB, 2, Items.BAMBOO_PLANKS, output);

                sawbench(UPBlocks.ACACIA_BEAM, 2, Items.ACACIA_PLANKS, output);
                sawbench(UPBlocks.BAMBOO_BEAM, 2, Items.BAMBOO_PLANKS, output);
                sawbench(UPBlocks.BIRCH_BEAM, 2, Items.BIRCH_PLANKS, output);
                sawbench(UPBlocks.CHERRY_BEAM, 2, Items.CHERRY_PLANKS, output);
                sawbench(UPBlocks.CRIMSON_BEAM, 2, Items.CRIMSON_PLANKS, output);
                sawbench(UPBlocks.DARK_OAK_BEAM, 2, Items.DARK_OAK_PLANKS, output);
                sawbench(UPBlocks.JUNGLE_BEAM, 2, Items.JUNGLE_PLANKS, output);
                sawbench(UPBlocks.MANGROVE_BEAM, 2, Items.MANGROVE_PLANKS, output);
                sawbench(UPBlocks.OAK_BEAM, 2, Items.OAK_PLANKS, output);
                sawbench(UPBlocks.PALE_OAK_BEAM, 2, Items.PALE_OAK_PLANKS, output);
                sawbench(UPBlocks.SPRUCE_BEAM, 2, Items.SPRUCE_PLANKS, output);
                sawbench(UPBlocks.WARPED_BEAM, 2, Items.WARPED_PLANKS, output);
            }

            private void sawbench(ItemLike result, int count, ItemLike ingredient, RecipeOutput out) {
                String resultName = getItemName(result.asItem());
                String ingredientName = getItemName(ingredient.asItem());
                ResourceKey<Recipe<?>> key = ResourceKey.create(
                        Registries.RECIPE,
                        Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "sawbench/" + resultName + "_from_" + ingredientName)
                );
                SawbenchRecipe recipe = new SawbenchRecipe(
                        Ingredient.of(ingredient),
                        new ItemStackTemplate(result.asItem(), count)
                );
                out.accept(key, recipe, null);
            }
        };
    }

    @Override
    public String getName() {
        return "UtilitiesPlus Recipes";
    }
}