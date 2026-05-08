package com.misterd.utilitiesplus.recipe.custom;

import com.misterd.utilitiesplus.component.UPDataComponents;
import com.misterd.utilitiesplus.component.custom.BarrelData;
import com.misterd.utilitiesplus.item.custom.BarrelBlockItem;
import com.misterd.utilitiesplus.recipe.UPRecipes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class BarrelResetRecipe extends CustomRecipe {

    @Override
    public boolean matches(CraftingInput input, Level level) {
        int nonEmpty = 0;
        ItemStack found = ItemStack.EMPTY;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (!stack.isEmpty()) {
                nonEmpty++;
                found = stack;
            }
        }

        if (nonEmpty != 1) return false;
        if (!(found.getItem() instanceof BarrelBlockItem)) return false;

        BarrelData data = found.get(UPDataComponents.BARREL_DATA);
        if (data == null) return false;

        return data.storedCount() == 0;
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (!stack.isEmpty() && stack.getItem() instanceof BarrelBlockItem) {
                ItemStack result = stack.copyWithCount(1);
                result.remove(UPDataComponents.BARREL_DATA);
                return result;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public RecipeSerializer<BarrelResetRecipe> getSerializer() {
        return UPRecipes.BARREL_RESET_SERIALIZER;
    }
}