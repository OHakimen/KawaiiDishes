package com.hakimen.kawaiidishes.block_entities;

import com.hakimen.kawaiidishes.containers.IceCreamMakerContainer;
import com.hakimen.kawaiidishes.recipes.IceCreamMakerRecipe;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.SlottedStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

public class IceCreamMakerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, BlockEntityTicker<IceCreamMakerBlockEntity> {

    private final PropertyDelegate data;
    private final SimpleInventory inventory = new SimpleInventory(6){
        @Override
        public boolean isValid(int slot, ItemStack stack) {
            return slot != 0 || stack.getItem() == Items.SNOWBALL;
        }
    };

    public SimpleInventory getInventory() {
        return inventory;
    }

    public SlottedStorage<ItemVariant> getStorage() {
        return InventoryStorage.of(inventory, null);
    }
    private int progress = 0;
    private int recipeTicks = 0;
    private boolean isCrafting = false;


    public IceCreamMakerBlockEntity(BlockPos pPos, BlockState pState) {
        super(BlockEntityRegister.ICE_CREAM_MAKER.get(), pPos, pState);
        this.data = new PropertyDelegate() {
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

            public int size() {
                return 2;
            }
        };
    }

    public static boolean hasRecipe(IceCreamMakerBlockEntity entity) {
        World level = entity.world;

        Optional<IceCreamMakerRecipe> match = level.getRecipeManager()
                .getFirstMatch(IceCreamMakerRecipe.Type.INSTANCE, entity.inventory, level);
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
        return Text.translatable("gui.kawaiidishes.ice_cream_maker");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int windowId, PlayerInventory inventory, PlayerEntity player) {
        return new IceCreamMakerContainer(windowId, inventory, this, data);
    }

    @Override
    public void tick(World pLevel, BlockPos pPos, BlockState pState, IceCreamMakerBlockEntity entity) {
        if (hasRecipe(entity)) {
            Optional<IceCreamMakerRecipe> match = world.getRecipeManager()
                    .getFirstMatch(IceCreamMakerRecipe.Type.INSTANCE, entity.inventory, world);
            if (match.isPresent()) {
                IceCreamMakerRecipe recipe = match.get();
                if (!isCrafting) {
                    isCrafting = true;
                    recipeTicks = recipe.getTicks();
                } else {
                    this.progress++;
                    if (progress >= recipeTicks) {
                        isCrafting = false;
                        progress = 0;
                        for (int i = 1; i < 5; i++) {
                            ItemStack inventoryStack = entity.inventory.getStack(i);
                            var stack = entity.inventory.getStack(i).getItem().getRecipeRemainder();
                            boolean hasRemainder = stack != null;
                            if (inventoryStack.getCount() > 0 && !hasRemainder) {
                                entity.inventory.removeStack(i, 1);
                            } else if (hasRemainder) {
                                entity.inventory.setStack(i, stack == null ? ItemStack.EMPTY : stack.getDefaultStack());
                            }
                        }
                        if(entity.inventory.getStack(0).getCount() >= recipe.getSnowballs()) {
                            entity.inventory.removeStack(0,recipe.getSnowballs());
                        }
                        ItemStack inventoryStack = entity.inventory.getStack(5);
                        if (inventoryStack.isEmpty()) {
                            entity.inventory.setStack(5, recipe.getOutput(null).copy());
                        } else if (inventoryStack.getItem().equals(recipe.getOutput(null).getItem())
                                && inventoryStack.getCount() < inventoryStack.getMaxCount()) {
                            entity.inventory.getStack(5).increment(1);
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


    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(getPos());
    }
}
