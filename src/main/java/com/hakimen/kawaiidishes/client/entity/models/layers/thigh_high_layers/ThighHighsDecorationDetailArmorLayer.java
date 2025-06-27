package com.hakimen.kawaiidishes.client.entity.models.layers.thigh_high_layers;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.layers.GeoArmorLayer;
import com.hakimen.kawaiidishes.custom.Registries;
import com.hakimen.kawaiidishes.custom.types.ThighHighDecoration;
import com.hakimen.kawaiidishes.item.armor.ThighHighsArmorItem;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

import java.util.List;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ThighHighsDecorationDetailArmorLayer extends GeoArmorLayer<ThighHighsArmorItem> {

    ItemStack stackData;

    public void updateStack(ItemStack stack){
        stackData = stack;
    }

    @Override
    public Identifier getTexture() {
        return getResourceLocationFromStack(stackData);
    }

    public ThighHighsDecorationDetailArmorLayer(GeoRenderer<ThighHighsArmorItem> entityRendererIn, ItemStack stack) {
        super(entityRendererIn, getResourceLocationFromStack(stack));
        this.stackData = stack;
    }

    private static Identifier getResourceLocationFromStack(ItemStack stack) {
        ThighHighDecoration decor = Registries.THIGH_HIGH_DECORATIONS.getEntry(stack.getOrCreateNbt().getInt("Decoration")).get().value();
        if(decor.getExtraTexture() != null){
            return decor.getExtraTexture();
        }
        return new Identifier(KawaiiDishes.MODID, "textures/models/armor/none.png");
    }

    @Override
    public void preRender(MatrixStack poseStack, ThighHighsArmorItem animatable, BakedGeoModel bakedModel, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        super.preRender(poseStack, animatable, bakedModel, renderType, bufferSource, buffer, partialTick, packedLight, packedOverlay);
    }

    @Override
    public void render(MatrixStack poseStack, ThighHighsArmorItem animatable, BakedGeoModel bakedModel, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderLayer armorRenderType = RenderLayer.getArmorCutoutNoCull(getTexture());

        getRenderer().reRender(getDefaultBakedModel(animatable),poseStack,bufferSource,animatable,armorRenderType,bufferSource.getBuffer(armorRenderType),partialTick,packedLight, OverlayTexture.DEFAULT_UV,1,1,1,1);
    }


}
