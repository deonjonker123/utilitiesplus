package com.misterd.utilitiesplus.mixin;

import com.misterd.utilitiesplus.block.UPBlocks;
import com.misterd.utilitiesplus.block.custom.LanternBracketBlock;
import com.misterd.utilitiesplus.blockentity.custom.LanternBracketBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public class LanternItemMixin {

    @Inject(method = "place", at = @At("HEAD"), cancellable = true)
    private void interceptLanternPlacement(BlockPlaceContext context, CallbackInfoReturnable<InteractionResult> cir) {
        if (!(((BlockItem) (Object) this).getBlock() instanceof LanternBlock)) return;

        Direction face = context.getClickedFace();
        if (!face.getAxis().isHorizontal()) return;

        Level level = context.getLevel();
        BlockPos supportPos = context.getClickedPos().relative(face.getOpposite());
        BlockState support = level.getBlockState(supportPos);

        LanternBracketBlock.BracketAttachment attachment;
        if (support.is(BlockTags.FENCES)) {
            attachment = LanternBracketBlock.BracketAttachment.FENCE_POST;
        } else if (support.is(BlockTags.WALLS) || isBeam(support)) {
            attachment = LanternBracketBlock.BracketAttachment.WALL;
        } else if (support.isFaceSturdy(level, supportPos, face)) {
            attachment = LanternBracketBlock.BracketAttachment.BLOCK;
        } else {
            return;
        }

        BlockPos placePos = context.getClickedPos();
        if (!level.getBlockState(placePos).canBeReplaced()) return;

        BlockState bracketState = UPBlocks.LANTERN_BRACKET.defaultBlockState()
                .setValue(LanternBracketBlock.FACING, face)
                .setValue(LanternBracketBlock.ATTACHMENT, attachment);

        if (!level.setBlock(placePos, bracketState, Block.UPDATE_ALL)) return;

        if (level.getBlockEntity(placePos) instanceof LanternBracketBlockEntity be) {
            be.setLanternState(((BlockItem) (Object) this).getBlock().defaultBlockState());
        }

        if (!context.getPlayer().isCreative()) {
            context.getItemInHand().shrink(1);
        }

        cir.setReturnValue(InteractionResult.SUCCESS_SERVER);
    }

    private boolean isBeam(BlockState state) {
        Block block = state.getBlock();
        return block == UPBlocks.OAK_BEAM || block == UPBlocks.SPRUCE_BEAM ||
                block == UPBlocks.BIRCH_BEAM || block == UPBlocks.JUNGLE_BEAM ||
                block == UPBlocks.ACACIA_BEAM || block == UPBlocks.DARK_OAK_BEAM ||
                block == UPBlocks.MANGROVE_BEAM || block == UPBlocks.CHERRY_BEAM ||
                block == UPBlocks.PALE_OAK_BEAM || block == UPBlocks.BAMBOO_BEAM ||
                block == UPBlocks.CRIMSON_BEAM || block == UPBlocks.WARPED_BEAM;
    }
}