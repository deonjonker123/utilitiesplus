package com.misterd.utilitiesplus.item;

import com.misterd.utilitiesplus.UtilitiesPlus;
import com.misterd.utilitiesplus.block.UPBlocks;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class UPCreativeTabs {
    public static final CreativeModeTab UTILITIES_PLUS_BUILDING = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "utilitiesplus_building_creativetab"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(UPBlocks.OAK_VERTICAL_SLAB))
                    .title(Component.translatable("creativetab.utilitiesplus.building"))
                    .displayItems((parameters, output) -> {
                        output.accept(UPBlocks.ACACIA_VERTICAL_SLAB);
                        output.accept(UPBlocks.BAMBOO_VERTICAL_SLAB);
                        output.accept(UPBlocks.BIRCH_VERTICAL_SLAB);
                        output.accept(UPBlocks.CHERRY_VERTICAL_SLAB);
                        output.accept(UPBlocks.CRIMSON_VERTICAL_SLAB);
                        output.accept(UPBlocks.DARK_OAK_VERTICAL_SLAB);
                        output.accept(UPBlocks.JUNGLE_VERTICAL_SLAB);
                        output.accept(UPBlocks.MANGROVE_VERTICAL_SLAB);
                        output.accept(UPBlocks.OAK_VERTICAL_SLAB);
                        output.accept(UPBlocks.PALE_OAK_VERTICAL_SLAB);
                        output.accept(UPBlocks.SPRUCE_VERTICAL_SLAB);
                        output.accept(UPBlocks.WARPED_VERTICAL_SLAB);
                        output.accept(UPBlocks.ACACIA_BEAM);
                        output.accept(UPBlocks.BAMBOO_BEAM);
                        output.accept(UPBlocks.BIRCH_BEAM);
                        output.accept(UPBlocks.CHERRY_BEAM);
                        output.accept(UPBlocks.CRIMSON_BEAM);
                        output.accept(UPBlocks.DARK_OAK_BEAM);
                        output.accept(UPBlocks.JUNGLE_BEAM);
                        output.accept(UPBlocks.MANGROVE_BEAM);
                        output.accept(UPBlocks.OAK_BEAM);
                        output.accept(UPBlocks.PALE_OAK_BEAM);
                        output.accept(UPBlocks.SPRUCE_BEAM);
                        output.accept(UPBlocks.WARPED_BEAM);
                    }).build());

    public static final CreativeModeTab UTILITIES_PLUS = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "utilitiesplus_creativetab"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(UPBlocks.KILN))
                    .title(Component.translatable("creativetab.utilitiesplus"))
                    .displayItems((parameters, output) -> {
                        output.accept(UPBlocks.HARVESTER);
                        output.accept(UPBlocks.KILN);
                        output.accept(UPBlocks.SAWBENCH);
                        output.accept(UPBlocks.FILTERED_HOPPER);
                        output.accept(UPBlocks.FAST_HOPPER);
                        output.accept(UPBlocks.FAN);
                        output.accept(UPBlocks.ACACIA_BARREL);
                        output.accept(UPBlocks.BIRCH_BARREL);
                        output.accept(UPBlocks.CHERRY_BARREL);
                        output.accept(UPBlocks.CRIMSON_BARREL);
                        output.accept(UPBlocks.DARK_OAK_BARREL);
                        output.accept(UPBlocks.JUNGLE_BARREL);
                        output.accept(UPBlocks.MANGROVE_BARREL);
                        output.accept(UPBlocks.OAK_BARREL);
                        output.accept(UPBlocks.PALE_OAK_BARREL);
                        output.accept(UPBlocks.SPRUCE_BARREL);
                        output.accept(UPBlocks.WARPED_BARREL);
                        output.accept(UPItems.COPPER_UPGRADE);
                        output.accept(UPItems.IRON_UPGRADE);
                        output.accept(UPItems.GOLD_UPGRADE);
                        output.accept(UPItems.DIAMOND_UPGRADE);
                        output.accept(UPItems.VILLAGER_CATCHER);
                        output.accept(UPItems.OBSIDIAN_BOAT);
                        output.accept(UPItems.OBSIDIAN_CHEST_BOAT);
                        output.accept(UPBlocks.CHARCOAL_BLOCK);
                        output.accept(UPItems.CHARCOAL_BIT);
                        output.accept(UPItems.COAL_BIT);
                    }).build());

    public static void register() {}
}