package com.hakimen.kawaiidishes.items;

import com.hakimen.kawaiidishes.blocks.MugBlock;
import com.hakimen.kawaiidishes.registry.EffectRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import com.mojang.authlib.GameProfile;
import net.minecraft.advancements.critereon.MobEffectsPredicate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class Drink extends BlockItem {


    public Drink(MugBlock pBlock,int nutrition,float saturation) {
        super(pBlock, new Properties().food(new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation).fast().build())
                .tab(ItemRegister.foods).stacksTo(16));
    }


    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
        super.onUsingTick(stack, player, count);
    }



    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        pLevel.addFreshEntity(new ItemEntity(pLevel,
                pLivingEntity.getX(),
                pLivingEntity.getY(),
                pLivingEntity.getZ(),
                ItemRegister.mug.get().getDefaultInstance()));
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }

    @Override
    public SoundEvent getEatingSound() {
        return super.getDrinkingSound();
    }
}
