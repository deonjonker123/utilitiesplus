package com.misterd.utilitiesplus.blockentity.custom;

import com.misterd.utilitiesplus.blockentity.UPBlockEntities;
import com.misterd.utilitiesplus.gui.custom.KilnMenu;
import com.misterd.utilitiesplus.util.UPTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class KilnBlockEntity extends BlockEntity implements WorldlyContainer, MenuProvider {

    private static final int SLOT_INPUT = 0;
    private static final int SLOT_FUEL = 1;
    private static final int SLOT_OUTPUT = 2;
    private static final int SIZE = 3;
    private static final int COOK_SPEED_MULTIPLIER = 2;

    private static final int[] SLOTS_TOP = {SLOT_INPUT};
    private static final int[] SLOTS_SIDE = {SLOT_FUEL};
    private static final int[] SLOTS_BOTTOM = {SLOT_OUTPUT};

    private final NonNullList<ItemStack> inventory = NonNullList.withSize(SIZE, ItemStack.EMPTY);

    int litTime = 0;
    int litDuration = 0;
    int cookingProgress = 0;
    int cookingTotalTime = 0;
    float experience = 0f;

    private final ContainerData data = new ContainerData() {
        @Override
        public int get(int i) {
            return switch (i) {
                case 0 -> litTime;
                case 1 -> litDuration;
                case 2 -> cookingProgress;
                case 3 -> cookingTotalTime;
                default -> 0;
            };
        }

        @Override
        public void set(int i, int v) {
            switch (i) {
                case 0 -> litTime = v;
                case 1 -> litDuration = v;
                case 2 -> cookingProgress = v;
                case 3 -> cookingTotalTime = v;
            }
        }

        @Override
        public int getCount() { return 4; }
    };

    public KilnBlockEntity(BlockPos pos, BlockState state) {
        super(UPBlockEntities.KILN_BE, pos, state);
    }

    public ContainerData getData() { return data; }

    public static void tick(Level level, BlockPos pos, BlockState state, KilnBlockEntity be) {
        if (!(level instanceof ServerLevel serverLevel)) return;

        boolean wasLit = be.isLit();
        boolean dirty = false;

        Optional<RecipeHolder<SmeltingRecipe>> recipe = be.getRecipe(serverLevel);

        if (be.isLit()) {
            be.litTime--;
        }

        if (recipe.isPresent()) {
            ItemStack fuel = be.inventory.get(SLOT_FUEL);

            if (!be.isLit() && !fuel.isEmpty()) {
                int burnDuration = serverLevel.fuelValues().burnDuration(fuel);
                if (burnDuration > 0) {
                    be.litDuration = burnDuration / COOK_SPEED_MULTIPLIER;
                    be.litTime = burnDuration / COOK_SPEED_MULTIPLIER;
                    dirty = true;
                    fuel.shrink(1);
                    if (fuel.isEmpty()) {
                        ItemStackTemplate remainder = fuel.getItem().getCraftingRemainder();
                        be.inventory.set(SLOT_FUEL, remainder != null ? remainder.create() : ItemStack.EMPTY);
                    }
                }
            }

            if (be.isLit()) {
                be.cookingProgress++;
                if (be.cookingProgress >= be.cookingTotalTime) {
                    be.cookingProgress = 0;
                    be.cookingTotalTime = be.getCookTime(serverLevel);
                    if (be.canSmelt(serverLevel, recipe)) {
                        be.doSmelt(serverLevel, recipe);
                    }
                    dirty = true;
                }
            } else {
                be.cookingProgress = 0;
            }
        } else {
            be.cookingProgress = 0;
            be.cookingTotalTime = 0;
        }

        if (wasLit != be.isLit()) {
            state = state.setValue(BlockStateProperties.LIT, be.isLit());
            level.setBlock(pos, state, 3);
            dirty = true;
        }

        if (dirty) be.setChanged();
    }

    private boolean isLit() { return litTime > 0; }

    private Optional<RecipeHolder<SmeltingRecipe>> getRecipe(ServerLevel level) {
        ItemStack input = inventory.get(SLOT_INPUT);
        if (input.isEmpty()) return Optional.empty();
        if (!input.is(UPTags.Items.KILN_SMELTABLES)) return Optional.empty();
        return level.recipeAccess().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(input), level);
    }

    private boolean canSmelt(ServerLevel level, Optional<RecipeHolder<SmeltingRecipe>> recipe) {
        if (recipe.isEmpty()) return false;
        ItemStack result = recipe.get().value().assemble(new SingleRecipeInput(inventory.get(SLOT_INPUT)));
        if (result.isEmpty()) return false;
        ItemStack output = inventory.get(SLOT_OUTPUT);
        if (output.isEmpty()) return true;
        if (!ItemStack.isSameItemSameComponents(output, result)) return false;
        return output.getCount() + result.getCount() <= output.getMaxStackSize();
    }

    private void doSmelt(ServerLevel level, Optional<RecipeHolder<SmeltingRecipe>> recipe) {
        if (recipe.isEmpty()) return;
        ItemStack result = recipe.get().value().assemble(new SingleRecipeInput(inventory.get(SLOT_INPUT)));
        ItemStack output = inventory.get(SLOT_OUTPUT);
        experience += recipe.get().value().experience();

        if (output.isEmpty()) {
            inventory.set(SLOT_OUTPUT, result.copy());
        } else {
            output.grow(result.getCount());
        }

        inventory.get(SLOT_INPUT).shrink(1);
    }

    private int getCookTime(ServerLevel level) {
        return getRecipe(level)
                .map(r -> r.value().cookingTime() / COOK_SPEED_MULTIPLIER)
                .orElse(200);
    }

    public void awardExperience(ServerLevel level, Vec3 pos) {
        int reward = (int) experience;
        float remainder = experience - reward;
        if (remainder > 0f && level.getRandom().nextFloat() < remainder) {
            reward++;
        }
        if (reward > 0) {
            ExperienceOrb.award(level, pos, reward);
        }
        experience = 0f;
        setChanged();
    }

    @Override
    public int[] getSlotsForFace(Direction dir) {
        if (dir == Direction.UP) return SLOTS_TOP;
        if (dir == Direction.DOWN) return SLOTS_BOTTOM;
        return SLOTS_SIDE;
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction dir) {
        if (dir == Direction.DOWN) return false;
        if (dir == Direction.UP) return slot == SLOT_INPUT && stack.is(UPTags.Items.KILN_SMELTABLES);
        return slot == SLOT_FUEL && level.fuelValues().isFuel(stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction dir) {
        return dir == Direction.DOWN && slot == SLOT_OUTPUT;
    }

    @Override
    public int getContainerSize() {
        return SIZE;
    }

    @Override
    public boolean isEmpty() {
        return inventory.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getItem(int slot) {
        return inventory.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack result = ContainerHelper.removeItem(inventory, slot, amount);
        if (!result.isEmpty()) setChanged();
        return result;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(inventory, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        inventory.set(slot, stack);
        if (stack.getCount() > getMaxStackSize()) stack.setCount(getMaxStackSize());
        if (slot == SLOT_INPUT && level instanceof ServerLevel serverLevel) {
            cookingTotalTime = getCookTime(serverLevel);
            cookingProgress = 0;
        }
        setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent() {
        inventory.clear();
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, inventory);
        output.putInt("litTime", litTime);
        output.putInt("litDuration", litDuration);
        output.putInt("cookingProgress", cookingProgress);
        output.putInt("cookingTotalTime", cookingTotalTime);
        output.putFloat("experience", experience);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, inventory);
        litTime = input.getIntOr("litTime", 0);
        litDuration = input.getIntOr("litDuration", 0);
        cookingProgress = input.getIntOr("cookingProgress", 0);
        cookingTotalTime = input.getIntOr("cookingTotalTime", 0);
        experience = input.getFloatOr("experience", 0f);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.utilitiesplus.kiln");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInv, Player player) {
        return new KilnMenu(id, playerInv, this, data);
    }
}