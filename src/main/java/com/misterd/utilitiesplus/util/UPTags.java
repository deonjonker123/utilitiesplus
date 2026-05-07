package com.misterd.utilitiesplus.util;

import com.misterd.utilitiesplus.UtilitiesPlus;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class UPTags {
    public static class Items {
        public static final TagKey<Item> BARREL_BLOCK_ITEMS = createTag("barrel_block_items");
        public static final TagKey<Item> KILN_SMELTABLES = createTag("kiln_smeltables");
        public static final TagKey<Item> ANIMAL_FEED = createTag("animal_feed");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, name));
        }
    }
}