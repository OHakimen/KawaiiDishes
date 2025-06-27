package com.hakimen.kawaiidishes.containers;

import com.hakimen.kawaiidishes.block_entities.CoffeeMachineBlockEntity;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.hakimen.kawaiidishes.registry.ContainerRegister;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class CoffeeMachineContainer extends ScreenHandler{
    public final CoffeeMachineBlockEntity blockEntity;
    private final PlayerInventory playerInventory;
    private final PropertyDelegate data;
    private FluidVariant fluidVariant;
    private long amount;

    public CoffeeMachineContainer(int pContainerId, PlayerInventory inv, PacketByteBuf extraData) {
        this(pContainerId, inv, inv.player.getWorld().getBlockEntity(extraData.readBlockPos()), new ArrayPropertyDelegate(2));
    }

    public CoffeeMachineContainer(int windowId, PlayerInventory inv, BlockEntity entity, PropertyDelegate data) {

        super(ContainerRegister.COFFEE_MACHINE.get(),windowId);
        this.data = data;
        blockEntity = (CoffeeMachineBlockEntity)entity;
        this.playerInventory = inv;
        this.fluidVariant = blockEntity.getWaterTank().variant;

        addSlot(new Slot(blockEntity.getInventory(), 0, 26, 18));
        addSlot(new Slot(blockEntity.getInventory(), 1, 26, 54));
        addSlot(new Slot(blockEntity.getInventory(), 2, 80, 18));
        addSlot(new Slot(blockEntity.getInventory(), 3, 80, 36));
        addSlot(new Slot(blockEntity.getInventory(), 4, 80, 54));
        addSlot(new Slot(blockEntity.getInventory(), 5, 134, 18));
        addSlot(new Slot(blockEntity.getInventory(), 6, 134, 54));

        layoutPlayerInventorySlots(8,86);

        addProperties(data);
    }



    private int addSlotRange(Inventory handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new Slot(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(Inventory handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
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

    @Override
    public ItemStack quickMove(PlayerEntity player, int index )
    {
        ItemStack stack;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();
            if (index < 7 && !this.insertItem(stack1, 7, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
            if (!this.insertItem(stack1, 2, 7, false)) {
                return ItemStack.EMPTY;
            }
            if (stack1.isEmpty()) {
                slot.canInsert(ItemStack.EMPTY);
            }
            if (stack1.getCount() == stack.getCount()) {
                return ItemStack.EMPTY;
            } else {
                slot.markDirty();
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1); // Max Progress
        int progressArrowSize = 68; // This is the height in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }



    public CoffeeMachineBlockEntity getBlockEntity() {
        return blockEntity;
    }

    public PlayerInventory getPlayerInventory() {
        return playerInventory;
    }

    public PropertyDelegate getData() {
        return data;
    }

    public FluidVariant getFluidVariant() {
        return fluidVariant;
    }

    public void setFluidVariant(FluidVariant fluidVariant) {
        this.fluidVariant = this.fluidVariant;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

}