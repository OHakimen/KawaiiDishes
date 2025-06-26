package com.hakimen.kawaiidishes.custom;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.custom.types.Aroma;
import com.hakimen.kawaiidishes.custom.types.ThighHighDecoration;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class Registries {
    public static ResourceKey<Registry<ThighHighDecoration>> THIGH_HIGH_DECORATION_KEY = ResourceKey.createRegistryKey(new ResourceLocation(KawaiiDishes.MODID,"thigh_high_decorations"));
    public static Registry<ThighHighDecoration> THIGH_HIGH_DECORATIONS = FabricRegistryBuilder.createSimple(THIGH_HIGH_DECORATION_KEY)
            .attribute(RegistryAttribute.SYNCED)
            .buildAndRegister();

    public static ResourceKey<Registry<Aroma>> AROMA_KEY = ResourceKey.createRegistryKey(new ResourceLocation(KawaiiDishes.MODID,"aromas"));
    public static Registry<Aroma> AROMAS = FabricRegistryBuilder.createSimple(AROMA_KEY)
            .attribute(RegistryAttribute.SYNCED)
            .buildAndRegister();
}
