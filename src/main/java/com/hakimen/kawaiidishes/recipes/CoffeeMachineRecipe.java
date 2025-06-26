package com.hakimen.kawaiidishes.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.containers.CoffeeMachineDataContainer;
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

public class CoffeeMachineRecipe implements Recipe<CoffeeMachineDataContainer> {

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    private final int ticks;
    private final int waterNeeded;
    private final ItemStack itemOnOutput;

    public CoffeeMachineRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems, int ticks, int waterNeeded, ItemStack itemOnOutput) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.ticks = ticks;
        this.waterNeeded = waterNeeded;
        this.itemOnOutput = itemOnOutput;
    }

    public ItemStack getOutput() {
        return output;
    }

    public NonNullList<Ingredient> getRecipeItems() {
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

    public int getWaterNeeded() {
        return waterNeeded;
    }

    @Override
    public boolean matches(CoffeeMachineDataContainer coffeeMachineContainer, Level pLevel) {
        List<Integer> slots = new ArrayList<Integer>();

        if (coffeeMachineContainer.blockEntity().getWaterTank().amount < getWaterNeeded()) {
            return false;
        }
        SimpleContainer inventory = coffeeMachineContainer.blockEntity().getInventory();

        if (!inventory.getItem(5).is(itemOnOutput.getItem())) {
            return false;
        }

        for (int i = 2; i < 5; i++) {
            if(!inventory.getItem(i).is(ItemStack.EMPTY.getItem())){
                slots.add(i);
            }
        }

        ItemStack last = inventory.getItem(6);
        if(!last.isEmpty() && !last.getItem().equals(getOutput().getItem()) || last.getCount() == last.getMaxStackSize()){
            return false;
        }

        if(slots.size() != recipeItems.get(0).getItems().length){
            return false;
        }else{
            for (int i = 0; i < slots.size(); i++) {
                if(!inventory.getItem(slots.get(i)).is(recipeItems.get(0).getItems()[i].getItem())){
                    return false;
                }
                if(slots.get(i)-2 > slots.size()-1){
                   return false;
                }
            }
        }

        return true;
    }

    @Override
    public ItemStack craft(CoffeeMachineDataContainer coffeeMachineContainer, RegistryAccess pRegistryAccess) {
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

    public static class Type implements RecipeType<CoffeeMachineRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "coffee_machining";
        private Type() {
        }
    }

    public static class Serializer implements RecipeSerializer<CoffeeMachineRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(KawaiiDishes.MODID, "coffee_machining");


        @Override
        public CoffeeMachineRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
            JsonArray array = jsonObject.getAsJsonArray("ingredients");

            NonNullList<Ingredient> inputs = NonNullList.withSize(array.size(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(array.get(i),false));
            }

            int ticks = jsonObject.get("ticks").getAsInt();
            int waterNeeded = jsonObject.get("waterNeeded").getAsInt();
            ItemStack onOutput = ItemStack.EMPTY;
            if(!GsonHelper.getAsJsonObject(jsonObject, "itemOnOutput").get("item").getAsString().equals("minecraft:air")){
                onOutput = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "itemOnOutput"));
            }
            ItemStack result = ItemStack.EMPTY;
            if(!GsonHelper.getAsJsonObject(jsonObject, "output").get("item").getAsString().equals("minecraft:air")) {
                result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "output"));
            }
            return new CoffeeMachineRecipe(resourceLocation, result, inputs, ticks, waterNeeded,onOutput);
        }

        @Override
        public CoffeeMachineRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf buf) {
            ResourceLocation id = resourceLocation;

            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);


            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            int waterAmount = buf.readInt();
            int ticks = buf.readInt();
            ItemStack onOutput = buf.readItem();
            ItemStack output = buf.readItem();

            return new CoffeeMachineRecipe(id,output, inputs, ticks, waterAmount, onOutput);
        }

        @Override
        public void write(FriendlyByteBuf buf, CoffeeMachineRecipe recipe) {
            buf.writeInt(recipe.getRecipeItems().size());

            for (Ingredient ing : recipe.getRecipeItems()) {
                ing.toNetwork(buf);
            }

            buf.writeInt(recipe.waterNeeded);
            buf.writeInt(recipe.ticks);
            buf.writeItem(recipe.itemOnOutput);
            buf.writeItem(recipe.getResultItem(null));
        }
    }
}
