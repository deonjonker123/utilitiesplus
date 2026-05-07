package com.misterd.utilitiesplus.block.custom;

import com.misterd.utilitiesplus.blockentity.UPBlockEntities;
import com.misterd.utilitiesplus.blockentity.custom.NestBoxBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
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
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import org.jspecify.annotations.Nullable;

public class NestBoxBlock extends BaseEntityBlock {

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
    protected MapCodec<NestBoxBlock> codec() {
        return CODEC;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
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
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case SOUTH -> SHAPE_NORTH;
            case EAST -> SHAPE_EAST;
            case WEST -> SHAPE_WEST;
            default -> SHAPE_SOUTH;
        };
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new NestBoxBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) return null;
        return type == UPBlockEntities.NEST_BOX_BE
                ? (lvl, pos, blockState, be) -> NestBoxBlockEntity.tick(lvl, pos, blockState, (NestBoxBlockEntity) be)
                : null;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide()) return InteractionResult.SUCCESS;
        if (!(level.getBlockEntity(pos) instanceof NestBoxBlockEntity nestBox)) return InteractionResult.FAIL;

        player.openMenu(new ExtendedMenuProvider<BlockPos>() {
            @Override
            public AbstractContainerMenu createMenu(int id, Inventory inv, Player p) {
                return nestBox.createMenu(id, inv, p);
            }

            @Override
            public Component getDisplayName() {
                return Component.translatable("block.utilitiesplus.nest_box");
            }

            @Override
            public BlockPos getScreenOpeningData(ServerPlayer serverPlayer) {
                return pos;
            }
        });

        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack destroyedWith) {
        if (!level.isClientSide() && blockEntity instanceof NestBoxBlockEntity nestBox) {
            Containers.dropContents(level, pos, nestBox);
        }
        super.playerDestroy(level, player, pos, state, blockEntity, destroyedWith);
    }
}