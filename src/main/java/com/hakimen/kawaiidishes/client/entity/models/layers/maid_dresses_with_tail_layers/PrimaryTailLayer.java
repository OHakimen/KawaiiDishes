package com.hakimen.kawaiidishes.client.entity.models.layers.maid_dresses_with_tail_layers;

import com.hakimen.kawaiidishes.client.entity.models.layers.GeoArmorLayer;
import com.hakimen.kawaiidishes.item.armor.MaidDressesWithTailArmorItem;
import com.hakimen.kawaiidishes.utils.AnimalType;
import com.hakimen.kawaiidishes.utils.ColorUtils;
import com.hakimen.kawaiidishes.utils.MaidDressesWithTailUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class PrimaryTailLayer extends GeoArmorLayer<MaidDressesWithTailArmorItem> {
    ItemStack stackData;

    AnimalType tail;

    public PrimaryTailLayer(GeoRenderer<MaidDressesWithTailArmorItem> entityRendererIn, AnimalType tail) {
        super(entityRendererIn, MaidDressesWithTailUtils.getTailedDressesTailBase().get(tail));
        this.tail = tail;
    }

    public void updateStack(ItemStack stack) {
        this.stackData = stack;
    }

    @Override
    public void render(PoseStack poseStack, MaidDressesWithTailArmorItem animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType armorRenderType = RenderType.armorCutoutNoCull(getTexture());

        float[] rgb = ColorUtils.getColorsFromHex(animatable.getSecondaryBaseColor(stackData));

        getRenderer().reRender(getDefaultBakedModel(animatable), poseStack, bufferSource, animatable, armorRenderType, bufferSource.getBuffer(armorRenderType), partialTick, packedLight, OverlayTexture.NO_OVERLAY, rgb[0], rgb[1], rgb[2], 1);
    }
}
