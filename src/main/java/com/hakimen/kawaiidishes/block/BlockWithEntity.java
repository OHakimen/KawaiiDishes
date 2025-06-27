package com.hakimen.kawaiidishes.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class BlockWithEntity extends Block implements BlockEntityProvider {
    public BlockWithEntity(Settings pProperties) {
        super(pProperties);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pPos, BlockState pState) {
        return null;
    }

}
