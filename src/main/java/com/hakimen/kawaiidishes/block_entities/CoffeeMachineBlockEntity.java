package com.hakimen.kawaiidishes.block_entities;

import com.hakimen.kawaiidishes.containers.CoffeeMachineContainer;
import com.hakimen.kawaiidishes.containers.CoffeeMachineDataContainer;
import com.hakimen.kawaiidishes.networking.FluidSyncS2CPacket;
import com.hakimen.kawaiidishes.recipes.CoffeeMachineRecipe;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import com.hakimen.kawaiidishes.registry.PacketRegister;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.configuration.ICustomConfigurationTask;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class CoffeeMachineBlockEntity extends BlockEntity implements MenuProvider, BlockEntityTicker<CoffeeMachineBlockEntity> {

    private final ItemStackHandler inventory = createHandler();
    private final FluidTank waterTank = new FluidTank(FluidType.BUCKET_VOLUME * 4) {
        @Override
        protected void onContentsChanged() {
            super.onContentsChanged();
            if (!level.isClientSide()) {
                PacketDistributor.ALL.noArg().send(new FluidSyncS2CPacket(this.fluid, worldPosition));
            }
        }

        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid() == Fluids.WATER;
        }
    };

    private final ContainerData data;
    private int progress = 0;
    private int recipeTicks = 0;
    private boolean isCrafting = false;

    public CoffeeMachineBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegister.COFFEE_MACHINE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0:
                        return CoffeeMachineBlockEntity.this.progress;
                    case 1:
                        return CoffeeMachineBlockEntity.this.recipeTicks;
                    default:
                        return 0;
                }
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        CoffeeMachineBlockEntity.this.progress = value;
                        break;
                    case 1:
                        CoffeeMachineBlockEntity.this.recipeTicks = value;
                        break;
                }
            }

            public int getCount() {
                return 2;
            }
        };
    }

    public static boolean hasRecipe(CoffeeMachineBlockEntity entity) {
        Level level = entity.level;
        CoffeeMachineDataContainer coffeeMachineContainer = new CoffeeMachineDataContainer(entity);
        Optional<RecipeHolder<CoffeeMachineRecipe>> match = level.getRecipeManager()
                .getRecipeFor(CoffeeMachineRecipe.Type.INSTANCE, coffeeMachineContainer, level);
        return match.isPresent();
    }

    private static void transferItemFluidToFluidTank(CoffeeMachineBlockEntity pEntity) {
        IFluidHandlerItem handler = pEntity.inventory.getStackInSlot(0).getCapability(Capabilities.FluidHandler.ITEM);
        if(handler != null){
            int drainAmount = Math.min(pEntity.waterTank.getSpace(), 1000);

            FluidStack stack = handler.drain(drainAmount, IFluidHandler.FluidAction.SIMULATE);
            if (pEntity.waterTank.isFluidValid(stack)) {

                stack = handler.drain(drainAmount, IFluidHandler.FluidAction.EXECUTE);
                pEntity.waterTank.fill(stack, IFluidHandler.FluidAction.EXECUTE);

                pEntity.inventory.extractItem(0, 1, false);
                pEntity.inventory.insertItem(0, handler.getContainer(), false);

                pEntity.setChanged();
            }
        }
    }

    private static void transferFluidToItemFluid(CoffeeMachineBlockEntity pEntity) {
        IFluidHandlerItem handler = pEntity.inventory.getStackInSlot(1).getCapability(Capabilities.FluidHandler.ITEM);
        if(handler != null){
            int drainAmount = 1000;

            FluidStack stack = pEntity.waterTank.drain(drainAmount, IFluidHandler.FluidAction.SIMULATE);
            if (handler.isFluidValid(0, stack) && handler.getFluidInTank(0).isEmpty()) {

                handler.fill(stack, IFluidHandler.FluidAction.EXECUTE);
                pEntity.waterTank.drain(drainAmount, IFluidHandler.FluidAction.EXECUTE);

                pEntity.inventory.extractItem(1, 1, false);
                pEntity.inventory.insertItem(1, handler.getContainer(), false);

                pEntity.setChanged();
            }
        }
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }


    public int getProgress() {
        return progress;
    }

    public int getRecipeTicks() {
        return recipeTicks;
    }

    public ContainerData getData() {
        return data;
    }

    public boolean isCrafting() {
        return isCrafting;
    }

    public void setFluid(FluidStack stack) {
        waterTank.setFluid(stack);
    }

    public FluidStack getFluidStack() {
        return waterTank.getFluid();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.merge(this.inventory.serializeNBT());
        waterTank.writeToNBT(pTag);
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
        waterTank.readFromNBT(pTag);
    }


    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this, BlockEntity::saveWithFullMetadata);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithFullMetadata();
    }

    @Override
    public void tick(Level pLevel, BlockPos pPos, BlockState pState, CoffeeMachineBlockEntity pBlockEntity) {
        if (hasRecipe(pBlockEntity)) {
            CoffeeMachineDataContainer coffeeMachineContainer = new CoffeeMachineDataContainer(pBlockEntity);
            Optional<RecipeHolder<CoffeeMachineRecipe>> match = level.getRecipeManager()
                    .getRecipeFor(CoffeeMachineRecipe.Type.INSTANCE, coffeeMachineContainer, level);
            if (match.isPresent()) {
                CoffeeMachineRecipe recipe = match.get().value();
                if (!isCrafting) {
                    isCrafting = true;
                    recipeTicks = recipe.getTicks();
                } else {
                    this.progress++;
                    if (progress >= recipeTicks) {
                        isCrafting = false;
                        progress = 0;
                        pBlockEntity.waterTank.drain(recipe.getWaterNeeded(), IFluidHandler.FluidAction.EXECUTE);
                        for (int i = 2; i < 6; i++) {
                            ItemStack inventoryStack = pBlockEntity.inventory.getStackInSlot(i);
                            var stack = pBlockEntity.inventory.getStackInSlot(i).getItem().getCraftingRemainingItem();
                            boolean hasRemainder = stack != null;
                            if(inventoryStack.getCount() > 0 && !hasRemainder) {
                                pBlockEntity.inventory.extractItem(i,1, false);
                            }else if(hasRemainder){
                                pBlockEntity.inventory.setStackInSlot(i, stack == null ? ItemStack.EMPTY : stack.getDefaultInstance());
                            }
                        }
                        ItemStack inventoryStack = pBlockEntity.inventory.getStackInSlot(6);
                        if(inventoryStack.isEmpty()){
                            pBlockEntity.inventory.setStackInSlot(6, recipe.getResultItem(null));
                        }else if(inventoryStack.getItem().equals(recipe.getResultItem(null).getItem())
                                && inventoryStack.getCount() <= inventoryStack.getMaxStackSize()){
                            pBlockEntity.inventory.insertItem(6, recipe.getResultItem(null), false);
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


        if (hasFluidInSlot(pBlockEntity)) {
            transferItemFluidToFluidTank(pBlockEntity);
        }

        if (hasTankInExtractSlot(pBlockEntity)) {
            transferFluidToItemFluid(pBlockEntity);
        }
    }

    public boolean hasFluidInSlot(CoffeeMachineBlockEntity entity) {
        return entity.inventory.getStackInSlot(0).getCount() > 0;
    }

    public boolean hasTankInExtractSlot(CoffeeMachineBlockEntity entity) {
        return entity.inventory.getStackInSlot(1).getCount() > 0;
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(7) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                switch (slot) {
                    case 0, 1 -> {
                        return stack.getCapability(Capabilities.FluidHandler.ITEM) != null;
                    }
                }
                return true;
            }



            @Override
            public int getSlotLimit(int slot) {
                switch (slot) {
                    case 0, 1 -> {
                        return 1;
                    }
                }
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

    public FluidTank getWaterTank() {
        return waterTank;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("gui.kawaiidishes.coffee_machine");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new CoffeeMachineContainer(pContainerId, pInventory, this, this.getData());
    }
}
