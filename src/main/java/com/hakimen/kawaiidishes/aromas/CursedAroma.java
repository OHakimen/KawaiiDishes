package com.hakimen.kawaiidishes.aromas;

import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.custom.types.Aroma;
import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.passive.MooshroomEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class CursedAroma extends Aroma {
    public CursedAroma(TagKey<Item> items, int color) {
        super(items, color);
    }

    @Override
    public void aromaTick(World pLevel, BlockPos pPos, BlockState pState, IncenseBlockEntity entity) {
        Box actuationRange =  Box.of(pPos.toCenterPos(), 1,1,1).expand(8);

        List<Entity> entities = pLevel.getOtherEntities(null, actuationRange);
        for (Entity mob:entities) {
            if(mob instanceof PigEntity || (mob instanceof CreeperEntity creeper && !creeper.shouldRenderOverlay())|| mob instanceof VillagerEntity || (mob instanceof MooshroomEntity cow  && cow.getVariant().equals(MooshroomEntity.Type.RED))){
                mob.onStruckByLightning((ServerWorld) pLevel, new LightningEntity(EntityType.LIGHTNING_BOLT, pLevel));
            }
        }
    }
}
