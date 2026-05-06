package com.misterd.utilitiesplus.gui.custom;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class FilteredHopperScreen extends AbstractContainerScreen<FilteredHopperMenu> {

    private static final Identifier GUI_TEXTURE =
            Identifier.fromNamespaceAndPath("utilitiesplus", "textures/gui/hopper_gui.png");

    private static final int GUI_HEIGHT = 166;

    public FilteredHopperScreen(FilteredHopperMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, 176, GUI_HEIGHT);
        this.inventoryLabelY = GUI_HEIGHT - 96;
    }

    @Override
    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE,
                leftPos, topPos, 0.0F, 0.0F,
                imageWidth, imageHeight, 256, 256);

        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;

        super.extractContents(graphics, mouseX, mouseY, partialTick);
    }
}