package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = KawaiiDishes.modId, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        generator.addProvider(true,new ItemModelSupplier(generator,fileHelper));
        generator.addProvider(true,new BlockStateSupplier(generator,fileHelper));
        generator.addProvider(true,new LangSupplier(generator,"en_us"));
        generator.addProvider(true,new CraftingRecipeSupplier(generator));
        generator.addProvider(true,new LootTableSupplier(generator));
        var blockTagSupplier = new BlockTagSupplier(generator,fileHelper);
        generator.addProvider(true,blockTagSupplier);
        generator.addProvider(true, new ItemTagSupplier(generator,blockTagSupplier,fileHelper));
    }
}
