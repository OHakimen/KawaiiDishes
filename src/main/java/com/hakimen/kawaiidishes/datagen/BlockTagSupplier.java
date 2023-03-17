package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BlockTagSupplier extends BlockTagsProvider {
    public BlockTagSupplier(DataGenerator pGenerator, CompletableFuture<HolderLookup.Provider> lookup, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator.getPackOutput(),lookup, KawaiiDishes.modId, existingFileHelper);
    }


    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

    }
}
