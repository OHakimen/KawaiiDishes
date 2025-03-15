package com.hakimen.kawaiidishes.recipes;

import com.hakimen.kawaiidishes.KawaiiDishes;
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
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BlenderRecipe implements Recipe<SimpleContainerRecipeInput> {

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

    public BlenderRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems, int ticks, Optional<ItemStack> itemOnOutput) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.ticks = ticks;
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


    @Override
    public boolean matches(SimpleContainerRecipeInput container, Level pLevel) {
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
    public ItemStack assemble(SimpleContainerRecipeInput container, HolderLookup.Provider pRegistryAccess) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider pRegistries) {
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
                ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "blending");

        private static final MapCodec<BlenderRecipe> CODEC = RecordCodecBuilder.mapCodec(
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
                                        .forGetter(BlenderRecipe::getRecipeItems),
                                ExtraCodecs.POSITIVE_INT.fieldOf("ticks").forGetter(BlenderRecipe::getTicks),
                                CraftableCodecs.ITEM_STACK_CODEC.fieldOf("itemOnOutput").forGetter(recipe -> recipe.itemOnOutput))
                        .apply(instance, BlenderRecipe::new)
        );
        @Override
        public MapCodec<BlenderRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, BlenderRecipe> streamCodec() {
            return new StreamCodec<RegistryFriendlyByteBuf, BlenderRecipe>() {
                @Override
                public BlenderRecipe decode(RegistryFriendlyByteBuf pBuffer) {
                    ResourceLocation id = pBuffer.readResourceLocation();

                    NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

                    for (int i = 0; i < inputs.size(); i++) {
                        inputs.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(pBuffer));
                    }

                    int ticks = pBuffer.readInt();
                    ItemStack onOutput = ItemStack.OPTIONAL_STREAM_CODEC.decode(pBuffer);
                    ItemStack output = ItemStack.STREAM_CODEC.decode(pBuffer);

                    return new BlenderRecipe(id,output, inputs, ticks, onOutput);
                }

                @Override
                public void encode(RegistryFriendlyByteBuf pBuffer, BlenderRecipe pValue) {
                    pBuffer.writeResourceLocation(pValue.id);

                    pBuffer.writeInt(pValue.recipeItems.size());
                    for (Ingredient ing : pValue.recipeItems) {
                        Ingredient.CONTENTS_STREAM_CODEC.encode(pBuffer,ing);
                    }

                    pBuffer.writeInt(pValue.ticks);
                    ItemStack.OPTIONAL_STREAM_CODEC.encode(pBuffer, pValue.itemOnOutput);
                    ItemStack.STREAM_CODEC.encode(pBuffer, pValue.getResultItem(null));
                }
            };
        }

    }
}
