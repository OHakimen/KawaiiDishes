package com.hakimen.kawaiidishes.blocks.block_entities;

import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class IceCreamBlockEntity extends BlockEntity {

    public CompoundTag mainEffect = new CompoundTag();
    public CompoundTag secondaryEffect = new CompoundTag();
    public IceCreamBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(BlockEntityRegister.iceCream.get(), pWorldPosition, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("mainEffect",mainEffect);
        pTag.put("secondaryEffect",secondaryEffect);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        mainEffect = pTag.getCompound("mainEffect") != null ? pTag.getCompound("mainEffect") : new CompoundTag() ;
        secondaryEffect = pTag.getCompound("secondaryEffect") != null ? pTag.getCompound("secondaryEffect") : new CompoundTag() ;
        super.load(pTag);
    }

}
