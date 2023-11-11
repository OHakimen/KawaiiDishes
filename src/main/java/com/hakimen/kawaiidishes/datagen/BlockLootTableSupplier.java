package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.registry.BlockRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class BlockLootTableSupplier extends BlockLootSubProvider {

    public BlockLootTableSupplier() {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS);
    }

    @Override
    protected void generate() {
        for (var i: BlockRegister.BLOCKS.getEntries().stream().toList()) {
            if(!i.get().equals(BlockRegister.coffeePlant.get())
            && !BuiltInRegistries.BLOCK.getKey(i.get()).toString().contains("_coffee")){
                this.dropSelf(i.get());
            }
        }
        this.dropOther(BlockRegister.expressoMug.get(), Items.AIR);
        this.dropOther(BlockRegister.macchiatoMug.get(), Items.AIR);
        this.dropOther(BlockRegister.americanMug.get(), Items.AIR);
        this.dropOther(BlockRegister.mochaMug.get(), Items.AIR);
        this.dropOther(BlockRegister.latteMug.get(), Items.AIR);
        this.dropOther(BlockRegister.cappuccinoMug.get(), Items.AIR);
        this.dropOther(BlockRegister.doppioMug.get(), Items.AIR);
        this.dropOther(BlockRegister.coffeePlant.get(), Items.AIR);

        this.dropOther(BlockRegister.chocolateCheeseCake.get(), Items.AIR);
        this.dropOther(BlockRegister.cheeseCake.get(), Items.AIR);
        this.dropOther(BlockRegister.honeyCheeseCake.get(), Items.AIR);
    }
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BlockRegister.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
