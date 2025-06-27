package com.hakimen.kawaiidishes.containers;

import com.hakimen.kawaiidishes.block_entities.CoffeeMachineBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

public record CoffeeMachineDataContainer(CoffeeMachineBlockEntity blockEntity) implements Inventory {

    @Override
    public int size() {
        return blockEntity.getInventory().size();
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < blockEntity.getInventory().size(); i++) {
            if (blockEntity.getInventory().getStack(i) != ItemStack.EMPTY) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStack(int idx) {
        return blockEntity.getInventory().getStack(idx);
    }

    @Override
    public ItemStack removeStack(int idx, int count) {
        return blockEntity.getInventory().removeStack(idx, count);
    }

    @Override
    public ItemStack removeStack(int idx) {
        return blockEntity.getInventory().removeStack(idx);
    }

    @Override
    public void setStack(int idx, ItemStack stack) {
        blockEntity.getInventory().setStack(idx, stack);
    }

    @Override
    public void markDirty() {
        blockEntity.markDirty();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity pPlayer) {
        return true;
    }


    @Override
    public void clear() {

    }
}
