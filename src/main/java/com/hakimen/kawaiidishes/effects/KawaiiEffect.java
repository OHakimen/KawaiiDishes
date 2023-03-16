package com.hakimen.kawaiidishes.effects;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.config.KawaiiDishesCommonConfig;
import com.hakimen.kawaiidishes.utils.EntityUtils;
import com.hakimen.kawaiidishes.utils.KawaiiMessages;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Random;


public class KawaiiEffect extends MobEffect {
    public KawaiiEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFF00C3);
    }


    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        var entities = pLivingEntity.getLevel().getEntities(pLivingEntity,
                AABB.ofSize(new Vec3(
                        pLivingEntity.getX(),
                        pLivingEntity.getY(),
                        pLivingEntity.getZ()
                ),8*(1+pAmplifier),8*(1+pAmplifier),8*(1+pAmplifier)));
        Random r = KawaiiDishes.RANDOM;
        for (Entity entity:entities) {
            if((entity instanceof LivingEntity livingEntity && EntityUtils.isHumanoid(livingEntity)&& pLivingEntity instanceof Player player)){
                if(r.nextFloat(0,1) < KawaiiDishesCommonConfig.chanceToMessage.get()){
                    KawaiiMessages.sendMessage(livingEntity,player);
                }
            }

        }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
