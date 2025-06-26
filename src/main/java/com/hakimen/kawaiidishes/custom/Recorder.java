package com.hakimen.kawaiidishes.custom;

import java.util.function.Supplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

public class Recorder<T>{
    private final String modId;
    private final Registry<T> registry;
    public Recorder(Registry<T> registry, String modId) {
        this.modId = modId;
        this.registry = registry;
    }

    public <X extends T> Supplier<X> register(String name, Supplier<X> object){
        X temp = Registry.register(registry,new ResourceLocation(modId, name), object.get());
        return () -> temp;
    }

    public Registry<T> getRegistry(){
        return registry;
    }
}
