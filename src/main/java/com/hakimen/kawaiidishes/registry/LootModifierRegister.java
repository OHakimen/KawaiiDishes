package com.hakimen.kawaiidishes.registry;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.LootNumberProviderType;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.NumberProviders;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class LootModifierRegister {

    private static final ResourceLocation CHERRY_BLOSSOM_LEAVES = Blocks.CHERRY_LEAVES.getLootTable();


    static HashMap<ResourceLocation, Consumer<LootTable.Builder>> MODIFY_LOOT_HASHMAP = new HashMap<>();

    static {
        MODIFY_LOOT_HASHMAP.put(CHERRY_BLOSSOM_LEAVES, (tableBuilder) -> {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .with(LootItem.lootTableItem(ItemRegister.CHERRY.get()).build())
                    .conditionally(LootItemRandomChanceCondition.randomChance(0.25f).build())
                    .setBonusRolls(new NumberProvider() {
                        @Override
                        public float getFloat(LootContext lootContext) {
                            return lootContext.getRandom().nextInt(1,3);
                        }

                        @Override
                        public LootNumberProviderType getType() {
                            return NumberProviders.UNIFORM;
                        }
                    });

            tableBuilder.pool(poolBuilder.build());
        });
    }
    public static void register(){
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin()) {
                MODIFY_LOOT_HASHMAP.forEach((k, v) -> {
                    if(k.equals(id)){
                        v.accept(tableBuilder);
                    }
                });
            }
        });
    }
}
