package com.hakimen.kawaiidishes.aromas;

import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.custom.types.Aroma;
import com.hakimen.kawaiidishes.effects.CalmingEffect;
import com.hakimen.kawaiidishes.registry.EffectRegister;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class CalmingAroma extends Aroma {
    public CalmingAroma(TagKey<Item> items, int color) {
        super(items, color);
    }

    @Override
    public void aromaTick(World pLevel, BlockPos pPos, BlockState pState, IncenseBlockEntity entity) {
        List<LivingEntity> entities = pLevel.getEntitiesByClass(LivingEntity.class, Box.of(pPos.toCenterPos(),1,1,1).expand(8),livingEntity -> true);

        for (LivingEntity livingEntity : entities) {
            livingEntity.addStatusEffect(new StatusEffectInstance(EffectRegister.CALMING.get(), 20 * 5, 0, false, false));
        }
    }
}
