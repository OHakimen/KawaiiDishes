package com.hakimen.kawaiidishes.block_entities;

import com.hakimen.kawaiidishes.item.IDyeableItem;
import com.hakimen.kawaiidishes.item.SeatItem;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class SeatBlockEntity extends BlockEntity {

    int color;
    public SeatBlockEntity(BlockPos pPos, BlockState pState) {
        super(BlockEntityRegister.SEAT.get(), pPos, pState);
    }

    public int getColor(){
        return color;
    }

    @Override
    protected void writeNbt(NbtCompound pTag) {
        pTag.putInt("Color", color);
        super.writeNbt(pTag);
    }

    @Override
    public void readNbt(NbtCompound pTag) {
        super.readNbt(pTag);
        color = pTag.getInt("Color");
    }

    public void saveToItem(SeatBlockEntity blockEntity, ItemStack pStack){
        SeatItem item = (SeatItem) pStack.getItem();
        if(blockEntity.getColor() == IDyeableItem.defaultColor){
            return;
        }
        item.setBaseColor(pStack, color);
    }

    public SeatBlockEntity fromItem(ItemStack pStack){
        SeatItem item = (SeatItem) pStack.getItem();

        this.color = item.getBaseColor(pStack);
        return this;
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this,BlockEntity::createNbtWithIdentifyingData);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbtWithIdentifyingData();
    }
}
