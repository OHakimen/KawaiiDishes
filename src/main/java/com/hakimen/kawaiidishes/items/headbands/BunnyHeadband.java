package com.hakimen.kawaiidishes.items.headbands;


import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BunnyHeadband extends Headband {
    public BunnyHeadband(Item item) {
        super(item);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {

        super.onArmorTick(stack,level,player);
    }
}
