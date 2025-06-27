package com.hakimen.kawaiidishes.item;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class SeatItem extends BlockItem implements IDyeableItem{
    public SeatItem(Block pBlock, Settings pProperties) {
        super(pBlock, pProperties);
    }
    @Override
    public void appendTooltip(ItemStack pStack, @Nullable World pLevel, List<Text> pTooltipComponents, TooltipContext pIsAdvanced) {
        if((hasBaseColor(pStack) || hasOverlayColor(pStack)) && !pIsAdvanced.isAdvanced() ){
            pTooltipComponents.add(Text.translatable("item.dyed").fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
        }else if((hasBaseColor(pStack) && !pIsAdvanced.isCreative())){
            pTooltipComponents.add(Text.translatable("item.kawaiidishes.base_dye", "0x"+Integer.toString(getBaseColor(pStack),16).toUpperCase()).fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
        }else{
            pTooltipComponents.add(Text.translatable("item.kawaiidishes.dyeable").fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
        }
    }

    @Override
    public boolean hasOverlay(ItemStack stack) {
        return false;
    }

}
