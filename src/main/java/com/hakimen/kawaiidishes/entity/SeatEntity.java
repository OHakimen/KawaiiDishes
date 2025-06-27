package com.hakimen.kawaiidishes.entity;

import com.hakimen.kawaiidishes.registry.EntityRegister;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SeatEntity extends Entity {

    public SeatEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }
    public SeatEntity(World worldIn, BlockPos position) {
        super(EntityRegister.SEAT.get(), worldIn);
        this.setPosition(position.getX() + 0.5D, position.getY() + 0D, position.getZ() + 0.5D);
    }

    public static ActionResult sitDown(PlayerEntity player, World level, BlockPos position) {

        List<SeatEntity> seats = level.getNonSpectatingEntities(SeatEntity.class,
                new Box(position.getX(), position.getY(), position.getZ(),
                        position.getX() + 1.0, position.getY() + 1.0, position.getZ() + 1.0)
        );
        if(seats.isEmpty())
        {
            SeatEntity seat = new SeatEntity(level, position);
            level.spawnEntity(seat);
            player.startRiding(seat, false);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public boolean collidesWith(Entity entity) {
        return false;
    }

    @Override
    public double getMountedHeightOffset() {
        return 0.5f;
    }


    @Override
    public float getTargetingMargin() {
        return 0.0f;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean canHit() {
        return false;
    }

    @Override
    public void move(MovementType typeIn, Vec3d pos) {

    }

    @Override
    public void onPlayerCollision(PlayerEntity entityIn) {

    }


    @Override
    protected void updatePassengerPosition(Entity pPassenger, PositionUpdater pCallback) {
        super.updatePassengerPosition(pPassenger, pCallback);
    }

    @Override
    public void pushAwayFrom(Entity entityIn) {

    }

    @Override
    public void tick() {
        super.tick();
        if(!this.getWorld().isClient)
        {
            if(this.getPassengerList().isEmpty() || this.getWorld().isAir(this.getBlockPos()))
            {
                this.remove(RemovalReason.DISCARDED);
                this.getWorld().updateComparators(getBlockPos(), this.getWorld().getBlockState(getBlockPos()).getBlock());
            }
        }
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound compound) {

    }

    @Override
    protected boolean canStartRiding(Entity entityIn) {
        return entityIn instanceof PlayerEntity;
    }

    @Override
    protected void checkBlockCollision() {

    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound compound) {

    }
}
