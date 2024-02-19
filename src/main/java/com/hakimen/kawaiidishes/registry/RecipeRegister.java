package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.recipes.BlenderRecipe;
import com.hakimen.kawaiidishes.recipes.CoffeeMachineRecipe;
import com.hakimen.kawaiidishes.recipes.IceCreamMakerRecipe;
import com.hakimen.kawaiidishes.recipes.crafting.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RecipeRegister {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, KawaiiDishes.MODID);
    public static final DeferredHolder<RecipeSerializer<?>,RecipeSerializer<DyeIDyeableRecipe>> DYE_IDYEABLE =
            SERIALIZERS.register("dye_idyeable", () -> new SimpleCraftingRecipeSerializer<>(DyeIDyeableRecipe::new));
    public static final DeferredHolder<RecipeSerializer<?>,RecipeSerializer<ThighHighOverlayRecipe>> THIGH_HIGH_DECORATION =
            SERIALIZERS.register("thigh_high_decoration", () -> new SimpleCraftingRecipeSerializer<>(ThighHighOverlayRecipe::new));

    public static final DeferredHolder<RecipeSerializer<?>,RecipeSerializer<MaidDressOverlayRecipe>> MAID_DRESS_APRON =
            SERIALIZERS.register("maid_dress_apron", () -> new SimpleCraftingRecipeSerializer<>(MaidDressOverlayRecipe::new));
    public static final DeferredHolder<RecipeSerializer<?>,RecipeSerializer<OverlayIDyeable>> OVERLAY_IDYEABLE =
            SERIALIZERS.register("overlay_idyeable", () -> new SimpleCraftingRecipeSerializer<>(OverlayIDyeable::new));

    public static final DeferredHolder<RecipeSerializer<?>,RecipeSerializer<MaidDressWithTailRecipe>> MAID_DRESS_TAIL =
            SERIALIZERS.register("combine_maid_dress_tail", () -> new SimpleCraftingRecipeSerializer<>(MaidDressWithTailRecipe::new));
    public static final DeferredHolder<RecipeSerializer<?>,RecipeSerializer<HeadBandWithEarsRecipe>> HEAD_BAND_EARS =
            SERIALIZERS.register("combine_head_band_ears", () -> new SimpleCraftingRecipeSerializer<>(HeadBandWithEarsRecipe::new));

    public static final DeferredHolder<RecipeSerializer<?>,RecipeSerializer<CoffeeMachineRecipe>> COFFEE_MACHINE_RECIPE =
            SERIALIZERS.register(CoffeeMachineRecipe.Type.ID, () -> CoffeeMachineRecipe.Serializer.INSTANCE);

    public static final DeferredHolder<RecipeSerializer<?>,RecipeSerializer<BlenderRecipe>> BLENDING_RECIPE =
            SERIALIZERS.register(BlenderRecipe.Type.ID, () -> BlenderRecipe.Serializer.INSTANCE);
    public static final DeferredHolder<RecipeSerializer<?>,RecipeSerializer<IceCreamMakerRecipe>> ICE_CREAM_MAKING =
            SERIALIZERS.register(IceCreamMakerRecipe.Type.ID, () -> IceCreamMakerRecipe.Serializer.INSTANCE);

    public static void register(IEventBus bus){
        SERIALIZERS.register(bus);
    }
}
