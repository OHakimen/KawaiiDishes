package com.hakimen.kawaiidishes.items;

import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class Hat extends Item {
    public Hat() {
        super(new Properties().stacksTo(1).tab(ItemRegister.cosmetics));
    }

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlot armorType, Entity entity) {
        return armorType.equals(EquipmentSlot.HEAD);
    }

    @Nullable
    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_LEATHER;
    }


}
