package com.hakimen.kawaiidishes.world.feature;

import com.hakimen.kawaiidishes.registry.PlacedFeaturesRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Set;

public class CoffeePlantFeature {

    public static void generateCoffee(final BiomeLoadingEvent event) {
        List<Holder<PlacedFeature>> base =
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);

        if(event.getCategory() != Biome.BiomeCategory.DESERT
                || event.getCategory() != Biome.BiomeCategory.BEACH
                || event.getCategory() != Biome.BiomeCategory.MESA
                || event.getCategory() != Biome.BiomeCategory.OCEAN
                || event.getCategory() != Biome.BiomeCategory.MUSHROOM){
            base.add(PlacedFeaturesRegistry.COFFEE_PLANT_PLACED);
        }
    }
}
