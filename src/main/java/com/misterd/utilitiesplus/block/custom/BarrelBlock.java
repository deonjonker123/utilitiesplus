package com.misterd.utilitiesplus.block.custom;

import com.misterd.utilitiesplus.blockentity.custom.BarrelBlockEntity;
import com.misterd.utilitiesplus.blockentity.custom.BarrelBlockEntity.Tier;
import com.misterd.utilitiesplus.component.UPDataComponents;
import com.misterd.utilitiesplus.component.custom.BarrelData;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
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
import org.jspecify.annotations.Nullable;

public class BarrelBlock extends BaseEntityBlock {

    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final MapCodec<BarrelBlock> CODEC = simpleCodec(BarrelBlock::new);

    public BarrelBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BarrelBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return null;
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide()) return InteractionResult.SUCCESS;
        if (!(level.getBlockEntity(pos) instanceof BarrelBlockEntity barrel)) return InteractionResult.FAIL;

        boolean isFrontFace = hit.getDirection() == state.getValue(FACING);

        if (isFrontFace) {
            if (!stack.isEmpty() && Tier.fromUpgradeItem(stack.getItem()) != null) {
                if (barrel.applyUpgrade(player, stack)) {
                    level.updateNeighbourForOutputSignal(pos, this);
                }
                return InteractionResult.SUCCESS_SERVER;
            }

            if (!stack.isEmpty() && barrel.canAccept(stack)) {
                barrel.insert(stack);
                level.updateNeighbourForOutputSignal(pos, this);
                return InteractionResult.SUCCESS_SERVER;
            }

            if (stack.isEmpty() && !barrel.isEmpty()) {
                int amount = player.isShiftKeyDown() ? 1 : barrel.getStoredType().getMaxStackSize();
                ItemStack extracted = barrel.extract(amount);
                player.getInventory().placeItemBackInInventory(extracted);
                if (!extracted.isEmpty()) player.drop(extracted, false);
                level.updateNeighbourForOutputSignal(pos, this);
                return InteractionResult.SUCCESS_SERVER;
            }

            return InteractionResult.SUCCESS_SERVER;
        }

        player.openMenu(new ExtendedMenuProvider<BlockPos>() {
            @Override
            public AbstractContainerMenu createMenu(int id, Inventory inv, Player p) {
                return barrel.createMenu(id, inv, p);
            }

            @Override
            public Component getDisplayName() {
                return Component.translatable("block.utilitiesplus.barrel");
            }

            @Override
            public BlockPos getScreenOpeningData(ServerPlayer serverPlayer) {
                return pos;
            }
        });
        return InteractionResult.SUCCESS_SERVER;
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos, Direction direction) {
        if (level.getBlockEntity(pos) instanceof BarrelBlockEntity barrel) {
            return barrel.getComparatorOutput();
        }
        return 0;
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide() && !player.isCreative()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof BarrelBlockEntity barrel) {
                ItemStack drop = new ItemStack(this);
                if (barrel.getStoredCount() > 0 || barrel.getTier() != BarrelBlockEntity.Tier.BASE) {
                    drop.set(UPDataComponents.BARREL_DATA, new BarrelData(
                            barrel.getStoredType().copy(),
                            barrel.getStoredCount(),
                            barrel.getTier().getLevel()
                    ));
                }
                barrel.clearContent();
                popResource(level, pos, drop);
            }
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (level.isClientSide()) return;
        if (!(level.getBlockEntity(pos) instanceof BarrelBlockEntity barrel)) return;
        BarrelData data = stack.get(UPDataComponents.BARREL_DATA);
        if (data == null) return;
        barrel.restoreFromData(data);
        barrel.setChanged();
        level.updateNeighbourForOutputSignal(pos, this);
    }
}