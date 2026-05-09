package com.misterd.utilitiesplus.mixin;

import com.misterd.utilitiesplus.block.UPBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LanternBlock.class)
public class LanternBlockMixin {

    @Inject(method = "canSurvive", at = @At("HEAD"), cancellable = true)
    private void allowSurviveOnBracket(BlockState state, LevelReader level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        boolean hanging = state.getValue(LanternBlock.HANGING);
        if (!hanging) return;

        BlockPos above = pos.relative(Direction.UP);
        BlockState above_state = level.getBlockState(above);

        if (above_state.is(UPBlocks.LANTERN_BRACKET)) {
            cir.setReturnValue(true);
        }
    }
}