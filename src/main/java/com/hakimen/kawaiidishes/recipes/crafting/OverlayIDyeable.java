package com.hakimen.kawaiidishes.recipes.crafting;

import com.hakimen.kawaiidishes.item.IDyeableItem;
import com.hakimen.kawaiidishes.item.armor.MaidDressArmorItem;
import com.hakimen.kawaiidishes.item.armor.MaidDressesWithTailArmorItem;
import com.hakimen.kawaiidishes.item.armor.ThighHighsArmorItem;
import com.hakimen.kawaiidishes.registry.RecipeRegister;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class OverlayIDyeable extends SpecialCraftingRecipe {
    public OverlayIDyeable(Identifier resourceLocation, CraftingRecipeCategory craftingBookCategory) {
        super(resourceLocation, craftingBookCategory);
    }

    @Override
    public boolean matches(RecipeInputInventory pContainer, World pLevel) {
        ItemStack idyeable = ItemStack.EMPTY;
        List<ItemStack> wool = new ArrayList<>();


        for (int i = 0; i < pContainer.size(); ++i) {
            ItemStack containerItem = pContainer.getStack(i);
            if (!containerItem.isEmpty()) {
                if (containerItem.getItem() instanceof IDyeableItem) {
                    if (!idyeable.isEmpty()) {
                        return false;
                    }

                    idyeable = containerItem;

                    Item item = idyeable.getItem();
                    if (item instanceof MaidDressArmorItem
                            || item instanceof MaidDressesWithTailArmorItem
                            || item instanceof ThighHighsArmorItem) {
                        return false;
                    }
                } else {
                    if (!(containerItem.isIn(ItemTags.WOOL))) {
                        return false;
                    }
                    wool.add(containerItem);
                }
            }
        }

        return !idyeable.isEmpty() && wool.size() == 2 && !((IDyeableItem) idyeable.getItem()).hasOverlay(idyeable);
    }

    @Override
    public ItemStack craft(RecipeInputInventory pContainer, DynamicRegistryManager pRegistryAccess) {
        ItemStack idyeable = ItemStack.EMPTY;
        List<ItemStack> wool = new ArrayList<>();

        for (int i = 0; i < pContainer.size(); ++i) {
            ItemStack containerItem = pContainer.getStack(i);
            if (!containerItem.isEmpty()) {
                if (containerItem.getItem() instanceof IDyeableItem) {
                    if (containerItem.getOrCreateNbt().contains("HasOverlay") && containerItem.getOrCreateNbt().getBoolean("HasOverlay")) {
                        return ItemStack.EMPTY;
                    }
                    if (!idyeable.isEmpty()) {
                        return ItemStack.EMPTY;
                    }

                    idyeable = containerItem;
                } else {
                    if (!(containerItem.isIn(ItemTags.WOOL))) {
                        return ItemStack.EMPTY;
                    }

                    wool.add(containerItem);
                }
            }
        }

        ItemStack stack = idyeable.copy();
        stack.getOrCreateNbt().putBoolean("HasOverlay", true);
        return !idyeable.isEmpty() && wool.size() == 2 ? stack : ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int pWidth, int pHeight) {
        return pWidth * pHeight >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegister.OVERLAY_IDYEABLE.get();
    }
}
