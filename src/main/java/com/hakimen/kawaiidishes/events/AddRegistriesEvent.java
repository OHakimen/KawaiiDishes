package com.hakimen.kawaiidishes.events;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.custom.Registries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.NewRegistryEvent;

@Mod.EventBusSubscriber(modid = KawaiiDishes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AddRegistriesEvent {
    @SubscribeEvent
    static void registerRegistries(NewRegistryEvent event) {
        event.register(Registries.AROMA_REGISTRY);
    }
}
