package com.hakimen.kawaiidishes.enchantments;

import com.hakimen.kawaiidishes.item.armor.EarsArmorItem;
import com.hakimen.kawaiidishes.item.armor.HeadBandWithEarsArmorItem;
import com.hakimen.kawaiidishes.item.armor.MaidDressesWithTailArmorItem;
import com.hakimen.kawaiidishes.item.armor.TailArmorItem;
import com.hakimen.kawaiidishes.registry.EnchantmentRegister;
import com.hakimen.kawaiidishes.utils.AnimalType;
import com.hakimen.kawaiidishes.utils.item.EnchantUtils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FoxAptitudeEnchant extends Enchantment {
    public FoxAptitudeEnchant() {
        super(Rarity.RARE, EnchantmentTarget.ARMOR, new EquipmentSlot[]{
                EquipmentSlot.CHEST,
                EquipmentSlot.HEAD
        });

    }

    public static void applySelf(ItemStack stack, World level, PlayerEntity player) {

        ItemStack headItem = player.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack chestItem = player.getEquippedStack(EquipmentSlot.CHEST);

        boolean isHeadItemValid =
                (headItem.getItem() instanceof HeadBandWithEarsArmorItem headBandWithEarsArmorItem && headBandWithEarsArmorItem.getEarsType().equals(AnimalType.FOX)) ||
                        (headItem.getItem() instanceof EarsArmorItem earsArmorItem && earsArmorItem.getEarsType().equals(AnimalType.FOX));

        boolean isChestItemValid = (chestItem.getItem() instanceof MaidDressesWithTailArmorItem maidDressesWithTailArmorItem && maidDressesWithTailArmorItem.getTailType().equals(AnimalType.FOX)) ||
                (chestItem.getItem() instanceof TailArmorItem tailArmorItem && tailArmorItem.getTailType().equals(AnimalType.FOX));

        int amp = Math.round((EnchantUtils.getEnchantLevel(headItem, EnchantmentRegister.FOX_APTITUDE.get()) + EnchantUtils.getEnchantLevel(chestItem, EnchantmentRegister.FOX_APTITUDE.get()))/2f);

        if (isHeadItemValid && isChestItemValid) {
            if(level.isNight()){
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 15*20));
            }

            if(player.isSprinting()) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 15*20, amp-1, false, false,false));
            }
        }
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        Item item = stack.getItem();

        // Check for fox tails
        if (item instanceof MaidDressesWithTailArmorItem maidDressesWithTailArmorItem) {
            return maidDressesWithTailArmorItem.getTailType().equals(AnimalType.FOX);
        } else if (item instanceof TailArmorItem tailArmorItem) {
            return tailArmorItem.getTailType().equals(AnimalType.FOX);
        }

        //Check for fox ears
        if (item instanceof HeadBandWithEarsArmorItem headBandWithEarsArmorItem) {
            return headBandWithEarsArmorItem.getEarsType().equals(AnimalType.FOX);
        } else if (item instanceof EarsArmorItem earsArmorItem) {
            return earsArmorItem.getEarsType().equals(AnimalType.FOX);
        }

        return super.isAcceptableItem(stack);
    }

    @Override
    public int getMinPower(int lvl) {
        return lvl * 10;
    }

    @Override
    public int getMaxPower(int lvl) {
        return lvl * 15;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }
}
