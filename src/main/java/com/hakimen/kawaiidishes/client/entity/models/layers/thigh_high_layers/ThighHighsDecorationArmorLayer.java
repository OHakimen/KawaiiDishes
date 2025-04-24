package com.hakimen.kawaiidishes.client.entity.models.layers.thigh_high_layers;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.layers.GeoArmorLayer;
import com.hakimen.kawaiidishes.item.armor.ThighHighsArmorItem;
import com.hakimen.kawaiidishes.registry.DataComponentRegister;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

import java.lang.ref.WeakReference;
import java.util.List;

public class ThighHighsDecorationArmorLayer extends GeoArmorLayer<ThighHighsArmorItem> {

    private WeakReference<ItemStack> stackData;

    public void updateStack(ItemStack stack) {
        this.stackData = new WeakReference<>(stack);
    }

    public ItemStack getStack() {
        return this.stackData.get();
    }

    @Override
    public ResourceLocation getTexture() {
        return getResourceLocationFromStack(getStack());
    }

    static List<ResourceLocation> decorations = List.of(
            ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID,"textures/models/armor/thigh_highs/double_band.png"),
            ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID,"textures/models/armor/thigh_highs/full_band.png"),
            ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID,"textures/models/armor/thigh_highs/leg_clip.png"),
            ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID,"textures/models/armor/thigh_highs/bow.png")
    );
    public ThighHighsDecorationArmorLayer(GeoRenderer<ThighHighsArmorItem> entityRendererIn, ItemStack stack) {
        super(entityRendererIn, getResourceLocationFromStack(stack));
        this.updateStack(stack);
    }

    private static ResourceLocation getResourceLocationFromStack(ItemStack stack) {
        CompoundTag stackInfo = stack.get(DataComponents.CUSTOM_DATA).copyTag();
        if(stackInfo.contains("Decoration") && stackInfo.getInt("Decoration") > 0 && stackInfo.getInt("Decoration")-1 < decorations.size()){
            return decorations.get(stackInfo.getInt("Decoration")-1);
        }
        return ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "textures/models/armor/none.png");
    }

    @Override
    public void preRender(PoseStack poseStack, ThighHighsArmorItem animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        super.preRender(poseStack, animatable, bakedModel, renderType, bufferSource, buffer, partialTick, packedLight, packedOverlay);
    }

    @Override
    public void render(PoseStack poseStack, ThighHighsArmorItem animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
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
                getStack().get(DataComponentRegister.DYEABLE.get()).getOverlay() | 0xff000000);
    }


}
