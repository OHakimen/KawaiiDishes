package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.loot.modifiers.ExtendLootTableModifier;
import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class LootModifierRegistry {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, KawaiiDishes.MODID);
    public static final DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<ExtendLootTableModifier>> EXTEND_LOOT_TABLE = LOOT_MODIFIERS.register("extend_loot_table", ExtendLootTableModifier.CODEC);

    public static void register(IEventBus bus){
        LOOT_MODIFIERS.register(bus);
    }
}
