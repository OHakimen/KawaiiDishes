package com.hakimen.kawaiidishes.client.entity.renderers;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.layers.maid_dresses_with_tail_layers.MaidDressOverlayLayer;
import com.hakimen.kawaiidishes.client.entity.models.layers.maid_dresses_with_tail_layers.PrimaryTailLayer;
import com.hakimen.kawaiidishes.client.entity.models.layers.maid_dresses_with_tail_layers.SecondaryTailLayer;
import com.hakimen.kawaiidishes.client.util.EarsSupport;
import com.hakimen.kawaiidishes.item.armor.MaidDressesWithTailArmorItem;
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

import java.lang.ref.WeakReference;

public class MaidDressesWithTailArmorRender extends GeoArmorItemRenderer<MaidDressesWithTailArmorItem> {

    private WeakReference<ItemStack> stackData;

    float chestAngle;

    public void updateStack(ItemStack stack) {
        this.stackData = new WeakReference<>(stack);
    }

    public ItemStack getStack() {
        return this.stackData.get();
    }

    public MaidDressesWithTailArmorRender(GeoModel<MaidDressesWithTailArmorItem> model, ItemStack stack) {
        super(model);
        this.updateStack(stack);

        addRenderLayer(new MaidDressOverlayLayer(this));
        addRenderLayer(new PrimaryTailLayer(this, ((MaidDressesWithTailArmorItem) stack.getItem()).getTailType()));
        addRenderLayer(new SecondaryTailLayer(this,((MaidDressesWithTailArmorItem)stack.getItem()).getTailType()));

        ((MaidDressOverlayLayer)getRenderLayers().get(0)).updateStack(stack);
        ((PrimaryTailLayer)getRenderLayers().get(1)).updateStack(stack);
        ((SecondaryTailLayer)getRenderLayers().get(2)).updateStack(stack);
    }
    @Override
    public ResourceLocation getTextureLocation(MaidDressesWithTailArmorItem animatable) {
        return new ResourceLocation[]{
                ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID,"textures/models/armor/maid_dresses_with_tail/dress/%s/dress.png".formatted(animatable.getTailType().name().toLowerCase())),
                ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID,"textures/models/armor/maid_dresses_with_tail/dress/%s/maid_dress.png".formatted(animatable.getTailType().name().toLowerCase()))
        }[getStack().get(DataComponentRegister.DYEABLE.get()).isHasOverlay() ? 1 : 0];
    }

    @Override
    public void prepForRender(@Nullable Entity entity, ItemStack stack, @Nullable EquipmentSlot slot, @Nullable HumanoidModel<?> baseModel) {
        updateStack(stack);

        ((MaidDressOverlayLayer) getRenderLayers().get(0)).updateStack(stack);
        ((PrimaryTailLayer) getRenderLayers().get(1)).updateStack(stack);
        ((SecondaryTailLayer) getRenderLayers().get(2)).updateStack(stack);

        this.chestAngle = EarsSupport.getChestSize(entity);

        super.prepForRender(entity, stack, slot, baseModel);
    }

    @Override
    public void preRender(final PoseStack poseStack, final MaidDressesWithTailArmorItem animatable, final BakedGeoModel model, @Nullable final MultiBufferSource bufferSource, @Nullable final VertexConsumer buffer, final boolean isReRender, final float partialTick, final int packedLight, final int packedOverlay, final int colour) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);

        model.getBone("armorChest").orElseThrow().updateRotation(Mth.DEG_TO_RAD * -90f + chestAngle, 0, 0);
    }

    @Override
    public Color getRenderColor(MaidDressesWithTailArmorItem animatable, float partialTick, int packedLight) {
        return Color.ofOpaque(getStack().get(DataComponentRegister.DYEABLE.get()).getBase());
    }
}
