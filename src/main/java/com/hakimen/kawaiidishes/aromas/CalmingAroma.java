package com.hakimen.kawaiidishes.aromas;

import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.custom.type.Aroma;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class CalmingAroma extends Aroma {
    public CalmingAroma(TagKey<Item> items, int color) {
        super(items, color);
    }

    @Override
    public void aromaTick(Level pLevel, BlockPos pPos, BlockState pState, IncenseBlockEntity entity) {

    }
}
