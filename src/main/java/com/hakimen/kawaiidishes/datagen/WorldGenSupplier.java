package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.world.ConfiguratedFeatures;
import com.hakimen.kawaiidishes.world.PlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class WorldGenSupplier extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder lookupProvider = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ConfiguratedFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, PlacedFeatures::bootstrap);



    public WorldGenSupplier(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries,lookupProvider, Set.of(KawaiiDishes.modId));
    }
}
