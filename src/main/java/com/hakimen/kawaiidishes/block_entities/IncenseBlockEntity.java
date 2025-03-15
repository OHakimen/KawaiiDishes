package com.hakimen.kawaiidishes.block_entities;

import com.hakimen.kawaiidishes.block.IncenseBlock;
import com.hakimen.kawaiidishes.custom.Registries;
import com.hakimen.kawaiidishes.custom.types.Aroma;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import com.hakimen.kawaiidishes.registry.ParticleRegister;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
        return Registries.AROMAS.getEntry(getAroma()).get().value();
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
    protected void writeNbt(NbtCompound pTag) {
        inventory.writeNbt(pTag);
        pTag.putInt("aroma", aroma);
        super.writeNbt(pTag);
    }

    @Override
    public void readNbt(NbtCompound pTag) {
        inventory.variant = ItemVariant.fromNbt(pTag.getCompound("variant"));
        inventory.amount = pTag.getLong("amount");
        aroma = pTag.getInt("aroma");
        super.readNbt(pTag);
    }

    public void tick(World pLevel, BlockPos pPos, BlockState pState, IncenseBlockEntity entity) {
        if (pLevel.isClient) {
            // On client
            if (pState.get(IncenseBlock.LIT) && pLevel.random.nextFloat() < 0.025f) {

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
            if(!this.getCachedState().get(IncenseBlock.LIT)){
                return;
            }

            if(getAroma() > 0){
                getAromaFromId().aromaTick(pLevel,pPos,pState,entity);
            }
        }
    }


    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbtWithIdentifyingData();
    }

    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        // Will get tag from #getUpdateTag
        return BlockEntityUpdateS2CPacket.create(this, BlockEntity::createNbtWithIdentifyingData);
    }

}
