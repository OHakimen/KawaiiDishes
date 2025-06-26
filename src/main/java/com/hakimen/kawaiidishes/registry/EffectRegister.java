package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.custom.Recorder;
import com.hakimen.kawaiidishes.effects.BlessingOfUnbindingEffect;
import java.util.function.Supplier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import com.hakimen.kawaiidishes.effects.CalmingEffect;

public class EffectRegister{
    public static final Recorder<MobEffect> EFFECTS = new Recorder<>(BuiltInRegistries.MOB_EFFECT, KawaiiDishes.MODID);

    public static final Supplier<BlessingOfUnbindingEffect> BLESSING_OF_UNBINDING = EFFECTS.register("blessing_of_unbinding",
            BlessingOfUnbindingEffect::new
    );

    public static final Supplier<CalmingEffect> CALMING = EFFECTS.register("calming",
            CalmingEffect::new
    );

    public static void register(){
        //Bootstrap
    }
}
