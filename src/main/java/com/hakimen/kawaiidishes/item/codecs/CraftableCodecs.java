package com.hakimen.kawaiidishes.item.codecs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CraftableCodecs {

    private static final Codec<Item> ITEM_CODEC = ExtraCodecs.validate(
            BuiltInRegistries.ITEM.byNameCodec(),
            DataResult::success
    );
    public static final Codec<ItemStack> ITEM_STACK_CODEC = RecordCodecBuilder.create(
            provider -> provider.group(
                            ITEM_CODEC.fieldOf("item").forGetter(ItemStack::getItem),
                            ExtraCodecs.strictOptionalField(ExtraCodecs.NON_NEGATIVE_INT, "count", 1).forGetter(ItemStack::getCount),
                            ExtraCodecs.strictOptionalField(ExtraCodecs.withAlternative(net.minecraft.nbt.TagParser.AS_CODEC, net.minecraft.nbt.CompoundTag.CODEC), "nbt").forGetter(stack -> java.util.Optional.ofNullable(stack.getTag()))
                    )
                    .apply(provider, ItemStack::new)
    );
}
