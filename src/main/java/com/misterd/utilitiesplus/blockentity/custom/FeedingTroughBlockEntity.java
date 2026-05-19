package com.misterd.utilitiesplus.blockentity.custom;

import com.misterd.utilitiesplus.blockentity.UPBlockEntities;
import com.misterd.utilitiesplus.config.UPConfig;
import com.misterd.utilitiesplus.util.FeedingTroughBreedingFlag;
import com.misterd.utilitiesplus.gui.custom.FeedingTroughMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.ticks.ContainerSingleItem;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class FeedingTroughBlockEntity extends BlockEntity implements ContainerSingleItem.BlockContainerSingleItem, MenuProvider {
    public NonNullList<ItemStack> inventory = NonNullList.withSize(1, ItemStack.EMPTY);
    private int tickCounter = 0;

    public FeedingTroughBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(UPBlockEntities.FEEDING_TROUGH_BE, worldPosition, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, FeedingTroughBlockEntity be) {
        if (level.isClientSide()) return;

        be.tickCounter++;
        if (be.tickCounter < UPConfig.get().feedingTroughFeedInterval) return;
        be.tickCounter = 0;

        ItemStack food = be.getTheItem();
        if (food.isEmpty()) return;

        int radius = UPConfig.get().feedingTroughRadius;
        AABB area = new AABB(
                pos.getX() - radius,pos.getY(), pos.getZ() - radius,
                pos.getX() + radius,pos.getY() + 3, pos.getZ() + radius
        );

        List<Animal> animals = level.getEntitiesOfClass(Animal.class, area);
        if (animals.size() >= UPConfig.get().feedingTroughAnimalLimit) return;

        for (Animal animal : animals) {
            if (animal.isFood(food) && animal.getAge() == 0 && animal.canFallInLove()) {
                FeedingTroughBreedingFlag.mark(animal);
                animal.setInLove(null);
                food.shrink(1);
                if (food.isEmpty()) {
                    be.setTheItem(ItemStack.EMPTY);
                } else {
                    be.setChanged();
                }
                return;
            }
        }
    }

    @Override
    public BlockEntity getContainerBlockEntity() {
        return this;
    }

    @Override
    public ItemStack getTheItem() {
        return inventory.getFirst();
    }

    @Override
    public void setTheItem(ItemStack itemStack) {
        setChanged();
        inventory.set(0, itemStack);
    }

    @Override
    public void clearContent() {
        inventory.set(0, ItemStack.EMPTY);
    }

    public void drops() {
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, inventory);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.utilitiesplus.feeding_trough");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new FeedingTroughMenu(containerId, inventory, this);
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if (!level.isClientSide()) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }
}