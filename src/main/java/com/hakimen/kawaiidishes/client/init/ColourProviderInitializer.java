package com.hakimen.kawaiidishes.client.init;

import com.hakimen.kawaiidishes.aromas.DecorativeAroma;
import com.hakimen.kawaiidishes.aromas.PotionAroma;
import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.block_entities.SeatBlockEntity;
import com.hakimen.kawaiidishes.custom.types.Aroma;
import com.hakimen.kawaiidishes.item.IDyeableItem;
import com.hakimen.kawaiidishes.item.IFourColorDyeableItem;
import com.hakimen.kawaiidishes.item.SeatItem;
import com.hakimen.kawaiidishes.item.armor.HeadBandWithEarsArmorItem;
import com.hakimen.kawaiidishes.item.armor.MaidDressesWithTailArmorItem;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtil;

public class ColourProviderInitializer {
    public static void init() {
        registerItemColors();
        registerBlockColors();
    }

    private static void registerItemColors() {
        ColorProviderRegistry.ITEM.register((stack, layer) -> {
                    switch (layer) {
                        case 0 -> {
                            return ItemRegister.CAT_EARS.get().getBaseColor(stack);
                        }
                        case 1 -> {
                            return ItemRegister.CAT_EARS.get().getOverlayColor(stack);
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

        ColorProviderRegistry.ITEM.register(((pStack, pTintIndex) -> {
                    int compoundValue = 0;

                    MaidDressesWithTailArmorItem tailDressItem = (MaidDressesWithTailArmorItem) pStack.getItem();

                    compoundValue += tailDressItem.hasPrimaryOverlay(pStack) ? 1 : 0;
                    compoundValue += tailDressItem.hasSecondaryOverlay(pStack) ? 2 : 0;


                    var value = compoundValue / 4f;
                    if (value == 0) {
                        switch (pTintIndex) {
                            case 0 -> {
                                return tailDressItem.getSecondaryBaseColor(pStack);
                            }

                            case 1 -> {
                                return tailDressItem.getPrimaryBaseColor(pStack);
                            }

                        }
                    } else if (value == 0.25) {
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
                    } else if (value == 0.50) {
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
                    } else if (value == 0.75) {
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
                    return IFourColorDyeableItem.defaultColor;
                }),
                ItemRegister.MAID_DRESS_FOX_TAIL.get(),
                ItemRegister.MAID_DRESS_BUNNY_TAIL.get(),
                ItemRegister.MAID_DRESS_CAT_TAIL.get());


        ColorProviderRegistry.ITEM.register(((pStack, pTintIndex) -> {
            int compoundValue = 0;

            HeadBandWithEarsArmorItem headBandWithEarsArmorItem = (HeadBandWithEarsArmorItem) pStack.getItem();

            compoundValue += headBandWithEarsArmorItem.hasPrimaryOverlay(pStack) ? 1 : 0;
            compoundValue += headBandWithEarsArmorItem.hasSecondaryOverlay(pStack) ? 2 : 0;


            // 0 sem nada
            // 1 overlay no vestido
            // 2 overlay na cauda
            // 3 overlay em ambos

            var value = compoundValue / 4f;
            if (value == 0) {
                switch (pTintIndex) {
                    case 0 -> {
                        return headBandWithEarsArmorItem.getPrimaryBaseColor(pStack);
                    }
                    case 1 -> {
                        return headBandWithEarsArmorItem.getSecondaryBaseColor(pStack);
                    }
                }
            } else if (value == 0.25) {
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

                }
            } else if (value == 0.50) {
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
            } else if (value == 0.75) {
                switch (pTintIndex) {
                    case 0 -> {
                        return headBandWithEarsArmorItem.getSecondaryBaseColor(pStack);
                    }
                    case 1 -> {
                        return headBandWithEarsArmorItem.getSecondaryOverlayColor(pStack);
                    }
                    case 2 -> {
                        return headBandWithEarsArmorItem.getPrimaryBaseColor(pStack);
                    }
                    case 3 -> {
                        return headBandWithEarsArmorItem.getPrimaryOverlayColor(pStack);
                    }
                }
            }
            return IFourColorDyeableItem.defaultColor;
        }), ItemRegister.HEAD_BAND_BUNNY_EARS.get());

        ColorProviderRegistry.ITEM.register(((pStack, pTintIndex) -> {
                    int compoundValue = 0;

                    HeadBandWithEarsArmorItem headBandWithEarsArmorItem = (HeadBandWithEarsArmorItem) pStack.getItem();

                    compoundValue += headBandWithEarsArmorItem.hasPrimaryOverlay(pStack) ? 1 : 0;
                    compoundValue += headBandWithEarsArmorItem.hasSecondaryOverlay(pStack) ? 2 : 0;

                    var value = compoundValue / 4f;
                    if (value == 0) {
                        switch (pTintIndex) {
                            case 0 -> {
                                return headBandWithEarsArmorItem.getSecondaryBaseColor(pStack);
                            }

                            case 2 -> {
                                return headBandWithEarsArmorItem.getPrimaryBaseColor(pStack);
                            }

                        }
                    } else if (value == 0.25) {
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
                    } else if (value == 0.50) {
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
                    } else if (value == 0.75) {
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
                    return IFourColorDyeableItem.defaultColor;
                }), ItemRegister.HEAD_BAND_FOX_EARS.get(),
                ItemRegister.HEAD_BAND_CAT_EARS.get());

        ColorProviderRegistry.ITEM.register(((stack, layer) -> {
            switch (layer) {
                case 0 -> {
                    return ((SeatItem) stack.getItem()).getBaseColor(stack);
                }
            }

            return IDyeableItem.defaultColor;
        }), ItemRegister.SEAT.get());
    }
    private static void registerBlockColors() {
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
            return tintIndex == 0 ? (view.getBlockEntity(pos) != null ? ((SeatBlockEntity) view.getBlockEntity(pos)).getColor() : -1) : -1;
        }, BlockRegister.SEAT.get());

        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
            if (view.getBlockEntity(pos) != null && tintIndex == 0) {
                int color = -1;

                IncenseBlockEntity incenseBlockEntity = (IncenseBlockEntity) view.getBlockEntity(pos);

                ItemStack stack = incenseBlockEntity.getInventory().getResource().toStack((int) incenseBlockEntity.getInventory().amount);

                Aroma aroma = incenseBlockEntity.getAromaFromId();

                if (aroma instanceof DecorativeAroma) {
                    color = stack.getItem() instanceof DyeItem dyeItem ? dyeItem.getColor().getFireworkColor() : 0;
                } else if (aroma instanceof PotionAroma) {
                    color = PotionUtil.getColor(stack);
                } else {
                    color = aroma.getColor();
                }
                return color;
            }
            return -1;
        }, BlockRegister.INCENSE_GLASS.get());

    }
}
