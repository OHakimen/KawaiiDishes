package com.hakimen.kawaiidishes.client.entity;

import com.hakimen.kawaiidishes.items.armor.GenericGeoArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class GenericGeoRenderer extends GeoArmorRenderer<GenericGeoArmorItem> {
    public GenericGeoRenderer() {
        super(new GenericGeoModel());
    }
}
