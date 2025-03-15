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

public class BunnyHasteEnchantment extends Enchantment {
    public BunnyHasteEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.ARMOR, new EquipmentSlot[]{
                EquipmentSlot.CHEST,
                EquipmentSlot.HEAD
        });

    }

    public static void applySelf(ItemStack stack, World level, PlayerEntity player) {

        ItemStack headItem = player.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack chestItem = player.getEquippedStack(EquipmentSlot.CHEST);

        int amp = Math.round((EnchantUtils.getEnchantLevel(headItem, EnchantmentRegister.BUNNY_HASTE.get()) + EnchantUtils.getEnchantLevel(chestItem, EnchantmentRegister.BUNNY_HASTE.get()))/2f);


        boolean isHeadItemValid =
                (headItem.getItem() instanceof HeadBandWithEarsArmorItem headBandWithEarsArmorItem && headBandWithEarsArmorItem.getEarsType().equals(AnimalType.BUNNY)) ||
                        (headItem.getItem() instanceof EarsArmorItem earsArmorItem && earsArmorItem.getEarsType().equals(AnimalType.BUNNY));

        boolean isChestItemValid = (chestItem.getItem() instanceof MaidDressesWithTailArmorItem maidDressesWithTailArmorItem && maidDressesWithTailArmorItem.getTailType().equals(AnimalType.BUNNY)) ||
                (chestItem.getItem() instanceof TailArmorItem tailArmorItem && tailArmorItem.getTailType().equals(AnimalType.BUNNY));

        if (isHeadItemValid && isChestItemValid) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 15*20, amp - 1));
        }
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        Item item = stack.getItem();

        // Check for fox tails
        if (item instanceof MaidDressesWithTailArmorItem maidDressesWithTailArmorItem) {
            return maidDressesWithTailArmorItem.getTailType().equals(AnimalType.BUNNY);
        } else if (item instanceof TailArmorItem tailArmorItem) {
            return tailArmorItem.getTailType().equals(AnimalType.BUNNY);
        }

        //Check for fox ears
        if (item instanceof HeadBandWithEarsArmorItem headBandWithEarsArmorItem) {
            return headBandWithEarsArmorItem.getEarsType().equals(AnimalType.BUNNY);
        } else if (item instanceof EarsArmorItem earsArmorItem) {
            return earsArmorItem.getEarsType().equals(AnimalType.BUNNY);
        }

        return super.isAcceptableItem(stack);
    }


    @Override
    public int getMaxLevel() {
        return 3;
    }
}
