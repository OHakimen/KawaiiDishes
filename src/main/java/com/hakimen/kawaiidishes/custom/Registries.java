package com.hakimen.kawaiidishes.custom;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.custom.types.Aroma;
import com.hakimen.kawaiidishes.custom.types.ThighHighDecoration;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class Registries {
    public static RegistryKey<Registry<ThighHighDecoration>> THIGH_HIGH_DECORATION_KEY = RegistryKey.ofRegistry(new Identifier(KawaiiDishes.MODID,"thigh_high_decorations"));
    public static Registry<ThighHighDecoration> THIGH_HIGH_DECORATIONS = FabricRegistryBuilder.createSimple(THIGH_HIGH_DECORATION_KEY)
            .attribute(RegistryAttribute.SYNCED)
            .buildAndRegister();

    public static RegistryKey<Registry<Aroma>> AROMA_KEY = RegistryKey.ofRegistry(new Identifier(KawaiiDishes.MODID,"aromas"));
    public static Registry<Aroma> AROMAS = FabricRegistryBuilder.createSimple(AROMA_KEY)
            .attribute(RegistryAttribute.SYNCED)
            .buildAndRegister();
}
