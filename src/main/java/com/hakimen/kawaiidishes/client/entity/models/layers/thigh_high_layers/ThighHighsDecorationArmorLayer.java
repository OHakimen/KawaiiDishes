package com.hakimen.kawaiidishes.client.entity.models.layers.thigh_high_layers;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.layers.GeoArmorLayer;
import com.hakimen.kawaiidishes.item.armor.ThighHighsArmorItem;
import com.hakimen.kawaiidishes.utils.ColorUtils;
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

public class ThighHighsDecorationArmorLayer extends GeoArmorLayer<ThighHighsArmorItem> {

    ItemStack stackData;

    public void updateStack(ItemStack stack){
        stackData = stack;
    }

    @Override
    public ResourceLocation getTexture() {
        return getResourceLocationFromStack(stackData);
    }

    static List<ResourceLocation> decorations = List.of(
            new ResourceLocation(KawaiiDishes.MODID,"textures/models/armor/thigh_highs/double_band.png"),
            new ResourceLocation(KawaiiDishes.MODID,"textures/models/armor/thigh_highs/full_band.png"),
            new ResourceLocation(KawaiiDishes.MODID,"textures/models/armor/thigh_highs/leg_clip.png"),
            new ResourceLocation(KawaiiDishes.MODID,"textures/models/armor/thigh_highs/bow.png")
    );
    public ThighHighsDecorationArmorLayer(GeoRenderer<ThighHighsArmorItem> entityRendererIn, ItemStack stack) {
        super(entityRendererIn, getResourceLocationFromStack(stack));
        this.stackData = stack;
    }

    private static ResourceLocation getResourceLocationFromStack(ItemStack stack) {
        if(stack.getOrCreateTag().contains("Decoration") && stack.getOrCreateTag().getInt("Decoration") > 0 && stack.getOrCreateTag().getInt("Decoration")-1 < decorations.size()){
            return decorations.get(stack.getOrCreateTag().getInt("Decoration")-1);
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
