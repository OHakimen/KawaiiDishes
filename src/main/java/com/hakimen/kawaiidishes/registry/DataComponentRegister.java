package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.item.component.KawaiiDyeableComponent;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DataComponentRegister {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS = DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE, KawaiiDishes.MODID);

    public static final DeferredHolder<DataComponentType<?>,DataComponentType<KawaiiDyeableComponent.KawaiiDyeable>> DYEABLE = DATA_COMPONENTS.register("kawaii_dyeable", () ->
            new DataComponentType.Builder<KawaiiDyeableComponent.KawaiiDyeable>().persistent(KawaiiDyeableComponent.CODEC).networkSynchronized(KawaiiDyeableComponent.STREAM_CODEC).build());

    public static void register() {

    }
}
