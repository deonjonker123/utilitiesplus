package com.misterd.utilitiesplus.block.custom;

import com.misterd.utilitiesplus.blockentity.UPBlockEntities;
import com.misterd.utilitiesplus.blockentity.custom.RedstoneClockBlockEntity;
import com.misterd.utilitiesplus.config.UPConfig;
import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.server.level.ServerLevel;
import org.jspecify.annotations.Nullable;

public class RedstoneClockBlock extends BaseEntityBlock {

    public static final MapCodec<RedstoneClockBlock> CODEC = simpleCodec(RedstoneClockBlock::new);
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty ENABLED = BooleanProperty.create("enabled");

    public RedstoneClockBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(POWERED, false)
                .setValue(ENABLED, true));
    }

    @Override
    protected MapCodec<RedstoneClockBlock> codec() {
        return CODEC;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED, ENABLED);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new RedstoneClockBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) return null;
        return type == UPBlockEntities.REDSTONE_CLOCK_BE
                ? (lvl, pos, blockState, be) -> tick(lvl, pos, blockState, (RedstoneClockBlockEntity) be)
                : null;
    }

    private static void tick(Level level, BlockPos pos, BlockState state, RedstoneClockBlockEntity be) {
        if (!(level instanceof ServerLevel)) return;
        if (!state.getValue(ENABLED)) {
            if (state.getValue(POWERED)) {
                level.setBlock(pos, state.setValue(POWERED, false), 3);
                level.updateNeighborsAt(pos, state.getBlock());
            }
            return;
        }
        boolean shouldPulse = be.onTick();
        boolean powered = state.getValue(POWERED);
        if (shouldPulse != powered) {
            level.setBlock(pos, state.setValue(POWERED, shouldPulse), 3);
            level.updateNeighborsAt(pos, state.getBlock());
            if (shouldPulse && UPConfig.get().redstoneClockSound) {
                level.playSound(null, pos, SoundEvents.DISPENSER_DISPENSE, SoundSource.BLOCKS, 0.4f, 2.0f);
            }
        }
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean movedByPiston) {
        if (level.isClientSide()) return;
        boolean hasSignal = level.hasNeighborSignal(pos);
        boolean enabled = state.getValue(ENABLED);
        if (hasSignal == enabled) {
            level.setBlock(pos, state.setValue(ENABLED, !hasSignal), 3);
        }
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (level.isClientSide()) return;
        if (level.hasNeighborSignal(pos)) {
            level.setBlock(pos, state.setValue(ENABLED, false), 3);
        }
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide()) return InteractionResult.SUCCESS;
        if (!(level.getBlockEntity(pos) instanceof RedstoneClockBlockEntity clock)) return InteractionResult.FAIL;

        if (stack.is(Items.REDSTONE_TORCH)) {
            clock.resetInterval();
            player.sendOverlayMessage(Component.translatable("message.utilitiesplus.redstone_clock.interval_reset").withStyle(ChatFormatting.GOLD));
            return InteractionResult.SUCCESS_SERVER;
        }

        clock.adjustInterval(!player.isShiftKeyDown());
        player.sendOverlayMessage(Component.translatable("message.utilitiesplus.redstone_clock.interval", clock.getInterval()).withStyle(ChatFormatting.GOLD));
        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    protected int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return state.getValue(POWERED) ? 15 : 0;
    }

    @Override
    protected int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return state.getValue(POWERED) ? 15 : 0;
    }
}