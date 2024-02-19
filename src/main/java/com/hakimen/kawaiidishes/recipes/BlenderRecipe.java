package com.hakimen.kawaiidishes.recipes;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.containers.BlenderContainer;
import com.hakimen.kawaiidishes.containers.CoffeeMachineDataContainer;
import com.hakimen.kawaiidishes.item.codecs.CraftableCodecs;
import com.hakimen.kawaiidishes.registry.RecipeRegister;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;

public class BlenderRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    private final int ticks;
    private final ItemStack itemOnOutput;

    public BlenderRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems, int ticks,ItemStack itemOnOutput) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.ticks = ticks;
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
    public ItemStack assemble(SimpleContainer container, RegistryAccess pRegistryAccess) {
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

        private static final Codec<BlenderRecipe> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                ResourceLocation.CODEC.fieldOf("type").forGetter(recipe -> ID),
                                CraftableCodecs.ITEM_STACK_CODEC.fieldOf("output").forGetter(recipe -> recipe.output),
                                Ingredient.CODEC_NONEMPTY
                                        .listOf()
                                        .fieldOf("ingredients")
                                        .flatXmap(
                                                ingredients -> {
                                                    Ingredient[] ingredientsArray = ingredients
                                                            .toArray(Ingredient[]::new); //Forge skip the empty check and immediatly create the array.
                                                    if (ingredientsArray.length == 0) {
                                                        return DataResult.error(() -> "No ingredients for recipe");
                                                    } else {
                                                        return ingredientsArray.length > 3
                                                                ? DataResult.error(() -> "Too many ingredients for recipe. The maximum is: %s".formatted(3))
                                                                : DataResult.success(NonNullList.of(Ingredient.EMPTY, ingredientsArray));
                                                    }
                                                },
                                                DataResult::success
                                        )
                                        .forGetter(BlenderRecipe::getRecipeItems),
                                ExtraCodecs.POSITIVE_INT.fieldOf("ticks").forGetter(BlenderRecipe::getTicks),
                                CraftableCodecs.ITEM_STACK_CODEC.fieldOf("itemOnOutput").forGetter(recipe -> recipe.itemOnOutput))
                        .apply(instance, BlenderRecipe::new)
        );
        @Override
        public Codec<BlenderRecipe> codec() {
            return CODEC;
        }

        @Override
        public BlenderRecipe fromNetwork(FriendlyByteBuf buf) {

            ResourceLocation id = buf.readResourceLocation();

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
        public void toNetwork(FriendlyByteBuf buf, BlenderRecipe recipe) {
            buf.writeResourceLocation(recipe.id);

            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }

            buf.writeInt(recipe.ticks);
            buf.writeItem(recipe.itemOnOutput);
            buf.writeItem(recipe.getResultItem(null));
        }
    }
}
