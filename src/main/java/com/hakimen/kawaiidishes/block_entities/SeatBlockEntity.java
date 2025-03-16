package com.hakimen.kawaiidishes.block_entities;

import com.hakimen.kawaiidishes.item.IFourColorDyeableItem;
import com.hakimen.kawaiidishes.item.SeatItem;
import com.hakimen.kawaiidishes.item.component.KawaiiDyeableComponent;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import com.hakimen.kawaiidishes.registry.DataComponentRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
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
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.putInt("Color", color);
        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        color = pTag.getInt("Color");
    }

    public void saveToItem(SeatBlockEntity blockEntity, ItemStack pStack){
        if(blockEntity.getColor() == IFourColorDyeableItem.defaultColor){
            return;
        }
        pStack.update(DataComponentRegister.DYEABLE.get(), KawaiiDyeableComponent.DEFAULT, dyeable -> new KawaiiDyeableComponent.KawaiiDyeableBuilder(dyeable).setBase(color).build());
    }

    public SeatBlockEntity fromItem(ItemStack pStack){
        KawaiiDyeableComponent.KawaiiDyeable data = pStack.get(DataComponentRegister.DYEABLE.get());
        this.color = data.getBase();
        return this;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this,BlockEntity::saveWithFullMetadata);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return this.saveWithFullMetadata(pRegistries);
    }
}