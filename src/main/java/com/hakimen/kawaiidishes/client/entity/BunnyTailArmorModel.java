package com.hakimen.kawaiidishes.client.entity;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.items.armor.BunnyTailArmorItem;
import com.hakimen.kawaiidishes.items.armor.CatTailArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BunnyTailArmorModel extends AnimatedGeoModel<BunnyTailArmorItem> {
    public ResourceLocation getModelResource(BunnyTailArmorItem object) {
        return new ResourceLocation(KawaiiDishes.modId, "geo/bunny_tail.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BunnyTailArmorItem object) {
        return new ResourceLocation(KawaiiDishes.modId, "textures/models/armor/"+object.textureLocation);
    }

    @Override
    public ResourceLocation getAnimationResource(BunnyTailArmorItem animatable) {
        return new ResourceLocation(KawaiiDishes.modId, "animations/cat_tail_animation.json");
    }

}
