package com.hakimen.kawaiidishes.registry;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProviderType;
import net.minecraft.loot.provider.number.LootNumberProviderTypes;
import net.minecraft.util.Identifier;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class LootModifierRegister {

    private static final Identifier CHERRY_BLOSSOM_LEAVES = Blocks.CHERRY_LEAVES.getLootTableId();


    static HashMap<Identifier, Consumer<LootTable.Builder>> MODIFY_LOOT_HASHMAP = new HashMap<>();

    static {
        MODIFY_LOOT_HASHMAP.put(CHERRY_BLOSSOM_LEAVES, (tableBuilder) -> {
            LootPool.Builder poolBuilder = LootPool.builder()
                    .with(ItemEntry.builder(ItemRegister.CHERRY.get()).build())
                    .conditionally(RandomChanceLootCondition.builder(0.25f).build())
                    .bonusRolls(new LootNumberProvider() {
                        @Override
                        public float nextFloat(LootContext lootContext) {
                            return lootContext.getRandom().nextBetweenExclusive(1,3);
                        }

                        @Override
                        public LootNumberProviderType getType() {
                            return LootNumberProviderTypes.UNIFORM;
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
