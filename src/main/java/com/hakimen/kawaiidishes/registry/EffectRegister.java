package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.effects.KawaiiEffect;
import com.hakimen.kawaiidishes.effects.NekoEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.hakimen.kawaiidishes.KawaiiDishes.modId;

public class EffectRegister {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS,modId);

    public static final RegistryObject<KawaiiEffect> kawaiiEffect = MOB_EFFECTS.register("kawaii", KawaiiEffect::new);
    public static final RegistryObject<NekoEffect> nekoEffect = MOB_EFFECTS.register("neko", NekoEffect::new);


    public static void register(IEventBus bus){
        MOB_EFFECTS.register(bus);
    }
}
