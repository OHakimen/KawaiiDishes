package com.hakimen.kawaiidishes.registry;

import com.google.common.collect.ImmutableSet;
import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.config.KawaiiDishesCommonConfig;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class VillagerWorkRegister {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, KawaiiDishes.modId);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS,  KawaiiDishes.modId);

    public static final RegistryObject<PoiType> baristaPOI = POI_TYPES.register("barista", () -> new PoiType(ImmutableSet.copyOf(BlockRegister.coffeeMachine.get().getStateDefinition().getPossibleStates()), 1, 1));
    public static final RegistryObject<VillagerProfession> baristaVillagerProfession = VILLAGER_PROFESSIONS.register("barista", () -> new VillagerProfession(KawaiiDishes.modId + ":barista", holder -> holder.is(baristaPOI.getKey()), holder -> holder.is(baristaPOI.getKey()), ImmutableSet.of(), ImmutableSet.of(BlockRegister.coffeeMachine.get()), SoundEvents.VILLAGER_WORK_CLERIC));


    public static void register(IEventBus bus) {
        if (!KawaiiDishesCommonConfig.newProfessions.get()) return;

        VILLAGER_PROFESSIONS.register(bus);
        POI_TYPES.register(bus);
    }

}
