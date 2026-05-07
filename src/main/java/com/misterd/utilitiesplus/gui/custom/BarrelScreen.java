package com.misterd.utilitiesplus.gui.custom;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class BarrelScreen extends AbstractContainerScreen<BarrelMenu> {

    private static final Identifier GUI_TEXTURE =
            Identifier.fromNamespaceAndPath("utilitiesplus", "textures/gui/barrel_gui.png");

    // Virtual slot position (relative to GUI origin)
    private static final int VIRTUAL_SLOT_X = 79;
    private static final int VIRTUAL_SLOT_Y = 39;

    // Count text position (relative to GUI origin)
    private static final int COUNT_TEXT_X = 63;
    private static final int COUNT_TEXT_Y = 65;
    private static final int COUNT_TEXT_WIDTH = 50;

    public BarrelScreen(BarrelMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, 176, 176);
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        super.extractBackground(graphics, mouseX, mouseY, partialTick);
        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE,
                this.leftPos, this.topPos, 0.0F, 0.0F,
                this.imageWidth, this.imageHeight, 256, 256);
    }

    @Override
    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        super.extractContents(graphics, mouseX, mouseY, partialTick);
        renderVirtualSlot(graphics);
        renderCountText(graphics);
    }

    private void renderVirtualSlot(GuiGraphicsExtractor graphics) {
        ItemStack stored = menu.blockEntity.getStoredType();
        if (stored.isEmpty()) return;

        graphics.item(stored, leftPos + VIRTUAL_SLOT_X + 1, topPos + VIRTUAL_SLOT_Y + 1);
    }

    private void renderCountText(GuiGraphicsExtractor graphics) {
        int count = menu.blockEntity.getStoredCount();
        if (count == 0) return;

        String text = String.valueOf(count);
        int textWidth = this.font.width(text);
        int x = leftPos + COUNT_TEXT_X + (COUNT_TEXT_WIDTH - textWidth) / 2;
        int y = topPos + COUNT_TEXT_Y;

        graphics.text(this.font, text, x, y, 0xFF404040, false);
    }
}