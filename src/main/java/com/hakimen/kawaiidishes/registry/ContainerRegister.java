package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.containers.BlenderContainer;
import com.hakimen.kawaiidishes.containers.CoffeeMachineContainer;
import com.hakimen.kawaiidishes.containers.DisplayCaseContainer;
import com.hakimen.kawaiidishes.containers.IceCreamMakerContainer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ContainerRegister {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(Registries.MENU, KawaiiDishes.MODID);

    public static final DeferredHolder<MenuType<?>,MenuType<CoffeeMachineContainer>> COFFEE_MACHINE = CONTAINERS.register("coffee_machine", () -> IMenuTypeExtension.create(CoffeeMachineContainer::new));
    public static final DeferredHolder<MenuType<?>,MenuType<BlenderContainer>> BLENDER = CONTAINERS.register("blender", () -> IMenuTypeExtension.create(BlenderContainer::new));
    public static final DeferredHolder<MenuType<?>,MenuType<DisplayCaseContainer>> DISPLAY_CASE = CONTAINERS.register("display_case", () -> IMenuTypeExtension.create(DisplayCaseContainer::new));
    public static final DeferredHolder<MenuType<?>,MenuType<IceCreamMakerContainer>> ICE_CREAM_MAKER = CONTAINERS.register("ice_cream_maker", () -> IMenuTypeExtension.create(IceCreamMakerContainer::new));

    public static void register(IEventBus bus){
        CONTAINERS.register(bus);
    }

}
