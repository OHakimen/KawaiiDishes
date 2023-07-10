package com.hakimen.kawaiidishes.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hakimen.kawaiidishes.KawaiiDishes;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class CoffeeMachineRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    private final int ticks;
    private final boolean requireWater;
    private final boolean requireMilk;
    private final ItemStack itemOnOutput;

    public CoffeeMachineRecipe(ResourceLocation id, ItemStack output,
                               NonNullList<Ingredient> recipeItems, int ticks,
    boolean water,boolean milk,ItemStack itemOnOutput) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.ticks = ticks;
        this.requireWater = water;
        this.requireMilk = milk;
        this.itemOnOutput = itemOnOutput;
    }



    public int getTicks() {
        return ticks;
    }

    public boolean requireWater() {
        return requireWater;
    }

    public boolean requireMilk() {
        return requireMilk;
    }

    public ItemStack getItemOnOutput() {
        return itemOnOutput;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        boolean match = true;
        if(requireWater)
            match &= pContainer.getItem(0).getItem().equals(Items.WATER_BUCKET);
        else if(pContainer.getItem(0).getItem().equals(Items.WATER_BUCKET)){
            return false;
        }
        if(requireMilk)
            match &= pContainer.getItem(1).getItem().equals(Items.MILK_BUCKET);
        else if(pContainer.getItem(1).getItem().equals(Items.MILK_BUCKET)){
            return false;
        }
        match &= pContainer.getItem(5).getItem().equals(itemOnOutput.getItem());
        boolean submatches[] = new boolean[] {false,false,false};
        if(recipeItems.get(0).getItems().length == 3){
            submatches[0] = recipeItems.get(0).getItems()[0].getItem().equals(pContainer.getItem(2).getItem());
            submatches[1] = recipeItems.get(0).getItems()[1].getItem().equals(pContainer.getItem(3).getItem());
            submatches[2] = recipeItems.get(0).getItems()[2].getItem().equals(pContainer.getItem(4).getItem());
        }else if(recipeItems.get(0).getItems().length == 2){
            submatches[0] = recipeItems.get(0).getItems()[0].getItem().equals(pContainer.getItem(2).getItem());
            submatches[1] = recipeItems.get(0).getItems()[1].getItem().equals(pContainer.getItem(3).getItem());
            submatches[2] = pContainer.getItem(4).getItem().equals(Items.AIR);
        }else if(recipeItems.get(0).getItems().length == 1){
            submatches[0] = recipeItems.get(0).getItems()[0].getItem().equals(pContainer.getItem(2).getItem());
            submatches[1] = pContainer.getItem(3).getItem().equals(Items.AIR);
            submatches[2] = pContainer.getItem(4).getItem().equals(Items.AIR);
        }
        for (int i = 0; i < submatches.length; i++) {
            match &= submatches[i];
        }
        return match;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }
    public static class Type implements RecipeType<CoffeeMachineRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "coffee_machining";
    }

    public static class Serializer implements RecipeSerializer<CoffeeMachineRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(KawaiiDishes.modId,"coffee_machining");

        @Override
        public CoffeeMachineRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));

            int ticks = GsonHelper.getAsInt(json,"ticks");
            boolean needWater = GsonHelper.getAsBoolean(json,"water");
            boolean needMilk = GsonHelper.getAsBoolean(json,"milk");
            ItemStack onOutput = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "itemOnOutput"));


            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(3, Ingredient.EMPTY);

            for (int i = 0; i < ingredients.size(); i++) {

                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new CoffeeMachineRecipe(id, output, inputs, ticks,needWater,needMilk,onOutput);
        }

        @Override
        public CoffeeMachineRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }
            int ticks = buf.readInt();
            boolean needWater = buf.readBoolean();
            boolean needMilk = buf.readBoolean();
            ItemStack onOutput = buf.readItem();
            ItemStack output = buf.readItem();

            return new CoffeeMachineRecipe(id, output, inputs,ticks,needWater,needMilk,onOutput);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, CoffeeMachineRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeInt(recipe.ticks);
            buf.writeBoolean(recipe.requireWater);
            buf.writeBoolean(recipe.requireMilk);
            buf.writeItemStack(recipe.itemOnOutput, false);
            buf.writeItemStack(recipe.getResultItem(), false);
        }

        @Override
        public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
            return INSTANCE;
        }

        @Nullable
        @Override
        public ResourceLocation getRegistryName() {
            return ID;
        }

        @Override
        public Class<RecipeSerializer<?>> getRegistryType() {
            return Serializer.castClass(RecipeSerializer.class);
        }

        @SuppressWarnings("unchecked") // Need this wrapper, because generics
        private static <G> Class<G> castClass(Class<?> cls) {
            return (Class<G>)cls;
        }
    }
}
