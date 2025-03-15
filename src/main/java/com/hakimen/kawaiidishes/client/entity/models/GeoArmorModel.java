package com.hakimen.kawaiidishes.client.entity.models;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class GeoArmorModel<T extends GeoAnimatable> extends GeoModel<T> {

    Identifier modelLocation;
    Identifier textureLocation;
    Identifier animationLocation;

    public GeoArmorModel(Identifier modelLocation, Identifier textureLocation, Identifier animationLocation) {
        this.modelLocation = modelLocation;
        this.textureLocation = textureLocation;
        this.animationLocation = animationLocation;
    }

    @Override
    public Identifier getModelResource(T animatable) {
        return modelLocation;
    }

    @Override
    public Identifier getTextureResource(T animatable) {
        return textureLocation;
    }

    @Override
    public Identifier getAnimationResource(T animatable) {
        return animationLocation;
    }
}
