package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BlockStateSupplier extends BlockStateProvider {
    public BlockStateSupplier(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, KawaiiDishes.modId, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for(RegistryObject<Block> blocks: BlockRegister.BLOCKS.getEntries().stream().toList()){
            if(blocks.get().getRegistryName().getPath().contains("_stool")){
                genState(blocks.get());
            }
        }
    }

    public void genState(Block block){
        try {
            FileWriter fileWriter = new FileWriter("D:\\Coding\\Java\\KawaiiDishes\\src\\generated\\resources\\assets\\kawaiidishes\\blockstates\\"+block.getRegistryName().getPath()+".json");
            fileWriter.write(String.format("{\n" +
                    "  \"variants\": {\n" +
                    "    \"\": {\n" +
                    "      \"model\": \"%s\"\n" +
                    "    }\n" +
                    "  }\n" +
                    "}",block.getRegistryName().toString().replace("kawaiidishes:","kawaiidishes:block/stools/")));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
