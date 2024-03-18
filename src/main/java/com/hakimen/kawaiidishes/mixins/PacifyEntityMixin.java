package com.hakimen.kawaiidishes.mixins;

import com.hakimen.kawaiidishes.aromas.PacifyAroma;
import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
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
    @Shadow
    public abstract boolean hasEffect(MobEffect p_21024_);

    @Shadow public abstract boolean canAttack(LivingEntity p_21041_, TargetingConditions p_21042_);

    @Shadow private BlockPos lastPos;

    @Inject(at = @At("RETURN"), method = "canAttack(Lnet/minecraft/world/entity/LivingEntity;)Z", cancellable = true)
    public void canAttack(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        boolean hasCalmingAroma = BlockPos.betweenClosedStream(AABB.ofSize(((Entity)(Object) this).blockPosition().getCenter(), 1, 1, 1).inflate(8))
                .anyMatch(blockPos -> entity.level().getBlockEntity(blockPos) instanceof IncenseBlockEntity incenseBlock && incenseBlock.getAromaFromId() instanceof PacifyAroma);
        cir.setReturnValue(!hasCalmingAroma && cir.getReturnValue());
    }

    @Inject(at = @At("RETURN"), method = "canAttack(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/ai/targeting/TargetingConditions;)Z", cancellable = true)
    public void canAttack(LivingEntity entity, TargetingConditions targetingConditions, CallbackInfoReturnable<Boolean> cir){
        boolean hasCalmingAroma = BlockPos.betweenClosedStream(AABB.ofSize(((Entity)(Object) this).blockPosition().getCenter(), 1, 1, 1).inflate(8))
                .anyMatch(blockPos -> entity.level().getBlockEntity(blockPos) instanceof IncenseBlockEntity incenseBlock && incenseBlock.getAromaFromId() instanceof PacifyAroma);
        cir.setReturnValue(!hasCalmingAroma && cir.getReturnValue());
    }
}
