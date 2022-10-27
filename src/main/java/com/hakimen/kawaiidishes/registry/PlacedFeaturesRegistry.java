package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.world.feature.ConfiguratedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class PlacedFeaturesRegistry {

    public static final Holder<PlacedFeature> COFFEE_PLANT_PLACED = PlacementUtils.register("pink_rose_placed",
            ConfiguratedFeatures.COFFEE_PLANT, RarityFilter.onAverageOnceEvery(16),
            InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
}
