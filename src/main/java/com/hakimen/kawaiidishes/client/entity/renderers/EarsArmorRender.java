package com.hakimen.kawaiidishes.client.entity.renderers;

import com.hakimen.kawaiidishes.client.entity.models.layers.ears_layers.EarSkinLayer;
import com.hakimen.kawaiidishes.client.entity.models.layers.ears_layers.EarsLayer;
import com.hakimen.kawaiidishes.item.armor.EarsArmorItem;
import com.hakimen.kawaiidishes.utils.ColorUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.object.Color;
import software.bernie.geckolib.model.GeoModel;

public class EarsArmorRender extends GeoArmorItemRenderer<EarsArmorItem> {
    ItemStack stackData;
    public void updateStack(ItemStack stack){
        this.stackData = stack;
    }
    public EarsArmorRender(GeoModel<EarsArmorItem> model) {
        super(model);

        addRenderLayer(new EarsLayer(this));
        addRenderLayer(new EarSkinLayer(this));
    }

    @Override
    public void prepForRender(@Nullable Entity entity, ItemStack stack, @Nullable EquipmentSlot slot, @Nullable HumanoidModel<?> baseModel) {
        updateStack(stack);

        ((EarsLayer)getRenderLayers().get(0)).updateStack(stack);
        ((EarSkinLayer)getRenderLayers().get(1)).updateStack(stack);

        super.prepForRender(entity, stack, slot, baseModel);
    }

    @Override
    public Color getRenderColor(EarsArmorItem animatable, float partialTick, int packedLight) {
        float[] rgb = ColorUtils.getColorsFromHex(animatable.getBaseColor(stackData));
        return Color.ofRGB(rgb[0],rgb[1],rgb[2]);
    }
}
