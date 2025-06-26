package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.custom.Recorder;
import com.hakimen.kawaiidishes.recipes.BlenderRecipe;
import com.hakimen.kawaiidishes.recipes.CoffeeMachineRecipe;
import com.hakimen.kawaiidishes.recipes.IceCreamMakerRecipe;
import com.hakimen.kawaiidishes.recipes.crafting.*;
import java.util.function.Supplier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;

public class RecipeRegister {
    public static final Recorder<RecipeSerializer<?>> RECIPE_SERIALIZERS = new Recorder<>(BuiltInRegistries.RECIPE_SERIALIZER, KawaiiDishes.MODID);

    public static final Supplier<RecipeSerializer<DyeIDyeableRecipe>> DYE_IDYEABLE =
            RECIPE_SERIALIZERS.register("dye_idyeable", () -> new SimpleCraftingRecipeSerializer<>(DyeIDyeableRecipe::new));
    public static final Supplier<RecipeSerializer<ThighHighOverlayRecipe>> THIGH_HIGH_DECORATION =
            RECIPE_SERIALIZERS.register("thigh_high_decoration", () -> new SimpleCraftingRecipeSerializer<>(ThighHighOverlayRecipe::new));

    public static final Supplier<RecipeSerializer<MaidDressOverlayRecipe>> MAID_DRESS_APRON =
            RECIPE_SERIALIZERS.register("maid_dress_apron", () -> new SimpleCraftingRecipeSerializer<>(MaidDressOverlayRecipe::new));

    public static final Supplier<RecipeSerializer<OverlayIDyeable>> OVERLAY_IDYEABLE =
            RECIPE_SERIALIZERS.register("overlay_idyeable", () -> new SimpleCraftingRecipeSerializer<>(OverlayIDyeable::new));

    public static final Supplier<RecipeSerializer<MaidDressWithTailRecipe>> MAID_DRESS_TAIL =
            RECIPE_SERIALIZERS.register("combine_maid_dress_tail", () -> new SimpleCraftingRecipeSerializer<>(MaidDressWithTailRecipe::new));
    public static final Supplier<RecipeSerializer<HeadBandWithEarsRecipe>> HEAD_BAND_EARS =
            RECIPE_SERIALIZERS.register("combine_head_band_ears", () -> new SimpleCraftingRecipeSerializer<>(HeadBandWithEarsRecipe::new));

    public static final Supplier<RecipeSerializer<CoffeeMachineRecipe>> COFFEE_MACHINE_RECIPE =
            RECIPE_SERIALIZERS.register(CoffeeMachineRecipe.Type.ID, () -> CoffeeMachineRecipe.Serializer.INSTANCE);
    public static final Supplier<RecipeSerializer<IceCreamMakerRecipe>> ICE_CREAM_MAKER_RECIPE =
            RECIPE_SERIALIZERS.register(IceCreamMakerRecipe.Type.ID, () -> IceCreamMakerRecipe.Serializer.INSTANCE);

    public static final Supplier<RecipeSerializer<BlenderRecipe>> BLENDING =
            RECIPE_SERIALIZERS.register(BlenderRecipe.Type.ID, () -> BlenderRecipe.Serializer.INSTANCE);
    public static void register(){
        //Bootstrap
    }
            
}
