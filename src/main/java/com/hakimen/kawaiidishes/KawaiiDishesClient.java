package com.hakimen.kawaiidishes;

import com.hakimen.kawaiidishes.client.entity.CatMaidDressArmorRenderer;
import com.hakimen.kawaiidishes.client.entity.CatTailArmorRenderer;
import com.hakimen.kawaiidishes.client.entity.MaidDressArmorRenderer;
import com.hakimen.kawaiidishes.client.entity.ThighHighsArmorRenderer;
import com.hakimen.kawaiidishes.items.armor.CatMaidArmorItem;
import com.hakimen.kawaiidishes.items.armor.CatTailArmorItem;
import com.hakimen.kawaiidishes.items.armor.MaidDressArmorItem;
import com.hakimen.kawaiidishes.items.armor.ThighHighsArmorItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = KawaiiDishes.modId, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KawaiiDishesClient {
    @SubscribeEvent
    public static void registerArmorRenderers(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(CatTailArmorItem.class, new CatTailArmorRenderer());
        GeoArmorRenderer.registerArmorRenderer(MaidDressArmorItem.class, new MaidDressArmorRenderer());
        GeoArmorRenderer.registerArmorRenderer(CatMaidArmorItem.class, new CatMaidDressArmorRenderer());
        GeoArmorRenderer.registerArmorRenderer(ThighHighsArmorItem.class, new ThighHighsArmorRenderer());

    }
}
