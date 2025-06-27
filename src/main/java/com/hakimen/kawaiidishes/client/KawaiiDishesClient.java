package com.hakimen.kawaiidishes.client;

import com.hakimen.kawaiidishes.client.init.ColourProviderInitializer;
import com.hakimen.kawaiidishes.client.init.EarsCompatibilityInitializer;
import com.hakimen.kawaiidishes.client.init.ItemPropertyInitializer;
import com.hakimen.kawaiidishes.client.init.ScreenInitializer;
import com.hakimen.kawaiidishes.particle.IncenseParticle;
import com.hakimen.kawaiidishes.registry.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import mod.azure.azurelib.AzureLib;

public class KawaiiDishesClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        AzureLib.initialize();
        ColourProviderInitializer.init();
        ItemPropertyInitializer.init();
        ScreenInitializer.init();
        EarsCompatibilityInitializer.init();

        ParticleFactoryRegistry.getInstance().register(ParticleRegister.INCENSE_PARTICLE.get(), IncenseParticle.Provider::new);
        PacketRegister.registerServer2ClientPackets();
    }
}
