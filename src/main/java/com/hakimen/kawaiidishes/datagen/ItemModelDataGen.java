package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ItemModelDataGen extends ItemModelProvider {

    public ItemModelDataGen(DataGenerator dataGenerator, ExistingFileHelper helper) {
        super(dataGenerator.getPackOutput(), KawaiiDishes.MODID, helper);
    }

    @Override
    protected void registerModels() {
        basicItem(ItemRegister.APRON.get());

        decorationItem(ItemRegister.BOW.get());
        decorationItem(ItemRegister.LEG_CLIP.get());
        decorationItem(ItemRegister.FULL_BANDS.get());
        decorationItem(ItemRegister.DOUBLE_BANDS.get());

        foodItem(ItemRegister.COFFEE_BERRIES.get());
        foodItem(ItemRegister.CHERRY.get());

        foodItem(ItemRegister.COFFEE_BEANS.get());
        foodItem(ItemRegister.ROAST_COFFEE_BEANS.get());

        foodItem(ItemRegister.GROUND_COFFEE.get());
        foodItem(ItemRegister.COCOA_POWDER.get());

        foodItem(ItemRegister.MILK_FOAM_BUCKET.get());
        foodItem(ItemRegister.STEAMED_MILK_BUCKET.get());

        foodItem(ItemRegister.CREAM_CHEESE_BALL.get());

        foodItem(ItemRegister.CHOCOLATE_COOKIE.get());
        foodItem(ItemRegister.SWEET_BERRY_COOKIE.get());
        foodItem(ItemRegister.GLOW_BERRY_COOKIE.get());
        foodItem(ItemRegister.GOLDEN_COOKIE.get());
        foodItem(ItemRegister.COOKIE_OF_UNBINDING.get());

        foodItem(ItemRegister.CAKE_SLICE.get());
        foodItem(ItemRegister.CHEESE_CAKE_SLICE.get());
        foodItem(ItemRegister.CHOCOLATE_CHEESE_CAKE_SLICE.get());
        foodItem(ItemRegister.HONEY_CHEESE_CAKE_SLICE.get());

        foodItem(ItemRegister.APPLE_PIE_SLICE.get());
        foodItem(ItemRegister.SWEET_BERRY_PIE_SLICE.get());
        foodItem(ItemRegister.GLOW_BERRY_PIE_SLICE.get());
        foodItem(ItemRegister.CHERRY_PIE_SLICE.get());

        foodItem(ItemRegister.CREAM_ICE_CREAM.get());
        foodItem(ItemRegister.COFFEE_ICE_CREAM.get());
        foodItem(ItemRegister.CHOCOLATE_ICE_CREAM.get());
        foodItem(ItemRegister.SWEET_BERRY_ICE_CREAM.get());
        foodItem(ItemRegister.GLOW_BERRY_ICE_CREAM.get());
        foodItem(ItemRegister.NEAPOLITAN_ICE_CREAM.get());
        foodItem(ItemRegister.MOCHA_ICE_CREAM.get());
        foodItem(ItemRegister.CHERRY_ICE_CREAM.get());

        foodItem(ItemRegister.WAFFLE.get());
        foodItem(ItemRegister.CHOCOLATE_WAFFLE.get());

        blockItem(ItemRegister.COFFEE_MACHINE.get());
        blockItem(ItemRegister.BLENDER.get());
        blockItem(ItemRegister.ICE_CREAM_MAKER.get());

        blockItem(ItemRegister.MUG.get());
        blockItem(ItemRegister.SEAT.get());
        blockItem(ItemRegister.KITCHEN_TILES.get());

        blockItem(ItemRegister.DISPLAY_CASE.get());

        drinkBlockItem(ItemRegister.DARK_COFFEE.get());
        drinkBlockItem(ItemRegister.ESPRESSO_COFFEE.get());
        drinkBlockItem(ItemRegister.DOPPIO_COFFEE.get());
        drinkBlockItem(ItemRegister.MACCHIATO_COFFEE.get());
        drinkBlockItem(ItemRegister.LATTE_COFFEE.get());
        drinkBlockItem(ItemRegister.CAPUCCINO_COFFEE.get());
        drinkBlockItem(ItemRegister.MOCHA_COFFEE.get());

        drinkBlockItem(ItemRegister.HOT_COCOA.get());

        cakeBlockItem(ItemRegister.CHEESE_CAKE.get());
        cakeBlockItem(ItemRegister.CHOCOLATE_CHEESE_CAKE.get());
        cakeBlockItem(ItemRegister.HONEY_CHEESE_CAKE.get());

        blockItem(ItemRegister.INCENSE_GLASS.get());

        pieBlockItem(ItemRegister.APPLE_PIE.get());
        pieBlockItem(ItemRegister.SWEET_BERRY_PIE.get());
        pieBlockItem(ItemRegister.GLOW_BERRY_PIE.get());
        pieBlockItem(ItemRegister.CHERRY_PIE.get());
    }

    public void blockItem(BlockItem blockItem){
        withExistingParent(
                BuiltInRegistries.ITEM.getKey(blockItem).toString().replace(KawaiiDishes.MODID+":", ""),
                BuiltInRegistries.BLOCK.getKey(blockItem.getBlock()).toString().replace(":", ":block/"));
    }

    public void drinkBlockItem(BlockItem blockItem){
        withExistingParent(
                BuiltInRegistries.ITEM.getKey(blockItem).toString().replace(KawaiiDishes.MODID+":", ""),
                BuiltInRegistries.BLOCK.getKey(blockItem.getBlock()).toString().replace(":", ":block/drinks/"));
    }

    public void cakeBlockItem(BlockItem blockItem){
        withExistingParent(
                BuiltInRegistries.ITEM.getKey(blockItem).toString().replace(KawaiiDishes.MODID+":", ""),
                BuiltInRegistries.BLOCK.getKey(blockItem.getBlock()).toString().replace(":", ":block/cake/") + "_slice_4");

    }

    public void pieBlockItem(BlockItem blockItem){
        withExistingParent(
                BuiltInRegistries.ITEM.getKey(blockItem).toString().replace(KawaiiDishes.MODID+":", ""),
                BuiltInRegistries.BLOCK.getKey(blockItem.getBlock()).toString().replace(":", ":block/pie/") + "_slice_4");

    }


    public ItemModelBuilder decorationItem(Item toRegister)
    {
        ResourceLocation item = BuiltInRegistries.ITEM.getKey(toRegister);
        return getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(item.getNamespace(), "item/thigh_highs/decorations/" + item.getPath()));
    }

    public ItemModelBuilder foodItem(Item toRegister)
    {
        ResourceLocation item = BuiltInRegistries.ITEM.getKey(toRegister);
        return getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(item.getNamespace(), "item/food/" + item.getPath()));
    }
}
