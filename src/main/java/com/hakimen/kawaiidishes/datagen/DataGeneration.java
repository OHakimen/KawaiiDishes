package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.world.Features;
import com.hakimen.kawaiidishes.world.PlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

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
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, Features::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, PlacedFeatures::bootstrap);
    }
}
