package com.hakimen.kawaiidishes.client.init;

import com.hakimen.kawaiidishes.client.screens.BlenderScreen;
import com.hakimen.kawaiidishes.client.screens.CoffeeMachineScreen;
import com.hakimen.kawaiidishes.client.screens.DisplayCaseScreen;
import com.hakimen.kawaiidishes.client.screens.IceCreamMakerScreen;
import com.hakimen.kawaiidishes.registry.ContainerRegister;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class ScreenInitializer {
    public static void init() {
        ScreenRegistry.register(ContainerRegister.COFFEE_MACHINE.get(), CoffeeMachineScreen::new);
        ScreenRegistry.register(ContainerRegister.DISPLAY_CASE.get(), DisplayCaseScreen::new);
        ScreenRegistry.register(ContainerRegister.ICE_CREAM_MAKER.get(), IceCreamMakerScreen::new);
        ScreenRegistry.register(ContainerRegister.BLENDER.get(), BlenderScreen::new);
    }
}
