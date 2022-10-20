package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.containers.CoffeeMachineContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ContainerRegister {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, KawaiiDishes.modId);

    public static final RegistryObject<MenuType<CoffeeMachineContainer>> coffeeMachine = CONTAINERS.register("coffee_machine", ()-> IForgeMenuType.create(CoffeeMachineContainer::new));
    public static void register(IEventBus bus){
        CONTAINERS.register(bus);
    }
}
