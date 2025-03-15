package com.hakimen.kawaiidishes.item;

import com.hakimen.kawaiidishes.item.component.KawaiiDyeableComponent;
import com.hakimen.kawaiidishes.registry.DataComponentRegister;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

import java.util.Arrays;
import java.util.List;

public interface IFourColorDyeableItem {


    int defaultColor = 0xFFFFFFFF;

    static ItemStack dyePrimaryBase(ItemStack stack, List<DyeItem> dyes){

        if(stack.has(DataComponentRegister.DYEABLE.get())){
            KawaiiDyeableComponent.KawaiiDyeable data = stack.get(DataComponentRegister.DYEABLE.get());
            ItemStack itemstack = stack.copyWithCount(1);

            int finalColor = doDye(itemstack, data.getBase(), dyes);

            itemstack.update(DataComponentRegister.DYEABLE.get(), KawaiiDyeableComponent.DEFAULT, dyeable ->
                    new KawaiiDyeableComponent.KawaiiDyeableBuilder(dyeable).setBase(finalColor).build());
            return itemstack;
        }else{
            return ItemStack.EMPTY;
        }
    }

    static ItemStack dyePrimaryOverlay(ItemStack stack, List<DyeItem> dyes){

        if(stack.has(DataComponentRegister.DYEABLE.get())){
            KawaiiDyeableComponent.KawaiiDyeable data = stack.get(DataComponentRegister.DYEABLE.get());
            ItemStack itemstack = stack.copyWithCount(1);

            int finalColor = doDye(itemstack, data.getOverlay(), dyes);

            itemstack.update(DataComponentRegister.DYEABLE.get(), KawaiiDyeableComponent.DEFAULT, dyeable ->
                    new KawaiiDyeableComponent.KawaiiDyeableBuilder(dyeable).setOverlay(finalColor).setHasOverlay(true).build());
            return itemstack;
        }else{
            return ItemStack.EMPTY;
        }
    }

    static ItemStack dyeSecondaryBase(ItemStack stack, List<DyeItem> dyes){
        if(stack.has(DataComponentRegister.DYEABLE.get())){
            KawaiiDyeableComponent.KawaiiDyeable data = stack.get(DataComponentRegister.DYEABLE.get());
            ItemStack itemstack = stack.copyWithCount(1);

            int finalColor = doDye(itemstack, data.getBase(), dyes);

            itemstack.update(DataComponentRegister.DYEABLE.get(), KawaiiDyeableComponent.DEFAULT, dyeable ->
                    new KawaiiDyeableComponent.KawaiiDyeableBuilder(dyeable).setSecondaryBase(finalColor).build());
            return itemstack;
        }else{
            return ItemStack.EMPTY;
        }
    }

    static ItemStack dyeSecondaryOverlay(ItemStack stack, List<DyeItem> dyes){
        if(stack.has(DataComponentRegister.DYEABLE.get())){
            KawaiiDyeableComponent.KawaiiDyeable data = stack.get(DataComponentRegister.DYEABLE.get());
            ItemStack itemstack = stack.copyWithCount(1);

            int finalColor = doDye(itemstack, data.getSecondaryOverlay(), dyes);

            itemstack.update(DataComponentRegister.DYEABLE.get(), KawaiiDyeableComponent.DEFAULT, dyeable ->
                    new KawaiiDyeableComponent.KawaiiDyeableBuilder(dyeable).setSecondaryOverlay(finalColor).setHasSecondaryOverlay(true).build());
            return itemstack;
        }else{
            return ItemStack.EMPTY;
        }
    }

    static int doDye(ItemStack stack, int value, List<DyeItem> dyes){
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        int i1 = 0;
        if (value != -1) {
            int j1 = FastColor.ARGB32.red(value);
            int k1 = FastColor.ARGB32.green(value);
            int l1 = FastColor.ARGB32.blue(value);
            l += Math.max(j1, Math.max(k1, l1));
            i += j1;
            j += k1;
            k += l1;
            i1++;
        }

        for (DyeItem dyeitem : dyes) {
            int j3 = dyeitem.getDyeColor().getTextureDiffuseColor();
            int i2 = FastColor.ARGB32.red(j3);
            int j2 = FastColor.ARGB32.green(j3);
            int k2 = FastColor.ARGB32.blue(j3);
            l += Math.max(i2, Math.max(j2, k2));
            i += i2;
            j += j2;
            k += k2;
            i1++;
        }

        int l2 = i / i1;
        int i3 = j / i1;
        int k3 = k / i1;
        float f = (float)l / (float)i1;
        float f1 = (float)Math.max(l2, Math.max(i3, k3));
        l2 = (int)((float)l2 * f / f1);
        i3 = (int)((float)i3 * f / f1);
        k3 = (int)((float)k3 * f / f1);
        return FastColor.ARGB32.color(0xff, l2, i3, k3);
    }
}
