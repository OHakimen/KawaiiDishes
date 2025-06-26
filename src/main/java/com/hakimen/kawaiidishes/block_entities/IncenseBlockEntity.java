package com.hakimen.kawaiidishes.block_entities;

import com.hakimen.kawaiidishes.block.IncenseBlock;
import com.hakimen.kawaiidishes.custom.Registries;
import com.hakimen.kawaiidishes.custom.types.Aroma;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import com.hakimen.kawaiidishes.registry.ParticleRegister;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class IncenseBlockEntity extends BlockEntity {

    SingleVariantStorage<ItemVariant> inventory = new SingleVariantStorage<>() {
        @Override
        protected ItemVariant getBlankVariant() {
            return ItemVariant.blank();
        }

        @Override
        protected long getCapacity(ItemVariant variant) {
            return 1;
        }
    };
    int aroma;

    public int getAroma() {
        return aroma;
    }

    public Aroma getAromaFromId(){
        return Registries.AROMAS.getHolder(getAroma()).get().value();
    }

    public void setAroma(int aroma) {
        this.aroma = aroma;
    }

    public IncenseBlockEntity(BlockPos pPos, BlockState pState) {
        super(BlockEntityRegister.INCENSE.get(), pPos, pState);
    }

    public SingleVariantStorage<ItemVariant> getInventory() {
        return inventory;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        inventory.writeNbt(pTag);
        pTag.putInt("aroma", aroma);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        inventory.variant = ItemVariant.fromNbt(pTag.getCompound("variant"));
        inventory.amount = pTag.getLong("amount");
        aroma = pTag.getInt("aroma");
        super.load(pTag);
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState, IncenseBlockEntity entity) {
        if (pLevel.isClientSide) {
            // On client
            if (pState.getValue(IncenseBlock.LIT) && pLevel.random.nextFloat() < 0.025f) {

                pLevel.addParticle(ParticleRegister.INCENSE_PARTICLE.get(),
                        pPos.getX() + 0.5f,
                        pPos.getY() + 0.45f,
                        pPos.getZ() + 0.5f,
                        0,
                        0.01f,
                        0
                );
            }
        }else {
            if(!this.getBlockState().getValue(IncenseBlock.LIT)){
                return;
            }

            if(getAroma() > 0){
                getAromaFromId().aromaTick(pLevel,pPos,pState,entity);
            }
        }
    }


    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithFullMetadata();
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        // Will get tag from #getUpdateTag
        return ClientboundBlockEntityDataPacket.create(this, BlockEntity::saveWithFullMetadata);
    }

}
