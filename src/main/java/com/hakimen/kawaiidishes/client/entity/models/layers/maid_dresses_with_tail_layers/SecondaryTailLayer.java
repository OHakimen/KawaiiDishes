package com.hakimen.kawaiidishes.client.entity.models.layers.maid_dresses_with_tail_layers;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.layers.GeoArmorLayer;
import com.hakimen.kawaiidishes.item.armor.MaidDressesWithTailArmorItem;
import com.hakimen.kawaiidishes.utils.AnimalType;
import com.hakimen.kawaiidishes.utils.ColorUtils;
import com.hakimen.kawaiidishes.utils.MaidDressesWithTailUtils;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class SecondaryTailLayer extends GeoArmorLayer<MaidDressesWithTailArmorItem> {
    ItemStack stackData;

    AnimalType tail;

    public SecondaryTailLayer(GeoRenderer<MaidDressesWithTailArmorItem> entityRendererIn, AnimalType tail) {
        super(entityRendererIn, new Identifier(KawaiiDishes.MODID, "textures/models/armor/none.png"));
        this.tail = tail;
    }

    public void updateStack(ItemStack stack) {
        this.stackData = stack;
    }

    @Override
    public Identifier getTexture() {
        return new Identifier[]{
                MaidDressesWithTailUtils.getTailedDressesTailOverlay().get(tail),
                new Identifier(KawaiiDishes.MODID, "textures/models/armor/none.png")
        }[((MaidDressesWithTailArmorItem)stackData.getItem()).hasSecondaryOverlay(stackData) ? 0 : 1];
    }

    @Override
    public void render(MatrixStack poseStack, MaidDressesWithTailArmorItem animatable, BakedGeoModel bakedModel, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderLayer armorRenderType = RenderLayer.getArmorCutoutNoCull(getTexture());

        float[] rgb = ColorUtils.getColorsFromHex(animatable.getSecondaryOverlayColor(stackData));

        getRenderer().reRender(getDefaultBakedModel(animatable), poseStack, bufferSource, animatable, armorRenderType, bufferSource.getBuffer(armorRenderType), partialTick, packedLight, OverlayTexture.DEFAULT_UV, rgb[0], rgb[1], rgb[2], 1);
    }
}
