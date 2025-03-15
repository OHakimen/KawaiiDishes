package com.hakimen.kawaiidishes.recipes.crafting;

import com.google.common.collect.Lists;
import com.hakimen.kawaiidishes.item.IFourColorDyeableItem;
import com.hakimen.kawaiidishes.item.component.KawaiiDyeableComponent;
import com.hakimen.kawaiidishes.registry.DataComponentRegister;
import com.hakimen.kawaiidishes.registry.RecipeRegister;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import java.util.List;

public class DyeIDyeableRecipe extends CustomRecipe {
    public DyeIDyeableRecipe(CraftingBookCategory pCategory) {
        super(pCategory);
    }

    @Override
    public boolean matches(CraftingInput pContainer, Level pLevel) {
        ItemStack itemstack = ItemStack.EMPTY;
        List<ItemStack> list = Lists.newArrayList();
        for (int i = 0; i < pContainer.size(); ++i) {
            ItemStack itemstack1 = pContainer.getItem(i);
            if (!itemstack1.isEmpty()) {
                if (itemstack1.has(DataComponentRegister.DYEABLE)) {
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
    public ItemStack assemble(CraftingInput pContainer, HolderLookup.Provider p_346030_) {
        List<DyeItem> list = Lists.newArrayList();
        ItemStack itemstack = ItemStack.EMPTY;

        for (int i = 0; i < pContainer.size(); ++i) {
            ItemStack itemstack1 = pContainer.getItem(i);
            if (!itemstack1.isEmpty()) {
                Item item = itemstack1.getItem();
                if (itemstack1.has(DataComponentRegister.DYEABLE)) {
                    if (!itemstack.isEmpty()) {
                        return ItemStack.EMPTY;
                    }

                    itemstack = itemstack1.copy();
                } else {
                    if (!(item instanceof DyeItem)) {
                        return ItemStack.EMPTY;
                    }

                    list.add((DyeItem) item);
                }
            }
        }
        KawaiiDyeableComponent.KawaiiDyeable data = itemstack.get(DataComponentRegister.DYEABLE);
        ItemStack stack = data != null ? data.isHasOverlay() ? IFourColorDyeableItem.dyePrimaryOverlay(itemstack, list) : IFourColorDyeableItem.dyePrimaryBase(itemstack, list) : ItemStack.EMPTY;
        return !itemstack.isEmpty() && !list.isEmpty() ? stack : ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegister.DYE_IDYEABLE.value();
    }
}
