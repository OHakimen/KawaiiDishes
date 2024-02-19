package com.hakimen.kawaiidishes.registry;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;


public class Registration{

    public static void init(IEventBus bus){

        //Do all registration here

        BlockRegister.register(bus);
        ItemRegister.register(bus);
        ItemTabRegister.register(bus);
        RecipeRegister.register(bus);
        EffectRegister.register(bus);
        BlockEntityRegister.register(bus);
        ContainerRegister.register(bus);
        EntityRegister.register(bus);
        LootModifierRegistry.register(bus);
        EnchantmentRegister.register(bus);

        ParticleRegister.register(bus);


    }
}