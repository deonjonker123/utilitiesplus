package com.misterd.utilitiesplus.client.ber;

import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;

public class LanternBracketBlockEntityRenderState extends BlockEntityRenderState {
    public final BlockModelRenderState lanternModelState = new BlockModelRenderState();
    public Direction facing = Direction.NORTH;
    public boolean isEmpty = true;
}