package com.hakimen.kawaiidishes.client.entity.models.layers.ears_layers;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.layers.GeoArmorLayer;
import com.hakimen.kawaiidishes.item.armor.EarsArmorItem;
import com.hakimen.kawaiidishes.utils.ColorUtils;
import com.hakimen.kawaiidishes.utils.EarUtils;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class EarsLayer extends GeoArmorLayer<EarsArmorItem> {
    ItemStack stackData;
    public void updateStack(ItemStack stack){
        this.stackData = stack;
    }
    public EarsLayer(GeoRenderer<EarsArmorItem> entityRendererIn) {
        super(entityRendererIn, new Identifier(KawaiiDishes.MODID, "textures/models/armor/none.png"));
    }

    @Override
    public Identifier getTexture() {
        return new Identifier[]{
                EarUtils.getEarOverlayTextures().get(((EarsArmorItem)stackData.getItem()).getEarsType()),
                new Identifier(KawaiiDishes.MODID, "textures/models/armor/none.png")
        }[((EarsArmorItem)stackData.getItem()).hasOverlay(stackData) ? 0 : 1];
    }

    @Override
    public void render(MatrixStack poseStack, EarsArmorItem animatable, BakedGeoModel bakedModel, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderLayer armorRenderType = RenderLayer.getArmorCutoutNoCull(getTexture());

        float[] rgb = ColorUtils.getColorsFromHex(animatable.getOverlayColor(stackData));

        getRenderer().reRender(getDefaultBakedModel(animatable),poseStack,bufferSource,animatable,armorRenderType,bufferSource.getBuffer(armorRenderType),partialTick,packedLight, OverlayTexture.DEFAULT_UV,rgb[0],rgb[1],rgb[2],1);
    }
}
