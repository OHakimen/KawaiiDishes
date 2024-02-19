package com.hakimen.kawaiidishes.client.entity.models.layers.thigh_high_layers;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.layers.GeoArmorLayer;
import com.hakimen.kawaiidishes.item.armor.ThighHighsArmorItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

import java.util.List;

public class ThighHighsDecorationDetailArmorLayer extends GeoArmorLayer<ThighHighsArmorItem> {

    ItemStack stackData;

    public void updateStack(ItemStack stack){
        stackData = stack;
    }

    @Override
    public ResourceLocation getTexture() {
        return getResourceLocationFromStack(stackData);
    }

    static List<ResourceLocation> decorations = List.of(
            new ResourceLocation(KawaiiDishes.MODID,"textures/models/armor/thigh_highs/leg_clip_clip.png")
    );
    public ThighHighsDecorationDetailArmorLayer(GeoRenderer<ThighHighsArmorItem> entityRendererIn, ItemStack stack) {
        super(entityRendererIn, getResourceLocationFromStack(stack));
        this.stackData = stack;
    }

    private static ResourceLocation getResourceLocationFromStack(ItemStack stack) {
        switch (stack.getOrCreateTag().getInt("Decoration")-1){
            case 2 -> {
                return decorations.get(0);
            }
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

        getRenderer().reRender(getDefaultBakedModel(animatable),poseStack,bufferSource,animatable,armorRenderType,bufferSource.getBuffer(armorRenderType),partialTick,packedLight, OverlayTexture.NO_OVERLAY,1,1,1,1);
    }


}
