package com.hakimen.kawaiidishes.registry;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class Registration {

    public static void init(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BlockRegister.register(bus);
        BlockEntityRegister.register(bus);
        ItemRegister.register(bus);
        ContainerRegister.register(bus);
        RecipeRegister.register(bus);
        EffectRegister.register(bus);
        EntityRegister.register(bus);
        PlacedFeaturesRegistry.register(bus);

    }

}
