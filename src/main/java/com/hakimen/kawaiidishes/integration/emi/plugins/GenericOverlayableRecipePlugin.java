package com.hakimen.kawaiidishes.integration.emi.plugins;

import com.hakimen.kawaiidishes.item.IDyeableItem;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import dev.emi.emi.api.recipe.EmiCraftingRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class GenericOverlayableRecipePlugin extends EmiCraftingRecipe {

    public GenericOverlayableRecipePlugin(ItemStack item, ItemStack output, Identifier id) {
        super(new ArrayList<>(), EmiStack.of(output), id);

        List<Item> usesWoolAsOverlay = List.of(
                ItemRegister.CAT_EARS.get(),
                ItemRegister.CAT_TAIL.get(),
                ItemRegister.BUNNY_EARS.get(),
                ItemRegister.BUNNY_TAIL.get(),
                ItemRegister.FOX_EARS.get(),
                ItemRegister.FOX_TAIL.get(),
                ItemRegister.SHOES.get(),
                ItemRegister.HEAD_BAND.get()
        );

        if(usesWoolAsOverlay.contains(item.getItem())){
            var stacc = item.copy();
            stacc = IDyeableItem.dyeBase(stacc, List.of(DyeItem.byColor(DyeColor.BLACK)));

            NbtList list = new NbtList();

            list.add(NbtString.of("\"This is an example!\""));

            NbtCompound compound = new NbtCompound();
            compound.put("Lore",list);

            stacc.getOrCreateNbt().put("display", compound);

            this.input.addAll(List.of(
                    EmiIngredient.of(Ingredient.ofStacks(stacc)),
                    EmiIngredient.of(ItemTags.WOOL),
                    EmiIngredient.of(ItemTags.WOOL)
            ));
        }
    }

    public GenericOverlayableRecipePlugin(ItemStack item, Ingredient overlayItem, ItemStack output, Identifier id) {
        super(new ArrayList<>(), EmiStack.of(output), id);
        var stacc = item.copy();
        stacc = IDyeableItem.dyeBase(stacc, List.of(DyeItem.byColor(DyeColor.BLACK)));

        NbtList list = new NbtList();

        list.add(NbtString.of("\"This is an example!\""));

        NbtCompound compound = new NbtCompound();
        compound.put("Lore",list);

        stacc.getOrCreateNbt().put("display", compound);

        this.input.addAll(List.of(
                EmiIngredient.of(Ingredient.ofStacks(stacc)),
                EmiIngredient.of(overlayItem)
        ));
    }

}
