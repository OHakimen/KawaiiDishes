package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.custom.Recorder;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import java.util.function.Supplier;

import static com.hakimen.kawaiidishes.registry.ItemRegister.*;



public class ItemTabRegister {
    static Recorder<ItemGroup> TABS = new Recorder<>(Registries.ITEM_GROUP, KawaiiDishes.MODID);
    public static final Supplier<ItemGroup> FOOD_TAB = TABS.register("food", () -> FabricItemGroup.builder()
            .icon(() -> new ItemStack(MUG.get()))
            .displayName(Text.translatable("itemGroup.kawaiidishes.food"))
            .entries((context, entries) -> {
                entries.add(COFFEE_BERRIES.get());
                entries.add(CHERRY.get());

                entries.add(COFFEE_BEANS.get());
                entries.add(ROAST_COFFEE_BEANS.get());

                entries.add(GROUND_COFFEE.get());
                entries.add(COCOA_POWDER.get());

                entries.add(STEAMED_MILK_BUCKET.get());
                entries.add(MILK_FOAM_BUCKET.get());

                entries.add(CREAM_CHEESE_BALL.get());

                entries.add(MUG.get());

                //Coffee
                entries.add(DARK_COFFEE.get());
                entries.add(ESPRESSO_COFFEE.get());
                entries.add(DOPPIO_COFFEE.get());
                entries.add(MACCHIATO_COFFEE.get());
                entries.add(LATTE_COFFEE.get());
                entries.add(CAPUCCINO_COFFEE.get());
                entries.add(MOCHA_COFFEE.get());
                entries.add(HOT_COCOA.get());

                //Cakes
                entries.add(CHEESE_CAKE.get());
                entries.add(CHOCOLATE_CHEESE_CAKE.get());
                entries.add(HONEY_CHEESE_CAKE.get());

                //Pie
                entries.add(APPLE_PIE.get());
                entries.add(SWEET_BERRY_PIE.get());
                entries.add(GLOW_BERRY_PIE.get());
                entries.add(CHERRY_PIE.get());

                //Cake Slices
                entries.add(CAKE_SLICE.get());
                entries.add(CHEESE_CAKE_SLICE.get());
                entries.add(CHOCOLATE_CHEESE_CAKE_SLICE.get());
                entries.add(HONEY_CHEESE_CAKE_SLICE.get());

                //Pie Slices
                entries.add(APPLE_PIE_SLICE.get());
                entries.add(SWEET_BERRY_PIE_SLICE.get());
                entries.add(GLOW_BERRY_PIE_SLICE.get());
                entries.add(CHERRY_PIE_SLICE.get());

                //Waffles
                entries.add(WAFFLE.get());
                entries.add(CHOCOLATE_WAFFLE.get());

                entries.add(CREAM_ICE_CREAM.get());
                entries.add(COFFEE_ICE_CREAM.get());
                entries.add(CHOCOLATE_ICE_CREAM.get());
                entries.add(SWEET_BERRY_ICE_CREAM.get());
                entries.add(GLOW_BERRY_ICE_CREAM.get());
                entries.add(NEAPOLITAN_ICE_CREAM.get());
                entries.add(MOCHA_ICE_CREAM.get());
                entries.add(CHERRY_ICE_CREAM.get());

                //Cookies
                entries.add(CHOCOLATE_COOKIE.get());
                entries.add(SWEET_BERRY_COOKIE.get());
                entries.add(GLOW_BERRY_COOKIE.get());
                entries.add(GOLDEN_COOKIE.get());
                entries.add(COOKIE_OF_UNBINDING.get());

                //Machinery
                entries.add(COFFEE_MACHINE.get());
                entries.add(BLENDER.get());
                entries.add(ICE_CREAM_MAKER.get());
            })
            .build());

    public static final Supplier<ItemGroup> DECORATION_TABS = TABS.register("decoration", () -> FabricItemGroup.builder()
            .displayName(Text.translatable("itemGroup.kawaiidishes.decoration"))
            .icon(() -> SEAT.get().getDefaultStack())
            .entries((enabledFeatures, entries) -> {
                entries.add(SEAT.get());
                entries.add(KITCHEN_TILES.get());
                entries.add(DISPLAY_CASE.get());
                entries.add(INCENSE_GLASS.get());
            }).build());
    public static final Supplier<ItemGroup> CLOTHING_TAB = TABS.register("clothing", () -> FabricItemGroup.builder()
            .displayName(Text.translatable("itemGroup.kawaiidishes.clothing"))
            .icon(() -> new ItemStack(APRON.get()))
            .entries((enabledFeatures, entries) -> {
                entries.add(THIGH_HIGHS.get());
                entries.add(MAID_DRESS.get());
                entries.add(HEAD_BAND.get());
                entries.add(SHOES.get());

                entries.add(FOX_TAIL.get());
                entries.add(MAID_DRESS_FOX_TAIL.get());
                entries.add(FOX_EARS.get());
                entries.add(HEAD_BAND_FOX_EARS.get());

                entries.add(BUNNY_TAIL.get());
                entries.add(MAID_DRESS_BUNNY_TAIL.get());
                entries.add(BUNNY_EARS.get());
                entries.add(HEAD_BAND_BUNNY_EARS.get());

                entries.add(CAT_TAIL.get());
                entries.add(MAID_DRESS_CAT_TAIL.get());
                entries.add(CAT_EARS.get());
                entries.add(HEAD_BAND_CAT_EARS.get());

                entries.add(APRON.get());

                entries.add(BOW.get());
                entries.add(LEG_CLIP.get());
                entries.add(DOUBLE_BANDS.get());
                entries.add(FULL_BANDS.get());
            }).build());

    public static void register(){
        //This does nothing but bootstrap the class
    }
}
