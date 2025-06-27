package com.hakimen.kawaiidishes.recipes.crafting;

import com.google.common.collect.Lists;
import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.item.IDyeableItem;
import com.hakimen.kawaiidishes.registry.RecipeRegister;
import java.util.List;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class DyeIDyeableRecipe extends SpecialCraftingRecipe {

    public DyeIDyeableRecipe(Identifier resourceLocation, CraftingRecipeCategory craftingBookCategory) {
        super(resourceLocation, craftingBookCategory);
    }

    @Override
    public boolean matches(RecipeInputInventory pContainer, World pLevel) {
        ItemStack itemstack = ItemStack.EMPTY;
        List<ItemStack> list = Lists.newArrayList();
        for(int i = 0; i < pContainer.size(); ++i) {
            ItemStack itemstack1 = pContainer.getStack(i);
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
    public ItemStack craft(RecipeInputInventory pContainer, DynamicRegistryManager pRegistryAccess) {
        List<DyeItem> list = Lists.newArrayList();
        ItemStack itemstack = ItemStack.EMPTY;

        for(int i = 0; i < pContainer.size(); ++i) {
            ItemStack itemstack1 = pContainer.getStack(i);
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
    public boolean fits(int pWidth, int pHeight) {
        return pWidth * pHeight >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegister.DYE_IDYEABLE.get();
    }
}
