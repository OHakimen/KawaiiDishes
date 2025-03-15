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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class IceCreamMakerRecipe implements Recipe<SimpleContainerRecipeInput> {

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    private final int snowballs;
    private final int ticks;
    private final ItemStack itemOnOutput;

    public IceCreamMakerRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems, int ticks, int snowballs, ItemStack itemOnOutput) {
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


    public int getSnowballs() {
        return snowballs;
    }

    @Override
    public boolean matches(SimpleContainerRecipeInput container, Level pLevel) {
        List<Integer> slots = new ArrayList<Integer>();


        if (itemOnOutput != ItemStack.EMPTY && !container.getItem(4).is(itemOnOutput.getItem())) {
            return false;
        }

        if(container.getItem(0).getCount() < snowballs){
            return false;
        }

        for (int i = 1; i < 4; i++) {
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
    public ItemStack getResultItem(HolderLookup.Provider pRegistryAccess) {
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
        public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "ice_cream_making");

        private static final MapCodec<IceCreamMakerRecipe> CODEC = RecordCodecBuilder.mapCodec(
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
                                        .forGetter(IceCreamMakerRecipe::getRecipeItems),
                                ExtraCodecs.POSITIVE_INT.fieldOf("ticks").forGetter(IceCreamMakerRecipe::getTicks),
                                ExtraCodecs.NON_NEGATIVE_INT.fieldOf("snowballs").forGetter(IceCreamMakerRecipe::getSnowballs),
                                CraftableCodecs.ITEM_STACK_CODEC.optionalFieldOf("itemOnOutput",ItemStack.EMPTY).forGetter(recipe -> recipe.itemOnOutput))
                        .apply(instance, IceCreamMakerRecipe::new)
        );
        @Override
        public MapCodec<IceCreamMakerRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, IceCreamMakerRecipe> streamCodec() {
            return new StreamCodec<RegistryFriendlyByteBuf, IceCreamMakerRecipe>() {
                @Override
                public IceCreamMakerRecipe decode(RegistryFriendlyByteBuf pBuffer) {

                    ResourceLocation id = pBuffer.readResourceLocation();

                    NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

                    for (int i = 0; i < inputs.size(); i++) {
                        inputs.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(pBuffer));
                    }

                    int ticks = pBuffer.readInt();
                    ItemStack onOutput = ItemStack.OPTIONAL_STREAM_CODEC.decode(pBuffer);
                    ItemStack output = ItemStack.STREAM_CODEC.decode(pBuffer);
                    int snowballs = pBuffer.readInt();

                    return new IceCreamMakerRecipe(id,output, inputs, ticks, snowballs, onOutput);
                }

                @Override
                public void encode(RegistryFriendlyByteBuf pBuffer, IceCreamMakerRecipe pValue) {
                    pBuffer.writeResourceLocation(pValue.id);


                    pBuffer.writeInt(pValue.recipeItems.size());
                    for (Ingredient ing : pValue.recipeItems) {
                        Ingredient.CONTENTS_STREAM_CODEC.encode(pBuffer,ing);
                    }

                    pBuffer.writeInt(pValue.ticks);
                     ItemStack.OPTIONAL_STREAM_CODEC.encode(pBuffer,pValue.itemOnOutput);
                     ItemStack.STREAM_CODEC.encode(pBuffer,pValue.getResultItem(null));
                    pBuffer.writeInt(pValue.snowballs);
                }
            };
        }

    }
}
