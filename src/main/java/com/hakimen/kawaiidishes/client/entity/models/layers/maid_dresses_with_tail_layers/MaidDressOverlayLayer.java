package com.hakimen.kawaiidishes.client.entity.models.layers.maid_dresses_with_tail_layers;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.layers.GeoArmorLayer;
import com.hakimen.kawaiidishes.item.armor.MaidDressesWithTailArmorItem;
import com.hakimen.kawaiidishes.utils.ColorUtils;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import mod.azure.azurelib.cache.object.BakedGeoModel;
import mod.azure.azurelib.renderer.GeoRenderer;

public class MaidDressOverlayLayer extends GeoArmorLayer<MaidDressesWithTailArmorItem> {
    ItemStack stackData;

    public void updateStack(ItemStack stack){
        this.stackData = stack;
    }
    public MaidDressOverlayLayer(GeoRenderer<MaidDressesWithTailArmorItem> entityRendererIn) {
        super(entityRendererIn, new Identifier(KawaiiDishes.MODID,"textures/models/armor/none.png"));
    }

    @Override
    public Identifier getTexture() {
        return new Identifier[]{
                new Identifier(KawaiiDishes.MODID,"textures/models/armor/maid_dresses_with_tail/dress/%s/maid_dress_overlay.png".formatted(((MaidDressesWithTailArmorItem)stackData.getItem()).getTailType().name().toLowerCase())),
                new Identifier(KawaiiDishes.MODID,"textures/models/armor/none.png")
        }[((MaidDressesWithTailArmorItem)stackData.getItem()).hasPrimaryOverlay(stackData) ? 0 : 1];
    }

    @Override
    public void render(MatrixStack poseStack, MaidDressesWithTailArmorItem animatable, BakedGeoModel bakedModel, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderLayer armorRenderType = RenderLayer.getArmorCutoutNoCull(getTexture());

        float[] rgb = ColorUtils.getColorsFromHex(animatable.getPrimaryOverlayColor(stackData));

        getRenderer().reRender(getDefaultBakedModel(animatable),poseStack,bufferSource,animatable,armorRenderType,bufferSource.getBuffer(armorRenderType),partialTick,packedLight, OverlayTexture.DEFAULT_UV,rgb[0],rgb[1],rgb[2],1);

    }
}
