package com.hakimen.kawaiidishes.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class Horns extends Hat{
    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
        return true;
    }
}
