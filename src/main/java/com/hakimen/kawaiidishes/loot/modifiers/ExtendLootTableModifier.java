package com.hakimen.kawaiidishes.loot.modifiers;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

public class ExtendLootTableModifier extends LootModifier {
    public static final Supplier<Codec<ExtendLootTableModifier>> CODEC = Suppliers.memoize(
            () -> RecordCodecBuilder.create(inst -> codecStart(inst).and(
                            ItemStack.CODEC.listOf()
                                    .fieldOf("items")
                                    .forGetter(ExtendLootTableModifier::getStackList)
                            )
                    .apply(inst, ExtendLootTableModifier::new)
            )
    );
    List<ItemStack> stackList;

    public ExtendLootTableModifier(LootItemCondition[] conditionsIn, List<ItemStack> stackList) {
        super(conditionsIn);
        this.stackList = stackList;
    }

    public List<ItemStack> getStackList() {
        return stackList;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> loot, LootContext lootContext) {
        for (LootItemCondition condition : this.conditions) {
            if (!condition.test(lootContext)) {
                return loot;
            }
        }

        loot.addAll(getStackList());

        return loot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
