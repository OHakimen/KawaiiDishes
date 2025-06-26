package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.world.Features;
import com.hakimen.kawaiidishes.world.PlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class DataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(ModelDataGen::new);
        pack.addProvider(LangDataGen::new);
        pack.addProvider(WorldDataGen::new);
        pack.addProvider(ItemTagDataGen::new);
        pack.addProvider(BlockTagDataGen::new);
        

    }


    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, Features::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, PlacedFeatures::bootstrap);
    }
}
