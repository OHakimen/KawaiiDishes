package com.hakimen.kawaiidishes.client.entity.models.layers.head_bands_with_ears_layers;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.layers.GeoArmorLayer;
import com.hakimen.kawaiidishes.item.armor.HeadBandWithEarsArmorItem;
import com.hakimen.kawaiidishes.utils.ColorUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.checkerframework.checker.units.qual.K;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class HeadBandOverlayLayer extends GeoArmorLayer<HeadBandWithEarsArmorItem> {
    ItemStack stackData;

    public void updateStack(ItemStack stack){
        this.stackData = stack;
    }
    public HeadBandOverlayLayer(GeoRenderer<HeadBandWithEarsArmorItem> entityRendererIn, ItemStack stack) {
        super(entityRendererIn, new ResourceLocation(KawaiiDishes.MODID, "textures/models/armor/head_bands_with_ears/head_bands/%s/head_band_overlay.png".formatted(((HeadBandWithEarsArmorItem)stack.getItem()).getEarsType().toString().toLowerCase())));
    }

    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation(KawaiiDishes.MODID, "textures/models/armor/head_bands_with_ears/head_bands/%s/head_band_overlay.png".formatted(((HeadBandWithEarsArmorItem)stackData.getItem()).getEarsType().toString().toLowerCase()));
    }

    @Override
    public void render(PoseStack poseStack, HeadBandWithEarsArmorItem animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType armorRenderType = RenderType.armorCutoutNoCull(getTexture());

        float[] rgb = ColorUtils.getColorsFromHex(animatable.getPrimaryOverlayColor(stackData));

        getRenderer().reRender(getDefaultBakedModel(animatable),poseStack,bufferSource,animatable,armorRenderType,bufferSource.getBuffer(armorRenderType),partialTick,packedLight, OverlayTexture.NO_OVERLAY,rgb[0],rgb[1],rgb[2],1);
    }
}
