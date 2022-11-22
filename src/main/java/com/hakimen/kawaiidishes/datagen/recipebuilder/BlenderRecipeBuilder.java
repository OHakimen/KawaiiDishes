package com.hakimen.kawaiidishes.datagen.recipebuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.recipes.BlenderRecipe;
import com.hakimen.kawaiidishes.recipes.CoffeeMachineRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class BlenderRecipeBuilder implements RecipeBuilder {
    private final ItemStack result;
    private final int ticks;
    private final Ingredient ingredient;
    private final int count;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    public BlenderRecipeBuilder(ItemLike ingredient, ItemLike ingredient1, ItemStack result, int ticks, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect) {
        this.ingredient = Ingredient.of(ingredient,ingredient1);
        this.ticks = ticks;
        var stack = result.getItem().getDefaultInstance();

        var mainEffectTag = new CompoundTag();
        var secondaryEffectTag = new CompoundTag();
        if(mainEffect != null){
            mainEffect.save(mainEffectTag);
        }
        if(secondaryEffect != null){
            secondaryEffect.save(secondaryEffectTag);
        }

        if(!mainEffectTag.equals(new CompoundTag())){
            stack.getOrCreateTag().put("mainEffect",mainEffectTag);
        }
        if(!secondaryEffectTag.equals(new CompoundTag())){
            stack.getOrCreateTag().put("secondaryEffect",secondaryEffectTag);
        }

        this.result = stack;
        this.count = 1;
    }
    public BlenderRecipeBuilder(ItemLike ingredient, ItemStack result, int ticks, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect) {
        this.ingredient = Ingredient.of(ingredient);
        this.ticks = ticks;

        var stack = result.getItem().getDefaultInstance();

        var mainEffectTag = new CompoundTag();
        var secondaryEffectTag = new CompoundTag();
        if(mainEffect != null){
            mainEffect.save(mainEffectTag);
        }
        if(secondaryEffect != null){
            secondaryEffect.save(secondaryEffectTag);
        }

        if(!mainEffectTag.equals(new CompoundTag())){
            stack.getOrCreateTag().put("mainEffect",mainEffectTag);
        }
        if(!secondaryEffectTag.equals(new CompoundTag())){
            stack.getOrCreateTag().put("secondaryEffect",secondaryEffectTag);
        }

        this.result = stack;
        this.count = 1;
    }

    public BlenderRecipeBuilder(ItemLike ingredient, ItemLike ingredient1, ItemStack result, int ticks) {
        this.ingredient = Ingredient.of(ingredient,ingredient1);
        this.ticks = ticks;
        var stack = result.getItem().getDefaultInstance();


        this.result = stack;
        this.count = 1;
    }
    public BlenderRecipeBuilder(ItemLike ingredient, ItemStack result, int ticks) {
        this.ingredient = Ingredient.of(ingredient);
        this.ticks = ticks;
        var stack = result.getItem().getDefaultInstance();

        this.result = stack;
        this.count = 1;
    }


    @Override
    public RecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        this.advancement.addCriterion(pCriterionName, pCriterionTrigger);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String pGroupName) {
        return this;
    }

    @Override
    public Item getResult() {
        return result.getItem();
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        this.advancement.parent(new ResourceLocation("recipes/root"))
                .addCriterion("has_the_recipe",
                        RecipeUnlockedTrigger.unlocked(pRecipeId))
                .rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);

        pFinishedRecipeConsumer.accept(new BlenderRecipeBuilder.Result(pRecipeId, this.result, this.count, this.ingredient, ticks,
                this.advancement, new ResourceLocation(pRecipeId.getNamespace(), "recipes/"+ pRecipeId.getPath()+"_from_blending")));

    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final ItemStack result;
        private final Ingredient ingredient;
        private final int ticks;
        private final int count;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation pId, ItemStack pResult, int pCount, Ingredient ingredient, int ticks, Advancement.Builder pAdvancement,
                      ResourceLocation pAdvancementId) {
            this.id = pId;
            this.ticks = ticks;
            this.result = pResult;
            this.count = pCount;
            this.ingredient = ingredient;
            this.advancement = pAdvancement;
            this.advancementId = pAdvancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {

            pJson.addProperty("ticks",ticks);

            JsonArray jsonarray = new JsonArray();
            jsonarray.add(ingredient.toJson());

            pJson.add("ingredients", jsonarray);
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", this.result.getItem().getRegistryName().toString());
            jsonobject.addProperty("nbt", this.result.getOrCreateTag().toString());
            if (this.count > 1) {
                jsonobject.addProperty("count", this.count);
            }


            pJson.add("output", jsonobject);
        }

        @Override
        public ResourceLocation getId() {
            return new ResourceLocation(KawaiiDishes.modId,
                    this.result.getItem().getRegistryName().getPath()+"_from_blending");
        }

        @Override
        public RecipeSerializer<?> getType() {
            return BlenderRecipe.Serializer.INSTANCE;
        }

        @Nullable
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
