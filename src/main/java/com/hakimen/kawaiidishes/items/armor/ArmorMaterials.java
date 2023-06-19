package com.hakimen.kawaiidishes.items.armor;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public class ArmorMaterials {
    public static ArmorMaterial tail = new ArmorMaterial() {
        @Override
        public int getDurabilityForType(ArmorItem.Type pSlot) {
            return -1;
        }

        @Override
        public int getDefenseForType(ArmorItem.Type pSlot) {
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
            return "tail";
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
        public int getDurabilityForType(ArmorItem.Type pSlot) {
            return -1;
        }

        @Override
        public int getDefenseForType(ArmorItem.Type pSlot) {
            switch (pSlot){
                case CHESTPLATE:
                    return 4;
                case LEGGINGS:
                    return 2;
                case BOOTS:
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
    public static ArmorMaterial tailedDress = new ArmorMaterial() {
        @Override
        public int getDurabilityForType(ArmorItem.Type pSlot) {
            return -1;
        }

        @Override
        public int getDefenseForType(ArmorItem.Type pSlot) {
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
            return "tailed_maid_dress";
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
