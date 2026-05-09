package com.misterd.utilitiesplus.client.ber;

import com.misterd.utilitiesplus.block.custom.LanternBracketBlock;
import com.misterd.utilitiesplus.blockentity.custom.LanternBracketBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class LanternBracketBlockEntityRenderer implements BlockEntityRenderer<LanternBracketBlockEntity, LanternBracketBlockEntityRenderState> {

    private static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();

    private final BlockModelResolver blockModelResolver;

    public LanternBracketBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        blockModelResolver = context.blockModelResolver();
    }

    @Override
    public LanternBracketBlockEntityRenderState createRenderState() {
        return new LanternBracketBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(LanternBracketBlockEntity blockEntity, LanternBracketBlockEntityRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.isEmpty = blockEntity.isEmpty();
        if (!state.isEmpty) {
            state.facing = blockEntity.getBlockState().getValue(LanternBracketBlock.FACING);
            blockModelResolver.update(state.lanternModelState, blockEntity.getLanternState(), BLOCK_DISPLAY_CONTEXT);
        }
    }

    @Override
    public void submit(LanternBracketBlockEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        if (state.isEmpty) return;

        poseStack.pushPose();

        poseStack.translate(0.5, 0.5, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0f));
        switch (state.facing) {
            case NORTH -> poseStack.mulPose(Axis.YP.rotationDegrees(0.0f));
            case EAST -> poseStack.mulPose(Axis.YP.rotationDegrees(-90.0f));
            case SOUTH -> poseStack.mulPose(Axis.YP.rotationDegrees(180.0f));
            case WEST -> poseStack.mulPose(Axis.YP.rotationDegrees(90.0f));
            default -> {}
        }
        poseStack.translate(-0.5, 0, -0.5);
        poseStack.translate(0, -0.375, 0);

        state.lanternModelState.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);

        poseStack.popPose();
    }
}