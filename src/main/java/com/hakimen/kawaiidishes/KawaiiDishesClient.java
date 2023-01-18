package com.hakimen.kawaiidishes;

import com.hakimen.kawaiidishes.client.block_entity_renderers.CoffeeMachineRenderer;
import com.hakimen.kawaiidishes.client.block_entity_renderers.CoffeePressRenderer;
import com.hakimen.kawaiidishes.client.block_entity_renderers.IceCreamMachineRenderer;
import com.hakimen.kawaiidishes.client.entity.*;
import com.hakimen.kawaiidishes.client.entity.maid_dresses.*;
import com.hakimen.kawaiidishes.items.armor.*;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import com.hakimen.kawaiidishes.registry.EntityRegister;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = KawaiiDishes.modId, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KawaiiDishesClient {
    @SubscribeEvent
    public static void registerArmorRenderers(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(CatTailArmorItem.class, CatTailArmorRenderer::new);
        GeoArmorRenderer.registerArmorRenderer(MaidDressArmorItem.class, MaidDressArmorRenderer::new);
        GeoArmorRenderer.registerArmorRenderer(CatMaidArmorItem.class, CatMaidDressArmorRenderer::new);
        GeoArmorRenderer.registerArmorRenderer(ThighHighsArmorItem.class, ThighHighsArmorRenderer::new);
        GeoArmorRenderer.registerArmorRenderer(FoxMaidArmorItem.class, FoxMaidDressArmorRenderer::new);
        GeoArmorRenderer.registerArmorRenderer(FoxTailArmorItem.class, FoxTailArmorRenderer::new);
        GeoArmorRenderer.registerArmorRenderer(BunnyMaidArmorItem.class, BunnyMaidDressArmorRenderer::new);
        GeoArmorRenderer.registerArmorRenderer(BunnyTailArmorItem.class, BunnyTailArmorRenderer::new);
        GeoArmorRenderer.registerArmorRenderer(DevilMaidArmorItem.class, DevilMaidDressArmorRenderer::new);
        GeoArmorRenderer.registerArmorRenderer(DevilTailArmorItem.class, DevilTailArmorRenderer::new);

    }
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(BlockEntityRegister.coffeePress.get(), CoffeePressRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegister.coffeeMachine.get(), CoffeeMachineRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegister.iceCreamMachine.get(), IceCreamMachineRenderer::new);

        event.registerEntityRenderer(EntityRegister.SEAT.get(), SeatRenderer::new);
    }
}
