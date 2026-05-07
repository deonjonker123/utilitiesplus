package com.misterd.utilitiesplus.client.ber;

import com.misterd.utilitiesplus.blockentity.custom.FeedingTroughBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class FeedingTroughBlockEntityRenderer implements BlockEntityRenderer<FeedingTroughBlockEntity, FeedingTroughBlockEntityRenderState> {
    private final ItemModelResolver itemModelResolver;

    public FeedingTroughBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        itemModelResolver = context.itemModelResolver();
    }

    @Override
    public FeedingTroughBlockEntityRenderState createRenderState() {
        return new FeedingTroughBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(FeedingTroughBlockEntity blockEntity, FeedingTroughBlockEntityRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);

        state.level = blockEntity.getLevel();

        itemModelResolver.updateForTopItem(state.itemStackRenderState, blockEntity.getTheItem(), ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 0);
    }

    @Override
    public void submit(FeedingTroughBlockEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();

        poseStack.translate(0.5f, 0.35f, 0.5f);
        poseStack.scale(0.6f, 0.5f, 0.6f);
        poseStack.mulPose(Axis.XP.rotationDegrees(90f));


        state.itemStackRenderState.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);

        poseStack.popPose();
    }
}
