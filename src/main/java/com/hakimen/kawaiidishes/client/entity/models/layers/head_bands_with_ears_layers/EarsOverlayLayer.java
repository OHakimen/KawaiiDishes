package com.hakimen.kawaiidishes.client.entity.models.layers.head_bands_with_ears_layers;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.layers.GeoArmorLayer;
import com.hakimen.kawaiidishes.item.armor.HeadBandWithEarsArmorItem;
import com.hakimen.kawaiidishes.utils.ColorUtils;
import com.hakimen.kawaiidishes.utils.HeadBandsWithEarsUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class EarsOverlayLayer extends GeoArmorLayer<HeadBandWithEarsArmorItem> {
    ItemStack stackData;

    public void updateStack(ItemStack stack){
        this.stackData = stack;
    }
    public EarsOverlayLayer(GeoRenderer<HeadBandWithEarsArmorItem> entityRendererIn) {
        super(entityRendererIn, new ResourceLocation(KawaiiDishes.MODID, "textures/models/armor/none.png"));
    }

    @Override
    public ResourceLocation getTexture() {
        return HeadBandsWithEarsUtils.getEaredHeadBandsEarsOverlay().get(((HeadBandWithEarsArmorItem)stackData.getItem()).getEarsType());
    }

    @Override
    public void render(PoseStack poseStack, HeadBandWithEarsArmorItem animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType armorRenderType = RenderType.armorCutoutNoCull(getTexture());

        float[] rgb = ColorUtils.getColorsFromHex(animatable.getSecondaryOverlayColor(stackData));

        getRenderer().reRender(getDefaultBakedModel(animatable),poseStack,bufferSource,animatable,armorRenderType,bufferSource.getBuffer(armorRenderType),partialTick,packedLight, OverlayTexture.NO_OVERLAY,rgb[0],rgb[1],rgb[2],1);
    }
}
