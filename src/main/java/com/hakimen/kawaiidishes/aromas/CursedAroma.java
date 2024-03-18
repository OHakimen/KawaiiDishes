package com.hakimen.kawaiidishes.aromas;

import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.custom.type.Aroma;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class CursedAroma extends Aroma {
    public CursedAroma(TagKey<Item> items, int color) {
        super(items, color);
    }

    @Override
    public void aromaTick(Level pLevel, BlockPos pPos, BlockState pState, IncenseBlockEntity entity) {
        AABB actuationRange =  AABB.ofSize(pPos.getCenter(), 1,1,1).inflate(8);

        List<Entity> entities = pLevel.getEntities(null, actuationRange);
        for (Entity mob:entities) {
            if(mob instanceof Pig || (mob instanceof Creeper creeper && !creeper.isPowered())|| mob instanceof Villager || (mob instanceof MushroomCow cow  && cow.getVariant().equals(MushroomCow.MushroomType.RED))){
                mob.thunderHit((ServerLevel) pLevel, new LightningBolt(EntityType.LIGHTNING_BOLT, pLevel));
            }
        }
    }
}
