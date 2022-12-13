package com.hakimen.kawaiidishes.client.entity;

import com.hakimen.kawaiidishes.KawaiiDishes;

import com.hakimen.kawaiidishes.items.armor.ThighHighsArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ThighHighsArmorModel extends AnimatedGeoModel<ThighHighsArmorItem> {
    public ResourceLocation getModelResource(ThighHighsArmorItem object) {
        return new ResourceLocation(KawaiiDishes.modId, "geo/thigh_highs.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ThighHighsArmorItem object) {
        return new ResourceLocation(KawaiiDishes.modId, "textures/models/armor/"+object.textureLocation);
    }

    @Override
    public ResourceLocation getAnimationResource(ThighHighsArmorItem animatable) {
        return new ResourceLocation(KawaiiDishes.modId, "animations/maid_dress_animation.json");
    }

}
