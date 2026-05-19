package com.misterd.utilitiesplus.gui.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.util.Util;

@Environment(EnvType.CLIENT)
public class NameTagButton extends Button {

    protected static final WidgetSprites SPRITES = new WidgetSprites(
            Identifier.fromNamespaceAndPath("utilitiesplus", "name_tag_button"),
            Identifier.fromNamespaceAndPath("utilitiesplus", "name_tag_button_disabled"),
            Identifier.fromNamespaceAndPath("utilitiesplus", "name_tag_button_highlighted")
    );

    public NameTagButton(int x, int y, int width, int height, Component label, OnPress onPress) {
        super(x, y, width, height, label, onPress, DEFAULT_NARRATION);
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        Minecraft minecraft = Minecraft.getInstance();

        graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                SPRITES.get(this.active, this.isHovered()),
                this.getX(), this.getY(),
                this.getWidth(), this.getHeight(),
                ARGB.white(alpha)
        );

        int color;
        int yOffset;
        if (!this.active) {
            color = 0xFFAAAAAA | Mth.ceil(this.alpha * 255.0F) << 24;
            yOffset = 0;
        } else if (this.isHovered()) {
            color = 0xFF000000 | Mth.ceil(this.alpha * 255.0F) << 24;
            yOffset = 0;
        } else {
            color = 0xFF3f3f3f | Mth.ceil(this.alpha * 255.0F) << 24;
            yOffset = 1;
        }

        renderButtonText(graphics, minecraft.font, this.getMessage(),
                this.getX() + 2, this.getY(),
                this.getX() + this.getWidth() - 2, this.getY() + this.getHeight(),
                yOffset, color);
    }

    private static void renderButtonText(GuiGraphicsExtractor graphics, Font font, Component text,
                                         int minX, int minY, int maxX, int maxY, int yOffset, int color) {
        int textWidth = font.width(text);
        int y = (minY + maxY - 9) / 2 + 1;
        int availableWidth = maxX - minX;

        if (textWidth > availableWidth) {
            int overflow = textWidth - availableWidth;
            double time = (double) Util.getMillis() / 1000.0;
            double period = Math.max((double) overflow * 0.5, 3.0);
            double scroll = Math.sin((Math.PI / 2) * Math.cos((Math.PI * 2) * time / period)) / 2.0 + 0.5;
            double offset = Mth.lerp(scroll, 0.0, (double) overflow);
            graphics.enableScissor(minX, minY, maxX, maxY);
            graphics.text(font, text, minX - (int) offset, y, color, false);
            graphics.disableScissor();
        } else {
            int centerX = (minX + maxX) / 2;
            int clampedX = Mth.clamp(centerX, minX + textWidth / 2, maxX - textWidth / 2);
            FormattedCharSequence sequence = text.getVisualOrderText();
            graphics.text(font, sequence, clampedX - font.width(sequence) / 2, y - yOffset, color, false);
        }
    }
}