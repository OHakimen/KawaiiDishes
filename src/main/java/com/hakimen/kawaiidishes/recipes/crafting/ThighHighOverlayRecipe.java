package com.hakimen.kawaiidishes.recipes.crafting;

import com.hakimen.kawaiidishes.item.armor.ThighHighsArmorItem;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import com.hakimen.kawaiidishes.registry.RecipeRegister;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class ThighHighOverlayRecipe extends CustomRecipe {
    public ThighHighOverlayRecipe(CraftingBookCategory pCategory) {
        super(pCategory);
    }
    List<Item> allDeco = List.of(
            ItemRegister.DOUBLE_BANDS.get(),
            ItemRegister.FULL_BANDS.get(),
            ItemRegister.LEG_CLIP.get(),
            ItemRegister.BOW.get()
    );
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
                    if (!allDeco.stream().filter(item -> containerItem.getItem().equals(item)).findFirst().isPresent()) {
                        return false;
                    }
                    decorations.add(containerItem);
                }
            }
        }

        return !thighHighs.isEmpty() && decorations.size() == 1;
    }

    @Override
    public ItemStack assemble(CraftingContainer pContainer, RegistryAccess pRegistryAccess) {
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
                    if (!allDeco.stream().filter(item -> containerItem.getItem().equals(item)).findFirst().isPresent()) {
                        return ItemStack.EMPTY;
                    }

                    decorations.add(containerItem);
                }
            }
        }

        ItemStack stack = thighHighs.copy();
        for (Item deco:allDeco) {
            if(decorations.get(0).getItem().equals(deco)){
                stack.getOrCreateTag().putInt("Decoration", allDeco.indexOf(deco)+1);
            }
        }
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
