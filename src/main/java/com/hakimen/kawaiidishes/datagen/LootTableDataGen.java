package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.datagen.loottable.BlockLootTableDataGen;
import com.hakimen.kawaiidishes.datagen.loottable.ChestLootDataGen;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class LootTableDataGen {
    public static LootTableProvider create(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(BlockLootTableDataGen::new, LootContextParamSets.BLOCK),
                new LootTableProvider.SubProviderEntry(ChestLootDataGen::new, LootContextParamSets.CHEST)
        ),provider);
    }
}
