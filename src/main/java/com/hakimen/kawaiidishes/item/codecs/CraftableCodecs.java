package com.hakimen.kawaiidishes.item.codecs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CraftableCodecs {

    private static final Codec<Item> ITEM_CODEC = BuiltInRegistries.ITEM.byNameCodec().validate(DataResult::success);

    public static final Codec<ItemStack> ITEM_STACK_CODEC = RecordCodecBuilder.create(
            provider -> provider.group(
                            ITEM_CODEC.fieldOf("item").forGetter(ItemStack::getItem),
                            ExtraCodecs.NON_NEGATIVE_INT.optionalFieldOf( "count", 1).forGetter(ItemStack::getCount),
                            DataComponentPatch.CODEC.optionalFieldOf("data" , DataComponentPatch.EMPTY)
                                    .forGetter(ItemStack::getComponentsPatch)
                    )
                    .apply(provider, (item, integer, dataComponentPatch) -> new ItemStack(Holder.direct(item), integer, dataComponentPatch))
    );
}