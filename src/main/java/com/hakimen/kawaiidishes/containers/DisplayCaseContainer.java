package com.hakimen.kawaiidishes.containers;

import com.hakimen.kawaiidishes.block_entities.DisplayCaseBlockEntity;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.hakimen.kawaiidishes.registry.ContainerRegister;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class DisplayCaseContainer extends ScreenHandler{
    public final DisplayCaseBlockEntity blockEntity;
    private final PlayerInventory playerInventory;

    public DisplayCaseContainer(int pContainerId, PlayerInventory inv, PacketByteBuf extraData) {
        this(pContainerId, inv, inv.player.getWorld().getBlockEntity(extraData.readBlockPos()));
    }

    public DisplayCaseContainer(int windowId, PlayerInventory inv, BlockEntity entity) {

        super(ContainerRegister.DISPLAY_CASE.get(),windowId);
        blockEntity = (DisplayCaseBlockEntity)entity;
        this.playerInventory = inv;

        for (int i = 0; i < 8; i++) {
            if(i < 4){
                addSlot(new FoodSlot(blockEntity.getInventory(), i, 52 + (i*18), 23));
            }else{
                addSlot(new FoodSlot(blockEntity.getInventory(), i, 52 + ((i %  4)*18), 41));
            }
        }
        layoutPlayerInventorySlots(8,86);
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
        int size = 8;
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();
            if (index < size && !this.insertItem(stack1, size, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
            if (!this.insertItem(stack1, 0, size, false)) {
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


    class FoodSlot extends Slot{

        public FoodSlot(Inventory container, int i, int j, int k) {
            super(container, i, j, k);
        }

        @Override
        public boolean canInsert(ItemStack itemStack) {
            return itemStack.isFood() || itemStack.getItem() instanceof PotionItem;
        }
    }
}