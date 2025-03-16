package net.neoforged.neoforge.registries;

import java.util.function.Supplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

public class DeferredRegister<T> {
    private final Registry<T> registry;
    private final String mod;

    private DeferredRegister(Registry<T> type, String mod) {
        this.registry = type;
        this.mod = mod;
    }

    public static <T> DeferredRegister<T> create(Registry<T> type, String mod) {
        return new DeferredRegister<>(type, mod);
    }

    public <I extends T> DeferredHolder<T, I> register(String name, Supplier<? extends T> supplier) {
        T item = supplier.get();
        Registry.register(this.registry, ResourceLocation.fromNamespaceAndPath(mod, name), item);
        return new DeferredHolder<>(item, (I) item);
    }
}
