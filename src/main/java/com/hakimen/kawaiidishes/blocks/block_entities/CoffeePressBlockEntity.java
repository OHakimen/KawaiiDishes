package com.hakimen.kawaiidishes.blocks.block_entities;

import com.hakimen.kawaiidishes.blocks.CoffeePressBlock;
import com.hakimen.kawaiidishes.recipes.CoffeePressRecipe;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Optional;

public class CoffeePressBlockEntity extends BlockEntity {

    public final ItemStackHandler inventory = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> inventory);

    public boolean coffeeGotMade;
    public ItemStack coffeeMade = ItemStack.EMPTY;
    public CoffeePressBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(BlockEntityRegister.coffeePress.get(), pWorldPosition, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.merge(this.inventory.serializeNBT());
        pTag.put("coffee",coffeeMade.serializeNBT());
        pTag.putBoolean("isDone",coffeeGotMade);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        this.inventory.deserializeNBT(pTag);
        coffeeMade.deserializeNBT(pTag.getCompound("coffee"));
        coffeeGotMade = pTag.getBoolean("isDone");
        super.load(pTag);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
            return (LazyOptional<T>) handler;
        }else{
            return super.getCapability(cap);
        }
    };
    public static boolean hasRecipe(CoffeePressBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.inventory.getSlots());
        for (int i = 0; i < entity.inventory.getSlots(); i++) {
            if(entity.inventory.getStackInSlot(i) != ItemStack.EMPTY){
                inventory.setItem(i, entity.inventory.getStackInSlot(i));
            }
        }
        Optional<CoffeePressRecipe> match = level.getRecipeManager()
                .getRecipeFor(CoffeePressRecipe.Type.INSTANCE, inventory, level);
        return match.isPresent();
    }

    public static void craft(CoffeePressBlockEntity entity){
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.inventory.getSlots());
        for (int i = 0; i < entity.inventory.getSlots(); i++) {
            inventory.setItem(i, entity.inventory.getStackInSlot(i));
        }
        Optional<CoffeePressRecipe> match = level.getRecipeManager()
                .getRecipeFor(CoffeePressRecipe.Type.INSTANCE, inventory, level);
        if(match.isPresent()) {
            for (int i = 0; i < entity.inventory.getSlots(); i++) {
                var stack = entity.inventory.getStackInSlot(i).getItem().getCraftingRemainingItem();
                if(stack == null){
                    entity.inventory.setStackInSlot(i,ItemStack.EMPTY);
                }else{
                    entity.level.addFreshEntity(new ItemEntity(entity.level,
                            entity.getBlockPos().getX(),
                            entity.getBlockPos().getY(),
                            entity.getBlockPos().getZ(),
                            stack.getDefaultInstance()));
                    entity.inventory.setStackInSlot(i,ItemStack.EMPTY);
                }

            }
            entity.coffeeMade = match.get().getResultItem(null);
            entity.coffeeGotMade = true;
            level.setBlockAndUpdate(entity.getBlockPos(),entity.getBlockState().setValue(
                    CoffeePressBlock.PRESSED,true
            ));
        }
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(3) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {

                return true;
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack)) {
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

}
