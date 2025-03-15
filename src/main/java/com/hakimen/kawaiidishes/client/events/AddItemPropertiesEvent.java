package com.hakimen.kawaiidishes.client.events;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.item.IFourColorDyeableItem;
import com.hakimen.kawaiidishes.item.component.KawaiiDyeableComponent;
import com.hakimen.kawaiidishes.registry.DataComponentRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.List;

@EventBusSubscriber(modid = KawaiiDishes.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
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


        ItemProperties.register(ItemRegister.THIGH_HIGHS.get(), ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "decoration"), ((pStack, pLevel, pEntity, pSeed) -> pStack.get(DataComponents.CUSTOM_DATA).copyTag().getInt("Decoration")));
        itemsWithState.forEach(item -> {
            ItemProperties.register(item,ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "state"), (pStack, pLevel, pEntity, pSeed) -> {
                float compoundValue = 0;

                KawaiiDyeableComponent.KawaiiDyeable dyeable = pStack.get(DataComponentRegister.DYEABLE);
                compoundValue += dyeable.isHasOverlay() ? 1 : 0;
                compoundValue += dyeable.isHasSecondaryOverlay() ? 2 : 0;


                // 0 sem nada
                // 1 overlay no vestido
                // 2 overlay na cauda
                // 3 overlay em ambos

                return compoundValue;
            });
        });

        itemsWithOverlay.forEach(item -> {
            ItemProperties.register(item,ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "has_overlay"), ((pStack, pLevel, pEntity, pSeed) -> pStack.get(DataComponentRegister.DYEABLE).isHasOverlay() ? 1 : 0));
        });
    }
}
