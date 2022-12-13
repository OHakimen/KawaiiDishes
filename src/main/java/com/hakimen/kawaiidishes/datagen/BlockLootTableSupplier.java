package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.registry.BlockRegister;
import net.minecraft.core.Registry;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class BlockLootTableSupplier extends BlockLoot {

    @Override
    protected void addTables() {
        for (var i: BlockRegister.BLOCKS.getEntries().stream().toList()) {
            if(!i.get().equals(BlockRegister.coffeePlant.get())
            && !Registry.BLOCK.getKey(i.get()).toString().contains("_coffee")){
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
        this.dropOther(BlockRegister.cafeAuLaitMug.get(), Items.AIR);
        this.dropOther(BlockRegister.coffeePlant.get(), Items.AIR);
    }
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BlockRegister.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
