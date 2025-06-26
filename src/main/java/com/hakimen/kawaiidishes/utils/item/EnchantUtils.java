package com.hakimen.kawaiidishes.utils.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class EnchantUtils {
    public static boolean hasEnchant(ItemStack stack, Enchantment enchantment){
        return EnchantmentHelper.getEnchantments(stack).keySet().stream().anyMatch((ent) -> ent.equals(enchantment));
    }


    public static int getEnchantLevel(ItemStack stack, Enchantment enchantment){
        return EnchantmentHelper.getEnchantments(stack).getOrDefault(enchantment, 0);
    }
}
