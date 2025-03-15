package com.hakimen.kawaiidishes.mixins;

import com.hakimen.kawaiidishes.aromas.PacifyAroma;
import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.registry.EffectRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class PacifyEntityMixin {
    @Inject(at = @At("RETURN"), method = "canAttack(Lnet/minecraft/world/entity/LivingEntity;)Z", cancellable = true)
    public void canAttack(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(!((LivingEntity)(Object) this).hasEffect(EffectRegister.CALMING) && cir.getReturnValue());
    }
    @Inject(at = @At("RETURN"), method = "canAttack(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/ai/targeting/TargetingConditions;)Z", cancellable = true)
    public void canAttack(LivingEntity pLivingentity, TargetingConditions pCondition, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(!((LivingEntity)(Object) this).hasEffect(EffectRegister.CALMING) && cir.getReturnValue());
    }
}
