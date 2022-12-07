package com.hakimen.kawaiidishes.items;


import com.hakimen.kawaiidishes.registry.EffectRegister;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CatHeadband extends Headband{
    public CatHeadband(Item item) {
        super(item);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if(player.getInventory().getArmor(EquipmentSlot.HEAD.getIndex()).getItem() instanceof CatHeadband){
            player.forceAddEffect(new MobEffectInstance(EffectRegister.nekoEffect.get(),14*20,0,false,false),player);
        }
        super.onArmorTick(stack,level,player);
    }
}
