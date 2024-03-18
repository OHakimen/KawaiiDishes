package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.item.SeatItem;
import com.hakimen.kawaiidishes.item.armor.*;
import com.hakimen.kawaiidishes.item.food.CoffeeItem;
import com.hakimen.kawaiidishes.item.food.OnConsumeDropItem;
import com.hakimen.kawaiidishes.utils.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemRegister {

    static final FoodProperties coffeeProperties = new FoodProperties.Builder()
            .fast()
            .nutrition(4)
            .saturationMod(1.3f)
            .build();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, KawaiiDishes.MODID);
    public static final DeferredHolder<Item, ThighHighsArmorItem> THIGH_HIGHS = ITEMS.register("thigh_highs", () -> new ThighHighsArmorItem(ArmorMaterials.IRON, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredHolder<Item, MaidDressArmorItem> MAID_DRESS = ITEMS.register("maid_dress", () -> new MaidDressArmorItem(ArmorMaterials.IRON, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredHolder<Item, HeadBandArmorItem> HEAD_BAND = ITEMS.register("head_band", () -> new HeadBandArmorItem(ArmorMaterials.IRON, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredHolder<Item, ShoesArmorItem> SHOES = ITEMS.register("shoes", () -> new ShoesArmorItem(ArmorMaterials.IRON, ArmorItem.Type.BOOTS, new Item.Properties()));
    //Fox
    public static final DeferredHolder<Item, TailArmorItem> FOX_TAIL = ITEMS.register("fox_tail", () -> new TailArmorItem(ArmorMaterials.IRON, ArmorItem.Type.CHESTPLATE, new Item.Properties(), AnimalType.FOX, true));
    public static final DeferredHolder<Item, MaidDressesWithTailArmorItem> MAID_DRESS_FOX_TAIL = ITEMS.register("maid_dress_fox_tail", () -> new MaidDressesWithTailArmorItem(ArmorMaterials.IRON, ArmorItem.Type.CHESTPLATE, new Item.Properties(), AnimalType.FOX));
    public static final DeferredHolder<Item, EarsArmorItem> FOX_EARS = ITEMS.register("fox_ears", () -> new EarsArmorItem(ArmorMaterials.IRON, ArmorItem.Type.HELMET, new Item.Properties(), AnimalType.FOX, true));
    public static final DeferredHolder<Item, HeadBandWithEarsArmorItem> HEAD_BAND_FOX_EARS = ITEMS.register("head_band_fox_ears", () -> new HeadBandWithEarsArmorItem(ArmorMaterials.IRON, ArmorItem.Type.HELMET, new Item.Properties(), AnimalType.FOX));

    //Bunny
    public static final DeferredHolder<Item, TailArmorItem> BUNNY_TAIL = ITEMS.register("bunny_tail", () -> new TailArmorItem(ArmorMaterials.IRON, ArmorItem.Type.CHESTPLATE, new Item.Properties(), AnimalType.BUNNY, true));
    public static final DeferredHolder<Item, MaidDressesWithTailArmorItem> MAID_DRESS_BUNNY_TAIL = ITEMS.register("maid_dress_bunny_tail", () -> new MaidDressesWithTailArmorItem(ArmorMaterials.IRON, ArmorItem.Type.CHESTPLATE, new Item.Properties(), AnimalType.BUNNY));
    public static final DeferredHolder<Item, EarsArmorItem> BUNNY_EARS = ITEMS.register("bunny_ears", () -> new EarsArmorItem(ArmorMaterials.IRON, ArmorItem.Type.HELMET, new Item.Properties(), AnimalType.BUNNY, true));
    public static final DeferredHolder<Item, HeadBandWithEarsArmorItem> HEAD_BAND_BUNNY_EARS = ITEMS.register("head_band_bunny_ears", () -> new HeadBandWithEarsArmorItem(ArmorMaterials.IRON, ArmorItem.Type.HELMET, new Item.Properties(), AnimalType.BUNNY));

    //Cat
    public static final DeferredHolder<Item, TailArmorItem> CAT_TAIL = ITEMS.register("cat_tail", () -> new TailArmorItem(ArmorMaterials.IRON, ArmorItem.Type.CHESTPLATE, new Item.Properties(), AnimalType.CAT, true));
    public static final DeferredHolder<Item, MaidDressesWithTailArmorItem> MAID_DRESS_CAT_TAIL = ITEMS.register("maid_dress_cat_tail", () -> new MaidDressesWithTailArmorItem(ArmorMaterials.IRON, ArmorItem.Type.CHESTPLATE, new Item.Properties(), AnimalType.CAT));
    public static final DeferredHolder<Item, EarsArmorItem> CAT_EARS = ITEMS.register("cat_ears", () -> new EarsArmorItem(ArmorMaterials.IRON, ArmorItem.Type.HELMET, new Item.Properties(), AnimalType.CAT, true));
    public static final DeferredHolder<Item, HeadBandWithEarsArmorItem> HEAD_BAND_CAT_EARS = ITEMS.register("head_band_cat_ears", () -> new HeadBandWithEarsArmorItem(ArmorMaterials.IRON, ArmorItem.Type.HELMET, new Item.Properties(), AnimalType.CAT));

    //Overlay Items
    public static final DeferredHolder<Item, Item> APRON = ITEMS.register("apron", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final DeferredHolder<Item, Item> BOW = ITEMS.register("bow", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final DeferredHolder<Item, Item> LEG_CLIP = ITEMS.register("leg_clip", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final DeferredHolder<Item, Item> DOUBLE_BANDS = ITEMS.register("double_bands", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final DeferredHolder<Item, Item> FULL_BANDS = ITEMS.register("full_bands", () -> new Item(new Item.Properties().stacksTo(16)));

    //Ingredients
    public static final DeferredHolder<Item, Item> STEAMED_MILK_BUCKET = ITEMS.register("steamed_milk_bucket", () -> new Item(new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final DeferredHolder<Item, Item> MILK_FOAM_BUCKET = ITEMS.register("milk_foam_bucket", () -> new Item(new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final DeferredHolder<Item, Item> COFFEE_BEANS = ITEMS.register("coffee_beans", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> ROAST_COFFEE_BEANS = ITEMS.register("roast_coffee_beans", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> GROUND_COFFEE = ITEMS.register("ground_coffee", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> COCOA_POWDER = ITEMS.register("cocoa_powder", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> CREAM_CHEESE_BALL = ITEMS.register("cream_cheese_ball", () -> new Item(new Item.Properties()));
    // Food
    public static final DeferredHolder<Item, Item> COFFEE_BERRIES = ITEMS.register("coffee_berries", () -> new BlockItem(BlockRegister.COFFEE_BUSH.get(), new Item.Properties().food(coffeeProperties)));
    public static final DeferredHolder<Item, Item> CHERRY = ITEMS.register("cherry", () -> new Item(new Item.Properties().food(coffeeProperties)));
    static final FoodProperties cookieProperties = new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(1f)
            .build();
    //Cookies
    public static final DeferredHolder<Item, Item> CHOCOLATE_COOKIE = ITEMS.register("chocolate_cookie", () -> new Item(new Item.Properties().food(cookieProperties)));
    public static final DeferredHolder<Item, Item> SWEET_BERRY_COOKIE = ITEMS.register("sweet_berry_cookie", () -> new Item(new Item.Properties().food(cookieProperties)));

    public static final DeferredHolder<Item, Item> GLOW_BERRY_COOKIE = ITEMS.register("glow_berry_cookie", () ->
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(1f)
                            .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 30 * 20), 1f)
                            .build()
            )
            )
    );
    public static final DeferredHolder<Item, Item> GOLDEN_COOKIE = ITEMS.register("golden_cookie", () ->
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(1f)
                            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 10 * 20), 1f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 30 * 20, 1), 1f)
                            .build()
            )
            )
    );
    public static final DeferredHolder<Item, Item> COOKIE_OF_UNBINDING = ITEMS.register("cookie_of_unbinding", () ->
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(1f)
                            .effect(() -> new MobEffectInstance(EffectRegister.BLESSING_OF_UNBINDING.get(), (2 * 60 + 30) * 20), 1f)
                            .build()
            )
            )
    );

    //Coffees
    public static final DeferredHolder<Item, BlockItem> MUG = ITEMS.register("mug", () -> new BlockItem(BlockRegister.MUG.get(), new Item.Properties()));
    public static final DeferredHolder<Item, CoffeeItem> ESPRESSO_COFFEE = ITEMS.register("espresso_coffee", () ->
            new CoffeeItem(
                    BlockRegister.ESPRESSO_COFFEE.get(),
                    6,
                    1.5f,
                    new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 30 * 20)
            )
    );
    public static final DeferredHolder<Item, CoffeeItem> DOPPIO_COFFEE = ITEMS.register("doppio_coffee", () ->
            new CoffeeItem(
                    BlockRegister.DOPPIO_COFFEE.get(),
                    6,
                    1.5f,
                    new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 30 * 20, 1)
            )
    );
    public static final DeferredHolder<Item, CoffeeItem> MACCHIATO_COFFEE = ITEMS.register("macchiato_coffee", () ->
            new CoffeeItem(
                    BlockRegister.MACCHIATO_COFFEE.get(),
                    6,
                    1.5f,
                    new MobEffectInstance(MobEffects.ABSORPTION, 30 * 20, 1)
            )
    );
    public static final DeferredHolder<Item, CoffeeItem> DARK_COFFEE = ITEMS.register("dark_coffee", () ->
            new CoffeeItem(
                    BlockRegister.DARK_COFFEE.get(),
                    6,
                    1.5f,
                    new MobEffectInstance(MobEffects.NIGHT_VISION, 120 * 20)
            )
    );
    public static final DeferredHolder<Item, CoffeeItem> LATTE_COFFEE = ITEMS.register("latte_coffee", () ->
            new CoffeeItem(
                    BlockRegister.LATTE_COFFEE.get(),
                    6,
                    1.5f,
                    new MobEffectInstance(MobEffects.DIG_SPEED, 30 * 20)
            )
    );
    public static final DeferredHolder<Item, CoffeeItem> CAPUCCINO_COFFEE = ITEMS.register("capuccino_coffee", () ->
            new CoffeeItem(
                    BlockRegister.CAPUCCINO_COFFEE.get(),
                    6,
                    1.5f,
                    new MobEffectInstance(MobEffects.REGENERATION, 30 * 20)
            )
    );
    public static final DeferredHolder<Item, CoffeeItem> MOCHA_COFFEE = ITEMS.register("mocha_coffee", () ->
            new CoffeeItem(
                    BlockRegister.MOCHA_COFFEE.get(),
                    6,
                    1.5f,
                    new MobEffectInstance(MobEffects.ABSORPTION, 30 * 20, 1),
                    new MobEffectInstance(MobEffects.HEALTH_BOOST, 60 * 20)
            )
    );
    public static final DeferredHolder<Item, CoffeeItem> HOT_COCOA = ITEMS.register("hot_cocoa", () ->
            new CoffeeItem(
                    BlockRegister.HOT_COCOA.get(),
                    6,
                    1.5f,
                    new MobEffectInstance(MobEffects.REGENERATION, 30 * 20),
                    new MobEffectInstance(MobEffects.HEALTH_BOOST, 60 * 20)
            )
    );

    // Cake Slices
    public static final FoodProperties CAKE_SLICE_PROPS = new FoodProperties.Builder()
            .fast()
            .nutrition(3)
            .saturationMod(1f)
            .build();
    public static final DeferredHolder<Item, Item> CAKE_SLICE = ITEMS.register("cake_slice", () ->
            new Item(new Item.Properties().food(CAKE_SLICE_PROPS))
    );

    public static final DeferredHolder<Item, Item> CHEESE_CAKE_SLICE = ITEMS.register("cheese_cake_slice", () ->
            new Item(new Item.Properties().food(CAKE_SLICE_PROPS))
    );

    public static final DeferredHolder<Item, Item> CHOCOLATE_CHEESE_CAKE_SLICE = ITEMS.register("chocolate_cheese_cake_slice", () ->
            new Item(new Item.Properties().food(CAKE_SLICE_PROPS))
    );

    public static final DeferredHolder<Item, Item> HONEY_CHEESE_CAKE_SLICE = ITEMS.register("honey_cheese_cake_slice", () ->
            new Item(new Item.Properties().food(CAKE_SLICE_PROPS))
    );

    public static final DeferredHolder<Item, Item> APPLE_PIE_SLICE = ITEMS.register("apple_pie_slice", () ->
            new Item(new Item.Properties().food(CAKE_SLICE_PROPS))
    );
    public static final DeferredHolder<Item, Item> SWEET_BERRY_PIE_SLICE = ITEMS.register("sweet_berry_pie_slice", () ->
            new Item(new Item.Properties().food(CAKE_SLICE_PROPS))
    );
    public static final DeferredHolder<Item, Item> GLOW_BERRY_PIE_SLICE = ITEMS.register("glow_berry_pie_slice", () ->
            new Item(new Item.Properties().food(CAKE_SLICE_PROPS))
    );
    public static final DeferredHolder<Item, Item> CHERRY_PIE_SLICE = ITEMS.register("cherry_pie_slice", () ->
            new Item(new Item.Properties().food(CAKE_SLICE_PROPS))
    );

    //Waffles
    public static final DeferredHolder<Item, Item> WAFFLE = ITEMS.register("waffle", () ->
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(1.25f)
                            .build()
            ))
    );

    public static final DeferredHolder<Item, Item> CHOCOLATE_WAFFLE = ITEMS.register("chocolate_waffle", () ->
            new Item(new Item.Properties().food(
                    new FoodProperties.Builder()
                            .nutrition(6)
                            .saturationMod(1.25f)
                            .build()
            ))
    );

    //Ice Creams
    public static final FoodProperties ICE_CREAM_PROPS = new FoodProperties.Builder()
            .nutrition(6)
            .saturationMod(1.5f)
            .build();

    public static final DeferredHolder<Item, OnConsumeDropItem> CREAM_ICE_CREAM = ITEMS.register("cream_ice_cream", () ->
            new OnConsumeDropItem(new Item.Properties().food(ICE_CREAM_PROPS), Items.BOWL.getDefaultInstance())
    );
    public static final DeferredHolder<Item, OnConsumeDropItem> COFFEE_ICE_CREAM = ITEMS.register("coffee_ice_cream", () ->
            new OnConsumeDropItem(new Item.Properties().food(ICE_CREAM_PROPS), Items.BOWL.getDefaultInstance())
    );
    public static final DeferredHolder<Item, OnConsumeDropItem> CHOCOLATE_ICE_CREAM = ITEMS.register("chocolate_ice_cream", () ->
            new OnConsumeDropItem(new Item.Properties().food(ICE_CREAM_PROPS), Items.BOWL.getDefaultInstance())
    );
    public static final DeferredHolder<Item, OnConsumeDropItem> SWEET_BERRY_ICE_CREAM = ITEMS.register("sweet_berry_ice_cream", () ->
            new OnConsumeDropItem(new Item.Properties().food(ICE_CREAM_PROPS), Items.BOWL.getDefaultInstance())
    );
    public static final DeferredHolder<Item, OnConsumeDropItem> GLOW_BERRY_ICE_CREAM = ITEMS.register("glow_berry_ice_cream", () ->
            new OnConsumeDropItem(new Item.Properties().food(
                    new FoodProperties.Builder()
                            .nutrition(6)
                            .saturationMod(1.5f)
                            .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 30 * 20), 1f)
                            .build()
            ), Items.BOWL.getDefaultInstance())
    );

    public static final DeferredHolder<Item, OnConsumeDropItem> NEAPOLITAN_ICE_CREAM = ITEMS.register("neapolitan_ice_cream", () ->
            new OnConsumeDropItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(10)
                    .saturationMod(1.5f)
                    .build()
            ), Items.BOWL.getDefaultInstance())
    );

    public static final DeferredHolder<Item, OnConsumeDropItem> MOCHA_ICE_CREAM = ITEMS.register("mocha_ice_cream", () ->
            new OnConsumeDropItem(new Item.Properties().food(ICE_CREAM_PROPS), Items.BOWL.getDefaultInstance())
    );

    public static final DeferredHolder<Item, OnConsumeDropItem> CHERRY_ICE_CREAM = ITEMS.register("cherry_ice_cream", () ->
            new OnConsumeDropItem(new Item.Properties().food(ICE_CREAM_PROPS), Items.BOWL.getDefaultInstance())
    );

    //Cakes
    public static final DeferredHolder<Item, BlockItem> CHEESE_CAKE = ITEMS.register("cheese_cake", () -> new BlockItem(BlockRegister.CHEESE_CAKE.get(), new Item.Properties()));
    public static final DeferredHolder<Item, BlockItem> CHOCOLATE_CHEESE_CAKE = ITEMS.register("chocolate_cheese_cake", () -> new BlockItem(BlockRegister.CHOCOLATE_CHEESE_CAKE.get(), new Item.Properties()));
    public static final DeferredHolder<Item, BlockItem> HONEY_CHEESE_CAKE = ITEMS.register("honey_cheese_cake", () -> new BlockItem(BlockRegister.HONEY_CHEESE_CAKE.get(), new Item.Properties()));

    //Pie
    public static final DeferredHolder<Item, BlockItem> APPLE_PIE = ITEMS.register("apple_pie", () -> new BlockItem(BlockRegister.APPLE_PIE.get(), new Item.Properties()));
    public static final DeferredHolder<Item, BlockItem> SWEET_BERRY_PIE = ITEMS.register("sweet_berry_pie", () -> new BlockItem(BlockRegister.SWEET_BERRY_PIE.get(), new Item.Properties()));
    public static final DeferredHolder<Item, BlockItem> GLOW_BERRY_PIE = ITEMS.register("glow_berry_pie", () -> new BlockItem(BlockRegister.GLOW_BERRY_PIE.get(), new Item.Properties()));
    public static final DeferredHolder<Item, BlockItem> CHERRY_PIE = ITEMS.register("cherry_pie", () -> new BlockItem(BlockRegister.CHERRY_PIE.get(), new Item.Properties()));

    //Machinery
    public static final DeferredHolder<Item, BlockItem> COFFEE_MACHINE = ITEMS.register("coffee_machine", () -> new BlockItem(BlockRegister.COFFEE_MACHINE.get(), new Item.Properties()));
    public static final DeferredHolder<Item, BlockItem> BLENDER = ITEMS.register("blender", () -> new BlockItem(BlockRegister.BLENDER.get(), new Item.Properties()));
    public static final DeferredHolder<Item, BlockItem> ICE_CREAM_MAKER = ITEMS.register("ice_cream_maker", () -> new BlockItem(BlockRegister.ICE_CREAM_MAKER.get(), new Item.Properties()));

    //Decor
    public static final DeferredHolder<Item, SeatItem> SEAT = ITEMS.register("seat", () -> new SeatItem(BlockRegister.SEAT.get(), new Item.Properties()));
    public static final DeferredHolder<Item, BlockItem> KITCHEN_TILES = ITEMS.register("kitchen_tiles", () -> new BlockItem(BlockRegister.KITCHEN_TILES.get(), new Item.Properties()));
    public static final DeferredHolder<Item, BlockItem> DISPLAY_CASE = ITEMS.register("display_case", () -> new BlockItem(BlockRegister.DISPLAY_CASE.get(), new Item.Properties()));
    public static final DeferredHolder<Item, BlockItem> INCENSE_GLASS = ITEMS.register("incense_glass", () -> new BlockItem(BlockRegister.INCENSE_GLASS.get(), new Item.Properties()));


    public static void setupItems() {
        TailUtils.makeTailWithDefaultAnims(AnimalType.FOX);
        TailUtils.makeTailWithDefaultAnims(AnimalType.BUNNY);
        TailUtils.makeTailWithDefaultAnims(AnimalType.CAT);

        EarUtils.makeEarsWithDefaultAnims(AnimalType.FOX);
        EarUtils.makeEarsWithDefaultAnims(AnimalType.BUNNY);
        EarUtils.makeEarsWithDefaultAnims(AnimalType.CAT);

        MaidDressesWithTailUtils.makeDressWithTailWithDefaultAnims(AnimalType.FOX, MAID_DRESS_FOX_TAIL);
        MaidDressesWithTailUtils.makeDressWithTailWithDefaultAnims(AnimalType.BUNNY, MAID_DRESS_BUNNY_TAIL);
        MaidDressesWithTailUtils.makeDressWithTailWithDefaultAnims(AnimalType.CAT, MAID_DRESS_CAT_TAIL);

        HeadBandsWithEarsUtils.makeHeadbandWithEarsDefaultAnims(AnimalType.FOX, HEAD_BAND_FOX_EARS);
        HeadBandsWithEarsUtils.makeHeadbandWithEarsDefaultAnims(AnimalType.BUNNY, HEAD_BAND_BUNNY_EARS);
        HeadBandsWithEarsUtils.makeHeadbandWithEarsDefaultAnims(AnimalType.CAT, HEAD_BAND_CAT_EARS);
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
        setupItems();
    }
}
