package com.hakimen.kawaiidishes.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.hakimen.kawaiidishes.items.armor.CatMaidArmorItem;
import com.hakimen.kawaiidishes.registry.EffectRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Hat extends Item {
    public Hat() {
        super(new Properties().stacksTo(1).tab(ItemRegister.cosmetics));
    }

    public Hat(Item item) {
        super(new Properties().stacksTo(1).tab(ItemRegister.cosmetics).craftRemainder(item));
    }

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlot armorType, Entity entity) {
        return armorType.equals(EquipmentSlot.HEAD);
    }


    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if(slot == EquipmentSlot.HEAD){
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            UUID uuid = UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B");
            builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", (double)2, AttributeModifier.Operation.ADDITION));

            return builder.build();
        }else
            return super.getAttributeModifiers(slot,stack);
    }

    @Nullable
    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_LEATHER;
    }

    @Nullable
    @Override
    public EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return EquipmentSlot.HEAD;
    }
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        EquipmentSlot equipmentslot = Mob.getEquipmentSlotForItem(itemstack);
        ItemStack itemstack1 = pPlayer.getItemBySlot(equipmentslot);
        if (itemstack1.isEmpty()) {
            pPlayer.setItemSlot(equipmentslot, itemstack.copy());
            if (!pLevel.isClientSide()) {
                pPlayer.awardStat(Stats.ITEM_USED.get(this));
            }

            itemstack.setCount(0);
            return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
        } else {
            return InteractionResultHolder.fail(itemstack);
        }
    }
}
