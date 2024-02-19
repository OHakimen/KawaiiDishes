package com.hakimen.kawaiidishes.datagen.recipeBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.containers.CoffeeMachineContainer;
import com.hakimen.kawaiidishes.recipes.BlenderRecipe;
import com.hakimen.kawaiidishes.recipes.CoffeeMachineRecipe;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.levelgen.blending.Blender;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlenderRecipeBuilder implements RecipeBuilder {
    private final ItemStack output;
    private final List<Ingredient> recipeItems;
    private final int ticks;
    private final ItemStack itemOnOutput;

    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    public BlenderRecipeBuilder(ItemStack output, List<Ingredient> recipeItems, int ticks, ItemStack itemOnOutput) {
        this.output = output;
        this.recipeItems = recipeItems;
        this.ticks = ticks;
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

        BlenderRecipe recipe = new BlenderRecipe(pRecipeId,output, NonNullList.copyOf(recipeItems),ticks,itemOnOutput);
        pFinishedRecipe.accept(pRecipeId,recipe, advancement.build(pRecipeId.withPrefix("recipes/")));
    }

}
