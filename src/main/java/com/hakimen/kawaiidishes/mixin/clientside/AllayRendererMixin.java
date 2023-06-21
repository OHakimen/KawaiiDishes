package com.hakimen.kawaiidishes.mixin.clientside;

import net.minecraft.client.renderer.entity.AllayRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.allay.Allay;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;


@Mixin(AllayRenderer.class)
public class AllayRendererMixin {

    @Shadow @Final private static ResourceLocation ALLAY_TEXTURE;
    private static final ResourceLocation MAID_ALLAY_LOCATION = new ResourceLocation("kawaiidishes","textures/entity/allay/maid_allay.png");

    /**
     * @author
     * @reason
     */
    @Overwrite
    public ResourceLocation getTextureLocation(Allay pEntity) {
        return (pEntity.hasCustomName() && pEntity.getCustomName().toString().toLowerCase().contains("maid"))
                ? MAID_ALLAY_LOCATION : ALLAY_TEXTURE;
    }
}
