package com.misterd.utilitiesplus.gui;

import com.misterd.utilitiesplus.UtilitiesPlus;
import com.misterd.utilitiesplus.gui.custom.*;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public class UPMenuTypes {

    private static final StreamCodec<RegistryFriendlyByteBuf, BlockPos> BLOCK_POS_CODEC =
            BlockPos.STREAM_CODEC.cast();

    public static final MenuType<KilnMenu> KILN_MENU =
            Registry.register(BuiltInRegistries.MENU,
                    Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "kiln_menu"),
                    new ExtendedMenuType<>(KilnMenu::new, BLOCK_POS_CODEC));

    public static final MenuType<HarvesterMenu> HARVESTER_MENU =
            Registry.register(BuiltInRegistries.MENU,
                    Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "harvester_menu"),
                    new ExtendedMenuType<>(HarvesterMenu::new, BLOCK_POS_CODEC));

    public static final MenuType<NestBoxMenu> NEST_BOX_MENU =
            Registry.register(BuiltInRegistries.MENU,
                    Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "nest_box_menu"),
                    new ExtendedMenuType<>(NestBoxMenu::new, BLOCK_POS_CODEC));

    public static final MenuType<FeedingTroughMenu> FEEDING_TROUGH_MENU =
            Registry.register(BuiltInRegistries.MENU,
                    Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "feeding_trough_menu"),
                    new ExtendedMenuType<>(FeedingTroughMenu::new, BLOCK_POS_CODEC));

    public static final MenuType<SawbenchMenu> SAWBENCH_MENU =
            Registry.register(BuiltInRegistries.MENU,
                    Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "sawbench_menu"),
                    new MenuType<>(SawbenchMenu::new, FeatureFlags.DEFAULT_FLAGS));

    public static final MenuType<FilteredHopperMenu> FILTERED_HOPPER_MENU =
            Registry.register(BuiltInRegistries.MENU,
                    Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "filtered_hopper_menu"),
                    new MenuType<>(FilteredHopperMenu::new, FeatureFlags.DEFAULT_FLAGS));

    public static final MenuType<FastHopperMenu> FAST_HOPPER_MENU =
            Registry.register(BuiltInRegistries.MENU,
                    Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "fast_hopper_menu"),
                    new MenuType<>(FastHopperMenu::new, FeatureFlags.DEFAULT_FLAGS));

    public static final MenuType<BarrelMenu> BARREL_MENU =
            Registry.register(BuiltInRegistries.MENU,
                    Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "barrel_menu"),
                    new ExtendedMenuType<>(BarrelMenu::new, BLOCK_POS_CODEC));

    public static void register() {}
}