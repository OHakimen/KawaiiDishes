package com.hakimen.kawaiidishes.integration.jei.categories;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.integration.jei.JEIIntegration;
import com.hakimen.kawaiidishes.recipes.IceCreamMachineRecipe;
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

public class IceCreamMachineRecipeCategory implements IRecipeCategory<IceCreamMachineRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(KawaiiDishes.modId, "ice_cream_making");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(KawaiiDishes.modId, "textures/integration/jei/ice_cream_machine_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public IceCreamMachineRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockRegister.iceCreamMachine.get()));
    }


    @Override
    public RecipeType<IceCreamMachineRecipe> getRecipeType() {
        return JEIIntegration.iceCreamMaking;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.kawaiidishes.recipe.ice_cream_making");
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
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull IceCreamMachineRecipe recipe, @Nonnull IFocusGroup focusGroup) {


        builder.addSlot(RecipeIngredientRole.INPUT,55,29).addItemStack(recipe.getIngredients().get(0).getItems()[0]);


        for (int i = 1; i < recipe.getIngredients().get(0).getItems().length; i++) {
            builder.addSlot(RecipeIngredientRole.INPUT,19,11+((i-1)*18)).addItemStack(recipe.getIngredients().get(0).getItems()[i]);
        }


        builder.addSlot(RecipeIngredientRole.INPUT,115,29).addItemStack(recipe.getItemOnOutput());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 115, 54).addItemStack(recipe.getResultItem());
    }
}
