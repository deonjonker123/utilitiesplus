package com.misterd.utilitiesplus.client.ber;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.Direction;

public class BarrelBlockEntityRenderState extends BlockEntityRenderState {
    public Direction facing = Direction.NORTH;
    public int storedCount = 0;
    public String countText = "";
    public boolean hasUpgrade = false;
    public final ItemStackRenderState storedItemState = new ItemStackRenderState();
    public final ItemStackRenderState upgradeItemState = new ItemStackRenderState();
}