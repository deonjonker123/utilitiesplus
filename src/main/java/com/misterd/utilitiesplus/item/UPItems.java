package com.misterd.utilitiesplus.item;

import com.misterd.utilitiesplus.UtilitiesPlus;
import com.misterd.utilitiesplus.entity.UPEntities;
import com.misterd.utilitiesplus.item.custom.TrowelItem;
import com.misterd.utilitiesplus.item.custom.VillagerCatcherItem;
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
            Item::new);

    public static final Item IRON_UPGRADE = registerItem("iron_upgrade",
            Item::new);

    public static final Item GOLD_UPGRADE = registerItem("gold_upgrade",
            Item::new);

    public static final Item DIAMOND_UPGRADE = registerItem("diamond_upgrade",
            Item::new);

    public static final Item VILLAGER_CATCHER = registerItem("villager_catcher",
            VillagerCatcherItem::new);

    public static final Item CHARCOAL_BIT = registerItem("charcoal_bit",
            Item::new);

    public static final Item COAL_BIT = registerItem("coal_bit",
            Item::new);

    public static final Item TROWEL = registerItem("trowel",
            TrowelItem::new);

    public static final Item OBSIDIAN_BOAT = registerItem("obsidian_boat",
            properties -> new BoatItem(UPEntities.OBSIDIAN_BOAT, properties.stacksTo(1).fireResistant()));

    public static final Item OBSIDIAN_CHEST_BOAT = registerItem("obsidian_chest_boat",
            properties -> new BoatItem(UPEntities.OBSIDIAN_CHEST_BOAT, properties.stacksTo(1).fireResistant()));

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, name)))));
    }

    public static void register() {

    }
}
