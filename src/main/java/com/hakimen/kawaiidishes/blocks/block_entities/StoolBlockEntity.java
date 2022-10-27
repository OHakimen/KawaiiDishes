package com.hakimen.kawaiidishes.blocks.block_entities;

import com.hakimen.kawaiidishes.entity.SittableEntity;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class StoolBlockEntity extends BlockEntity {

    public SittableEntity seat;

    public StoolBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(BlockEntityRegister.stool.get(), pWorldPosition, pBlockState);
    }

    public SittableEntity getOrCreateSeat() {
        if (this.seat == null) {
            final var seat = new SittableEntity(this.level);
            seat.absMoveTo(this.worldPosition.getX() + 0.5D, this.worldPosition.getY()+0.125f,
                    this.worldPosition.getZ() + 0.5D);
            this.level.addFreshEntity(seat);
            this.seat = seat;
        }

        return this.seat;
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        getOrCreateSeat();
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        if (this.seat != null) {
            this.seat.kill();
        }
    }
}
