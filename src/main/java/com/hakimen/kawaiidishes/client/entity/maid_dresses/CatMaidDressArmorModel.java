package com.hakimen.kawaiidishes.client.entity.maid_dresses;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.items.armor.CatMaidArmorItem;
import com.hakimen.kawaiidishes.items.armor.MaidDressArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CatMaidDressArmorModel extends AnimatedGeoModel<CatMaidArmorItem> {
    public ResourceLocation getModelLocation(CatMaidArmorItem object) {
        return new ResourceLocation(KawaiiDishes.modId, "geo/cat_tailed_maid_dress.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CatMaidArmorItem object) {
        return new ResourceLocation(KawaiiDishes.modId, "textures/models/armor/cat_tailed/"+object.textureLocation);
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CatMaidArmorItem animatable) {
        return new ResourceLocation(KawaiiDishes.modId, "animations/cat_tail_animation.json");
    }

}
