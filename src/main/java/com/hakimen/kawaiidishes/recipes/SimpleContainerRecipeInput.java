package com.hakimen.kawaiidishes.recipes;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public class SimpleContainerRecipeInput implements RecipeInput {

    SimpleContainer container;

    public SimpleContainerRecipeInput(SimpleContainer container) {
        this.container = container;
    }

    public SimpleContainer getContainer() {
        return container;
    }

    @Override
    public ItemStack getItem(int pIndex) {
        return container.getItem(pIndex);
    }

    @Override
    public int size() {
        return container.getContainerSize();
    }
}
