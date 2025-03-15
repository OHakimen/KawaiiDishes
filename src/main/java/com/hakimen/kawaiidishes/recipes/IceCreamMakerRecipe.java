package com.hakimen.kawaiidishes.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hakimen.kawaiidishes.KawaiiDishes;
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
import java.util.ArrayList;
import java.util.List;

public class IceCreamMakerRecipe implements Recipe<SimpleInventory> {

    private final Identifier id;
    private final ItemStack output;
    private final DefaultedList<Ingredient> recipeItems;
    private final int snowballs;
    private final int ticks;
    private final ItemStack itemOnOutput;

    public IceCreamMakerRecipe(Identifier id, ItemStack output, DefaultedList<Ingredient> recipeItems, int ticks, int snowballs, ItemStack itemOnOutput) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.ticks = ticks;
        this.snowballs = snowballs;
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


    public int getSnowballs() {
        return snowballs;
    }

    @Override
    public boolean matches(SimpleInventory container, World pLevel) {
        List<Integer> slots = new ArrayList<Integer>();


        if (itemOnOutput != ItemStack.EMPTY && !container.getStack(4).isOf(itemOnOutput.getItem())) {
            return false;
        }


        if(container.getStack(0).getCount() < snowballs){
            return false;
        }



        for (int i = 1; i < 4; i++) {
            if(!container.getStack(i).isOf(ItemStack.EMPTY.getItem())){
                slots.add(i);
            }
        }

        ItemStack last = container.getStack(5);
        if(!last.isEmpty() && !last.getItem().equals(getOutput().getItem()) || last.getCount() == last.getMaxCount()){
            return false;
        }

        if(slots.size() != recipeItems.get(0).getMatchingStacks().length){
            return false;
        }else{
            for (int i = 0; i < slots.size(); i++) {
                if(!container.getStack(slots.get(i)).isOf(recipeItems.get(0).getMatchingStacks()[i].getItem())){
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
    public ItemStack craft(SimpleInventory container, DynamicRegistryManager pRegistryAccess) {
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

    public static class Type implements RecipeType<IceCreamMakerRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "ice_cream_making";
        private Type() {
        }
    }

    public static class Serializer implements RecipeSerializer<IceCreamMakerRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final Identifier ID =
                new Identifier(KawaiiDishes.MODID, "ice_cream_making");

        @Override
        public IceCreamMakerRecipe read(Identifier resourceLocation, PacketByteBuf buf) {

            Identifier id = resourceLocation;

            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            int ticks = buf.readInt();
            ItemStack onOutput = buf.readItemStack();
            ItemStack output = buf.readItemStack();
            int snowballs = buf.readInt();

            return new IceCreamMakerRecipe(id,output, inputs, ticks, snowballs, onOutput);
        }

        @Override
        public IceCreamMakerRecipe read(Identifier resourceLocation, JsonObject jsonObject) {
            JsonArray array = jsonObject.getAsJsonArray("ingredients");

            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(array.size(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(array.get(i),false));
            }

            int ticks = jsonObject.get("ticks").getAsInt();
            ItemStack onOutput = ItemStack.EMPTY;
            if(!JsonHelper.getObject(jsonObject, "itemOnOutput").get("item").getAsString().equals("minecraft:air")){
                onOutput = ShapedRecipe.outputFromJson(JsonHelper.getObject(jsonObject, "itemOnOutput"));
            }
            ItemStack result = ItemStack.EMPTY;
            if(!JsonHelper.getObject(jsonObject, "output").get("item").getAsString().equals("minecraft:air")) {
                result = ShapedRecipe.outputFromJson(JsonHelper.getObject(jsonObject, "output"));
            }

            int snowballs = jsonObject.get("snowballs").getAsInt();

            return new IceCreamMakerRecipe(resourceLocation, result, inputs, ticks, snowballs, onOutput);
        }

        @Override
        public void write(PacketByteBuf buf, IceCreamMakerRecipe recipe) {

            buf.writeInt(recipe.getRecipeItems().size());
            for (Ingredient ing : recipe.getRecipeItems()) {
                ing.write(buf);
            }

            buf.writeInt(recipe.ticks);
            buf.writeItemStack(recipe.itemOnOutput);
            buf.writeItemStack(recipe.getOutput(null));
            buf.writeInt(recipe.snowballs);
        }
    }
}
