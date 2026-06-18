package com.misterd.utilitiesplus.datagen;

import com.misterd.utilitiesplus.block.UPBlocks;
import com.misterd.utilitiesplus.util.UPBlockStateProperties;
import com.misterd.utilitiesplus.util.VerticalSlabType;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.concurrent.CompletableFuture;

public class UPLootTableProvider extends FabricBlockLootSubProvider {
    public UPLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        add(UPBlocks.ACACIA_BARREL, LootTable.lootTable());
        add(UPBlocks.BIRCH_BARREL, LootTable.lootTable());
        add(UPBlocks.CHERRY_BARREL, LootTable.lootTable());
        add(UPBlocks.CRIMSON_BARREL, LootTable.lootTable());
        add(UPBlocks.DARK_OAK_BARREL, LootTable.lootTable());
        add(UPBlocks.JUNGLE_BARREL, LootTable.lootTable());
        add(UPBlocks.MANGROVE_BARREL, LootTable.lootTable());
        add(UPBlocks.OAK_BARREL, LootTable.lootTable());
        add(UPBlocks.PALE_OAK_BARREL, LootTable.lootTable());
        add(UPBlocks.SPRUCE_BARREL, LootTable.lootTable());
        add(UPBlocks.WARPED_BARREL, LootTable.lootTable());

        dropSelf(UPBlocks.SAWBENCH);
        dropSelf(UPBlocks.FEEDING_TROUGH);
        dropSelf(UPBlocks.NEST_BOX);
        dropSelf(UPBlocks.REDSTONE_CLOCK);
        dropSelf(UPBlocks.HARVESTER);
        dropSelf(UPBlocks.KILN);

        add(UPBlocks.ACACIA_VERTICAL_SLAB, createVerticalSlabItemTable(UPBlocks.ACACIA_VERTICAL_SLAB));
        add(UPBlocks.BAMBOO_VERTICAL_SLAB, createVerticalSlabItemTable(UPBlocks.BAMBOO_VERTICAL_SLAB));
        add(UPBlocks.BIRCH_VERTICAL_SLAB, createVerticalSlabItemTable(UPBlocks.BIRCH_VERTICAL_SLAB));
        add(UPBlocks.CHERRY_VERTICAL_SLAB, createVerticalSlabItemTable(UPBlocks.CHERRY_VERTICAL_SLAB));
        add(UPBlocks.CRIMSON_VERTICAL_SLAB, createVerticalSlabItemTable(UPBlocks.CRIMSON_VERTICAL_SLAB));
        add(UPBlocks.DARK_OAK_VERTICAL_SLAB, createVerticalSlabItemTable(UPBlocks.DARK_OAK_VERTICAL_SLAB));
        add(UPBlocks.JUNGLE_VERTICAL_SLAB, createVerticalSlabItemTable(UPBlocks.JUNGLE_VERTICAL_SLAB));
        add(UPBlocks.MANGROVE_VERTICAL_SLAB, createVerticalSlabItemTable(UPBlocks.MANGROVE_VERTICAL_SLAB));
        add(UPBlocks.OAK_VERTICAL_SLAB, createVerticalSlabItemTable(UPBlocks.OAK_VERTICAL_SLAB));
        add(UPBlocks.PALE_OAK_VERTICAL_SLAB, createVerticalSlabItemTable(UPBlocks.PALE_OAK_VERTICAL_SLAB));
        add(UPBlocks.SPRUCE_VERTICAL_SLAB, createVerticalSlabItemTable(UPBlocks.SPRUCE_VERTICAL_SLAB));
        add(UPBlocks.WARPED_VERTICAL_SLAB, createVerticalSlabItemTable(UPBlocks.WARPED_VERTICAL_SLAB));

        dropSelf(UPBlocks.ACACIA_BEAM);
        dropSelf(UPBlocks.BAMBOO_BEAM);
        dropSelf(UPBlocks.BIRCH_BEAM);
        dropSelf(UPBlocks.CHERRY_BEAM);
        dropSelf(UPBlocks.CRIMSON_BEAM);
        dropSelf(UPBlocks.DARK_OAK_BEAM);
        dropSelf(UPBlocks.JUNGLE_BEAM);
        dropSelf(UPBlocks.MANGROVE_BEAM);
        dropSelf(UPBlocks.OAK_BEAM);
        dropSelf(UPBlocks.PALE_OAK_BEAM);
        dropSelf(UPBlocks.SPRUCE_BEAM);
        dropSelf(UPBlocks.WARPED_BEAM);
        dropSelf(UPBlocks.FILTERED_HOPPER);
        dropSelf(UPBlocks.FAST_HOPPER);
        dropSelf(UPBlocks.FAN);
    }

    private LootTable.Builder createVerticalSlabItemTable(Block slab) {
        return LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .add(this.applyExplosionDecay(slab, LootItem.lootTableItem(slab)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(slab)
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(UPBlockStateProperties.VERTICAL_SLAB_TYPE, VerticalSlabType.DOUBLE)))))));
    }
}