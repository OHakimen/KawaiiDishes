package com.hakimen.kawaiidishes.containers;

import com.hakimen.kawaiidishes.block_entities.BlenderBlockEntity;
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
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.neoforged.neoforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;

public class BlenderContainer extends AbstractContainerMenu implements Container {
    public final BlenderBlockEntity blockEntity;
    private final IItemHandler playerInventory;
    private final ContainerData data;

    public BlenderContainer(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }

    public BlenderContainer(int windowId, Inventory inv, BlockEntity entity, ContainerData data) {

        super(ContainerRegister.BLENDER.get(),windowId);
        this.data = data;
        blockEntity = (BlenderBlockEntity)entity;
        this.playerInventory = new InvWrapper(inv);

        IItemHandler handler = entity.getLevel().getCapability(Capabilities.ItemHandler.BLOCK, entity.getBlockPos(), null);
        if(handler != null){
            addSlot(new SlotItemHandler(handler, 0, 53, 16));
            addSlot(new SlotItemHandler(handler, 1, 53, 34));
            addSlot(new SlotItemHandler(handler, 2, 53, 52));
            addSlot(new SlotItemHandler(handler, 3, 105, 16));
            addSlot(new SlotItemHandler(handler, 4, 105, 52));
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
            if (index < 5 && !this.moveItemStackTo(stack1,  5, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
            if (!this.moveItemStackTo(stack1, 0,  5, false)) {
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
        int progressArrowSize = 25; // This is the height in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    @Override
    public int getContainerSize() {
        return blockEntity.getInventory().getSlots();
    }

    @Override
    public boolean isEmpty() {

        for (int i = 0; i < blockEntity.getInventory().getSlots(); i++) {
            if(blockEntity.getInventory().getStackInSlot(i) != ItemStack.EMPTY){
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int idx) {
        return blockEntity.getInventory().getStackInSlot(idx);
    }

    @Override
    public ItemStack removeItem(int idx, int count) {
        return blockEntity.getInventory().extractItem(idx, count, false);
    }

    @Override
    public ItemStack removeItemNoUpdate(int idx) {
        return blockEntity.getInventory().extractItem(idx, 64, false);
    }

    @Override
    public void setItem(int idx, ItemStack stack) {
        blockEntity.getInventory().setStackInSlot(idx,stack);
    }

    @Override
    public void setChanged() {
        blockEntity.setChanged();
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(pPlayer.level(), blockEntity.getBlockPos()),
                pPlayer, BlockRegister.BLENDER.get());
    }

    public BlenderBlockEntity getBlockEntity() {
        return blockEntity;
    }

    public IItemHandler getPlayerInventory() {
        return playerInventory;
    }

    public ContainerData getData() {
        return data;
    }


    @Override
    public void clearContent() {

    }
}