package com.hakimen.kawaiidishes;

import com.hakimen.kawaiidishes.client.screens.CoffeeMachineScreen;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.hakimen.kawaiidishes.registry.ContainerRegister;
import com.hakimen.kawaiidishes.registry.Registration;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


@Mod("kawaiidishes")
public class KawaiiDishes {

    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String modId = "kawaiidishes";

    public KawaiiDishes() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        Registration.init();
        bus.addListener(this::clientStartup);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
    }

    private void processIMC(final InterModProcessEvent event) {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    private void clientStartup(final FMLClientSetupEvent event){
        event.enqueueWork(() -> {
            MenuScreens.register(ContainerRegister.coffeeMachine.get(), CoffeeMachineScreen::new);
        });

        for(var block: BlockRegister.BLOCKS.getEntries().stream().toList()){
            if(block.get().getRegistryName().getPath().contains("_stool")){
                ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout());
            }
        }
        ItemBlockRenderTypes.setRenderLayer(BlockRegister.coffeePlant.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegister.coffeePress.get(), RenderType.cutout());

    }
}
