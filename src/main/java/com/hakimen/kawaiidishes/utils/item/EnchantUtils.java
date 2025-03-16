//package com.hakimen.kawaiidishes.utils.item;
//
//import net.minecraft.core.Holder;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.enchantment.Enchantment;
//import net.minecraft.world.item.enchantment.EnchantmentHelper;
//
//public class EnchantUtils {
//    public static boolean hasEnchant(ItemStack stack, Enchantment enchantment){
//        return stack.getTagEnchantments().keySet().stream().anyMatch((ent) -> ent.value().equals(enchantment));
//    }
//
//
//    public static int getEnchantLevel(ItemStack stack, Enchantment enchantment){
//        return stack.getTagEnchantments().getLevel(Holder.direct(enchantment));
//    }
//}

// TODO: enchantments