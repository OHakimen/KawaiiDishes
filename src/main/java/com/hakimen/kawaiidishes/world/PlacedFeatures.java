//package com.hakimen.kawaiidishes.world;
//
//import com.hakimen.kawaiidishes.KawaiiDishes;
//import net.minecraft.core.Holder;
//import net.minecraft.core.HolderGetter;
//import net.minecraft.core.RegistrySetBuilder;
//import net.minecraft.core.registries.Registries;
//import net.minecraft.data.worldgen.BootstrapContext;
//import net.minecraft.data.worldgen.placement.PlacementUtils;
//import net.minecraft.resources.ResourceKey;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
//import net.minecraft.world.level.levelgen.placement.PlacedFeature;
//
//import net.minecraft.world.level.levelgen.placement.*;
//
//import java.util.List;
//
//public class PlacedFeatures {
//    public static final ResourceKey<PlacedFeature> COFFEE_BUSH_PLACED_KEY = registerKey("coffee_bush_placed");
//
//
//    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
//        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
//
//        register(context, COFFEE_BUSH_PLACED_KEY, configuredFeatures.getOrThrow(Features.COFFEE_BUSH_KEY),
//                List.of(RarityFilter.onAverageOnceEvery(64),
//                        InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
//    }
//
//    private static ResourceKey<PlacedFeature> registerKey(String name) {
//        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, name));
//    }
//
//    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
//                                 List<PlacementModifier> modifiers) {
//        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
//    }
//
//}

// TODO: worldgen