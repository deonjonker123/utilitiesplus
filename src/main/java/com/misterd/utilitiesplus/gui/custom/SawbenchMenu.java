package com.misterd.utilitiesplus.gui.custom;

import com.misterd.utilitiesplus.block.UPBlocks;
import com.misterd.utilitiesplus.gui.UPMenuTypes;
import com.misterd.utilitiesplus.network.SawbenchRecipesPacket;
import com.misterd.utilitiesplus.recipe.UPRecipes;
import com.misterd.utilitiesplus.recipe.custom.SawbenchRecipe;
import com.misterd.utilitiesplus.util.UPTags;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class SawbenchMenu extends AbstractContainerMenu {

    public static final int INPUT_SLOT = 0;
    public static final int RESULT_SLOT = 1;
    private static final int INV_SLOT_START = 2;
    private static final int INV_SLOT_END = 29;
    private static final int USE_ROW_SLOT_START = 29;
    private static final int USE_ROW_SLOT_END = 38;

    private final ContainerLevelAccess access;
    private final DataSlot selectedRecipeIndex;
    private final Level level;
    private List<RecipeHolder<SawbenchRecipe>> recipesForInput;
    private List<ItemStack> clientResults = Collections.emptyList();
    private ItemStack input;
    private long lastSoundTime;
    public final Container container;
    private final ResultContainer resultContainer;
    public final Slot inputSlot;
    public final Slot resultSlot;
    private Runnable slotUpdateListener;

    public SawbenchMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public SawbenchMenu(int containerId, Inventory inventory, ContainerLevelAccess access) {
        super(UPMenuTypes.SAWBENCH_MENU, containerId);
        this.selectedRecipeIndex = DataSlot.standalone();
        this.recipesForInput = Collections.emptyList();
        this.input = ItemStack.EMPTY;
        this.slotUpdateListener = () -> {};
        this.resultContainer = new ResultContainer();
        this.access = access;
        this.level = inventory.player.level();

        this.container = new SimpleContainer(1) {
            {
                Objects.requireNonNull(SawbenchMenu.this);
            }

            @Override
            public void setChanged() {
                super.setChanged();
                SawbenchMenu.this.slotsChanged(this);
                SawbenchMenu.this.slotUpdateListener.run();
            }
        };

        this.inputSlot = this.addSlot(new Slot(this.container, 0, 20, 33) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(UPTags.Items.SAWBENCH_INPUTS);
            }
        });

        this.resultSlot = this.addSlot(new Slot(this.resultContainer, 1, 143, 33) {
            {
                Objects.requireNonNull(SawbenchMenu.this);
            }

            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack carried) {
                carried.onCraftedBy(player, carried.getCount());
                SawbenchMenu.this.resultContainer.awardUsedRecipes(player, getRelevantItems());
                ItemStack remaining = SawbenchMenu.this.inputSlot.remove(1);
                if (!remaining.isEmpty()) {
                    SawbenchMenu.this.setupResultSlot(SawbenchMenu.this.selectedRecipeIndex.get());
                }

                access.execute((level, pos) -> {
                    long gameTime = level.getGameTime();
                    if (SawbenchMenu.this.lastSoundTime != gameTime) {
                        level.playSound((Entity) null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, 1.0F);
                        SawbenchMenu.this.lastSoundTime = gameTime;
                    }
                });

                super.onTake(player, carried);
            }

            private List<ItemStack> getRelevantItems() {
                return List.of(SawbenchMenu.this.inputSlot.getItem());
            }
        });

        this.addStandardInventorySlots(inventory, 8, 84);
        this.addDataSlot(this.selectedRecipeIndex);
    }

    public int getSelectedRecipeIndex() { return this.selectedRecipeIndex.get(); }
    public List<RecipeHolder<SawbenchRecipe>> getVisibleRecipes() { return this.recipesForInput; }
    public int getNumberOfVisibleRecipes() { return this.clientResults.isEmpty() ? this.recipesForInput.size() : this.clientResults.size(); }
    public boolean hasInputItem() { return this.inputSlot.hasItem() && (!this.recipesForInput.isEmpty() || !this.clientResults.isEmpty()); }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, UPBlocks.SAWBENCH);
    }

    @Override
    public boolean clickMenuButton(Player player, int buttonId) {
        if (this.selectedRecipeIndex.get() == buttonId) return false;
        if (isValidRecipeIndex(buttonId)) {
            this.selectedRecipeIndex.set(buttonId);
            this.setupResultSlot(buttonId);
        }
        return true;
    }

    private boolean isValidRecipeIndex(int buttonId) {
        return buttonId >= 0 && buttonId < this.recipesForInput.size();
    }

    @Override
    public void slotsChanged(Container container) {
        ItemStack item = this.inputSlot.getItem();
        if (!item.is(this.input.getItem())) {
            this.input = item.copy();
            if (item.isEmpty()) {
                this.clientResults = Collections.emptyList();
                this.recipesForInput = Collections.emptyList();
                this.slotUpdateListener.run();
            }
            this.setupRecipeList(item);
        }
    }

    @SuppressWarnings("unchecked")
    private void setupRecipeList(ItemStack item) {
        this.selectedRecipeIndex.set(-1);
        this.resultSlot.set(ItemStack.EMPTY);
        if (item.isEmpty() || !(this.level instanceof ServerLevel serverLevel)) {
            this.recipesForInput = Collections.emptyList();
            return;
        }
        SingleRecipeInput recipeInput = new SingleRecipeInput(item);
        this.recipesForInput = serverLevel.recipeAccess().getRecipes().stream()
                .filter(h -> h.value().getType() == UPRecipes.SAWBENCH_TYPE)
                .map(h -> (RecipeHolder<SawbenchRecipe>) h)
                .filter(h -> h.value().matches(recipeInput, serverLevel))
                .collect(Collectors.toList());

        List<ItemStack> results = this.recipesForInput.stream()
                .map(h -> h.value().getResult())
                .collect(Collectors.toList());

        this.access.execute((level, pos) -> {
            level.players().forEach(p -> {
                if (p instanceof ServerPlayer sp && sp.containerMenu == this) {
                    ServerPlayNetworking.send(sp, new SawbenchRecipesPacket(results));
                }
            });
        });
    }

    private void setupResultSlot(int index) {
        if (!this.recipesForInput.isEmpty() && isValidRecipeIndex(index)) {
            RecipeHolder<SawbenchRecipe> holder = this.recipesForInput.get(index);
            this.resultContainer.setRecipeUsed(holder);
            this.resultSlot.set(holder.value().assemble(new SingleRecipeInput(this.container.getItem(0))));
        } else {
            this.resultSlot.set(ItemStack.EMPTY);
            this.resultContainer.setRecipeUsed(null);
        }
        this.broadcastChanges();
    }

    public List<ItemStack> getClientResults() { return this.clientResults; }

    public void setClientRecipes(List<ItemStack> results) {
        this.clientResults = results;
        this.recipesForInput = results.stream()
                .map(stack -> new RecipeHolder<>(
                        ResourceKey.create(
                                Registries.RECIPE,
                                Identifier.fromNamespaceAndPath("utilitiesplus", "client/" + UUID.randomUUID())
                        ),
                        new SawbenchRecipe(
                                Ingredient.of(Items.OAK_LOG),
                                new ItemStackTemplate(stack.getItem(), stack.getCount())
                        )
                ))
                .collect(Collectors.toList());
        this.slotUpdateListener.run();
    }

    public void registerUpdateListener(Runnable listener) { this.slotUpdateListener = listener; }

    @Override
    public boolean canTakeItemForPickAll(ItemStack carried, Slot target) {
        return target.container != this.resultContainer && super.canTakeItemForPickAll(carried, target);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        ItemStack clicked = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            Item item = stack.getItem();
            clicked = stack.copy();

            if (slotIndex == RESULT_SLOT) {
                item.onCraftedBy(stack, player);
                if (!this.moveItemStackTo(stack, INV_SLOT_START, USE_ROW_SLOT_END, true)) return ItemStack.EMPTY;
                slot.onQuickCraft(stack, clicked);
            } else if (slotIndex == INPUT_SLOT) {
                if (!this.moveItemStackTo(stack, INV_SLOT_START, USE_ROW_SLOT_END, false)) return ItemStack.EMPTY;
            } else if (slotIndex >= INV_SLOT_START && slotIndex < INV_SLOT_END) {
                if (!this.moveItemStackTo(stack, INPUT_SLOT, INPUT_SLOT + 1, false))
                    if (!this.moveItemStackTo(stack, USE_ROW_SLOT_START, USE_ROW_SLOT_END, false)) return ItemStack.EMPTY;
            } else if (slotIndex >= USE_ROW_SLOT_START && slotIndex < USE_ROW_SLOT_END) {
                if (!this.moveItemStackTo(stack, INPUT_SLOT, INPUT_SLOT + 1, false))
                    if (!this.moveItemStackTo(stack, INV_SLOT_START, INV_SLOT_END, false)) return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) slot.setByPlayer(ItemStack.EMPTY);
            slot.setChanged();
            if (stack.getCount() == clicked.getCount()) return ItemStack.EMPTY;
            slot.onTake(player, stack);
            if (slotIndex == RESULT_SLOT) player.drop(stack, false);
            this.broadcastChanges();
        }

        return clicked;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.resultContainer.removeItemNoUpdate(1);
        this.access.execute((level, pos) -> this.clearContainer(player, this.container));
    }
}