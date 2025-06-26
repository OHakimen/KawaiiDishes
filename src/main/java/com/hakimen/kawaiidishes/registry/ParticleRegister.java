package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.custom.Recorder;
import java.util.function.Supplier;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

public class ParticleRegister {

    static class SimpleParticleCallable extends SimpleParticleType{

        public SimpleParticleCallable(boolean bl) {
            super(bl);
        }
    }
    public static final Recorder<ParticleType<?>> PARTICLE_TYPES = new Recorder<>(BuiltInRegistries.PARTICLE_TYPE, KawaiiDishes.MODID);

    public static final Supplier<SimpleParticleType> INCENSE_PARTICLE = PARTICLE_TYPES.register("incense", () -> new SimpleParticleCallable(false));
    public static void register(){
        //Bootstrap
    }
}
