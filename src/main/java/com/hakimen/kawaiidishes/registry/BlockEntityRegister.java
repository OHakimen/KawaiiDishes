package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.block_entities.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockEntityRegister {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, KawaiiDishes.MODID);

    public static final DeferredHolder<BlockEntityType<?>,BlockEntityType<CoffeeMachineBlockEntity>> COFFEE_MACHINE =
            BLOCK_ENTITIES.register("coffee_machine",
                    () -> BlockEntityType.Builder.of(
                            CoffeeMachineBlockEntity::new,
                            BlockRegister.COFFEE_MACHINE.get()
                    ).build(null)
            );

    public static final DeferredHolder<BlockEntityType<?>,BlockEntityType<BlenderBlockEntity>> BLENDER =
            BLOCK_ENTITIES.register("blender",
                    () -> BlockEntityType.Builder.of(
                            BlenderBlockEntity::new,
                            BlockRegister.BLENDER.get()
                    ).build(null)
            );

    public static final DeferredHolder<BlockEntityType<?>,BlockEntityType<SeatBlockEntity>> SEAT =
            BLOCK_ENTITIES.register("seat",
                    () -> BlockEntityType.Builder.of(
                            SeatBlockEntity::new,
                            BlockRegister.SEAT.get()
                    ).build(null)
            );

    public static final DeferredHolder<BlockEntityType<?>,BlockEntityType<DisplayCaseBlockEntity>> DISPLAY_CASE =
            BLOCK_ENTITIES.register("display_case",
                    () -> BlockEntityType.Builder.of(
                            DisplayCaseBlockEntity::new,
                            BlockRegister.DISPLAY_CASE.get()
                    ).build(null)
            );

    public static final DeferredHolder<BlockEntityType<?>,BlockEntityType<IceCreamMakerBlockEntity>> ICE_CREAM_MAKER =
            BLOCK_ENTITIES.register("ice_cream_maker",
                    () -> BlockEntityType.Builder.of(
                            IceCreamMakerBlockEntity::new,
                            BlockRegister.ICE_CREAM_MAKER.get()
                    ).build(null)
            );

    public static final DeferredHolder<BlockEntityType<?>,BlockEntityType<IncenseBlockEntity>> INCENSE =
            BLOCK_ENTITIES.register("incense",
                    () -> BlockEntityType.Builder.of(
                            IncenseBlockEntity::new,
                            BlockRegister.INCENSE_GLASS.get()
                    ).build(null)
            );

    public static void register(IEventBus bus){
        BLOCK_ENTITIES.register(bus);
    }
}
