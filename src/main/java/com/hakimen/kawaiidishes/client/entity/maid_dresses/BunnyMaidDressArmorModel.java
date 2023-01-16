package com.hakimen.kawaiidishes.client.entity.maid_dresses;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.items.armor.BunnyMaidArmorItem;
import com.hakimen.kawaiidishes.items.armor.CatMaidArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BunnyMaidDressArmorModel extends AnimatedGeoModel<BunnyMaidArmorItem> {
    public ResourceLocation getModelResource(BunnyMaidArmorItem object) {
        return new ResourceLocation(KawaiiDishes.modId, "geo/bunny_tailed_maid_dress.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BunnyMaidArmorItem object) {
        return new ResourceLocation(KawaiiDishes.modId, "textures/models/armor/bunny_tailed/"+object.textureLocation);
    }

    @Override
    public ResourceLocation getAnimationResource(BunnyMaidArmorItem animatable) {
        return new ResourceLocation(KawaiiDishes.modId, "animations/cat_tail_animation.json");
    }

}
