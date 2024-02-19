package com.hakimen.kawaiidishes.recipes;

import com.hakimen.kawaiidishes.KawaiiDishes;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;

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

        if (coffeeMachineContainer.blockEntity().getFluidStack().getAmount() < getWaterNeeded()) {
            return false;
        }
        IItemHandler inventory = coffeeMachineContainer.blockEntity().getInventory();

        if (!inventory.getStackInSlot(5).is(itemOnOutput.getItem())) {
            return false;
        }

        for (int i = 2; i < 5; i++) {
            if(!inventory.getStackInSlot(i).is(ItemStack.EMPTY.getItem())){
                slots.add(i);
            }
        }

        if(slots.size() != recipeItems.get(0).getItems().length){
            return false;
        }else{
            for (int i = 0; i < slots.size(); i++) {
                if(!inventory.getStackInSlot(slots.get(i)).is(recipeItems.get(0).getItems()[i].getItem())){
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
    public ItemStack assemble(CoffeeMachineDataContainer coffeeMachineContainer, RegistryAccess pRegistryAccess) {
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

        private static final Codec<CoffeeMachineRecipe> CODEC = RecordCodecBuilder.create(
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
                                        .forGetter(CoffeeMachineRecipe::getRecipeItems),
                                ExtraCodecs.POSITIVE_INT.fieldOf("ticks").forGetter(CoffeeMachineRecipe::getTicks),
                                ExtraCodecs.NON_NEGATIVE_INT.fieldOf("waterNeeded").forGetter(CoffeeMachineRecipe::getWaterNeeded),
                                CraftableCodecs.ITEM_STACK_CODEC.fieldOf("itemOnOutput").forGetter(recipe -> recipe.itemOnOutput))
                        .apply(instance, CoffeeMachineRecipe::new)
        );
        @Override
        public Codec<CoffeeMachineRecipe> codec() {
            return CODEC;
        }

        @Override
        public CoffeeMachineRecipe fromNetwork(FriendlyByteBuf buf) {

            ResourceLocation id = buf.readResourceLocation();

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
        public void toNetwork(FriendlyByteBuf buf, CoffeeMachineRecipe recipe) {
            buf.writeResourceLocation(recipe.id);

            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }

            buf.writeInt(recipe.waterNeeded);
            buf.writeInt(recipe.ticks);
            buf.writeItem(recipe.itemOnOutput);
            buf.writeItem(recipe.getResultItem(null));
        }
    }
}
