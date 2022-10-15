package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.registry.BlockRegister;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class BlockLootTableSupplier extends BlockLoot {

    @Override
    protected void addTables() {
        for (var i: BlockRegister.BLOCKS.getEntries().stream().toList()) {
            if(!i.get().equals(BlockRegister.coffeePlant.get())){
                this.dropSelf(i.get());
            }
        }
        this.dropOther(BlockRegister.coffeePlant.get(), Items.AIR);
    }
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BlockRegister.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
