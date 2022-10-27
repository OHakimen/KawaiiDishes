package com.hakimen.kawaiidishes.integration.jei.categories;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.recipes.CoffeeMachineRecipe;
import com.hakimen.kawaiidishes.recipes.CoffeePressRecipe;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.Nonnull;

public class CoffeePressRecipeCategory implements IRecipeCategory<CoffeePressRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(KawaiiDishes.modId, "coffee_pressing");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(KawaiiDishes.modId, "textures/integration/jei/coffee_press_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public CoffeePressRecipeCategory(IGuiHelper helper) {

        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(BlockRegister.coffeePress.get()));

    }


    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends CoffeePressRecipe> getRecipeClass() {
        return CoffeePressRecipe.class;
    }

    @Override
    public Component getTitle() {
        return new TextComponent("Coffee Pressing");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull CoffeePressRecipe recipe, @Nonnull IFocusGroup focusGroup) {
        for (int i = 0; i < recipe.getIngredients().get(0).getItems().length; i++) {
            builder.addSlot(RecipeIngredientRole.INPUT,19,11+(i*18)).addItemStack(recipe.getIngredients().get(0).getItems()[i]);

        }

        builder.addSlot(RecipeIngredientRole.CATALYST,80,29).addItemStack(ItemRegister.coffeePress.get().getDefaultInstance());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 131, 29).addItemStack(recipe.getResultItem());
    }
}
