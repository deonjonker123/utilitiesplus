package com.misterd.utilitiesplus.block.custom;

import com.misterd.utilitiesplus.blockentity.UPBlockEntities;
import com.misterd.utilitiesplus.blockentity.custom.FeedingTroughBlockEntity;
import com.misterd.utilitiesplus.util.UPTags;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class FeedingTroughBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);

    public static final MapCodec<FeedingTroughBlock> CODEC = simpleCodec(FeedingTroughBlock::new);

    public FeedingTroughBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new FeedingTroughBlockEntity(worldPosition, blockState);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack destroyedWith) {
        if (level.getBlockEntity(pos) instanceof FeedingTroughBlockEntity feedingTroughBlockEntity) {
            feedingTroughBlockEntity.drops();
            level.updateNeighbourForOutputSignal(pos, this);
        }

        super.playerDestroy(level, player, pos, state, blockEntity, destroyedWith);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide()) return InteractionResult.SUCCESS;

        if (level.getBlockEntity(pos) instanceof FeedingTroughBlockEntity feedingTroughBlockEntity) {
            boolean isFeedingTroughEmpty = feedingTroughBlockEntity.isEmpty();

            if (isFeedingTroughEmpty && !itemStack.isEmpty() && itemStack.is(UPTags.Items.ANIMAL_FEED)) {
                feedingTroughBlockEntity.setTheItem(itemStack.copy());
                itemStack.setCount(0);
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1F, 2F);
            }
            else {
                player.openMenu(new ExtendedMenuProvider<BlockPos>() {
                    public AbstractContainerMenu createMenu(int id, Inventory inv, Player p) {
                        return feedingTroughBlockEntity.createMenu(id, inv, p);
                    }

                    @Override
                    public Component getDisplayName() {
                        return Component.translatable("block.utilitiesplus.feeding_trough");
                    }

                    @Override
                    public BlockPos getScreenOpeningData(ServerPlayer serverPlayer) {
                        return pos;
                    }
                });
            }
        }

        return InteractionResult.SUCCESS_SERVER;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) return null;
        return type == UPBlockEntities.FEEDING_TROUGH_BE
                ? (lvl, pos, blockState, be) -> FeedingTroughBlockEntity.tick(lvl, pos, blockState, (FeedingTroughBlockEntity) be)
                : null;
    }
}
