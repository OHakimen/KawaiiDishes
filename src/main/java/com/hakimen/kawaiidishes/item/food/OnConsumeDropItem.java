package com.hakimen.kawaiidishes.item.food;

import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class OnConsumeDropItem extends Item {

    ItemStack toDrop;
    public OnConsumeDropItem(Settings props, ItemStack toDrop) {
        super(props);
        this.toDrop = toDrop;
    }


    @Override
    public ItemStack finishUsing(ItemStack itemStack, World level, LivingEntity livingEntity) {
        if (livingEntity instanceof PlayerEntity player) {
            if(player.getInventory().containsAny(item -> item.isOf(ItemRegister.MUG.get()) && item.getCount() < item.getMaxCount()) || player.getInventory().getEmptySlot() != -1){
                player.giveItemStack(toDrop.copy());
            }else if (player.getInventory().getEmptySlot() == -1) {
                level.spawnEntity(new ItemEntity(level,
                        player.getX() + 0.5 + MathHelper.nextDouble(level.random, -0.25, 0.25),
                        player.getY() + 0.5 + MathHelper.nextDouble(level.random, -0.25, 0.25),
                        player.getZ() + 0.5 + MathHelper.nextDouble(level.random, -0.25, 0.25),
                        toDrop.copy()));
            }
        }

        return super.finishUsing(itemStack, level, livingEntity);
    }
}
