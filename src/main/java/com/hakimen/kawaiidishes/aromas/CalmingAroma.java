package com.hakimen.kawaiidishes.aromas;

import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.custom.types.Aroma;
import com.hakimen.kawaiidishes.effects.CalmingEffect;
import com.hakimen.kawaiidishes.registry.EffectRegister;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class CalmingAroma extends Aroma {
    public CalmingAroma(TagKey<Item> items, int color) {
        super(items, color);
    }

    @Override
    public void aromaTick(Level pLevel, BlockPos pPos, BlockState pState, IncenseBlockEntity entity) {
        List<LivingEntity> entities = pLevel.getEntitiesOfClass(LivingEntity.class, AABB.ofSize(pPos.getCenter(),1,1,1).inflate(8),livingEntity -> true);

        for (LivingEntity livingEntity : entities) {
            livingEntity.addEffect(new MobEffectInstance(EffectRegister.CALMING.get(), 20 * 5, 0, false, false));
        }
    }
}
