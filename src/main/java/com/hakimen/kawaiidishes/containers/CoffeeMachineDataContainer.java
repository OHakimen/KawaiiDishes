package com.hakimen.kawaiidishes.containers;

import com.hakimen.kawaiidishes.block_entities.CoffeeMachineBlockEntity;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public record CoffeeMachineDataContainer(CoffeeMachineBlockEntity blockEntity) implements Container {

    @Override
    public int getContainerSize() {
        return blockEntity.getInventory().getContainerSize();
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < blockEntity.getInventory().getContainerSize(); i++) {
            if (blockEntity.getInventory().getItem(i) != ItemStack.EMPTY) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int idx) {
        return blockEntity.getInventory().getItem(idx);
    }

    @Override
    public ItemStack removeItem(int idx, int count) {
        return blockEntity.getInventory().removeItem(idx, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int idx) {
        return blockEntity.getInventory().removeItemNoUpdate(idx);
    }

    @Override
    public void setItem(int idx, ItemStack stack) {
        blockEntity.getInventory().setItem(idx, stack);
    }

    @Override
    public void setChanged() {
        blockEntity.setChanged();
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }


    @Override
    public void clearContent() {

    }
}
