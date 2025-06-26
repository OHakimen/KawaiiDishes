package com.hakimen.kawaiidishes.containers;

import com.hakimen.kawaiidishes.block_entities.IceCreamMakerBlockEntity;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.hakimen.kawaiidishes.registry.ContainerRegister;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;

public class IceCreamMakerContainer extends AbstractContainerMenu {
    public final IceCreamMakerBlockEntity blockEntity;
    private final Inventory playerInventory;
    private final ContainerData data;

    public IceCreamMakerContainer(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }

    public IceCreamMakerContainer(int windowId, Inventory inv, BlockEntity entity, ContainerData data) {

        super(ContainerRegister.ICE_CREAM_MAKER.get(),windowId);
        this.data = data;
        blockEntity = (IceCreamMakerBlockEntity)entity;
        this.playerInventory = inv;

        addSlot(new IceCreamMakerSlot(blockEntity.getInventory(), 0, 26, 18));
        addSlot(new IceCreamMakerSlot(blockEntity.getInventory(), 1, 62, 18));
        addSlot(new IceCreamMakerSlot(blockEntity.getInventory(), 2, 62, 36));
        addSlot(new IceCreamMakerSlot(blockEntity.getInventory(), 3, 62, 54));
        addSlot(new IceCreamMakerSlot(blockEntity.getInventory(), 4, 114, 18));
        addSlot(new IceCreamMakerSlot(blockEntity.getInventory(), 5, 114, 54));
        layoutPlayerInventorySlots(8,86);

        addDataSlots(data);
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
    public ItemStack quickMoveStack( Player player, int index )
    {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack1 = slot.getItem();
            stack = stack1.copy();
            if (index < 6 && !this.moveItemStackTo(stack1,  6, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
            if (!this.moveItemStackTo(stack1, 0,  6, false)) {
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

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);  // Max Progress
        int progressArrowSize = 60; // This is the height in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public IceCreamMakerBlockEntity getBlockEntity() {
        return blockEntity;
    }

    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    public ContainerData getData() {
        return data;
    }


    class IceCreamMakerSlot extends Slot {

        public IceCreamMakerSlot(Container container, int i, int j, int k) {
            super(container, i, j, k);
        }

        @Override
        public boolean mayPlace(ItemStack itemStack) {
            return index != 0 || itemStack.getItem() == Items.SNOWBALL;
        }
    }
}