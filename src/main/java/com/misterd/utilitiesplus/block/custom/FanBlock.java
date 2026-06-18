package com.misterd.utilitiesplus.block.custom;

import com.misterd.utilitiesplus.config.UPConfig;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class FanBlock extends Block {

    public static final MapCodec<FanBlock> CODEC = simpleCodec(FanBlock::new);
    public static final EnumProperty<Direction> FACING = BlockStateProperties.FACING;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty PULLING = BooleanProperty.create("pulling");
    public static final IntegerProperty POWER = BlockStateProperties.POWER;

    public FanBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(POWERED, false)
                .setValue(PULLING, false)
                .setValue(POWER, 0));
    }

    @Override
    protected MapCodec<FanBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        int power = context.getLevel().getBestNeighborSignal(context.getClickedPos());
        return this.defaultBlockState()
                .setValue(FACING, context.getNearestLookingDirection().getOpposite())
                .setValue(POWERED, power > 0)
                .setValue(PULLING, false)
                .setValue(POWER, power);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED, PULLING, POWER);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!player.isShiftKeyDown()) return InteractionResult.PASS;
        if (level.isClientSide()) return InteractionResult.SUCCESS;
        level.setBlock(pos, state.cycle(PULLING), 3);
        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean movedByPiston) {
        if (level.isClientSide()) return;
        int power = level.getBestNeighborSignal(pos);
        boolean powered = power > 0;
        BlockState newState = state.setValue(POWERED, powered).setValue(POWER, power);
        level.setBlock(pos, newState, 3);
        if (powered) {
            level.scheduleTick(pos, this, 1);
        }
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (level.isClientSide()) return;
        int power = level.getBestNeighborSignal(pos);
        if (power > 0) {
            level.setBlock(pos, state.setValue(POWERED, true).setValue(POWER, power), 3);
            level.scheduleTick(pos, this, 1);
        }
    }

    @Override
    protected void tick(BlockState state, net.minecraft.server.level.ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.getValue(POWERED)) return;
        int power = state.getValue(POWER);
        if (power <= 0) return;

        Direction facing = state.getValue(FACING);
        boolean pulling = state.getValue(PULLING);
        Direction pushDir = pulling ? facing.getOpposite() : facing;

        int range = computeRange(level, pos, facing, power);
        if (range > 0) {
            Vec3 force = new Vec3(pushDir.getStepX(), pushDir.getStepY(), pushDir.getStepZ()).scale(0.1);
            for (int i = 1; i <= range; i++) {
                BlockPos target = pos.relative(facing, i);
                AABB box = new AABB(target);
                List<Entity> entities = level.getEntities(null, box);
                for (Entity entity : entities) {
                    entity.setDeltaMovement(entity.getDeltaMovement().add(force));
                    entity.hurtMarked = true;
                }
            }
        }

        level.scheduleTick(pos, this, 1);
    }

    private int computeRange(Level level, BlockPos pos, Direction facing, int power) {
        for (int i = 1; i <= power; i++) {
            BlockPos target = pos.relative(facing, i);
            BlockState targetState = level.getBlockState(target);
            if (targetState.isRedstoneConductor(level, target)) return i - 1;
        }
        return power;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (!state.getValue(POWERED)) return;
        if (!UPConfig.get().fanParticles) return;

        int power = state.getValue(POWER);
        if (power <= 0) return;

        Direction facing = state.getValue(FACING);
        boolean pulling = state.getValue(PULLING);
        Direction particleDir = pulling ? facing.getOpposite() : facing;

        int range = computeRange(level, pos, facing, power);
        if (range <= 0) return;

        for (int i = 1; i <= range; i++) {
            BlockPos target = pos.relative(facing, i);
            if (random.nextFloat() < 0.6f) {
                double x = target.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.8;
                double y = target.getY() + 0.5 + (random.nextDouble() - 0.5) * 0.8;
                double z = target.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.8;
                Vec3 vel = new Vec3(particleDir.getStepX(), particleDir.getStepY(), particleDir.getStepZ()).scale(0.1 + random.nextDouble() * 0.1);
                level.addParticle(ParticleTypes.SMOKE, x, y, z, vel.x, vel.y, vel.z);
            }
        }
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return false;
    }
}