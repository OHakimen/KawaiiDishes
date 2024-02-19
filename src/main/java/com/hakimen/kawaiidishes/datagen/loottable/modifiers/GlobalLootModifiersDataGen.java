package com.hakimen.kawaiidishes.datagen.loottable.modifiers;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.loot.modifiers.ExtendLootTableModifier;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.List;

public class GlobalLootModifiersDataGen extends GlobalLootModifierProvider {
    public GlobalLootModifiersDataGen(DataGenerator generator) {
        super(generator.getPackOutput(), KawaiiDishes.MODID);
    }

    @Override
    protected void start() {
        add("cherry_to_cherry_blossoms",new ExtendLootTableModifier(
                new LootItemCondition[]{
                        LootItemRandomChanceCondition.randomChance(0.15f).build(),
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.CHERRY_LEAVES).build()
                },
                List.of(
                        ItemRegister.CHERRY.get().getDefaultInstance()
                )
        ));

        add("double_cherry_to_cherry_blossoms",new ExtendLootTableModifier(
                new LootItemCondition[]{
                        LootItemRandomChanceCondition.randomChance(0.10f).build(),
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.CHERRY_LEAVES).build()
                },
                List.of(
                        ItemRegister.CHERRY.get().getDefaultInstance(),
                        ItemRegister.CHERRY.get().getDefaultInstance()
                )
        ));
        add("triple_cherry_to_cherry_blossoms",new ExtendLootTableModifier(
                new LootItemCondition[]{
                        LootItemRandomChanceCondition.randomChance(0.05f).build(),
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.CHERRY_LEAVES).build()
                },
                List.of(
                        ItemRegister.CHERRY.get().getDefaultInstance(),
                        ItemRegister.CHERRY.get().getDefaultInstance(),
                        ItemRegister.CHERRY.get().getDefaultInstance()
                )
        ));
    }
}
