package com.hakimen.kawaiidishes.client.entity.renderers;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.layers.maid_dresses_with_tail_layers.MaidDressOverlayLayer;
import com.hakimen.kawaiidishes.client.entity.models.layers.maid_dresses_with_tail_layers.PrimaryTailLayer;
import com.hakimen.kawaiidishes.client.entity.models.layers.maid_dresses_with_tail_layers.SecondaryTailLayer;
import com.hakimen.kawaiidishes.item.armor.MaidDressesWithTailArmorItem;
import com.hakimen.kawaiidishes.utils.ColorUtils;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.object.Color;
import software.bernie.geckolib.model.GeoModel;

public class MaidDressesWithTailArmorRender extends GeoArmorItemRenderer<MaidDressesWithTailArmorItem>{

    ItemStack stackData;

    public void updateStack(ItemStack stack){
        this.stackData = stack;
    }
    public MaidDressesWithTailArmorRender(GeoModel<MaidDressesWithTailArmorItem> model, ItemStack stack) {
        super(model);
        this.stackData = stack;

        addRenderLayer(new MaidDressOverlayLayer(this));
        addRenderLayer(new PrimaryTailLayer(this, ((MaidDressesWithTailArmorItem)stack.getItem()).getTailType()));
        addRenderLayer(new SecondaryTailLayer(this,((MaidDressesWithTailArmorItem)stack.getItem()).getTailType()));

        ((MaidDressOverlayLayer)getRenderLayers().get(0)).updateStack(stack);
        ((PrimaryTailLayer)getRenderLayers().get(1)).updateStack(stack);
        ((SecondaryTailLayer)getRenderLayers().get(2)).updateStack(stack);
    }
    @Override
    public Identifier getTextureLocation(MaidDressesWithTailArmorItem animatable) {
        return new Identifier[]{
                new Identifier(KawaiiDishes.MODID,"textures/models/armor/maid_dresses_with_tail/dress/%s/maid_dress.png".formatted(animatable.getTailType().name().toLowerCase())),
                new Identifier(KawaiiDishes.MODID,"textures/models/armor/maid_dresses_with_tail/dress/%s/dress.png".formatted(animatable.getTailType().name().toLowerCase()))
        }[animatable.hasPrimaryOverlay(stackData) ? 0 : 1];
    }

    @Override
    public void prepForRender(@Nullable Entity entity, ItemStack stack, @Nullable EquipmentSlot slot, @Nullable BipedEntityModel<?> baseModel) {
        updateStack(stack);

        ((MaidDressOverlayLayer)getRenderLayers().get(0)).updateStack(stack);
        ((PrimaryTailLayer)getRenderLayers().get(1)).updateStack(stack);
        ((SecondaryTailLayer)getRenderLayers().get(2)).updateStack(stack);

        super.prepForRender(entity, stack, slot, baseModel);
    }

    @Override
    public Color getRenderColor(MaidDressesWithTailArmorItem animatable, float partialTick, int packedLight) {
        float[] rgb = ColorUtils.getColorsFromHex(animatable.getPrimaryBaseColor(stackData));
        return Color.ofRGB(rgb[0],rgb[1],rgb[2]);
    }
}
