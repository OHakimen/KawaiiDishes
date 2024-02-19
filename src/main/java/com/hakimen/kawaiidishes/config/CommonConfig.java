package com.hakimen.kawaiidishes.config;

import com.hakimen.kawaiidishes.KawaiiDishes;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.ModConfigSpec;


public class CommonConfig
{

    public static ModConfigSpec COMMON_CONFIG;


    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();


        COMMON_CONFIG = builder.build();
    }
}
