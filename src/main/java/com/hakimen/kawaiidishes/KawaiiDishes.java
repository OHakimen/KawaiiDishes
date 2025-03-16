package com.hakimen.kawaiidishes;

import com.hakimen.kawaiidishes.registry.Registration;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
public class KawaiiDishes implements ModInitializer {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "kawaiidishes";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitialize() {
        Registration.init();
    }
}
