package com.misterd.utilitiesplus.recipe.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class BarrelResetRecipeSerializer {

    public static final RecipeSerializer<BarrelResetRecipe> INSTANCE = new RecipeSerializer<>(
            MapCodec.unit(BarrelResetRecipe::new),
            StreamCodec.unit(new BarrelResetRecipe())
    );
}