package com.hakimen.kawaiidishes.containers;

import com.hakimen.kawaiidishes.block_entities.DisplayCaseBlockEntity;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.hakimen.kawaiidishes.registry.ContainerRegister;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.level.block.entity.BlockEntity;

public class DisplayCaseContainer extends AbstractContainerMenu{
    public final DisplayCaseBlockEntity blockEntity;
    private final Inventory playerInventory;

    public DisplayCaseContainer(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public DisplayCaseContainer(int windowId, Inventory inv, BlockEntity entity) {

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



    private int addSlotRange(Container handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new Slot(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(Container handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
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
    public ItemStack quickMoveStack(Player player, int index )
    {
        int size = 8;
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack1 = slot.getItem();
            stack = stack1.copy();
            if (index < size && !this.moveItemStackTo(stack1, size, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
            if (!this.moveItemStackTo(stack1, 0, size, false)) {
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

    @Override
    public boolean stillValid(Player player) {
        return true;
    }


    class FoodSlot extends Slot{

        public FoodSlot(Container container, int i, int j, int k) {
            super(container, i, j, k);
        }

        @Override
        public boolean mayPlace(ItemStack itemStack) {
            return itemStack.isEdible() || itemStack.getItem() instanceof PotionItem;
        }
    }
}