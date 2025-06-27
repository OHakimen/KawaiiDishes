package com.hakimen.kawaiidishes.client.init;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.item.IDyeableItem;
import com.hakimen.kawaiidishes.item.IFourColorDyeableItem;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import com.hakimen.kawaiidishes.registry.ThighHighsDecorationRegister;
import java.util.List;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ItemPropertyInitializer {
    public static void init() {
        List<Item> itemsWithState = List.of(
                ItemRegister.MAID_DRESS_FOX_TAIL.get(),
                ItemRegister.HEAD_BAND_FOX_EARS.get(),
                ItemRegister.MAID_DRESS_BUNNY_TAIL.get(),
                ItemRegister.HEAD_BAND_BUNNY_EARS.get(),
                ItemRegister.MAID_DRESS_CAT_TAIL.get(),
                ItemRegister.HEAD_BAND_CAT_EARS.get()
        );

        List<Item> itemsWithOverlay = List.of(
                ItemRegister.SHOES.get(),
                ItemRegister.HEAD_BAND.get(),
                ItemRegister.MAID_DRESS.get(),
                ItemRegister.FOX_TAIL.get(),
                ItemRegister.FOX_EARS.get(),
                ItemRegister.BUNNY_TAIL.get(),
                ItemRegister.BUNNY_EARS.get(),
                ItemRegister.CAT_TAIL.get(),
                ItemRegister.CAT_EARS.get()
        );


        ModelPredicateProviderRegistry.register(ItemRegister.THIGH_HIGHS.get(), new Identifier(KawaiiDishes.MODID, "decoration"), ((pStack, pLevel, pEntity, pSeed) -> {
            return (pStack.getOrCreateNbt().getInt("Decoration") / (float) ThighHighsDecorationRegister.DECORATIONS.getRegistry().size());
        }));

        // 0 -> nothing
        // 1 -> dress
        // 2 -> tail
        // 3 -> both
        itemsWithState.forEach(item -> {
            ModelPredicateProviderRegistry.register(item, new Identifier(KawaiiDishes.MODID, "state"), (pStack, pLevel, pEntity, pSeed) -> {
                float compoundValue = 0;

                IFourColorDyeableItem fourColoredItem = (IFourColorDyeableItem) pStack.getItem();

                compoundValue += fourColoredItem.hasPrimaryOverlay(pStack) ? 1 : 0;
                compoundValue += fourColoredItem.hasSecondaryOverlay(pStack) ? 2 : 0;

                return compoundValue / 4f;
            });
        });

        itemsWithOverlay.forEach(item -> {
            ModelPredicateProviderRegistry.register(item, new Identifier(KawaiiDishes.MODID, "has_overlay"), ((pStack, pLevel, pEntity, pSeed) -> ((IDyeableItem) pStack.getItem()).hasOverlay(pStack) ? 1 : 0));
        });
    }
}
