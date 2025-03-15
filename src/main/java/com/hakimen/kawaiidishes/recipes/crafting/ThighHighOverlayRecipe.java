package com.hakimen.kawaiidishes.recipes.crafting;

import com.hakimen.kawaiidishes.custom.Registries;
import com.hakimen.kawaiidishes.item.DecorationItem;
import com.hakimen.kawaiidishes.item.armor.ThighHighsArmorItem;
import com.hakimen.kawaiidishes.registry.RecipeRegister;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ThighHighOverlayRecipe extends SpecialCraftingRecipe {

    public ThighHighOverlayRecipe(Identifier resourceLocation, CraftingRecipeCategory craftingBookCategory) {
        super(resourceLocation, craftingBookCategory);
    }

    @Override
    public boolean matches(RecipeInputInventory pContainer, World pLevel) {
        ItemStack thighHighs = ItemStack.EMPTY;
        List<ItemStack> decorations = new ArrayList<>();


        for(int i = 0; i < pContainer.size(); ++i) {
            ItemStack containerItem = pContainer.getStack(i);
            if (!containerItem.isEmpty()) {
                if (containerItem.getItem() instanceof ThighHighsArmorItem) {
                    if (!thighHighs.isEmpty()) {
                        return false;
                    }
                    thighHighs = containerItem;
                } else {
                    if(containerItem.getItem() instanceof DecorationItem decorationItem && Registries.THIGH_HIGH_DECORATIONS.containsId(decorationItem.getThighHighDecorationLocation())){
                        decorations.add(containerItem);
                    }
                }
            }
        }

        return !thighHighs.isEmpty() && decorations.size() == 1;
    }

    @Override
    public ItemStack craft(RecipeInputInventory pContainer, DynamicRegistryManager pRegistryAccess) {
        ItemStack thighHighs = ItemStack.EMPTY;
        List<ItemStack> decorations = new ArrayList<>();


        for(int i = 0; i < pContainer.size(); ++i) {
            ItemStack containerItem = pContainer.getStack(i);
            if (!containerItem.isEmpty()) {
                if (containerItem.getItem() instanceof ThighHighsArmorItem) {
                    if(containerItem.getOrCreateNbt().contains("Decoration") && containerItem.getOrCreateNbt().getInt("Decoration") > 1){
                        return ItemStack.EMPTY;
                    }
                    if (!thighHighs.isEmpty()) {
                        return ItemStack.EMPTY;
                    }

                    thighHighs = containerItem;
                } else {
                    if(!(containerItem.getItem() instanceof DecorationItem decorationItem && Registries.THIGH_HIGH_DECORATIONS.containsId(decorationItem.getThighHighDecorationLocation()))){
                        return ItemStack.EMPTY;
                    }

                    decorations.add(containerItem);
                }
            }
        }

        ItemStack stack = thighHighs.copy();

        var decoration = Registries.THIGH_HIGH_DECORATIONS.getRawId(Registries.THIGH_HIGH_DECORATIONS.get(((DecorationItem)decorations.get(0).getItem()).getThighHighDecorationLocation()));
        stack.getOrCreateNbt().putInt("Decoration", decoration);

        return !thighHighs.isEmpty() && decorations.size() == 1 ? stack : ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int pWidth, int pHeight) {
        return pWidth * pHeight >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegister.THIGH_HIGH_DECORATION.get();
    }
}
