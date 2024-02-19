package com.hakimen.kawaiidishes.datagen.loottable;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.function.BiConsumer;

public class ChestLootDataGen implements LootTableSubProvider {

    ResourceLocation MAID_CAFE = new ResourceLocation(KawaiiDishes.MODID, "chests/maid_cafe");

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> registry) {
        registry.accept(MAID_CAFE, LootTable
                .lootTable()
                .withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(16f))
                                .add(LootItem.lootTableItem(ItemRegister.APPLE_PIE_SLICE.get()).setWeight(10))
                                .add(LootItem.lootTableItem(ItemRegister.SWEET_BERRY_PIE_SLICE.get()).setWeight(10))
                                .add(LootItem.lootTableItem(ItemRegister.CAKE_SLICE.get()).setWeight(10))
                                .add(LootItem.lootTableItem(ItemRegister.CHEESE_CAKE_SLICE.get()).setWeight(10))
                                .add(LootItem.lootTableItem(ItemRegister.ROAST_COFFEE_BEANS.get()).setQuality(8).setWeight(20))
                                .add(LootItem.lootTableItem(Items.COCOA_BEANS).setQuality(4).setWeight(20))
                                .add(LootItem.lootTableItem(ItemRegister.STEAMED_MILK_BUCKET.get()).setWeight(10))
                                .add(LootItem.lootTableItem(ItemRegister.WAFFLE.get()).setQuality(4).setWeight(10))
                                .add(LootItem.lootTableItem(ItemRegister.CHOCOLATE_WAFFLE.get()).setWeight(10))
                                .add(LootItem.lootTableItem(Items.COOKIE).setQuality(4).setWeight(40))
                ));
    }
}
