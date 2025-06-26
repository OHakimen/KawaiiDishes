package com.hakimen.kawaiidishes.block_entities;

import com.hakimen.kawaiidishes.containers.BlenderContainer;
import com.hakimen.kawaiidishes.recipes.BlenderRecipe;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class BlenderBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, BlockEntityTicker<BlenderBlockEntity> {

    private final ContainerData data;
    private final SimpleContainer inventory = new SimpleContainer(5);

    private int progress = 0;
    private int recipeTicks = 0;
    private boolean isCrafting = false;

    public BlenderBlockEntity(BlockPos pPos, BlockState pState) {
        super(BlockEntityRegister.BLENDER.get(), pPos, pState);

        this.data = new ContainerData() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> BlenderBlockEntity.this.progress;
                    case 1 -> BlenderBlockEntity.this.recipeTicks;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> BlenderBlockEntity.this.progress = value;
                    case 1 -> BlenderBlockEntity.this.recipeTicks = value;
                }
            }

            public int getCount() {
                return 2;
            }
        };
    }

    public static boolean hasRecipe(BlenderBlockEntity entity) {
        Level level = entity.level;

        Optional<BlenderRecipe> match = level.getRecipeManager()
                .getRecipeFor(BlenderRecipe.Type.INSTANCE, entity.inventory, level);
        return match.isPresent();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        ListTag listTag = new ListTag();

        for(int i = 0; i < this.getInventory().getContainerSize(); ++i) {
            ItemStack itemStack = this.getInventory().getItem(i);
            CompoundTag tag = new CompoundTag();
            tag.putInt("Slot", i);
            tag.put("Item", itemStack.save(new CompoundTag()));
            listTag.add(tag);
        }

        pTag.put("Items", listTag);
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
        ListTag listTag = pTag.getList("Items", Tag.TAG_COMPOUND);
        this.getInventory().clearContent();

        for(int i = 0; i < listTag.size(); ++i) {
            CompoundTag tag = listTag.getCompound(i);
            int slot = tag.getInt("Slot");
            ItemStack stack = ItemStack.of(tag.getCompound("Item"));
            this.getInventory().setItem(slot, stack);
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("gui.kawaiidishes.blender");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory inventory, Player player) {
        return new BlenderContainer(windowId, inventory, this, data);
    }

    @Override
    public void tick(Level pLevel, BlockPos pPos, BlockState pState, BlenderBlockEntity entity) {
        if (hasRecipe(entity)) {
            Optional<BlenderRecipe> match = level.getRecipeManager()
                    .getRecipeFor(BlenderRecipe.Type.INSTANCE, inventory, level);
            if (match.isPresent()) {
                BlenderRecipe recipe = match.get();
                if (!isCrafting) {
                    isCrafting = true;
                    recipeTicks = recipe.getTicks();
                } else {
                    this.progress++;
                    if (progress >= recipeTicks) {
                        isCrafting = false;
                        progress = 0;
                        for (int i = 0; i < 4; i++) {
                            ItemStack inventoryStack = entity.inventory.getItem(i);
                            var stack = entity.inventory.getItem(i).getItem().getCraftingRemainingItem();
                            boolean hasRemainder = stack != null;
                            if (inventoryStack.getCount() > 0 && !hasRemainder) {
                                entity.inventory.removeItem(i, 1);
                            } else if (hasRemainder) {
                                entity.inventory.setItem(i, stack == null ? ItemStack.EMPTY : stack.getDefaultInstance());
                            }
                        }
                        ItemStack inventoryStack = entity.inventory.getItem(4);
                        if (inventoryStack.isEmpty()) {
                            entity.inventory.setItem(4, recipe.getResultItem(null).copy());
                        } else if (inventoryStack.getItem().equals(recipe.getResultItem(null).getItem())
                                && inventoryStack.getCount() < inventoryStack.getMaxStackSize()) {
                            entity.inventory.getItem(4).grow(1);
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


    public SimpleContainer getInventory() {
        return inventory;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayer player, FriendlyByteBuf buf) {
        buf.writeBlockPos(getBlockPos());
    }
}

