package com.hakimen.kawaiidishes.custom;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.custom.type.Aroma;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.RegistryBuilder;

public class Registries {
    public static final ResourceKey<Registry<Aroma>> AROMA_REGISTRY_KEY = ResourceKey.createRegistryKey(new ResourceLocation(KawaiiDishes.MODID, "aromas"));
    public static final Registry<Aroma> AROMA_REGISTRY = new RegistryBuilder<>(AROMA_REGISTRY_KEY)
            .sync(true)
            .create();


}
