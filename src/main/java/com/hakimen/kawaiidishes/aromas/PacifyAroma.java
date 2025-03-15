package com.hakimen.kawaiidishes.aromas;

import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.custom.types.Aroma;
import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class PacifyAroma extends Aroma {
    public PacifyAroma(TagKey<Item> items, int color) {
        super(items, color);
    }

    @Override
    public void aromaTick(World pLevel, BlockPos pPos, BlockState pState, IncenseBlockEntity entity) {
        Box actuationRange =  Box.of(pPos.toCenterPos(), 1,1,1).expand(8);
        List<Entity> entities = pLevel.getOtherEntities(null, actuationRange);
        for (Entity mob:entities) {
            if(mob instanceof HostileEntity){
                mob.remove(Entity.RemovalReason.DISCARDED);
            }
        }
    }
}
