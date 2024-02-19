package com.hakimen.kawaiidishes.datagen.recipeBuilder;

import com.hakimen.kawaiidishes.recipes.IceCreamMakerRecipe;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IceCreamMachineRecipeBuilder implements RecipeBuilder {
    private final ItemStack output;
    private final List<Ingredient> recipeItems;
    private final int ticks;
    private final int snowballs;
    private final ItemStack itemOnOutput;

    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    public IceCreamMachineRecipeBuilder(ItemStack output, List<Ingredient> recipeItems, int ticks, int snowballs, ItemStack itemOnOutput) {
        this.output = output;
        this.recipeItems = recipeItems;
        this.ticks = ticks;
        this.snowballs = snowballs;
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

        pFinishedRecipe.accept(pRecipeId, new IceCreamMakerRecipe(pRecipeId,output, NonNullList.copyOf(recipeItems), ticks, snowballs, itemOnOutput), advancement.build(pRecipeId.withPrefix("recipes/")));
    }
}
