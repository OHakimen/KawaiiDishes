package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.custom.Registries;
import com.hakimen.kawaiidishes.custom.types.ThighHighDecoration;
import com.hakimen.kawaiidishes.registry.*;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class LangDataGen extends FabricLanguageProvider {
    protected LangDataGen(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add("item.kawaiidishes.base_dye","Base %s");
        translationBuilder.add("item.kawaiidishes.overlay_dye","Decoration %s");

        translationBuilder.add("kawaiidishes.tooltip.liquid.amount_with_capacity","%dmB/%dmB");
        translationBuilder.add("kawaiidishes.tooltip.liquid.amount","%dmB");

        translationBuilder.add("item.kawaiidishes.dress_color","Dress Color %s");
        translationBuilder.add("item.kawaiidishes.dress_decoration_color","Dress Decoration Color %s");

        translationBuilder.add("item.kawaiidishes.tail_color","Tail Color %s");
        translationBuilder.add("item.kawaiidishes.tail_decoration_color","Tail Decoration Color %s");

        translationBuilder.add("item.kawaiidishes.head_band_color","Head Band Color %s");
        translationBuilder.add("item.kawaiidishes.head_band_decoration_color","Head Band Decoration Color %s");

        translationBuilder.add("item.kawaiidishes.ears_color","Ears Color %s");
        translationBuilder.add("item.kawaiidishes.ears_decoration_color","Ears Decoration Color %s");

        translationBuilder.add("item.kawaiidishes.dyeable", "Dyeable");
        translationBuilder.add("item.kawaiidishes.overlayed", "Overlayed");


        item(translationBuilder);
        itemTabs(translationBuilder);
        blocks(translationBuilder);
        enchants(translationBuilder);
        effects(translationBuilder);

        guis(translationBuilder);

        emi(translationBuilder);
    }

    public void emi(TranslationBuilder translationBuilder){
        translationBuilder.add("emi.category.kawaiidishes.ice_cream_maker", "Ice Cream Making");
        translationBuilder.add("emi.category.kawaiidishes.blender", "Blending");
        translationBuilder.add("emi.category.kawaiidishes.coffee_machine", "Coffee Machining");
    }

    public void blocks(TranslationBuilder translationBuilder){

        translationBuilder.add(ItemRegister.COFFEE_MACHINE.get(), "Coffee Machine");
        translationBuilder.add(ItemRegister.BLENDER.get(), "Blender");

        translationBuilder.add(BlockRegister.COFFEE_BUSH.get(), "Coffee Berries");

        translationBuilder.add(ItemRegister.MUG.get(), "Mug");
        translationBuilder.add(ItemRegister.DARK_COFFEE.get(), "Dark Coffee");
        translationBuilder.add(ItemRegister.ESPRESSO_COFFEE.get(), "Espresso");
        translationBuilder.add(ItemRegister.DOPPIO_COFFEE.get(), "Doppio");
        translationBuilder.add(ItemRegister.MACCHIATO_COFFEE.get(), "Macchiato");
        translationBuilder.add(ItemRegister.LATTE_COFFEE.get(), "Latte");
        translationBuilder.add(ItemRegister.CAPUCCINO_COFFEE.get(), "Capuccino");
        translationBuilder.add(ItemRegister.MOCHA_COFFEE.get(), "Mocha");

        translationBuilder.add(ItemRegister.HOT_COCOA.get(), "Hot Cocoa");

        translationBuilder.add(ItemRegister.CHEESE_CAKE.get(), "Cheese Cake");
        translationBuilder.add(ItemRegister.CHOCOLATE_CHEESE_CAKE.get(), "Chocolate Cheese Cake");
        translationBuilder.add(ItemRegister.HONEY_CHEESE_CAKE.get(), "Honey Cheese Cake");

        translationBuilder.add(ItemRegister.APPLE_PIE.get(), "Apple Pie");
        translationBuilder.add(ItemRegister.SWEET_BERRY_PIE.get(), "Sweet Berry Pie");
        translationBuilder.add(ItemRegister.GLOW_BERRY_PIE.get(), "Glow Berry Pie");
        translationBuilder.add(ItemRegister.CHERRY_PIE.get(), "Cherry Pie");

        translationBuilder.add(ItemRegister.SEAT.get(), "Seat");

        translationBuilder.add(ItemRegister.INCENSE_GLASS.get(), "Incense Glass");
        translationBuilder.add(ItemRegister.KITCHEN_TILES.get(), "Kitchen Tiles");
        translationBuilder.add(ItemRegister.DISPLAY_CASE.get(), "Display Case");
        translationBuilder.add(ItemRegister.ICE_CREAM_MAKER.get(), "Ice Cream Maker");
    }

    private void enchants(TranslationBuilder translationBuilder){
        translationBuilder.add(EnchantmentRegister.CAT_AURA.get(), "Cat's Aura");
        translationBuilder.add(EnchantmentRegister.FOX_APTITUDE.get(), "Fox's Aptitude");
        translationBuilder.add(EnchantmentRegister.BUNNY_HASTE.get(), "Bunny's Haste");
        translationBuilder.add(EnchantmentRegister.AUTO_EQUIP_CURSE.get(), "Curse of Automatic Equipment");
    }

    public void itemTabs(TranslationBuilder translationBuilder){
        translationBuilder.add("itemGroup.kawaiidishes.food", "Kawaii Dishes: Foods");
        translationBuilder.add("itemGroup.kawaiidishes.clothing","Kawaii Dishes: Clothing");
        translationBuilder.add("itemGroup.kawaiidishes.decoration", "Kawaii Dishes: Decoration");
    }

    private void effects(TranslationBuilder translationBuilder){
        translationBuilder.add(EffectRegister.BLESSING_OF_UNBINDING.get(), "Unbinding");
    }


    public void item(TranslationBuilder translationBuilder){
        translationBuilder.add(ItemRegister.THIGH_HIGHS.get(), "Thigh Highs");

        addThighHighDecorNames("thighhighsdecoration.kawaiidishes.double_bands", "Double Banded", translationBuilder);
        addThighHighDecorNames("thighhighsdecoration.kawaiidishes.bow", "Bow", translationBuilder);
        addThighHighDecorNames("thighhighsdecoration.kawaiidishes.full_bands", "Fully Banded", translationBuilder);
        addThighHighDecorNames("thighhighsdecoration.kawaiidishes.leg_clip", "Leg Clip", translationBuilder);

        translationBuilder.add(ItemRegister.MAID_DRESS.get(), "Maid Dress");
        translationBuilder.add(ItemRegister.HEAD_BAND.get(), "Head Band");
        translationBuilder.add(ItemRegister.SHOES.get(), "Shoes");

        translationBuilder.add(ItemRegister.FOX_TAIL.get(), "Fox Tail");
        translationBuilder.add(ItemRegister.MAID_DRESS_FOX_TAIL.get(), "Maid Dress with Fox Tail");
        translationBuilder.add(ItemRegister.FOX_EARS.get(), "Fox Ears");
        translationBuilder.add(ItemRegister.HEAD_BAND_FOX_EARS.get(), "Head Band with Fox Ears");

        translationBuilder.add(ItemRegister.BUNNY_TAIL.get(), "Bunny Tail");
        translationBuilder.add(ItemRegister.MAID_DRESS_BUNNY_TAIL.get(), "Maid Dress with Bunny Tail");
        translationBuilder.add(ItemRegister.BUNNY_EARS.get(), "Bunny Ears");
        translationBuilder.add(ItemRegister.HEAD_BAND_BUNNY_EARS.get(), "Head Band with Bunny Ears");

        translationBuilder.add(ItemRegister.CAT_TAIL.get(), "Cat Tail");
        translationBuilder.add(ItemRegister.MAID_DRESS_CAT_TAIL.get(), "Maid Dress with Cat Tail");
        translationBuilder.add(ItemRegister.CAT_EARS.get(), "Cat Ears");
        translationBuilder.add(ItemRegister.HEAD_BAND_CAT_EARS.get(), "Head Band with Cat Ears");

        translationBuilder.add(ItemRegister.APRON.get(), "Apron");
        translationBuilder.add(ItemRegister.LEG_CLIP.get(), "Leg Clip");
        translationBuilder.add(ItemRegister.FULL_BANDS.get(), "Full Bands");
        translationBuilder.add(ItemRegister.BOW.get(), "Bow");
        translationBuilder.add(ItemRegister.DOUBLE_BANDS.get(),"Double Bands");

        translationBuilder.add(ItemRegister.CHERRY.get(), "Cherry");
        translationBuilder.add(ItemRegister.COFFEE_BEANS.get(), "Coffee Beans");

        translationBuilder.add(ItemRegister.ROAST_COFFEE_BEANS.get(), "Roast Coffee Beans");
        translationBuilder.add(ItemRegister.GROUND_COFFEE.get(), "Ground Coffee");
        translationBuilder.add(ItemRegister.COCOA_POWDER.get(), "Cocoa Powder");

        translationBuilder.add(ItemRegister.STEAMED_MILK_BUCKET.get(), "Steamed Milk Bucket");
        translationBuilder.add(ItemRegister.MILK_FOAM_BUCKET.get(), "Milk Foam Bucket");
        translationBuilder.add(ItemRegister.CREAM_CHEESE_BALL.get(), "Cream Cheese Ball");

        translationBuilder.add(ItemRegister.CHOCOLATE_COOKIE.get(), "Chocolate Cookie");
        translationBuilder.add(ItemRegister.SWEET_BERRY_COOKIE.get(), "Sweet Berry Cookie");
        translationBuilder.add(ItemRegister.GLOW_BERRY_COOKIE.get(), "Glow Berry Cookie");
        translationBuilder.add(ItemRegister.GOLDEN_COOKIE.get(), "Golden Cookie");
        translationBuilder.add(ItemRegister.COOKIE_OF_UNBINDING.get(), "Cookie of Unbinding");
        translationBuilder.add(ItemRegister.CAKE_SLICE.get(), "Cake Slice");
        translationBuilder.add(ItemRegister.CHEESE_CAKE_SLICE.get(), "Cheese Cake Slice");
        translationBuilder.add(ItemRegister.CHOCOLATE_CHEESE_CAKE_SLICE.get(), "Chocolate Cheese Cake Slice");
        translationBuilder.add(ItemRegister.HONEY_CHEESE_CAKE_SLICE.get(), "Honey Cheese Cake Slice");
        translationBuilder.add(ItemRegister.APPLE_PIE_SLICE.get(), "Apple Pie Slice");
        translationBuilder.add(ItemRegister.SWEET_BERRY_PIE_SLICE.get(), "Sweet Berry Pie Slice");
        translationBuilder.add(ItemRegister.GLOW_BERRY_PIE_SLICE.get(), "Glow Berry Pie Slice");
        translationBuilder.add(ItemRegister.CHERRY_PIE_SLICE.get(), "Cherry Pie Slice");

        translationBuilder.add(ItemRegister.CREAM_ICE_CREAM.get(), "Cream Ice Cream");
        translationBuilder.add(ItemRegister.COFFEE_ICE_CREAM.get(), "Coffee Ice Cream");
        translationBuilder.add(ItemRegister.CHOCOLATE_ICE_CREAM.get(), "Chocolate Ice Cream");
        translationBuilder.add(ItemRegister.SWEET_BERRY_ICE_CREAM.get(), "Sweet Berry Ice Cream");
        translationBuilder.add(ItemRegister.GLOW_BERRY_ICE_CREAM.get(), "Glow Berry Ice Cream");
        translationBuilder.add(ItemRegister.NEAPOLITAN_ICE_CREAM.get(), "Neapolitan Ice Cream");
        translationBuilder.add(ItemRegister.MOCHA_ICE_CREAM.get(), "Mocha Ice Cream");
        translationBuilder.add(ItemRegister.CHERRY_ICE_CREAM.get(), "Cherry Ice Cream");

        translationBuilder.add(ItemRegister.WAFFLE.get(), "Waffle");
        translationBuilder.add(ItemRegister.CHOCOLATE_WAFFLE.get(), "Chocolate Waffle");
    }

    public void guis(TranslationBuilder translationBuilder){
        translationBuilder.add("gui.kawaiidishes.blender", "Blender");
        translationBuilder.add("gui.kawaiidishes.coffee_machine", "Coffee Machine");
        translationBuilder.add("gui.kawaiidishes.ice_cream_maker", "Ice Cream Maker");
    }

    public void addThighHighDecorNames(String name, String translation, TranslationBuilder translationBuilder){
        translationBuilder.add(name,translation);
    }
}
