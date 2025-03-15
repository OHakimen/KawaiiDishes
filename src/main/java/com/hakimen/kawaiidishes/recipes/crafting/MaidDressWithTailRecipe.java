package com.hakimen.kawaiidishes.recipes.crafting;

import com.hakimen.kawaiidishes.item.armor.MaidDressArmorItem;
import com.hakimen.kawaiidishes.item.armor.MaidDressesWithTailArmorItem;
import com.hakimen.kawaiidishes.item.armor.TailArmorItem;
import com.hakimen.kawaiidishes.item.component.KawaiiDyeableComponent;
import com.hakimen.kawaiidishes.registry.DataComponentRegister;
import com.hakimen.kawaiidishes.registry.RecipeRegister;
import com.hakimen.kawaiidishes.utils.MaidDressesWithTailUtils;
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

public class MaidDressWithTailRecipe extends CustomRecipe {
    public MaidDressWithTailRecipe(CraftingBookCategory pCategory) {
        super(pCategory);
    }

    @Override
    public boolean matches(CraftingInput pContainer, Level pLevel) {
        ItemStack dress = ItemStack.EMPTY;
        List<ItemStack> tail = new ArrayList<>();


        for(int i = 0; i < pContainer.size(); ++i) {
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
    public ItemStack assemble(CraftingInput pContainer, HolderLookup.Provider pRegistryAccess) {
        ItemStack dress = ItemStack.EMPTY;
        List<ItemStack> tail = new ArrayList<>();


        for(int i = 0; i < pContainer.size(); ++i) {
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
        var tailComponents = tail.get(0).get(DataComponentRegister.DYEABLE);
        var dressData = (MaidDressArmorItem) dress.getItem();
        var dressComponents = dress.get(DataComponentRegister.DYEABLE);
        ItemStack stack = MaidDressesWithTailUtils.getTailedDressItems().get(tailData.getTailType()).get().getDefaultInstance();
        MaidDressesWithTailArmorItem tailDressArmorItem = (MaidDressesWithTailArmorItem) stack.getItem();

        KawaiiDyeableComponent.KawaiiDyeableBuilder builder = new KawaiiDyeableComponent.KawaiiDyeableBuilder();

        builder.setBase(dressComponents.getBase());

        if(dressComponents.isHasOverlay()){
            builder.setOverlay(dressComponents.getOverlay());
            builder.setHasOverlay(true);
        }

        builder.setSecondaryBase(tailComponents.getBase());

        if(tailComponents.isHasOverlay()){
            builder.setSecondaryOverlay(tailComponents.getOverlay());
            builder.setHasSecondaryOverlay(true);
        }

        stack.set(DataComponentRegister.DYEABLE,builder.build());

        return !dress.isEmpty() && tail.size() == 1 ? stack : ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegister.MAID_DRESS_TAIL.value();
    }
}
