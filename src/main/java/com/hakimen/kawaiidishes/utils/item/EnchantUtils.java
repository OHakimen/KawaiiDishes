package com.hakimen.kawaiidishes.utils.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

public class EnchantUtils {
    public static boolean hasEnchant(ItemStack stack, Enchantment enchantment){
        return EnchantmentHelper.get(stack).keySet().stream().anyMatch((ent) -> ent.equals(enchantment));
    }


    public static int getEnchantLevel(ItemStack stack, Enchantment enchantment){
        return EnchantmentHelper.get(stack).getOrDefault(enchantment, 0);
    }
}
