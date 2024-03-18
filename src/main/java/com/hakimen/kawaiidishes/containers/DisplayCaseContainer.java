package com.hakimen.kawaiidishes.containers;

import com.hakimen.kawaiidishes.block_entities.BlenderBlockEntity;
import com.hakimen.kawaiidishes.block_entities.DisplayCaseBlockEntity;
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

public class DisplayCaseContainer extends AbstractContainerMenu {
    public final DisplayCaseBlockEntity blockEntity;
    private final IItemHandler playerInventory;

    public DisplayCaseContainer(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public DisplayCaseContainer(int windowId, Inventory inv, BlockEntity entity) {

        super(ContainerRegister.DISPLAY_CASE.get(),windowId);
        blockEntity = (DisplayCaseBlockEntity)entity;
        this.playerInventory = new InvWrapper(inv);

        IItemHandler handler = entity.getLevel().getCapability(Capabilities.ItemHandler.BLOCK, entity.getBlockPos(), null);
        if(handler != null){
            for (int i = 0; i < 8; i++) {
                if(i < 4){
                    addSlot(new SlotItemHandler(handler, i, 52 + (i*18), 23));
                }else{
                    addSlot(new SlotItemHandler(handler, i, 52 + ((i %  4)*18), 41));
                }
            }
        }
        layoutPlayerInventorySlots(8,86);
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
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(pPlayer.level(), blockEntity.getBlockPos()),
                pPlayer, BlockRegister.DISPLAY_CASE.get());
    }

    public DisplayCaseBlockEntity getBlockEntity() {
        return blockEntity;
    }

    public IItemHandler getPlayerInventory() {
        return playerInventory;
    }

}