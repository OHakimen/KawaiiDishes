package com.hakimen.kawaiidishes.datagen.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hakimen.kawaiidishes.custom.Registries;
import com.hakimen.kawaiidishes.custom.types.ThighHighDecoration;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import java.util.Map;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public class ItemModelDataGen {
    public static void gen(ItemModelGenerators itemGen){
        generateThighHighs(itemGen);

        decorationItem(ItemRegister.BOW.get(), itemGen);
        decorationItem(ItemRegister.LEG_CLIP.get(), itemGen);
        decorationItem(ItemRegister.FULL_BANDS.get(), itemGen);
        decorationItem(ItemRegister.DOUBLE_BANDS.get(), itemGen);

        basicItem(ItemRegister.APRON.get(), itemGen);

        foodItem(ItemRegister.COFFEE_BERRIES.get(), itemGen);
        foodItem(ItemRegister.CHERRY.get(), itemGen);

        foodItem(ItemRegister.COFFEE_BEANS.get(), itemGen);
        foodItem(ItemRegister.ROAST_COFFEE_BEANS.get(), itemGen);
        foodItem(ItemRegister.GROUND_COFFEE.get(), itemGen);
        foodItem(ItemRegister.COCOA_POWDER.get(), itemGen);
        foodItem(ItemRegister.MILK_FOAM_BUCKET.get(), itemGen);
        foodItem(ItemRegister.STEAMED_MILK_BUCKET.get(), itemGen);
        foodItem(ItemRegister.CREAM_CHEESE_BALL.get(), itemGen);
        foodItem(ItemRegister.CHOCOLATE_COOKIE.get(), itemGen);
        foodItem(ItemRegister.SWEET_BERRY_COOKIE.get(), itemGen);
        foodItem(ItemRegister.GLOW_BERRY_COOKIE.get(), itemGen);
        foodItem(ItemRegister.GOLDEN_COOKIE.get(), itemGen);
        foodItem(ItemRegister.COOKIE_OF_UNBINDING.get(), itemGen);

        foodItem(ItemRegister.CAKE_SLICE.get(), itemGen);
        foodItem(ItemRegister.CHEESE_CAKE_SLICE.get(), itemGen);
        foodItem(ItemRegister.CHOCOLATE_CHEESE_CAKE_SLICE.get(), itemGen);
        foodItem(ItemRegister.HONEY_CHEESE_CAKE_SLICE.get(), itemGen);
        foodItem(ItemRegister.APPLE_PIE_SLICE.get(), itemGen);
        foodItem(ItemRegister.SWEET_BERRY_PIE_SLICE.get(), itemGen);
        foodItem(ItemRegister.GLOW_BERRY_PIE_SLICE.get(), itemGen);
        foodItem(ItemRegister.CHERRY_PIE_SLICE.get(), itemGen);
        foodItem(ItemRegister.CREAM_ICE_CREAM.get(), itemGen);
        foodItem(ItemRegister.COFFEE_ICE_CREAM.get(), itemGen);
        foodItem(ItemRegister.CHOCOLATE_ICE_CREAM.get(), itemGen);
        foodItem(ItemRegister.SWEET_BERRY_ICE_CREAM.get(), itemGen);
        foodItem(ItemRegister.GLOW_BERRY_ICE_CREAM.get(), itemGen);
        foodItem(ItemRegister.NEAPOLITAN_ICE_CREAM.get(), itemGen);
        foodItem(ItemRegister.MOCHA_ICE_CREAM.get(), itemGen);
        foodItem(ItemRegister.CHERRY_ICE_CREAM.get(), itemGen);
        foodItem(ItemRegister.WAFFLE.get(), itemGen);
        foodItem(ItemRegister.CHOCOLATE_WAFFLE.get(), itemGen);

        blockItem(ItemRegister.MUG.get(), itemGen);
        blockItem(ItemRegister.COFFEE_MACHINE.get(), itemGen);
        blockItem(ItemRegister.BLENDER.get(), itemGen);
        blockItem(ItemRegister.ICE_CREAM_MAKER.get(), itemGen);

        blockItem(ItemRegister.SEAT.get(), itemGen);
//        blockItem(ItemRegister.KITCHEN_TILES.get());

        blockItem(ItemRegister.DISPLAY_CASE.get(),itemGen);

        drinkBlockItem(ItemRegister.DARK_COFFEE.get(), itemGen);
        drinkBlockItem(ItemRegister.ESPRESSO_COFFEE.get(), itemGen);
        drinkBlockItem(ItemRegister.DOPPIO_COFFEE.get(), itemGen);
        drinkBlockItem(ItemRegister.MACCHIATO_COFFEE.get(), itemGen);
        drinkBlockItem(ItemRegister.LATTE_COFFEE.get(), itemGen);
        drinkBlockItem(ItemRegister.CAPUCCINO_COFFEE.get(), itemGen);
        drinkBlockItem(ItemRegister.MOCHA_COFFEE.get(), itemGen);

        drinkBlockItem(ItemRegister.HOT_COCOA.get(), itemGen);

        cakeBlockItem(ItemRegister.CHEESE_CAKE.get(), itemGen);
        cakeBlockItem(ItemRegister.CHOCOLATE_CHEESE_CAKE.get(), itemGen);
        cakeBlockItem(ItemRegister.HONEY_CHEESE_CAKE.get(), itemGen);

        pieBlockItem(ItemRegister.APPLE_PIE.get(), itemGen);
        pieBlockItem(ItemRegister.SWEET_BERRY_PIE.get(), itemGen);
        pieBlockItem(ItemRegister.GLOW_BERRY_PIE.get(), itemGen);
        pieBlockItem(ItemRegister.CHERRY_PIE.get(), itemGen);


        blockItem(ItemRegister.INCENSE_GLASS.get(), itemGen);

    }

    public static void decorationItem(Item item, ItemModelGenerators itemGen){
        itemWithPath(item, "thigh_highs/decorations/", itemGen);
    }

    public static void generateThighHighs(ItemModelGenerators itemGen){
        JsonObject item = new JsonObject();
        item.addProperty("parent", "minecraft:item/generated");

        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", "kawaiidishes:item/thigh_highs");

        item.add("textures", textures);

        JsonArray overrides = new JsonArray();

        for (ThighHighDecoration decor: Registries.THIGH_HIGH_DECORATIONS){
            JsonObject override = new JsonObject();

            JsonObject predicate = new JsonObject();
            predicate.addProperty("kawaiidishes:decoration", (Registries.THIGH_HIGH_DECORATIONS.getId(decor) / (float) Registries.THIGH_HIGH_DECORATIONS.size()));

            override.add("predicate", predicate);
            override.addProperty("model", decor.getOverlayModel().toString());

            overrides.add(override);
        }

        item.add("overrides", overrides);

        ResourceLocation itemKey = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(ItemRegister.THIGH_HIGHS.get());
        //This makes the thigh high model itself
        itemGen.output.accept(new ResourceLocation(itemKey.getNamespace(), "item/" + itemKey.getPath()),() -> item);
    }


    public static void drinkBlockItem(BlockItem blockItem, ItemModelGenerators itemGen){
        blockItemWithPath(blockItem, "drinks/", itemGen);
    }
    public static void basicItem(Item item, ItemModelGenerators itemGen){
        itemWithPath(item, "", itemGen);
    }

    public static void blockItem(BlockItem blockItem, ItemModelGenerators itemGen){
        blockItemWithPath(blockItem, "", itemGen);
    }

    public static void pieBlockItem(BlockItem blockItem, ItemModelGenerators itemGen){
        ResourceLocation itemKey = net.minecraft.core.registries.BuiltInRegistries.BLOCK.getKey(blockItem.getBlock());
        String toRegister = "pie/" + itemKey.getPath() + "_slice_4";
        itemGen.output.accept(new ResourceLocation(itemKey.getNamespace(), "item/" + itemKey.getPath()),
                () -> {
                    JsonObject object = new JsonObject();
                    object.addProperty("parent", new ResourceLocation(itemKey.getNamespace(), "block/%s".formatted(toRegister)).toString());
                    return object;
                }
        );
    }


    public static void cakeBlockItem(BlockItem blockItem, ItemModelGenerators itemGen){
        ResourceLocation itemKey = net.minecraft.core.registries.BuiltInRegistries.BLOCK.getKey(blockItem.getBlock());
        String toRegister = "cake/" + itemKey.getPath() + "_slice_4";
        itemGen.output.accept(new ResourceLocation(itemKey.getNamespace(), "item/" + itemKey.getPath()),
                () -> {
                    JsonObject object = new JsonObject();
                    object.addProperty("parent", new ResourceLocation(itemKey.getNamespace(), "block/%s".formatted(toRegister)).toString());
                    return object;
                }
        );
    }
    public static void foodItem(Item item, ItemModelGenerators itemGen) {
        itemWithPath(item, "food/", itemGen);
    }

    public static void blockItemWithPath(BlockItem item, String where, ItemModelGenerators itemGen) {
        ResourceLocation itemKey = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(item);
        String toRegister = where + itemKey.getPath();
        itemGen.output.accept(new ResourceLocation(itemKey.getNamespace(), "item/" + itemKey.getPath()),
                () -> {
                    JsonObject object = new JsonObject();
                    object.addProperty("parent", new ResourceLocation(itemKey.getNamespace(), "block/%s".formatted(toRegister)).toString());
                    return object;
                }
        );
    }

    public static void itemWithPath(Item item, String where, ItemModelGenerators itemGen) {
        ResourceLocation itemKey = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(item);
        String toRegister = where + itemKey.getPath();
        itemGen.output.accept(new ResourceLocation(itemKey.getNamespace(), "item/" + itemKey.getPath()),
                () -> ModelTemplates.FLAT_ITEM.createBaseTemplate(itemKey,
                        Map.of(TextureSlot.create("layer0"), new ResourceLocation(itemKey.getNamespace(), "item/%s".formatted(toRegister)))
                )
        );
    }
}
