package com.hakimen.kawaiidishes.aromas;

import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.custom.types.Aroma;
import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DecorativeAroma extends Aroma {
    public DecorativeAroma(TagKey<Item> items, int color) {
        super(items, color);
    }

    @Override
    public void aromaTick(World pLevel, BlockPos pPos, BlockState pState, IncenseBlockEntity entity) {

    }
}
