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

public class IceCreamMachineRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    private final int ticks;
    private final ItemStack itemOnOutput;

    public IceCreamMachineRecipe(ResourceLocation id, ItemStack output,
                                 NonNullList<Ingredient> recipeItems, int ticks, ItemStack itemOnOutput) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.ticks = ticks;
        this.itemOnOutput = itemOnOutput;
    }



    public int getTicks() {
        return ticks;
    }


    public ItemStack getItemOnOutput() {
        return itemOnOutput;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        boolean match = true;
        int all = 0;
        for (int i = 0; i < pContainer.getContainerSize()-2; i++) {
            if(!pContainer.getItem(i).getItem().equals(Items.AIR)){
                all++;
            }
        }
        if(recipeItems.get(0).getItems().length == all){
            for (int i = 0; i < recipeItems.get(0).getItems().length; i++) {
                match &= recipeItems.get(0).getItems()[i].getItem().equals(pContainer.getItem(i).getItem());
            }
        }else{
            return false;
        }
        match &= pContainer.getItem(4).getItem().equals(itemOnOutput.getItem());

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
    public static class Type implements RecipeType<IceCreamMachineRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "ice_cream_making";
    }

    public static class Serializer implements RecipeSerializer<IceCreamMachineRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(KawaiiDishes.modId,"ice_cream_making");

        @Override
        public IceCreamMachineRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));

            int ticks = GsonHelper.getAsInt(json,"ticks");
            ItemStack onOutput = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "itemOnOutput"));


            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(4, Ingredient.EMPTY);

            for (int i = 0; i < ingredients.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new IceCreamMachineRecipe(id, output, inputs, ticks, onOutput);
        }

        @Override
        public IceCreamMachineRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }
            int ticks = buf.readInt();
            ItemStack onOutput = buf.readItem();
            ItemStack output = buf.readItem();

            return new IceCreamMachineRecipe(id, output, inputs,ticks,onOutput);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, IceCreamMachineRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeInt(recipe.ticks);
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
