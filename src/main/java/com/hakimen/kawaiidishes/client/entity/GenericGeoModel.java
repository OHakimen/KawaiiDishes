package com.hakimen.kawaiidishes.client.entity;

import com.hakimen.kawaiidishes.items.armor.GenericGeoArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GenericGeoModel extends GeoModel<GenericGeoArmorItem> {

    ResourceLocation modelLocation;
    ResourceLocation textureLocation;
    ResourceLocation animationLocation;
    @Override
    public ResourceLocation getModelResource(GenericGeoArmorItem animatable) {
        modelLocation = animatable.modelLocation;
        return animatable.modelLocation;
    }

    @Override
    public ResourceLocation getTextureResource(GenericGeoArmorItem animatable) {
        textureLocation = animatable.textureLocation;
        return animatable.textureLocation;
    }

    @Override
    public ResourceLocation getAnimationResource(GenericGeoArmorItem animatable) {
        animationLocation = animatable.animationLocation;
        return animatable.animationLocation;
    }
}
