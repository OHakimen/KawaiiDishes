package com.hakimen.kawaiidishes.client.entity.models.layers;

import net.minecraft.util.Identifier;
import mod.azure.azurelib.core.animatable.GeoAnimatable;
import mod.azure.azurelib.renderer.GeoRenderer;
import mod.azure.azurelib.renderer.layer.GeoRenderLayer;

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
