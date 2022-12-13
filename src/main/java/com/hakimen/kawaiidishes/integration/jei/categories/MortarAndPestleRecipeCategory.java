package com.hakimen.kawaiidishes.integration.jei.categories;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.integration.jei.JEIIntegration;
import com.hakimen.kawaiidishes.recipes.MortarAndPestleRecipe;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
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

public class MortarAndPestleRecipeCategory implements IRecipeCategory<MortarAndPestleRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(KawaiiDishes.modId, "mortar_grinding");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(KawaiiDishes.modId, "textures/integration/jei/mortar_grinding_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public MortarAndPestleRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockRegister.mortarAndPestle.get()));
    }


    @Override
    public RecipeType<MortarAndPestleRecipe> getRecipeType() {
        return JEIIntegration.mortarGrinding;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Griding");
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
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull MortarAndPestleRecipe recipe, @Nonnull IFocusGroup focusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT,19,29).addItemStack(recipe.getIngredients().get(0).getItems()[0]);

        builder.addSlot(RecipeIngredientRole.CATALYST,80,29).addItemStack(ItemRegister.mortarAndPestle.get().getDefaultInstance());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 131, 29).addItemStack(recipe.getResultItem());
    }
}
