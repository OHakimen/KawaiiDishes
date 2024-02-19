package com.hakimen.kawaiidishes.datagen.loottable;

import com.hakimen.kawaiidishes.registry.BlockRegister;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public class BlockLootTableDataGen extends BlockLootSubProvider {
    public BlockLootTableDataGen() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    static List<Block> blocks = List.of(
            BlockRegister.KITCHEN_TILES.get(),
            BlockRegister.BLENDER.get(),
            BlockRegister.COFFEE_MACHINE.get(),
            BlockRegister.MUG.get(),
            BlockRegister.DISPLAY_CASE.get(),
            BlockRegister.ICE_CREAM_MAKER.get()
    );

    @Override
    protected void generate() {
        blocks.forEach(this::dropSelf);
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return blocks;
    }
}
