package com.hakimen.kawaiidishes.client.events;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.item.IDyeableItem;
import com.hakimen.kawaiidishes.item.IFourColorDyeableItem;
import com.hakimen.kawaiidishes.item.SeatItem;
import com.hakimen.kawaiidishes.item.armor.HeadBandWithEarsArmorItem;
import com.hakimen.kawaiidishes.item.armor.MaidDressesWithTailArmorItem;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@Mod.EventBusSubscriber(modid = KawaiiDishes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AddItemColorsEvent {

    @SubscribeEvent
    public static void registerColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, layer) -> {
                    switch (layer) {
                        case 0 -> {
                            return ItemRegister.THIGH_HIGHS.get().getBaseColor(stack);
                        }
                        case 1 -> {
                            return ItemRegister.THIGH_HIGHS.get().getOverlayColor(stack);
                        }
                    }
                    return IDyeableItem.defaultColor;
                }, ItemRegister.MAID_DRESS.get(),
                ItemRegister.HEAD_BAND.get(),
                ItemRegister.THIGH_HIGHS.get(),
                ItemRegister.SHOES.get(),

                ItemRegister.FOX_TAIL.get(),
                ItemRegister.FOX_EARS.get(),

                ItemRegister.BUNNY_TAIL.get(),
                ItemRegister.BUNNY_EARS.get(),

                ItemRegister.CAT_TAIL.get(),
                ItemRegister.CAT_EARS.get()
        );


        event.register(((pStack, pTintIndex) -> {
            int compoundValue = 0;

            MaidDressesWithTailArmorItem tailDressItem = (MaidDressesWithTailArmorItem) pStack.getItem();

            compoundValue += tailDressItem.hasPrimaryOverlay(pStack) ? 1 : 0;
            compoundValue += tailDressItem.hasSecondaryOverlay(pStack) ? 2 : 0;


            switch (compoundValue) {
                case 0 -> {
                    switch (pTintIndex) {
                        case 0 -> {
                            return tailDressItem.getSecondaryBaseColor(pStack);
                        }
                        case 1 -> {
                            return tailDressItem.getPrimaryBaseColor(pStack);
                        }
                    }
                }
                case 1 -> {
                    switch (pTintIndex) {
                        case 0 -> {
                            return tailDressItem.getSecondaryBaseColor(pStack);
                        }
                        case 1 -> {
                            return tailDressItem.getPrimaryBaseColor(pStack);
                        }
                        case 2 -> {
                            return tailDressItem.getPrimaryOverlayColor(pStack);
                        }
                    }
                }
                case 2 -> {
                    switch (pTintIndex) {
                        case 0 -> {
                            return tailDressItem.getSecondaryBaseColor(pStack);
                        }
                        case 1 -> {
                            return tailDressItem.getSecondaryOverlayColor(pStack);
                        }
                        case 2 -> {
                            return tailDressItem.getPrimaryBaseColor(pStack);
                        }
                    }
                }
                case 3 -> {
                    switch (pTintIndex) {
                        case 0 -> {
                            return tailDressItem.getSecondaryBaseColor(pStack);
                        }
                        case 1 -> {
                            return tailDressItem.getSecondaryOverlayColor(pStack);
                        }
                        case 2 -> {
                            return tailDressItem.getPrimaryBaseColor(pStack);
                        }
                        case 3 -> {
                            return tailDressItem.getPrimaryOverlayColor(pStack);
                        }
                    }
                }
            }
            return IFourColorDyeableItem.defaultColor;
        }),
                ItemRegister.MAID_DRESS_FOX_TAIL.get(),
                ItemRegister.MAID_DRESS_BUNNY_TAIL.get(),
                ItemRegister.MAID_DRESS_CAT_TAIL.get());


        event.register(((pStack, pTintIndex) -> {
            int compoundValue = 0;

            HeadBandWithEarsArmorItem headBandWithEarsArmorItem = (HeadBandWithEarsArmorItem) pStack.getItem();

            compoundValue += headBandWithEarsArmorItem.hasPrimaryOverlay(pStack) ? 1 : 0;
            compoundValue += headBandWithEarsArmorItem.hasSecondaryOverlay(pStack) ? 2 : 0;


            // 0 sem nada
            // 1 overlay no vestido
            // 2 overlay na cauda
            // 3 overlay em ambos

            switch (compoundValue) {
                case 0 -> {
                    switch (pTintIndex) {
                        case 0 -> {
                            return headBandWithEarsArmorItem.getPrimaryBaseColor(pStack);
                        }
                        case 2 -> {
                            return headBandWithEarsArmorItem.getSecondaryBaseColor(pStack);
                        }

                    }
                }
                case 1 -> {
                    switch (pTintIndex) {
                        case 0 -> {
                            return headBandWithEarsArmorItem.getPrimaryBaseColor(pStack);
                        }
                        case 1 -> {
                            return headBandWithEarsArmorItem.getSecondaryBaseColor(pStack);
                        }
                        case 2 -> {
                            return headBandWithEarsArmorItem.getPrimaryOverlayColor(pStack);
                        }

                    }
                }
                case 2 -> {
                    switch (pTintIndex) {
                        case 0 -> {
                            return headBandWithEarsArmorItem.getPrimaryBaseColor(pStack);
                        }
                        case 1 -> {
                            return headBandWithEarsArmorItem.getSecondaryOverlayColor(pStack);
                        }
                        case 2 -> {
                            return headBandWithEarsArmorItem.getSecondaryBaseColor(pStack);
                        }
                    }
                }
                case 3 -> {
                    switch (pTintIndex) {
                        case 0 -> {
                            return headBandWithEarsArmorItem.getPrimaryBaseColor(pStack);
                        }
                        case 1 -> {
                            return headBandWithEarsArmorItem.getPrimaryOverlayColor(pStack);
                        }
                        case 2 -> {
                            return headBandWithEarsArmorItem.getSecondaryBaseColor(pStack);
                        }
                        case 3 -> {
                            return headBandWithEarsArmorItem.getSecondaryOverlayColor(pStack);
                        }

                    }
                }
            }
            return IFourColorDyeableItem.defaultColor;
        }), ItemRegister.HEAD_BAND_BUNNY_EARS.get());

        event.register(((pStack, pTintIndex) -> {
            int compoundValue = 0;

            HeadBandWithEarsArmorItem headBandWithEarsArmorItem = (HeadBandWithEarsArmorItem) pStack.getItem();

            compoundValue += headBandWithEarsArmorItem.hasPrimaryOverlay(pStack) ? 1 : 0;
            compoundValue += headBandWithEarsArmorItem.hasSecondaryOverlay(pStack) ? 2 : 0;


            switch (compoundValue) {
                case 0 -> {
                    switch (pTintIndex) {

                        case 0 -> {
                            return headBandWithEarsArmorItem.getSecondaryBaseColor(pStack);
                        }

                        case 2-> {
                            return headBandWithEarsArmorItem.getPrimaryBaseColor(pStack);
                        }

                    }
                }
                case 1 -> {
                    switch (pTintIndex) {
                        case 0 -> {
                            return headBandWithEarsArmorItem.getSecondaryBaseColor(pStack);
                        }
                        case 2 -> {
                            return headBandWithEarsArmorItem.getPrimaryBaseColor(pStack);
                        }
                        case 3 -> {
                            return headBandWithEarsArmorItem.getPrimaryOverlayColor(pStack);
                        }
                    }
                }
                case 2 -> {
                    switch (pTintIndex) {
                        case 0 -> {
                            return headBandWithEarsArmorItem.getSecondaryBaseColor(pStack);
                        }
                        case 1 -> {
                            return headBandWithEarsArmorItem.getSecondaryOverlayColor(pStack);
                        }
                        case 3 -> {
                            return headBandWithEarsArmorItem.getPrimaryBaseColor(pStack);
                        }
                    }
                }
                case 3 -> {
                    switch (pTintIndex) {
                        case 0 -> {
                            return headBandWithEarsArmorItem.getSecondaryBaseColor(pStack);
                        }
                        case 1 -> {
                            return headBandWithEarsArmorItem.getSecondaryOverlayColor(pStack);
                        }
                        case 3 -> {
                            return headBandWithEarsArmorItem.getPrimaryBaseColor(pStack);
                        }
                        case 4 -> {
                            return headBandWithEarsArmorItem.getPrimaryOverlayColor(pStack);
                        }
                    }
                }
            }
            return IFourColorDyeableItem.defaultColor;
        }), ItemRegister.HEAD_BAND_FOX_EARS.get(),
                ItemRegister.HEAD_BAND_CAT_EARS.get());

        event.register(((stack, layer) -> {
            switch (layer) {
                case 0 -> {
                    return ((SeatItem) stack.getItem()).getBaseColor(stack);
                }
            }

            return IDyeableItem.defaultColor;
        }),ItemRegister.SEAT.get());
    }
}
