package com.hakimen.kawaiidishes.world;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraftforge.registries.DeferredRegister;

public class ConfiguratedFeatures {

    public static final DeferredRegister<Feature<?>> FEATURE_REGISTER = DeferredRegister.create(Registries.FEATURE, KawaiiDishes.modId);

    public static final Feature<RandomPatchConfiguration> coffeePlantFeature = register("coffee_plant", Feature.NO_BONEMEAL_FLOWER);
    public static final ResourceKey<ConfiguredFeature<?,?>> coffeePlantConfigured = createKey("coffee_plant");


    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(KawaiiDishes.modId, name));

    }


    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context){
        register(context, coffeePlantConfigured, coffeePlantFeature, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BlockRegister.coffeePlant.get()))));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, F feature, FC configuration)
    {
        context.register(configuredFeatureKey, new ConfiguredFeature<>(feature, configuration));
    }

    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(String key, F value)
    {
        FEATURE_REGISTER.register(key, () -> value);
        return value;
    }
}
