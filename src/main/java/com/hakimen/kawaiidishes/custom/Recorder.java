package com.hakimen.kawaiidishes.custom;

import java.util.function.Supplier;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class Recorder<T>{
    private final String modId;
    private final Registry<T> registry;
    public Recorder(Registry<T> registry, String modId) {
        this.modId = modId;
        this.registry = registry;
    }

    public <X extends T> Supplier<X> register(String name, Supplier<X> object){
        X temp = Registry.register(registry,new Identifier(modId, name), object.get());
        return () -> temp;
    }

    public Registry<T> getRegistry(){
        return registry;
    }
}
