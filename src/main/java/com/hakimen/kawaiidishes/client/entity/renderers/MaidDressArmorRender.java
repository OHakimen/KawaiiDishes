package com.hakimen.kawaiidishes.client.entity.renderers;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.layers.maid_dress_layers.MaidDressOverlayLayer;
import com.hakimen.kawaiidishes.item.armor.MaidDressArmorItem;
import com.hakimen.kawaiidishes.utils.ColorUtils;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.object.Color;
import software.bernie.geckolib.model.GeoModel;

public class MaidDressArmorRender extends GeoArmorItemRenderer<MaidDressArmorItem>{

    ItemStack stackData;

    public void updateStack(ItemStack stack){
        this.stackData = stack;
    }
    public MaidDressArmorRender(GeoModel<MaidDressArmorItem> model, ItemStack stack) {
        super(model);
        this.stackData = stack;

        addRenderLayer(new MaidDressOverlayLayer(this));
        ((MaidDressOverlayLayer)getRenderLayers().get(0)).updateStack(stack);
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

        ((MaidDressOverlayLayer)getRenderLayers().get(0)).updateStack(stack);

        super.prepForRender(entity, stack, slot, baseModel);
    }

    @Override
    public Color getRenderColor(MaidDressArmorItem animatable, float partialTick, int packedLight) {
        float[] rgb = ColorUtils.getColorsFromHex(animatable.getBaseColor(stackData));
        return Color.ofRGB(rgb[0],rgb[1],rgb[2]);
    }
}
