package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.CriterionProgress;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class CraftingRecipeSupplier extends RecipeProvider implements IConditionBuilder {
    public CraftingRecipeSupplier(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        maidOutfit(pFinishedRecipeConsumer,ItemRegister.blackMaidDress.get(), Items.BLACK_WOOL,Items.WHITE_WOOL);
        maidOutfit(pFinishedRecipeConsumer,ItemRegister.whiteMaidDress.get(), Items.WHITE_WOOL,Items.BLACK_WOOL);

        maidOutfit(pFinishedRecipeConsumer,ItemRegister.grayMaidDress.get(), Items.GRAY_WOOL,Items.WHITE_WOOL);
        maidOutfit(pFinishedRecipeConsumer,ItemRegister.light_grayMaidDress.get(), Items.LIGHT_GRAY_WOOL,Items.WHITE_WOOL);

        maidOutfit(pFinishedRecipeConsumer,ItemRegister.light_blueMaidDress.get(), Items.LIGHT_BLUE_WOOL,Items.WHITE_WOOL);
        maidOutfit(pFinishedRecipeConsumer,ItemRegister.redMaidDress.get(), Items.RED_WOOL,Items.WHITE_WOOL);

        maidOutfit(pFinishedRecipeConsumer,ItemRegister.pinkMaidDress.get(), Items.PINK_WOOL,Items.WHITE_WOOL);
        maidOutfit(pFinishedRecipeConsumer,ItemRegister.magentaMaidDress.get(), Items.MAGENTA_WOOL,Items.WHITE_WOOL);

        maidOutfit(pFinishedRecipeConsumer,ItemRegister.purpleMaidDress.get(), Items.PURPLE_WOOL,Items.WHITE_WOOL);
        maidOutfit(pFinishedRecipeConsumer,ItemRegister.greenMaidDress.get(), Items.GREEN_WOOL,Items.WHITE_WOOL);

        maidOutfit(pFinishedRecipeConsumer,ItemRegister.limeMaidDress.get(), Items.LIME_WOOL,Items.WHITE_WOOL);
        maidOutfit(pFinishedRecipeConsumer,ItemRegister.blueMaidDress.get(), Items.BLUE_WOOL,Items.WHITE_WOOL);

        maidOutfit(pFinishedRecipeConsumer,ItemRegister.cyanMaidDress.get(), Items.CYAN_WOOL,Items.WHITE_WOOL);
        maidOutfit(pFinishedRecipeConsumer,ItemRegister.yellowMaidDress.get(), Items.YELLOW_WOOL,Items.WHITE_WOOL);

        maidOutfit(pFinishedRecipeConsumer,ItemRegister.orangeMaidDress.get(), Items.ORANGE_WOOL,Items.WHITE_WOOL);
        maidOutfit(pFinishedRecipeConsumer,ItemRegister.brownMaidDress.get(), Items.BROWN_WOOL,Items.WHITE_WOOL);


        maidOutfitVariations(pFinishedRecipeConsumer,ItemRegister.blackMaidDress.get());
        maidOutfitVariations(pFinishedRecipeConsumer,ItemRegister.whiteMaidDress.get());

        maidOutfitVariations(pFinishedRecipeConsumer,ItemRegister.grayMaidDress.get());
        maidOutfitVariations(pFinishedRecipeConsumer,ItemRegister.light_grayMaidDress.get());

        maidOutfitVariations(pFinishedRecipeConsumer,ItemRegister.light_blueMaidDress.get());
        maidOutfitVariations(pFinishedRecipeConsumer,ItemRegister.redMaidDress.get());

        maidOutfitVariations(pFinishedRecipeConsumer,ItemRegister.pinkMaidDress.get());
        maidOutfitVariations(pFinishedRecipeConsumer,ItemRegister.magentaMaidDress.get());

        maidOutfitVariations(pFinishedRecipeConsumer,ItemRegister.purpleMaidDress.get());
        maidOutfitVariations(pFinishedRecipeConsumer,ItemRegister.greenMaidDress.get());

        maidOutfitVariations(pFinishedRecipeConsumer,ItemRegister.limeMaidDress.get());
        maidOutfitVariations(pFinishedRecipeConsumer,ItemRegister.blueMaidDress.get());

        maidOutfitVariations(pFinishedRecipeConsumer,ItemRegister.cyanMaidDress.get());
        maidOutfitVariations(pFinishedRecipeConsumer,ItemRegister.yellowMaidDress.get());

        maidOutfitVariations(pFinishedRecipeConsumer,ItemRegister.orangeMaidDress.get());
        maidOutfitVariations(pFinishedRecipeConsumer,ItemRegister.brownMaidDress.get());

        shoes(pFinishedRecipeConsumer,ItemRegister.blackThighHighsShoes.get(),Items.LEATHER,Items.BLACK_WOOL);
        shoes(pFinishedRecipeConsumer,ItemRegister.whiteThighHighsShoes.get(),Items.LEATHER,Items.GRAY_WOOL);

        thighHighs(pFinishedRecipeConsumer,ItemRegister.blackThighHighs.get(),Items.BLACK_WOOL);
        thighHighs(pFinishedRecipeConsumer,ItemRegister.whiteThighHighs.get(),Items.WHITE_WOOL);

        catEars(pFinishedRecipeConsumer,ItemRegister.blackCatEars.get(),Items.BLACK_WOOL);
        catEars(pFinishedRecipeConsumer,ItemRegister.whiteCatEars.get(),Items.WHITE_WOOL);
        catEars(pFinishedRecipeConsumer,ItemRegister.caramelCatEars.get(),Items.YELLOW_WOOL);

        catTails(pFinishedRecipeConsumer,ItemRegister.blackCatTail.get(),Items.BLACK_WOOL);
        catTails(pFinishedRecipeConsumer,ItemRegister.whiteCatTail.get(),Items.WHITE_WOOL);
        catTails(pFinishedRecipeConsumer,ItemRegister.caramelCatTail.get(),Items.YELLOW_WOOL);

        roasting(pFinishedRecipeConsumer,ItemRegister.coffeeFruit.get(),ItemRegister.driedCoffeeBeans.get(),ItemRegister.roastedCoffeeBeans.get());
    }

    public void roasting(Consumer<FinishedRecipe> consumer,Item stage1,Item stage2,Item stage3){
        simpleCookingRecipe(consumer,"smelting", SimpleCookingSerializer.SMELTING_RECIPE,200,stage1,stage2,0.12f);
        simpleCookingRecipe(consumer,"smoking", SimpleCookingSerializer.SMOKING_RECIPE,100,stage1,stage2,0.12f);

        simpleCookingRecipe(consumer,"smelting", SimpleCookingSerializer.SMELTING_RECIPE,200,stage2,stage3,0.12f);
        simpleCookingRecipe(consumer,"smoking", SimpleCookingSerializer.SMOKING_RECIPE,100,stage2,stage3,0.12f);

    }
    public void catTails(Consumer<FinishedRecipe> consumer,Item result,Item item){
        ShapedRecipeBuilder.shaped(result)
                .pattern("  #")
                .pattern("s##")
                .pattern("ss ")
                .define('#',Ingredient.of(item.getDefaultInstance()))
                .define('s',Ingredient.of(Tags.Items.STRING))
                .unlockedBy(getHasName(item), has(item))
                .save(consumer);
    }

    public void thighHighs(Consumer<FinishedRecipe> consumer,Item result,Item item){
        ShapedRecipeBuilder.shaped(result)
                .pattern("# #")
                .pattern("# #")
                .pattern("# #")
                .define('#',Ingredient.of(item.getDefaultInstance()))
                .unlockedBy(getHasName(item), has(item))
                .save(consumer);
    }


    public void shoes(Consumer<FinishedRecipe> consumer,Item result,Item item,Item item2){
        ShapedRecipeBuilder.shaped(result)
                .pattern("# #")
                .pattern("s s")
                .define('#',Ingredient.of(item.getDefaultInstance()))
                .define('s',Ingredient.of(item2.getDefaultInstance()))
                .unlockedBy(getHasName(item), has(item))
                .unlockedBy(getHasName(item2), has(item2))
                .save(consumer);
    }

    public void catEars(Consumer<FinishedRecipe> consumer,Item result,Item item){
        ShapedRecipeBuilder.shaped(result)
                .pattern("#s#")
                .pattern("s s")
                .define('#',Ingredient.of(item.getDefaultInstance()))
                .define('s',Ingredient.of(Tags.Items.STRING))
                .unlockedBy(getHasName(item), has(item))
                .save(consumer);
    }

    public void maidOutfit(Consumer<FinishedRecipe> consumer,Item result,Item mainColor,Item secondaryColor){
        ShapedRecipeBuilder.shaped(result)
                .pattern("# #")
                .pattern("s#s")
                .pattern("@@@")
                .define('#',Ingredient.of(mainColor.getDefaultInstance()))
                .define('@',Ingredient.of(secondaryColor.getDefaultInstance()))
                .define('s',Ingredient.of(Tags.Items.STRING))
                .unlockedBy(getHasName(mainColor), has(mainColor))
                .unlockedBy(getHasName(secondaryColor), has(secondaryColor))
                .save(consumer);
    }

    public void maidOutfitVariations(Consumer<FinishedRecipe> consumer,Item result){
        for (var i:ItemRegister.ITEMS.getEntries().stream().toList()) {
            if(i.get().getRegistryName().getPath().equals(result.getRegistryName().getPath()+"_cat_tail_black")){
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.blackCatTail.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if(i.get().getRegistryName().getPath().equals(result.getRegistryName().getPath()+"_cat_tail_white")){
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.whiteCatTail.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
            if(i.get().getRegistryName().getPath().equals(result.getRegistryName().getPath()+"_cat_tail_caramel")){
                ShapelessRecipeBuilder.shapeless(i.get())
                        .requires(Ingredient.of(result.getDefaultInstance()))
                        .requires(Ingredient.of(ItemRegister.caramelCatTail.get().getDefaultInstance()))
                        .unlockedBy(getHasName(result), has(result))
                        .save(consumer);
            }
        }

    }
}
