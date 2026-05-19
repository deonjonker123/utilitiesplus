package com.misterd.utilitiesplus.gui.custom;

import com.misterd.utilitiesplus.network.NameTagNamePacket;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

@Environment(EnvType.CLIENT)
public class NameTagScreen extends Screen {

    private static final Identifier GUI_TEXTURE =
            Identifier.fromNamespaceAndPath("utilitiesplus", "textures/gui/name_tag_gui.png");

    private static final int IMAGE_WIDTH = 176;
    private static final int IMAGE_HEIGHT = 72;

    private final ItemStack stack;
    private EditBox nameField;
    private int leftPos;
    private int topPos;

    public NameTagScreen(ItemStack stack) {
        super(Component.translatable("screen.utilitiesplus.name_tag"));
        this.stack = stack;
    }

    @Override
    protected void init() {
        leftPos = (width - IMAGE_WIDTH) / 2;
        topPos = (height - IMAGE_HEIGHT) / 2;

        nameField = new EditBox(font, leftPos + 10, topPos + 23, 158, 14, Component.translatable("screen.utilitiesplus.name_tag.field"));
        nameField.setMaxLength(50);
        nameField.setBordered(false);
        nameField.setTextShadow(false);
        nameField.setTextColor(0xFF404040);

        Component existingName = stack.get(DataComponents.CUSTOM_NAME);
        if (existingName != null) {
            nameField.setValue(existingName.getString());
        }

        addRenderableWidget(nameField);
        setInitialFocus(nameField);

        addRenderableWidget(new NameTagButton(
                leftPos + 66, topPos + 43, 50, 20,
                Component.translatable("screen.utilitiesplus.name_tag.set"),
                btn -> applyAndClose()
        ));

        addRenderableWidget(new NameTagButton(
                leftPos + 119, topPos + 43, 50, 20,
                Component.translatable("screen.utilitiesplus.name_tag.cancel"),
                btn -> onClose()
        ));
    }

    private void applyAndClose() {
        String name = nameField.getValue().trim();
        if (!name.isBlank()) {
            ClientPlayNetworking.send(new NameTagNamePacket(name));
        }
        onClose();
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE,
                leftPos, topPos, 0.0F, 0.0F,
                IMAGE_WIDTH, IMAGE_HEIGHT, 256, 256);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}