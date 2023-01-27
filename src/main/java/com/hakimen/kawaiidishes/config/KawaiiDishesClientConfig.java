package com.hakimen.kawaiidishes.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class KawaiiDishesClientConfig {
    public static final ForgeConfigSpec.Builder clientConfigBuilder = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec clientSpec;
    static {
        clientConfigBuilder.push("Client Side Configs for Kawaii Dishes");
        clientConfigBuilder.pop();
        clientSpec = clientConfigBuilder.build();
    }


}
