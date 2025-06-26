package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.world.PlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.GenerationStep;

public class WorldGenerationRegister {

    public static void register() {
        BiomeModifications.addFeature(
                BiomeSelectors.tag(
                        new TagKey<>(Registries.BIOME,
                                new ResourceLocation(KawaiiDishes.MODID, "can_place_coffee_bushes")
                        )
                ), GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.COFFEE_BUSH_PLACED_KEY);
    }
}
