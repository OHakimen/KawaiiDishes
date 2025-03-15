package com.hakimen.kawaiidishes;

import com.hakimen.kawaiidishes.configs.ServerConfig;
import com.hakimen.kawaiidishes.events.AddVillagerTrades;
import com.hakimen.kawaiidishes.events.MobSpawnedEvent;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import com.hakimen.kawaiidishes.registry.Registration;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.minecraftforge.fml.config.ModConfig;

public class KawaiiDishes implements ModInitializer {
    public static final String MODID = "kawaiidishes";
    @Override
    public void onInitialize() {
        Registration.init();

        ForgeConfigRegistry.INSTANCE.register(KawaiiDishes.MODID, ModConfig.Type.SERVER, ServerConfig.SERVER_CONFIG);

        MobSpawnedEvent.handle();
        AddVillagerTrades.handle();

        ItemStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> {
            return blockEntity.getStorage();
        }, BlockEntityRegister.COFFEE_MACHINE.get());

        ItemStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> {
            return blockEntity.getStorage();
        }, BlockEntityRegister.DISPLAY_CASE.get());


        FluidStorage.SIDED.registerForBlockEntity((tank, direction) -> tank.getWaterTank(), BlockEntityRegister.COFFEE_MACHINE.get());
    }
}
