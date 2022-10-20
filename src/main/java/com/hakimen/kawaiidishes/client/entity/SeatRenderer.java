package com.hakimen.kawaiidishes.client.entity;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.entity.SittableEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SeatRenderer extends EntityRenderer<SittableEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(KawaiiDishes.modId, "");

    public SeatRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(SittableEntity entity) {
        return TEXTURE;
    }
}