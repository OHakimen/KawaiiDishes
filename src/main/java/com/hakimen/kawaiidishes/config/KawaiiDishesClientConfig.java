package com.hakimen.kawaiidishes.config;

import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.ForgeConfigSpec;

public class KawaiiDishesClientConfig {
    public static final ForgeConfigSpec.Builder clientConfigBuilder = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue shouldSendMessage;


    public static final ForgeConfigSpec clientSpec;


    static {
        clientConfigBuilder.push("Client Side Configs for Kawaii Dishes");
        shouldSendMessage = clientConfigBuilder.comment("Define if the Kawaii Effect can send messages to the local player")
                        .define("canSendMessage",true);
        clientConfigBuilder.pop();
        clientSpec = clientConfigBuilder.build();
    }


}
