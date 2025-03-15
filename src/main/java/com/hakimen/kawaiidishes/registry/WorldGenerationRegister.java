package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.world.PlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;

public class WorldGenerationRegister {

    public static void register() {
        BiomeModifications.addFeature(
                BiomeSelectors.tag(
                        new TagKey<>(RegistryKeys.BIOME,
                                new Identifier(KawaiiDishes.MODID, "can_place_coffee_bushes")
                        )
                ), GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatures.COFFEE_BUSH_PLACED_KEY);
    }
}
