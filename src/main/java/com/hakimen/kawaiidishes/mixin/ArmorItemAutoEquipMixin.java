package com.hakimen.kawaiidishes.mixin;

import com.hakimen.kawaiidishes.registry.EnchantmentRegister;
import com.hakimen.kawaiidishes.utils.item.EnchantUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Equipment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public class ArmorItemAutoEquipMixin {

    @Inject(at = @At("HEAD"), method = "inventoryTick")
    public void inventoryTick(ItemStack pStack, World pLevel, Entity pEntity, int pPartialTicks, boolean someBoolean, CallbackInfo ci){
        if(pStack.getItem() instanceof Equipment equipable && pEntity instanceof PlayerEntity player) {
            boolean hasAutoEquip = EnchantUtils.hasEnchant(pStack, EnchantmentRegister.AUTO_EQUIP_CURSE.get());
            EquipmentSlot targetSlot = equipable.getSlotType();
            if(player.getEquippedStack(targetSlot).equals(ItemStack.EMPTY) && hasAutoEquip && !player.getItemCooldownManager().isCoolingDown(pStack.getItem())){
                player.equipStack(targetSlot, pStack.copy());
                pStack.setCount(0);
            }
        }
    }
}
