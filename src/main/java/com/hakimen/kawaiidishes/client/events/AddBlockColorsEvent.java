package com.hakimen.kawaiidishes.client.events;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.aromas.DecorativeAroma;
import com.hakimen.kawaiidishes.aromas.PotionAroma;
import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.block_entities.SeatBlockEntity;
import com.hakimen.kawaiidishes.custom.type.Aroma;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@Mod.EventBusSubscriber(modid = KawaiiDishes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AddBlockColorsEvent {

    @SubscribeEvent
    public static void registerBlockColours(RegisterColorHandlersEvent.Block event) {
        event.register((state, view, pos, tintIndex) -> {
            return tintIndex == 0 ? (view.getBlockEntity(pos) != null ? ((SeatBlockEntity) view.getBlockEntity(pos)).getColor() : -1) : -1;
        }, BlockRegister.SEAT.get());

        event.register((state, view, pos, tintIndex) -> {
            if (view.getBlockEntity(pos) != null && tintIndex == 0) {
                int color = 0xffffff;

                IncenseBlockEntity incenseBlockEntity = (IncenseBlockEntity) view.getBlockEntity(pos);

                ItemStack stack = incenseBlockEntity.getInventory().getStackInSlot(0);

                Aroma aroma = incenseBlockEntity.getAromaFromId();

                if (aroma instanceof DecorativeAroma) {
                    color = stack.getItem() instanceof DyeItem dyeItem ? dyeItem.getDyeColor().getFireworkColor() : 0;
                } else if (aroma instanceof PotionAroma) {
                    color = PotionUtils.getColor(stack);
                } else {
                    color = aroma.getColor();
                }
                return color;
            }
            return -1;
        }, BlockRegister.INCENSE_GLASS.get());
    }
}
