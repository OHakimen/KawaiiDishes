package com.hakimen.kawaiidishes.integration.jei;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.integration.jei.categories.*;
import com.hakimen.kawaiidishes.recipes.*;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIIntegration implements IModPlugin {

    RecipeType<CoffeeMachineRecipe> coffeeMachining = RecipeType.create(KawaiiDishes.modId,"coffee_machining",CoffeeMachineRecipe.class);
    RecipeType<CoffeePressRecipe> coffeePressing = RecipeType.create(KawaiiDishes.modId,"coffee_pressing", CoffeePressRecipe.class);
    RecipeType<IceCreamMachineRecipe> iceCreamMaking = RecipeType.create(KawaiiDishes.modId,"ice_cream_making", IceCreamMachineRecipe.class);
    RecipeType<BlenderRecipe> blending = RecipeType.create(KawaiiDishes.modId,"blending", BlenderRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(KawaiiDishes.modId, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                CoffeeMachineRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new
                CoffeePressRecipeCategory(registration.getJeiHelpers().getGuiHelper()));

        registration.addRecipeCategories(new
                IceCreamMachineRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new
                BlendingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ItemRegister.coffeePress.get().getDefaultInstance(),coffeePressing);
        registration.addRecipeCatalyst(ItemRegister.coffeeMachine.get().getDefaultInstance(),coffeeMachining);
        registration.addRecipeCatalyst(ItemRegister.iceCreamMachine.get().getDefaultInstance(),iceCreamMaking);
        registration.addRecipeCatalyst(ItemRegister.blender.get().getDefaultInstance(),blending);

    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<CoffeeMachineRecipe> coffeeMachineRecipes = rm.getAllRecipesFor(CoffeeMachineRecipe.Type.INSTANCE);
        List<CoffeePressRecipe> coffeePressRecipes = rm.getAllRecipesFor(CoffeePressRecipe.Type.INSTANCE);
        List<IceCreamMachineRecipe> iceCreamMachineRecipes = rm.getAllRecipesFor(IceCreamMachineRecipe.Type.INSTANCE);
        List<BlenderRecipe> blenderRecipes = rm.getAllRecipesFor(BlenderRecipe.Type.INSTANCE);

        registration.addRecipes(coffeeMachining, coffeeMachineRecipes);
        registration.addRecipes(coffeePressing, coffeePressRecipes);
        registration.addRecipes(iceCreamMaking, iceCreamMachineRecipes);
        registration.addRecipes(blending, blenderRecipes);
    }
}
