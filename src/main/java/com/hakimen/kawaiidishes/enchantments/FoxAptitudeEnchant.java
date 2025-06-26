package com.hakimen.kawaiidishes.enchantments;

import com.hakimen.kawaiidishes.item.armor.EarsArmorItem;
import com.hakimen.kawaiidishes.item.armor.HeadBandWithEarsArmorItem;
import com.hakimen.kawaiidishes.item.armor.MaidDressesWithTailArmorItem;
import com.hakimen.kawaiidishes.item.armor.TailArmorItem;
import com.hakimen.kawaiidishes.registry.EnchantmentRegister;
import com.hakimen.kawaiidishes.utils.AnimalType;
import com.hakimen.kawaiidishes.utils.item.EnchantUtils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;

public class FoxAptitudeEnchant extends Enchantment {
    public FoxAptitudeEnchant() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR, new EquipmentSlot[]{
                EquipmentSlot.CHEST,
                EquipmentSlot.HEAD
        });

    }

    public static void applySelf(ItemStack stack, Level level, Player player) {

        ItemStack headItem = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chestItem = player.getItemBySlot(EquipmentSlot.CHEST);

        boolean isHeadItemValid =
                (headItem.getItem() instanceof HeadBandWithEarsArmorItem headBandWithEarsArmorItem && headBandWithEarsArmorItem.getEarsType().equals(AnimalType.FOX)) ||
                        (headItem.getItem() instanceof EarsArmorItem earsArmorItem && earsArmorItem.getEarsType().equals(AnimalType.FOX));

        boolean isChestItemValid = (chestItem.getItem() instanceof MaidDressesWithTailArmorItem maidDressesWithTailArmorItem && maidDressesWithTailArmorItem.getTailType().equals(AnimalType.FOX)) ||
                (chestItem.getItem() instanceof TailArmorItem tailArmorItem && tailArmorItem.getTailType().equals(AnimalType.FOX));

        int amp = Math.round((EnchantUtils.getEnchantLevel(headItem, EnchantmentRegister.FOX_APTITUDE.get()) + EnchantUtils.getEnchantLevel(chestItem, EnchantmentRegister.FOX_APTITUDE.get()))/2f);

        if (isHeadItemValid && isChestItemValid) {
            if(level.isNight()){
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 15*20));
            }

            if(player.isSprinting()) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 15*20, amp-1, false, false,false));
            }
        }
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
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

        return super.canEnchant(stack);
    }

    @Override
    public int getMinCost(int lvl) {
        return lvl * 10;
    }

    @Override
    public int getMaxCost(int lvl) {
        return lvl * 15;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }
}
