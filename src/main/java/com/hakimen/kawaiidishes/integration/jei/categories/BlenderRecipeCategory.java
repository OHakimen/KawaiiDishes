package com.hakimen.kawaiidishes.integration.jei.categories;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.integration.jei.JEIIntegration;
import com.hakimen.kawaiidishes.recipes.BlenderRecipe;
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
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidType;

import javax.annotation.Nonnull;

public class BlenderRecipeCategory implements IRecipeCategory<BlenderRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(KawaiiDishes.MODID, "coffee_machining");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(KawaiiDishes.MODID, "textures/integration/jei/blender_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public BlenderRecipeCategory(IGuiHelper helper) {
        this.background = helper.drawableBuilder(TEXTURE, 0, 0, 168, 80).setTextureSize(168, 80).build();
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockRegister.BLENDER.get()));
    }

    @Override
    public RecipeType<BlenderRecipe> getRecipeType() {
        return JEIIntegration.blenderRecipeRecipeType;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.kawaiidishes.recipe.blending");
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
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull BlenderRecipe recipe, @Nonnull IFocusGroup focusGroup) {
        for (int i = 0; i < recipe.getRecipeItems().get(0).getItems().length; i++) {
            builder.addSlot(RecipeIngredientRole.INPUT, 49,12 + (18 * i)).addItemStack(recipe.getRecipeItems().get(0).getItems()[i]);
        }

        builder.addSlot(RecipeIngredientRole.INPUT,101,12).addItemStack(recipe.getItemOnOutput());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 101, 48).addItemStack(recipe.getResultItem(null));
    }
}
