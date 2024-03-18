package com.hakimen.kawaiidishes.aromas;

import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.custom.type.Aroma;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Monster;
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
        List<Entity> entities = pLevel.getEntities(null, actuationRange);
        for (Entity mob:entities) {
            if(mob instanceof Monster){
                mob.remove(Entity.RemovalReason.DISCARDED);
            }
        }
    }
}
