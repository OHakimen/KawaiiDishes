package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.recipes.CoffeeMachineRecipe;
import com.hakimen.kawaiidishes.recipes.CoffeePressRecipe;
import com.hakimen.kawaiidishes.recipes.MortarAndPestleRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeRegister {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, KawaiiDishes.modId);

    public static final RegistryObject<RecipeSerializer<CoffeePressRecipe>> CoffeePressRecipeSerializer =
            SERIALIZERS.register("coffee_pressing", () -> CoffeePressRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<CoffeeMachineRecipe>> CoffeeMachineRecipeSerializer =
            SERIALIZERS.register("coffee_machining", () -> CoffeeMachineRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<MortarAndPestleRecipe>> MortarGrindingRecipe =
            SERIALIZERS.register("mortar_grinding", () -> MortarAndPestleRecipe.Serializer.INSTANCE);


    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
