package com.hakimen.kawaiidishes.client.entity.renderers;

import net.minecraft.item.Item;
import mod.azure.azurelib.animatable.GeoItem;
import mod.azure.azurelib.model.GeoModel;
import mod.azure.azurelib.renderer.GeoArmorRenderer;

@SuppressWarnings({"removal"})
public class GeoArmorItemRenderer<T extends Item & GeoItem> extends GeoArmorRenderer<T> {
    public GeoArmorItemRenderer(GeoModel<T> model) {
        super(model);
    }
}
