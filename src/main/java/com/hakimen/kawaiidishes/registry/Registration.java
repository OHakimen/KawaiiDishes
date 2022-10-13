package com.hakimen.kawaiidishes.registry;

import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class Registration {

    public static void init(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

    }
}
