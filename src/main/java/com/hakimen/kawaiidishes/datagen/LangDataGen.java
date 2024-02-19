package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.registry.EffectRegister;
import com.hakimen.kawaiidishes.registry.EnchantmentRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.data.DataGenerator;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class LangDataGen extends LanguageProvider {
    @Override
    protected void addTranslations() {
        add("item.kawaiidishes.base_dye","Base %s");
        add("item.kawaiidishes.overlay_dye","Decoration %s");


        add("kawaiidishes.tooltip.liquid.amount_with_capacity","%dmB/%dmB");
        add("kawaiidishes.tooltip.liquid.amount","%dmB");


        add("item.kawaiidishes.dress_color","Dress Color %s");
        add("item.kawaiidishes.dress_decoration_color","Dress Decoration Color %s");
        add("item.kawaiidishes.tail_color","Tail Color %s");
        add("item.kawaiidishes.tail_decoration_color","Tail Decoration Color %s");

        add("item.kawaiidishes.head_band_color","Head Band Color %s");
        add("item.kawaiidishes.head_band_decoration_color","Head Band Decoration Color %s");
        add("item.kawaiidishes.ears_color","Ears Color %s");
        add("item.kawaiidishes.ears_decoration_color","Ears Decoration Color %s");

        add("item.kawaiidishes.dyeable", "Dyeable");

        tabs();
        guis();
        items();
        blocks();
        effects();
        enchants();

        jeiNames();
    }

    private void tabs(){
        add("itemGroup.kawaiidishes.clothing","Kawaii Dishes: Clothing");
        add("itemGroup.kawaiidishes.food","Kawaii Dishes: Food");
        add("itemGroup.kawaiidishes.decoration","Kawaii Dishes: Decoration");
    }

    private void guis(){
        add("gui.kawaiidishes.coffee_machine", "Coffee Machine");
        add("gui.kawaiidishes.blender", "Blender");
        add("gui.kawaiidishes.display_case", "Display Case");
        add("gui.kawaiidishes.ice_cream_maker", "Ice Cream Maker");
    }

    private void items(){

        add(ItemRegister.THIGH_HIGHS.get(), "Thigh Highs");
        addThighHighDecorationNames();

        add(ItemRegister.MAID_DRESS.get(), "Maid Dress");
        add(ItemRegister.HEAD_BAND.get(), "Head Band");
        add(ItemRegister.SHOES.get(), "Shoes");

        add(ItemRegister.FOX_TAIL.get(), "Fox Tail");
        add(ItemRegister.MAID_DRESS_FOX_TAIL.get(), "Maid Dress with Fox Tail");
        add(ItemRegister.FOX_EARS.get(), "Fox Ears");
        add(ItemRegister.HEAD_BAND_FOX_EARS.get(), "Head Band with Fox Ears");

        add(ItemRegister.BUNNY_TAIL.get(), "Bunny Tail");
        add(ItemRegister.MAID_DRESS_BUNNY_TAIL.get(), "Maid Dress with Bunny Tail");
        add(ItemRegister.BUNNY_EARS.get(), "Bunny Ears");
        add(ItemRegister.HEAD_BAND_BUNNY_EARS.get(), "Head Band with Bunny Ears");

        add(ItemRegister.CAT_TAIL.get(), "Cat Tail");
        add(ItemRegister.MAID_DRESS_CAT_TAIL.get(), "Maid Dress with Cat Tail");
        add(ItemRegister.CAT_EARS.get(), "Cat Ears");
        add(ItemRegister.HEAD_BAND_CAT_EARS.get(), "Head Band with Cat Ears");

        add(ItemRegister.APRON.get(), "Apron");

        add(ItemRegister.LEG_CLIP.get(), "Leg Clip");
        add(ItemRegister.FULL_BANDS.get(), "Full Bands");
        add(ItemRegister.BOW.get(), "Bow");
        add(ItemRegister.DOUBLE_BANDS.get(),"Double Bands");

        add(ItemRegister.COFFEE_BERRIES.get(), "Coffee Berries");
        add(ItemRegister.CHERRY.get(), "Cherry");

        add(ItemRegister.COFFEE_BEANS.get(), "Coffee Beans");
        add(ItemRegister.ROAST_COFFEE_BEANS.get(), "Roast Coffee Beans");

        add(ItemRegister.GROUND_COFFEE.get(), "Ground Coffee");
        add(ItemRegister.COCOA_POWDER.get(), "Cocoa Powder");

        add(ItemRegister.STEAMED_MILK_BUCKET.get(), "Steamed Milk Bucket");
        add(ItemRegister.MILK_FOAM_BUCKET.get(), "Milk Foam Bucket");

        add(ItemRegister.CREAM_CHEESE_BALL.get(), "Cream Cheese Ball");

        add(ItemRegister.CHOCOLATE_COOKIE.get(), "Chocolate Cookie");
        add(ItemRegister.SWEET_BERRY_COOKIE.get(), "Sweet Berry Cookie");
        add(ItemRegister.GLOW_BERRY_COOKIE.get(), "Glow Berry Cookie");
        add(ItemRegister.GOLDEN_COOKIE.get(), "Golden Cookie");
        add(ItemRegister.COOKIE_OF_UNBINDING.get(), "Cookie of Unbinding");

        add(ItemRegister.CAKE_SLICE.get(), "Cake Slice");
        add(ItemRegister.CHEESE_CAKE_SLICE.get(), "Cheese Cake Slice");
        add(ItemRegister.CHOCOLATE_CHEESE_CAKE_SLICE.get(), "Chocolate Cheese Cake Slice");
        add(ItemRegister.HONEY_CHEESE_CAKE_SLICE.get(), "Honey Cheese Cake Slice");

        add(ItemRegister.APPLE_PIE_SLICE.get(), "Apple Pie Slice");
        add(ItemRegister.SWEET_BERRY_PIE_SLICE.get(), "Sweet Berry Pie Slice");
        add(ItemRegister.GLOW_BERRY_PIE_SLICE.get(), "Glow Berry Pie Slice");
        add(ItemRegister.CHERRY_PIE_SLICE.get(), "Cherry Pie Slice");

        add(ItemRegister.CREAM_ICE_CREAM.get(), "Cream Ice Cream");
        add(ItemRegister.COFFEE_ICE_CREAM.get(), "Coffee Ice Cream");
        add(ItemRegister.CHOCOLATE_ICE_CREAM.get(), "Chocolate Ice Cream");
        add(ItemRegister.SWEET_BERRY_ICE_CREAM.get(), "Sweet Berry Ice Cream");
        add(ItemRegister.GLOW_BERRY_ICE_CREAM.get(), "Glow Berry Ice Cream");
        add(ItemRegister.NEAPOLITAN_ICE_CREAM.get(), "Neapolitan Ice Cream");
        add(ItemRegister.MOCHA_ICE_CREAM.get(), "Mocha Ice Cream");
        add(ItemRegister.CHERRY_ICE_CREAM.get(), "Cherry Ice Cream");

        add(ItemRegister.WAFFLE.get(), "Waffle");
        add(ItemRegister.CHOCOLATE_WAFFLE.get(), "Chocolate Waffle");

    }
    private void blocks(){
        add(ItemRegister.COFFEE_MACHINE.get(), "Coffee Machine");
        add(ItemRegister.BLENDER.get(), "Blender");


        add(ItemRegister.MUG.get(), "Mug");
        add(ItemRegister.DARK_COFFEE.get(), "Dark Coffee");
        add(ItemRegister.ESPRESSO_COFFEE.get(), "Espresso");
        add(ItemRegister.DOPPIO_COFFEE.get(), "Doppio");
        add(ItemRegister.MACCHIATO_COFFEE.get(), "Macchiato");
        add(ItemRegister.LATTE_COFFEE.get(), "Latte");
        add(ItemRegister.CAPUCCINO_COFFEE.get(), "Capuccino");
        add(ItemRegister.MOCHA_COFFEE.get(), "Mocha");

        add(ItemRegister.HOT_COCOA.get(), "Hot Cocoa");

        add(ItemRegister.CHEESE_CAKE.get(), "Cheese Cake");
        add(ItemRegister.CHOCOLATE_CHEESE_CAKE.get(), "Chocolate Cheese Cake");
        add(ItemRegister.HONEY_CHEESE_CAKE.get(), "Honey Cheese Cake");

        add(ItemRegister.APPLE_PIE.get(), "Apple Pie");
        add(ItemRegister.SWEET_BERRY_PIE.get(), "Sweet Berry Pie");
        add(ItemRegister.GLOW_BERRY_PIE.get(), "Glow Berry Pie");
        add(ItemRegister.CHERRY_PIE.get(), "Cherry Pie");

        add(ItemRegister.SEAT.get(), "Seat");
        add(ItemRegister.KITCHEN_TILES.get(), "Kitchen Tiles");
        add(ItemRegister.DISPLAY_CASE.get(), "Display Case");
        add(ItemRegister.ICE_CREAM_MAKER.get(), "Ice Cream Maker");
        add(ItemRegister.INCENSE_GLASS.get(), "Incense Glass");
    }
    private void addThighHighDecorationNames(){
        add("item.kawaiidishes.thigh_highs.double_band", "Double Banded");
        add("item.kawaiidishes.thigh_highs.full_band", "Fully Banded");
        add("item.kawaiidishes.thigh_highs.leg_clip", "Leg Clip");
        add("item.kawaiidishes.thigh_highs.bow", "Bow");
    }

    private void enchants(){
        add(EnchantmentRegister.CAT_AURA.get(), "Cat's Aura");
        add(EnchantmentRegister.FOX_APTITUDE.get(), "Fox's Aptitude");
        add(EnchantmentRegister.BUNNY_HASTE.get(), "Bunny's Haste");
        add(EnchantmentRegister.AUTO_EQUIP_CURSE.get(), "Curse of Automatic Equipment");
    }

    private void jeiNames(){
        add("jei.kawaiidishes.recipe.ice_cream_making", "Ice Cream Making");
        add("jei.kawaiidishes.recipe.coffee_machining", "Coffee Machining");
        add("jei.kawaiidishes.recipe.blending", "Blending");
    }
    private void effects(){
        add(EffectRegister.BLESSING_OF_UNBINDING.get(), "Unbinding");
    }

    public LangDataGen(DataGenerator dataGenerator){
        super(dataGenerator.getPackOutput(), KawaiiDishes.MODID, "en_us");
    }
}
