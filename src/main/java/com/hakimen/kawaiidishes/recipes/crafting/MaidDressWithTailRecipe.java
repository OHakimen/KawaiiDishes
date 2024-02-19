package com.hakimen.kawaiidishes.recipes.crafting;

import com.hakimen.kawaiidishes.item.armor.MaidDressArmorItem;
import com.hakimen.kawaiidishes.item.armor.MaidDressesWithTailArmorItem;
import com.hakimen.kawaiidishes.item.armor.TailArmorItem;
import com.hakimen.kawaiidishes.registry.RecipeRegister;
import com.hakimen.kawaiidishes.utils.MaidDressesWithTailUtils;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class MaidDressWithTailRecipe extends CustomRecipe {
    public MaidDressWithTailRecipe(CraftingBookCategory pCategory) {
        super(pCategory);
    }

    @Override
    public boolean matches(CraftingContainer pContainer, Level pLevel) {
        ItemStack dress = ItemStack.EMPTY;
        List<ItemStack> tail = new ArrayList<>();


        for(int i = 0; i < pContainer.getContainerSize(); ++i) {
            ItemStack containerItem = pContainer.getItem(i);
            if (!containerItem.isEmpty()) {
                if (containerItem.getItem() instanceof MaidDressArmorItem) {
                    if (!dress.isEmpty()) {
                        return false;
                    }

                    dress = containerItem;
                } else {
                    if (!(containerItem.getItem() instanceof TailArmorItem)) {
                        return false;
                    }
                    tail.add(containerItem);
                }
            }
        }

        return !dress.isEmpty() && tail.size() == 1;
    }

    @Override
    public ItemStack assemble(CraftingContainer pContainer, RegistryAccess pRegistryAccess) {
        ItemStack dress = ItemStack.EMPTY;
        List<ItemStack> tail = new ArrayList<>();


        for(int i = 0; i < pContainer.getContainerSize(); ++i) {
            ItemStack containerItem = pContainer.getItem(i);
            if (!containerItem.isEmpty()) {
                if (containerItem.getItem() instanceof MaidDressArmorItem maidDress) {
                    if (!dress.isEmpty()) {
                        return ItemStack.EMPTY;
                    }

                    dress = containerItem;
                } else {
                    if (!(containerItem.getItem() instanceof TailArmorItem)) {
                        return ItemStack.EMPTY;
                    }

                    tail.add(containerItem);
                }
            }
        }

        var tailData = (TailArmorItem) tail.get(0).getItem();
        var dressData = (MaidDressArmorItem) dress.getItem();
        ItemStack stack = MaidDressesWithTailUtils.getTailedDressItems().get(tailData.getTailType()).get().getDefaultInstance();
        MaidDressesWithTailArmorItem tailDressArmorItem = (MaidDressesWithTailArmorItem) stack.getItem();

        tailDressArmorItem.setPrimaryBaseColor(stack,dressData.getBaseColor(dress));

        if(dressData.hasOverlay(dress)){
            stack.getOrCreateTag().putBoolean("HasPrimaryOverlay",true);

            tailDressArmorItem.setPrimaryOverlayColor(stack,dressData.getOverlayColor(dress));
        }

        tailDressArmorItem.setSecondaryBaseColor(stack,tailData.getBaseColor(tail.get(0)));

        if(tailData.hasOverlay(tail.get(0))){
            stack.getOrCreateTag().putBoolean("HasSecondaryOverlay",true);

            tailDressArmorItem.setSecondaryOverlayColor(stack,tailData.getOverlayColor(tail.get(0)));
        }


        return !dress.isEmpty() && tail.size() == 1 ? stack : ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegister.MAID_DRESS_TAIL.get();
    }
}
