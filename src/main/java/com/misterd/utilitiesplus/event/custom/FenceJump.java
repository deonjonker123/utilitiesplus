package com.misterd.utilitiesplus.event.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;

public class FenceJump {

    public static void register() {
        PlayerFenceJumpEvent.EVENT.register((player) -> {
            if (isNextToFence(player)) {
                player.setDeltaMovement(player.getDeltaMovement().add(0.0, 0.05, 0.0));
            }
            return InteractionResult.PASS;
        });
    }

    private static boolean isNextToFence(Player player) {
        BlockPos pos = player.blockPosition();
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockState state = player.level().getBlockState(pos.offset(x, 0, z));
                Block block = state.getBlock();
                if (block instanceof FenceBlock || block instanceof WallBlock || block instanceof FenceGateBlock
                        || state.is(BlockTags.FENCES) || state.is(BlockTags.WALLS) || state.is(BlockTags.FENCE_GATES)) {
                    return true;
                }
            }
        }
        return false;
    }
}