package com.misterd.utilitiesplus.datagen;

import com.misterd.utilitiesplus.UtilitiesPlus;
import com.misterd.utilitiesplus.block.UPBlocks;
import com.misterd.utilitiesplus.item.UPItems;
import com.misterd.utilitiesplus.util.UPBlockStateProperties;
import com.misterd.utilitiesplus.util.VerticalSlabType;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.Optional;

public class UPModelProvider extends FabricModelProvider {

    public UPModelProvider(FabricPackOutput output) {
        super(output);
    }

    private static final ModelTemplate VERTICAL_SLAB_TEMPLATE = new ModelTemplate(
            Optional.of(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/vertical_slab")),
            Optional.empty(),
            TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE
    );

    private static final ModelTemplate WOODEN_BEAM_TEMPLATE = new ModelTemplate(
            Optional.of(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/wooden_beam")),
            Optional.empty(),
            TextureSlot.ALL
    );

    private void createBeam(BlockModelGenerators gen, Block beamBlock, Block fullBlock) {
        TextureMapping mapping = new TextureMapping().put(TextureSlot.ALL, TextureMapping.getBlockTexture(fullBlock));
        Identifier modelId = WOODEN_BEAM_TEMPLATE.create(beamBlock, mapping, gen.modelOutput);
        gen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(beamBlock, BlockModelGenerators.plainVariant(modelId)));
        gen.registerSimpleItemModel(beamBlock, modelId);
    }

    private static final PropertyDispatch<VariantMutator> ROTATION_HORIZONTAL_FACING =
            PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
                    .select(Direction.EAST, BlockModelGenerators.Y_ROT_90)
                    .select(Direction.SOUTH, BlockModelGenerators.Y_ROT_180)
                    .select(Direction.WEST, BlockModelGenerators.Y_ROT_270)
                    .select(Direction.NORTH, BlockModelGenerators.NOP);

    private void createVerticalSlab(BlockModelGenerators gen, Block slabBlock, Block fullBlock) {
        TextureMapping mapping = TextureMapping.cube(fullBlock);
        Identifier modelId = VERTICAL_SLAB_TEMPLATE.create(slabBlock, mapping, gen.modelOutput);
        MultiVariant north = BlockModelGenerators.plainVariant(modelId);
        MultiVariant full = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(fullBlock));
        gen.blockStateOutput.accept(MultiVariantGenerator.dispatch(slabBlock)
                .with(PropertyDispatch.initial(UPBlockStateProperties.VERTICAL_SLAB_TYPE)
                        .select(VerticalSlabType.NORTH, north)
                        .select(VerticalSlabType.SOUTH, north.with(BlockModelGenerators.Y_ROT_180))
                        .select(VerticalSlabType.EAST, north.with(BlockModelGenerators.Y_ROT_90))
                        .select(VerticalSlabType.WEST, north.with(BlockModelGenerators.Y_ROT_270))
                        .select(VerticalSlabType.DOUBLE, full)));
        gen.registerSimpleItemModel(slabBlock, modelId);
    }

    private void createCustomHopper(BlockModelGenerators gen, Block block, String modelName) {
        Identifier downModel = Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/" + modelName);
        Identifier sideModel = Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/" + modelName + "_side");
        MultiVariant downBlock = BlockModelGenerators.plainVariant(downModel);
        MultiVariant sideBlock = BlockModelGenerators.plainVariant(sideModel);
        gen.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                .with(PropertyDispatch.initial(BlockStateProperties.FACING_HOPPER)
                        .select(Direction.DOWN, downBlock)
                        .select(Direction.NORTH, sideBlock)
                        .select(Direction.EAST, sideBlock.with(BlockModelGenerators.Y_ROT_90))
                        .select(Direction.SOUTH, sideBlock.with(BlockModelGenerators.Y_ROT_180))
                        .select(Direction.WEST, sideBlock.with(BlockModelGenerators.Y_ROT_270))));
        gen.registerSimpleItemModel(block, downModel);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators gen) {
        gen.createHorizontallyRotatedBlock(UPBlocks.HARVESTER, TexturedModel.ORIENTABLE);
        gen.createFurnace(UPBlocks.KILN, TexturedModel.ORIENTABLE);

        gen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(UPBlocks.ACACIA_BARREL,
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/acacia_barrel")))
                .with(ROTATION_HORIZONTAL_FACING));
        gen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(UPBlocks.BIRCH_BARREL,
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/birch_barrel")))
                .with(ROTATION_HORIZONTAL_FACING));
        gen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(UPBlocks.CHERRY_BARREL,
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/cherry_barrel")))
                .with(ROTATION_HORIZONTAL_FACING));
        gen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(UPBlocks.CRIMSON_BARREL,
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/crimson_barrel")))
                .with(ROTATION_HORIZONTAL_FACING));
        gen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(UPBlocks.DARK_OAK_BARREL,
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/dark_oak_barrel")))
                .with(ROTATION_HORIZONTAL_FACING));
        gen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(UPBlocks.JUNGLE_BARREL,
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/jungle_barrel")))
                .with(ROTATION_HORIZONTAL_FACING));
        gen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(UPBlocks.MANGROVE_BARREL,
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/mangrove_barrel")))
                .with(ROTATION_HORIZONTAL_FACING));
        gen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(UPBlocks.OAK_BARREL,
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/oak_barrel")))
                .with(ROTATION_HORIZONTAL_FACING));
        gen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(UPBlocks.PALE_OAK_BARREL,
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/pale_oak_barrel")))
                .with(ROTATION_HORIZONTAL_FACING));
        gen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(UPBlocks.SPRUCE_BARREL,
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/spruce_barrel")))
                .with(ROTATION_HORIZONTAL_FACING));
        gen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(UPBlocks.WARPED_BARREL,
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/warped_barrel")))
                .with(ROTATION_HORIZONTAL_FACING));

        createVerticalSlab(gen, UPBlocks.ACACIA_VERTICAL_SLAB, net.minecraft.world.level.block.Blocks.ACACIA_PLANKS);
        createVerticalSlab(gen, UPBlocks.BAMBOO_VERTICAL_SLAB, net.minecraft.world.level.block.Blocks.BAMBOO_PLANKS);
        createVerticalSlab(gen, UPBlocks.BIRCH_VERTICAL_SLAB, net.minecraft.world.level.block.Blocks.BIRCH_PLANKS);
        createVerticalSlab(gen, UPBlocks.CHERRY_VERTICAL_SLAB, net.minecraft.world.level.block.Blocks.CHERRY_PLANKS);
        createVerticalSlab(gen, UPBlocks.CRIMSON_VERTICAL_SLAB, net.minecraft.world.level.block.Blocks.CRIMSON_PLANKS);
        createVerticalSlab(gen, UPBlocks.DARK_OAK_VERTICAL_SLAB, net.minecraft.world.level.block.Blocks.DARK_OAK_PLANKS);
        createVerticalSlab(gen, UPBlocks.JUNGLE_VERTICAL_SLAB, net.minecraft.world.level.block.Blocks.JUNGLE_PLANKS);
        createVerticalSlab(gen, UPBlocks.MANGROVE_VERTICAL_SLAB, net.minecraft.world.level.block.Blocks.MANGROVE_PLANKS);
        createVerticalSlab(gen, UPBlocks.OAK_VERTICAL_SLAB, net.minecraft.world.level.block.Blocks.OAK_PLANKS);
        createVerticalSlab(gen, UPBlocks.PALE_OAK_VERTICAL_SLAB, net.minecraft.world.level.block.Blocks.PALE_OAK_PLANKS);
        createVerticalSlab(gen, UPBlocks.SPRUCE_VERTICAL_SLAB, net.minecraft.world.level.block.Blocks.SPRUCE_PLANKS);
        createVerticalSlab(gen, UPBlocks.WARPED_VERTICAL_SLAB, net.minecraft.world.level.block.Blocks.WARPED_PLANKS);

        createBeam(gen, UPBlocks.ACACIA_BEAM, net.minecraft.world.level.block.Blocks.ACACIA_PLANKS);
        createBeam(gen, UPBlocks.BAMBOO_BEAM, net.minecraft.world.level.block.Blocks.BAMBOO_PLANKS);
        createBeam(gen, UPBlocks.BIRCH_BEAM, net.minecraft.world.level.block.Blocks.BIRCH_PLANKS);
        createBeam(gen, UPBlocks.CHERRY_BEAM, net.minecraft.world.level.block.Blocks.CHERRY_PLANKS);
        createBeam(gen, UPBlocks.CRIMSON_BEAM, net.minecraft.world.level.block.Blocks.CRIMSON_PLANKS);
        createBeam(gen, UPBlocks.DARK_OAK_BEAM, net.minecraft.world.level.block.Blocks.DARK_OAK_PLANKS);
        createBeam(gen, UPBlocks.JUNGLE_BEAM, net.minecraft.world.level.block.Blocks.JUNGLE_PLANKS);
        createBeam(gen, UPBlocks.MANGROVE_BEAM, net.minecraft.world.level.block.Blocks.MANGROVE_PLANKS);
        createBeam(gen, UPBlocks.OAK_BEAM, net.minecraft.world.level.block.Blocks.OAK_PLANKS);
        createBeam(gen, UPBlocks.PALE_OAK_BEAM, net.minecraft.world.level.block.Blocks.PALE_OAK_PLANKS);
        createBeam(gen, UPBlocks.SPRUCE_BEAM, net.minecraft.world.level.block.Blocks.SPRUCE_PLANKS);
        createBeam(gen, UPBlocks.WARPED_BEAM, net.minecraft.world.level.block.Blocks.WARPED_PLANKS);

        createCustomHopper(gen, UPBlocks.FILTERED_HOPPER, "filtered_hopper");
        createCustomHopper(gen, UPBlocks.FAST_HOPPER, "fast_hopper");
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(UPItems.VILLAGER_CATCHER, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(UPItems.IRON_UPGRADE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(UPItems.COPPER_UPGRADE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(UPItems.GOLD_UPGRADE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(UPItems.DIAMOND_UPGRADE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(UPItems.OBSIDIAN_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(UPItems.OBSIDIAN_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
    }
}