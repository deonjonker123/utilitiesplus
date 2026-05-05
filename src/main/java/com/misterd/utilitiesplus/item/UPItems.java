package com.misterd.utilitiesplus.item;

import com.misterd.utilitiesplus.UtilitiesPlus;
import com.misterd.utilitiesplus.entity.UPEntities;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class UPItems {
    public static final Item COPPER_UPGRADE = registerItem("copper_upgrade",
            properties -> new Item(properties));

    public static final Item IRON_UPGRADE = registerItem("iron_upgrade",
            properties -> new Item(properties));

    public static final Item GOLD_UPGRADE = registerItem("gold_upgrade",
            properties -> new Item(properties));

    public static final Item DIAMOND_UPGRADE = registerItem("diamond_upgrade",
            properties -> new Item(properties));

    public static final Item VILLAGER_CATCHER = registerItem("villager_catcher",
            properties -> new Item(properties));

    public static final Item OBSIDIAN_BOAT = registerItem("obsidian_boat",
            properties -> new BoatItem(UPEntities.OBSIDIAN_BOAT, properties.stacksTo(1)));

    public static final Item OBSIDIAN_CHEST_BOAT = registerItem("obsidian_chest_boat",
            properties -> new BoatItem(UPEntities.OBSIDIAN_CHEST_BOAT, properties.stacksTo(1)));

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, name)))));
    }

    public static void register() {

    }
}
