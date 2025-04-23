package com.hakimen.kawaiidishes.client.entity.renderers;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.layers.maid_dress_layers.MaidDressOverlayLayer;
import com.hakimen.kawaiidishes.client.util.EarsSupport;
import com.hakimen.kawaiidishes.item.armor.MaidDressArmorItem;
import com.hakimen.kawaiidishes.registry.DataComponentRegister;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.util.Color;

public class MaidDressArmorRender extends GeoArmorItemRenderer<MaidDressArmorItem> {

    ItemStack stackData;

    float chestAngle;

    public void updateStack(ItemStack stack) {
        this.stackData = stack;
    }

    public MaidDressArmorRender(GeoModel<MaidDressArmorItem> model, ItemStack stack) {
        super(model);
        this.stackData = stack;

        addRenderLayer(new MaidDressOverlayLayer(this));
        ((MaidDressOverlayLayer) getRenderLayers().get(0)).updateStack(stack);
    }
    @Override
    public ResourceLocation getTextureLocation(MaidDressArmorItem animatable) {
        return new ResourceLocation[]{
                ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID,"textures/models/armor/maid_dress/dress.png"),
                ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID,"textures/models/armor/maid_dress/maid_dress.png")
        }[stackData.get(DataComponentRegister.DYEABLE.get()).isHasOverlay() ? 1 : 0];
    }

    @Override
    public void prepForRender(@Nullable Entity entity, ItemStack stack, @Nullable EquipmentSlot slot, @Nullable HumanoidModel<?> baseModel) {
        updateStack(stack);

        ((MaidDressOverlayLayer) getRenderLayers().get(0)).updateStack(stack);

        this.chestAngle = EarsSupport.getChestSize(entity);

        super.prepForRender(entity, stack, slot, baseModel);
    }

    @Override
    public void preRender(final PoseStack poseStack, final MaidDressArmorItem animatable, final BakedGeoModel model, @Nullable final MultiBufferSource bufferSource, @Nullable final VertexConsumer buffer, final boolean isReRender, final float partialTick, final int packedLight, final int packedOverlay, final int colour) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);

        model.getBone("armorChest").orElseThrow().updateRotation(Mth.DEG_TO_RAD * -90f + chestAngle, 0, 0);
    }

    @Override
    public void postRender(final PoseStack poseStack, final MaidDressArmorItem animatable, final BakedGeoModel model, final MultiBufferSource bufferSource, @Nullable final VertexConsumer buffer, final boolean isReRender, final float partialTick, final int packedLight, final int packedOverlay, final int colour) {
        super.postRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);

        this.updateStack(null);
    }

    @Override
    public Color getRenderColor(MaidDressArmorItem animatable, float partialTick, int packedLight) {
        return Color.ofOpaque(stackData.get(DataComponentRegister.DYEABLE.get()).getBase());
    }
}
