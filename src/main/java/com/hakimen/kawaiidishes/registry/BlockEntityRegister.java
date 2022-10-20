package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.blocks.MugBlock;
import com.hakimen.kawaiidishes.blocks.block_entities.CoffeeMachineBlockEntity;
import com.hakimen.kawaiidishes.blocks.block_entities.CoffeePressBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.hakimen.kawaiidishes.KawaiiDishes.modId;

public class BlockEntityRegister {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES,modId);

    public static final RegistryObject<BlockEntityType<CoffeePressBlockEntity>> coffeePress = BLOCK_ENTITIES.register("coffee_press_entity",
            ()->BlockEntityType.Builder.of(CoffeePressBlockEntity::new,BlockRegister.coffeePress.get()).build(null));
    public static final RegistryObject<BlockEntityType<CoffeeMachineBlockEntity>> coffeeMachine = BLOCK_ENTITIES.register("coffee_machine_entity",
            ()->BlockEntityType.Builder.of(CoffeeMachineBlockEntity::new,BlockRegister.coffeeMachine.get()).build(null));



    public static void register(IEventBus bus){
        BLOCK_ENTITIES.register(bus);
    }
}
