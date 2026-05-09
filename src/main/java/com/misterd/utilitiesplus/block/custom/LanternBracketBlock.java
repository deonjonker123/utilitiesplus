package com.misterd.utilitiesplus.block.custom;

import com.misterd.utilitiesplus.block.UPBlocks;
import com.misterd.utilitiesplus.blockentity.custom.LanternBracketBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class LanternBracketBlock extends BaseEntityBlock {

    public static final MapCodec<LanternBracketBlock> CODEC = simpleCodec(LanternBracketBlock::new);

    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<BracketAttachment> ATTACHMENT =
            EnumProperty.create("attachment", BracketAttachment.class);
    public static final IntegerProperty LIGHT_LEVEL =
            IntegerProperty.create("light_level", 0, 15);

    private static final VoxelShape BLOCK_SOUTH = Shapes.or(
            Block.box(7, 13, 1, 9, 15, 11),
            Block.box(6, 12, 0, 10, 16, 1),
            Block.box(6.5, 10, 0, 9.5, 12, 1),
            Block.box(7, 9, 0, 9, 10, 1),
            Block.box(5, 2, 5, 11, 11, 11)
    );
    private static final VoxelShape BLOCK_NORTH = Shapes.or(
            Block.box(7, 13, 5, 9, 15, 15),
            Block.box(6, 12, 15, 10, 16, 16),
            Block.box(6.5, 10, 15, 9.5, 12, 16),
            Block.box(7, 9, 15, 9, 10, 16),
            Block.box(5, 2, 5, 11, 11, 11)
    );
    private static final VoxelShape BLOCK_EAST = Shapes.or(
            Block.box(1, 13, 7, 11, 15, 9),
            Block.box(0, 12, 6, 1, 16, 10),
            Block.box(0, 10, 6.5, 1, 12, 9.5),
            Block.box(0, 9, 7, 1, 10, 9),
            Block.box(5, 2, 5, 11, 11, 11)
    );
    private static final VoxelShape BLOCK_WEST = Shapes.or(
            Block.box(5, 13, 7, 15, 15, 9),
            Block.box(15, 12, 6, 16, 16, 10),
            Block.box(15, 10, 6.5, 16, 12, 9.5),
            Block.box(15, 9, 7, 16, 10, 9),
            Block.box(5, 2, 5, 11, 11, 11)
    );

    private static final VoxelShape FENCE_SOUTH = Shapes.or(
            Block.box(7, 13, -5, 9, 15, 11),
            Block.box(6, 10, -6, 10, 16, -5),
            Block.box(6.5, 8, -6, 9.5, 10, -5),
            Block.box(7, 7, -6, 9, 8, -5),
            Block.box(5, 2, 5, 11, 11, 11)
    );
    private static final VoxelShape FENCE_NORTH = Shapes.or(
            Block.box(7, 13, 5, 9, 15, 21),
            Block.box(6, 10, 21, 10, 16, 22),
            Block.box(6.5, 8, 21, 9.5, 10, 22),
            Block.box(7, 7, 21, 9, 8, 22),
            Block.box(5, 2, 5, 11, 11, 11)
    );
    private static final VoxelShape FENCE_EAST = Shapes.or(
            Block.box(-5, 13, 7, 11, 15, 9),
            Block.box(-6, 10, 6, -5, 16, 10),
            Block.box(-6, 8, 6.5, -5, 10, 9.5),
            Block.box(-6, 7, 7, -5, 8, 9),
            Block.box(5, 2, 5, 11, 11, 11)
    );
    private static final VoxelShape FENCE_WEST = Shapes.or(
            Block.box(5, 13, 7, 21, 15, 9),
            Block.box(21, 10, 6, 22, 16, 10),
            Block.box(21, 8, 6.5, 22, 10, 9.5),
            Block.box(21, 7, 7, 22, 8, 9),
            Block.box(5, 2, 5, 11, 11, 11)
    );

    private static final VoxelShape WALL_SOUTH = Shapes.or(
            Block.box(7, 13, -3, 9, 15, 11),
            Block.box(6, 10, -4, 10, 16, -3),
            Block.box(6.5, 8, -4, 9.5, 10, -3),
            Block.box(7, 7, -4, 9, 8, -3),
            Block.box(5, 2, 5, 11, 11, 11)
    );
    private static final VoxelShape WALL_NORTH = Shapes.or(
            Block.box(7, 13, 5, 9, 15, 19),
            Block.box(6, 10, 19, 10, 16, 20),
            Block.box(6.5, 8, 19, 9.5, 10, 20),
            Block.box(7, 7, 19, 9, 8, 20),
            Block.box(5, 2, 5, 11, 11, 11)
    );
    private static final VoxelShape WALL_EAST = Shapes.or(
            Block.box(-3, 13, 7, 11, 15, 9),
            Block.box(-4, 10, 6, -3, 16, 10),
            Block.box(-4, 8, 6.5, -3, 10, 9.5),
            Block.box(-4, 7, 7, -3, 8, 9),
            Block.box(5, 2, 5, 11, 11, 11)
    );
    private static final VoxelShape WALL_WEST = Shapes.or(
            Block.box(5, 13, 7, 19, 15, 9),
            Block.box(19, 10, 6, 20, 16, 10),
            Block.box(19, 8, 6.5, 20, 10, 9.5),
            Block.box(19, 7, 7, 20, 8, 9),
            Block.box(5, 2, 5, 11, 11, 11)
    );

    public LanternBracketBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(ATTACHMENT, BracketAttachment.BLOCK)
                .setValue(LIGHT_LEVEL, 0));
    }

    @Override
    protected MapCodec<LanternBracketBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, ATTACHMENT, LIGHT_LEVEL);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction facing = context.getClickedFace();
        if (!facing.getAxis().isHorizontal()) return null;

        BlockPos supportPos = context.getClickedPos().relative(facing.getOpposite());
        BlockState support = context.getLevel().getBlockState(supportPos);

        BracketAttachment attachment;
        if (support.is(BlockTags.FENCES)) {
            attachment = BracketAttachment.FENCE_POST;
        } else if (support.is(BlockTags.WALLS) || isBeam(support)) {
            attachment = BracketAttachment.WALL;
        } else if (support.isFaceSturdy(context.getLevel(), supportPos, facing)) {
            attachment = BracketAttachment.BLOCK;
        } else {
            return null;
        }

        return this.defaultBlockState()
                .setValue(FACING, facing)
                .setValue(ATTACHMENT, attachment);
    }

    private boolean isBeam(BlockState state) {
        Block block = state.getBlock();
        return block == UPBlocks.OAK_BEAM || block == UPBlocks.SPRUCE_BEAM ||
                block == UPBlocks.BIRCH_BEAM || block == UPBlocks.JUNGLE_BEAM ||
                block == UPBlocks.ACACIA_BEAM || block == UPBlocks.DARK_OAK_BEAM ||
                block == UPBlocks.MANGROVE_BEAM || block == UPBlocks.CHERRY_BEAM ||
                block == UPBlocks.PALE_OAK_BEAM || block == UPBlocks.BAMBOO_BEAM ||
                block == UPBlocks.CRIMSON_BEAM || block == UPBlocks.WARPED_BEAM;
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks,
                                     BlockPos pos, Direction direction, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        Direction supportDir = state.getValue(FACING).getOpposite();
        if (direction == supportDir) {
            boolean stillValid = switch (state.getValue(ATTACHMENT)) {
                case FENCE_POST -> neighbourState.is(BlockTags.FENCES);
                case WALL -> neighbourState.is(BlockTags.WALLS) || isBeam(neighbourState);
                case BLOCK -> neighbourState.isFaceSturdy(level, neighbourPos, state.getValue(FACING));
            };
            if (!stillValid) {
                if (level instanceof Level worldLevel) {
                    BlockEntity be = worldLevel.getBlockEntity(pos);
                    if (be instanceof LanternBracketBlockEntity bracket && !bracket.isEmpty()) {
                        Containers.dropItemStack(worldLevel, pos.getX(), pos.getY(), pos.getZ(),
                                new ItemStack(bracket.getLanternState().getBlock()));
                    }
                }
                return Blocks.AIR.defaultBlockState();
            }
        }
        return super.updateShape(state, level, ticks, pos, direction, neighbourPos, neighbourState, random);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (level.isClientSide()) return InteractionResult.SUCCESS;
        if (!(level.getBlockEntity(pos) instanceof LanternBracketBlockEntity be)) return InteractionResult.PASS;
        if (be.isEmpty()) return InteractionResult.PASS;

        if (!player.isCreative()) {
            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(),
                    new ItemStack(be.getLanternState().getBlock()));
        }
        be.setLanternState(Blocks.AIR.defaultBlockState());
        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack destroyedWith) {
        if (!level.isClientSide() && blockEntity instanceof LanternBracketBlockEntity be && !be.isEmpty()) {
            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(),
                    new ItemStack(be.getLanternState().getBlock()));
        }
        super.playerDestroy(level, player, pos, state, blockEntity, destroyedWith);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(ATTACHMENT)) {
            case FENCE_POST -> switch (state.getValue(FACING)) {
                case SOUTH -> FENCE_SOUTH;
                case EAST -> FENCE_EAST;
                case WEST -> FENCE_WEST;
                default -> FENCE_NORTH;
            };
            case WALL -> switch (state.getValue(FACING)) {
                case SOUTH -> WALL_SOUTH;
                case EAST -> WALL_EAST;
                case WEST -> WALL_WEST;
                default -> WALL_NORTH;
            };
            default -> switch (state.getValue(FACING)) {
                case SOUTH -> BLOCK_SOUTH;
                case EAST -> BLOCK_EAST;
                case WEST -> BLOCK_WEST;
                default -> BLOCK_NORTH;
            };
        };
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    protected VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Block.box(0, 0, 0, 16, 1, 16);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return true;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LanternBracketBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return null;
    }

    public enum BracketAttachment implements StringRepresentable {
        BLOCK("block"),
        FENCE_POST("fence_post"),
        WALL("wall");

        private final String name;

        BracketAttachment(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}