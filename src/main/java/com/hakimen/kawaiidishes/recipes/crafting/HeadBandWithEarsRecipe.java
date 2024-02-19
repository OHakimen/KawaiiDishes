package com.hakimen.kawaiidishes.recipes.crafting;

import com.hakimen.kawaiidishes.item.armor.EarsArmorItem;
import com.hakimen.kawaiidishes.item.armor.HeadBandArmorItem;
import com.hakimen.kawaiidishes.item.armor.HeadBandWithEarsArmorItem;
import com.hakimen.kawaiidishes.registry.RecipeRegister;
import com.hakimen.kawaiidishes.utils.HeadBandsWithEarsUtils;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class HeadBandWithEarsRecipe extends CustomRecipe {
    public HeadBandWithEarsRecipe(CraftingBookCategory pCategory) {
        super(pCategory);
    }

    @Override
    public boolean matches(CraftingContainer pContainer, Level pLevel) {
        ItemStack headBand = ItemStack.EMPTY;
        List<ItemStack> ears = new ArrayList<>();


        for(int i = 0; i < pContainer.getContainerSize(); ++i) {
            ItemStack containerItem = pContainer.getItem(i);
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
    public ItemStack assemble(CraftingContainer pContainer, RegistryAccess pRegistryAccess) {
        ItemStack headBand = ItemStack.EMPTY;
        List<ItemStack> ears = new ArrayList<>();


        for(int i = 0; i < pContainer.getContainerSize(); ++i) {
            ItemStack containerItem = pContainer.getItem(i);
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
        ItemStack stack = HeadBandsWithEarsUtils.getEaredHeadBandsItems().get(earsData.getEarsType()).get().getDefaultInstance();

        HeadBandWithEarsArmorItem headBandWithEarsArmorItem = (HeadBandWithEarsArmorItem) stack.getItem();

        headBandWithEarsArmorItem.setPrimaryBaseColor(stack,headBandData.getBaseColor(headBand));

        if(headBandData.hasOverlay(headBand)){
            stack.getOrCreateTag().putBoolean("HasPrimaryOverlay",true);

            headBandWithEarsArmorItem.setPrimaryOverlayColor(stack,headBandData.getOverlayColor(headBand));
        }

        headBandWithEarsArmorItem.setSecondaryBaseColor(stack,earsData.getBaseColor(ears.get(0)));

        if(earsData.hasOverlay(ears.get(0))){
            stack.getOrCreateTag().putBoolean("HasSecondaryOverlay",true);

            headBandWithEarsArmorItem.setSecondaryOverlayColor(stack,earsData.getOverlayColor(ears.get(0)));
        }


        return !headBand.isEmpty() && ears.size() == 1 ? stack : ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegister.HEAD_BAND_EARS.get();
    }
}
