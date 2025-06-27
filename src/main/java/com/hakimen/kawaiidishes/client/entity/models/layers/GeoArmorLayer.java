package com.hakimen.kawaiidishes.client.entity.models.layers;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class GeoArmorLayer<T extends GeoAnimatable> extends GeoRenderLayer<T> {

    private final Identifier texture;
    public GeoArmorLayer(GeoRenderer<T> entityRendererIn, Identifier texture) {
        super(entityRendererIn);
        this.texture = texture;
    }

    public Identifier getTexture() {
        return texture;
    }
}
