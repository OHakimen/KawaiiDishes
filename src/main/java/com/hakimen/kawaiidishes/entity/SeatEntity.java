package com.hakimen.kawaiidishes.entity;

import com.hakimen.kawaiidishes.registry.EntityRegister;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class SeatEntity extends Entity {

    public SeatEntity(EntityType<?> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }
    public SeatEntity(Level worldIn, BlockPos position) {
        super(EntityRegister.SEAT.get(), worldIn);
        this.setPos(position.getX() + 0.5D, position.getY() + 0D, position.getZ() + 0.5D);
    }

    public static InteractionResult sitDown(Player player, Level level, BlockPos position) {

        List<SeatEntity> seats = level.getEntitiesOfClass(SeatEntity.class,
                new AABB(position.getX(), position.getY(), position.getZ(),
                        position.getX() + 1.0, position.getY() + 1.0, position.getZ() + 1.0)
        );
        if(seats.isEmpty())
        {
            SeatEntity seat = new SeatEntity(level, position);
            level.addFreshEntity(seat);
            player.startRiding(seat, false);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean canCollideWith(Entity entity) {
        return false;
    }

    @Override
    public double getPassengersRidingOffset() {
        return 0.5f;
    }


    @Override
    public float getPickRadius() {
        return 0.0f;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public void move(MoverType typeIn, Vec3 pos) {

    }

    @Override
    public void playerTouch(Player entityIn) {

    }


    @Override
    protected void positionRider(Entity pPassenger, MoveFunction pCallback) {
        super.positionRider(pPassenger, pCallback);
    }

    @Override
    public void push(Entity entityIn) {

    }

    @Override
    public void tick() {
        super.tick();
        if(!this.level().isClientSide)
        {
            if(this.getPassengers().isEmpty() || this.level().isEmptyBlock(this.blockPosition()))
            {
                this.remove(RemovalReason.DISCARDED);
                this.level().updateNeighbourForOutputSignal(blockPosition(), this.level().getBlockState(blockPosition()).getBlock());
            }
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {

    }

    @Override
    protected boolean canRide(Entity entityIn) {
        return entityIn instanceof Player;
    }

    @Override
    protected void checkInsideBlocks() {

    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {

    }
}
