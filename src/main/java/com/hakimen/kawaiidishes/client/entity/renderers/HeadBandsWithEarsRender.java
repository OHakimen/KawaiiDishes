package com.hakimen.kawaiidishes.client.entity.renderers;

import com.hakimen.kawaiidishes.client.entity.models.layers.head_bands_with_ears_layers.EarsBaseLayer;
import com.hakimen.kawaiidishes.client.entity.models.layers.head_bands_with_ears_layers.EarsOverlayLayer;
import com.hakimen.kawaiidishes.client.entity.models.layers.head_bands_with_ears_layers.EarsSkinLayer;
import com.hakimen.kawaiidishes.client.entity.models.layers.head_bands_with_ears_layers.HeadBandOverlayLayer;
import com.hakimen.kawaiidishes.item.armor.HeadBandWithEarsArmorItem;
import com.hakimen.kawaiidishes.registry.DataComponentRegister;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.util.Color;

import java.lang.ref.WeakReference;

public class HeadBandsWithEarsRender extends GeoArmorItemRenderer<HeadBandWithEarsArmorItem>{

    private WeakReference<ItemStack> stackData;

    public void updateStack(ItemStack stack) {
        this.stackData = new WeakReference<>(stack);
    }

    public ItemStack getStack() {
        return this.stackData.get();
    }
    public HeadBandsWithEarsRender(GeoModel<HeadBandWithEarsArmorItem> model, ItemStack stack) {
        super(model);
        updateStack(stack);

        addRenderLayer(new HeadBandOverlayLayer(this,stack));
        addRenderLayer(new EarsBaseLayer(this));
        addRenderLayer(new EarsOverlayLayer(this));
        addRenderLayer(new EarsSkinLayer(this));

        ((HeadBandOverlayLayer)getRenderLayers().get(0)).updateStack(stack);
        ((EarsBaseLayer)getRenderLayers().get(1)).updateStack(stack);
        ((EarsOverlayLayer)getRenderLayers().get(2)).updateStack(stack);
        ((EarsSkinLayer)getRenderLayers().get(3)).updateStack(stack);
    }

    @Override
    public ResourceLocation getTextureLocation(HeadBandWithEarsArmorItem animatable) {
        return super.getTextureLocation(animatable);
    }

    @Override
    public void prepForRender(@Nullable Entity entity, ItemStack stack, @Nullable EquipmentSlot slot, @Nullable HumanoidModel<?> baseModel) {
        updateStack(stack);

        ((HeadBandOverlayLayer)getRenderLayers().get(0)).updateStack(stack);
        ((EarsBaseLayer)getRenderLayers().get(1)).updateStack(stack);
        ((EarsOverlayLayer)getRenderLayers().get(2)).updateStack(stack);
        ((EarsSkinLayer)getRenderLayers().get(3)).updateStack(stack);

        super.prepForRender(entity, stack, slot, baseModel);
    }

    @Override
    public Color getRenderColor(HeadBandWithEarsArmorItem animatable, float partialTick, int packedLight) {
        return Color.ofOpaque(getStack().get(DataComponentRegister.DYEABLE.get()).getBase());
    }
}
