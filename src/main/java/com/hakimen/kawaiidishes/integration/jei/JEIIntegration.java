package com.hakimen.kawaiidishes.integration.jei;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.screens.CoffeeMachineScreen;
import com.hakimen.kawaiidishes.containers.CoffeeMachineContainer;
import com.hakimen.kawaiidishes.integration.jei.categories.BlenderRecipeCategory;
import com.hakimen.kawaiidishes.integration.jei.categories.CoffeeMachineRecipeCategory;
import com.hakimen.kawaiidishes.integration.jei.categories.IceCreamMakerRecipeCategory;
import com.hakimen.kawaiidishes.item.IDyeableItem;
import com.hakimen.kawaiidishes.recipes.BlenderRecipe;
import com.hakimen.kawaiidishes.recipes.CoffeeMachineRecipe;
import com.hakimen.kawaiidishes.recipes.IceCreamMakerRecipe;
import com.hakimen.kawaiidishes.recipes.crafting.DyeIDyeableRecipe;
import com.hakimen.kawaiidishes.registry.ContainerRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import mezz.jei.api.recipe.transfer.IRecipeTransferError;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandler;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.levelgen.blending.Blender;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@JeiPlugin
public class JEIIntegration implements IModPlugin {

    public static RecipeType<CoffeeMachineRecipe> coffeeMachineRecipeRecipeType = RecipeType.create(KawaiiDishes.MODID, "coffee_machining", CoffeeMachineRecipe.class);
    public static RecipeType<BlenderRecipe> blenderRecipeRecipeType = RecipeType.create(KawaiiDishes.MODID, "blending", BlenderRecipe.class);
    public static RecipeType<IceCreamMakerRecipe> iceCreamMakerRecipeRecipeType = RecipeType.create(KawaiiDishes.MODID, "ice_cream_making", IceCreamMakerRecipe.class);

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(CoffeeMachineScreen.class,139,37,6,14, coffeeMachineRecipeRecipeType);
        registration.addRecipeClickArea(CoffeeMachineScreen.class,110,35,6,14, blenderRecipeRecipeType);
        registration.addRecipeClickArea(CoffeeMachineScreen.class,119,37,6,14, iceCreamMakerRecipeRecipeType);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CoffeeMachineRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new BlenderRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new IceCreamMakerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ItemRegister.COFFEE_MACHINE.get().getDefaultInstance(), coffeeMachineRecipeRecipeType);
        registration.addRecipeCatalyst(ItemRegister.BLENDER.get().getDefaultInstance(), blenderRecipeRecipeType);
        registration.addRecipeCatalyst(ItemRegister.ICE_CREAM_MAKER.get().getDefaultInstance(), iceCreamMakerRecipeRecipeType);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<CoffeeMachineRecipe> coffeeMachineRecipes = rm.getAllRecipesFor(CoffeeMachineRecipe.Type.INSTANCE)
                .stream().map(RecipeHolder::value).toList();
        List<BlenderRecipe> blenderRecipes = rm.getAllRecipesFor(BlenderRecipe.Type.INSTANCE)
                .stream().map(RecipeHolder::value).toList();
        List<IceCreamMakerRecipe> iceCreamMakerRecipes = rm.getAllRecipesFor(IceCreamMakerRecipe.Type.INSTANCE)
                .stream().map(RecipeHolder::value).toList();

        registration.addRecipes(coffeeMachineRecipeRecipeType, coffeeMachineRecipes);
        registration.addRecipes(blenderRecipeRecipeType, blenderRecipes);
        registration.addRecipes(iceCreamMakerRecipeRecipeType, iceCreamMakerRecipes);
    }

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(KawaiiDishes.MODID, "jei_integration");
    }
}
