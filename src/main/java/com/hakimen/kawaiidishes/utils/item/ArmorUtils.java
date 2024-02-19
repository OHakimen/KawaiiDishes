package com.hakimen.kawaiidishes.utils.item;

import com.hakimen.kawaiidishes.enchantments.BunnyHasteEnchantment;
import com.hakimen.kawaiidishes.enchantments.CatAuraEnchant;
import com.hakimen.kawaiidishes.enchantments.FoxAptitudeEnchant;
import com.hakimen.kawaiidishes.registry.EnchantmentRegister;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ArmorUtils {
    public static void applyEnchantmentEffects(ItemStack stack, Level level, Player player){
        if(EnchantUtils.hasEnchant(stack, EnchantmentRegister.CAT_AURA.get())){
            CatAuraEnchant.applySelf(stack,level,player);
        }

        if(EnchantUtils.hasEnchant(stack, EnchantmentRegister.FOX_APTITUDE.get())){
            FoxAptitudeEnchant.applySelf(stack,level,player);
        }

        if(EnchantUtils.hasEnchant(stack, EnchantmentRegister.BUNNY_HASTE.get())){
            BunnyHasteEnchantment.applySelf(stack,level,player);
        }
    }
}
