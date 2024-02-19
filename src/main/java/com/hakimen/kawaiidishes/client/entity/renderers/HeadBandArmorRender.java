package com.hakimen.kawaiidishes.client.entity.renderers;

import com.hakimen.kawaiidishes.client.entity.models.layers.head_band_layers.HeadBandRibbonLayer;
import com.hakimen.kawaiidishes.item.armor.HeadBandArmorItem;
import com.hakimen.kawaiidishes.utils.ColorUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.object.Color;
import software.bernie.geckolib.model.GeoModel;

public class HeadBandArmorRender extends GeoArmorItemRenderer<HeadBandArmorItem> {

    ItemStack stackData;
    public HeadBandArmorRender(GeoModel<HeadBandArmorItem> model, ItemStack stack) {
        super(model);
        this.stackData = stack;
        addRenderLayer(new HeadBandRibbonLayer(this));

        ((HeadBandRibbonLayer)getRenderLayers().get(0)).updateStack(stack);
    }


    public void updateStack(ItemStack stack){
        this.stackData = stack;
    }
    @Override
    public void prepForRender(@Nullable Entity entity, ItemStack stack, @Nullable EquipmentSlot slot, @Nullable HumanoidModel<?> baseModel) {
        updateStack(stack);

        ((HeadBandRibbonLayer)getRenderLayers().get(0)).updateStack(stack);

        super.prepForRender(entity, stack, slot, baseModel);
    }

    @Override
    public Color getRenderColor(HeadBandArmorItem animatable, float partialTick, int packedLight) {
        float[] rgb = ColorUtils.getColorsFromHex(animatable.getBaseColor(stackData));
        return Color.ofRGB(rgb[0],rgb[1],rgb[2]);
    }
}
