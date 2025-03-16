//package com.hakimen.kawaiidishes.aromas;
//
//import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
//import com.hakimen.kawaiidishes.custom.type.Aroma;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.component.DataComponents;
//import net.minecraft.tags.TagKey;
//import net.minecraft.world.effect.MobEffectInstance;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.PotionItem;
//import net.minecraft.world.item.alchemy.Potions;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.phys.AABB;
//
//import java.util.List;
//
//public class PotionAroma extends Aroma {
//    public PotionAroma(TagKey<Item> items, int color) {
//        super(items, color);
//    }
//
//    @Override
//    public void aromaTick(Level pLevel, BlockPos pPos, BlockState pState, IncenseBlockEntity entity) {
//        AABB actuationRange =  AABB.ofSize(pPos.getCenter(), 1,1,1).inflate(8);
//
//        List<Entity> entities = pLevel.getEntities(null, actuationRange);
//        ItemStack stack = entity.getInventory().getStackInSlot(0);
//        for (Entity mob:entities) {
//            if(stack.getItem() instanceof PotionItem && mob instanceof LivingEntity livingEntity){
//                for (MobEffectInstance i: stack.get(DataComponents.POTION_CONTENTS).getAllEffects()) {
//                    MobEffectInstance cloned = new MobEffectInstance(i.getEffect(), 15 * 20, i.getAmplifier());
//                    livingEntity.addEffect(cloned);
//                }
//            }
//        }
//    }
//}

// TODO: aromas