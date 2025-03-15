package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.registry.BlockRegister;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import java.util.concurrent.CompletableFuture;

public class BlockTagDataGen extends FabricTagProvider.BlockTagProvider {
    public BlockTagDataGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        minableByToolTier(BlockTags.NEEDS_STONE_TOOL, BlockTags.PICKAXE_MINEABLE, BlockRegister.KITCHEN_TILES.get());

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(BlockRegister.BLENDER.get())
                .add(BlockRegister.MUG.get())
                .add(BlockRegister.COFFEE_MACHINE.get())
                .add(BlockRegister.DISPLAY_CASE.get())
                .add(BlockRegister.ICE_CREAM_MAKER.get());

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(BlockRegister.SEAT.get());
    }

    public void minableByToolTier(TagKey<Block> tier, TagKey<Block> tool, Block block){
        getOrCreateTagBuilder(tier).add(block);
        getOrCreateTagBuilder(tool).add(block);
    }
}
