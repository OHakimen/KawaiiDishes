package com.hakimen.kawaiidishes.items;

import com.hakimen.kawaiidishes.blocks.MugBlock;
import com.hakimen.kawaiidishes.blocks.block_entities.CoffeeMugBlockEntity;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import com.hakimen.kawaiidishes.registry.EffectRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import com.mojang.authlib.GameProfile;
import net.minecraft.advancements.critereon.MobEffectsPredicate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BannerBlock;

import java.util.UUID;

public class Drink extends BlockItem {


    public Drink(MugBlock pBlock,int nutrition,float saturation) {
        super(pBlock, new Properties().food(new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation).fast().build())
                .tab(ItemRegister.foods).stacksTo(16).craftRemainder(
                        ItemRegister.mug.get()
                ));
    }


    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
        super.onUsingTick(stack, player, count);
    }

    CompoundTag mainEffect;
    CompoundTag secondaryEffect;

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {

        if(pLivingEntity instanceof Player p){
            p.addItem(ItemRegister.mug.get().getDefaultInstance());
        }
        loadItemData(pStack);

        if(!mainEffect.equals(new CompoundTag())){
            pLivingEntity.addEffect(MobEffectInstance.load(mainEffect));
        }
        if(!secondaryEffect.equals(new CompoundTag())){
            pLivingEntity.addEffect(MobEffectInstance.load(secondaryEffect));
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }

    public void loadItemData(ItemStack pStack){
        mainEffect = pStack.getOrCreateTag().getCompound("mainEffect");
        secondaryEffect = pStack.getOrCreateTag().getCompound("secondaryEffect");

    }

    @Override
    public void onDestroyed(ItemEntity pItemEntity) {
        CompoundTag tag = new CompoundTag();
        tag.put("mainEffect",mainEffect);
        tag.put("secondaryEffect",secondaryEffect);
        setBlockEntityData(pItemEntity.getItem(), BlockEntityRegister.coffeeMug.get(),tag);
        super.onDestroyed(pItemEntity);

    }


    @Override
    public SoundEvent getEatingSound() {
        return super.getDrinkingSound();
    }
}
