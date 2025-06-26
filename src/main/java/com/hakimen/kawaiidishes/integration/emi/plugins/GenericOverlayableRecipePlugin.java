package com.hakimen.kawaiidishes.integration.emi.plugins;

import com.hakimen.kawaiidishes.item.IDyeableItem;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import dev.emi.emi.api.recipe.EmiCraftingRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.nbt.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import java.util.ArrayList;
import java.util.List;

public class GenericOverlayableRecipePlugin extends EmiCraftingRecipe {

    public GenericOverlayableRecipePlugin(ItemStack item, ItemStack output, ResourceLocation id) {
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

            ListTag list = new ListTag();

            list.add(StringTag.valueOf("\"This is an example!\""));

            CompoundTag compound = new CompoundTag();
            compound.put("Lore",list);

            stacc.getOrCreateTag().put("display", compound);

            this.input.addAll(List.of(
                    EmiIngredient.of(Ingredient.of(stacc)),
                    EmiIngredient.of(ItemTags.WOOL),
                    EmiIngredient.of(ItemTags.WOOL)
            ));
        }
    }

    public GenericOverlayableRecipePlugin(ItemStack item, Ingredient overlayItem, ItemStack output, ResourceLocation id) {
        super(new ArrayList<>(), EmiStack.of(output), id);
        var stacc = item.copy();
        stacc = IDyeableItem.dyeBase(stacc, List.of(DyeItem.byColor(DyeColor.BLACK)));

        ListTag list = new ListTag();

        list.add(StringTag.valueOf("\"This is an example!\""));

        CompoundTag compound = new CompoundTag();
        compound.put("Lore",list);

        stacc.getOrCreateTag().put("display", compound);

        this.input.addAll(List.of(
                EmiIngredient.of(Ingredient.of(stacc)),
                EmiIngredient.of(overlayItem)
        ));
    }

}
