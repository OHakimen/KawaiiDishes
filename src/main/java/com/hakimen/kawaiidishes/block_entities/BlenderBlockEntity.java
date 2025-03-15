package com.hakimen.kawaiidishes.block_entities;

import com.hakimen.kawaiidishes.containers.BlenderContainer;
import com.hakimen.kawaiidishes.recipes.BlenderRecipe;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class BlenderBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, BlockEntityTicker<BlenderBlockEntity> {

    private final PropertyDelegate data;
    private final SimpleInventory inventory = new SimpleInventory(5);

    private int progress = 0;
    private int recipeTicks = 0;
    private boolean isCrafting = false;

    public BlenderBlockEntity(BlockPos pPos, BlockState pState) {
        super(BlockEntityRegister.BLENDER.get(), pPos, pState);

        this.data = new PropertyDelegate() {
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

            public int size() {
                return 2;
            }
        };
    }

    public static boolean hasRecipe(BlenderBlockEntity entity) {
        World level = entity.world;

        Optional<BlenderRecipe> match = level.getRecipeManager()
                .getFirstMatch(BlenderRecipe.Type.INSTANCE, entity.inventory, level);
        return match.isPresent();
    }

    @Override
    protected void writeNbt(NbtCompound pTag) {
        NbtList listTag = new NbtList();

        for(int i = 0; i < this.getInventory().size(); ++i) {
            ItemStack itemStack = this.getInventory().getStack(i);
            NbtCompound tag = new NbtCompound();
            tag.putInt("Slot", i);
            tag.put("Item", itemStack.writeNbt(new NbtCompound()));
            listTag.add(tag);
        }

        pTag.put("Items", listTag);
        pTag.putInt("Progress", progress);
        pTag.putInt("RecipeTicks", recipeTicks);
        pTag.putBoolean("IsCrafting", isCrafting);
        super.writeNbt(pTag);
    }

    @Override
    public void readNbt(NbtCompound pTag) {
        super.readNbt(pTag);
        progress = pTag.getInt("Progress");
        recipeTicks = pTag.getInt("RecipeTicks");
        isCrafting = pTag.getBoolean("IsCrafting");
        NbtList listTag = pTag.getList("Items", NbtElement.COMPOUND_TYPE);
        this.getInventory().clear();

        for(int i = 0; i < listTag.size(); ++i) {
            NbtCompound tag = listTag.getCompound(i);
            int slot = tag.getInt("Slot");
            ItemStack stack = ItemStack.fromNbt(tag.getCompound("Item"));
            this.getInventory().setStack(slot, stack);
        }
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("gui.kawaiidishes.blender");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int windowId, PlayerInventory inventory, PlayerEntity player) {
        return new BlenderContainer(windowId, inventory, this, data);
    }

    @Override
    public void tick(World pLevel, BlockPos pPos, BlockState pState, BlenderBlockEntity entity) {
        if (hasRecipe(entity)) {
            Optional<BlenderRecipe> match = world.getRecipeManager()
                    .getFirstMatch(BlenderRecipe.Type.INSTANCE, inventory, world);
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
                            ItemStack inventoryStack = entity.inventory.getStack(i);
                            var stack = entity.inventory.getStack(i).getItem().getRecipeRemainder();
                            boolean hasRemainder = stack != null;
                            if (inventoryStack.getCount() > 0 && !hasRemainder) {
                                entity.inventory.removeStack(i, 1);
                            } else if (hasRemainder) {
                                entity.inventory.setStack(i, stack == null ? ItemStack.EMPTY : stack.getDefaultStack());
                            }
                        }
                        ItemStack inventoryStack = entity.inventory.getStack(4);
                        if (inventoryStack.isEmpty()) {
                            entity.inventory.setStack(4, recipe.getOutput(null).copy());
                        } else if (inventoryStack.getItem().equals(recipe.getOutput(null).getItem())
                                && inventoryStack.getCount() < inventoryStack.getMaxCount()) {
                            entity.inventory.getStack(4).increment(1);
                        }
                    }
                }
            }
        } else {
            if (progress > 0) {
                progress--;
            }
        }
        markDirty();
    }


    public SimpleInventory getInventory() {
        return inventory;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(getPos());
    }
}

