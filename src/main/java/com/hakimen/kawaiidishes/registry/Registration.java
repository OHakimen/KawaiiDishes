package com.hakimen.kawaiidishes.registry;

public class Registration{

    public static void init() {

        //Do all registration here

//        AromaRegister.register();

        BlockRegister.register();
        ItemRegister.register();
        ItemTabRegister.register();
        RecipeRegister.register();
        EffectRegister.register();
        BlockEntityRegister.register();
//        ContainerRegister.register();
        EntityRegister.register();
//        LootModifierRegistry.register();
        EnchantmentRegister.register();

        DataComponentRegister.register();

//        ParticleRegister.register();


    }
}