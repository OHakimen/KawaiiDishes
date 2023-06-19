package com.hakimen.kawaiidishes.integration.jei.categories;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.integration.jei.JEIIntegration;
import com.hakimen.kawaiidishes.recipes.CoffeeMachineRecipe;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.Nonnull;

public class CoffeeMachineRecipeCategory implements IRecipeCategory<CoffeeMachineRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(KawaiiDishes.modId, "coffee_machining");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(KawaiiDishes.modId, "textures/integration/jei/coffee_machine_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public CoffeeMachineRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockRegister.coffeeMachine.get()));
    }

    @Override
    public RecipeType<CoffeeMachineRecipe> getRecipeType() {
        return JEIIntegration.coffeeMachining;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Coffee Machining");
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
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull CoffeeMachineRecipe recipe, @Nonnull IFocusGroup focusGroup) {
        if(recipe.requireMilk()){
            builder.addSlot(RecipeIngredientRole.INPUT, 19, 47).addIngredients(Ingredient.of(Items.MILK_BUCKET));
        }
        if(recipe.requireWater()){
            builder.addSlot(RecipeIngredientRole.INPUT, 19, 11).addIngredients(Ingredient.of(Items.WATER_BUCKET));
        }


        for (int i = 0; i < recipe.getIngredients().get(0).getItems().length; i++) {
            builder.addSlot(RecipeIngredientRole.INPUT,55,11+(i*18)).addItemStack(recipe.getIngredients().get(0).getItems()[i]);

        }


        builder.addSlot(RecipeIngredientRole.INPUT,115,29).addItemStack(recipe.getItemOnOutput());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 115, 54).addItemStack(recipe.getResultItem(null));
    }
}
