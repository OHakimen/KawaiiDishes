package com.hakimen.kawaiidishes.mixin;

import net.minecraft.client.renderer.entity.SquidRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Squid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@OnlyIn(Dist.CLIENT)
@Mixin(SquidRenderer.class)
public class SquidRendererMixin {
    @Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/animal/Squid;)Lnet/minecraft/resources/ResourceLocation;", at = @At("RETURN"), cancellable = true)
    public void getTextureLocation(Squid pEntity, CallbackInfoReturnable<ResourceLocation> cir) {
        if(pEntity.hasCustomName() && pEntity.getCustomName().toString().toLowerCase().contains("maid")){
            cir.setReturnValue(new ResourceLocation("kawaiidishes","textures/entities/maid_squid.png"));
        }
    }

}
