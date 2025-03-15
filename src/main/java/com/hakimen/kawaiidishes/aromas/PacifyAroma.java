package com.hakimen.kawaiidishes.aromas;

import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.custom.type.Aroma;
import com.hakimen.kawaiidishes.registry.EffectRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class PacifyAroma extends Aroma {
    public PacifyAroma(TagKey<Item> items, int color) {
        super(items, color);
    }

    @Override
    public void aromaTick(Level pLevel, BlockPos pPos, BlockState pState, IncenseBlockEntity entity) {
        AABB actuationRange =  AABB.ofSize(pPos.getCenter(), 1,1,1).inflate(8);
        List<LivingEntity> entities = pLevel.getEntitiesOfClass(LivingEntity.class, actuationRange, livingEntity -> true);
        for (LivingEntity mob:entities) {
            mob.addEffect(new MobEffectInstance(EffectRegister.CALMING, 20 * 10, 0, false, false));
        }
    }
}
