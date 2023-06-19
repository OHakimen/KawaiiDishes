package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.items.Candy;
import com.hakimen.kawaiidishes.items.PlaceableFoodItem;
import com.hakimen.kawaiidishes.items.UnbindingCookie;
import com.hakimen.kawaiidishes.items.armor.ArmorMaterials;
import com.hakimen.kawaiidishes.items.armor.GenericGeoArmorItem;
import com.hakimen.kawaiidishes.items.ears.BunnyEars;
import com.hakimen.kawaiidishes.items.ears.CatEars;
import com.hakimen.kawaiidishes.items.ears.FoxEars;
import com.hakimen.kawaiidishes.items.ears.Horns;
import com.hakimen.kawaiidishes.items.headbands.BunnyHeadband;
import com.hakimen.kawaiidishes.items.headbands.CatHeadband;
import com.hakimen.kawaiidishes.items.headbands.Headband;
import com.hakimen.kawaiidishes.items.headbands.HornHeadband;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.hakimen.kawaiidishes.KawaiiDishes.modId;
import static com.hakimen.kawaiidishes.registry.ArmorTickRegister.*;

public class ItemRegister {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, modId);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, modId);
    public static FoodProperties cake = new FoodProperties.Builder().nutrition(2).saturationMod(0.5f).build();

    public static final RegistryObject<Item> mug = ITEMS.register("mug", () -> new BlockItem(BlockRegister.mug.get(), new Item.Properties()));
    public static final RegistryObject<Item> glassCup = ITEMS.register("glass_cup", () -> new BlockItem(BlockRegister.glassCup.get(), new Item.Properties()));
    public static final RegistryObject<Item> milkshakeCup = ITEMS.register("milkshake_cup", () -> new BlockItem(BlockRegister.milkshakeCup.get(), new Item.Properties()));

    public static final RegistryObject<Item> coffeePress = ITEMS.register("coffee_press", () -> new BlockItem(BlockRegister.coffeePress.get(), new Item.Properties()));
    public static final RegistryObject<Item> coffeeMachine = ITEMS.register("coffee_machine", () -> new BlockItem(BlockRegister.coffeeMachine.get(), new Item.Properties()));
    public static final RegistryObject<Item> blender = ITEMS.register("blender", () -> new BlockItem(BlockRegister.blender.get(), new Item.Properties()));

    public static final RegistryObject<Item> iceCreamMachine = ITEMS.register("ice_cream_machine", () -> new BlockItem(BlockRegister.iceCreamMachine.get(), new Item.Properties()));

    public static final RegistryObject<CatEars> blackCatEars = ITEMS.register("black_cat_ears", CatEars::new);
    public static final RegistryObject<CatEars> caramelCatEars = ITEMS.register("caramel_cat_ears", CatEars::new);
    public static final RegistryObject<CatEars> whiteCatEars = ITEMS.register("white_cat_ears", CatEars::new);

    public static final RegistryObject<BunnyEars> blackBunnyEars = ITEMS.register("black_bunny_ears", BunnyEars::new);
    public static final RegistryObject<BunnyEars> caramelBunnyEars = ITEMS.register("caramel_bunny_ears", BunnyEars::new);
    public static final RegistryObject<BunnyEars> whiteBunnyEars = ITEMS.register("white_bunny_ears", BunnyEars::new);

    public static final RegistryObject<FoxEars> blackFoxEars = ITEMS.register("black_fox_ears", FoxEars::new);
    public static final RegistryObject<FoxEars> redFoxEars = ITEMS.register("red_fox_ears", FoxEars::new);
    public static final RegistryObject<FoxEars> whiteFoxEars = ITEMS.register("white_fox_ears", FoxEars::new);

    public static final RegistryObject<FoxEars> brownFoxEars = ITEMS.register("brown_fox_ears", FoxEars::new);

    public static final RegistryObject<Horns> lightGrayHorns = ITEMS.register("light_gray_horns", Horns::new);
    public static final RegistryObject<Horns> grayHorns = ITEMS.register("gray_horns", Horns::new);
    public static final RegistryObject<Horns> whiteHorns = ITEMS.register("white_horns", Horns::new);

    public static final RegistryObject<Horns> redHorns = ITEMS.register("red_horns", Horns::new);
    public static final RegistryObject<Horns> purpleHorns = ITEMS.register("purple_horns", Horns::new);
    public static final RegistryObject<Horns> blackHorns = ITEMS.register("black_horns", Horns::new);

    public static final RegistryObject<Item> blackCatTail = ITEMS.register("black_cat_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "black_cat_tail.png", "cat_tail.geo.json", "cat_tail_animation.json", AnimationsRegister.catTail, catArmorTick));
    public static final RegistryObject<Item> caramelCatTail = ITEMS.register("caramel_cat_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "caramel_cat_tail.png", "cat_tail.geo.json", "cat_tail_animation.json", AnimationsRegister.catTail, catArmorTick));

    public static final RegistryObject<Item> whiteCatTail = ITEMS.register("white_cat_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "white_cat_tail.png", "cat_tail.geo.json", "cat_tail_animation.json", AnimationsRegister.catTail, catArmorTick));

    public static final RegistryObject<Item> blackBunnyTail = ITEMS.register("black_bunny_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "black_bunny_tail.png", "bunny_tail.geo.json", "cat_tail_animation.json", AnimationsRegister.NULL, bunnyArmorTick));
    public static final RegistryObject<Item> caramelBunnyTail = ITEMS.register("caramel_bunny_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "caramel_bunny_tail.png", "bunny_tail.geo.json", "cat_tail_animation.json", AnimationsRegister.NULL, bunnyArmorTick));
    public static final RegistryObject<Item> whiteBunnyTail = ITEMS.register("white_bunny_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "white_bunny_tail.png", "bunny_tail.geo.json", "cat_tail_animation.json", AnimationsRegister.NULL, bunnyArmorTick));

    public static final RegistryObject<Item> blackFoxTail = ITEMS.register("black_fox_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "black_fox_tail.png", "fox_tail.geo.json", "fox_tail_animations.json", AnimationsRegister.foxTail, foxArmorTick));
    public static final RegistryObject<Item> redFoxTail = ITEMS.register("red_fox_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "red_fox_tail.png", "fox_tail.geo.json", "fox_tail_animations.json", AnimationsRegister.foxTail, foxArmorTick));
    public static final RegistryObject<Item> whiteFoxTail = ITEMS.register("white_fox_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "white_fox_tail.png", "fox_tail.geo.json", "fox_tail_animations.json", AnimationsRegister.foxTail, foxArmorTick));

    public static final RegistryObject<Item> brownFoxTail = ITEMS.register("brown_fox_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "brown_fox_tail.png", "fox_tail.geo.json", "fox_tail_animations.json", AnimationsRegister.foxTail, foxArmorTick));

    public static final RegistryObject<Item> blackDevilTail = ITEMS.register("black_devil_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "black_devil_tail.png", "devil_tail.geo.json", "devil_tail_animations.json", AnimationsRegister.devilTail, NULL).setMakesPiglinsNeutral(true));
    public static final RegistryObject<Item> redDevilTail = ITEMS.register("red_devil_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "red_devil_tail.png", "devil_tail.geo.json", "devil_tail_animations.json", AnimationsRegister.devilTail, NULL).setMakesPiglinsNeutral(true));
    public static final RegistryObject<Item> purpleDevilTail = ITEMS.register("purple_devil_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tail, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "purple_devil_tail.png", "devil_tail.geo.json", "devil_tail_animations.json", AnimationsRegister.devilTail, NULL).setMakesPiglinsNeutral(true));
    public static final RegistryObject<Item> whiteThighHighsShoes = ITEMS.register("brown_shoes",
            () -> new GenericGeoArmorItem(ArmorMaterials.maidDress, ArmorItem.Type.BOOTS, new Item.Properties(), "thigh_highs/white_thigh_highs.png", "thigh_highs.geo.json", "cat_tail_animation.json", AnimationsRegister.NULL, NULL).setCanStandOnPowderSnow(true));
    public static final RegistryObject<Item> blackThighHighsShoes = ITEMS.register("dark_brown_shoes",
            () -> new GenericGeoArmorItem(ArmorMaterials.maidDress, ArmorItem.Type.BOOTS, new Item.Properties(), "thigh_highs/black_thigh_highs.png", "thigh_highs.geo.json", "cat_tail_animation.json", AnimationsRegister.NULL, NULL).setCanStandOnPowderSnow(true));

    public static final RegistryObject<Item> coffeeFruit = ITEMS.register("coffee_fruit", () -> new BlockItem(
            BlockRegister.coffeePlant.get(), new Item.Properties().food(new FoodProperties.Builder().fast().nutrition(2).saturationMod(1.125f).build()
    )));
    public static final RegistryObject<Item> driedCoffeeBeans = ITEMS.register("dried_coffee_beans", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> roastedCoffeeBeans = ITEMS.register("roasted_coffee_beans", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> coffeePowder = ITEMS.register("coffee_powder", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> driedCocoaBeans = ITEMS.register("dried_cocoa_beans", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> roastedCocoaBeans = ITEMS.register("roasted_cocoa_beans", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> cocoaPowder = ITEMS.register("cocoa_powder", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> whiteChocolateBar = ITEMS.register("white_chocolate_bar", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(1.5f).build())));
    public static final RegistryObject<Item> darkChocolateBar = ITEMS.register("dark_chocolate_bar", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(1.5f).build())));
    public static final RegistryObject<Item> milkChocolateBar = ITEMS.register("milk_chocolate_bar", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(1.5f).build())));


    public static final RegistryObject<PlaceableFoodItem> expressoCoffee = ITEMS.register("expresso_coffee", () -> new PlaceableFoodItem(BlockRegister.expressoMug.get(), 3, 1.2f, ItemRegister.mug.get()));
    public static final RegistryObject<PlaceableFoodItem> americanCoffee = ITEMS.register("american_coffee", () -> new PlaceableFoodItem(BlockRegister.americanMug.get(), 4, 1.2f, ItemRegister.mug.get()));
    public static final RegistryObject<PlaceableFoodItem> latteCoffee = ITEMS.register("latte_coffee", () -> new PlaceableFoodItem(BlockRegister.latteMug.get(), 5, 1.2f, ItemRegister.mug.get()));
    public static final RegistryObject<PlaceableFoodItem> mochaCoffee = ITEMS.register("mocha_coffee", () -> new PlaceableFoodItem(BlockRegister.mochaMug.get(), 6, 1.2f, ItemRegister.mug.get()));
    public static final RegistryObject<PlaceableFoodItem> macchiatoCoffee = ITEMS.register("macchiato_coffee", () -> new PlaceableFoodItem(BlockRegister.macchiatoMug.get(), 3, 1.2f, ItemRegister.mug.get()));
    public static final RegistryObject<PlaceableFoodItem> doppioCoffee = ITEMS.register("doppio_coffee", () -> new PlaceableFoodItem(BlockRegister.doppioMug.get(), 5, 1.2f, ItemRegister.mug.get()));
    public static final RegistryObject<PlaceableFoodItem> cappuccinoCoffee = ITEMS.register("cappuccino_coffee", () -> new PlaceableFoodItem(BlockRegister.cappuccinoMug.get(), 6, 1.2f, ItemRegister.mug.get()));

    public static final RegistryObject<PlaceableFoodItem> sweetBerryIceCream = ITEMS.register("sweet_berry_ice_cream", () -> new PlaceableFoodItem(BlockRegister.sweetBerryIceCream.get(), 6, 1.2f, ItemRegister.glassCup.get()));
    public static final RegistryObject<PlaceableFoodItem> napolitanoIceCream = ITEMS.register("napolitano_ice_cream", () -> new PlaceableFoodItem(BlockRegister.napolitanoIceCream.get(), 6, 1.2f, ItemRegister.glassCup.get()));
    public static final RegistryObject<PlaceableFoodItem> creamIceCream = ITEMS.register("cream_ice_cream", () -> new PlaceableFoodItem(BlockRegister.creamIceCream.get(), 6, 1.2f, ItemRegister.glassCup.get()));
    public static final RegistryObject<PlaceableFoodItem> chocolateIceCream = ITEMS.register("chocolate_ice_cream", () -> new PlaceableFoodItem(BlockRegister.chocolateIceCream.get(), 6, 1.2f, ItemRegister.glassCup.get()));
    public static final RegistryObject<PlaceableFoodItem> coffeeIceCream = ITEMS.register("coffee_ice_cream", () -> new PlaceableFoodItem(BlockRegister.coffeeIceCream.get(), 6, 1.2f, ItemRegister.glassCup.get()));
    public static final RegistryObject<PlaceableFoodItem> mochaIceCream = ITEMS.register("mocha_ice_cream", () -> new PlaceableFoodItem(BlockRegister.mochaIceCream.get(), 6, 1.2f, ItemRegister.glassCup.get()));
    public static final RegistryObject<PlaceableFoodItem> glowBerryIceCream = ITEMS.register("glow_berry_ice_cream", () -> new PlaceableFoodItem(BlockRegister.glowBerryIceCream.get(), 6, 1.2f, ItemRegister.glassCup.get()));

    public static final RegistryObject<PlaceableFoodItem> sweetBerryMilkshake = ITEMS.register("sweet_berry_milkshake", () -> new PlaceableFoodItem(BlockRegister.sweetBerryMilkshake.get(), 6, 1.2f, ItemRegister.milkshakeCup.get()));
    public static final RegistryObject<PlaceableFoodItem> chocolateMilkshake = ITEMS.register("chocolate_milkshake", () -> new PlaceableFoodItem(BlockRegister.chocolateMilkshake.get(), 6, 1.2f, ItemRegister.milkshakeCup.get()));
    public static final RegistryObject<PlaceableFoodItem> creamMilkshake = ITEMS.register("cream_milkshake", () -> new PlaceableFoodItem(BlockRegister.creamMilkshake.get(), 6, 1.2f, ItemRegister.milkshakeCup.get()));
    public static final RegistryObject<PlaceableFoodItem> napolitanoMilkshake = ITEMS.register("napolitano_milkshake", () -> new PlaceableFoodItem(BlockRegister.napolitanoMilkshake.get(), 6, 1.2f, ItemRegister.milkshakeCup.get()));
    public static final RegistryObject<PlaceableFoodItem> coffeeMilkshake = ITEMS.register("coffee_milkshake", () -> new PlaceableFoodItem(BlockRegister.coffeeMilkshake.get(), 6, 1.2f, ItemRegister.milkshakeCup.get()));
    public static final RegistryObject<PlaceableFoodItem> mochaMilkshake = ITEMS.register("mocha_milkshake", () -> new PlaceableFoodItem(BlockRegister.mochaMilkshake.get(), 6, 1.2f, ItemRegister.milkshakeCup.get()));
    public static final RegistryObject<PlaceableFoodItem> glowBerryMilkshake = ITEMS.register("glow_berry_milkshake", () -> new PlaceableFoodItem(BlockRegister.glowBerryMilkshake.get(), 6, 1.2f, ItemRegister.milkshakeCup.get()));

    public static final RegistryObject<Item> condensedMilk = ITEMS.register("condensed_milk", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> brigadeiroMix = ITEMS.register("brigadeiro_mix", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> creamCheese = ITEMS.register("cream_cheese", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> cakePiece = ITEMS.register("piece_of_cake", () -> new Item(new Item.Properties().food(cake)));
    public static final RegistryObject<Item> cheeseCakePiece = ITEMS.register("piece_of_cheesecake", () -> new Item(new Item.Properties().food(cake)));
    public static final RegistryObject<Item> chocolateCheeseCakePiece = ITEMS.register("piece_of_chocolate_cheesecake", () -> new Item(new Item.Properties().food(cake)));

    public static final RegistryObject<Item> honeyCheeseCakePiece = ITEMS.register("piece_of_honey_cheesecake", () -> new Item(new Item.Properties().food(cake)));

    public static final RegistryObject<Item> cheeseCake = ITEMS.register("cheese_cake", () -> new BlockItem(BlockRegister.cheeseCake.get(), new Item.Properties()));
    public static final RegistryObject<Item> chocolateCheeseCake = ITEMS.register("chocolate_cheese_cake", () -> new BlockItem(BlockRegister.chocolateCheeseCake.get(), new Item.Properties()));
    public static final RegistryObject<Item> honeyCheeseCake = ITEMS.register("honey_cheese_cake", () -> new BlockItem(BlockRegister.honeyCheeseCake.get(), new Item.Properties()));

    public static final RegistryObject<Candy> beijinho = ITEMS.register("beijinho", () -> new Candy(BlockRegister.beijinho.get(), 3, 1.2f));
    public static final RegistryObject<Candy> brigadeiro = ITEMS.register("brigadeiro", () -> new Candy(BlockRegister.brigadeiro.get(), 3, 1.2f));


    public static final RegistryObject<Item> sweetBerryCookie = ITEMS.register("sweet_berry_cookie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationMod(1).nutrition(3).build())));
    public static final RegistryObject<Item> honeyCookie = ITEMS.register("honey_cookie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationMod(1).nutrition(3).build())));
    public static final RegistryObject<Item> chocolateCookie = ITEMS.register("chocolate_cookie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationMod(1).nutrition(3).build())));
    public static final RegistryObject<Item> goldenCookie = ITEMS.register("golden_cookie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationMod(1).nutrition(6).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 20 * 60, 1), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20 * 20, 1), 1f).build())));

    public static final RegistryObject<Item> unbindingCookie = ITEMS.register("cookie_of_unbinding", () -> new UnbindingCookie(new Item.Properties().food(new FoodProperties.Builder().saturationMod(1).nutrition(3).alwaysEat().build())));

    public static final RegistryObject<Item> blackStool = ITEMS.register("black_stool", () -> new BlockItem(BlockRegister.blackStool.get(), new Item.Properties()));
    public static final RegistryObject<Item> blueStool = ITEMS.register("blue_stool", () -> new BlockItem(BlockRegister.blueStool.get(), new Item.Properties()));
    public static final RegistryObject<Item> brownStool = ITEMS.register("brown_stool", () -> new BlockItem(BlockRegister.brownStool.get(), new Item.Properties()));
    public static final RegistryObject<Item> cyanStool = ITEMS.register("cyan_stool", () -> new BlockItem(BlockRegister.cyanStool.get(), new Item.Properties()));
    public static final RegistryObject<Item> grayStool = ITEMS.register("gray_stool", () -> new BlockItem(BlockRegister.grayStool.get(), new Item.Properties()));
    public static final RegistryObject<Item> greenStool = ITEMS.register("green_stool", () -> new BlockItem(BlockRegister.greenStool.get(), new Item.Properties()));
    public static final RegistryObject<Item> lightBlueStool = ITEMS.register("light_blue_stool", () -> new BlockItem(BlockRegister.lightBlueStool.get(), new Item.Properties()));
    public static final RegistryObject<Item> lightGrayStool = ITEMS.register("light_gray_stool", () -> new BlockItem(BlockRegister.lightGrayStool.get(), new Item.Properties()));
    public static final RegistryObject<Item> limeStool = ITEMS.register("lime_stool", () -> new BlockItem(BlockRegister.limeStool.get(), new Item.Properties()));
    public static final RegistryObject<Item> magentaStool = ITEMS.register("magenta_stool", () -> new BlockItem(BlockRegister.magentaStool.get(), new Item.Properties()));
    public static final RegistryObject<Item> orangeStool = ITEMS.register("orange_stool", () -> new BlockItem(BlockRegister.orangeStool.get(), new Item.Properties()));
    public static final RegistryObject<Item> pinkStool = ITEMS.register("pink_stool", () -> new BlockItem(BlockRegister.pinkStool.get(), new Item.Properties()));
    public static final RegistryObject<Item> purpleStool = ITEMS.register("purple_stool", () -> new BlockItem(BlockRegister.purpleStool.get(), new Item.Properties()));
    public static final RegistryObject<Item> redStool = ITEMS.register("red_stool", () -> new BlockItem(BlockRegister.redStool.get(), new Item.Properties()));
    public static final RegistryObject<Item> whiteStool = ITEMS.register("white_stool", () -> new BlockItem(BlockRegister.whiteStool.get(), new Item.Properties()));
    public static final RegistryObject<Item> yellowStool = ITEMS.register("yellow_stool", () -> new BlockItem(BlockRegister.yellowStool.get(), new Item.Properties()));

    public static final RegistryObject<Item> bunnySuitWhiteTail = ITEMS.register("bunny_suit_white_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Properties(),
                    "bunny_suit/bunny_suit_white_tail.png",
                    "bunny_suit.geo.json",
                    "maid_dress_animation.json",
                    AnimationsRegister.NULL,
                    bunnyArmorTick));
    public static final RegistryObject<Item> bunnySuitBlackTail = ITEMS.register("bunny_suit_black_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Properties(),
                    "bunny_suit/bunny_suit_black_tail.png",
                    "bunny_suit.geo.json",
                    "maid_dress_animation.json",
                    AnimationsRegister.NULL,
                    bunnyArmorTick));

    public static final RegistryObject<Item> bunnySuitCaramelTail = ITEMS.register("bunny_suit_caramel_tail",
            () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Properties(),
                    "bunny_suit/bunny_suit_caramel_tail.png",
                    "bunny_suit.geo.json",
                    "maid_dress_animation.json",
                    AnimationsRegister.NULL,
                    bunnyArmorTick));

    public static final RegistryObject<Item> bunnySuitSocks = ITEMS.register("bunny_suit_socks",
            () -> new GenericGeoArmorItem(ArmorMaterials.maidDress,
                    ArmorItem.Type.LEGGINGS,
                    new Item.Properties(),
                    "bunny_suit/bunny_suit_white_tail.png",
                    "bunny_suit.geo.json",
                    "maid_dress_animation.json",
                    AnimationsRegister.NULL,
                    bunnyArmorTick));

    public static HashMap<String, RegistryObject<GenericGeoArmorItem>> dresses = new HashMap<>();

    public static RegistryObject<GenericGeoArmorItem> registerDresses(String color) {

        final var toReturn = ITEMS.register(color + "_maid_dress", () -> new GenericGeoArmorItem(
                ArmorMaterials.maidDress,
                ArmorItem.Type.CHESTPLATE,
                new Item.Properties(),
                "cat_tailed/" + color + "_maid_dress_cat_tail_black.png",
                "maid_dress.geo.json",
                "maid_dress_animation.json",
                AnimationsRegister.NULL,
                maidArmorTick));

        ITEMS.register(color + "_maid_dress_cat_tail_black",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress,
                        ArmorItem.Type.CHESTPLATE,
                        new Item.Properties().craftRemainder(toReturn.get()),
                        "cat_tailed/" + color + "_maid_dress_cat_tail_black.png",
                        "cat_tailed_maid_dress.geo.json",
                        "cat_tail_animation.json",
                        AnimationsRegister.catTail,
                        catArmorTick));
        ITEMS.register(color + "_maid_dress_cat_tail_caramel",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress,
                        ArmorItem.Type.CHESTPLATE,
                        new Item.Properties().craftRemainder(toReturn.get()),
                        "cat_tailed/" + color + "_maid_dress_cat_tail_caramel.png",
                        "cat_tailed_maid_dress.geo.json",
                        "cat_tail_animation.json",
                        AnimationsRegister.catTail,
                        catArmorTick));
        ITEMS.register(color + "_maid_dress_cat_tail_white",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress,
                        ArmorItem.Type.CHESTPLATE,
                        new Item.Properties().craftRemainder(toReturn.get()),
                        "cat_tailed/" + color + "_maid_dress_cat_tail_white.png",
                        "cat_tailed_maid_dress.geo.json",
                        "cat_tail_animation.json",
                        AnimationsRegister.catTail,
                        catArmorTick));
        ITEMS.register(color + "_maid_dress_fox_tail_black",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress,
                        ArmorItem.Type.CHESTPLATE,
                        new Item.Properties().craftRemainder(toReturn.get()),
                        "fox_tailed/" + color + "_maid_dress_fox_tail_black.png",
                        "fox_maid_dress.geo.json",
                        "fox_tail_animations.json",
                        AnimationsRegister.foxTail,
                        foxArmorTick));
        ITEMS.register(color + "_maid_dress_fox_tail_red",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress,
                        ArmorItem.Type.CHESTPLATE,
                        new Item.Properties().craftRemainder(toReturn.get()),
                        "fox_tailed/" + color + "_maid_dress_fox_tail_red.png",
                        "fox_maid_dress.geo.json",
                        "fox_tail_animations.json",
                        AnimationsRegister.foxTail,
                        foxArmorTick));
        ITEMS.register(color + "_maid_dress_fox_tail_white",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress,
                        ArmorItem.Type.CHESTPLATE,
                        new Item.Properties().craftRemainder(toReturn.get()),
                        "fox_tailed/" + color + "_maid_dress_fox_tail_white.png",
                        "fox_maid_dress.geo.json",
                        "fox_tail_animations.json",
                        AnimationsRegister.foxTail,
                        foxArmorTick));
        ITEMS.register(color + "_maid_dress_fox_tail_brown",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress,
                        ArmorItem.Type.CHESTPLATE,
                        new Item.Properties().craftRemainder(toReturn.get()),
                        "fox_tailed/" + color + "_maid_dress_fox_tail_brown.png",
                        "fox_maid_dress.geo.json",
                        "fox_tail_animations.json",
                        AnimationsRegister.foxTail,
                        foxArmorTick));

        ITEMS.register(color + "_maid_dress_bunny_tail_black",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress,
                        ArmorItem.Type.CHESTPLATE,
                        new Item.Properties().craftRemainder(toReturn.get()),
                        "bunny_tailed/" + color + "_maid_dress_bunny_tail_black.png",
                        "bunny_tailed_maid_dress.geo.json",
                        "",
                        AnimationsRegister.NULL,
                        bunnyArmorTick));
        ITEMS.register(color + "_maid_dress_bunny_tail_caramel",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress,
                        ArmorItem.Type.CHESTPLATE,
                        new Item.Properties().craftRemainder(toReturn.get()),
                        "bunny_tailed/" + color + "_maid_dress_bunny_tail_caramel.png",
                        "bunny_tailed_maid_dress.geo.json",
                        "",
                        AnimationsRegister.NULL,
                        bunnyArmorTick));
        ITEMS.register(color + "_maid_dress_bunny_tail_white",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress,
                        ArmorItem.Type.CHESTPLATE,
                        new Item.Properties().craftRemainder(toReturn.get()),
                        "bunny_tailed/" + color + "_maid_dress_bunny_tail_white.png",
                        "bunny_tailed_maid_dress.geo.json",
                        "",
                        AnimationsRegister.NULL,
                        bunnyArmorTick));
        ITEMS.register(color + "_maid_dress_devil_tail_black",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress,
                        ArmorItem.Type.CHESTPLATE,
                        new Item.Properties().craftRemainder(toReturn.get()),
                        "devil_tailed/" + color + "_maid_dress_devil_tail_black.png",
                        "devil_tailed_maid_dress.geo.json",
                        "devil_tail_animations.json",
                        AnimationsRegister.devilTail,
                        NULL).setMakesPiglinsNeutral(true));
        ITEMS.register(color + "_maid_dress_devil_tail_purple",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress,
                        ArmorItem.Type.CHESTPLATE,
                        new Item.Properties().craftRemainder(toReturn.get()),
                        "devil_tailed/" + color + "_maid_dress_devil_tail_purple.png",
                        "devil_tailed_maid_dress.geo.json",
                        "devil_tail_animations.json",
                        AnimationsRegister.devilTail,
                        NULL).setMakesPiglinsNeutral(true));
        ITEMS.register(color + "_maid_dress_devil_tail_red",
                () -> new GenericGeoArmorItem(ArmorMaterials.tailedDress,
                        ArmorItem.Type.CHESTPLATE,
                        new Item.Properties().craftRemainder(toReturn.get()),
                        "devil_tailed/" + color + "_maid_dress_devil_tail_red.png",
                        "devil_tailed_maid_dress.geo.json",
                        "devil_tail_animations.json",
                        AnimationsRegister.devilTail,
                        NULL).setMakesPiglinsNeutral(true));

        return toReturn;
    }

    public static HashMap<String, RegistryObject<Headband>> headbands = new HashMap<>();

    public static RegistryObject<Headband> registerHeadbands(String color) {
        var toReturn = ITEMS.register(color + "_headband", () -> new Headband(Items.AIR));
        ITEMS.register(color + "_headband_cat_ears_black", () -> new CatHeadband(toReturn.get()));
        ITEMS.register(color + "_headband_cat_ears_white", () -> new CatHeadband(toReturn.get()));
        ITEMS.register(color + "_headband_cat_ears_caramel", () -> new CatHeadband(toReturn.get()));
        ITEMS.register(color + "_headband_fox_ears_black", () -> new Headband(toReturn.get()));
        ITEMS.register(color + "_headband_fox_ears_white", () -> new Headband(toReturn.get()));
        ITEMS.register(color + "_headband_fox_ears_red", () -> new Headband(toReturn.get()));
        ITEMS.register(color + "_headband_fox_ears_brown", () -> new Headband(toReturn.get()));
        ITEMS.register(color + "_headband_bunny_ears_black", () -> new BunnyHeadband(toReturn.get()));
        ITEMS.register(color + "_headband_bunny_ears_white", () -> new BunnyHeadband(toReturn.get()));
        ITEMS.register(color + "_headband_bunny_ears_caramel", () -> new BunnyHeadband(toReturn.get()));
        ITEMS.register(color + "_headband_horns_light_gray", () -> new HornHeadband(toReturn.get()));
        ITEMS.register(color + "_headband_horns_gray", () -> new HornHeadband(toReturn.get()));
        ITEMS.register(color + "_headband_horns_white", () -> new HornHeadband(toReturn.get()));
        ITEMS.register(color + "_headband_horns_red", () -> new HornHeadband(toReturn.get()));
        ITEMS.register(color + "_headband_horns_purple", () -> new HornHeadband(toReturn.get()));
        ITEMS.register(color + "_headband_horns_black", () -> new HornHeadband(toReturn.get()));

        return toReturn;
    }

    public static HashMap<String, RegistryObject<GenericGeoArmorItem>> thighHighs = new HashMap<>();

    public static RegistryObject<GenericGeoArmorItem> registerThighHighs(String color) {
        return ITEMS.register(color + "_thigh_highs", () -> new GenericGeoArmorItem(
                ArmorMaterials.maidDress, ArmorItem.Type.LEGGINGS, new Item.Properties(),
                "thigh_highs/" + color + "_thigh_highs.png",
                "thigh_highs.geo.json",
                "", AnimationsRegister.NULL, NULL)
        );
    }

    public static void preGen() {
        ArrayList<String> nameMap = new ArrayList<>();

        nameMap.add("blue");
        nameMap.add("brown");
        nameMap.add("cyan");
        nameMap.add("gray");
        nameMap.add("green");
        nameMap.add("light_blue");
        nameMap.add("light_gray");
        nameMap.add("lime");
        nameMap.add("magenta");
        nameMap.add("orange");
        nameMap.add("pink");
        nameMap.add("red");
        nameMap.add("white");
        nameMap.add("black");
        nameMap.add("yellow");
        nameMap.add("purple");

        for (int i = 0; i < nameMap.size(); i++) {
            thighHighs.put(nameMap.get(i), registerThighHighs(nameMap.get(i)));
        }
        for (int i = 0; i < nameMap.size(); i++) {
            dresses.put(nameMap.get(i), registerDresses(nameMap.get(i)));
        }
        for (int i = 0; i < nameMap.size(); i++) {
            headbands.put(nameMap.get(i), registerHeadbands(nameMap.get(i)));
        }
    }

    public static final RegistryObject<CreativeModeTab> COSMETICS_TAB = TABS.register("cosmetics", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.kawaiidishes.cosmetics"))
            .icon(() -> new ItemStack(dresses.get("black").get()))
            .displayItems((enabledFeatures, entries) -> {
                ITEMS.getEntries().forEach(x -> {
                    var key = x.get().toString();
                    if (key.contains("maid")) {
                        entries.accept(x.get());
                    } else if (key.contains("ears") || key.contains("horns")) {
                        entries.accept(x.get());
                    } else if (key.contains("tail")) {
                        entries.accept(x.get());
                    } else if (key.contains("headband")) {
                        entries.accept(x.get());
                    } else if (key.contains("thigh")) {
                        entries.accept(x.get());
                    } else if (key.contains("shoes")) {
                        entries.accept(x.get());
                    } else if (key.contains("bunny_suit")) {
                        entries.accept(x.get());
                    } else if (key.contains("socks")) {
                        entries.accept(x.get());
                    }
                });
            })
            .build());

    public static final RegistryObject<CreativeModeTab> BLOCKS_TAB = TABS.register("blocks", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.kawaiidishes.blocks"))
            .icon(() -> new ItemStack(coffeeMachine.get()))
            .displayItems((enabledFeatures, entries) -> {
                ITEMS.getEntries().forEach(x -> {
                    entries.accept(ItemRegister.mug.get());
                    entries.accept(ItemRegister.glassCup.get());
                    entries.accept(ItemRegister.milkshakeCup.get());
                    entries.accept(ItemRegister.coffeeMachine.get());
                    entries.accept(ItemRegister.coffeePress.get());
                    entries.accept(ItemRegister.blender.get());
                    entries.accept(ItemRegister.iceCreamMachine.get());
                });
            })
            .build());
    
    public static final RegistryObject<CreativeModeTab> DECORATION_TAB = TABS.register("decoration", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.kawaiidishes.decoration"))
            .icon(() -> new ItemStack(coffeeMachine.get()))
            .displayItems((enabledFeatures, entries) -> {
                ITEMS.getEntries().forEach(x -> {
                    String item = x.get().toString();
                    if(item.contains("stool")){
                        entries.accept(x.get());
                    }
                });
            })
            .build());

    public static final RegistryObject<CreativeModeTab> FOOD_TAB = TABS.register("foods", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.kawaiidishes.foods"))
            .icon(() -> new ItemStack(coffeeMachine.get()))
            .displayItems((enabledFeatures, entries) -> {
                entries.accept(ItemRegister.brigadeiroMix.get());
                entries.accept(ItemRegister.condensedMilk.get());
                entries.accept(ItemRegister.creamCheese.get());

                entries.accept(ItemRegister.coffeePowder.get());
                entries.accept(ItemRegister.driedCoffeeBeans.get());
                entries.accept(ItemRegister.roastedCoffeeBeans.get());

                entries.accept(ItemRegister.cocoaPowder.get());
                entries.accept(ItemRegister.driedCocoaBeans.get());
                entries.accept(ItemRegister.roastedCocoaBeans.get());
                ItemRegister.ITEMS.getEntries().forEach(x -> {
                    String item = x.get().toString();
                    if(x.get().isEdible()){
                        entries.accept(x.get());
                    }else if(item.contains("cake")){
                        entries.accept(x.get());
                    }
                });
            })
            .build());
    public static void register(IEventBus bus) {
        preGen();
        TABS.register(bus);
        ITEMS.register(bus);
    }
}
