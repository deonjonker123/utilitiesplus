package com.misterd.utilitiesplus.gui;

import com.misterd.utilitiesplus.UtilitiesPlus;
import com.misterd.utilitiesplus.gui.custom.KilnMenu;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.MenuType;

public class UPMenuTypes {

    private static final StreamCodec<RegistryFriendlyByteBuf, BlockPos> BLOCK_POS_CODEC =
            BlockPos.STREAM_CODEC.cast();

    public static final MenuType<KilnMenu> KILN_MENU =
            Registry.register(BuiltInRegistries.MENU,
                    Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "kiln_menu"),
                    new ExtendedMenuType<>((id, inv, pos) -> new KilnMenu(id, inv, pos), BLOCK_POS_CODEC));

    public static void register() {}
}