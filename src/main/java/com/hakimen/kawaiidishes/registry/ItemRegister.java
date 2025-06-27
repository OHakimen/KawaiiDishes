package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.custom.Recorder;
import com.hakimen.kawaiidishes.item.SeatItem;
import com.hakimen.kawaiidishes.item.DecorationItem;
import com.hakimen.kawaiidishes.item.armor.*;
import com.hakimen.kawaiidishes.item.food.CoffeeItem;
import com.hakimen.kawaiidishes.item.food.OnConsumeDropItem;
import com.hakimen.kawaiidishes.utils.*;
import java.util.function.Supplier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;

public class ItemRegister {
    // Cake Slices
    public static final FoodComponent CAKE_SLICE_PROPS = new FoodComponent.Builder()
            .snack()
            .hunger(3)
            .saturationModifier(1f)
            .build();
    //Ice Creams
    public static final FoodComponent ICE_CREAM_PROPS = new FoodComponent.Builder()
            .hunger(6)
            .saturationModifier(1.5f)
            .build();
    static final FoodComponent berriesProperties = new FoodComponent.Builder()
            .snack()
            .hunger(4)
            .saturationModifier(1.3f)
            .build();
    static final FoodComponent cookieProperties = new FoodComponent.Builder()
            .hunger(2)
            .saturationModifier(1f)
            .build();
    public static Recorder<Item> ITEM = new Recorder<>(Registries.ITEM, KawaiiDishes.MODID);
    public static final Supplier<BlockItem> MUG = ITEM.register("mug", () -> new BlockItem(BlockRegister.MUG.get(), new Item.Settings()));
    //Food
    public static final Supplier<Item> COFFEE_BERRIES = ITEM.register("coffee_berries", () -> new BlockItem(BlockRegister.COFFEE_BUSH.get(), new Item.Settings().food(berriesProperties)));
    public static final Supplier<Item> CHERRY = ITEM.register("cherry", () -> new Item(new Item.Settings().food(berriesProperties)));
    //Clothing
    public static final Supplier<ThighHighsArmorItem> THIGH_HIGHS = ITEM.register("thigh_highs", () -> new ThighHighsArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.LEGGINGS, new Item.Settings()));
    public static final Supplier<MaidDressArmorItem> MAID_DRESS = ITEM.register("maid_dress", () -> new MaidDressArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE, new Item.Settings()));
    public static final Supplier<HeadBandArmorItem> HEAD_BAND = ITEM.register("head_band", () -> new HeadBandArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.HELMET, new Item.Settings()));
    public static final Supplier<ShoesArmorItem> SHOES = ITEM.register("shoes", () -> new ShoesArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.BOOTS, new Item.Settings()));
    //Fox
    public static final Supplier<TailArmorItem> FOX_TAIL = ITEM.register("fox_tail", () -> new TailArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE, new Item.Settings(), AnimalType.FOX, true));
    public static final Supplier<MaidDressesWithTailArmorItem> MAID_DRESS_FOX_TAIL = ITEM.register("maid_dress_fox_tail", () -> new MaidDressesWithTailArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE, new Item.Settings(), AnimalType.FOX));
    public static final Supplier<EarsArmorItem> FOX_EARS = ITEM.register("fox_ears", () -> new EarsArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.HELMET, new Item.Settings(), AnimalType.FOX, true));
    public static final Supplier<HeadBandWithEarsArmorItem> HEAD_BAND_FOX_EARS = ITEM.register("head_band_fox_ears", () -> new HeadBandWithEarsArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.HELMET, new Item.Settings(), AnimalType.FOX));
    //Bunny
    public static final Supplier<TailArmorItem> BUNNY_TAIL = ITEM.register("bunny_tail", () -> new TailArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE, new Item.Settings(), AnimalType.BUNNY, true));
    public static final Supplier<MaidDressesWithTailArmorItem> MAID_DRESS_BUNNY_TAIL = ITEM.register("maid_dress_bunny_tail", () -> new MaidDressesWithTailArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE, new Item.Settings(), AnimalType.BUNNY));
    public static final Supplier<EarsArmorItem> BUNNY_EARS = ITEM.register("bunny_ears", () -> new EarsArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.HELMET, new Item.Settings(), AnimalType.BUNNY, true));
    public static final Supplier<HeadBandWithEarsArmorItem> HEAD_BAND_BUNNY_EARS = ITEM.register("head_band_bunny_ears", () -> new HeadBandWithEarsArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.HELMET, new Item.Settings(), AnimalType.BUNNY));
    //Cat
    public static final Supplier<TailArmorItem> CAT_TAIL = ITEM.register("cat_tail", () -> new TailArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE, new Item.Settings(), AnimalType.CAT, true));
    public static final Supplier<MaidDressesWithTailArmorItem> MAID_DRESS_CAT_TAIL = ITEM.register("maid_dress_cat_tail", () -> new MaidDressesWithTailArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE, new Item.Settings(), AnimalType.CAT));
    public static final Supplier<EarsArmorItem> CAT_EARS = ITEM.register("cat_ears", () -> new EarsArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.HELMET, new Item.Settings(), AnimalType.CAT, true));
    public static final Supplier<HeadBandWithEarsArmorItem> HEAD_BAND_CAT_EARS = ITEM.register("head_band_cat_ears", () -> new HeadBandWithEarsArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.HELMET, new Item.Settings(), AnimalType.CAT));
    //Overlays
    public static final Supplier<Item> APRON = ITEM.register("apron", () -> new Item(new Item.Settings().maxCount(16)));
    public static final Supplier<DecorationItem> DOUBLE_BANDS = ITEM.register("double_bands", () -> new DecorationItem(new Item.Settings().maxCount(16),
            ThighHighsDecorationRegister.DECORATIONS.getRegistry().getId(
                    ThighHighsDecorationRegister.DOUBLE_BANDS.get()
            )
    ));
    public static final Supplier<DecorationItem> BOW = ITEM.register("bow", () -> new DecorationItem(new Item.Settings().maxCount(16),
            ThighHighsDecorationRegister.DECORATIONS.getRegistry().getId(
                    ThighHighsDecorationRegister.BOW.get()
            )
    ));
    public static final Supplier<DecorationItem> LEG_CLIP = ITEM.register("leg_clip", () -> new DecorationItem(new Item.Settings().maxCount(16),
            ThighHighsDecorationRegister.DECORATIONS.getRegistry().getId(
                    ThighHighsDecorationRegister.LEG_CLIP.get()
            )
    ));
    public static final Supplier<DecorationItem> FULL_BANDS = ITEM.register("full_bands", () -> new DecorationItem(new Item.Settings().maxCount(16),
            ThighHighsDecorationRegister.DECORATIONS.getRegistry().getId(
                    ThighHighsDecorationRegister.FULL_BANDS.get()
            )
    ));

    //Ingredients
    public static final Supplier<Item> STEAMED_MILK_BUCKET = ITEM.register("steamed_milk_bucket", () -> new Item(new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Supplier<Item> MILK_FOAM_BUCKET = ITEM.register("milk_foam_bucket", () -> new Item(new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Supplier<Item> COFFEE_BEANS = ITEM.register("coffee_beans", () -> new Item(new Item.Settings()));
    public static final Supplier<Item> ROAST_COFFEE_BEANS = ITEM.register("roast_coffee_beans", () -> new Item(new Item.Settings()));
    public static final Supplier<Item> GROUND_COFFEE = ITEM.register("ground_coffee", () -> new Item(new Item.Settings()));
    public static final Supplier<Item> COCOA_POWDER = ITEM.register("cocoa_powder", () -> new Item(new Item.Settings()));
    public static final Supplier<Item> CREAM_CHEESE_BALL = ITEM.register("cream_cheese_ball", () -> new Item(new Item.Settings()));
    //Cookies
    public static final Supplier<Item> CHOCOLATE_COOKIE = ITEM.register("chocolate_cookie", () -> new Item(new Item.Settings().food(cookieProperties)));
    public static final Supplier<Item> SWEET_BERRY_COOKIE = ITEM.register("sweet_berry_cookie", () -> new Item(new Item.Settings().food(cookieProperties)));
    public static final Supplier<Item> GLOW_BERRY_COOKIE = ITEM.register("glow_berry_cookie", () ->
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .hunger(2)
                            .saturationModifier(1f)
                            .statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 30 * 20), 1f)
                            .build()
            )
            )
    );
    public static final Supplier<Item> GOLDEN_COOKIE = ITEM.register("golden_cookie", () ->
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .hunger(2)
                            .saturationModifier(1f)
                            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 10 * 20), 1f)
                            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 30 * 20, 1), 1f)
                            .build()
            )
            )
    );
    public static final Supplier<Item> COOKIE_OF_UNBINDING = ITEM.register("cookie_of_unbinding", () ->
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .hunger(2)
                            .saturationModifier(1f)
                            .statusEffect(new StatusEffectInstance(EffectRegister.BLESSING_OF_UNBINDING.get(), (2 * 60 + 30) * 20), 1f).build()
            )
            )
    );
    //Coffees
    public static final Supplier<CoffeeItem> ESPRESSO_COFFEE = ITEM.register("espresso_coffee", () ->
            new CoffeeItem(
                    BlockRegister.ESPRESSO_COFFEE.get(),
                    6,
                    1.5f,
                    new StatusEffectInstance(StatusEffects.SPEED, 30 * 20)
            )
    );
    public static final Supplier<CoffeeItem> DOPPIO_COFFEE = ITEM.register("doppio_coffee", () ->
            new CoffeeItem(
                    BlockRegister.DOPPIO_COFFEE.get(),
                    6,
                    1.5f,
                    new StatusEffectInstance(StatusEffects.SPEED, 30 * 20, 1)
            )
    );
    public static final Supplier<CoffeeItem> MACCHIATO_COFFEE = ITEM.register("macchiato_coffee", () ->
            new CoffeeItem(
                    BlockRegister.MACCHIATO_COFFEE.get(),
                    6,
                    1.5f,
                    new StatusEffectInstance(StatusEffects.ABSORPTION, 30 * 20, 1)
            )
    );
    public static final Supplier<CoffeeItem> DARK_COFFEE = ITEM.register("dark_coffee", () ->
            new CoffeeItem(
                    BlockRegister.DARK_COFFEE.get(),
                    6,
                    1.5f,
                    new StatusEffectInstance(StatusEffects.NIGHT_VISION, 120 * 20)
            )
    );
    public static final Supplier<CoffeeItem> LATTE_COFFEE = ITEM.register("latte_coffee", () ->
            new CoffeeItem(
                    BlockRegister.LATTE_COFFEE.get(),
                    6,
                    1.5f,
                    new StatusEffectInstance(StatusEffects.HASTE, 30 * 20)
            )
    );
    public static final Supplier<CoffeeItem> CAPUCCINO_COFFEE = ITEM.register("capuccino_coffee", () ->
            new CoffeeItem(
                    BlockRegister.CAPUCCINO_COFFEE.get(),
                    6,
                    1.5f,
                    new StatusEffectInstance(StatusEffects.REGENERATION, 30 * 20)
            )
    );
    public static final Supplier<CoffeeItem> MOCHA_COFFEE = ITEM.register("mocha_coffee", () ->
            new CoffeeItem(
                    BlockRegister.MOCHA_COFFEE.get(),
                    6,
                    1.5f,
                    new StatusEffectInstance(StatusEffects.ABSORPTION, 30 * 20, 1),
                    new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 60 * 20)
            )
    );
    public static final Supplier<CoffeeItem> HOT_COCOA = ITEM.register("hot_cocoa", () ->
            new CoffeeItem(
                    BlockRegister.HOT_COCOA.get(),
                    6,
                    1.5f,
                    new StatusEffectInstance(StatusEffects.REGENERATION, 30 * 20),
                    new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 60 * 20)
            )
    );
    public static final Supplier<Item> CAKE_SLICE = ITEM.register("cake_slice", () ->
            new Item(new Item.Settings().food(CAKE_SLICE_PROPS))
    );
    public static final Supplier<Item> CHEESE_CAKE_SLICE = ITEM.register("cheese_cake_slice", () ->
            new Item(new Item.Settings().food(CAKE_SLICE_PROPS))
    );
    public static final Supplier<Item> CHOCOLATE_CHEESE_CAKE_SLICE = ITEM.register("chocolate_cheese_cake_slice", () ->
            new Item(new Item.Settings().food(CAKE_SLICE_PROPS))
    );
    public static final Supplier<Item> HONEY_CHEESE_CAKE_SLICE = ITEM.register("honey_cheese_cake_slice", () ->
            new Item(new Item.Settings().food(CAKE_SLICE_PROPS))
    );
    public static final Supplier<Item> APPLE_PIE_SLICE = ITEM.register("apple_pie_slice", () ->
            new Item(new Item.Settings().food(CAKE_SLICE_PROPS))
    );
    public static final Supplier<Item> SWEET_BERRY_PIE_SLICE = ITEM.register("sweet_berry_pie_slice", () ->
            new Item(new Item.Settings().food(CAKE_SLICE_PROPS))
    );
    public static final Supplier<Item> GLOW_BERRY_PIE_SLICE = ITEM.register("glow_berry_pie_slice", () ->
            new Item(new Item.Settings().food(CAKE_SLICE_PROPS))
    );
    public static final Supplier<Item> CHERRY_PIE_SLICE = ITEM.register("cherry_pie_slice", () ->
            new Item(new Item.Settings().food(CAKE_SLICE_PROPS))
    );
    //Waffles
    public static final Supplier<Item> WAFFLE = ITEM.register("waffle", () ->
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .hunger(5)
                            .saturationModifier(1.25f)
                            .build()
            ))
    );
    public static final Supplier<Item> CHOCOLATE_WAFFLE = ITEM.register("chocolate_waffle", () ->
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .hunger(6)
                            .saturationModifier(1.25f)
                            .build()
            ))
    );
    public static final Supplier<OnConsumeDropItem> CREAM_ICE_CREAM = ITEM.register("cream_ice_cream", () ->
            new OnConsumeDropItem(new Item.Settings().food(ICE_CREAM_PROPS), Items.BOWL.getDefaultStack())
    );
    public static final Supplier<OnConsumeDropItem> COFFEE_ICE_CREAM = ITEM.register("coffee_ice_cream", () ->
            new OnConsumeDropItem(new Item.Settings().food(ICE_CREAM_PROPS), Items.BOWL.getDefaultStack())
    );
    public static final Supplier<OnConsumeDropItem> CHOCOLATE_ICE_CREAM = ITEM.register("chocolate_ice_cream", () ->
            new OnConsumeDropItem(new Item.Settings().food(ICE_CREAM_PROPS), Items.BOWL.getDefaultStack())
    );
    public static final Supplier<OnConsumeDropItem> SWEET_BERRY_ICE_CREAM = ITEM.register("sweet_berry_ice_cream", () ->
            new OnConsumeDropItem(new Item.Settings().food(ICE_CREAM_PROPS), Items.BOWL.getDefaultStack())
    );
    public static final Supplier<OnConsumeDropItem> GLOW_BERRY_ICE_CREAM = ITEM.register("glow_berry_ice_cream", () ->
            new OnConsumeDropItem(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .hunger(6)
                            .saturationModifier(1.5f)
                            .statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 30 * 20), 1f)
                            .build()
            ), Items.BOWL.getDefaultStack())
    );

    public static final Supplier<OnConsumeDropItem> NEAPOLITAN_ICE_CREAM = ITEM.register("neapolitan_ice_cream", () ->
            new OnConsumeDropItem(new Item.Settings().food(new FoodComponent.Builder()
                    .hunger(10)
                    .saturationModifier(1.5f)
                    .build()
            ), Items.BOWL.getDefaultStack())
    );

    public static final Supplier<OnConsumeDropItem> MOCHA_ICE_CREAM = ITEM.register("mocha_ice_cream", () ->
            new OnConsumeDropItem(new Item.Settings().food(ICE_CREAM_PROPS), Items.BOWL.getDefaultStack())
    );

    public static final Supplier<OnConsumeDropItem> CHERRY_ICE_CREAM = ITEM.register("cherry_ice_cream", () ->
            new OnConsumeDropItem(new Item.Settings().food(ICE_CREAM_PROPS), Items.BOWL.getDefaultStack())
    );

    //Cakes
    public static final Supplier<BlockItem> CHEESE_CAKE = ITEM.register("cheese_cake", () -> new BlockItem(BlockRegister.CHEESE_CAKE.get(), new Item.Settings()));
    public static final Supplier<BlockItem> CHOCOLATE_CHEESE_CAKE = ITEM.register("chocolate_cheese_cake", () -> new BlockItem(BlockRegister.CHOCOLATE_CHEESE_CAKE.get(), new Item.Settings()));
    public static final Supplier<BlockItem> HONEY_CHEESE_CAKE = ITEM.register("honey_cheese_cake", () -> new BlockItem(BlockRegister.HONEY_CHEESE_CAKE.get(), new Item.Settings()));

    //Pie
    public static final Supplier<BlockItem> APPLE_PIE = ITEM.register("apple_pie", () -> new BlockItem(BlockRegister.APPLE_PIE.get(), new Item.Settings()));
    public static final Supplier<BlockItem> SWEET_BERRY_PIE = ITEM.register("sweet_berry_pie", () -> new BlockItem(BlockRegister.SWEET_BERRY_PIE.get(), new Item.Settings()));
    public static final Supplier<BlockItem> GLOW_BERRY_PIE = ITEM.register("glow_berry_pie", () -> new BlockItem(BlockRegister.GLOW_BERRY_PIE.get(), new Item.Settings()));
    public static final Supplier<BlockItem> CHERRY_PIE = ITEM.register("cherry_pie", () -> new BlockItem(BlockRegister.CHERRY_PIE.get(), new Item.Settings()));


    //Machinery
    public static final Supplier<BlockItem> COFFEE_MACHINE = ITEM.register("coffee_machine", () -> new BlockItem(BlockRegister.COFFEE_MACHINE.get(), new Item.Settings()));
    public static final Supplier<BlockItem> ICE_CREAM_MAKER = ITEM.register("ice_cream_maker", () -> new BlockItem(BlockRegister.ICE_CREAM_MAKER.get(), new Item.Settings()));
    public static final Supplier<BlockItem> BLENDER = ITEM.register("blender", () -> new BlockItem(BlockRegister.BLENDER.get(), new Item.Settings()));

    // Decor
    public static final Supplier<SeatItem> SEAT = ITEM.register("seat", () -> new SeatItem(BlockRegister.SEAT.get(), new Item.Settings()));
    public static final Supplier<BlockItem> INCENSE_GLASS = ITEM.register("incense_glass", () -> new BlockItem(BlockRegister.INCENSE_GLASS.get(), new Item.Settings()));
    public static final Supplier<BlockItem> DISPLAY_CASE = ITEM.register("display_case", () -> new BlockItem(BlockRegister.DISPLAY_CASE.get(), new Item.Settings()));
    public static final Supplier<BlockItem> KITCHEN_TILES = ITEM.register("kitchen_tiles", () -> new BlockItem(BlockRegister.KITCHEN_TILES.get(), new Item.Settings()));
    public static void register() {
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
}
