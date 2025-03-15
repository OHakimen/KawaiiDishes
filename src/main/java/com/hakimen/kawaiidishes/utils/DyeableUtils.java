package com.hakimen.kawaiidishes.utils;

import com.hakimen.kawaiidishes.item.component.KawaiiDyeableComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class DyeableUtils {
    public static void makeTooltips(KawaiiDyeableComponent.KawaiiDyeable data, List<Component> tooltip, TooltipFlag flags){
        if(flags.isAdvanced()){
            if(data.getBase() != -1){
                tooltip.add(Component.translatable("item.kawaiidishes.base_dye",  "0x"+Integer.toHexString(data.getBase() & 0xFFFFFF).toUpperCase())
                        .withStyle(ChatFormatting.DARK_GRAY));
            }
            if(data.getOverlay() != -1){
                tooltip.add(Component.translatable("item.kawaiidishes.overlay_dye",  "0x"+Integer.toHexString(data.getOverlay() & 0xFFFFFF).toUpperCase())
                        .withStyle(ChatFormatting.DARK_GRAY));
            }
            if(data.getSecondaryBase() != -1){
                tooltip.add(Component.translatable("item.kawaiidishes.base_secondary_dye",  "0x"+Integer.toHexString(data.getSecondaryBase() & 0xFFFFFF).toUpperCase())
                        .withStyle(ChatFormatting.DARK_GRAY));
            }
            if(data.getSecondaryOverlay() != -1){
                tooltip.add(Component.translatable("item.kawaiidishes.overlay_secondary_color",  "0x"+Integer.toHexString(data.getSecondaryOverlay() & 0xFFFFFF).toUpperCase())
                        .withStyle(ChatFormatting.DARK_GRAY));
            }
        }
        else if(data.getBase() != -1 || data.getOverlay() != -1 || data.getSecondaryBase() != -1 ||data.getSecondaryOverlay() != -1) {
            tooltip.add(Component.translatable("item.dyed")
                    .withStyle(ChatFormatting.DARK_GRAY));
        }
    }
}
