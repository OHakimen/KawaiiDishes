package com.hakimen.kawaiidishes;

import com.hakimen.kawaiidishes.block_entities.SeatBlockEntity;
import com.hakimen.kawaiidishes.client.entity.SeatRenderer;
import com.hakimen.kawaiidishes.client.entity.blockEntityRenderers.DisplayCaseBlockEntityRenderer;
import com.hakimen.kawaiidishes.client.entity.blockEntityRenderers.IncenseGlassBlockEntityRenderer;
import com.hakimen.kawaiidishes.client.screens.BlenderScreen;
import com.hakimen.kawaiidishes.client.screens.CoffeeMachineScreen;
import com.hakimen.kawaiidishes.client.screens.DisplayCaseScreen;
import com.hakimen.kawaiidishes.client.screens.IceCreamMakerScreen;
import com.hakimen.kawaiidishes.item.IDyeableItem;
import com.hakimen.kawaiidishes.item.IFourColorDyeableItem;
import com.hakimen.kawaiidishes.item.SeatItem;
import com.hakimen.kawaiidishes.item.armor.HeadBandWithEarsArmorItem;
import com.hakimen.kawaiidishes.item.armor.MaidDressesWithTailArmorItem;
import com.hakimen.kawaiidishes.item.armor.TailArmorItem;
import com.hakimen.kawaiidishes.particle.IncenseParticle;
import com.hakimen.kawaiidishes.registry.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@Mod.EventBusSubscriber(modid = KawaiiDishes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KawaiiDishesClient {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(EntityRegister.SEAT.get(), SeatRenderer::new);

        event.registerBlockEntityRenderer(BlockEntityRegister.DISPLAY_CASE.get(), DisplayCaseBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegister.INCENSE.get(), IncenseGlassBlockEntityRenderer::new);

        Minecraft.getInstance().particleEngine.register(ParticleRegister.INCENSE_PARTICLES.get(),
                IncenseParticle.Provider::new);
    }

    @SubscribeEvent
    public static void clientStartup(FMLClientSetupEvent event){
        event.enqueueWork(() -> {
            MenuScreens.register(ContainerRegister.COFFEE_MACHINE.get(), CoffeeMachineScreen::new);
            MenuScreens.register(ContainerRegister.BLENDER.get(), BlenderScreen::new);
            MenuScreens.register(ContainerRegister.DISPLAY_CASE.get(), DisplayCaseScreen::new);
            MenuScreens.register(ContainerRegister.ICE_CREAM_MAKER.get(), IceCreamMakerScreen::new);
        });
    }

}