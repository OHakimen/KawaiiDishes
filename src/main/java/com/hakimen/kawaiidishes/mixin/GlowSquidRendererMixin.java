package com.hakimen.kawaiidishes.mixin;

import net.minecraft.client.renderer.entity.GlowSquidRenderer;
import net.minecraft.client.renderer.entity.SquidRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.entity.animal.Squid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@OnlyIn(Dist.CLIENT)
@Mixin(GlowSquidRenderer.class)
public class GlowSquidRendererMixin {
    @Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/GlowSquid;)Lnet/minecraft/resources/ResourceLocation;", at = @At("RETURN"), cancellable = true)
    public void getTextureLocation(GlowSquid pEntity, CallbackInfoReturnable<ResourceLocation> cir) {
        if(pEntity.hasCustomName() && pEntity.getCustomName().toString().toLowerCase().contains("maid")){
            cir.setReturnValue(new ResourceLocation("kawaiidishes","textures/entities/maid_glowsquid.png"));
        }
    }

}
