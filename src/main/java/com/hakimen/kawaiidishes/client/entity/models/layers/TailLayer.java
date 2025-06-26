package com.hakimen.kawaiidishes.client.entity.models.layers;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.item.armor.TailArmorItem;
import com.hakimen.kawaiidishes.utils.AnimalType;
import com.hakimen.kawaiidishes.utils.ColorUtils;
import com.hakimen.kawaiidishes.utils.TailUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class TailLayer extends GeoArmorLayer<TailArmorItem> {
    ItemStack stackData;

    AnimalType tail;

    public TailLayer(GeoRenderer<TailArmorItem> entityRendererIn) {
        super(entityRendererIn, new ResourceLocation(KawaiiDishes.MODID, "textures/models/armor/none.png"));
    }

    public void updateStack(ItemStack stack) {
        this.stackData = stack;
    }

    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation[]{
                TailUtils.getTailOverlayTextures().get(((TailArmorItem)stackData.getItem()).getTailType()),
                new ResourceLocation(KawaiiDishes.MODID, "textures/models/armor/none.png")
        }[((TailArmorItem)stackData.getItem()).hasOverlay(stackData) ? 0 : 1];
    }

    @Override
    public void render(PoseStack poseStack, TailArmorItem animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType armorRenderType = RenderType.armorCutoutNoCull(getTexture());

        float[] rgb = ColorUtils.getColorsFromHex(animatable.getOverlayColor(stackData));

        getRenderer().reRender(getDefaultBakedModel(animatable), poseStack, bufferSource, animatable, armorRenderType, bufferSource.getBuffer(armorRenderType), partialTick, packedLight, OverlayTexture.NO_OVERLAY, rgb[0], rgb[1], rgb[2], 1);
    }
}
