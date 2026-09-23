package com.misterd.utilitiesplus.util;

import com.misterd.utilitiesplus.block.UPBlocks;
import com.misterd.utilitiesplus.item.UPItems;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CookingFuel;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.floats.ResolvableFloat;
import net.minecraft.world.level.storage.loot.providers.number.ints.ResolvableInt;

public class UPFuels {
    public static void register() {
        DefaultItemComponentEvents.MODIFY.register(modifyContext -> {
            modifyContext.modify(UPBlocks.CHARCOAL_BLOCK.asItem(), builder ->
                    builder.set(DataComponents.COOKING_FUEL, fuel(16000)));
            modifyContext.modify(UPItems.CHARCOAL_BIT, builder ->
                    builder.set(DataComponents.COOKING_FUEL, fuel(200)));
            modifyContext.modify(UPItems.COAL_BIT, builder ->
                    builder.set(DataComponents.COOKING_FUEL, fuel(200)));
        });
    }

    private static CookingFuel fuel(int burnTime) {
        return new CookingFuel(
                new ResolvableInt.Constant(burnTime),
                ResolvableFloat.fromKey(ContextFloatProviders.COOKING_DEFAULT_SPEED_MULTIPLIER)
        );
    }
}