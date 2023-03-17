package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import net.minecraft.Util;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;


@Mod.EventBusSubscriber(modid = KawaiiDishes.modId, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        var holder = CompletableFuture.supplyAsync(VanillaRegistries::createLookup, Util.backgroundExecutor());
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();


        generator.addProvider(true,new ItemModelSupplier(generator,fileHelper));
        generator.addProvider(true,new BlockStateSupplier(generator,fileHelper));
        generator.addProvider(true,new LangSupplier(generator,"en_us"));
        generator.addProvider(true,new CraftingRecipeSupplier(generator));
        var blockTagSupplier = new BlockTagSupplier(generator,holder,fileHelper);
        generator.addProvider(true,blockTagSupplier);
        generator.addProvider(true, new ItemTagSupplier(generator,holder,blockTagSupplier,fileHelper));
        generator.addProvider(true, LootTableSupplier.create(generator.getPackOutput()));
        generator.addProvider(false, new WorldGenSupplier(generator.getPackOutput(),lookupProvider));

    }
}
