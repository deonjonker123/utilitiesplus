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
    public static final CreativeModeTab UTILITIES_PLUS = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "utilitiesplus_creativetab"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(UPBlocks.KILN))
                    .title(Component.translatable("creativetab.utilitiesplus"))
                    .displayItems((parameters, output) -> {
                        output.accept(UPBlocks.HARVESTER);
                        output.accept(UPBlocks.KILN);
                        output.accept(UPBlocks.SAWBENCH);
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
                    }).build());

    public static void register() {}
}
