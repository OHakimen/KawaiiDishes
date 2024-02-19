package com.hakimen.kawaiidishes.recipes.crafting;

import com.hakimen.kawaiidishes.item.IDyeableItem;
import com.hakimen.kawaiidishes.item.armor.EarsArmorItem;
import com.hakimen.kawaiidishes.registry.RecipeRegister;
import net.minecraft.core.RegistryAccess;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class OverlayIDyeable extends CustomRecipe {
    public OverlayIDyeable(CraftingBookCategory pCategory) {
        super(pCategory);
    }

    @Override
    public boolean matches(CraftingContainer pContainer, Level pLevel) {
        ItemStack idyeable = ItemStack.EMPTY;
        List<ItemStack> wool = new ArrayList<>();


        for(int i = 0; i < pContainer.getContainerSize(); ++i) {
            ItemStack containerItem = pContainer.getItem(i);
            if (!containerItem.isEmpty()) {
                if (containerItem.getItem() instanceof IDyeableItem) {
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

        return !idyeable.isEmpty() && wool.size() == 2 && !((IDyeableItem) idyeable.getItem()).hasOverlay(idyeable);
    }

    @Override
    public ItemStack assemble(CraftingContainer pContainer, RegistryAccess pRegistryAccess) {
        ItemStack idyeable = ItemStack.EMPTY;
        List<ItemStack> wool = new ArrayList<>();


        for(int i = 0; i < pContainer.getContainerSize(); ++i) {
            ItemStack containerItem = pContainer.getItem(i);
            if (!containerItem.isEmpty()) {
                if (containerItem.getItem() instanceof IDyeableItem) {
                    if(containerItem.getOrCreateTag().contains("HasOverlay") && containerItem.getOrCreateTag().getBoolean("HasOverlay")){
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
        stack.getOrCreateTag().putBoolean("HasOverlay",true);
        return !idyeable.isEmpty() && wool.size() == 2 ? stack : ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegister.OVERLAY_IDYEABLE.get();
    }
}
