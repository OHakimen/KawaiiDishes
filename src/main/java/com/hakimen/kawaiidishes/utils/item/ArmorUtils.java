package com.hakimen.kawaiidishes.utils.item;

import com.hakimen.kawaiidishes.enchantments.BunnyHasteEnchantment;
import com.hakimen.kawaiidishes.enchantments.CatAuraEnchant;
import com.hakimen.kawaiidishes.enchantments.FoxAptitudeEnchant;
import com.hakimen.kawaiidishes.registry.EnchantmentRegister;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ArmorUtils {
    public static void applyEnchantmentEffects(ItemStack stack, World level, PlayerEntity player){
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
