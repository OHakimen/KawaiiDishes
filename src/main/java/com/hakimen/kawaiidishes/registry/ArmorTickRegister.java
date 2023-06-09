package com.hakimen.kawaiidishes.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.util.TriConsumer;

public class ArmorTickRegister {
    public static TriConsumer<ItemStack, Level, Player> NULL = (stack, level, player) -> {

    };

    public static TriConsumer<ItemStack, Level, Player> maidArmorTick = (stack,level,player) -> {
        player.forceAddEffect(new MobEffectInstance(EffectRegister.kawaiiEffect.get(),14*20,0,false,false),player);
    };

    public static TriConsumer<ItemStack, Level, Player> foxArmorTick = (stack,level,player) -> {
        var head = player.getItemBySlot(EquipmentSlot.HEAD);
        if((head.getItem().toString().contains("fox")) &&
                player.getItemBySlot(EquipmentSlot.CHEST).getItem().toString().contains("fox")){
            player.forceAddEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,14*20,0,false,false),player);
        }
        if(player.getItemBySlot(EquipmentSlot.CHEST).toString().contains("maid")){
            maidArmorTick.accept(stack,level,player);
        }
    };
    public static TriConsumer<ItemStack, Level, Player> bunnyArmorTick = (stack,level,player) -> {
        var head = player.getItemBySlot(EquipmentSlot.HEAD);
        if((head.getItem().toString().contains("bunny")) &&
                player.getItemBySlot(EquipmentSlot.CHEST).getItem().toString().contains("bunny")){
            player.forceAddEffect(new MobEffectInstance(MobEffects.JUMP,14*20,0,false,false),player);
            player.fallDistance = 0;
        }
        if(player.getItemBySlot(EquipmentSlot.CHEST).toString().contains("maid")){
            maidArmorTick.accept(stack,level,player);
        }
    };
    public static TriConsumer<ItemStack, Level, Player> catArmorTick = (stack, level, player) -> {
        var head = player.getItemBySlot(EquipmentSlot.HEAD);
        if((head.getItem().toString().contains("cat")) && player.getItemBySlot(EquipmentSlot.CHEST).getItem().toString().contains("cat")) {
            player.forceAddEffect(new MobEffectInstance(EffectRegister.nekoEffect.get(),14*20,0,false,false),player);
        }
        if(player.getItemBySlot(EquipmentSlot.CHEST).toString().contains("maid")){
            maidArmorTick.accept(stack,level,player);
        }
    };
}
