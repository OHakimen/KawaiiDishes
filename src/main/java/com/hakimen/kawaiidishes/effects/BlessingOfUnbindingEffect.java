package com.hakimen.kawaiidishes.effects;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.neoforge.common.EffectCure;

import java.util.List;
import java.util.Set;

public class BlessingOfUnbindingEffect extends MobEffect {
    public BlessingOfUnbindingEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x00FFBE);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tick, int amplifier) {
        return tick == 1;
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if(entity instanceof Player player){
            List.of(
                    EquipmentSlot.HEAD,
                    EquipmentSlot.CHEST,
                    EquipmentSlot.LEGS,
                    EquipmentSlot.FEET
            ).forEach(
                    equipmentSlot -> {
                        ItemStack stack = player.getItemBySlot(equipmentSlot);
                        if(stack.has(DataComponents.ENCHANTMENTS) && stack.get(DataComponents.ENCHANTMENTS).keySet().stream().filter(enchantmentHolder -> enchantmentHolder.is(Enchantments.BINDING_CURSE)).count() > 0){
                            player.getCooldowns().addCooldown(stack.getItem(), 5 * 20);
                            if(player.getInventory().getFreeSlot() == -1){
                                player.setItemSlot(equipmentSlot, ItemStack.EMPTY);
                                player.level().addFreshEntity(
                                        new ItemEntity(player.level(), player.getX() + 0.5, player.getY() + 0.5, player.getZ() + 0.5,stack)
                                );
                            }else{
                                player.addItem(stack);
                            }
                        }
                    }
            );
        }
        return true;
    }

    @Override
    public void fillEffectCures(Set<EffectCure> cures, MobEffectInstance effectInstance) {

    }
}
