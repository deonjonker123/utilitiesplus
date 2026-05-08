package com.misterd.utilitiesplus.recipe;

import com.misterd.utilitiesplus.UtilitiesPlus;
import com.misterd.utilitiesplus.recipe.custom.BarrelResetRecipe;
import com.misterd.utilitiesplus.recipe.custom.BarrelResetRecipeSerializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class UPRecipes {

    public static final RecipeSerializer<BarrelResetRecipe> BARREL_RESET_SERIALIZER =
            Registry.register(
                    BuiltInRegistries.RECIPE_SERIALIZER,
                    Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "barrel_reset"),
                    BarrelResetRecipeSerializer.INSTANCE
            );

    public static void register() {}
}