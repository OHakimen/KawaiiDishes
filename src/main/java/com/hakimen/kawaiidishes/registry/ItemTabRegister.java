package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import static com.hakimen.kawaiidishes.registry.ItemRegister.*;

public class ItemTabRegister {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KawaiiDishes.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CLOTHING_TAB = TABS.register("clothing", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.kawaiidishes.clothing"))
            .icon(() -> new ItemStack(APRON.get()))
            .displayItems((enabledFeatures, entries) -> {
                entries.accept(THIGH_HIGHS.get());
                entries.accept(MAID_DRESS.get());
                entries.accept(HEAD_BAND.get());
                entries.accept(SHOES.get());

                entries.accept(FOX_TAIL.get());
                entries.accept(MAID_DRESS_FOX_TAIL.get());
                entries.accept(FOX_EARS.get());
                entries.accept(HEAD_BAND_FOX_EARS.get());

                entries.accept(BUNNY_TAIL.get());
                entries.accept(MAID_DRESS_BUNNY_TAIL.get());
                entries.accept(BUNNY_EARS.get());
                entries.accept(HEAD_BAND_BUNNY_EARS.get());

                entries.accept(CAT_TAIL.get());
                entries.accept(MAID_DRESS_CAT_TAIL.get());
                entries.accept(CAT_EARS.get());
                entries.accept(HEAD_BAND_CAT_EARS.get());

                entries.accept(APRON.get());

                entries.accept(BOW.get());
                entries.accept(LEG_CLIP.get());
                entries.accept(DOUBLE_BANDS.get());
                entries.accept(FULL_BANDS.get());
            }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DECORATION_TABS = TABS.register("decoration", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.kawaiidishes.decoration"))
            .icon(() -> SEAT.get().getDefaultInstance())
            .displayItems((enabledFeatures, entries) -> {
                entries.accept(SEAT.get());
                entries.accept(KITCHEN_TILES.get());
                entries.accept(DISPLAY_CASE.get());
                entries.accept(INCENSE_GLASS.get());
            }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> FOOD_TAB = TABS.register("food", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.kawaiidishes.food"))
            .icon(() -> new ItemStack(COFFEE_BERRIES.get()))
            .displayItems((enabledFeatures, entries) -> {
                entries.accept(COFFEE_BERRIES.get());
                entries.accept(CHERRY.get());

                entries.accept(COFFEE_BEANS.get());
                entries.accept(ROAST_COFFEE_BEANS.get());

                entries.accept(GROUND_COFFEE.get());
                entries.accept(COCOA_POWDER.get());

                entries.accept(STEAMED_MILK_BUCKET.get());
                entries.accept(MILK_FOAM_BUCKET.get());

                entries.accept(CREAM_CHEESE_BALL.get());

                entries.accept(MUG.get());

                //Coffee
                entries.accept(DARK_COFFEE.get());
                entries.accept(ESPRESSO_COFFEE.get());
                entries.accept(DOPPIO_COFFEE.get());
                entries.accept(MACCHIATO_COFFEE.get());
                entries.accept(LATTE_COFFEE.get());
                entries.accept(CAPUCCINO_COFFEE.get());
                entries.accept(MOCHA_COFFEE.get());
                entries.accept(HOT_COCOA.get());

                //Cakes
                entries.accept(CHEESE_CAKE.get());
                entries.accept(CHOCOLATE_CHEESE_CAKE.get());
                entries.accept(HONEY_CHEESE_CAKE.get());

                //Pie
                entries.accept(APPLE_PIE.get());
                entries.accept(SWEET_BERRY_PIE.get());
                entries.accept(GLOW_BERRY_PIE.get());
                entries.accept(CHERRY_PIE.get());

                //Cake Slices
                entries.accept(CAKE_SLICE.get());
                entries.accept(CHEESE_CAKE_SLICE.get());
                entries.accept(CHOCOLATE_CHEESE_CAKE_SLICE.get());
                entries.accept(HONEY_CHEESE_CAKE_SLICE.get());

                //Pie Slices
                entries.accept(APPLE_PIE_SLICE.get());
                entries.accept(SWEET_BERRY_PIE_SLICE.get());
                entries.accept(GLOW_BERRY_PIE_SLICE.get());
                entries.accept(CHERRY_PIE_SLICE.get());

                //Waffles
                entries.accept(WAFFLE.get());
                entries.accept(CHOCOLATE_WAFFLE.get());

                entries.accept(CREAM_ICE_CREAM.get());
                entries.accept(COFFEE_ICE_CREAM.get());
                entries.accept(CHOCOLATE_ICE_CREAM.get());
                entries.accept(SWEET_BERRY_ICE_CREAM.get());
                entries.accept(GLOW_BERRY_ICE_CREAM.get());
                entries.accept(NEAPOLITAN_ICE_CREAM.get());
                entries.accept(MOCHA_ICE_CREAM.get());
                entries.accept(CHERRY_ICE_CREAM.get());

                //Cookies
                entries.accept(CHOCOLATE_COOKIE.get());
                entries.accept(SWEET_BERRY_COOKIE.get());
                entries.accept(GLOW_BERRY_COOKIE.get());
                entries.accept(GOLDEN_COOKIE.get());
                entries.accept(COOKIE_OF_UNBINDING.get());

                //Machinery
                entries.accept(COFFEE_MACHINE.get());
                entries.accept(BLENDER.get());
                entries.accept(ICE_CREAM_MAKER.get());
            }).build());

    public static void register(IEventBus bus){
        TABS.register(bus);
    }
}

