package com.hakimen.kawaiidishes.recipes.crafting;

import com.hakimen.kawaiidishes.custom.Registries;
import com.hakimen.kawaiidishes.item.DecorationItem;
import com.hakimen.kawaiidishes.item.armor.ThighHighsArmorItem;
import com.hakimen.kawaiidishes.registry.RecipeRegister;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class ThighHighOverlayRecipe extends CustomRecipe {

    public ThighHighOverlayRecipe(ResourceLocation resourceLocation, CraftingBookCategory craftingBookCategory) {
        super(resourceLocation, craftingBookCategory);
    }

    @Override
    public boolean matches(CraftingContainer pContainer, Level pLevel) {
        ItemStack thighHighs = ItemStack.EMPTY;
        List<ItemStack> decorations = new ArrayList<>();


        for(int i = 0; i < pContainer.getContainerSize(); ++i) {
            ItemStack containerItem = pContainer.getItem(i);
            if (!containerItem.isEmpty()) {
                if (containerItem.getItem() instanceof ThighHighsArmorItem) {
                    if (!thighHighs.isEmpty()) {
                        return false;
                    }
                    thighHighs = containerItem;
                } else {
                    if(containerItem.getItem() instanceof DecorationItem decorationItem && Registries.THIGH_HIGH_DECORATIONS.containsKey(decorationItem.getThighHighDecorationLocation())){
                        decorations.add(containerItem);
                    }
                }
            }
        }

        return !thighHighs.isEmpty() && decorations.size() == 1;
    }

    @Override
    public ItemStack craft(CraftingContainer pContainer, RegistryAccess pRegistryAccess) {
        ItemStack thighHighs = ItemStack.EMPTY;
        List<ItemStack> decorations = new ArrayList<>();


        for(int i = 0; i < pContainer.getContainerSize(); ++i) {
            ItemStack containerItem = pContainer.getItem(i);
            if (!containerItem.isEmpty()) {
                if (containerItem.getItem() instanceof ThighHighsArmorItem) {
                    if(containerItem.getOrCreateTag().contains("Decoration") && containerItem.getOrCreateTag().getInt("Decoration") > 1){
                        return ItemStack.EMPTY;
                    }
                    if (!thighHighs.isEmpty()) {
                        return ItemStack.EMPTY;
                    }

                    thighHighs = containerItem;
                } else {
                    if(!(containerItem.getItem() instanceof DecorationItem decorationItem && Registries.THIGH_HIGH_DECORATIONS.containsKey(decorationItem.getThighHighDecorationLocation()))){
                        return ItemStack.EMPTY;
                    }

                    decorations.add(containerItem);
                }
            }
        }

        ItemStack stack = thighHighs.copy();

        var decoration = Registries.THIGH_HIGH_DECORATIONS.getId(Registries.THIGH_HIGH_DECORATIONS.get(((DecorationItem)decorations.get(0).getItem()).getThighHighDecorationLocation()));
        stack.getOrCreateTag().putInt("Decoration", decoration);

        return !thighHighs.isEmpty() && decorations.size() == 1 ? stack : ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegister.THIGH_HIGH_DECORATION.get();
    }
}
