package com.hakimen.kawaiidishes.utils;

import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.time.Instant;
import java.time.Month;
import java.util.Date;
import java.util.Random;

public class MaidMobEventHandler {

    public static String[] colors = new String[]{
            "black",
            "blue",
            "brown",
            "cyan",
            "gray",
            "green",
            "light_blue",
            "light_gray",
            "lime",
            "magenta",
            "orange",
            "pink",
            "purple",
            "red",
            "white",
            "yellow"
    };

    public static String[] types = new String[]{
            "none",
            "fox",
            "cat",
    };

    public static String[] catColors = new String[]{
            "black",
            "white",
            "caramel",
    };

    public static String[] foxColors = new String[]{
            "black",
            "white",
            "red",
    };




    public static ItemStack[] armorBuild(Random r) {

        String color, type, typeColor = "";

        color = colors[r.nextInt(colors.length)];

        type = types[r.nextInt(types.length)];

        switch (type) {
            case "fox" -> {
                typeColor = foxColors[r.nextInt(foxColors.length)];
            }
            case "cat" -> {
                typeColor = catColors[r.nextInt(catColors.length)];
            }
        }

        ItemStack[] stacks = new ItemStack[]{
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY
        };
        for (var item : ItemRegister.ITEMS.getEntries().stream().toList()) {
            var stack = item.get().getRegistryName().toString();
            if(!stacks[0].equals(ItemStack.EMPTY) && !stacks[1].equals(ItemStack.EMPTY)){
                break;
            }
            if (stack.contains(color + "_headband")) {
                if (!type.equals("none")) {
                    if (stack.endsWith("_" + type + "_ears_" + typeColor)) {
                        stacks[0] = item.get().getDefaultInstance();
                    }
                } else if (!stack.contains("ears")) {
                    stacks[0] = item.get().getDefaultInstance();
                }
            }else if (stack.contains(color + "_maid_dress")) {
                if (!type.equals("none")) {
                    if (stack.endsWith("_" + type + "_tail_" + typeColor)) {
                        stacks[1] = item.get().getDefaultInstance();
                    }
                }else if(!stack.contains("tail")){
                    stacks[1] = item.get().getDefaultInstance();
                }
            }
        }

        stacks[2] = r.nextInt(2) == 1 ? ItemRegister.whiteThighHighs.get().getDefaultInstance() : ItemRegister.blackThighHighs.get().getDefaultInstance();

        stacks[3] = r.nextInt(2) == 1 ? ItemRegister.whiteThighHighsShoes.get().getDefaultInstance() : ItemRegister.blackThighHighsShoes.get().getDefaultInstance();
        if(r.nextFloat(0,1) < (Date.from(Instant.now()).getMonth() == Month.OCTOBER.getValue() ? 1f :  0.05f)){
            stacks[0] = Items.JACK_O_LANTERN.getDefaultInstance();
        }
        return stacks;
    }
}
