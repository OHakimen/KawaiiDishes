package com.hakimen.kawaiidishes.recipes;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hakimen.kawaiidishes.KawaiiDishes;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;

public class BlenderRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation id;
    private final ItemStack output;
    private final List<Ingredient> recipeItems;
    private final int ticks;
    private final ItemStack itemOnOutput;

    public BlenderRecipe(ResourceLocation id, ItemStack output, List<Ingredient> recipeItems, int ticks,ItemStack itemOnOutput) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.ticks = ticks;
        this.itemOnOutput = itemOnOutput;
    }

    public ItemStack getOutput() {
        return output;
    }

    public List<Ingredient> getRecipeItems() {
        return recipeItems;
    }

    public int getTicks() {
        return ticks;
    }

    public ResourceLocation getId() {
        return id;
    }

    public ItemStack getItemOnOutput() {
        return itemOnOutput;
    }


    @Override
    public boolean matches(SimpleContainer container, Level pLevel) {
        List<Integer> slots = new ArrayList<Integer>();


        if (itemOnOutput != ItemStack.EMPTY && !container.getItem(3).is(itemOnOutput.getItem())) {
            return false;
        }

        for (int i = 0; i < 3; i++) {
            if(!container.getItem(i).is(ItemStack.EMPTY.getItem())){
                slots.add(i);
            }
        }

        ItemStack last = container.getItem(4);
        if(!last.isEmpty() && !last.getItem().equals(getOutput().getItem()) || last.getCount() == last.getMaxStackSize() ){
            return false;
        }

        if(slots.size() != recipeItems.get(0).getItems().length){
            return false;
        }else{
            for (int i = 0; i < slots.size(); i++) {
                if(!container.getItem(slots.get(i)).is(recipeItems.get(0).getItems()[i].getItem())){
                    return false;
                }
                if(slots.get(i) > slots.size()){
                   return false;
                }
            }
        }

        return true;
    }

    @Override
    public ItemStack craft(SimpleContainer container, RegistryAccess pRegistryAccess) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<BlenderRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "blending";
        private Type() {
        }
    }

    public static class Serializer implements RecipeSerializer<BlenderRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(KawaiiDishes.MODID, "blending");


        @Override
        public BlenderRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {

            JsonArray array = jsonObject.getAsJsonArray("ingredients");

            List<Ingredient> inputs = NonNullList.withSize(array.size(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(array.get(i),false));
            }

            int ticks = jsonObject.get("ticks").getAsInt();
            ItemStack onOutput = ItemStack.EMPTY;
            if(!GsonHelper.getAsJsonObject(jsonObject, "itemOnOutput").get("item").getAsString().equals("minecraft:air")){
                onOutput = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "itemOnOutput"));
            }
            ItemStack result = ItemStack.EMPTY;
            if(!GsonHelper.getAsJsonObject(jsonObject, "output").get("item").getAsString().equals("minecraft:air")) {
                result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "output"));
            }

            return new BlenderRecipe(resourceLocation, result, inputs, ticks, onOutput);
        }

        @Override
        public BlenderRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf buf) {

            ResourceLocation id = resourceLocation;

            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            int ticks = buf.readInt();
            ItemStack onOutput = buf.readItem();
            ItemStack output = buf.readItem();

            return new BlenderRecipe(id,output, inputs, ticks, onOutput);
        }


        @Override
        public void write(FriendlyByteBuf buf, BlenderRecipe recipe) {

            buf.writeInt(recipe.getRecipeItems().size());
            for (Ingredient ing : recipe.getRecipeItems()) {
                ing.toNetwork(buf);
            }

            buf.writeInt(recipe.ticks);
            buf.writeItem(recipe.itemOnOutput);
            buf.writeItem(recipe.getResultItem(null));
        }
    }
}
