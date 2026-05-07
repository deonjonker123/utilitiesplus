package com.misterd.utilitiesplus.client.ber;

import com.misterd.utilitiesplus.block.custom.BarrelBlock;
import com.misterd.utilitiesplus.blockentity.custom.BarrelBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.text.NumberFormat;
import java.util.Locale;

public class BarrelBlockEntityRenderer implements BlockEntityRenderer<BarrelBlockEntity, BarrelBlockEntityRenderState> {

    private final ItemModelResolver itemModelResolver;
    private final Font font;

    public BarrelBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
        this.font = context.font();
    }

    @Override
    public BarrelBlockEntityRenderState createRenderState() {
        return new BarrelBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(BarrelBlockEntity be, BarrelBlockEntityRenderState state, float partialTicks, Vec3 cameraPos, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(be, state, partialTicks, cameraPos, breakProgress);

        state.facing = be.getBlockState().getValue(BarrelBlock.FACING);
        state.storedCount = be.getStoredCount();

        if (be.getStoredCount() > 0) {
            itemModelResolver.updateForTopItem(state.storedItemState, be.getStoredType(), ItemDisplayContext.GUI, be.getLevel(), null, 0);
            state.countText = NumberFormat.getNumberInstance(Locale.US).format(be.getStoredCount());
        } else {
            state.storedItemState.clear();
            state.countText = "";
        }

        ItemStack upgrade = be.getItem(BarrelBlockEntity.SLOT_UPGRADE);
        if (!upgrade.isEmpty()) {
            itemModelResolver.updateForTopItem(state.upgradeItemState, upgrade, ItemDisplayContext.FIXED, be.getLevel(), null, 0);
            state.hasUpgrade = true;
        } else {
            state.upgradeItemState.clear();
            state.hasUpgrade = false;
        }
    }

    @Override
    public void submit(BarrelBlockEntityRenderState state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState camera) {
        poseStack.pushPose();

        applyFacingRotation(poseStack, state.facing);
        poseStack.translate(0.0, 0.0, 0.5);

        if (state.storedCount > 0) {
            poseStack.pushPose();
            poseStack.translate(0.0, 0.05, -0.01);
            poseStack.scale(0.4f, 0.4f, 0.005f);
            state.storedItemState.submit(poseStack, collector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
            poseStack.popPose();
        }

        if (!state.countText.isEmpty()) {
            poseStack.pushPose();
            poseStack.translate(0.0, -0.22, -0.01);
            float textScale = 0.6f / 60.0f;
            poseStack.scale(textScale, -textScale, textScale);
            float width = font.width(state.countText);
            collector.submitText(
                    poseStack,
                    -width / 2.0f,
                    0f,
                    Component.literal(state.countText).getVisualOrderText(),
                    false,
                    Font.DisplayMode.NORMAL,
                    state.lightCoords,
                    0xFFFFFFFF,
                    0,
                    0
            );
            poseStack.popPose();
        }

        if (state.hasUpgrade) {
            poseStack.pushPose();
            poseStack.translate(0.445, -0.445, 0.01);
            poseStack.scale(0.12f, 0.12f, 0.001f);
            state.upgradeItemState.submit(poseStack, collector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
            poseStack.popPose();
        }

        poseStack.popPose();
    }

    private void applyFacingRotation(PoseStack poseStack, Direction facing) {
        poseStack.translate(0.5, 0.5, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0f));
        switch (facing) {
            case NORTH -> poseStack.mulPose(Axis.YP.rotationDegrees(0.0f));
            case EAST -> poseStack.mulPose(Axis.YP.rotationDegrees(-90.0f));
            case SOUTH -> poseStack.mulPose(Axis.YP.rotationDegrees(180.0f));
            case WEST -> poseStack.mulPose(Axis.YP.rotationDegrees(90.0f));
            default -> {
            }
        }
    }
}