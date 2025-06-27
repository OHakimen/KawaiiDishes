package com.hakimen.kawaiidishes.client.entity.models.layers.head_bands_with_ears_layers;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.layers.GeoArmorLayer;
import com.hakimen.kawaiidishes.item.armor.HeadBandWithEarsArmorItem;
import com.hakimen.kawaiidishes.utils.ColorUtils;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import mod.azure.azurelib.cache.object.BakedGeoModel;
import mod.azure.azurelib.renderer.GeoRenderer;

public class HeadBandOverlayLayer extends GeoArmorLayer<HeadBandWithEarsArmorItem> {
    ItemStack stackData;

    public void updateStack(ItemStack stack){
        this.stackData = stack;
    }
    public HeadBandOverlayLayer(GeoRenderer<HeadBandWithEarsArmorItem> entityRendererIn, ItemStack stack) {
        super(entityRendererIn, new Identifier(KawaiiDishes.MODID, "textures/models/armor/head_bands_with_ears/head_bands/%s/head_band_overlay.png".formatted(((HeadBandWithEarsArmorItem)stack.getItem()).getEarsType().toString().toLowerCase())));
    }

    @Override
    public Identifier getTexture() {
        return new Identifier(KawaiiDishes.MODID, "textures/models/armor/head_bands_with_ears/head_bands/%s/head_band_overlay.png".formatted(((HeadBandWithEarsArmorItem)stackData.getItem()).getEarsType().toString().toLowerCase()));
    }

    @Override
    public void render(MatrixStack poseStack, HeadBandWithEarsArmorItem animatable, BakedGeoModel bakedModel, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderLayer armorRenderType = RenderLayer.getArmorCutoutNoCull(getTexture());

        float[] rgb = ColorUtils.getColorsFromHex(animatable.getPrimaryOverlayColor(stackData));

        getRenderer().reRender(getDefaultBakedModel(animatable),poseStack,bufferSource,animatable,armorRenderType,bufferSource.getBuffer(armorRenderType),partialTick,packedLight, OverlayTexture.DEFAULT_UV,rgb[0],rgb[1],rgb[2],1);
    }
}
