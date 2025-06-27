package com.hakimen.kawaiidishes.item.armor;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import mod.azure.azurelib.animatable.GeoItem;

public abstract class GeoArmorItem extends ArmorItem implements GeoItem {
    public GeoArmorItem(ArmorMaterial pMaterial, Type pType, Settings pProperties) {
        super(pMaterial, pType, pProperties
                .maxDamage(-1));
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return true;
    }
}
