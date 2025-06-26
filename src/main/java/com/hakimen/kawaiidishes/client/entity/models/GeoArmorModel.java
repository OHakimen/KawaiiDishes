package com.hakimen.kawaiidishes.client.entity.models;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class GeoArmorModel<T extends GeoAnimatable> extends GeoModel<T> {

    ResourceLocation modelLocation;
    ResourceLocation textureLocation;
    ResourceLocation animationLocation;

    public GeoArmorModel(ResourceLocation modelLocation, ResourceLocation textureLocation, ResourceLocation animationLocation) {
        this.modelLocation = modelLocation;
        this.textureLocation = textureLocation;
        this.animationLocation = animationLocation;
    }

    @Override
    public ResourceLocation getModelResource(T animatable) {
        return modelLocation;
    }

    @Override
    public ResourceLocation getTextureResource(T animatable) {
        return textureLocation;
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return animationLocation;
    }
}
