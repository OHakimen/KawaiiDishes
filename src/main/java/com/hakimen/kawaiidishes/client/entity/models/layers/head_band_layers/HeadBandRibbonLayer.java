package com.hakimen.kawaiidishes.client.entity.models.layers.head_band_layers;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.layers.GeoArmorLayer;
import com.hakimen.kawaiidishes.item.armor.HeadBandArmorItem;
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

public class HeadBandRibbonLayer extends GeoArmorLayer<HeadBandArmorItem> {
    ItemStack stackData;

    public void updateStack(ItemStack stack){
        this.stackData = stack;
    }
    public HeadBandRibbonLayer(GeoRenderer<HeadBandArmorItem> entityRendererIn) {
        super(entityRendererIn, new ResourceLocation(KawaiiDishes.MODID,"textures/models/armor/head_band/head_band_overlay.png"));
    }

    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation[]{
                new ResourceLocation(KawaiiDishes.MODID,"textures/models/armor/none.png"),
                new ResourceLocation(KawaiiDishes.MODID,"textures/models/armor/head_band/head_band_overlay.png")
        }[stackData.getOrCreateTag().contains("HasOverlay") ? (stackData.getOrCreateTag().getBoolean("HasOverlay") ? 1 : 0): 0];
    }

    @Override
    public void render(PoseStack poseStack, HeadBandArmorItem animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType armorRenderType = RenderType.armorCutoutNoCull(getTexture());

        float[] rgb = ColorUtils.getColorsFromHex(animatable.getOverlayColor(stackData));

        getRenderer().reRender(getDefaultBakedModel(animatable),poseStack,bufferSource,animatable,armorRenderType,bufferSource.getBuffer(armorRenderType),partialTick,packedLight, OverlayTexture.NO_OVERLAY,rgb[0],rgb[1],rgb[2],1);
    }
}
