package com.hakimen.kawaiidishes.recipes.crafting;

import com.hakimen.kawaiidishes.item.IFourColorDyeableItem;
import com.hakimen.kawaiidishes.item.armor.EarsArmorItem;
import com.hakimen.kawaiidishes.item.armor.HeadBandArmorItem;
import com.hakimen.kawaiidishes.item.armor.HeadBandWithEarsArmorItem;
import com.hakimen.kawaiidishes.item.component.KawaiiDyeableComponent;
import com.hakimen.kawaiidishes.registry.DataComponentRegister;
import com.hakimen.kawaiidishes.registry.RecipeRegister;
import com.hakimen.kawaiidishes.utils.HeadBandsWithEarsUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
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
    public boolean matches(CraftingInput pContainer, Level pLevel) {
        ItemStack headBand = ItemStack.EMPTY;
        List<ItemStack> ears = new ArrayList<>();


        for(int i = 0; i < pContainer.size(); ++i) {
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
    public ItemStack assemble(CraftingInput pContainer, HolderLookup.Provider pRegistryAccess) {
        ItemStack headBand = ItemStack.EMPTY;
        List<ItemStack> ears = new ArrayList<>();

        for(int i = 0; i < pContainer.size(); ++i) {
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
        var earsComponents = ears.get(0).get(DataComponentRegister.DYEABLE);
        var headBandData = (HeadBandArmorItem) headBand.getItem();
        var headBandComponents = headBand.get(DataComponentRegister.DYEABLE);
        ItemStack stack = HeadBandsWithEarsUtils.getEaredHeadBandsItems().get(earsData.getEarsType()).get().getDefaultInstance();

        KawaiiDyeableComponent.KawaiiDyeableBuilder builder = new KawaiiDyeableComponent.KawaiiDyeableBuilder();


        builder.setBase(headBandComponents.getBase());

        if(headBandComponents.isHasOverlay()){
            builder.setOverlay(headBandComponents.getOverlay());
            builder.setHasOverlay(true);
        }

        builder.setSecondaryBase(earsComponents.getBase());

        if(earsComponents.isHasOverlay()){
            builder.setSecondaryOverlay(earsComponents.getOverlay());
            builder.setHasSecondaryOverlay(true);
        }

        stack.set(DataComponentRegister.DYEABLE,builder.build());

        return !headBand.isEmpty() && ears.size() == 1 ? stack : ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegister.HEAD_BAND_EARS.value();
    }
}
