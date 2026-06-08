package com.misterd.utilitiesplus.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.phys.BlockHitResult;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class TrowelItem extends Item {

    public TrowelItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (player == null) return InteractionResult.PASS;

        var level = context.getLevel();
        var pos = level.getBlockState(context.getClickedPos()).canBeReplaced()
                ? context.getClickedPos()
                : context.getClickedPos().relative(context.getClickedFace());

        Map<ItemStack, Integer> placeables = new HashMap<>();
        int total = 0;
        for (int i = 0; i < 9; i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (!stack.isEmpty() && stack.getItem() instanceof BlockItem) {
                total += stack.getCount();
                placeables.put(stack, stack.getCount());
            }
        }

        if (placeables.isEmpty()) return InteractionResult.PASS;

        if (!level.isClientSide()) {
            ItemStack chosen = ItemStack.EMPTY;
            int randomWeight = level.getRandom().nextInt(total);
            for (Map.Entry<ItemStack, Integer> entry : placeables.entrySet()) {
                randomWeight -= entry.getValue();
                if (randomWeight <= 0) {
                    chosen = entry.getKey();
                    break;
                }
            }

            if (!chosen.isEmpty() && chosen.getItem() instanceof BlockItem blockItem) {
                InteractionResult result = blockItem.place(new BlockPlaceContext(level, player, context.getHand(), chosen, new BlockHitResult(context.getClickLocation(), player.getDirection(), pos, false)));
                if (result.consumesAction()) {
                    var blockState = blockItem.getBlock().defaultBlockState();
                    SoundType soundtype = blockState.getSoundType();
                    level.playSound(null, pos, blockState.getSoundType().getPlaceSound(), SoundSource.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                }
            }
        }

        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> adder, TooltipFlag flag) {
        super.appendHoverText(stack, context, display, adder, flag);

            adder.accept(Component.translatable("tooltip.utilitiesplus.trowel").withStyle(ChatFormatting.GOLD));
    }
}