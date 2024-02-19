package com.hakimen.kawaiidishes.client.events;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.block_entities.SeatBlockEntity;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.world.item.DyeColor;
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

                IncenseBlockEntity.Aromas aroma = incenseBlockEntity.getAroma();

                switch (aroma) {
                    case DecorativeAroma -> {
                        color = DyeColor.getColor(stack).getFireworkColor();
                    }
                    case PotionAroma -> {
                        color = PotionUtils.getColor(stack);
                    }
                    default -> {
                        color = aroma.color;
                    }
                }
                return color;
            }
            return -1;
        }, BlockRegister.INCENSE_GLASS.get());
    }
}
