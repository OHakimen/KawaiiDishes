package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.blocks.MugBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.hakimen.kawaiidishes.KawaiiDishes.modId;

public class BlockRegister {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,modId);

    public static final RegistryObject<MugBlock> mug = BLOCKS.register("mug",MugBlock::new);

    public static void register(IEventBus bus){
        BLOCKS.register(bus);
    }
}
