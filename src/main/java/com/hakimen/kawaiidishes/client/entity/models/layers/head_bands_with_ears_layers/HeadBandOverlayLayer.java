package com.hakimen.kawaiidishes.client.entity.models.layers.head_bands_with_ears_layers;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.layers.GeoArmorLayer;
import com.hakimen.kawaiidishes.item.armor.HeadBandWithEarsArmorItem;
import com.hakimen.kawaiidishes.registry.DataComponentRegister;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

import java.lang.ref.WeakReference;

public class HeadBandOverlayLayer extends GeoArmorLayer<HeadBandWithEarsArmorItem> {
    private WeakReference<ItemStack> stackData;

    public void updateStack(ItemStack stack) {
        this.stackData = new WeakReference<>(stack);
    }

    public ItemStack getStack() {
        return this.stackData.get();
    }
    public HeadBandOverlayLayer(GeoRenderer<HeadBandWithEarsArmorItem> entityRendererIn, ItemStack stack) {
        super(entityRendererIn, ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "textures/models/armor/head_bands_with_ears/head_bands/%s/head_band_overlay.png".formatted(((HeadBandWithEarsArmorItem)stack.getItem()).getEarsType().toString().toLowerCase())));
    }

    @Override
    public ResourceLocation getTexture() {
        return ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "textures/models/armor/head_bands_with_ears/head_bands/%s/head_band_overlay.png".formatted(((HeadBandWithEarsArmorItem) getStack().getItem()).getEarsType().toString().toLowerCase()));
    }

    @Override
    public void render(PoseStack poseStack, HeadBandWithEarsArmorItem animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType armorRenderType = RenderType.armorCutoutNoCull(getTexture());


        getRenderer().reRender(
                getDefaultBakedModel(animatable),
                poseStack,
                bufferSource,
                animatable,armorRenderType,
                bufferSource.getBuffer(armorRenderType),
                partialTick,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                getStack().get(DataComponentRegister.DYEABLE.get()).getOverlay());
    }
}
