package com.hakimen.kawaiidishes.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.containers.CoffeeMachineDataContainer;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class CoffeeMachineRecipe implements Recipe<CoffeeMachineDataContainer> {

    private final Identifier id;
    private final ItemStack output;
    private final DefaultedList<Ingredient> recipeItems;
    private final int ticks;
    private final int waterNeeded;
    private final ItemStack itemOnOutput;

    public CoffeeMachineRecipe(Identifier id, ItemStack output, DefaultedList<Ingredient> recipeItems, int ticks, int waterNeeded, ItemStack itemOnOutput) {
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

    public DefaultedList<Ingredient> getRecipeItems() {
        return recipeItems;
    }

    public int getTicks() {
        return ticks;
    }

    public Identifier getId() {
        return id;
    }

    public ItemStack getItemOnOutput() {
        return itemOnOutput;
    }

    public int getWaterNeeded() {
        return waterNeeded;
    }

    @Override
    public boolean matches(CoffeeMachineDataContainer coffeeMachineContainer, World pLevel) {
        List<Integer> slots = new ArrayList<Integer>();

        if (coffeeMachineContainer.blockEntity().getWaterTank().amount < getWaterNeeded()) {
            return false;
        }
        SimpleInventory inventory = coffeeMachineContainer.blockEntity().getInventory();

        if (!inventory.getStack(5).isOf(itemOnOutput.getItem())) {
            return false;
        }

        for (int i = 2; i < 5; i++) {
            if(!inventory.getStack(i).isOf(ItemStack.EMPTY.getItem())){
                slots.add(i);
            }
        }

        ItemStack last = inventory.getStack(6);
        if(!last.isEmpty() && !last.getItem().equals(getOutput().getItem()) || last.getCount() == last.getMaxCount()){
            return false;
        }

        if(slots.size() != recipeItems.get(0).getMatchingStacks().length){
            return false;
        }else{
            for (int i = 0; i < slots.size(); i++) {
                if(!inventory.getStack(slots.get(i)).isOf(recipeItems.get(0).getMatchingStacks()[i].getItem())){
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
    public ItemStack craft(CoffeeMachineDataContainer coffeeMachineContainer, DynamicRegistryManager pRegistryAccess) {
        return output;
    }

    @Override
    public boolean fits(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager pRegistryAccess) {
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
        public static final Identifier ID =
                new Identifier(KawaiiDishes.MODID, "coffee_machining");


        @Override
        public CoffeeMachineRecipe read(Identifier resourceLocation, JsonObject jsonObject) {
            JsonArray array = jsonObject.getAsJsonArray("ingredients");

            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(array.size(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(array.get(i),false));
            }

            int ticks = jsonObject.get("ticks").getAsInt();
            int waterNeeded = jsonObject.get("waterNeeded").getAsInt();
            ItemStack onOutput = ItemStack.EMPTY;
            if(!JsonHelper.getObject(jsonObject, "itemOnOutput").get("item").getAsString().equals("minecraft:air")){
                onOutput = ShapedRecipe.outputFromJson(JsonHelper.getObject(jsonObject, "itemOnOutput"));
            }
            ItemStack result = ItemStack.EMPTY;
            if(!JsonHelper.getObject(jsonObject, "output").get("item").getAsString().equals("minecraft:air")) {
                result = ShapedRecipe.outputFromJson(JsonHelper.getObject(jsonObject, "output"));
            }
            return new CoffeeMachineRecipe(resourceLocation, result, inputs, ticks, waterNeeded,onOutput);
        }

        @Override
        public CoffeeMachineRecipe read(Identifier resourceLocation, PacketByteBuf buf) {
            Identifier id = resourceLocation;

            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);


            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            int waterAmount = buf.readInt();
            int ticks = buf.readInt();
            ItemStack onOutput = buf.readItemStack();
            ItemStack output = buf.readItemStack();

            return new CoffeeMachineRecipe(id,output, inputs, ticks, waterAmount, onOutput);
        }

        @Override
        public void write(PacketByteBuf buf, CoffeeMachineRecipe recipe) {
            buf.writeInt(recipe.getRecipeItems().size());

            for (Ingredient ing : recipe.getRecipeItems()) {
                ing.write(buf);
            }

            buf.writeInt(recipe.waterNeeded);
            buf.writeInt(recipe.ticks);
            buf.writeItemStack(recipe.itemOnOutput);
            buf.writeItemStack(recipe.getOutput(null));
        }
    }
}
