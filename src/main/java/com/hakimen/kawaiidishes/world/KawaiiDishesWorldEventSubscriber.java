package com.hakimen.kawaiidishes.world;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.world.feature.CoffeePlantFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KawaiiDishes.modId)
public class KawaiiDishesWorldEventSubscriber {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        CoffeePlantFeature.generateCoffeeOnForest(event);
        CoffeePlantFeature.generateCoffeeOnPlains(event);
    }
}
