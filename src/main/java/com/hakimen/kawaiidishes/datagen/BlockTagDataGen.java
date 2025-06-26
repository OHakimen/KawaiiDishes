package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.registry.BlockRegister;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import java.util.concurrent.CompletableFuture;

public class BlockTagDataGen extends FabricTagProvider.BlockTagProvider {
    public BlockTagDataGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
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
