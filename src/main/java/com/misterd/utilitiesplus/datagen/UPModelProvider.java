package com.misterd.utilitiesplus.datagen;

import com.misterd.utilitiesplus.UtilitiesPlus;
import com.misterd.utilitiesplus.block.UPBlocks;
import com.misterd.utilitiesplus.item.UPItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class UPModelProvider extends FabricModelProvider {
    public UPModelProvider(FabricPackOutput output) {
        super(output);
    }

    private static final PropertyDispatch<VariantMutator> ROTATION_HORIZONTAL_FACING =
            PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
                    .select(Direction.EAST, BlockModelGenerators.Y_ROT_90)
                    .select(Direction.SOUTH, BlockModelGenerators.Y_ROT_180)
                    .select(Direction.WEST, BlockModelGenerators.Y_ROT_270)
                    .select(Direction.NORTH, BlockModelGenerators.NOP);

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createHorizontallyRotatedBlock(UPBlocks.HARVESTER, TexturedModel.ORIENTABLE);
        blockModelGenerators.createFurnace(UPBlocks.KILN, TexturedModel.ORIENTABLE);

        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(UPBlocks.ACACIA_BARREL,
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/acacia_barrel")))
                .with(ROTATION_HORIZONTAL_FACING));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(UPBlocks.BIRCH_BARREL,
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/birch_barrel")))
                .with(ROTATION_HORIZONTAL_FACING));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(UPBlocks.CHERRY_BARREL,
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/cherry_barrel")))
                .with(ROTATION_HORIZONTAL_FACING));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(UPBlocks.CRIMSON_BARREL,
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/crimson_barrel")))
                .with(ROTATION_HORIZONTAL_FACING));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(UPBlocks.DARK_OAK_BARREL,
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/dark_oak_barrel")))
                .with(ROTATION_HORIZONTAL_FACING));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(UPBlocks.JUNGLE_BARREL,
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/jungle_barrel")))
                .with(ROTATION_HORIZONTAL_FACING));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(UPBlocks.MANGROVE_BARREL,
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/mangrove_barrel")))
                .with(ROTATION_HORIZONTAL_FACING));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(UPBlocks.OAK_BARREL,
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/oak_barrel")))
                .with(ROTATION_HORIZONTAL_FACING));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(UPBlocks.PALE_OAK_BARREL,
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/pale_oak_barrel")))
                .with(ROTATION_HORIZONTAL_FACING));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(UPBlocks.SPRUCE_BARREL,
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/spruce_barrel")))
                .with(ROTATION_HORIZONTAL_FACING));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(UPBlocks.WARPED_BARREL,
                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(UtilitiesPlus.MODID, "block/warped_barrel")))
                .with(ROTATION_HORIZONTAL_FACING));
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
