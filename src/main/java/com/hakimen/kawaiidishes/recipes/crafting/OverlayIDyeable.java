package com.hakimen.kawaiidishes.recipes.crafting;

import com.hakimen.kawaiidishes.item.component.KawaiiDyeableComponent;
import com.hakimen.kawaiidishes.registry.DataComponentRegister;
import com.hakimen.kawaiidishes.registry.RecipeRegister;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class OverlayIDyeable extends CustomRecipe {
    public OverlayIDyeable(CraftingBookCategory pCategory) {
        super(pCategory);
    }

    @Override
    public boolean matches(CraftingInput pContainer, Level pLevel) {
        ItemStack idyeable = ItemStack.EMPTY;
        List<ItemStack> wool = new ArrayList<>();


        for(int i = 0; i < pContainer.size(); ++i) {
            ItemStack containerItem = pContainer.getItem(i);
            if (!containerItem.isEmpty()) {
                if (containerItem.has(DataComponentRegister.DYEABLE.get())) {
                    if (!idyeable.isEmpty()) {
                        return false;
                    }

                    idyeable = containerItem;
                } else {
                    if (!(containerItem.is(ItemTags.WOOL))) {
                        return false;
                    }
                    wool.add(containerItem);
                }
            }
        }

        return !idyeable.isEmpty() && wool.size() == 2 && !idyeable.get(DataComponentRegister.DYEABLE.get()).isHasOverlay();
    }

    @Override
    public ItemStack assemble(CraftingInput pContainer, HolderLookup.Provider pHolder) {
        ItemStack idyeable = ItemStack.EMPTY;
        List<ItemStack> wool = new ArrayList<>();


        for(int i = 0; i < pContainer.size(); ++i) {
            ItemStack containerItem = pContainer.getItem(i);
            if (!containerItem.isEmpty()) {
                KawaiiDyeableComponent.KawaiiDyeable dyeable = containerItem.get(DataComponentRegister.DYEABLE.get());
                if (containerItem.has(DataComponentRegister.DYEABLE.get())) {
                    if(dyeable.isHasOverlay()){
                        return ItemStack.EMPTY;
                    }
                    if (!idyeable.isEmpty()) {
                        return ItemStack.EMPTY;
                    }

                    idyeable = containerItem;
                } else {
                    if (!(containerItem.is(ItemTags.WOOL))) {
                        return ItemStack.EMPTY;
                    }

                    wool.add(containerItem);
                }
            }
        }

        ItemStack stack = idyeable.copy();
        stack.update(DataComponentRegister.DYEABLE.get(), KawaiiDyeableComponent.DEFAULT, dyeable -> new KawaiiDyeableComponent.KawaiiDyeableBuilder(dyeable).setHasOverlay(true).build());
        return !idyeable.isEmpty() && wool.size() == 2 ? stack : ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegister.OVERLAY_IDYEABLE.value();
    }
}
