package com.hakimen.kawaiidishes.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfig {
    public static ModConfigSpec SERVER_CONFIG;
    public static ModConfigSpec.DoubleValue catsAuraAmplifier;
    public static ModConfigSpec.DoubleValue dressedMobsSpawnRate;
    public static ModConfigSpec.DoubleValue dressedDropRate;
    public static ModConfigSpec.IntValue structureWeight;
    public static ModConfigSpec.BooleanValue shouldAddVillagerTrades;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        catsAuraAmplifier = builder
                .comment("Define the range multiplier for the Cat's Aura Enchantment")
                .defineInRange("cats_aura_range_amplifier", () -> 2d, 1,4);

        dressedMobsSpawnRate = builder
                .comment("Define the rate of dressed mobs that can spawn")
                .defineInRange("dressed_mobs_spawn_rate", () -> 0.125d, 0d, 1d);

        dressedDropRate = builder
                .comment("Drop rate for dressed mobs")
                .defineInRange("drop_rate_dressed_mobs", () -> 0.25d, 0d, 1d);

        structureWeight = builder
                .comment("Spawn rate for buildings")
                .defineInRange("structure_weight", () -> 125, 0, 1000);

        shouldAddVillagerTrades = builder
                .comment("Should add trades for villagers ?")
                .define("should_add_trades", true);
        SERVER_CONFIG = builder.build();
    }
}
