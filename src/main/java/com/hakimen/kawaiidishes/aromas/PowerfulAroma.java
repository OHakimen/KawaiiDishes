package com.hakimen.kawaiidishes.aromas;

import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.custom.type.Aroma;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class PowerfulAroma extends Aroma {
    public PowerfulAroma(TagKey<Item> items, int color) {
        super(items, color);
    }

    @Override
    public void aromaTick(Level pLevel, BlockPos pPos, BlockState pState, IncenseBlockEntity entity) {
        AABB actuationRange =  AABB.ofSize(pPos.getCenter(), 1,1,1).inflate(8);

        List<Player> entities = pLevel.getEntities(EntityType.PLAYER, actuationRange, player -> true);
        for (Player player:entities) {
            player.forceAddEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 15 * 20, 1, false ,false, false), player);
        }
    }
}
