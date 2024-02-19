package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BlockTagDataGen extends BlockTagsProvider {
    public BlockTagDataGen(DataGenerator generator, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), lookupProvider, KawaiiDishes.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        minableByToolTier(BlockTags.NEEDS_STONE_TOOL, BlockTags.MINEABLE_WITH_PICKAXE, BlockRegister.KITCHEN_TILES.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BlockRegister.BLENDER.get())
                .add(BlockRegister.MUG.get())
                .add(BlockRegister.COFFEE_MACHINE.get())
                .add(BlockRegister.DISPLAY_CASE.get())
                .add(BlockRegister.ICE_CREAM_MAKER.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(BlockRegister.SEAT.get());

    }

    public void minableByToolTier(TagKey<Block> tier, TagKey<Block> tool, Block block){
        tag(tier).add(block);
        tag(tool).add(block);
    }
}
