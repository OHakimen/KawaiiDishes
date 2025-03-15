package com.hakimen.kawaiidishes.integration.jei;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.screens.BlenderScreen;
import com.hakimen.kawaiidishes.client.screens.CoffeeMachineScreen;
import com.hakimen.kawaiidishes.client.screens.IceCreamMakerScreen;
import com.hakimen.kawaiidishes.datagen.ItemTagDataGen;
import com.hakimen.kawaiidishes.integration.jei.categories.BlenderRecipeCategory;
import com.hakimen.kawaiidishes.integration.jei.categories.CoffeeMachineRecipeCategory;
import com.hakimen.kawaiidishes.integration.jei.categories.IceCreamMakerRecipeCategory;
import com.hakimen.kawaiidishes.recipes.BlenderRecipe;
import com.hakimen.kawaiidishes.recipes.CoffeeMachineRecipe;
import com.hakimen.kawaiidishes.recipes.IceCreamMakerRecipe;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIIntegration implements IModPlugin {

    public static RecipeType<CoffeeMachineRecipe> coffeeMachineRecipeRecipeType = RecipeType.create(KawaiiDishes.MODID, "coffee_machining", CoffeeMachineRecipe.class);
    public static RecipeType<BlenderRecipe> blenderRecipeRecipeType = RecipeType.create(KawaiiDishes.MODID, "blending", BlenderRecipe.class);
    public static RecipeType<IceCreamMakerRecipe> iceCreamMakerRecipeRecipeType = RecipeType.create(KawaiiDishes.MODID, "ice_cream_making", IceCreamMakerRecipe.class);

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(CoffeeMachineScreen.class,139,37,6,14, coffeeMachineRecipeRecipeType);
        registration.addRecipeClickArea(BlenderScreen.class,110,35,6,14, blenderRecipeRecipeType);
        registration.addRecipeClickArea(IceCreamMakerScreen.class,119,37,6,14, iceCreamMakerRecipeRecipeType);
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

        ItemRegister.ITEMS.getEntries().forEach(
                itemDeferredHolder -> {
                    if(itemDeferredHolder.is(ItemTagDataGen.OVERLAYABLE)){
                        registration.addIngredientInfo(itemDeferredHolder.get().getDefaultInstance(), VanillaTypes.ITEM_STACK,
                                Component.literal("This item can be dyed and overlayed in a crafting table with 2 wool (any color)"));
                    }
                    if(itemDeferredHolder.equals(ItemRegister.MAID_DRESS)){
                        registration.addIngredientInfo(itemDeferredHolder.get().getDefaultInstance(), VanillaTypes.ITEM_STACK,
                                Component.literal("This item can be dyed and overlayed in a crafting table with an apron"));
                    }
                    if(itemDeferredHolder.equals(ItemRegister.THIGH_HIGHS)){
                        registration.addIngredientInfo(itemDeferredHolder.get().getDefaultInstance(), VanillaTypes.ITEM_STACK,
                                Component.literal("This item can be dyed and overlayed with any decoration item (see tags)"));
                    }
                }
        );

    }

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "jei_integration");
    }
}
