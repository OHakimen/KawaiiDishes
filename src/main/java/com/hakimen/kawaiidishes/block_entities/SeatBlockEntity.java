package com.hakimen.kawaiidishes.block_entities;

import com.hakimen.kawaiidishes.item.IDyeableItem;
import com.hakimen.kawaiidishes.item.SeatItem;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class SeatBlockEntity extends BlockEntity {

    int color;
    public SeatBlockEntity(BlockPos pPos, BlockState pState) {
        super(BlockEntityRegister.SEAT.get(), pPos, pState);
    }

    public int getColor(){
        return serializeNBT().getInt("Color");
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putInt("Color", color);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
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
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this,BlockEntity::saveWithFullMetadata);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithFullMetadata();
    }
}
