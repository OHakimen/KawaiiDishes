package com.hakimen.kawaiidishes.containers;

import com.hakimen.kawaiidishes.blocks.block_entities.CoffeeMachineBlockEntity;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.hakimen.kawaiidishes.registry.ContainerRegister;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;

public class CoffeeMachineContainer extends AbstractContainerMenu {
    public final CoffeeMachineBlockEntity blockEntity;
    private final IItemHandler playerInventory;
    private final ContainerData data;


    public CoffeeMachineContainer(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }

    public CoffeeMachineContainer(int windowId, Inventory inv, BlockEntity entity, ContainerData data) {

        super(ContainerRegister.coffeeMachine.get(),windowId);
        this.data = data;
        blockEntity = (CoffeeMachineBlockEntity)entity;
        this.playerInventory = new InvWrapper(inv.player.getInventory());

        if(blockEntity != null){
            blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                addSlot(new SlotItemHandler(h,0,26,18));
                addSlot(new SlotItemHandler(h,1,26,54));
                addSlot(new SlotItemHandler(h,2,62,18));
                addSlot(new SlotItemHandler(h,3,62,36));
                addSlot(new SlotItemHandler(h,4,62,54));
                addSlot(new SlotItemHandler(h,5,122,36));
            });
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
            if (index < 6 && !this.moveItemStackTo(stack1, 1, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
            if (!this.moveItemStackTo(stack1, 0, 6, false)) {
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
        int progressArrowSize = 20; // This is the height in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(pPlayer.level, blockEntity.getBlockPos()),
                pPlayer, BlockRegister.coffeeMachine.get());
    }
}
