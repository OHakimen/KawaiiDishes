package com.hakimen.kawaiidishes.client.entity;

import com.hakimen.kawaiidishes.items.armor.GenericGeoArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class GenericGeoRenderer extends GeoArmorRenderer<GenericGeoArmorItem> {
    public GenericGeoRenderer(ResourceLocation model) {
        super(new GenericGeoModel());
    }
}
