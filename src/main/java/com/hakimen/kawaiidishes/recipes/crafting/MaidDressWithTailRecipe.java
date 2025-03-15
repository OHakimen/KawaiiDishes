package com.hakimen.kawaiidishes.recipes.crafting;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.item.armor.MaidDressArmorItem;
import com.hakimen.kawaiidishes.item.armor.MaidDressesWithTailArmorItem;
import com.hakimen.kawaiidishes.item.armor.TailArmorItem;
import com.hakimen.kawaiidishes.registry.RecipeRegister;
import com.hakimen.kawaiidishes.utils.MaidDressesWithTailUtils;
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

public class MaidDressWithTailRecipe extends SpecialCraftingRecipe {
    public MaidDressWithTailRecipe(Identifier resourceLocation, CraftingRecipeCategory craftingBookCategory) {
        super(resourceLocation, craftingBookCategory);
    }

    public MaidDressWithTailRecipe(CraftingRecipeCategory pCategory) {
        super(new Identifier(KawaiiDishes.MODID, "combine_dress_tail"),pCategory);
    }

    @Override
    public boolean matches(RecipeInputInventory pContainer, World pLevel) {
        ItemStack dress = ItemStack.EMPTY;
        List<ItemStack> tail = new ArrayList<>();


        for(int i = 0; i < pContainer.size(); ++i) {
            ItemStack containerItem = pContainer.getStack(i);
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
    public ItemStack craft(RecipeInputInventory pContainer, DynamicRegistryManager pRegistryAccess) {
        ItemStack dress = ItemStack.EMPTY;
        List<ItemStack> tail = new ArrayList<>();


        for(int i = 0; i < pContainer.size(); ++i) {
            ItemStack containerItem = pContainer.getStack(i);
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
        ItemStack stack = MaidDressesWithTailUtils.getTailedDressItems().get(tailData.getTailType()).get().getDefaultStack();
        MaidDressesWithTailArmorItem tailDressArmorItem = (MaidDressesWithTailArmorItem) stack.getItem();

        tailDressArmorItem.setPrimaryBaseColor(stack,dressData.getBaseColor(dress));

        if(dressData.hasOverlay(dress)){
            stack.getOrCreateNbt().putBoolean("HasPrimaryOverlay",true);

            tailDressArmorItem.setPrimaryOverlayColor(stack,dressData.getOverlayColor(dress));
        }

        tailDressArmorItem.setSecondaryBaseColor(stack,tailData.getBaseColor(tail.get(0)));

        if(tailData.hasOverlay(tail.get(0))){
            stack.getOrCreateNbt().putBoolean("HasSecondaryOverlay",true);

            tailDressArmorItem.setSecondaryOverlayColor(stack,tailData.getOverlayColor(tail.get(0)));
        }


        return !dress.isEmpty() && tail.size() == 1 ? stack : ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int pWidth, int pHeight) {
        return pWidth * pHeight >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegister.MAID_DRESS_TAIL.get();
    }
}
