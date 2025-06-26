package com.hakimen.kawaiidishes.client.entity.models.layers;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class GeoArmorLayer<T extends GeoAnimatable> extends GeoRenderLayer<T> {

    private final ResourceLocation texture;
    public GeoArmorLayer(GeoRenderer<T> entityRendererIn, ResourceLocation texture) {
        super(entityRendererIn);
        this.texture = texture;
    }

    public ResourceLocation getTexture() {
        return texture;
    }
}
