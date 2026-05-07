package com.misterd.utilitiesplus.util;

import com.misterd.utilitiesplus.block.UPBlocks;
import com.misterd.utilitiesplus.item.UPItems;
import net.fabricmc.fabric.api.registry.FuelValueEvents;

public class UPFuels {
    public static void register() {
        FuelValueEvents.BUILD.register(((builder, context) ->
                builder
                        .add(UPBlocks.CHARCOAL_BLOCK, 16000)
                        .add(UPItems.CHARCOAL_BIT, 200)
                        .add(UPItems.COAL_BIT, 200)
                )
        );
    }
}
