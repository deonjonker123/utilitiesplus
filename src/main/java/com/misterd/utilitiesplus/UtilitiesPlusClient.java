package com.misterd.utilitiesplus;

import com.misterd.utilitiesplus.blockentity.UPBlockEntities;
import com.misterd.utilitiesplus.client.ber.BarrelBlockEntityRenderer;
import com.misterd.utilitiesplus.client.ber.FeedingTroughBlockEntityRenderer;
import com.misterd.utilitiesplus.client.ber.LanternBracketBlockEntityRenderer;
import com.misterd.utilitiesplus.entity.UPEntities;
import com.misterd.utilitiesplus.entity.client.UPModelLayerLocations;
import com.misterd.utilitiesplus.gui.UPMenuTypes;
import com.misterd.utilitiesplus.gui.custom.*;
import com.misterd.utilitiesplus.network.UPNetwork;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class UtilitiesPlusClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        UPNetwork.registerClient();

        MenuScreens.register(UPMenuTypes.KILN_MENU, KilnScreen::new);
        MenuScreens.register(UPMenuTypes.SAWBENCH_MENU, SawbenchScreen::new);
        MenuScreens.register(UPMenuTypes.FILTERED_HOPPER_MENU, FilteredHopperScreen::new);
        MenuScreens.register(UPMenuTypes.FAST_HOPPER_MENU, FastHopperScreen::new);
        MenuScreens.register(UPMenuTypes.HARVESTER_MENU, HarvesterScreen::new);
        MenuScreens.register(UPMenuTypes.NEST_BOX_MENU, NestBoxScreen::new);
        MenuScreens.register(UPMenuTypes.FEEDING_TROUGH_MENU, FeedingTroughScreen::new);
        MenuScreens.register(UPMenuTypes.BARREL_MENU, BarrelScreen::new);

        ModelLayerRegistry.registerModelLayer(UPModelLayerLocations.OBSIDIAN_BOAT, BoatModel::createBoatModel);
        ModelLayerRegistry.registerModelLayer(UPModelLayerLocations.OBSIDIAN_CHEST_BOAT, BoatModel::createChestBoatModel);

        EntityRenderers.register(UPEntities.OBSIDIAN_BOAT, context -> new BoatRenderer(context, UPModelLayerLocations.OBSIDIAN_BOAT));
        EntityRenderers.register(UPEntities.OBSIDIAN_CHEST_BOAT, context -> new BoatRenderer(context, UPModelLayerLocations.OBSIDIAN_CHEST_BOAT));

        BlockEntityRenderers.register(UPBlockEntities.FEEDING_TROUGH_BE, FeedingTroughBlockEntityRenderer::new);
        BlockEntityRenderers.register(UPBlockEntities.BARREL_BE, BarrelBlockEntityRenderer::new);
        BlockEntityRenderers.register(UPBlockEntities.LANTERN_BRACKET_BE, LanternBracketBlockEntityRenderer::new);
    }
}