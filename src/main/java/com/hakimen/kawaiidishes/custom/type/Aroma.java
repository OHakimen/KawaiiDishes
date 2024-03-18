package com.hakimen.kawaiidishes.custom.type;

import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class Aroma {

    TagKey<Item> items;
    int color;

    public Aroma(TagKey<Item> items, int color) {
        this.items = items;
        this.color = color;
    }

    public TagKey<Item> getItems() {
        return items;
    }

    public void setItems(TagKey<Item> items) {
        this.items = items;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void aromaTick(Level pLevel, BlockPos pPos, BlockState pState, IncenseBlockEntity entity) {};
}
