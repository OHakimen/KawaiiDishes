package com.hakimen.kawaiidishes.mixin.clientside;

import net.minecraft.client.renderer.entity.SquidRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Squid;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;


@Mixin(SquidRenderer.class)
public class SquidRendererMixin {

    @Shadow @Final private static ResourceLocation SQUID_LOCATION;
    private static final ResourceLocation MAID_SQUID_LOCATION = new ResourceLocation("kawaiidishes","textures/entity/squid/maid_squid.png");

    /**
     * @author
     * @reason
     */
    @Overwrite
    public ResourceLocation getTextureLocation(Squid pEntity) {
        return (pEntity.hasCustomName() && pEntity.getCustomName().toString().toLowerCase().contains("maid"))
                ? MAID_SQUID_LOCATION : SQUID_LOCATION;
    }

}
