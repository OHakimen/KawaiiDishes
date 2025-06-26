package com.hakimen.kawaiidishes.client;

import F;
import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.aromas.DecorativeAroma;
import com.hakimen.kawaiidishes.aromas.PotionAroma;
import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.block_entities.SeatBlockEntity;
import com.hakimen.kawaiidishes.client.blockEntityRenderers.DisplayCaseBlockEntityRenderer;
import com.hakimen.kawaiidishes.client.blockEntityRenderers.IncenseGlassBlockEntityRenderer;
import com.hakimen.kawaiidishes.client.entity.mobRenderer.SeatRenderer;
import com.hakimen.kawaiidishes.client.screens.BlenderScreen;
import com.hakimen.kawaiidishes.client.screens.CoffeeMachineScreen;
import com.hakimen.kawaiidishes.client.screens.DisplayCaseScreen;
import com.hakimen.kawaiidishes.client.screens.IceCreamMakerScreen;
import com.hakimen.kawaiidishes.custom.types.Aroma;
import com.hakimen.kawaiidishes.item.IDyeableItem;
import com.hakimen.kawaiidishes.item.IFourColorDyeableItem;
import com.hakimen.kawaiidishes.item.SeatItem;
import com.hakimen.kawaiidishes.item.armor.HeadBandWithEarsArmorItem;
import com.hakimen.kawaiidishes.item.armor.MaidDressesWithTailArmorItem;
import com.hakimen.kawaiidishes.particle.IncenseParticle;
import com.hakimen.kawaiidishes.registry.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import software.bernie.geckolib.GeckoLib;

import java.util.List;

public class KawaiiDishesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        GeckoLib.initialize();

        registerItemColors();
        registerBlockColors();

        registerItemPropertiesOverrides();

        EntityRendererRegistry.register(EntityRegister.SEAT.get(), SeatRenderer::new);

        ScreenRegistry.register(ContainerRegister.COFFEE_MACHINE.get(), CoffeeMachineScreen::new);
        ScreenRegistry.register(ContainerRegister.DISPLAY_CASE.get(), DisplayCaseScreen::new);
        ScreenRegistry.register(ContainerRegister.ICE_CREAM_MAKER.get(), IceCreamMakerScreen::new);
        ScreenRegistry.register(ContainerRegister.BLENDER.get(), BlenderScreen::new);

        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.COFFEE_BUSH.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.INCENSE_GLASS.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.ICE_CREAM_MAKER.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.DISPLAY_CASE.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.BLENDER.get(), RenderType.cutout());

        BlockEntityRenderers.register(BlockEntityRegister.INCENSE.get(), IncenseGlassBlockEntityRenderer::new);
        BlockEntityRenderers.register(BlockEntityRegister.DISPLAY_CASE.get(), DisplayCaseBlockEntityRenderer::new);

        ParticleFactoryRegistry.getInstance().register(ParticleRegister.INCENSE_PARTICLE.get(), IncenseParticle.Provider::new);

        PacketRegister.registerServer2ClientPackets();
    }

    public void registerBlockColors() {
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

    public void registerItemColors() {

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

    public void registerItemPropertiesOverrides() {

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


        ItemProperties.register(ItemRegister.THIGH_HIGHS.get(), new ResourceLocation(KawaiiDishes.MODID, "decoration"), ((pStack, pLevel, pEntity, pSeed) -> {
            return (pStack.getOrCreateTag().getInt("Decoration") / (float) ThighHighsDecorationRegister.DECORATIONS.getRegistry().size());
        }));

        itemsWithState.forEach(item -> {
            ItemProperties.register(item, new ResourceLocation(KawaiiDishes.MODID, "state"), (pStack, pLevel, pEntity, pSeed) -> {
                float compoundValue = 0;

                IFourColorDyeableItem fourColoredItem = (IFourColorDyeableItem) pStack.getItem();

                compoundValue += fourColoredItem.hasPrimaryOverlay(pStack) ? 1 : 0;
                compoundValue += fourColoredItem.hasSecondaryOverlay(pStack) ? 2 : 0;

                // 0 sem nada
                // 1 overlay no vestido
                // 2 overlay na cauda
                // 3 overlay em ambos

                return compoundValue / 4f;
            });
        });

        itemsWithOverlay.forEach(item -> {
            ItemProperties.register(item, new ResourceLocation(KawaiiDishes.MODID, "has_overlay"), ((pStack, pLevel, pEntity, pSeed) -> ((IDyeableItem) pStack.getItem()).hasOverlay(pStack) ? 1 : 0));
        });
    }
}
