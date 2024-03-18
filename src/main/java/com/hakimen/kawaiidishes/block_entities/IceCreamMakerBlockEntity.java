package com.hakimen.kawaiidishes.block_entities;

import com.hakimen.kawaiidishes.block.IceCreamMakerBlock;
import com.hakimen.kawaiidishes.containers.IceCreamMakerContainer;
import com.hakimen.kawaiidishes.recipes.IceCreamMakerRecipe;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiPredicate;

public class IceCreamMakerBlockEntity extends BlockEntity implements MenuProvider, BlockEntityTicker<IceCreamMakerBlockEntity> {

    private final ContainerData data;
    private final ItemStackHandler inventory = createHandler();
    private int progress = 0;
    private int recipeTicks = 0;
    private boolean isCrafting = false;


    public IceCreamMakerBlockEntity(BlockPos pPos, BlockState pState) {
        super(BlockEntityRegister.ICE_CREAM_MAKER.get(), pPos, pState);
        this.data = new ContainerData() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> IceCreamMakerBlockEntity.this.progress;
                    case 1 -> IceCreamMakerBlockEntity.this.recipeTicks;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> IceCreamMakerBlockEntity.this.progress = value;
                    case 1 -> IceCreamMakerBlockEntity.this.recipeTicks = value;
                }
            }

            public int getCount() {
                return 2;
            }
        };
    }

    public static boolean hasRecipe(IceCreamMakerBlockEntity entity) {
        Level level = entity.level;

        SimpleContainer inventory = new SimpleContainer(entity.inventory.getSlots());
        for (int i = 0; i < entity.inventory.getSlots(); i++) {
            inventory.setItem(i, entity.inventory.getStackInSlot(i));
        }


        Optional<RecipeHolder<IceCreamMakerRecipe>> match = level.getRecipeManager()
                .getRecipeFor(IceCreamMakerRecipe.Type.INSTANCE, inventory, level);
        return match.isPresent();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.merge(this.inventory.serializeNBT());
        pTag.putInt("Progress", progress);
        pTag.putInt("RecipeTicks", recipeTicks);
        pTag.putBoolean("IsCrafting", isCrafting);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        progress = pTag.getInt("Progress");
        recipeTicks = pTag.getInt("RecipeTicks");
        isCrafting = pTag.getBoolean("IsCrafting");
        inventory.deserializeNBT(pTag);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("gui.kawaiidishes.ice_cream_maker");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory inventory, Player player) {
        return new IceCreamMakerContainer(windowId, inventory, this, data);
    }

    @Override
    public void tick(Level pLevel, BlockPos pPos, BlockState pState, IceCreamMakerBlockEntity entity) {
        if (hasRecipe(entity)) {
            SimpleContainer inventory = new SimpleContainer(entity.inventory.getSlots());
            for (int i = 0; i < entity.inventory.getSlots(); i++) {
                inventory.setItem(i, entity.inventory.getStackInSlot(i));
            }
            Optional<RecipeHolder<IceCreamMakerRecipe>> match = level.getRecipeManager()
                    .getRecipeFor(IceCreamMakerRecipe.Type.INSTANCE, inventory, level);
            if (match.isPresent()) {
                IceCreamMakerRecipe recipe = match.get().value();
                if (!isCrafting) {
                    isCrafting = true;
                    recipeTicks = recipe.getTicks();
                } else {
                    this.progress++;
                    if (progress >= recipeTicks) {
                        isCrafting = false;
                        progress = 0;
                        for (int i = 1; i < 5; i++) {
                            ItemStack inventoryStack = entity.inventory.getStackInSlot(i);
                            var stack = entity.inventory.getStackInSlot(i).getItem().getCraftingRemainingItem();
                            boolean hasRemainder = stack != null;
                            if (inventoryStack.getCount() > 0 && !hasRemainder) {
                                entity.inventory.extractItem(i, 1, false);
                            } else if (hasRemainder) {
                                entity.inventory.setStackInSlot(i, stack == null ? ItemStack.EMPTY : stack.getDefaultInstance());
                            }
                        }
                        if(entity.inventory.getStackInSlot(0).getCount() >= recipe.getSnowballs()) {
                            entity.inventory.extractItem(0,recipe.getSnowballs(), false);
                        }
                        ItemStack inventoryStack = entity.inventory.getStackInSlot(5);
                        if (inventoryStack.isEmpty()) {
                            entity.inventory.setStackInSlot(5, recipe.getResultItem(null).copy());
                        } else if (inventoryStack.getItem().equals(recipe.getResultItem(null).getItem())
                                && inventoryStack.getCount() < inventoryStack.getMaxStackSize()) {
                            entity.inventory.insertItem(5, recipe.getResultItem(null).copy(), false);
                        }
                    }
                }
            }
        } else {
            if (progress > 0) {
                progress--;
            }
        }
        setChanged();
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(6) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return slot != 0 || stack.getItem() == Items.SNOWBALL;
            }


            @Override
            public int getSlotLimit(int slot) {
                return 64;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!isItemValid(slot, stack)) {
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

}
