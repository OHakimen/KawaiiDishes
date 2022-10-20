package com.hakimen.kawaiidishes.client.entity.maid_dresses;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.items.armor.FoxMaidArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FoxMaidDressArmorModel extends AnimatedGeoModel<FoxMaidArmorItem> {
    public ResourceLocation getModelLocation(FoxMaidArmorItem object) {
        return new ResourceLocation(KawaiiDishes.modId, "geo/fox_maid_dress.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(FoxMaidArmorItem object) {
        return new ResourceLocation(KawaiiDishes.modId, "textures/models/armor/fox_tailed/"+object.textureLocation);
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FoxMaidArmorItem animatable) {
        return new ResourceLocation(KawaiiDishes.modId, "animations/fox_tail_animations.json");
    }

}
