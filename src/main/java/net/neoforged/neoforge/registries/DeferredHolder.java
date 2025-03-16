package net.neoforged.neoforge.registries;

public class DeferredHolder<K, V> {
    protected final K type;
    public final V value;

    protected DeferredHolder(K type, V value) {
        this.type = type;
        this.value = value;
    }

    public V get() {
        return this.value;
    }

    public V value() {
        return this.value;
    }
}
