package com.hakimen.kawaiidishes.item;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class DecorationItem extends Item {

    Identifier thighHighDecorationLocation;

    public DecorationItem(Settings properties, Identifier resourceLocation) {
        super(properties);
        thighHighDecorationLocation = resourceLocation;
    }

    public Identifier getThighHighDecorationLocation() {
        return thighHighDecorationLocation;
    }
}
