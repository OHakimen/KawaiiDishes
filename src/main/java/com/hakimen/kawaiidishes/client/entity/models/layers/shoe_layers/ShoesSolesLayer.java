package com.hakimen.kawaiidishes.client.entity.models.layers.shoe_layers;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.layers.GeoArmorLayer;
import com.hakimen.kawaiidishes.item.armor.ShoesArmorItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class ShoesSolesLayer extends GeoArmorLayer<ShoesArmorItem> {


    public ShoesSolesLayer(GeoRenderer<ShoesArmorItem> entityRendererIn) {
        super(entityRendererIn, new ResourceLocation(KawaiiDishes.MODID, "textures/models/armor/shoes/shoes_soles.png"));
    }
    @Override
    public void render(PoseStack poseStack, ShoesArmorItem animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType armorRenderType = RenderType.armorCutoutNoCull(getTexture());

        getRenderer().reRender(getDefaultBakedModel(animatable),poseStack,bufferSource,animatable,armorRenderType,bufferSource.getBuffer(armorRenderType),partialTick,packedLight, OverlayTexture.NO_OVERLAY,1,1,1,1);
    }
}
