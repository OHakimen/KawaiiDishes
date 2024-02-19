package com.hakimen.kawaiidishes.integration.jei.categories;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.block_entities.CoffeeMachineBlockEntity;
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
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.FluidUtil;

import javax.annotation.Nonnull;

public class CoffeeMachineRecipeCategory  implements IRecipeCategory<CoffeeMachineRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(KawaiiDishes.MODID, "coffee_machining");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(KawaiiDishes.MODID, "textures/integration/jei/coffee_machine_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public CoffeeMachineRecipeCategory(IGuiHelper helper) {
        this.background = helper.drawableBuilder(TEXTURE, 0, 0, 168, 80).setTextureSize(168, 80).build();
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockRegister.COFFEE_MACHINE.get()));
    }

    @Override
    public RecipeType<CoffeeMachineRecipe> getRecipeType() {
        return JEIIntegration.coffeeMachineRecipeRecipeType;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.kawaiidishes.recipe.coffee_machining");
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

        if(recipe.getWaterNeeded() > 0){
            builder.addSlot(RecipeIngredientRole.INPUT,45,14).setFluidRenderer(FluidType.BUCKET_VOLUME * 4, true, 12,52).addFluidStack(Fluids.WATER, recipe.getWaterNeeded());
        }

        for (int i = 0; i < recipe.getRecipeItems().get(0).getItems().length; i++) {
            builder.addSlot(RecipeIngredientRole.INPUT, 76,14 + (18 * i)).addItemStack(recipe.getRecipeItems().get(0).getItems()[i]);
        }

        builder.addSlot(RecipeIngredientRole.INPUT,130,14).addItemStack(recipe.getItemOnOutput());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 130, 50).addItemStack(recipe.getResultItem(null));
    }
}
