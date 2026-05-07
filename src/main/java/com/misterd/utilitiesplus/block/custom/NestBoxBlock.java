package com.misterd.utilitiesplus.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import org.jspecify.annotations.Nullable;

public class NestBoxBlock extends Block {

    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final MapCodec<NestBoxBlock> CODEC = simpleCodec(NestBoxBlock::new);

    private static final VoxelShape SHAPE_SOUTH = Shapes.or(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 1, 15, 16, 15, 16),
            Block.box(0, 15, 2, 16, 16, 16),
            Block.box(0, 1, 4, 1, 15, 15),
            Block.box(15, 1, 4, 16, 15, 15),
            Block.box(0, 1, 2, 1, 9, 4),
            Block.box(0, 1, 0, 1, 4, 2),
            Block.box(15, 1, 2, 16, 9, 4),
            Block.box(15, 1, 0, 16, 4, 2),
            Block.box(1, 1, 0, 15, 4, 1)
    );

    private static final VoxelShape SHAPE_NORTH = Shapes.or(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 1, 0, 16, 15, 1),
            Block.box(0, 15, 0, 16, 16, 14),
            Block.box(0, 1, 1, 1, 15, 12),
            Block.box(15, 1, 1, 16, 15, 12),
            Block.box(0, 1, 12, 1, 9, 14),
            Block.box(0, 1, 14, 1, 4, 16),
            Block.box(15, 1, 12, 16, 9, 14),
            Block.box(15, 1, 14, 16, 4, 16),
            Block.box(1, 1, 15, 15, 4, 16)
    );

    private static final VoxelShape SHAPE_EAST = Shapes.or(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 1, 0, 1, 15, 16),
            Block.box(0, 15, 0, 14, 16, 16),
            Block.box(1, 1, 0, 12, 15, 1),
            Block.box(1, 1, 15, 12, 15, 16),
            Block.box(12, 1, 0, 14, 9, 1),
            Block.box(14, 1, 0, 16, 4, 1),
            Block.box(12, 1, 15, 14, 9, 16),
            Block.box(14, 1, 15, 16, 4, 16),
            Block.box(15, 1, 1, 16, 4, 15)
    );

    private static final VoxelShape SHAPE_WEST = Shapes.or(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(15, 1, 0, 16, 15, 16),
            Block.box(2, 15, 0, 16, 16, 16),
            Block.box(4, 1, 0, 15, 15, 1),
            Block.box(4, 1, 15, 15, 15, 16),
            Block.box(2, 1, 0, 4, 9, 1),
            Block.box(0, 1, 0, 2, 4, 1),
            Block.box(2, 1, 15, 4, 9, 16),
            Block.box(0, 1, 15, 2, 4, 16),
            Block.box(0, 1, 1, 1, 4, 15)
    );

    public NestBoxBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.SOUTH));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case SOUTH -> SHAPE_NORTH;
            case EAST -> SHAPE_EAST;
            case WEST -> SHAPE_WEST;
            default -> SHAPE_SOUTH;
        };
    }
}