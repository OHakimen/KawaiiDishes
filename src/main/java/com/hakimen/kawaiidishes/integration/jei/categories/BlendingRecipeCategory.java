package com.hakimen.kawaiidishes.integration.jei.categories;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.integration.jei.JEIIntegration;
import com.hakimen.kawaiidishes.recipes.BlenderRecipe;
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

import javax.annotation.Nonnull;

public class BlendingRecipeCategory implements IRecipeCategory<BlenderRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(KawaiiDishes.modId, "blending");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(KawaiiDishes.modId, "textures/integration/jei/blender_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public BlendingRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockRegister.blender.get()));
    }

    @Override
    public RecipeType<BlenderRecipe> getRecipeType() {
        return JEIIntegration.blending;
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
        for (int i = 0; i < recipe.getIngredients().get(0).getItems().length; i++) {
            builder.addSlot(RecipeIngredientRole.INPUT,45,27+(i*18)).addItemStack(recipe.getIngredients().get(0).getItems()[i]);

        }
        builder.addSlot(RecipeIngredientRole.OUTPUT, 122, 36).addItemStack(recipe.getOnOutput());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 122, 61).addItemStack(recipe.getResultItem());
    }
}
