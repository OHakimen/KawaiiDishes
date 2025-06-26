package com.hakimen.kawaiidishes.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class BlessingOfUnbindingEffect extends MobEffect {
    public BlessingOfUnbindingEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x00FFBE);
    }


    @Override
    public boolean isDurationEffectTick(int tick, int amplifier) {
        return tick == 1;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if(entity instanceof Player player){
            for (int i = 0; i < 4; i++) {
                EquipmentSlot slot = EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR,i);
                ItemStack stack = player.getItemBySlot(slot);
                if(EnchantmentHelper.hasBindingCurse(stack)){
                    player.getCooldowns().addCooldown(stack.getItem(), 5 * 20);
                    if(player.getInventory().getFreeSlot() == -1){
                        player.setItemSlot(slot, ItemStack.EMPTY);
                        player.level().addFreshEntity(
                                new ItemEntity(player.level(), player.getX() + 0.5,player.getY() + 0.5,player.getZ() + 0.5,stack)
                        );
                    }else{
                        player.addItem(stack);
                    }
                }
            }
        }
    }
}
