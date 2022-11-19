package com.hakimen.kawaiidishes.items.armor;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public class ArmorMaterials {
    public static ArmorMaterial catTail = new ArmorMaterial() {
        @Override
        public int getDurabilityForSlot(EquipmentSlot pSlot) {
            return -1;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlot pSlot) {
            return 2;
        }

        @Override
        public int getEnchantmentValue() {
            return 0;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_LEATHER;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return null;
        }

        @Override
        public String getName() {
            return "cat_tail";
        }

        @Override
        public float getToughness() {
            return 0;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }};
    public static ArmorMaterial maidDress = new ArmorMaterial() {
        @Override
        public int getDurabilityForSlot(EquipmentSlot pSlot) {
            return -1;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlot pSlot) {
            switch (pSlot){
                case CHEST:
                    return 4;
                case LEGS:
                    return 2;
                case FEET:
                    return 1;
            }
            return 3;
        }

        @Override
        public int getEnchantmentValue() {
            return 0;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_LEATHER;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return null;
        }

        @Override
        public String getName() {
            return "maid_dress";
        }

        @Override
        public float getToughness() {
            return 0;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }};
    public static ArmorMaterial catMaidDress = new ArmorMaterial() {
        @Override
        public int getDurabilityForSlot(EquipmentSlot pSlot) {
            return -1;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlot pSlot) {
            return 6;
        }

        @Override
        public int getEnchantmentValue() {
            return 0;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_LEATHER;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return null;
        }

        @Override
        public String getName() {
            return "cat_maid_dress";
        }

        @Override
        public float getToughness() {
            return 0;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }};
}
