package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.item.component.KawaiiDyeableComponent;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.UnaryOperator;

public class DataComponentRegister {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS = DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE, KawaiiDishes.MODID);

    public static final DeferredHolder<DataComponentType<?>,DataComponentType<KawaiiDyeableComponent.KawaiiDyeable>> DYEABLE = DATA_COMPONENTS.register("kawaii_dyeable", () ->
            new DataComponentType.Builder<KawaiiDyeableComponent.KawaiiDyeable>().persistent(KawaiiDyeableComponent.CODEC).networkSynchronized(KawaiiDyeableComponent.STREAM_CODEC).build());

    public static void register(IEventBus bus){
        DATA_COMPONENTS.register(bus);
    }
}
