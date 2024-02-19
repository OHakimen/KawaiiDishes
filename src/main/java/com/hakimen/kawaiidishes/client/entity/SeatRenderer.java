package com.hakimen.kawaiidishes.client.entity;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.entity.SeatEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SeatRenderer extends EntityRenderer<SeatEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(KawaiiDishes.MODID, "");

    public SeatRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(SeatEntity entity) {
        return TEXTURE;
    }
}