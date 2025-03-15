package com.hakimen.kawaiidishes.item;

import com.hakimen.kawaiidishes.item.component.KawaiiDyeableComponent;
import com.hakimen.kawaiidishes.registry.DataComponentRegister;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SeatItem extends BlockItem {
    public SeatItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties.component(DataComponentRegister.DYEABLE, KawaiiDyeableComponent.DEFAULT));
    }

}
