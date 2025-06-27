package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.block_entities.*;
import com.hakimen.kawaiidishes.custom.Recorder;
import java.util.function.Supplier;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;

public class BlockEntityRegister {

    public static final Recorder<BlockEntityType<?>> BLOCK_ENTITIES = new Recorder<>(Registries.BLOCK_ENTITY_TYPE, KawaiiDishes.MODID);

    public static final Supplier<BlockEntityType<CoffeeMachineBlockEntity>> COFFEE_MACHINE =
            BLOCK_ENTITIES.register("coffee_machine",
                    () -> BlockEntityType.Builder.create(
                            CoffeeMachineBlockEntity::new,
                            BlockRegister.COFFEE_MACHINE.get()
                    ).build(null)
            );

    public static final Supplier<BlockEntityType<BlenderBlockEntity>> BLENDER =
            BLOCK_ENTITIES.register("blender",
                    () -> BlockEntityType.Builder.create(
                            BlenderBlockEntity::new,
                            BlockRegister.BLENDER.get()
                    ).build(null)
            );

    public static final Supplier<BlockEntityType<SeatBlockEntity>> SEAT =
            BLOCK_ENTITIES.register("seat",
                    () -> BlockEntityType.Builder.create(
                            SeatBlockEntity::new,
                            BlockRegister.SEAT.get()
                    ).build(null)
            );

    public static final Supplier<BlockEntityType<DisplayCaseBlockEntity>> DISPLAY_CASE =
            BLOCK_ENTITIES.register("display_case",
                    () -> BlockEntityType.Builder.create(
                            DisplayCaseBlockEntity::new,
                            BlockRegister.DISPLAY_CASE.get()
                    ).build(null)
            );

    public static final Supplier<BlockEntityType<IceCreamMakerBlockEntity>> ICE_CREAM_MAKER =
            BLOCK_ENTITIES.register("ice_cream_maker",
                    () -> BlockEntityType.Builder.create(
                            IceCreamMakerBlockEntity::new,
                            BlockRegister.ICE_CREAM_MAKER.get()
                    ).build(null)
            );

    public static final Supplier<BlockEntityType<IncenseBlockEntity>> INCENSE =
            BLOCK_ENTITIES.register("incense",
                    () -> BlockEntityType.Builder.create(
                            IncenseBlockEntity::new,
                            BlockRegister.INCENSE_GLASS.get()
                    ).build(null)
            );

    public static void register(){
        //Bootstrap
    }
}
