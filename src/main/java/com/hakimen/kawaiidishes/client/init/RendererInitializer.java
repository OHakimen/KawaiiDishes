package com.hakimen.kawaiidishes.client.init;

import com.hakimen.kawaiidishes.client.blockEntityRenderers.DisplayCaseBlockEntityRenderer;
import com.hakimen.kawaiidishes.client.blockEntityRenderers.IncenseGlassBlockEntityRenderer;
import com.hakimen.kawaiidishes.client.entity.mobRenderer.SeatRenderer;
import com.hakimen.kawaiidishes.item.armor.TailArmorItem;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.hakimen.kawaiidishes.registry.EntityRegister;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRenderEvents;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static com.hakimen.kawaiidishes.client.init.EarsCompatibilityInitializer.isMaidOutfit;

public class RendererInitializer {
    public static void init() {
        LivingEntityFeatureRenderEvents.ALLOW_CAPE_RENDER.register(player -> {
            for (ItemStack slot : player.getArmorItems()) {
                Item item = slot.getItem();
                if (item instanceof TailArmorItem || isMaidOutfit(item)) {
                    return false;
                }
            }
            return true;
        });

        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.COFFEE_BUSH.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.INCENSE_GLASS.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.ICE_CREAM_MAKER.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.DISPLAY_CASE.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.BLENDER.get(), RenderLayer.getCutout());

        BlockEntityRendererFactories.register(BlockEntityRegister.INCENSE.get(), IncenseGlassBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(BlockEntityRegister.DISPLAY_CASE.get(), DisplayCaseBlockEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegister.SEAT.get(), SeatRenderer::new);

    }
}
