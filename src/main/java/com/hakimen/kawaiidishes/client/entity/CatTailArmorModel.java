package com.hakimen.kawaiidishes.client.entity;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.items.armor.CatTailArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CatTailArmorModel extends AnimatedGeoModel<CatTailArmorItem> {
    public ResourceLocation getModelResource(CatTailArmorItem object) {
        return new ResourceLocation(KawaiiDishes.modId, "geo/cat_tail.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CatTailArmorItem object) {
        return new ResourceLocation(KawaiiDishes.modId, "textures/models/armor/"+object.textureLocation);
    }

    @Override
    public ResourceLocation getAnimationResource(CatTailArmorItem animatable) {
        return new ResourceLocation(KawaiiDishes.modId, "animations/cat_tail_animation.json");
    }

}
