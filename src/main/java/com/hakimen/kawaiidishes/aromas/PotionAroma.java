package com.hakimen.kawaiidishes.aromas;

import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.custom.types.Aroma;
import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.PotionUtil;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class PotionAroma extends Aroma {
    public PotionAroma(TagKey<Item> items, int color) {
        super(items, color);
    }

    @Override
    public void aromaTick(World pLevel, BlockPos pPos, BlockState pState, IncenseBlockEntity entity) {
        Box actuationRange =  Box.of(pPos.toCenterPos(), 1,1,1).expand(8);

        List<Entity> entities = pLevel.getOtherEntities(null, actuationRange);
        ItemStack stack = entity.getInventory().getResource().toStack((int) entity.getInventory().amount);
        for (Entity mob:entities) {
            if(stack.getItem() instanceof PotionItem && mob instanceof LivingEntity livingEntity){
                List<StatusEffectInstance> effects = PotionUtil.getPotionEffects(stack);
                for (StatusEffectInstance i:effects) {
                    StatusEffectInstance cloned = new StatusEffectInstance(i.getEffectType(), 15 * 20, i.getAmplifier());
                    livingEntity.addStatusEffect(cloned);
                }
            }
        }
    }
}
