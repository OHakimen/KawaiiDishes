package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.containers.BlenderContainer;
import com.hakimen.kawaiidishes.containers.CoffeeMachineContainer;
import com.hakimen.kawaiidishes.containers.DisplayCaseContainer;
import com.hakimen.kawaiidishes.containers.IceCreamMakerContainer;
import com.hakimen.kawaiidishes.custom.Recorder;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.screen.ScreenHandlerType;
import java.util.function.Supplier;

public class ContainerRegister {
    public static final Recorder<ScreenHandlerType<?>> CONTAINERS = new Recorder<>(Registries.SCREEN_HANDLER, KawaiiDishes.MODID);

    public static final Supplier<ScreenHandlerType<CoffeeMachineContainer>> COFFEE_MACHINE = CONTAINERS.register("coffee_machine", () ->
            new ExtendedScreenHandlerType<>(CoffeeMachineContainer::new)
    );
    public static final Supplier<ScreenHandlerType<DisplayCaseContainer>> DISPLAY_CASE = CONTAINERS.register("display_case", () ->
            new ExtendedScreenHandlerType<>(DisplayCaseContainer::new)
    );

    public static final Supplier<ScreenHandlerType<IceCreamMakerContainer>> ICE_CREAM_MAKER = CONTAINERS.register("ice_cream_maker", () ->
            new ExtendedScreenHandlerType<>(IceCreamMakerContainer::new)
    );

    public static final Supplier<ScreenHandlerType<BlenderContainer>> BLENDER = CONTAINERS.register("blender", () ->
            new ExtendedScreenHandlerType<>(BlenderContainer::new)
    );

    public static void register(){

    }
}
