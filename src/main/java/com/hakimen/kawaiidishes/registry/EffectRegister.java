package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.effects.BlessingOfUnbindingEffect;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EffectRegister {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, KawaiiDishes.MODID);

    public static final DeferredHolder<MobEffect, BlessingOfUnbindingEffect> BLESSING_OF_UNBINDING = EFFECTS.register("blessing_of_unbinding",BlessingOfUnbindingEffect::new);

    public static void register(IEventBus bus){
        EFFECTS.register(bus);
    }
}
