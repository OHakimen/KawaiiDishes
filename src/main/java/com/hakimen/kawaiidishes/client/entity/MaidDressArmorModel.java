package com.hakimen.kawaiidishes.client.entity;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.items.armor.MaidDressArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MaidDressArmorModel extends AnimatedGeoModel<MaidDressArmorItem> {
    public ResourceLocation getModelLocation(MaidDressArmorItem object) {
        return new ResourceLocation(KawaiiDishes.modId, "geo/maid_dress.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MaidDressArmorItem object) {
        return new ResourceLocation(KawaiiDishes.modId, "textures/models/armor/"+object.textureLocation);
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MaidDressArmorItem animatable) {
        return new ResourceLocation(KawaiiDishes.modId, "animations/maid_dress_animation.json");
    }

}
