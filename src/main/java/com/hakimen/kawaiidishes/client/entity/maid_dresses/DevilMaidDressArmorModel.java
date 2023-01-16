package com.hakimen.kawaiidishes.client.entity.maid_dresses;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.items.armor.BunnyMaidArmorItem;
import com.hakimen.kawaiidishes.items.armor.DevilMaidArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DevilMaidDressArmorModel extends AnimatedGeoModel<DevilMaidArmorItem> {
    public ResourceLocation getModelResource(DevilMaidArmorItem object) {
        return new ResourceLocation(KawaiiDishes.modId, "geo/devil_tailed_maid_dress.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DevilMaidArmorItem object) {
        return new ResourceLocation(KawaiiDishes.modId, "textures/models/armor/devil_tailed/"+object.textureLocation);
    }

    @Override
    public ResourceLocation getAnimationResource(DevilMaidArmorItem animatable) {
        return new ResourceLocation(KawaiiDishes.modId, "animations/devil_tail_animations.json");
    }

}
