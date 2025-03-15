package com.hakimen.kawaiidishes.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class DirectionalBlockWithEntity extends HorizontalFacingBlock implements BlockEntityProvider {
    public DirectionalBlockWithEntity(Settings pProperties) {
        super(pProperties);
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pPos, BlockState pState) {
        return null;
    }

}
