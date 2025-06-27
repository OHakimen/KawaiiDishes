package com.hakimen.kawaiidishes.effects;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class BlessingOfUnbindingEffect extends StatusEffect {
    public BlessingOfUnbindingEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0x00FFBE);
    }


    @Override
    public boolean canApplyUpdateEffect(int tick, int amplifier) {
        return tick == 1;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(entity instanceof PlayerEntity player){
            for (int i = 0; i < 4; i++) {
                EquipmentSlot slot = EquipmentSlot.fromTypeIndex(EquipmentSlot.Type.ARMOR,i);
                ItemStack stack = player.getEquippedStack(slot);
                if(EnchantmentHelper.hasBindingCurse(stack)){
                    player.getItemCooldownManager().set(stack.getItem(), 5 * 20);
                    if(player.getInventory().getEmptySlot() == -1){
                        player.equipStack(slot, ItemStack.EMPTY);
                        player.getWorld().spawnEntity(
                                new ItemEntity(player.getWorld(), player.getX() + 0.5,player.getY() + 0.5,player.getZ() + 0.5,stack)
                        );
                    }else{
                        player.giveItemStack(stack);
                    }
                }
            }
        }
    }
}
