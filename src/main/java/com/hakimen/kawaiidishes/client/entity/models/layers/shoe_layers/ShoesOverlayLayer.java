package com.hakimen.kawaiidishes.client.entity.models.layers.shoe_layers;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.layers.GeoArmorLayer;
import com.hakimen.kawaiidishes.item.armor.ShoesArmorItem;
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

public class ShoesOverlayLayer extends GeoArmorLayer<ShoesArmorItem> {

    ItemStack stackData;
    public void updateStack(ItemStack stack){
        stackData = stack;
    }

    public ShoesOverlayLayer(GeoRenderer<ShoesArmorItem> entityRendererIn) {
        super(entityRendererIn, new ResourceLocation(KawaiiDishes.MODID, "textures/models/armor/shoes/shoes_overlay.png"));
    }

    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation[]{
                new ResourceLocation(KawaiiDishes.MODID, "textures/models/armor/none.png"),
                new ResourceLocation(KawaiiDishes.MODID, "textures/models/armor/shoes/shoes_overlay.png")
        }[((ShoesArmorItem)stackData.getItem()).hasOverlay(stackData) ? 1 : 0];
    }

    @Override
    public void render(PoseStack poseStack, ShoesArmorItem animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType armorRenderType = RenderType.armorCutoutNoCull(getTexture());

        float[] rgb = ColorUtils.getColorsFromHex(animatable.getOverlayColor(stackData));

        getRenderer().reRender(getDefaultBakedModel(animatable),poseStack,bufferSource,animatable,armorRenderType,bufferSource.getBuffer(armorRenderType),partialTick,packedLight, OverlayTexture.NO_OVERLAY,rgb[0],rgb[1],rgb[2],1);

    }
}
