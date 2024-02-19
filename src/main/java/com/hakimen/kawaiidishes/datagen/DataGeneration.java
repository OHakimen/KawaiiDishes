package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.datagen.loottable.modifiers.GlobalLootModifiersDataGen;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = KawaiiDishes.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGeneration {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        var pack = generator.getVanillaPack(true);
        pack.addProvider(output -> new RecipeDataGen(generator,lookupProvider));
        pack.addProvider(output -> new BlockStateDataGen(generator,helper));
        pack.addProvider(output -> new ItemModelDataGen(generator,helper));

        BlockTagsProvider blockTagsProvider = new BlockTagDataGen(generator, lookupProvider, helper);

        pack.addProvider(output -> blockTagsProvider);
        pack.addProvider(output -> new ItemTagDataGen(generator, lookupProvider, blockTagsProvider));

        pack.addProvider(output -> new BiomeTagDataGen(generator, lookupProvider, helper));
        pack.addProvider(output -> new WorldGenDataGen(generator, lookupProvider));

        pack.addProvider(output -> new GlobalLootModifiersDataGen(generator));

        pack.addProvider(LootTableDataGen::create);
        pack.addProvider(output -> new LangDataGen(generator));
    }
}
