package com.hakimen.kawaiidishes.items;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;


public class Candy extends BlockItem {
    public Candy(Block pBlock, int nutrition, float saturation) {
        super(pBlock, new Properties().food(new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation).fast().build()));
    }


    @Override
    protected boolean canPlace(BlockPlaceContext pContext, BlockState pState) {
        return pContext.getPlayer().isShiftKeyDown();
    }
}
