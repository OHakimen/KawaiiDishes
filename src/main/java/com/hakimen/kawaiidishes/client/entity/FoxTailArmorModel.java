package com.hakimen.kawaiidishes.client.entity;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.items.armor.CatTailArmorItem;
import com.hakimen.kawaiidishes.items.armor.FoxTailArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FoxTailArmorModel extends AnimatedGeoModel<FoxTailArmorItem> {
    public ResourceLocation getModelResource(FoxTailArmorItem object) {
        return new ResourceLocation(KawaiiDishes.modId, "geo/fox_tail.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FoxTailArmorItem object) {
        return new ResourceLocation(KawaiiDishes.modId, "textures/models/armor/"+object.textureLocation);
    }

    @Override
    public ResourceLocation getAnimationResource(FoxTailArmorItem animatable) {
        return new ResourceLocation(KawaiiDishes.modId, "animations/fox_tail_animations.json");
    }

}
