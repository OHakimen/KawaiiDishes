package com.hakimen.kawaiidishes.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface IDyeableItem {
    String colors = "Colors";
    String base = "Base";
    String overlay = "Overlay";
    

    int defaultColor = 0xFFFFFF;
    default int getBaseColor(ItemStack stack){
        CompoundTag colorData = stack.getTagElement(colors);
        return colorData != null && colorData.contains(base, Tag.TAG_ANY_NUMERIC) ? colorData.getInt(base) : defaultColor;
    }

    default int getOverlayColor(ItemStack stack){
        CompoundTag colorData = stack.getTagElement(colors);
        return colorData != null && colorData.contains(overlay, Tag.TAG_ANY_NUMERIC) ? colorData.getInt(overlay) : defaultColor;
    }

    default boolean hasBaseColor(ItemStack stack){
        CompoundTag colorData = stack.getTagElement(colors);
        return colorData != null && colorData.contains(base, Tag.TAG_ANY_NUMERIC);
    }

    default boolean hasOverlayColor(ItemStack stack){
        CompoundTag colorData = stack.getTagElement(colors);
        return colorData != null && colorData.contains(overlay, Tag.TAG_ANY_NUMERIC);
    }

    default void clearBaseColor(ItemStack stack) {
        CompoundTag colorData = stack.getTagElement(colors);
        if (colorData != null && colorData.contains(base)) {
            colorData.remove(base);
        }
    }

    default void clearOverlayColor(ItemStack stack) {
        CompoundTag colorData = stack.getTagElement(colors);
        if (colorData != null && colorData.contains(overlay)) {
            colorData.remove(overlay);
        }
    }

    boolean hasOverlay(ItemStack stack);

    default void setBaseColor(ItemStack stack, int color) {
        stack.getOrCreateTagElement(colors).putInt(base, color);
    }
    default void setOverlayColor(ItemStack stack, int color) {
        stack.getOrCreateTagElement(colors).putInt(overlay, color);
    }

    static ItemStack dyeBase(ItemStack stack, List<DyeItem> dyes){
        ItemStack itemstack = ItemStack.EMPTY;

        IDyeableItem dyeableItem = null;

        int[] colorResult = {0,0,0};

        int baseColorCount = 0;

        Item item = stack.getItem();
        if(item instanceof IDyeableItem){
            dyeableItem = (IDyeableItem) item;
            itemstack = stack.copyWithCount(1);
            if (dyeableItem.hasBaseColor(stack)) {
                int k = dyeableItem.getBaseColor(itemstack);
                float r = (float)(k >> 16 & 255) / 255.0F;
                float g = (float)(k >> 8 & 255) / 255.0F;
                float b = (float)(k & 255) / 255.0F;
                colorResult[0] += (int)(r * 255.0F);
                colorResult[1] += (int)(g * 255.0F);
                colorResult[2] += (int)(b * 255.0F);
                ++baseColorCount;
            }
        }

        for(DyeItem dyeitem : dyes) {
            float[] dyeColors = dyeitem.getDyeColor().getTextureDiffuseColors();
            int r = (int)(dyeColors[0] * 255.0F);
            int g = (int)(dyeColors[1] * 255.0F);
            int b = (int)(dyeColors[2] * 255.0F);
            colorResult[0] += r;
            colorResult[1] += g;
            colorResult[2] += b;
            ++baseColorCount;
        }

        if (dyeableItem == null) {
            return ItemStack.EMPTY;
        } else {
            int j1 = colorResult[0] / baseColorCount;
            int k1 = colorResult[1] / baseColorCount;
            int l1 = colorResult[2] / baseColorCount;
            int rgb = (j1 << 8) + k1;
            rgb = (rgb << 8) + l1;
            dyeableItem.setBaseColor(itemstack, rgb);
            return itemstack;
        }
    }

    static ItemStack dyeOverlay(ItemStack stack, List<DyeItem> dyes){
        ItemStack itemstack = ItemStack.EMPTY;

        IDyeableItem dyeableItem = null;

        int[] colorResult = {0,0,0};

        int baseColorCount = 0;

        Item item = stack.getItem();
        if(item instanceof IDyeableItem){
            dyeableItem = (IDyeableItem) item;
            itemstack = stack.copyWithCount(1);
            if (dyeableItem.hasOverlayColor(stack)) {
                int k = dyeableItem.getOverlayColor(itemstack);
                float r = (float)(k >> 16 & 255) / 255.0F;
                float g = (float)(k >> 8 & 255) / 255.0F;
                float b = (float)(k & 255) / 255.0F;
                colorResult[0] += (int)(r * 255.0F);
                colorResult[1] += (int)(g * 255.0F);
                colorResult[2] += (int)(b * 255.0F);
                ++baseColorCount;
            }
        }

        for(DyeItem dyeitem : dyes) {
            float[] dyeColors = dyeitem.getDyeColor().getTextureDiffuseColors();
            int r = (int)(dyeColors[0] * 255.0F);
            int g = (int)(dyeColors[1] * 255.0F);
            int b = (int)(dyeColors[2] * 255.0F);
            colorResult[0] += r;
            colorResult[1] += g;
            colorResult[2] += b;
            ++baseColorCount;
        }

        if (dyeableItem == null) {
            return ItemStack.EMPTY;
        } else {
            int r = colorResult[0] / baseColorCount;
            int g = colorResult[1] / baseColorCount;
            int b = colorResult[2] / baseColorCount;
            int rgb = (r << 8) + g;
            rgb = (rgb << 8) + b;
            dyeableItem.setOverlayColor(itemstack, rgb);
            return itemstack;
        }
    }
}
