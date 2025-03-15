package com.hakimen.kawaiidishes.item.armor;

import com.hakimen.kawaiidishes.item.component.KawaiiDyeableComponent;
import com.hakimen.kawaiidishes.registry.DataComponentRegister;
import com.hakimen.kawaiidishes.utils.DyeableUtils;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoItem;

import java.util.List;

public abstract class GeoArmorItem extends ArmorItem implements GeoItem {
    public GeoArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(Holder.direct(pMaterial), pType, pProperties.component(DataComponentRegister.DYEABLE, KawaiiDyeableComponent.DEFAULT).stacksTo(1));
    }


    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        DyeableUtils.makeTooltips(pStack.get(DataComponentRegister.DYEABLE), pTooltipComponents, pTooltipFlag);
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
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

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if ((pSlotId == 100 || pSlotId == 101 || pSlotId == 102 || pSlotId == 103) && pEntity instanceof Player player) {
            onArmorTick(pStack,pLevel, player);
        }
        super.inventoryTick(pStack,pLevel,pEntity,pSlotId,pIsSelected);
    }

    public void onArmorTick(ItemStack stack, Level level, Player player){

    }
}
