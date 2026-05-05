package com.misterd.utilitiesplus.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HopperBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HopperDuctBlock extends Block{
    public static final MapCodec<HopperDuctBlock> CODEC = simpleCodec(HopperDuctBlock::new);

    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty WEST = BooleanProperty.create("west");

    private static final VoxelShape CORE = Block.box(6, 4, 6, 10,  8, 10);
    private static final VoxelShape ARM_NORTH = Block.box(6, 4,  0, 10,  8,  6);
    private static final VoxelShape ARM_SOUTH = Block.box(6, 4, 10, 10,  8, 16);
    private static final VoxelShape ARM_EAST = Block.box(10, 4, 6, 16,  8, 10);
    private static final VoxelShape ARM_WEST = Block.box( 0, 4, 6,  6,  8, 10);

    public HopperDuctBlock(BlockBehaviour.Properties props) {
        super(props);
        registerDefaultState(stateDefinition.any()
                .setValue(NORTH,false)
                .setValue(SOUTH,false)
                .setValue(EAST, false)
                .setValue(WEST, false)
        );
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(NORTH, SOUTH, EAST, WEST);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return withConnections(defaultBlockState(), ctx.getLevel(), ctx.getClickedPos());
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction dir, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        return withConnections(state, level, pos);
    }

    private BlockState withConnections(BlockState state, LevelReader level, BlockPos pos) {
        return state
                .setValue(NORTH, connects(level, pos, Direction.NORTH))
                .setValue(SOUTH, connects(level, pos, Direction.SOUTH))
                .setValue(EAST,  connects(level, pos, Direction.EAST))
                .setValue(WEST,  connects(level, pos, Direction.WEST));
    }

    private boolean connects(LevelReader level, BlockPos pos, Direction dir) {
        BlockPos neighbor = pos.relative(dir);
        BlockState state = level.getBlockState(neighbor);
        return state.getBlock() instanceof HopperDuctBlock
                || state.getBlock() instanceof HopperBlock
                || level.getBlockEntity(neighbor) instanceof Container;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        VoxelShape s = CORE;
        if (state.getValue(NORTH)) s = Shapes.or(s, ARM_NORTH);
        if (state.getValue(SOUTH)) s = Shapes.or(s, ARM_SOUTH);
        if (state.getValue(EAST))  s = Shapes.or(s, ARM_EAST);
        if (state.getValue(WEST))  s = Shapes.or(s, ARM_WEST);
        return s;
    }
}
