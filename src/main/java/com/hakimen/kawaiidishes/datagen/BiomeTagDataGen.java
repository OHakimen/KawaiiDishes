package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BiomeTagDataGen extends BiomeTagsProvider {

    public static TagKey<Biome> BIOME_TAGS = new TagKey<>(Registries.BIOME, new ResourceLocation(KawaiiDishes.MODID, "can_place_coffee_bushes"));
    public BiomeTagDataGen(DataGenerator generator, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), lookupProvider, KawaiiDishes.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BIOME_TAGS)
                .addTags(Tags.Biomes.IS_PLAINS)
                .addTags(Tags.Biomes.IS_PLATEAU)
                .add(Biomes.JUNGLE)
                .add(Biomes.BAMBOO_JUNGLE)
                .add(Biomes.SPARSE_JUNGLE)
                .add(Biomes.CHERRY_GROVE)
                .add(Biomes.FOREST)
                .add(Biomes.BIRCH_FOREST);
    }
}
