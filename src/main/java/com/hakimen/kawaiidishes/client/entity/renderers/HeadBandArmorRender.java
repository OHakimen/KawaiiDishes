package com.hakimen.kawaiidishes.client.entity.renderers;

import com.hakimen.kawaiidishes.client.entity.models.layers.head_band_layers.HeadBandRibbonLayer;
import com.hakimen.kawaiidishes.item.armor.HeadBandArmorItem;
import com.hakimen.kawaiidishes.registry.DataComponentRegister;
import com.hakimen.kawaiidishes.utils.ColorUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.util.Color;

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
        return Color.ofOpaque(stackData.get(DataComponentRegister.DYEABLE).getBase());
    }
}
