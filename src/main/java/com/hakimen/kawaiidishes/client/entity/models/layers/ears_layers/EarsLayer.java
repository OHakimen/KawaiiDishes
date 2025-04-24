package com.hakimen.kawaiidishes.client.entity.models.layers.ears_layers;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.layers.GeoArmorLayer;
import com.hakimen.kawaiidishes.item.armor.EarsArmorItem;
import com.hakimen.kawaiidishes.registry.DataComponentRegister;
import com.hakimen.kawaiidishes.utils.EarUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

import java.lang.ref.WeakReference;

public class EarsLayer extends GeoArmorLayer<EarsArmorItem> {
    private WeakReference<ItemStack> stackData;

    public void updateStack(ItemStack stack) {
        this.stackData = new WeakReference<>(stack);
    }

    public ItemStack getStack() {
        return this.stackData.get();
    }

    public EarsLayer(GeoRenderer<EarsArmorItem> entityRendererIn) {
        super(entityRendererIn, ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "textures/models/armor/none.png"));
    }

    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation[]{
                ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "textures/models/armor/none.png"),
                EarUtils.getEarOverlayTextures().get(((EarsArmorItem) getStack().getItem()).getEarsType())
        }[getStack().get(DataComponentRegister.DYEABLE.get()).isHasOverlay() ? 1 : 0];
    }

    @Override
    public void render(PoseStack poseStack, EarsArmorItem animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType armorRenderType = RenderType.armorCutoutNoCull(getTexture());
        getRenderer().reRender(
                getDefaultBakedModel(animatable),
                poseStack,
                bufferSource,
                animatable,armorRenderType,
                bufferSource.getBuffer(armorRenderType),
                partialTick,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                getStack().get(DataComponentRegister.DYEABLE.get()).getOverlay());
    }
}
