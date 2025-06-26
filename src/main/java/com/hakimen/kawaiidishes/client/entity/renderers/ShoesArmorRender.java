package com.hakimen.kawaiidishes.client.entity.renderers;

import com.hakimen.kawaiidishes.client.entity.models.layers.shoe_layers.ShoesOverlayLayer;
import com.hakimen.kawaiidishes.client.entity.models.layers.shoe_layers.ShoesSolesLayer;
import com.hakimen.kawaiidishes.item.armor.ShoesArmorItem;
import com.hakimen.kawaiidishes.utils.ColorUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.object.Color;
import software.bernie.geckolib.model.GeoModel;

public class ShoesArmorRender extends GeoArmorItemRenderer<ShoesArmorItem> {
    ItemStack stackData;
    public void updateStack(ItemStack stack){
        this.stackData = stack;
    }
    public ShoesArmorRender(GeoModel<ShoesArmorItem> model) {
        super(model);
        addRenderLayer(new ShoesSolesLayer(this));
        addRenderLayer(new ShoesOverlayLayer(this));
    }

    @Override
    public void prepForRender(@Nullable Entity entity, ItemStack stack, @Nullable EquipmentSlot slot, @Nullable HumanoidModel<?> baseModel) {
        updateStack(stack);

        ((ShoesOverlayLayer)getRenderLayers().get(1)).updateStack(stack);

        super.prepForRender(entity, stack, slot, baseModel);
    }

    @Override
    public Color getRenderColor(ShoesArmorItem animatable, float partialTick, int packedLight) {
        float[] rgb = ColorUtils.getColorsFromHex(animatable.getBaseColor(stackData));
        return Color.ofRGB(rgb[0],rgb[1],rgb[2]);
    }
}
