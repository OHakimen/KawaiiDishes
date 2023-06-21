package com.hakimen.kawaiidishes.mixin.clientside;

import net.minecraft.client.renderer.entity.GlowSquidRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.GlowSquid;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;


@Mixin(GlowSquidRenderer.class)
public class GlowSquidRendererMixin {

    @Shadow @Final private static ResourceLocation GLOW_SQUID_LOCATION;
    private static final ResourceLocation MAID_GLOWSQUID_LOCATION = new ResourceLocation("kawaiidishes","textures/entity/squid/maid_glowsquid.png");

    /**
     * @author
     * @reason
     */
    @Overwrite
    public ResourceLocation getTextureLocation(GlowSquid pEntity) {
        return (pEntity.hasCustomName() && pEntity.getCustomName().toString().toLowerCase().contains("maid"))
                ? MAID_GLOWSQUID_LOCATION : GLOW_SQUID_LOCATION;
    }
}
