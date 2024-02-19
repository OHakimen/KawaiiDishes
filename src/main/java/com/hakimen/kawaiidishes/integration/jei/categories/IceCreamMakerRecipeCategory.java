package com.hakimen.kawaiidishes.integration.jei.categories;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.integration.jei.JEIIntegration;
import com.hakimen.kawaiidishes.recipes.BlenderRecipe;
import com.hakimen.kawaiidishes.recipes.IceCreamMakerRecipe;
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

import javax.annotation.Nonnull;

public class IceCreamMakerRecipeCategory implements IRecipeCategory<IceCreamMakerRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(KawaiiDishes.MODID, "coffee_machining");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(KawaiiDishes.MODID, "textures/integration/jei/ice_cream_maker_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public IceCreamMakerRecipeCategory(IGuiHelper helper) {
        this.background = helper.drawableBuilder(TEXTURE, 0, 0, 168, 80).setTextureSize(168, 80).build();
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockRegister.ICE_CREAM_MAKER.get()));
    }

    @Override
    public RecipeType<IceCreamMakerRecipe> getRecipeType() {
        return JEIIntegration.iceCreamMakerRecipeRecipeType;
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
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull IceCreamMakerRecipe recipe, @Nonnull IFocusGroup focusGroup) {

        builder.addSlot(RecipeIngredientRole.INPUT, 22,14).addItemStack(new ItemStack(Items.SNOWBALL, recipe.getSnowballs()));

        for (int i = 0; i < recipe.getRecipeItems().get(0).getItems().length; i++) {
            builder.addSlot(RecipeIngredientRole.INPUT, 58,14 + (18 * i)).addItemStack(recipe.getRecipeItems().get(0).getItems()[i]);
        }

        builder.addSlot(RecipeIngredientRole.INPUT,110,14).addItemStack(recipe.getItemOnOutput());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 110, 50).addItemStack(recipe.getResultItem(null));
    }
}
