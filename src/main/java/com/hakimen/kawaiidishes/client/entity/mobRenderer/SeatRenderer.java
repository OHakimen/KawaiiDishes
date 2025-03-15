package com.hakimen.kawaiidishes.client.entity.mobRenderer;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.entity.SeatEntity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class SeatRenderer extends EntityRenderer<SeatEntity> {

    private static final Identifier TEXTURE = new Identifier(KawaiiDishes.MODID, "");

    public SeatRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(SeatEntity entity) {
        return TEXTURE;
    }
}