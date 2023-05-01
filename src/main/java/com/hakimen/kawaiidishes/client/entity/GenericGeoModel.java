package com.hakimen.kawaiidishes.client.entity;

import com.hakimen.kawaiidishes.items.armor.GenericGeoArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GenericGeoModel extends AnimatedGeoModel<GenericGeoArmorItem> {

    ResourceLocation modelLocation;
    ResourceLocation textureLocation;
    ResourceLocation animationLocation;
    @Override
    public ResourceLocation getModelResource(GenericGeoArmorItem object) {
        modelLocation = object.modelLocation;
        return object.modelLocation;
    }

    @Override
    public ResourceLocation getTextureResource(GenericGeoArmorItem object) {
        textureLocation = object.textureLocation;
        return object.textureLocation;
    }

    @Override
    public ResourceLocation getAnimationResource(GenericGeoArmorItem animatable) {
        animationLocation = animatable.animationLocation;
        return animatable.animationLocation;
    }
}
