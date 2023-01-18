package com.hakimen.kawaiidishes.client.entity;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.items.armor.BunnyTailArmorItem;
import com.hakimen.kawaiidishes.items.armor.DevilTailArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DevilTailArmorModel extends AnimatedGeoModel<DevilTailArmorItem> {
    public ResourceLocation getModelResource(DevilTailArmorItem object) {
        return new ResourceLocation(KawaiiDishes.modId, "geo/devil_tail.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DevilTailArmorItem object) {
        return new ResourceLocation(KawaiiDishes.modId, "textures/models/armor/"+object.textureLocation);
    }

    @Override
    public ResourceLocation getAnimationResource(DevilTailArmorItem animatable) {
        return new ResourceLocation(KawaiiDishes.modId, "animations/devil_tail_animations.json");
    }

}
