package com.hakimen.kawaiidishes.recipes.crafting;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.item.armor.EarsArmorItem;
import com.hakimen.kawaiidishes.item.armor.HeadBandArmorItem;
import com.hakimen.kawaiidishes.item.armor.HeadBandWithEarsArmorItem;
import com.hakimen.kawaiidishes.registry.RecipeRegister;
import com.hakimen.kawaiidishes.utils.HeadBandsWithEarsUtils;
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

public class HeadBandWithEarsRecipe extends SpecialCraftingRecipe {

    public HeadBandWithEarsRecipe(Identifier resourceLocation, CraftingRecipeCategory craftingBookCategory) {
        super(resourceLocation, craftingBookCategory);
    }

    @Override
    public boolean matches(RecipeInputInventory pContainer, World pLevel) {
        ItemStack headBand = ItemStack.EMPTY;
        List<ItemStack> ears = new ArrayList<>();


        for(int i = 0; i < pContainer.size(); ++i) {
            ItemStack containerItem = pContainer.getStack(i);
            if (!containerItem.isEmpty()) {
                if (containerItem.getItem() instanceof HeadBandArmorItem) {
                    if (!headBand.isEmpty()) {
                        return false;
                    }

                    headBand = containerItem;
                } else {
                    if (!(containerItem.getItem() instanceof EarsArmorItem)) {
                        return false;
                    }
                    ears.add(containerItem);
                }
            }
        }

        return !headBand.isEmpty() && ears.size() == 1;
    }

    @Override
    public ItemStack craft(RecipeInputInventory pContainer, DynamicRegistryManager pRegistryAccess) {
        ItemStack headBand = ItemStack.EMPTY;
        List<ItemStack> ears = new ArrayList<>();


        for(int i = 0; i < pContainer.size(); ++i) {
            ItemStack containerItem = pContainer.getStack(i);
            if (!containerItem.isEmpty()) {
                if (containerItem.getItem() instanceof HeadBandArmorItem) {
                    if (!headBand.isEmpty()) {
                        return ItemStack.EMPTY;
                    }

                    headBand = containerItem;
                } else {
                    if (!(containerItem.getItem() instanceof EarsArmorItem)) {
                        return ItemStack.EMPTY;
                    }

                    ears.add(containerItem);
                }
            }
        }

        var earsData = (EarsArmorItem) ears.get(0).getItem();
        var headBandData = (HeadBandArmorItem) headBand.getItem();
        ItemStack stack = HeadBandsWithEarsUtils.getEaredHeadBandsItems().get(earsData.getEarsType()).get().getDefaultStack();

        HeadBandWithEarsArmorItem headBandWithEarsArmorItem = (HeadBandWithEarsArmorItem) stack.getItem();

        headBandWithEarsArmorItem.setPrimaryBaseColor(stack,headBandData.getBaseColor(headBand));

        if(headBandData.hasOverlay(headBand)){
            stack.getOrCreateNbt().putBoolean("HasPrimaryOverlay",true);

            headBandWithEarsArmorItem.setPrimaryOverlayColor(stack,headBandData.getOverlayColor(headBand));
        }

        headBandWithEarsArmorItem.setSecondaryBaseColor(stack,earsData.getBaseColor(ears.get(0)));

        if(earsData.hasOverlay(ears.get(0))){
            stack.getOrCreateNbt().putBoolean("HasSecondaryOverlay",true);

            headBandWithEarsArmorItem.setSecondaryOverlayColor(stack,earsData.getOverlayColor(ears.get(0)));
        }


        return !headBand.isEmpty() && ears.size() == 1 ? stack : ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int pWidth, int pHeight) {
        return pWidth * pHeight >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegister.HEAD_BAND_EARS.get();
    }
}
