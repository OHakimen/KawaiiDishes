package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.custom.Recorder;
import com.hakimen.kawaiidishes.entity.SeatEntity;
import java.util.function.Supplier;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class EntityRegister {
    public static final Recorder<EntityType<?>> ENTITIES = new Recorder<>(Registries.ENTITY_TYPE, KawaiiDishes.MODID);

    public static final Supplier<EntityType<SeatEntity>> SEAT = ENTITIES.register("seat",
            () -> EntityType.Builder.<SeatEntity>create(
                    SeatEntity::new, SpawnGroup.MISC).setDimensions(1f, 1f)
            .build(
                    new Identifier(KawaiiDishes.MODID, "seat").toString()
            )
    );

    public static void register(){
        //Bootstrap
    }
}
