package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.containers.BlenderContainer;
import com.hakimen.kawaiidishes.containers.CoffeeMachineContainer;
import com.hakimen.kawaiidishes.containers.DisplayCaseContainer;
import com.hakimen.kawaiidishes.containers.IceCreamMakerContainer;
import com.hakimen.kawaiidishes.custom.Recorder;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import java.util.function.Supplier;

public class ContainerRegister {
    public static final Recorder<MenuType<?>> CONTAINERS = new Recorder<>(BuiltInRegistries.MENU, KawaiiDishes.MODID);

    public static final Supplier<MenuType<CoffeeMachineContainer>> COFFEE_MACHINE = CONTAINERS.register("coffee_machine", () ->
            new ExtendedScreenHandlerType<>(CoffeeMachineContainer::new)
    );
    public static final Supplier<MenuType<DisplayCaseContainer>> DISPLAY_CASE = CONTAINERS.register("display_case", () ->
            new ExtendedScreenHandlerType<>(DisplayCaseContainer::new)
    );

    public static final Supplier<MenuType<IceCreamMakerContainer>> ICE_CREAM_MAKER = CONTAINERS.register("ice_cream_maker", () ->
            new ExtendedScreenHandlerType<>(IceCreamMakerContainer::new)
    );

    public static final Supplier<MenuType<BlenderContainer>> BLENDER = CONTAINERS.register("blender", () ->
            new ExtendedScreenHandlerType<>(BlenderContainer::new)
    );

    public static void register(){

    }
}
