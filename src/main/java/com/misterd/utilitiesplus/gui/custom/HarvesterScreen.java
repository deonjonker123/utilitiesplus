package com.misterd.utilitiesplus.gui.custom;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class HarvesterScreen extends AbstractContainerScreen<HarvesterMenu> {

    private static final Identifier GUI_TEXTURE =
            Identifier.fromNamespaceAndPath("utilitiesplus", "textures/gui/harvester_gui.png");

    public HarvesterScreen(HarvesterMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE,
                leftPos, topPos, 0.0F, 0.0F,
                imageWidth, imageHeight, 256, 256);

        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;

        renderFlame(graphics);

        super.extractContents(graphics, mouseX, mouseY, partialTick);
    }

    private void renderFlame(GuiGraphicsExtractor graphics) {
        if (!menu.isLit()) return;
        int litTime = menu.getLitTime();
        int litDuration = menu.getLitDuration();
        int flameHeight = 14;
        int scaled = litDuration > 0 ? (litTime * flameHeight) / litDuration : 0;
        int offset = flameHeight - scaled;
        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE,
                leftPos + 81, topPos + 56 + offset,
                176.0F, 0.0F + offset,
                14, scaled, 256, 256);
    }
}