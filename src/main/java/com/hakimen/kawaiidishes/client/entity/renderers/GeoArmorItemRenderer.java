package com.hakimen.kawaiidishes.client.entity.renderers;

import net.minecraft.world.item.Item;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class GeoArmorItemRenderer<T extends Item & GeoItem> extends GeoArmorRenderer<T> {
    public GeoArmorItemRenderer(GeoModel<T> model) {
        super(model);
    }
}
