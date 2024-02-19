package com.hakimen.kawaiidishes.item.armor;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.animatable.GeoItem;

public abstract class GeoArmorItem extends ArmorItem implements GeoItem {
    public GeoArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }


    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return -1;
    }

    @Override
    public boolean isEnchantable(ItemStack p_41456_) {
        return true;
    }
}
