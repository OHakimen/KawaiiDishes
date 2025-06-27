package com.hakimen.kawaiidishes.world;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.block.CoffeeBushBlock;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class Features {
    public static final RegistryKey<ConfiguredFeature<?, ?>> COFFEE_BUSH_KEY = registerKey("coffee_bush");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context,COFFEE_BUSH_KEY, Feature.FLOWER, new RandomPatchFeatureConfig(
                8,4,2,
                PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(
                                BlockStateProvider.of(BlockRegister.COFFEE_BUSH.get().getDefaultState().with(CoffeeBushBlock.AGE,3))),
                        PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP
                ))
        );
    }
    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(KawaiiDishes.MODID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                          RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
