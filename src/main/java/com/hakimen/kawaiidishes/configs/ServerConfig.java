package com.hakimen.kawaiidishes.configs;

import com.electronwill.nightconfig.core.ConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfig {
    public static ForgeConfigSpec SERVER_CONFIG;
    public static ForgeConfigSpec.DoubleValue catsAuraAmplifier;
    public static ForgeConfigSpec.DoubleValue dressedMobsSpawnRate;
    public static ForgeConfigSpec.DoubleValue dressedDropRate;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        catsAuraAmplifier = builder
                .comment("Define the range multiplier for the Cat's Aura Enchantment")
                .defineInRange("cats_aura_range_amplifier", () -> 2d, 1,4);

        dressedMobsSpawnRate = builder
                .comment("Define the rate of dressed mobs that can spawn")
                .defineInRange("dressed_mobs_spawn_rate", () -> 0.125d, 0d, 1d);

        dressedDropRate = builder
                .comment("Drop rate for dressed mobs")
                .defineInRange("drop_rate_dressed_mobs", () -> 0.25d, 0d, 1d);

        SERVER_CONFIG = builder.build();
    }
}
