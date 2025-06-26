package com.hakimen.kawaiidishes.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class DecorationItem extends Item {

    ResourceLocation thighHighDecorationLocation;

    public DecorationItem(Properties properties, ResourceLocation resourceLocation) {
        super(properties);
        thighHighDecorationLocation = resourceLocation;
    }

    public ResourceLocation getThighHighDecorationLocation() {
        return thighHighDecorationLocation;
    }
}
