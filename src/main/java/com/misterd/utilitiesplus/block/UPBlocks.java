package com.misterd.utilitiesplus.block;

import com.misterd.utilitiesplus.UtilitiesPlus;
import com.misterd.utilitiesplus.block.custom.BarrelBlock;
import com.misterd.utilitiesplus.block.custom.HarvesterBlock;
import com.misterd.utilitiesplus.block.custom.KilnBlock;
import com.misterd.utilitiesplus.block.custom.SawbenchBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class UPBlocks {
    public static final Block ACACIA_BARREL = registerBlock("acacia_barrel",
            p -> new BarrelBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block BIRCH_BARREL = registerBlock("birch_barrel",
            p -> new BarrelBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block CHERRY_BARREL = registerBlock("cherry_barrel",
            p -> new BarrelBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block CRIMSON_BARREL = registerBlock("crimson_barrel",
            p -> new BarrelBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block DARK_OAK_BARREL = registerBlock("dark_oak_barrel",
            p -> new BarrelBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block JUNGLE_BARREL = registerBlock("jungle_barrel",
            p -> new BarrelBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block MANGROVE_BARREL = registerBlock("mangrove_barrel",
            p -> new BarrelBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block OAK_BARREL = registerBlock("oak_barrel",
            p -> new BarrelBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block PALE_OAK_BARREL = registerBlock("pale_oak_barrel",
            p -> new BarrelBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block SPRUCE_BARREL = registerBlock("spruce_barrel",
            p -> new BarrelBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block WARPED_BARREL = registerBlock("warped_barrel",
            p -> new BarrelBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block HARVESTER = registerBlock("harvester",
            p -> new HarvesterBlock(p.strength(2F, 6F).sound(SoundType.STONE).requiresCorrectToolForDrops().noOcclusion()));

    public static final Block KILN = registerBlock("kiln",
            p -> new KilnBlock(p.strength(2F, 6F).sound(SoundType.STONE).requiresCorrectToolForDrops().noOcclusion()));

    public static final Block SAWBENCH = registerBlock("sawbench",
            p -> new SawbenchBlock(p.strength(2F, 6F).sound(SoundType.STONE).noOcclusion()));

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block block = function.apply(BlockBehaviour.Properties.of()
                .setId(ResourceKey.create(Registries.BLOCK,
                        Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, name))));
        registerBlockItem(name, block);
        return Registry.register(BuiltInRegistries.BLOCK,
                Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM,
                Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, name),
                new BlockItem(block, new Item.Properties()
                        .useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM,
                                Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, name)))));
    }
    public static void register() {}
}
