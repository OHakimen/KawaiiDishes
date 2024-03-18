package com.hakimen.kawaiidishes;

import com.hakimen.kawaiidishes.config.CommonConfig;
import com.hakimen.kawaiidishes.config.ServerConfig;
import com.hakimen.kawaiidishes.registry.Registration;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(KawaiiDishes.MODID)
public class KawaiiDishes {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "kawaiidishes";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public KawaiiDishes(IEventBus bus) {

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.COMMON_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ServerConfig.SERVER_CONFIG);

        Registration.init(bus);
    }
}
