package com.hakimen.kawaiidishes.client.entity.blocks;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.blocks.block_entities.MortarAndPestleBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MortarAndPestleBlockModel extends AnimatedGeoModel<MortarAndPestleBlockEntity> {
    @Override
    public ResourceLocation getModelResource(MortarAndPestleBlockEntity object) {
        return new ResourceLocation(KawaiiDishes.modId, "geo/mortar_and_pestle.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MortarAndPestleBlockEntity object) {
        return new ResourceLocation(KawaiiDishes.modId, "textures/block/mortar_and_pestle.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MortarAndPestleBlockEntity animatable) {
        return new ResourceLocation(KawaiiDishes.modId, "animations/mortar_and_pestle_animations.json");
    }


}
