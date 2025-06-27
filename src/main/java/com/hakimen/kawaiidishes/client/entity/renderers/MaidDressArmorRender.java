package com.hakimen.kawaiidishes.client.entity.renderers;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.layers.maid_dress_layers.MaidDressOverlayLayer;
import com.hakimen.kawaiidishes.client.util.EarsSupport;
import com.hakimen.kawaiidishes.item.armor.MaidDressArmorItem;
import com.hakimen.kawaiidishes.utils.ColorUtils;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;
import mod.azure.azurelib.cache.object.BakedGeoModel;
import mod.azure.azurelib.core.object.Color;
import mod.azure.azurelib.model.GeoModel;

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
    public Identifier getTextureLocation(MaidDressArmorItem animatable) {
        return new Identifier[]{
                new Identifier(KawaiiDishes.MODID,"textures/models/armor/maid_dress/dress.png"),
                new Identifier(KawaiiDishes.MODID,"textures/models/armor/maid_dress/maid_dress.png")
        }[animatable.hasOverlay(stackData) ? 1 : 0];
    }

    @Override
    public void prepForRender(@Nullable Entity entity, ItemStack stack, @Nullable EquipmentSlot slot, @Nullable BipedEntityModel<?> baseModel) {
        updateStack(stack);

        ((MaidDressOverlayLayer) getRenderLayers().get(0)).updateStack(stack);

        this.chestAngle = EarsSupport.getChestSize(entity);

        super.prepForRender(entity, stack, slot, baseModel);
    }

    @Override
    public void preRender(MatrixStack poseStack, MaidDressArmorItem animatable, BakedGeoModel model, VertexConsumerProvider bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
        model.getBone("armorChest").orElseThrow().updateRotation(MathHelper.RADIANS_PER_DEGREE * -90f + chestAngle, 0, 0);
    }

    @Override
    public Color getRenderColor(MaidDressArmorItem animatable, float partialTick, int packedLight) {
        float[] rgb = ColorUtils.getColorsFromHex(animatable.getBaseColor(stackData));
        return Color.ofRGB(rgb[0],rgb[1],rgb[2]);
    }
}
