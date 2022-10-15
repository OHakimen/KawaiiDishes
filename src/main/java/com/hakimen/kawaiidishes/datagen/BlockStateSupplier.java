package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStateSupplier extends BlockStateProvider {
    public BlockStateSupplier(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, KawaiiDishes.modId, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }
}
