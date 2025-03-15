package com.hakimen.kawaiidishes.custom.types;

import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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

    public void aromaTick(World pLevel, BlockPos pPos, BlockState pState, IncenseBlockEntity entity) {};
}
