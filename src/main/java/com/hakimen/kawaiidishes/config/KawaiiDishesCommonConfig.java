package com.hakimen.kawaiidishes.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Arrays;
import java.util.List;

public class KawaiiDishesCommonConfig {
    public static final ForgeConfigSpec.Builder commonConfigBuilder = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec commonSpec;
    public static final ForgeConfigSpec.BooleanValue shouldMobSpawnWithDress;
    public static final ForgeConfigSpec.DoubleValue chanceToSpawnWithDress;

    public static final ForgeConfigSpec.DoubleValue chanceToMessage;
    public static final ForgeConfigSpec.BooleanValue shouldSendMessage;

    public static final ForgeConfigSpec.BooleanValue wanderingTraderTrades;
    public static final ForgeConfigSpec.BooleanValue villagerTrades;
    public static final ForgeConfigSpec.DoubleValue chanceToDropArmorSet;

    public static final ForgeConfigSpec.IntValue villageStructWeight;
    public static final ForgeConfigSpec.ConfigValue<List<String>> messages;
    static {
        commonConfigBuilder.push("Common Configs for Kawaii Dishes");

        shouldSendMessage = commonConfigBuilder.comment("Define if the Kawaii Effect can send messages to local players")
                .define("canSendMessage",true);

        chanceToMessage = commonConfigBuilder.comment("Set the chance to message the player")
                .defineInRange("chanceToMessage",  0.00025,0,1);

        messages = commonConfigBuilder.comment("Sets the messages that are sent to the player")
                .define("messages", Arrays.asList("You look so kawaii !!",
                        "OuO",
                        "Cute <3",
                        "You look nice today",
                        "Hi Cutie !",
                        "Hey sweetie!"));

        shouldMobSpawnWithDress = commonConfigBuilder.comment("Should the mobs spawn with the maid dresses")
                        .define("shouldSpawn", true);

        chanceToSpawnWithDress = commonConfigBuilder.comment("Sets the chance for mobs to spawn with maid dresses")
                .defineInRange("chanceToSpawn", 0.075,0,1);

        chanceToDropArmorSet = commonConfigBuilder.comment("Sets the chance for mobs that spawn with maid dresses to drop them")
                .defineInRange("chanceToDrop", 0.25,0,1);

        villagerTrades = commonConfigBuilder.comment("Should the add item trades for villagers")
                .define("villagerTrades", true);
        wanderingTraderTrades = commonConfigBuilder.comment("Should the add item trades for wandering traders")
                .define("wanderingTraderTrades", true);

        villageStructWeight = commonConfigBuilder.comment("Structure generation weights for the village houses")
                .defineInRange("villageStructWeight", 125,0,450);

        commonConfigBuilder.pop();
        commonSpec = commonConfigBuilder.build();
    }


}
