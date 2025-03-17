package com.hakimen.kawaiidishes;

import com.hakimen.kawaiidishes.client.entity.SeatRenderer;
import com.hakimen.kawaiidishes.item.IFourColorDyeableItem;
import com.hakimen.kawaiidishes.item.armor.TailArmorItem;
import com.hakimen.kawaiidishes.item.component.KawaiiDyeableComponent;
import com.hakimen.kawaiidishes.registry.DataComponentRegister;
import com.hakimen.kawaiidishes.registry.EntityRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import com.unascribed.ears.api.EarsFeatureType;
import com.unascribed.ears.api.EarsStateType;
import com.unascribed.ears.api.OverrideResult;
import com.unascribed.ears.api.registry.EarsInhibitorRegistry;
import com.unascribed.ears.api.registry.EarsStateOverriderRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

@SuppressWarnings("DataFlowIssue")
public class KawaiiDishesClient implements ClientModInitializer {
//    @SubscribeEvent
//    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
//        event.registerEntityRenderer(EntityRegister.SEAT.get(), SeatRenderer::new);
//
//        event.registerBlockEntityRenderer(BlockEntityRegister.DISPLAY_CASE.get(), DisplayCaseBlockEntityRenderer::new);
//        event.registerBlockEntityRenderer(BlockEntityRegister.INCENSE.get(), IncenseGlassBlockEntityRenderer::new);
//
//        Minecraft.getInstance().particleEngine.register(ParticleRegister.INCENSE_PARTICLES.get(),
//                IncenseParticle.Provider::new);
//    } // TODO: renderers

    public void onInitializeClient() {
//        event.register(ContainerRegister.COFFEE_MACHINE.get(), CoffeeMachineScreen::new);
//        event.register(ContainerRegister.BLENDER.get(), BlenderScreen::new);
//        event.register(ContainerRegister.DISPLAY_CASE.get(), DisplayCaseScreen::new);
//        event.register(ContainerRegister.ICE_CREAM_MAKER.get(), IceCreamMakerScreen::new);
        // TODO: screens

        if (FabricLoader.getInstance().isModLoaded("ears")) {
            EarsInhibitorRegistry.register(KawaiiDishes.MODID, (type, peer) -> {
                Player player = (Player) peer;
                for (ItemStack slot : player.getArmorSlots()) {
                    if (slot.getItem() instanceof TailArmorItem && type == EarsFeatureType.TAIL) {
                        return true;
                    }
                }

                return false;
            });

            EarsStateOverriderRegistry.register(KawaiiDishes.MODID, (type, peer) -> {
                Player player = (Player) peer;
                for (ItemStack slot : player.getArmorSlots()) {
                    if (slot.getItem() instanceof TailArmorItem && type == EarsStateType.WEARING_CHESTPLATE) {
                        return OverrideResult.FALSE;
                    }
                }

                return OverrideResult.DEFAULT;
            });
        }

        EntityRendererRegistry.register(EntityRegister.SEAT.get(), SeatRenderer::new);

        ColorProviderRegistry.ITEM.register((stack, layer) -> {
            KawaiiDyeableComponent.KawaiiDyeable dyeable = stack.get(DataComponentRegister.DYEABLE.get());
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
        
        ColorProviderRegistry.ITEM.register((stack, layer) -> {
            int compoundValue = 0;

            KawaiiDyeableComponent.KawaiiDyeable dyeable = stack.get(DataComponentRegister.DYEABLE.get());
            compoundValue += dyeable.isHasOverlay() ? 1 : 0;
            compoundValue += dyeable.isHasSecondaryOverlay() ? 2 : 0;


            switch (compoundValue) {
                case 0 -> {
                    switch (layer) {
                        case 0 -> {
                            return dyeable.getSecondaryBase();
                        }
                        case 1 -> {
                            return dyeable.getBase();
                        }
                    }
                }
                case 1 -> {
                    switch (layer) {
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
                    switch (layer) {
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
                    switch (layer) {
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
        },      ItemRegister.MAID_DRESS_FOX_TAIL.get(),
                ItemRegister.MAID_DRESS_BUNNY_TAIL.get(),
                ItemRegister.MAID_DRESS_CAT_TAIL.get()
        );

//        ColorProviderRegistry.ITEM.register((stack, layer) -> {
//            KawaiiDyeableComponent.KawaiiDyeable dyeable = stack.get(DataComponentRegister.DYEABLE.get());
//            switch (layer) {
//                case 0 -> {
//                    return dyeable.getBase();
//                }
//            }
//
//            return IFourColorDyeableItem.defaultColor;
//        }), ItemRegister.SEAT.get()); // TODO: seats

        ColorProviderRegistry.ITEM.register((stack, layer) -> {
            int compoundValue = 0;

            KawaiiDyeableComponent.KawaiiDyeable dyeable = stack.get(DataComponentRegister.DYEABLE.get());
            compoundValue += dyeable.isHasOverlay() ? 1 : 0;
            compoundValue += dyeable.isHasSecondaryOverlay() ? 2 : 0;


            switch (compoundValue) {
                case 0 -> {
                    switch (layer) {

                        case 0 -> {
                            return dyeable.getSecondaryBase();
                        }

                        case 2 -> {
                            return dyeable.getBase();
                        }

                    }
                }
                case 1 -> {
                    switch (layer) {
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
                    switch (layer) {
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
                    switch (layer) {
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
        }, ItemRegister.HEAD_BAND_FOX_EARS.get(),
                ItemRegister.HEAD_BAND_CAT_EARS.get());

        ColorProviderRegistry.ITEM.register((stack, layer) -> {
            int compoundValue = 0;

            KawaiiDyeableComponent.KawaiiDyeable dyeable = stack.get(DataComponentRegister.DYEABLE.get());
            compoundValue += dyeable.isHasOverlay() ? 1 : 0;
            compoundValue += dyeable.isHasSecondaryOverlay() ? 2 : 0;


            // 0 sem nada
            // 1 overlay no vestido
            // 2 overlay na cauda
            // 3 overlay em ambos


            switch (compoundValue) {
                case 0 -> {
                    switch (layer) {
                        case 0 -> {
                            return dyeable.getBase();
                        }
                        case 1 -> {
                            return dyeable.getSecondaryBase();
                        }
                    }
                }
                case 1 -> {
                    switch (layer) {
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
                    switch (layer) {
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
                    switch (layer) {
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
        }, ItemRegister.HEAD_BAND_BUNNY_EARS.get());
    }

}