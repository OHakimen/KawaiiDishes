package com.hakimen.kawaiidishes.mixin;

import com.hakimen.kawaiidishes.registry.EffectRegister;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class PacifyEntityMixin {

    @ModifyReturnValue(method = "canTarget(Lnet/minecraft/entity/LivingEntity;)Z", at = @At("RETURN"))
    private boolean kawaiidishes$pacify(boolean canTarget, LivingEntity target) {
        return canTarget && !((LivingEntity) (Object) this).hasStatusEffect(EffectRegister.CALMING.get());
    }
}
