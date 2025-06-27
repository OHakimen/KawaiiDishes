package com.hakimen.kawaiidishes.aromas;

import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.custom.types.Aroma;
import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class StimulatingAroma extends Aroma {
    public StimulatingAroma(TagKey<Item> items, int color) {
        super(items, color);
    }

    @Override
    public void aromaTick(World pLevel, BlockPos pPos, BlockState pState, IncenseBlockEntity entity) {
        Box actuationRange =  Box.of(pPos.toCenterPos(), 1,1,1).expand(8);

        List<PlayerEntity> entities = pLevel.getEntitiesByType(EntityType.PLAYER, actuationRange, player -> true);
        for (PlayerEntity player:entities) {
            player.setStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 15 * 20, 0, false ,false, false), player);
            player.setStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 15 * 20, 0, false ,false, false), player);
        }
    }
}
