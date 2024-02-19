package com.hakimen.kawaiidishes.recipes.crafting;

import com.google.common.collect.Lists;
import com.hakimen.kawaiidishes.item.IDyeableItem;
import com.hakimen.kawaiidishes.registry.RecipeRegister;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import java.util.List;

public class DyeIDyeableRecipe extends CustomRecipe {
    public DyeIDyeableRecipe(CraftingBookCategory pCategory) {
        super(pCategory);
    }

    @Override
    public boolean matches(CraftingContainer pContainer, Level pLevel) {
        ItemStack itemstack = ItemStack.EMPTY;
        List<ItemStack> list = Lists.newArrayList();
        for(int i = 0; i < pContainer.getContainerSize(); ++i) {
            ItemStack itemstack1 = pContainer.getItem(i);
            if (!itemstack1.isEmpty()) {
                if (itemstack1.getItem() instanceof IDyeableItem) {
                    if (!itemstack.isEmpty()) {
                        return false;
                    }

                    itemstack = itemstack1;
                } else {
                    if (!(itemstack1.getItem() instanceof DyeItem)) {
                        return false;
                    }

                    list.add(itemstack1);
                }
            }
        }

        return !itemstack.isEmpty() && !list.isEmpty();
    }

    @Override
    public ItemStack assemble(CraftingContainer pContainer, RegistryAccess pRegistryAccess) {
        List<DyeItem> list = Lists.newArrayList();
        ItemStack itemstack = ItemStack.EMPTY;

        for(int i = 0; i < pContainer.getContainerSize(); ++i) {
            ItemStack itemstack1 = pContainer.getItem(i);
            if (!itemstack1.isEmpty()) {
                Item item = itemstack1.getItem();
                if (item instanceof IDyeableItem) {
                    if (!itemstack.isEmpty()) {
                        return ItemStack.EMPTY;
                    }

                    itemstack = itemstack1.copy();
                } else {
                    if (!(item instanceof DyeItem)) {
                        return ItemStack.EMPTY;
                    }

                    list.add((DyeItem)item);
                }
            }
        }
        ItemStack stack = itemstack.getItem() instanceof IDyeableItem item ? item.hasOverlay(itemstack) ? IDyeableItem.dyeOverlay(itemstack, list) : IDyeableItem.dyeBase(itemstack, list) : ItemStack.EMPTY;
        return !itemstack.isEmpty() && !list.isEmpty() ? stack : ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegister.DYE_IDYEABLE.get();
    }
}
