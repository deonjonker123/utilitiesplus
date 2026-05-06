package com.misterd.utilitiesplus.util;

import net.minecraft.world.level.block.state.properties.EnumProperty;

public class UPBlockStateProperties {
    public static final EnumProperty<VerticalSlabType> VERTICAL_SLAB_TYPE =
            EnumProperty.create("type", VerticalSlabType.class);
}