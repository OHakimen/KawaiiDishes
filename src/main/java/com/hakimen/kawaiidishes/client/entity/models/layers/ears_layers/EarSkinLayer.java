package com.hakimen.kawaiidishes.client.entity.models.layers.ears_layers;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.layers.GeoArmorLayer;
import com.hakimen.kawaiidishes.item.armor.EarsArmorItem;
import com.hakimen.kawaiidishes.utils.EarUtils;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import mod.azure.azurelib.cache.object.BakedGeoModel;
import mod.azure.azurelib.renderer.GeoRenderer;

public class EarSkinLayer extends GeoArmorLayer<EarsArmorItem> {
    ItemStack stackData;

    public void updateStack(ItemStack stack){
        this.stackData = stack;
    }
    public EarSkinLayer(GeoRenderer<EarsArmorItem> entityRendererIn) {
        super(entityRendererIn, new Identifier(KawaiiDishes.MODID, "textures/models/armor/none.png"));
    }

    @Override
    public Identifier getTexture() {
        return EarUtils.getEarSkinTexture().get(((EarsArmorItem)stackData.getItem()).getEarsType());
    }

    @Override
    public void render(MatrixStack poseStack, EarsArmorItem animatable, BakedGeoModel bakedModel, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderLayer armorRenderType = RenderLayer.getArmorCutoutNoCull(getTexture());

        getRenderer().reRender(getDefaultBakedModel(animatable),poseStack,bufferSource,animatable,armorRenderType,bufferSource.getBuffer(armorRenderType),partialTick,packedLight, OverlayTexture.DEFAULT_UV,1,1,1,1);
    }
}
