package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.datagen.recipeBuilder.BlenderRecipeBuilder;
import com.hakimen.kawaiidishes.datagen.recipeBuilder.CoffeeMachineRecipeBuilder;
import com.hakimen.kawaiidishes.datagen.recipeBuilder.IceCreamMachineRecipeBuilder;
import com.hakimen.kawaiidishes.recipes.BlenderRecipe;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.internal.NeoForgeItemTagsProvider;
import org.checkerframework.checker.units.qual.K;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RecipeDataGen extends RecipeProvider {
    public RecipeDataGen(DataGenerator generator, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(generator.getPackOutput(), lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput pOutput) {
        crafting(pOutput);
        coffeeMachine(pOutput);
        blender(pOutput);
        furnaceRecipes(pOutput);
        iceCreamMaker(pOutput);
    }

    public void furnaceRecipes(RecipeOutput output) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemRegister.COFFEE_BERRIES.get()), RecipeCategory.FOOD, ItemRegister.COFFEE_BEANS.get(), 1.4f, 200)
                .unlockedBy(getHasName(ItemRegister.COFFEE_BERRIES.get()), has(ItemRegister.COFFEE_BERRIES.get()))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "coffee_beans_from_furnaces"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemRegister.COFFEE_BEANS.get()), RecipeCategory.FOOD, ItemRegister.ROAST_COFFEE_BEANS.get(), 1.4f, 200)
                .unlockedBy(getHasName(ItemRegister.COFFEE_BEANS.get()), has(ItemRegister.COFFEE_BEANS.get()))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "roast_coffee_beans_from_furnaces"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ItemRegister.COFFEE_BERRIES.get()), RecipeCategory.FOOD, ItemRegister.COFFEE_BEANS.get(), 1.4f, 100)
                .unlockedBy(getHasName(ItemRegister.COFFEE_BERRIES.get()), has(ItemRegister.COFFEE_BERRIES.get()))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "coffee_beans_from_smoking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ItemRegister.COFFEE_BEANS.get()), RecipeCategory.FOOD, ItemRegister.ROAST_COFFEE_BEANS.get(), 1.4f, 100)
                .unlockedBy(getHasName(ItemRegister.COFFEE_BEANS.get()), has(ItemRegister.COFFEE_BEANS.get()))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "roast_coffee_beans_from_smoking"));

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ItemRegister.COFFEE_BERRIES.get()), RecipeCategory.FOOD, ItemRegister.COFFEE_BEANS.get(), 1.4f, 200)
                .unlockedBy(getHasName(ItemRegister.COFFEE_BERRIES.get()), has(ItemRegister.COFFEE_BERRIES.get()))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "coffee_beans_from_campfires"));

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ItemRegister.COFFEE_BEANS.get()), RecipeCategory.FOOD, ItemRegister.ROAST_COFFEE_BEANS.get(), 1.4f, 200)
                .unlockedBy(getHasName(ItemRegister.COFFEE_BEANS.get()), has(ItemRegister.COFFEE_BEANS.get()))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "roast_coffee_beans_from_campfires"));
    }


    public void crafting(RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.APRON.get())
                .pattern(" @ ")
                .pattern("@x@")
                .pattern("xxx")
                .define('@', Tags.Items.STRING)
                .define('x', ItemTags.WOOL)
                .unlockedBy(getHasName(Items.STRING), has(Tags.Items.STRING))
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "apron"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.MAID_DRESS.get())
                .pattern("x x")
                .pattern("xxx")
                .pattern("xxx")
                .define('x', ItemTags.WOOL)
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "maid_dress"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.THIGH_HIGHS.get())
                .pattern("x x")
                .pattern("x x")
                .pattern("x x")
                .define('x', ItemTags.WOOL)
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "thigh_highs"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.DOUBLE_BANDS.get())
                .pattern("xxx")
                .define('x', Tags.Items.STRING)
                .unlockedBy(getHasName(Items.STRING), has(Tags.Items.STRING))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "double_bands"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.FULL_BANDS.get())
                .pattern("xxx")
                .pattern("   ")
                .pattern("xxx")
                .define('x', Tags.Items.STRING)
                .unlockedBy(getHasName(Items.STRING), has(Tags.Items.STRING))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "full_bands"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.LEG_CLIP.get())
                .pattern("x,x")
                .pattern(" x ")
                .pattern("xxx")
                .define('x', Tags.Items.STRING)
                .define(',', Tags.Items.NUGGETS_IRON)
                .unlockedBy(getHasName(Items.STRING), has(Tags.Items.STRING))
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Tags.Items.NUGGETS_IRON))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "leg_clip"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.BOW.get())
                .pattern(" xx")
                .pattern("xsx")
                .pattern("xx ")
                .define('x', ItemTags.WOOL)
                .define('s', Tags.Items.STRING)
                .unlockedBy(getHasName(Items.STRING), has(Tags.Items.STRING))
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "bow"));


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.HEAD_BAND.get())
                .pattern("xxx")
                .pattern("x x")
                .define('x', ItemTags.WOOL)
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "head_band"));


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.FOX_EARS.get())
                .pattern("x x")
                .pattern("s s")
                .define('x', ItemTags.WOOL)
                .define('s', Items.PINK_WOOL)
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "fox_ears"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.FOX_TAIL.get())
                .pattern(" xx")
                .pattern(" @x")
                .pattern("@  ")
                .define('x', ItemTags.WOOL)
                .define('@', Tags.Items.STRING)
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .unlockedBy(getHasName(Items.STRING), has(Tags.Items.STRING))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "fox_tail"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.BUNNY_EARS.get())
                .pattern("x x")
                .pattern("s s")
                .pattern("s s")
                .define('x', ItemTags.WOOL)
                .define('s', Items.PINK_WOOL)
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "bunny_ears"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.BUNNY_TAIL.get())
                .pattern("xx")
                .pattern("@x")
                .define('x', ItemTags.WOOL)
                .define('@', Items.STRING)
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .unlockedBy(getHasName(Items.STRING), has(Tags.Items.STRING))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "bunny_tail"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.SHOES.get())
                .pattern("x x")
                .pattern("l l")
                .define('x', ItemTags.WOOL)
                .define('l', Items.LEATHER)
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "shoes"));


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.KITCHEN_TILES.get(), 4)
                .pattern("xy")
                .pattern("yx")
                .define('x', Items.WHITE_CONCRETE)
                .define('y', Items.BLACK_CONCRETE)
                .unlockedBy(getHasName(Items.WHITE_CONCRETE), has(Items.WHITE_CONCRETE))
                .unlockedBy(getHasName(Items.BLACK_CONCRETE), has(Items.BLACK_CONCRETE))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "kitchen_tiles"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.DISPLAY_CASE.get())
                .pattern("xxx")
                .pattern("xcx")
                .pattern("---")
                .define('x', Tags.Items.GLASS_PANES)
                .define('c', Items.CHEST)
                .define('-', Tags.Items.INGOTS_IRON)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .unlockedBy(getHasName(Items.CHEST), has(Items.CHEST))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Tags.Items.INGOTS_IRON))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "display_case"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.COFFEE_MACHINE.get())
                .pattern("xxx")
                .pattern("xux")
                .pattern("---")
                .define('x', Tags.Items.NUGGETS_IRON)
                .define('u', Items.BUCKET)
                .define('-', Tags.Items.INGOTS_IRON)
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Tags.Items.NUGGETS_IRON))
                .unlockedBy(getHasName(Items.BUCKET), has(Items.BUCKET))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Tags.Items.INGOTS_IRON))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "coffee_machine"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.BLENDER.get())
                .pattern("xxx")
                .pattern("x x")
                .pattern("-,-")
                .define('x', Tags.Items.GLASS_PANES)
                .define(',', Tags.Items.NUGGETS_IRON)
                .define('-', Tags.Items.INGOTS_IRON)
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Tags.Items.NUGGETS_IRON))
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Tags.Items.INGOTS_IRON))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "blender"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.ICE_CREAM_MAKER.get())
                .pattern("l-x")
                .pattern("-sx")
                .pattern("---")
                .define('x', Tags.Items.GLASS_PANES)
                .define('l', Items.LEVER)
                .define('s', Items.SNOW_BLOCK)
                .define('-', Tags.Items.INGOTS_IRON)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .unlockedBy(getHasName(Items.LEVER), has(Items.LEVER))
                .unlockedBy(getHasName(Items.SNOW_BLOCK), has(Items.SNOW_BLOCK))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Tags.Items.INGOTS_IRON))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "ice_cream_maker"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.SEAT.get())
                .pattern("xox")
                .pattern(" x ")
                .pattern("xxx")
                .define('x', ItemTags.PLANKS)
                .define('o', ItemTags.WOOL)
                .unlockedBy(getHasName(Items.OAK_PLANKS), has(ItemTags.PLANKS))
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "seat"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.MUG.get())
                .pattern("x x")
                .pattern("x x")
                .pattern(" x ")
                .define('x', Items.BRICK)
                .unlockedBy(getHasName(Items.BRICK), has(Items.BRICK))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "mug"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegister.APPLE_PIE.get())
                .requires(Items.EGG)
                .requires(Items.SUGAR)
                .requires(Items.APPLE)
                .requires(Items.WHEAT)
                .unlockedBy(getHasName(Items.EGG), has(Items.EGG))
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .unlockedBy(getHasName(Items.APPLE), has(Items.APPLE))
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "apple_pie"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegister.GLOW_BERRY_PIE.get())
                .requires(Items.EGG)
                .requires(Items.SUGAR)
                .requires(Items.GLOW_BERRIES)
                .requires(Items.WHEAT)
                .unlockedBy(getHasName(Items.EGG), has(Items.EGG))
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .unlockedBy(getHasName(Items.GLOW_BERRIES), has(Items.GLOW_BERRIES))
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "glow_berry_pie"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegister.SWEET_BERRY_PIE.get())
                .requires(Items.EGG)
                .requires(Items.SUGAR)
                .requires(Items.SWEET_BERRIES)
                .requires(Items.WHEAT)
                .unlockedBy(getHasName(Items.EGG), has(Items.EGG))
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .unlockedBy(getHasName(Items.APPLE), has(Items.APPLE))
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "sweet_berry_pie"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegister.CHERRY_PIE.get())
                .requires(Items.EGG)
                .requires(Items.SUGAR)
                .requires(ItemRegister.CHERRY.get())
                .requires(Items.WHEAT)
                .unlockedBy(getHasName(Items.EGG), has(Items.EGG))
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .unlockedBy(getHasName(ItemRegister.CHERRY.get()), has(ItemRegister.CHERRY.get()))
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "cherry_pie"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.CHEESE_CAKE.get())
                .pattern("xsx")
                .pattern("gsg")
                .pattern("ese")
                .define('x', Items.WHEAT)
                .define('s', ItemRegister.CREAM_CHEESE_BALL.get())
                .define('g', Items.SUGAR)
                .define('e', Items.EGG)
                .unlockedBy(getHasName(Items.EGG), has(Items.EGG))
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .unlockedBy(getHasName(ItemRegister.CREAM_CHEESE_BALL.get()), has(ItemRegister.CREAM_CHEESE_BALL.get()))
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "cheese_cake"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.CHOCOLATE_CHEESE_CAKE.get())
                .pattern("x-x")
                .pattern("gsg")
                .pattern("ese")
                .define('x', Items.WHEAT)
                .define('s', ItemRegister.CREAM_CHEESE_BALL.get())
                .define('g', Items.SUGAR)
                .define('e', Items.EGG)
                .define('-', ItemRegister.COCOA_POWDER.get())
                .unlockedBy(getHasName(Items.EGG), has(Items.EGG))
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .unlockedBy(getHasName(ItemRegister.COCOA_POWDER.get()), has(ItemRegister.COCOA_POWDER.get()))
                .unlockedBy(getHasName(ItemRegister.CREAM_CHEESE_BALL.get()), has(ItemRegister.CREAM_CHEESE_BALL.get()))
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "chocolate_cheese_cake"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.HONEY_CHEESE_CAKE.get())
                .pattern("x-x")
                .pattern("gsg")
                .pattern("ese")
                .define('x', Items.WHEAT)
                .define('s', ItemRegister.CREAM_CHEESE_BALL.get())
                .define('g', Items.SUGAR)
                .define('e', Items.EGG)
                .define('-', Items.HONEY_BOTTLE)
                .unlockedBy(getHasName(Items.EGG), has(Items.EGG))
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .unlockedBy(getHasName(Items.HONEY_BOTTLE), has(Items.HONEY_BOTTLE))
                .unlockedBy(getHasName(ItemRegister.CREAM_CHEESE_BALL.get()), has(ItemRegister.CREAM_CHEESE_BALL.get()))
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "honey_cheese_cake"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegister.CAKE_SLICE.get(), 8)
                .requires(Items.CAKE)
                .unlockedBy(getHasName(Items.CAKE), has(Items.CAKE))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "cake_slice"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegister.CHEESE_CAKE_SLICE.get(), 8)
                .requires(ItemRegister.CHEESE_CAKE.get())
                .unlockedBy(getHasName(ItemRegister.CHEESE_CAKE.get()), has(ItemRegister.CHEESE_CAKE.get()))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "cheese_cake_slice"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegister.CHOCOLATE_CHEESE_CAKE_SLICE.get(), 8)
                .requires(ItemRegister.CHOCOLATE_CHEESE_CAKE.get())
                .unlockedBy(getHasName(ItemRegister.CHOCOLATE_CHEESE_CAKE.get()), has(ItemRegister.CHOCOLATE_CHEESE_CAKE.get()))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "chocolate_cheese_cake_slice"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegister.HONEY_CHEESE_CAKE_SLICE.get(), 8)
                .requires(ItemRegister.HONEY_CHEESE_CAKE.get())
                .unlockedBy(getHasName(ItemRegister.HONEY_CHEESE_CAKE.get()), has(ItemRegister.HONEY_CHEESE_CAKE.get()))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "honey_cheese_cake_slice"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegister.SWEET_BERRY_PIE_SLICE.get(), 8)
                .requires(ItemRegister.SWEET_BERRY_PIE.get())
                .unlockedBy(getHasName(ItemRegister.SWEET_BERRY_PIE.get()), has(ItemRegister.SWEET_BERRY_PIE.get()))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "sweet_berry_pie_slice"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegister.GLOW_BERRY_PIE_SLICE.get(), 8)
                .requires(ItemRegister.GLOW_BERRY_PIE.get())
                .unlockedBy(getHasName(ItemRegister.GLOW_BERRY_PIE.get()), has(ItemRegister.GLOW_BERRY_PIE.get()))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "glow_berry_pie_slice"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegister.CHERRY_PIE_SLICE.get(), 8)
                .requires(ItemRegister.CHERRY_PIE.get())
                .unlockedBy(getHasName(ItemRegister.CHERRY_PIE.get()), has(ItemRegister.CHERRY_PIE.get()))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "cherry_pie_slice"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegister.APPLE_PIE_SLICE.get(), 8)
                .requires(ItemRegister.APPLE_PIE.get())
                .unlockedBy(getHasName(ItemRegister.APPLE_PIE.get()), has(ItemRegister.APPLE_PIE.get()))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "apple_pie_slice"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegister.WAFFLE.get(), 8)
                .requires(Items.EGG)
                .requires(Items.SUGAR)
                .requires(Items.WHEAT)
                .requires(Items.MILK_BUCKET)
                .unlockedBy(getHasName(Items.EGG), has(Items.EGG))
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .unlockedBy(getHasName(Items.MILK_BUCKET), has(Items.MILK_BUCKET))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "waffle"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegister.CHOCOLATE_WAFFLE.get(), 8)
                .requires(Items.EGG)
                .requires(Items.SUGAR)
                .requires(Items.WHEAT)
                .requires(Items.MILK_BUCKET)
                .requires(ItemRegister.COCOA_POWDER.get())
                .unlockedBy(getHasName(Items.EGG), has(Items.EGG))
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .unlockedBy(getHasName(Items.MILK_BUCKET), has(Items.MILK_BUCKET))
                .unlockedBy(getHasName(ItemRegister.COCOA_POWDER.get()), has(ItemRegister.COCOA_POWDER.get()))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "chocolate_waffle"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegister.CHOCOLATE_COOKIE.get(), 8)
                .requires(ItemRegister.COCOA_POWDER.get())
                .requires(Items.WHEAT)
                .requires(Items.WHEAT)
                .unlockedBy(getHasName(ItemRegister.COCOA_POWDER.get()), has(ItemRegister.COCOA_POWDER.get()))
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "chocolate_cookie"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegister.SWEET_BERRY_COOKIE.get(), 8)
                .requires(Items.SWEET_BERRIES)
                .requires(Items.WHEAT)
                .requires(Items.WHEAT)
                .unlockedBy(getHasName(Items.SWEET_BERRIES), has(Items.SWEET_BERRIES))
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "sweet_berry_cookie"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegister.GLOW_BERRY_COOKIE.get(), 8)
                .requires(Items.GLOW_BERRIES)
                .requires(Items.WHEAT)
                .requires(Items.WHEAT)
                .unlockedBy(getHasName(Items.GLOW_BERRIES), has(Items.GLOW_BERRIES))
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "glow_berry_cookie"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.GOLDEN_COOKIE.get())
                .pattern(" x ")
                .pattern("xcx")
                .pattern(" x ")
                .define('x', Items.GOLD_INGOT)
                .define('c', ItemTagDataGen.COOKIES)
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "golden_cookie"));


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.COOKIE_OF_UNBINDING.get())
                .pattern(" x ")
                .pattern("wcy")
                .pattern(" z ")
                .define('w', Items.WITHER_SKELETON_SKULL)
                .define('x', Items.GHAST_TEAR)
                .define('y', Items.FERMENTED_SPIDER_EYE)
                .define('z', Items.EXPERIENCE_BOTTLE)
                .define('c', ItemTagDataGen.COOKIES)
                .unlockedBy(getHasName(Items.WITHER_SKELETON_SKULL), has(Items.WITHER_SKELETON_SKULL))
                .unlockedBy(getHasName(Items.GHAST_TEAR), has(Items.GHAST_TEAR))
                .unlockedBy(getHasName(Items.FERMENTED_SPIDER_EYE), has(Items.FERMENTED_SPIDER_EYE))
                .unlockedBy(getHasName(Items.EXPERIENCE_BOTTLE), has(Items.EXPERIENCE_BOTTLE))
                .save(output, new ResourceLocation(KawaiiDishes.MODID, "cookie_of_unbinding"));

    }

    public void coffeeMachine(RecipeOutput pOutput) {
        registerCoffeeMachineRecipe(pOutput, "steamed_milk",
                ItemRegister.STEAMED_MILK_BUCKET.get().getDefaultInstance(),
                List.of(
                        Ingredient.of(
                                Items.MILK_BUCKET
                        )),
                200,
                0,
                Items.BUCKET.getDefaultInstance()
        );

        registerCoffeeMachineRecipe(pOutput, "dark_coffee",
                ItemRegister.DARK_COFFEE.get().getDefaultInstance(),
                List.of(Ingredient.of(
                        ItemRegister.GROUND_COFFEE.get(),
                        ItemRegister.GROUND_COFFEE.get(),
                        ItemRegister.GROUND_COFFEE.get()
                )),
                200,
                250,
                ItemRegister.MUG.get().getDefaultInstance()
        );

        registerCoffeeMachineRecipe(pOutput, "espresso",
                ItemRegister.ESPRESSO_COFFEE.get().getDefaultInstance(),
                List.of(Ingredient.of(
                        ItemRegister.GROUND_COFFEE.get(),
                        Items.SUGAR
                )),
                200,
                250,
                ItemRegister.MUG.get().getDefaultInstance()
        );

        registerCoffeeMachineRecipe(pOutput, "doppio",
                ItemRegister.DOPPIO_COFFEE.get().getDefaultInstance(),
                List.of(Ingredient.of(
                        ItemRegister.GROUND_COFFEE.get(),
                        ItemRegister.GROUND_COFFEE.get(),
                        Items.SUGAR
                )),
                200,
                250,
                ItemRegister.MUG.get().getDefaultInstance()
        );
        registerCoffeeMachineRecipe(pOutput, "macchiato",
                ItemRegister.MACCHIATO_COFFEE.get().getDefaultInstance(),
                List.of(Ingredient.of(
                        ItemRegister.GROUND_COFFEE.get(),
                        ItemRegister.MILK_FOAM_BUCKET.get()
                )),
                200,
                250,
                ItemRegister.MUG.get().getDefaultInstance()
        );
        registerCoffeeMachineRecipe(pOutput, "latte",
                ItemRegister.LATTE_COFFEE.get().getDefaultInstance(),
                List.of(Ingredient.of(
                        ItemRegister.GROUND_COFFEE.get(),
                        ItemRegister.STEAMED_MILK_BUCKET.get()
                )),
                200,
                250,
                ItemRegister.MUG.get().getDefaultInstance()
        );
        registerCoffeeMachineRecipe(pOutput, "capuccino",
                ItemRegister.CAPUCCINO_COFFEE.get().getDefaultInstance(),
                List.of(Ingredient.of(
                        ItemRegister.GROUND_COFFEE.get(),
                        ItemRegister.STEAMED_MILK_BUCKET.get(),
                        ItemRegister.MILK_FOAM_BUCKET.get()
                )),
                200,
                250,
                ItemRegister.MUG.get().getDefaultInstance()
        );

        registerCoffeeMachineRecipe(pOutput, "mocha",
                ItemRegister.MOCHA_COFFEE.get().getDefaultInstance(),
                List.of(Ingredient.of(
                        ItemRegister.GROUND_COFFEE.get(),
                        ItemRegister.COCOA_POWDER.get(),
                        ItemRegister.STEAMED_MILK_BUCKET.get()
                )),
                200,
                250,
                ItemRegister.MUG.get().getDefaultInstance()
        );

        registerCoffeeMachineRecipe(pOutput, "hot_cocoa",
                ItemRegister.HOT_COCOA.get().getDefaultInstance(),
                List.of(Ingredient.of(
                        ItemRegister.COCOA_POWDER.get(),
                        Items.SUGAR,
                        ItemRegister.STEAMED_MILK_BUCKET.get()
                )),
                200,
                0,
                ItemRegister.MUG.get().getDefaultInstance()
        );

    }

    public void registerCoffeeMachineRecipe(RecipeOutput pOutput, String name, ItemStack stack, List<Ingredient> ingredients, int ticks, int waterNeeded, ItemStack itemOnOutput) {
        CoffeeMachineRecipeBuilder builder = new CoffeeMachineRecipeBuilder(
                stack,
                ingredients,
                ticks,
                waterNeeded,
                itemOnOutput
        );
        builder.save(pOutput, new ResourceLocation(KawaiiDishes.MODID, name));
    }

    public void blender(RecipeOutput pOutput) {

        registerBlenderRecipe(pOutput, "milk_foam",
                ItemRegister.MILK_FOAM_BUCKET.get().getDefaultInstance(),
                List.of(
                        Ingredient.of(
                                ItemRegister.STEAMED_MILK_BUCKET.get()
                        )
                ),
                100,
                Items.BUCKET.getDefaultInstance());

        registerBlenderRecipe(pOutput, "ground_coffee",
                ItemRegister.GROUND_COFFEE.get().getDefaultInstance(),
                List.of(
                        Ingredient.of(
                                ItemRegister.ROAST_COFFEE_BEANS.get()
                        )
                ),
                100,
                ItemStack.EMPTY);

        registerBlenderRecipe(pOutput, "cocoa_powder",
                ItemRegister.COCOA_POWDER.get().getDefaultInstance(),
                List.of(
                        Ingredient.of(
                                Items.COCOA_BEANS
                        )
                ),
                100,
                ItemStack.EMPTY);

        registerBlenderRecipe(pOutput, "cream_cheese_ball",
                ItemRegister.CREAM_CHEESE_BALL.get().getDefaultInstance(),
                List.of(
                        Ingredient.of(
                                Items.MILK_BUCKET
                        )
                ),
                100,
                ItemStack.EMPTY);
    }

    public void registerBlenderRecipe(RecipeOutput pOutput, String name, ItemStack stack, List<Ingredient> ingredients, int ticks, ItemStack itemOnOutput) {
        BlenderRecipeBuilder builder = new BlenderRecipeBuilder(
                stack,
                ingredients,
                ticks,
                itemOnOutput
        );

        builder.save(pOutput, new ResourceLocation(KawaiiDishes.MODID, name));
    }

    public void iceCreamMaker(RecipeOutput pOutput) {
        registerIceCreamMakerRecipe(pOutput, "sweet_berry_ice_cream",
                ItemRegister.SWEET_BERRY_ICE_CREAM.get().getDefaultInstance(),
                List.of(
                        Ingredient.of(
                                Items.SWEET_BERRIES
                        )
                ),
                200,
                1,
                Items.BOWL.getDefaultInstance()
        );

        registerIceCreamMakerRecipe(pOutput, "glow_berry_ice_cream",
                ItemRegister.GLOW_BERRY_ICE_CREAM.get().getDefaultInstance(),
                List.of(
                        Ingredient.of(
                                Items.GLOW_BERRIES
                        )
                ),
                200,
                1,
                Items.BOWL.getDefaultInstance()
        );

        registerIceCreamMakerRecipe(pOutput, "chocolate_ice_cream",
                ItemRegister.CHOCOLATE_ICE_CREAM.get().getDefaultInstance(),
                List.of(
                        Ingredient.of(
                                ItemRegister.COCOA_POWDER.get(),
                                ItemRegister.STEAMED_MILK_BUCKET.get(),
                                Items.SUGAR
                        )
                ),
                200,
                1,
                Items.BOWL.getDefaultInstance()
        );

        registerIceCreamMakerRecipe(pOutput, "cream_ice_cream",
                ItemRegister.CREAM_ICE_CREAM.get().getDefaultInstance(),
                List.of(
                        Ingredient.of(
                                ItemRegister.STEAMED_MILK_BUCKET.get(),
                                Items.SUGAR
                        )
                ),
                200,
                1,
                Items.BOWL.getDefaultInstance()
        );

        registerIceCreamMakerRecipe(pOutput, "neapolitan_ice_cream",
                ItemRegister.NEAPOLITAN_ICE_CREAM.get().getDefaultInstance(),
                List.of(
                        Ingredient.of(
                                ItemRegister.CHOCOLATE_ICE_CREAM.get(),
                                ItemRegister.SWEET_BERRY_ICE_CREAM.get(),
                                ItemRegister.CREAM_ICE_CREAM.get()
                        )
                ),
                200,
                0,
                Items.BOWL.getDefaultInstance()
        );

        registerIceCreamMakerRecipe(pOutput, "mocha_ice_cream",
                ItemRegister.MOCHA_ICE_CREAM.get().getDefaultInstance(),
                List.of(
                        Ingredient.of(
                                ItemRegister.CREAM_ICE_CREAM.get(),
                                ItemRegister.COCOA_POWDER.get()
                        )
                ),
                200,
                0,
                Items.BOWL.getDefaultInstance()
        );

        registerIceCreamMakerRecipe(pOutput, "coffee_ice_cream",
                ItemRegister.COFFEE_ICE_CREAM.get().getDefaultInstance(),
                List.of(
                        Ingredient.of(
                                ItemRegister.GROUND_COFFEE.get(),
                                ItemRegister.STEAMED_MILK_BUCKET.get(),
                                Items.SUGAR
                        )
                ),
                200,
                1,
                Items.BOWL.getDefaultInstance()
        );

        registerIceCreamMakerRecipe(pOutput, "cherry_ice_cream",
                ItemRegister.CHERRY_ICE_CREAM.get().getDefaultInstance(),
                List.of(
                        Ingredient.of(
                                ItemRegister.CHERRY.get()
                        )
                ),
                200,
                1,
                Items.BOWL.getDefaultInstance()
        );
    }

    public void registerIceCreamMakerRecipe(RecipeOutput pOutput, String name, ItemStack stack, List<Ingredient> ingredients, int ticks, int snowballs, ItemStack itemOnOutput) {
        IceCreamMachineRecipeBuilder builder = new IceCreamMachineRecipeBuilder(
                stack,
                ingredients,
                ticks,
                snowballs,
                itemOnOutput
        );
        builder.save(pOutput, new ResourceLocation(KawaiiDishes.MODID, name));
    }
}
