package com.misterd.utilitiesplus.gui.custom;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class NestBoxScreen extends AbstractContainerScreen<NestBoxMenu> {

    private static final Identifier GUI_TEXTURE =
            Identifier.fromNamespaceAndPath("utilitiesplus", "textures/gui/nest_box_gui.png");

    public NestBoxScreen(NestBoxMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, 176, 133);
        this.inventoryLabelY = this.imageHeight - 92;
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        super.extractBackground(graphics, mouseX, mouseY, partialTick);
        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE,
                this.leftPos, this.topPos, 0.0F, 0.0F,
                this.imageWidth, this.imageHeight, 256, 256);
    }
}