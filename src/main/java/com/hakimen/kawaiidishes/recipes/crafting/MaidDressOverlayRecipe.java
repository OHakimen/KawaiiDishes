package com.hakimen.kawaiidishes.recipes.crafting;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.item.armor.MaidDressArmorItem;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import com.hakimen.kawaiidishes.registry.RecipeRegister;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class MaidDressOverlayRecipe extends SpecialCraftingRecipe {
    public MaidDressOverlayRecipe(Identifier resourceLocation, CraftingRecipeCategory craftingBookCategory) {
        super(resourceLocation, craftingBookCategory);
    }

    @Override
    public boolean matches(RecipeInputInventory pContainer, World pLevel) {
        ItemStack dress = ItemStack.EMPTY;
        List<ItemStack> apron = new ArrayList<>();


        for(int i = 0; i < pContainer.size(); ++i) {
            ItemStack containerItem = pContainer.getStack(i);
            if (!containerItem.isEmpty()) {
                if (containerItem.getItem() instanceof MaidDressArmorItem) {
                    if (!dress.isEmpty()) {
                        return false;
                    }

                    dress = containerItem;
                } else {
                    if (!(containerItem.getItem().equals(ItemRegister.APRON.get()))) {
                        return false;
                    }
                    apron.add(containerItem);
                }
            }
        }

        return !dress.isEmpty() && apron.size() == 1;
    }

    @Override
    public ItemStack craft(RecipeInputInventory pContainer, DynamicRegistryManager pRegistryAccess) {
        ItemStack dress = ItemStack.EMPTY;
        List<ItemStack> apron = new ArrayList<>();


        for(int i = 0; i < pContainer.size(); ++i) {
            ItemStack containerItem = pContainer.getStack(i);
            if (!containerItem.isEmpty()) {
                if (containerItem.getItem() instanceof MaidDressArmorItem) {
                    if(containerItem.getOrCreateNbt().contains("HasOverlay") && containerItem.getOrCreateNbt().getBoolean("HasOverlay")){
                        return ItemStack.EMPTY;
                    }
                    if (!dress.isEmpty()) {
                        return ItemStack.EMPTY;
                    }

                    dress = containerItem;
                } else {
                    if (!(containerItem.getItem().equals(ItemRegister.APRON.get()))) {
                        return ItemStack.EMPTY;
                    }

                    apron.add(containerItem);
                }
            }
        }

        ItemStack stack = dress.copy();
        stack.getOrCreateNbt().putBoolean("HasOverlay",true);
        return !dress.isEmpty() && apron.size() == 1 ? stack : ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int pWidth, int pHeight) {
        return pWidth * pHeight >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegister.MAID_DRESS_APRON.get();
    }
}
