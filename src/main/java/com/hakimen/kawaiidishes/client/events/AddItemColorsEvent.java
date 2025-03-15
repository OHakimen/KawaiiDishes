package com.hakimen.kawaiidishes.client.events;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.item.IFourColorDyeableItem;
import com.hakimen.kawaiidishes.item.SeatItem;
import com.hakimen.kawaiidishes.item.component.KawaiiDyeableComponent;
import com.hakimen.kawaiidishes.registry.DataComponentRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.core.component.DataComponents;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = KawaiiDishes.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AddItemColorsEvent {

    @SubscribeEvent
    public static void registerColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, layer) -> {
                    KawaiiDyeableComponent.KawaiiDyeable dyeable = stack.get(DataComponentRegister.DYEABLE);
                    switch (layer) {
                        case 0 -> {
                            return dyeable.getBase();
                        }
                        case 1 -> {
                            return dyeable.getOverlay();
                        }
                    }
                    return IFourColorDyeableItem.defaultColor;
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

                    KawaiiDyeableComponent.KawaiiDyeable dyeable = pStack.get(DataComponentRegister.DYEABLE);
                    compoundValue += dyeable.isHasOverlay() ? 1 : 0;
                    compoundValue += dyeable.isHasSecondaryOverlay() ? 2 : 0;


                    switch (compoundValue) {
                        case 0 -> {
                            switch (pTintIndex) {
                                case 0 -> {
                                    return dyeable.getSecondaryBase();
                                }
                                case 1 -> {
                                    return dyeable.getBase();
                                }
                            }
                        }
                        case 1 -> {
                            switch (pTintIndex) {
                                case 0 -> {
                                    return dyeable.getSecondaryBase();
                                }
                                case 1 -> {
                                    return dyeable.getBase();
                                }
                                case 2 -> {
                                    return dyeable.getOverlay();
                                }
                            }
                        }
                        case 2 -> {
                            switch (pTintIndex) {
                                case 0 -> {
                                    return dyeable.getSecondaryBase();
                                }
                                case 1 -> {
                                    return dyeable.getSecondaryOverlay();
                                }
                                case 2 -> {
                                    return dyeable.getBase();
                                }
                            }
                        }
                        case 3 -> {
                            switch (pTintIndex) {
                                case 0 -> {
                                    return dyeable.getSecondaryBase();
                                }
                                case 1 -> {
                                    return dyeable.getSecondaryOverlay();
                                }
                                case 2 -> {
                                    return dyeable.getBase();
                                }
                                case 3 -> {
                                    return dyeable.getOverlay();
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

            KawaiiDyeableComponent.KawaiiDyeable dyeable = pStack.get(DataComponentRegister.DYEABLE);
            compoundValue += dyeable.isHasOverlay() ? 1 : 0;
            compoundValue += dyeable.isHasSecondaryOverlay() ? 2 : 0;


            // 0 sem nada
            // 1 overlay no vestido
            // 2 overlay na cauda
            // 3 overlay em ambos


            switch (compoundValue) {
                case 0 -> {
                    switch (pTintIndex) {
                        case 0 -> {
                            return dyeable.getBase();
                        }
                        case 1 -> {
                            return dyeable.getSecondaryBase();
                        }
                    }
                }
                case 1 -> {
                    switch (pTintIndex) {
                        case 0 -> {
                            return dyeable.getBase();
                        }
                        case 1 -> {
                            return dyeable.getSecondaryBase();
                        }
                        case 2 -> {
                            return dyeable.getOverlay();
                        }

                    }
                }
                case 2 -> {
                    switch (pTintIndex) {
                        case 0 -> {
                            return dyeable.getBase();
                        }
                        case 1 -> {
                            return dyeable.getSecondaryOverlay();
                        }
                        case 2 -> {
                            return dyeable.getSecondaryBase();
                        }
                    }
                }
                case 3 -> {
                    switch (pTintIndex) {
                        case 0 -> {
                            return dyeable.getBase();
                        }
                        case 1 -> {
                            return dyeable.getOverlay();
                        }
                        case 2 -> {
                            return dyeable.getSecondaryBase();
                        }
                        case 3 -> {
                            return dyeable.getSecondaryOverlay();
                        }

                    }
                }
            }
            return IFourColorDyeableItem.defaultColor;
        }), ItemRegister.HEAD_BAND_BUNNY_EARS.get());

        event.register(((pStack, pTintIndex) -> {
                    int compoundValue = 0;

                    KawaiiDyeableComponent.KawaiiDyeable dyeable = pStack.get(DataComponentRegister.DYEABLE);
                    compoundValue += dyeable.isHasOverlay() ? 1 : 0;
                    compoundValue += dyeable.isHasSecondaryOverlay() ? 2 : 0;


                    switch (compoundValue) {
                        case 0 -> {
                            switch (pTintIndex) {

                                case 0 -> {
                                    return dyeable.getSecondaryBase();
                                }

                                case 2 -> {
                                    return dyeable.getBase();
                                }

                            }
                        }
                        case 1 -> {
                            switch (pTintIndex) {
                                case 0 -> {
                                    return dyeable.getSecondaryBase();
                                }
                                case 2 -> {
                                    return dyeable.getBase();
                                }
                                case 3 -> {
                                    return dyeable.getOverlay();
                                }
                            }
                        }
                        case 2 -> {
                            switch (pTintIndex) {
                                case 0 -> {
                                    return dyeable.getSecondaryBase();
                                }
                                case 1 -> {
                                    return dyeable.getSecondaryOverlay();
                                }
                                case 3 -> {
                                    return dyeable.getBase();
                                }
                            }
                        }
                        case 3 -> {
                            switch (pTintIndex) {
                                case 0 -> {
                                    return dyeable.getSecondaryBase();
                                }
                                case 1 -> {
                                    return dyeable.getSecondaryOverlay();
                                }
                                case 3 -> {
                                    return dyeable.getBase();
                                }
                                case 4 -> {
                                    return dyeable.getOverlay();
                                }
                            }
                        }
                    }
                    return IFourColorDyeableItem.defaultColor;
                }), ItemRegister.HEAD_BAND_FOX_EARS.get(),
                ItemRegister.HEAD_BAND_CAT_EARS.get());

        event.register(((stack, layer) -> {
            KawaiiDyeableComponent.KawaiiDyeable dyeable = stack.get(DataComponentRegister.DYEABLE);
            switch (layer) {
                case 0 -> {
                    return dyeable.getBase();
                }
            }

            return IFourColorDyeableItem.defaultColor;
        }), ItemRegister.SEAT.get());
    }
}
