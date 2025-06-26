package com.hakimen.kawaiidishes.client.entity.models.layers.maid_dresses_with_tail_layers;

import com.hakimen.kawaiidishes.KawaiiDishes;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class SecondaryTailLayer extends GeoArmorLayer<MaidDressesWithTailArmorItem> {
    ItemStack stackData;

    AnimalType tail;

    public SecondaryTailLayer(GeoRenderer<MaidDressesWithTailArmorItem> entityRendererIn, AnimalType tail) {
        super(entityRendererIn, new ResourceLocation(KawaiiDishes.MODID, "textures/models/armor/none.png"));
        this.tail = tail;
    }

    public void updateStack(ItemStack stack) {
        this.stackData = stack;
    }

    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation[]{
                MaidDressesWithTailUtils.getTailedDressesTailOverlay().get(tail),
                new ResourceLocation(KawaiiDishes.MODID, "textures/models/armor/none.png")
        }[((MaidDressesWithTailArmorItem)stackData.getItem()).hasSecondaryOverlay(stackData) ? 0 : 1];
    }

    @Override
    public void render(PoseStack poseStack, MaidDressesWithTailArmorItem animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType armorRenderType = RenderType.armorCutoutNoCull(getTexture());

        float[] rgb = ColorUtils.getColorsFromHex(animatable.getSecondaryOverlayColor(stackData));

        getRenderer().reRender(getDefaultBakedModel(animatable), poseStack, bufferSource, animatable, armorRenderType, bufferSource.getBuffer(armorRenderType), partialTick, packedLight, OverlayTexture.NO_OVERLAY, rgb[0], rgb[1], rgb[2], 1);
    }
}
