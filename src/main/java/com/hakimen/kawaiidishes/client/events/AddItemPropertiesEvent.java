package com.hakimen.kawaiidishes.client.events;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.item.IDyeableItem;
import com.hakimen.kawaiidishes.item.IFourColorDyeableItem;
import com.hakimen.kawaiidishes.item.armor.HeadBandWithEarsArmorItem;
import com.hakimen.kawaiidishes.item.armor.MaidDressesWithTailArmorItem;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid = KawaiiDishes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AddItemPropertiesEvent {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {

        List<Item> itemsWithState = List.of(
                ItemRegister.MAID_DRESS_FOX_TAIL.get(),
                ItemRegister.HEAD_BAND_FOX_EARS.get(),
                ItemRegister.MAID_DRESS_BUNNY_TAIL.get(),
                ItemRegister.HEAD_BAND_BUNNY_EARS.get(),
                ItemRegister.MAID_DRESS_CAT_TAIL.get(),
                ItemRegister.HEAD_BAND_CAT_EARS.get());

        List<Item> itemsWithOverlay = List.of(
                ItemRegister.SHOES.get(),
                ItemRegister.HEAD_BAND.get(),
                ItemRegister.MAID_DRESS.get(),
                ItemRegister.FOX_TAIL.get(),
                ItemRegister.FOX_EARS.get(),
                ItemRegister.BUNNY_TAIL.get(),
                ItemRegister.BUNNY_EARS.get(),
                ItemRegister.CAT_TAIL.get(),
                ItemRegister.CAT_EARS.get());


        ItemProperties.register(ItemRegister.THIGH_HIGHS.get(), new ResourceLocation(KawaiiDishes.MODID, "decoration"), ((pStack, pLevel, pEntity, pSeed) -> pStack.getOrCreateTag().getInt("Decoration")));
        itemsWithState.forEach(item -> {
            ItemProperties.register(item,new ResourceLocation(KawaiiDishes.MODID, "state"), (pStack, pLevel, pEntity, pSeed) -> {
                float compoundValue = 0;

                IFourColorDyeableItem fourColoredItem = (IFourColorDyeableItem) pStack.getItem();

                compoundValue += fourColoredItem.hasPrimaryOverlay(pStack) ? 1 : 0;
                compoundValue += fourColoredItem.hasSecondaryOverlay(pStack) ? 2 : 0;

                // 0 sem nada
                // 1 overlay no vestido
                // 2 overlay na cauda
                // 3 overlay em ambos

                return compoundValue;
            });
        });

        itemsWithOverlay.forEach(item -> {
            ItemProperties.register(item,new ResourceLocation(KawaiiDishes.MODID, "has_overlay"), ((pStack, pLevel, pEntity, pSeed) -> ((IDyeableItem)pStack.getItem()).hasOverlay(pStack) ? 1 : 0));
        });
    }
}
