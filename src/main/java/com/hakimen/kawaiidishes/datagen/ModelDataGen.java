package com.hakimen.kawaiidishes.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.custom.Registries;
import com.hakimen.kawaiidishes.custom.types.ThighHighDecoration;
import com.hakimen.kawaiidishes.datagen.model.BlockStateModelDataGen;
import com.hakimen.kawaiidishes.datagen.model.ItemModelDataGen;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.client.model.Model;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import java.util.Map;

public class ModelDataGen extends FabricModelProvider {
    public ModelDataGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator stateGen) {
        BlockStateModelDataGen.gen(stateGen);
    }


    @Override
    public void generateItemModels(ItemModelGenerator itemGen) {
        ItemModelDataGen.gen(itemGen);
    }


}
