package com.misterd.utilitiesplus.block.custom;

import com.misterd.utilitiesplus.util.UPBlockStateProperties;
import com.misterd.utilitiesplus.util.VerticalSlabType;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class VerticalSlabBlock extends Block implements SimpleWaterloggedBlock {

    public static final MapCodec<VerticalSlabBlock> CODEC = simpleCodec(VerticalSlabBlock::new);
    public static final EnumProperty<VerticalSlabType> TYPE = UPBlockStateProperties.VERTICAL_SLAB_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape SHAPE_NORTH = Block.box(0, 0, 0, 16, 16, 8);
    private static final VoxelShape SHAPE_SOUTH = Block.box(0, 0, 8, 16, 16, 16);
    private static final VoxelShape SHAPE_EAST = Block.box(8, 0, 0, 16, 16, 16);
    private static final VoxelShape SHAPE_WEST = Block.box(0, 0, 0, 8, 16, 16);

    public VerticalSlabBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(TYPE, VerticalSlabType.NORTH)
                .setValue(WATERLOGGED, false));
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TYPE, WATERLOGGED);
    }

    @Override
    protected boolean useShapeForLightOcclusion(BlockState state) {
        return state.getValue(TYPE) != VerticalSlabType.DOUBLE;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(TYPE)) {
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case EAST -> SHAPE_EAST;
            case WEST -> SHAPE_WEST;
            case DOUBLE -> Shapes.block();
        };
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        BlockState existing = context.getLevel().getBlockState(pos);

        if (existing.is(this) && existing.getValue(TYPE) != VerticalSlabType.DOUBLE) {
            return existing.setValue(TYPE, VerticalSlabType.DOUBLE).setValue(WATERLOGGED, false);
        }

        FluidState fluidState = context.getLevel().getFluidState(pos);
        Direction clickedFace = context.getClickedFace();

        VerticalSlabType type;
        if (clickedFace.getAxis().isHorizontal()) {
            type = fromDirection(clickedFace.getOpposite());
        } else {
            Vec3 clickLocation = context.getClickLocation();
            double x = clickLocation.x - pos.getX() - 0.5;
            double z = clickLocation.z - pos.getZ() - 0.5;
            if (Math.abs(x) > Math.abs(z)) {
                type = x > 0 ? VerticalSlabType.EAST : VerticalSlabType.WEST;
            } else {
                type = z > 0 ? VerticalSlabType.SOUTH : VerticalSlabType.NORTH;
            }
        }

        return this.defaultBlockState()
                .setValue(TYPE, type)
                .setValue(WATERLOGGED, fluidState.is(Fluids.WATER));
    }

    @Override
    protected boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        VerticalSlabType type = state.getValue(TYPE);
        if (type == VerticalSlabType.DOUBLE || !context.getItemInHand().is(this.asItem())) return false;

        if (context.replacingClickedOnBlock()) {
            Direction clickedFace = context.getClickedFace();
            Vec3 clickLocation = context.getClickLocation();
            BlockPos pos = context.getClickedPos();
            double x = clickLocation.x - pos.getX();
            double z = clickLocation.z - pos.getZ();

            return switch (type) {
                case NORTH -> clickedFace == Direction.SOUTH || (clickedFace.getAxis().isVertical() && z >= 0.5);
                case SOUTH -> clickedFace == Direction.NORTH || (clickedFace.getAxis().isVertical() && z < 0.5);
                case EAST -> clickedFace == Direction.WEST || (clickedFace.getAxis().isVertical() && x < 0.5);
                case WEST -> clickedFace == Direction.EAST || (clickedFace.getAxis().isVertical() && x >= 0.5);
                default -> false;
            };
        }

        return true;
    }

    private static VerticalSlabType fromDirection(Direction dir) {
        return switch (dir) {
            case NORTH -> VerticalSlabType.NORTH;
            case SOUTH -> VerticalSlabType.SOUTH;
            case EAST -> VerticalSlabType.EAST;
            case WEST -> VerticalSlabType.WEST;
            default -> VerticalSlabType.NORTH;
        };
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState fluidState) {
        if (state.getValue(TYPE) == VerticalSlabType.DOUBLE) return false;
        return SimpleWaterloggedBlock.super.placeLiquid(level, pos, state, fluidState);
    }

    @Override
    public boolean canPlaceLiquid(@Nullable LivingEntity user, BlockGetter level, BlockPos pos, BlockState state, Fluid fluid) {
        if (state.getValue(TYPE) == VerticalSlabType.DOUBLE) return false;
        return SimpleWaterloggedBlock.super.canPlaceLiquid(user, level, pos, state, fluid);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction direction, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        if (state.getValue(WATERLOGGED)) {
            ticks.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return super.updateShape(state, level, ticks, pos, direction, neighbourPos, neighbourState, random);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }
}