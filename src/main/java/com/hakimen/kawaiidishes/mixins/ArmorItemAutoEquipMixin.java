package com.hakimen.kawaiidishes.mixins;

import com.hakimen.kawaiidishes.registry.EnchantmentRegister;
import com.hakimen.kawaiidishes.utils.item.EnchantUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public class ArmorItemAutoEquipMixin {

    @Inject(at = @At("HEAD"), method = "inventoryTick")
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pPartialTicks, boolean someBoolean, CallbackInfo ci){
        if(pStack.getItem() instanceof Equipable equipable && pEntity instanceof Player player) {
            boolean hasAutoEquip = EnchantUtils.hasEnchant(pStack, EnchantmentRegister.AUTO_EQUIP_CURSE.get());
            EquipmentSlot targetSlot = equipable.getEquipmentSlot();
            if(player.getItemBySlot(targetSlot).equals(ItemStack.EMPTY) && hasAutoEquip && !player.getCooldowns().isOnCooldown(pStack.getItem())){
                player.setItemSlot(targetSlot, pStack.copy());
                pStack.setCount(0);
            }
        }
    }
}
