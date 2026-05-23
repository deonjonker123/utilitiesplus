package com.misterd.utilitiesplus.event.custom;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;

public interface PlayerFenceJumpEvent {
    Event<PlayerFenceJumpEvent> EVENT = EventFactory.createArrayBacked(PlayerFenceJumpEvent.class,
            (listeners) -> (player) -> {
                for (PlayerFenceJumpEvent event : listeners) {
                    InteractionResult result = event.jump(player);

                    if (result != InteractionResult.PASS) {
                        return result;
                    }
                }

                return InteractionResult.PASS;
            }
    );

    InteractionResult jump(Player player);

}
