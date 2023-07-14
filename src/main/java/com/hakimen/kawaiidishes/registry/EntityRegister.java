package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.entity.SittableEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.hakimen.kawaiidishes.KawaiiDishes.modId;

public class EntityRegister {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,modId);

    public static final RegistryObject<EntityType<SittableEntity>> SEAT = ENTITIES.register("seat",
            () -> EntityType.Builder.<SittableEntity>of(SittableEntity::new, MobCategory.MISC).sized(1f, 1f)
                    .build(new ResourceLocation(modId, "seat").toString()));

    public static void register(IEventBus bus){
        ENTITIES.register(bus);
    }
}
