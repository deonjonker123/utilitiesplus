package com.misterd.utilitiesplus.item.custom;

import com.misterd.utilitiesplus.blockentity.custom.BarrelBlockEntity.Tier;
import com.misterd.utilitiesplus.component.UPDataComponents;
import com.misterd.utilitiesplus.component.custom.BarrelData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.Consumer;

public class BarrelBlockItem extends BlockItem {

    public BarrelBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> adder, TooltipFlag flag) {
        super.appendHoverText(stack, context, display, adder, flag);

        BarrelData data = stack.get(UPDataComponents.BARREL_DATA);
        if (data == null || data.storedCount() == 0) return;

        Tier tier = Tier.fromLevel(data.tierLevel());
        NumberFormat fmt = NumberFormat.getNumberInstance(Locale.US);

        adder.accept(Component.translatable("tooltip.utilitiesplus.barrel.contains",
                        data.storedType().getHoverName(),
                        fmt.format(data.storedCount()),
                        fmt.format(tier.getCapacity()))
                .withStyle(ChatFormatting.AQUA));

        if (tier != Tier.BASE) {
            adder.accept(Component.translatable("tooltip.utilitiesplus.barrel.tier",
                            Component.translatable("tier.utilitiesplus." + tier.name().toLowerCase()))
                    .withStyle(ChatFormatting.GOLD));
        }
    }
}