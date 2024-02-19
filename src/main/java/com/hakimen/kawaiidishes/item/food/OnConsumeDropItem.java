package com.hakimen.kawaiidishes.item.food;

import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class OnConsumeDropItem extends Item {

    ItemStack toDrop;
    public OnConsumeDropItem(Properties props, ItemStack toDrop) {
        super(props);
        this.toDrop = toDrop;
    }


    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof Player player) {
            if(player.getInventory().hasAnyMatching(item -> item.is(ItemRegister.MUG.get()) && item.getCount() < item.getMaxStackSize()) || player.getInventory().getFreeSlot() != -1){
                player.addItem(toDrop.copy());
            }else if (player.getInventory().getFreeSlot() == -1) {
                level.addFreshEntity(new ItemEntity(level,
                        player.getX() + 0.5 + Mth.nextDouble(level.random, -0.25, 0.25),
                        player.getY() + 0.5 + Mth.nextDouble(level.random, -0.25, 0.25),
                        player.getZ() + 0.5 + Mth.nextDouble(level.random, -0.25, 0.25),
                        toDrop.copy()));
            }
        }

        return super.finishUsingItem(itemStack, level, livingEntity);
    }
}
