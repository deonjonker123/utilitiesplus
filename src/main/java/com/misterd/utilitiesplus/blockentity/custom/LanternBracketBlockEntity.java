package com.misterd.utilitiesplus.blockentity.custom;

import com.misterd.utilitiesplus.block.custom.LanternBracketBlock;
import com.misterd.utilitiesplus.blockentity.UPBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

public class LanternBracketBlockEntity extends BlockEntity {

    private BlockState lanternState = Blocks.AIR.defaultBlockState();

    public LanternBracketBlockEntity(BlockPos pos, BlockState state) {
        super(UPBlockEntities.LANTERN_BRACKET_BE, pos, state);
    }

    public BlockState getLanternState() {
        return lanternState;
    }

    public void setLanternState(BlockState state) {
        lanternState = state;
        setChanged();
        if (level != null && !level.isClientSide()) {
            int light = state.getLightEmission();
            level.setBlock(worldPosition, getBlockState().setValue(LanternBracketBlock.LIGHT_LEVEL, light), 3);
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    public boolean isEmpty() {
        return lanternState.isAir();
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.store("Lantern", BlockState.CODEC, lanternState);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        lanternState = input.read("Lantern", BlockState.CODEC).orElse(Blocks.AIR.defaultBlockState());
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