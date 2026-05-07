package com.misterd.utilitiesplus.block;

import com.misterd.utilitiesplus.UtilitiesPlus;
import com.misterd.utilitiesplus.block.custom.*;
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
            p -> new BarrelBlock(p.strength(2F, 6F).sound(SoundType.NETHER_WOOD).noOcclusion()));

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
            p -> new BarrelBlock(p.strength(2F, 6F).sound(SoundType.NETHER_WOOD).noOcclusion()));

    public static final Block HARVESTER = registerBlock("harvester",
            p -> new HarvesterBlock(p.strength(3F, 6F).sound(SoundType.STONE).requiresCorrectToolForDrops().noOcclusion()));

    public static final Block KILN = registerBlock("kiln",
            p -> new KilnBlock(p.strength(3F, 6F).sound(SoundType.STONE).requiresCorrectToolForDrops().noOcclusion()));

    public static final Block SAWBENCH = registerBlock("sawbench",
            p -> new SawbenchBlock(p.strength(2F, 6F).sound(SoundType.STONE).noOcclusion()));

    public static final Block FILTERED_HOPPER = registerBlock("filtered_hopper",
            p -> new FilteredHopperBlock(p.strength(3F, 6F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final Block FAST_HOPPER = registerBlock("fast_hopper",
            p -> new FastHopperBlock(p.strength(3F, 6F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final Block REDSTONE_CLOCK = registerBlock("redstone_clock",
            p -> new Block(p.strength(3F, 6F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final Block FAN = registerBlock("fan",
            p -> new FanBlock(p.strength(3F, 6F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final Block FEEDING_TROUGH = registerBlock("feeding_trough",
            p -> new FeedingTroughBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block NEST_BOX = registerBlock("nest_box",
            p -> new NestBoxBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block CHARCOAL_BLOCK = registerBlock("charcoal_block",
            p -> new Block(p.strength(3F, 6F).sound(SoundType.STONE).requiresCorrectToolForDrops()));

    public static final Block ACACIA_VERTICAL_SLAB = registerBlock("acacia_vertical_slab",
            p -> new VerticalSlabBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block BAMBOO_VERTICAL_SLAB = registerBlock("bamboo_vertical_slab",
            p -> new VerticalSlabBlock(p.strength(2F, 6F).sound(SoundType.BAMBOO_WOOD).noOcclusion()));

    public static final Block BIRCH_VERTICAL_SLAB = registerBlock("birch_vertical_slab",
            p -> new VerticalSlabBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block CHERRY_VERTICAL_SLAB = registerBlock("cherry_vertical_slab",
            p -> new VerticalSlabBlock(p.strength(2F, 6F).sound(SoundType.CHERRY_WOOD).noOcclusion()));

    public static final Block CRIMSON_VERTICAL_SLAB = registerBlock("crimson_vertical_slab",
            p -> new VerticalSlabBlock(p.strength(2F, 6F).sound(SoundType.NETHER_WOOD).noOcclusion()));

    public static final Block DARK_OAK_VERTICAL_SLAB = registerBlock("dark_oak_vertical_slab",
            p -> new VerticalSlabBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block JUNGLE_VERTICAL_SLAB = registerBlock("jungle_vertical_slab",
            p -> new VerticalSlabBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block MANGROVE_VERTICAL_SLAB = registerBlock("mangrove_vertical_slab",
            p -> new VerticalSlabBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block OAK_VERTICAL_SLAB = registerBlock("oak_vertical_slab",
            p -> new VerticalSlabBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block PALE_OAK_VERTICAL_SLAB = registerBlock("pale_oak_vertical_slab",
            p -> new VerticalSlabBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block SPRUCE_VERTICAL_SLAB = registerBlock("spruce_vertical_slab",
            p -> new VerticalSlabBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block WARPED_VERTICAL_SLAB = registerBlock("warped_vertical_slab",
            p -> new VerticalSlabBlock(p.strength(2F, 6F).sound(SoundType.NETHER_WOOD).noOcclusion()));

    public static final Block ACACIA_BEAM = registerBlock("acacia_beam",
            p -> new WoodenBeamBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block BAMBOO_BEAM = registerBlock("bamboo_beam",
            p -> new WoodenBeamBlock(p.strength(2F, 6F).sound(SoundType.BAMBOO_WOOD).noOcclusion()));

    public static final Block BIRCH_BEAM = registerBlock("birch_beam",
            p -> new WoodenBeamBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block CHERRY_BEAM = registerBlock("cherry_beam",
            p -> new WoodenBeamBlock(p.strength(2F, 6F).sound(SoundType.CHERRY_WOOD).noOcclusion()));

    public static final Block CRIMSON_BEAM = registerBlock("crimson_beam",
            p -> new WoodenBeamBlock(p.strength(2F, 6F).sound(SoundType.NETHER_WOOD).noOcclusion()));

    public static final Block DARK_OAK_BEAM = registerBlock("dark_oak_beam",
            p -> new WoodenBeamBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block JUNGLE_BEAM = registerBlock("jungle_beam",
            p -> new WoodenBeamBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block MANGROVE_BEAM = registerBlock("mangrove_beam",
            p -> new WoodenBeamBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block OAK_BEAM = registerBlock("oak_beam",
            p -> new WoodenBeamBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block PALE_OAK_BEAM = registerBlock("pale_oak_beam",
            p -> new WoodenBeamBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block SPRUCE_BEAM = registerBlock("spruce_beam",
            p -> new WoodenBeamBlock(p.strength(2F, 6F).sound(SoundType.WOOD).noOcclusion()));

    public static final Block WARPED_BEAM = registerBlock("warped_beam",
            p -> new WoodenBeamBlock(p.strength(2F, 6F).sound(SoundType.NETHER_WOOD).noOcclusion()));

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