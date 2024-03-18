package com.hakimen.kawaiidishes.containers;

import com.hakimen.kawaiidishes.block_entities.CoffeeMachineBlockEntity;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.hakimen.kawaiidishes.registry.ContainerRegister;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.neoforged.neoforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;

public class CoffeeMachineContainer extends AbstractContainerMenu {
    public final CoffeeMachineBlockEntity blockEntity;
    private final IItemHandler playerInventory;
    private final ContainerData data;
    private FluidStack fluidStack;

    public CoffeeMachineContainer(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }

    public CoffeeMachineContainer(int windowId, Inventory inv, BlockEntity entity, ContainerData data) {

        super(ContainerRegister.COFFEE_MACHINE.get(),windowId);
        this.data = data;
        blockEntity = (CoffeeMachineBlockEntity)entity;
        this.playerInventory = new InvWrapper(inv);
        this.fluidStack = blockEntity.getFluidStack();

        IItemHandler handler = entity.getLevel().getCapability(Capabilities.ItemHandler.BLOCK, entity.getBlockPos(), null);

        if(handler != null){
            addSlot(new SlotItemHandler(handler, 0, 26, 18));
            addSlot(new SlotItemHandler(handler, 1, 26, 54));
            addSlot(new SlotItemHandler(handler, 2, 80, 18));
            addSlot(new SlotItemHandler(handler, 3, 80, 36));
            addSlot(new SlotItemHandler(handler, 4, 80, 54));
            addSlot(new SlotItemHandler(handler, 5, 134, 18));
            addSlot(new SlotItemHandler(handler, 6, 134, 54));
        }
        layoutPlayerInventorySlots(8,86);

        addDataSlots(data);
    }



    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

    @Nonnull
    @Override
    public ItemStack quickMoveStack( @Nonnull Player player, int index )
    {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack1 = slot.getItem();
            stack = stack1.copy();
            if (index < 7 && !this.moveItemStackTo(stack1, 7, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
            if (!this.moveItemStackTo(stack1, 0, 7, false)) {
                return ItemStack.EMPTY;
            }
            if (stack1.isEmpty()) {
                slot.mayPlace(ItemStack.EMPTY);
            }
            if (stack1.getCount() == stack.getCount()) {
                return ItemStack.EMPTY;
            } else {
                slot.setChanged();
            }
        }
        return stack;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);  // Max Progress
        int progressArrowSize = 68; // This is the height in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(pPlayer.level(), blockEntity.getBlockPos()),
                pPlayer, BlockRegister.COFFEE_MACHINE.get());
    }

    public CoffeeMachineBlockEntity getBlockEntity() {
        return blockEntity;
    }

    public IItemHandler getPlayerInventory() {
        return playerInventory;
    }

    public ContainerData getData() {
        return data;
    }

    public FluidStack getFluidStack() {
        return fluidStack;
    }

    public void setFluidStack(FluidStack fluidStack) {
        this.fluidStack = fluidStack;
    }

}