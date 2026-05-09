package com.misterd.utilitiesplus.recipe;

import com.misterd.utilitiesplus.UtilitiesPlus;
import com.misterd.utilitiesplus.recipe.custom.BarrelResetRecipe;
import com.misterd.utilitiesplus.recipe.custom.BarrelResetRecipeSerializer;
import com.misterd.utilitiesplus.recipe.custom.SawbenchRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class UPRecipes {

    public static final RecipeSerializer<BarrelResetRecipe> BARREL_RESET_SERIALIZER =
            Registry.register(
                    BuiltInRegistries.RECIPE_SERIALIZER,
                    Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "barrel_reset"),
                    BarrelResetRecipeSerializer.INSTANCE
            );

    public static final RecipeType<SawbenchRecipe> SAWBENCH_TYPE =
            Registry.register(
                    BuiltInRegistries.RECIPE_TYPE,
                    Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "sawbench"),
                    new RecipeType<>() {
                        @Override
                        public String toString() {
                            return "utilitiesplus:sawbench";
                        }
                    }
            );

    public static final RecipeSerializer<SawbenchRecipe> SAWBENCH_SERIALIZER =
            Registry.register(
                    BuiltInRegistries.RECIPE_SERIALIZER,
                    Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "sawbench"),
                    new RecipeSerializer<>(SawbenchRecipe.CODEC, SawbenchRecipe.STREAM_CODEC)
            );

    public static void register() {}
}