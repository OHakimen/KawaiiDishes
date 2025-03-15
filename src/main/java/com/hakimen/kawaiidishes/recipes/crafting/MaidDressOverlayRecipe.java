package com.hakimen.kawaiidishes.recipes.crafting;

import com.hakimen.kawaiidishes.item.armor.MaidDressArmorItem;
import com.hakimen.kawaiidishes.item.component.KawaiiDyeableComponent;
import com.hakimen.kawaiidishes.registry.DataComponentRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import com.hakimen.kawaiidishes.registry.RecipeRegister;
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

public class MaidDressOverlayRecipe extends CustomRecipe {
    public MaidDressOverlayRecipe(CraftingBookCategory pCategory) {
        super(pCategory);
    }

    @Override
    public boolean matches(CraftingInput pContainer, Level pLevel) {
        ItemStack dress = ItemStack.EMPTY;
        List<ItemStack> apron = new ArrayList<>();


        for(int i = 0; i < pContainer.size(); ++i) {
            ItemStack containerItem = pContainer.getItem(i);
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
    public ItemStack assemble(CraftingInput pContainer, HolderLookup.Provider pRegistryAccess) {
        ItemStack dress = ItemStack.EMPTY;
        List<ItemStack> apron = new ArrayList<>();


        for(int i = 0; i < pContainer.size(); ++i) {
            ItemStack containerItem = pContainer.getItem(i);
            if (!containerItem.isEmpty()) {
                if (containerItem.getItem() instanceof MaidDressArmorItem) {
                    KawaiiDyeableComponent.KawaiiDyeable dyeable = containerItem.get(DataComponentRegister.DYEABLE);
                    if(dyeable.isHasOverlay()){
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
        stack.update(DataComponentRegister.DYEABLE, KawaiiDyeableComponent.DEFAULT, dyeable -> new KawaiiDyeableComponent.KawaiiDyeableBuilder(dyeable).setHasOverlay(true).build());
        return !dress.isEmpty() && apron.size() == 1 ? stack : ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegister.MAID_DRESS_APRON.value();
    }
}
