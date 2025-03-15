package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.custom.Recorder;
import java.util.function.Supplier;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;

public class ParticleRegister {

    static class SimpleParticleCallable extends DefaultParticleType{

        public SimpleParticleCallable(boolean bl) {
            super(bl);
        }
    }
    public static final Recorder<ParticleType<?>> PARTICLE_TYPES = new Recorder<>(Registries.PARTICLE_TYPE, KawaiiDishes.MODID);

    public static final Supplier<DefaultParticleType> INCENSE_PARTICLE = PARTICLE_TYPES.register("incense", () -> new SimpleParticleCallable(false));
    public static void register(){
        //Bootstrap
    }
}
