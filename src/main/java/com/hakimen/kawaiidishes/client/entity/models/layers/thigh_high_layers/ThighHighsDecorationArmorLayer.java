package com.hakimen.kawaiidishes.client.entity.models.layers.thigh_high_layers;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.layers.GeoArmorLayer;
import com.hakimen.kawaiidishes.custom.Registries;
import com.hakimen.kawaiidishes.item.armor.ThighHighsArmorItem;
import com.hakimen.kawaiidishes.utils.ColorUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

import java.util.List;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class ThighHighsDecorationArmorLayer extends GeoArmorLayer<ThighHighsArmorItem> {

    ItemStack stackData;

    public void updateStack(ItemStack stack){
        stackData = stack;
    }

    @Override
    public ResourceLocation getTexture() {
        return getResourceLocationFromStack(stackData);
    }

    public ThighHighsDecorationArmorLayer(GeoRenderer<ThighHighsArmorItem> entityRendererIn, ItemStack stack) {
        super(entityRendererIn, getResourceLocationFromStack(stack));
        this.stackData = stack;
    }

    private static ResourceLocation getResourceLocationFromStack(ItemStack stack) {

        if(stack.getOrCreateTag().contains("Decoration")){
            return Registries.THIGH_HIGH_DECORATIONS.getHolder(stack.getOrCreateTag().getInt("Decoration")).get().value().getTexture();
        }
        return new ResourceLocation(KawaiiDishes.MODID, "textures/models/armor/none.png");
    }

    @Override
    public void preRender(PoseStack poseStack, ThighHighsArmorItem animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        super.preRender(poseStack, animatable, bakedModel, renderType, bufferSource, buffer, partialTick, packedLight, packedOverlay);
    }

    @Override
    public void render(PoseStack poseStack, ThighHighsArmorItem animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType armorRenderType = RenderType.armorCutoutNoCull(getTexture());

        float[] rgb = ColorUtils.getColorsFromHex(animatable.getOverlayColor(stackData));

        getRenderer().reRender(getDefaultBakedModel(animatable),poseStack,bufferSource,animatable,armorRenderType,bufferSource.getBuffer(armorRenderType),partialTick,packedLight, OverlayTexture.NO_OVERLAY,rgb[0],rgb[1],rgb[2],1);
    }


}
