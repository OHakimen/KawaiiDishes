package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.blocks.block_entities.*;
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

    public static final RegistryObject<BlockEntityType<IceCreamMachineBlockEntity>> iceCreamMachine = BLOCK_ENTITIES.register("ice_cream_machine_entity",
            ()->BlockEntityType.Builder.of(IceCreamMachineBlockEntity::new,BlockRegister.iceCreamMachine.get()).build(null));

    public static final RegistryObject<BlockEntityType<BlenderBlockEntity>> blender = BLOCK_ENTITIES.register("blender_entity",
            ()->BlockEntityType.Builder.of(BlenderBlockEntity::new,BlockRegister.blender.get()).build(null));


    public static final RegistryObject<BlockEntityType<PlaceableFoodBlockEntity>> placeableFood = BLOCK_ENTITIES.register("coffee_mug_entity",
            ()->BlockEntityType.Builder.of(
                    PlaceableFoodBlockEntity::new,
                    BlockRegister.mochaMug.get(),
                    BlockRegister.cafeAuLaitMug.get(),
                    BlockRegister.doppioMug.get(),
                    BlockRegister.americanMug.get(),
                    BlockRegister.cappuccinoMug.get(),
                    BlockRegister.macchiatoMug.get(),
                    BlockRegister.latteMug.get(),
                    BlockRegister.expressoMug.get(),

                    BlockRegister.sweetBerryIceCream.get(),
                    BlockRegister.napolitanoIceCream.get(),
                    BlockRegister.creamIceCream.get(),
                    BlockRegister.chocolateIceCream.get(),
                    BlockRegister.mochaIceCream.get(),
                    BlockRegister.coffeeIceCream.get(),
                    BlockRegister.glowBerryIceCream.get(),

                    BlockRegister.sweetBerryMilkshake.get()
            ).build(null));


    public static void register(IEventBus bus){
        BLOCK_ENTITIES.register(bus);
    }
}
