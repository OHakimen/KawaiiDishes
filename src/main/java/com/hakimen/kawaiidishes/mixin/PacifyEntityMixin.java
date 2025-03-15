package com.hakimen.kawaiidishes.mixin;

import com.hakimen.kawaiidishes.registry.EffectRegister;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class PacifyEntityMixin {


    @Inject(at = @At("RETURN"), method = "canTarget(Lnet/minecraft/entity/LivingEntity;)Z", cancellable = true)
    public void canAttack(LivingEntity entity, CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(!((LivingEntity)(Object) this).hasStatusEffect(EffectRegister.CALMING.get()) && cir.getReturnValue());
    }

}
