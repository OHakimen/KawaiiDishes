package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.custom.Recorder;
import com.hakimen.kawaiidishes.entity.SeatEntity;
import java.util.function.Supplier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class EntityRegister {
    public static final Recorder<EntityType<?>> ENTITIES = new Recorder<>(BuiltInRegistries.ENTITY_TYPE, KawaiiDishes.MODID);

    public static final Supplier<EntityType<SeatEntity>> SEAT = ENTITIES.register("seat",
            () -> EntityType.Builder.<SeatEntity>of(
                    SeatEntity::new, MobCategory.MISC).sized(1f, 1f)
            .build(
                    new ResourceLocation(KawaiiDishes.MODID, "seat").toString()
            )
    );

    public static void register(){
        //Bootstrap
    }
}
