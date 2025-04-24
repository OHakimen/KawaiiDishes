package com.hakimen.kawaiidishes.client.entity.renderers;

import com.hakimen.kawaiidishes.client.entity.models.layers.thigh_high_layers.ThighHighsDecorationArmorLayer;
import com.hakimen.kawaiidishes.client.entity.models.layers.thigh_high_layers.ThighHighsDecorationDetailArmorLayer;
import com.hakimen.kawaiidishes.item.armor.ThighHighsArmorItem;
import com.hakimen.kawaiidishes.registry.DataComponentRegister;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.util.Color;

import java.lang.ref.WeakReference;

public class ThighHighsArmorRender extends GeoArmorItemRenderer<ThighHighsArmorItem> {

    private WeakReference<ItemStack> stackData;

    public void updateStack(ItemStack stack) {
        this.stackData = new WeakReference<>(stack);
    }

    public ItemStack getStack() {
        return this.stackData.get();
    }

    public ThighHighsArmorRender(GeoModel<ThighHighsArmorItem> model, ItemStack stack) {
        super(model);
        updateStack(stack);
        addRenderLayer(new ThighHighsDecorationArmorLayer(this, stack));
        addRenderLayer(new ThighHighsDecorationDetailArmorLayer(this, stack));
    }

    @Override
    public void prepForRender(@Nullable Entity entity, ItemStack stack, @Nullable EquipmentSlot slot, @Nullable HumanoidModel<?> baseModel) {
        updateStack(stack);
        ((ThighHighsDecorationArmorLayer)getRenderLayers().get(0)).updateStack(stack);
        ((ThighHighsDecorationDetailArmorLayer)getRenderLayers().get(1)).updateStack(stack);
        super.prepForRender(entity, stack, slot, baseModel);
    }

    @Override
    public Color getRenderColor(ThighHighsArmorItem animatable, float partialTick, int packedLight) {
        return Color.ofOpaque(getStack().get(DataComponentRegister.DYEABLE.get()).getBase());
    }
}
