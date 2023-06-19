package com.hakimen.kawaiidishes.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;


public class NekoEffect extends MobEffect {


    public NekoEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x00B21D);
    }


    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        var entities = pLivingEntity.level().getEntities(pLivingEntity,
                AABB.ofSize(new Vec3(
                        pLivingEntity.getX(),
                        pLivingEntity.getY(),
                        pLivingEntity.getZ()
                ),8*(1+pAmplifier),8*(1+pAmplifier),8*(1+pAmplifier)));
        for (Entity entity:entities) {
            if(entity instanceof Creeper creeper){
                creeper.knockback(
                        0.25f,
                        (pLivingEntity.getX()-creeper.getX()),
                        (pLivingEntity.getZ()-creeper.getZ())
                );
            }
        }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }



    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
