package com.hakimen.kawaiidishes.registry;


public class Registration {
    public static void init(){
        //This is basically to bootstrap the classes, so they are instanced
        ThighHighsDecorationRegister.register();
        AromaRegister.register();

        ItemRegister.register();
        BlockRegister.register();
        BlockEntityRegister.register();
        ItemTabRegister.register();
        RecipeRegister.register();
        EntityRegister.register();
        ContainerRegister.register();
        LootModifierRegister.register();
        WorldGenerationRegister.register();

        EffectRegister.register();
        EnchantmentRegister.register();
    }

}
