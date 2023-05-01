package com.hakimen.kawaiidishes.mixin.clientside;

import com.hakimen.kawaiidishes.KawaiiDishes;
import net.minecraft.client.renderer.entity.FoxRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Fox;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FoxRenderer.class)
public class FoxRendererMixin {

    @Shadow @Final private static ResourceLocation RED_FOX_SLEEP_TEXTURE;

    @Shadow @Final private static ResourceLocation RED_FOX_TEXTURE;

    @Shadow @Final private static ResourceLocation SNOW_FOX_SLEEP_TEXTURE;

    @Shadow @Final private static ResourceLocation SNOW_FOX_TEXTURE;


    private final static ResourceLocation BLACK_FOX_TEXTURE = new ResourceLocation(KawaiiDishes.modId,"textures/entity/fox/black_fox.png");
    private final static ResourceLocation BLACK_FOX_SLEEP_TEXTURE = new ResourceLocation(KawaiiDishes.modId,"textures/entity/fox/black_fox_sleeping.png");

    private final static ResourceLocation BROWN_FOX_TEXTURE = new ResourceLocation(KawaiiDishes.modId,"textures/entity/fox/brown_fox.png");
    private final static ResourceLocation BROWN_FOX_SLEEP_TEXTURE = new ResourceLocation(KawaiiDishes.modId,"textures/entity/fox/brown_fox_sleeping.png");

    /**
     * @author Me
     * @reason Because Yes
     */
    @Overwrite
    public ResourceLocation getTextureLocation(Fox pEntity) {
        if(pEntity.hasCustomName()){
            var name = pEntity.getCustomName().getString().toLowerCase();
            if(name.equals("black fox")){
                return pEntity.isSleeping() ? BLACK_FOX_SLEEP_TEXTURE : BLACK_FOX_TEXTURE;
            }
            if(name.equals("brown fox")){
                return pEntity.isSleeping() ? BROWN_FOX_SLEEP_TEXTURE : BROWN_FOX_TEXTURE;
            }
        }

        if (pEntity.getFoxType() == Fox.Type.RED) {
            return pEntity.isSleeping() ? RED_FOX_SLEEP_TEXTURE : RED_FOX_TEXTURE;
        } else {
            return pEntity.isSleeping() ? SNOW_FOX_SLEEP_TEXTURE : SNOW_FOX_TEXTURE;
        }
    }
}
