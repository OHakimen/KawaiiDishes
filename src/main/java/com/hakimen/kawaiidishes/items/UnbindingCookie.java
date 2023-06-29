package com.hakimen.kawaiidishes.items;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UnbindingCookie extends Item {
    public UnbindingCookie(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(pStack.getOrCreateTag().getBoolean("activated")){
            pTooltipComponents.add(Component.translatable("item.kawaiidishes.cookie_of_unbinding.active.desc"));

        }else {
            pTooltipComponents.add(Component.translatable("item.kawaiidishes.cookie_of_unbinding.unactive.desc"));
            pTooltipComponents.add(Component.translatable("item.kawaiidishes.cookie_of_unbinding.unactive.subdesc").setStyle(
                    Style.EMPTY.withColor(0x888888)
            ));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        for (EquipmentSlot slot:EquipmentSlot.values()) {
            if(pLivingEntity instanceof Player p && !p.getInventory().isEmpty()){
                if(EnchantmentHelper.hasBindingCurse(p.getItemBySlot(slot)) && pStack.getOrCreateTag().getBoolean("activated")) {
                    pLevel.addFreshEntity(new ItemEntity(
                            pLevel,
                            p.getX(),
                            p.getY(),
                            p.getZ(),
                            p.getItemBySlot(slot)));
                    p.setItemSlot(slot, ItemStack.EMPTY);
                    p.addEffect(new MobEffectInstance(MobEffects.CONFUSION,20*8,1));
                    p.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,20*5));
                }
            }
            if(pLivingEntity instanceof Player p && p.getInventory().isEmpty() ){
                if(EnchantmentHelper.hasBindingCurse(p.getItemBySlot(slot)) && pStack.getOrCreateTag().getBoolean("activated")) {
                    p.addItem(p.getItemBySlot(slot));
                    p.setItemSlot(slot, ItemStack.EMPTY);
                    p.addEffect(new MobEffectInstance(MobEffects.CONFUSION,20*8,1));
                    p.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,20*5));
                }
            }

        }
        pStack.shrink(1);
        return pStack;
    }

    @Override
    public void onCraftedBy(ItemStack pStack, Level pLevel, Player pPlayer) {

        for (EquipmentSlot slot: EquipmentSlot.values()){
            if(EnchantmentHelper.hasBindingCurse(pPlayer.getItemBySlot(slot))){
                pStack.getOrCreateTag().putBoolean("activated", true);
                return;
            }
        }
        pStack.getOrCreateTag().putBoolean("activated", false);
        super.onCraftedBy(pStack, pLevel, pPlayer);
    }
}
