package com.hakimen.kawaiidishes.containers;

import com.hakimen.kawaiidishes.block_entities.BlenderBlockEntity;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.hakimen.kawaiidishes.registry.ContainerRegister;
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


public class BlenderContainer extends ScreenHandler {
    public final BlenderBlockEntity blockEntity;
    private final PlayerInventory playerInventory;
    private final PropertyDelegate data;

    public BlenderContainer(int pContainerId, PlayerInventory inv, PacketByteBuf extraData) {
        this(pContainerId, inv, inv.player.getWorld().getBlockEntity(extraData.readBlockPos()), new ArrayPropertyDelegate(2));
    }

    public BlenderContainer(int windowId, PlayerInventory inv, BlockEntity entity, PropertyDelegate data) {

        super(ContainerRegister.BLENDER.get(),windowId);
        this.data = data;
        blockEntity = (BlenderBlockEntity)entity;
        this.playerInventory = inv;

        addSlot(new Slot(blockEntity.getInventory(), 0, 53, 16));
        addSlot(new Slot(blockEntity.getInventory(), 1, 53, 34));
        addSlot(new Slot(blockEntity.getInventory(), 2, 53, 52));
        addSlot(new Slot(blockEntity.getInventory(), 3, 105, 16));
        addSlot(new Slot(blockEntity.getInventory(), 4, 105, 52));

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
    public ItemStack quickMove( PlayerEntity player, int index )
    {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();
            if (index < 5 && !this.insertItem(stack1,  5, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
            if (!this.insertItem(stack1, 0,  5, false)) {
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
        return stack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);  // Max Progress
        int progressArrowSize = 25; // This is the height in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public BlenderBlockEntity getBlockEntity() {
        return blockEntity;
    }

    public PlayerInventory getPlayerInventory() {
        return playerInventory;
    }

    public PropertyDelegate getData() {
        return data;
    }

}