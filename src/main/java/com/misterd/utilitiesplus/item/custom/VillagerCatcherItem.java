package com.misterd.utilitiesplus.item.custom;

import com.misterd.utilitiesplus.UtilitiesPlus;
import com.misterd.utilitiesplus.component.UPDataComponents;
import com.misterd.utilitiesplus.component.custom.CapturedVillagerData;
import com.misterd.utilitiesplus.config.UPConfig;
import com.misterd.utilitiesplus.item.UPItems;
import com.misterd.utilitiesplus.mixin.VillagerAccessor;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.TagValueInput;

import java.util.Optional;

public class VillagerCatcherItem extends Item {

    public VillagerCatcherItem(Properties properties) {
        super(properties);
    }

    public static void registerEvents() {
        UseEntityCallback.EVENT.register((player, level, hand, entity, hitResult) -> {
            if (level.isClientSide()) return InteractionResult.PASS;
            if (hand != InteractionHand.MAIN_HAND) return InteractionResult.PASS;

            ItemStack stack = player.getItemInHand(hand);
            if (!stack.is(UPItems.VILLAGER_CATCHER)) return InteractionResult.PASS;
            if (!(entity instanceof Villager villager)) return InteractionResult.PASS;
            if (stack.has(UPDataComponents.VILLAGER_DATA)) return InteractionResult.PASS;

            ProblemReporter.Collector collector = new ProblemReporter.Collector();
            net.minecraft.world.level.storage.TagValueOutput valueOutput = net.minecraft.world.level.storage.TagValueOutput.createWithoutContext(collector);
            villager.saveWithoutId(valueOutput);
            CompoundTag nbt = valueOutput.buildResult();

            if (!collector.isEmpty()) {
                UtilitiesPlus.LOGGER.warn("Problems saving villager NBT: {}", collector.getReport());
            }

            nbt.getCompound("Brain").flatMap(brain -> brain.getCompound("memories")).ifPresent(memories -> {
                memories.remove("minecraft:potential_job_site");
                memories.remove("minecraft:home");
                memories.remove("minecraft:meeting_point");
            });

            nbt.remove("LastRestock");
            nbt.remove("RestocksToday");

            String professionName = villager.getVillagerData().profession()
                    .unwrapKey()
                    .map(key -> key.identifier().getPath())
                    .orElse("none");

            Optional<String> customName = Optional.ofNullable(villager.getCustomName())
                    .map(Component::getString);

            stack.set(UPDataComponents.VILLAGER_DATA, new CapturedVillagerData(nbt, professionName, customName));

            villager.discard();

            ((ServerLevel) level).sendParticles(
                    ParticleTypes.PORTAL,
                    villager.getX(), villager.getY() + 1.0, villager.getZ(),
                    80, 0.2, 0.3, 0.2, 0.05
            );

            return InteractionResult.SUCCESS;
        });
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        Level level = ctx.getLevel();
        if (level.isClientSide()) return InteractionResult.PASS;

        ItemStack stack = ctx.getItemInHand();
        CapturedVillagerData data = stack.get(UPDataComponents.VILLAGER_DATA);
        if (data == null) return InteractionResult.PASS;

        BlockPos spawnPos = ctx.getClickedPos().relative(ctx.getClickedFace());

        if (!level.getBlockState(spawnPos).isAir() && !level.getBlockState(spawnPos).canBeReplaced()) {
            return InteractionResult.FAIL;
        }

        ServerLevel serverLevel = (ServerLevel) level;

        Villager villager = EntityType.VILLAGER.create(serverLevel, EntitySpawnReason.LOAD);
        if (villager == null) return InteractionResult.FAIL;

        CompoundTag nbt = data.nbt().copy();

        if (UPConfig.get().villagerResetOnRelease) {
            nbt.remove("VillagerData");
            nbt.remove("Offers");
            nbt.remove("Inventory");
            nbt.remove("Brain");
        }

        ProblemReporter.Collector collector = new ProblemReporter.Collector();
        ((VillagerAccessor) villager).invokeReadAdditionalSaveData(
                TagValueInput.create(collector, serverLevel.registryAccess(), nbt)
        );

        if (!collector.isEmpty()) {
            UtilitiesPlus.LOGGER.warn("Problems loading villager NBT: {}", collector.getReport());
        }

        villager.setPos(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5);
        serverLevel.addFreshEntity(villager);

        serverLevel.sendParticles(
                ParticleTypes.PORTAL,
                villager.getX(), villager.getY() + 1.0, villager.getZ(),
                80, 0.2, 0.3, 0.2, 0.05
        );

        stack.remove(UPDataComponents.VILLAGER_DATA);

        return InteractionResult.SUCCESS;
    }
}