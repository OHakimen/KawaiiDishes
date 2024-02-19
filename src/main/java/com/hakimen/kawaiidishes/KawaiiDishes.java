package com.hakimen.kawaiidishes;

import com.hakimen.kawaiidishes.client.screens.BlenderScreen;
import com.hakimen.kawaiidishes.client.screens.CoffeeMachineScreen;
import com.hakimen.kawaiidishes.client.screens.DisplayCaseScreen;
import com.hakimen.kawaiidishes.client.screens.IceCreamMakerScreen;
import com.hakimen.kawaiidishes.config.CommonConfig;
import com.hakimen.kawaiidishes.config.ServerConfig;
import com.hakimen.kawaiidishes.particle.IncenseParticle;
import com.hakimen.kawaiidishes.registry.ContainerRegister;
import com.hakimen.kawaiidishes.registry.ParticleRegister;
import com.hakimen.kawaiidishes.registry.Registration;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import software.bernie.example.GeckoLibMod;
import software.bernie.geckolib.GeckoLib;
import software.bernie.geckolib.util.GeckoLibUtil;

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
