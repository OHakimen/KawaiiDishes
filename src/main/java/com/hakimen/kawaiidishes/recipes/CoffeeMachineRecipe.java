package com.hakimen.kawaiidishes.recipes;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.containers.CoffeeMachineDataContainer;
import com.hakimen.kawaiidishes.item.codecs.CraftableCodecs;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public CoffeeMachineRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems, int ticks, int waterNeeded, Optional<ItemStack> itemOnOutput) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.ticks = ticks;
        this.waterNeeded = waterNeeded;
        this.itemOnOutput = itemOnOutput.orElse(ItemStack.EMPTY);
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
    public ItemStack assemble(CoffeeMachineDataContainer coffeeMachineContainer, HolderLookup.Provider pRegistryAccess) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem( HolderLookup.Provider pRegistryAccess) {
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
                ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "coffee_machining");

        private static final MapCodec<CoffeeMachineRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                                ResourceLocation.CODEC.fieldOf("type").forGetter(recipe -> ID),
                                ItemStack.OPTIONAL_CODEC.fieldOf("output").forGetter(recipe -> recipe.output),
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
        public MapCodec<CoffeeMachineRecipe> codec() {
            return CODEC;
        }


        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CoffeeMachineRecipe> streamCodec() {
            return new StreamCodec<RegistryFriendlyByteBuf, CoffeeMachineRecipe>() {
                @Override
                public CoffeeMachineRecipe decode(RegistryFriendlyByteBuf pBuffer) {

                    ResourceLocation id = pBuffer.readResourceLocation();

                    NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

                    for (int i = 0; i < inputs.size(); i++) {
                        inputs.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(pBuffer));
                    }

                    int waterAmount = pBuffer.readInt();
                    int ticks = pBuffer.readInt();
                    ItemStack onOutput = ItemStack.OPTIONAL_STREAM_CODEC.decode(pBuffer);
                    ItemStack output = ItemStack.STREAM_CODEC.decode(pBuffer);

                    return new CoffeeMachineRecipe(id,output, inputs, ticks, waterAmount, onOutput);
                }

                @Override
                public void encode(RegistryFriendlyByteBuf pBuffer, CoffeeMachineRecipe pValue) {
                    pBuffer.writeResourceLocation(pValue.id);

                    pBuffer.writeInt(pValue.recipeItems.size());
                    for (Ingredient ing : pValue.recipeItems) {
                        Ingredient.CONTENTS_STREAM_CODEC.encode(pBuffer,ing);
                    }

                    pBuffer.writeInt(pValue.waterNeeded);
                    pBuffer.writeInt(pValue.ticks);
                    ItemStack.OPTIONAL_STREAM_CODEC.encode(pBuffer, pValue.itemOnOutput);
                    ItemStack.STREAM_CODEC.encode(pBuffer, pValue.getResultItem(null));
                }
            };
        }
    }
}
