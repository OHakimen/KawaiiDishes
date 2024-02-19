package com.hakimen.kawaiidishes.datagen.recipeBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.recipes.CoffeeMachineRecipe;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CoffeeMachineRecipeBuilder implements RecipeBuilder {
    private final ItemStack output;
    private final List<Ingredient> recipeItems;
    private final int ticks;

    private final int waterNeeded;
    private final ItemStack itemOnOutput;

    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    public CoffeeMachineRecipeBuilder(ItemStack output, List<Ingredient> recipeItems, int ticks, int waterNeeded, ItemStack itemOnOutput) {
        this.output = output;
        this.recipeItems = recipeItems;
        this.ticks = ticks;
        this.waterNeeded = waterNeeded;
        this.itemOnOutput = itemOnOutput;
    }
    @Override
    public RecipeBuilder unlockedBy(String key, Criterion<?> criterion) {
        this.advancement.addCriterion(key, criterion);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String p_176495_) {
        return this;
    }

    @Override
    public Item getResult() {
        return this.output.getItem();
    }

    @Override
    public void save(RecipeOutput pFinishedRecipe, ResourceLocation pRecipeId) {
        this.advancement.parent(new AdvancementHolder(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT, Advancement.Builder.recipeAdvancement().build(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT).value()))
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId))
                .rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(AdvancementRequirements.Strategy.OR);

        pFinishedRecipe.accept(pRecipeId, new CoffeeMachineRecipe(pRecipeId,output, NonNullList.copyOf(recipeItems), ticks,waterNeeded, itemOnOutput), advancement.build(pRecipeId.withPrefix("recipes/")));
    }

}
