package com.hakimen.kawaiidishes.block_entities;

import com.hakimen.kawaiidishes.block.IncenseBlock;
import com.hakimen.kawaiidishes.custom.Registries;
import com.hakimen.kawaiidishes.custom.type.Aroma;
import com.hakimen.kawaiidishes.datagen.ItemTagDataGen;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import com.hakimen.kawaiidishes.registry.ParticleRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class IncenseBlockEntity extends BlockEntity {

    private final ItemStackHandler inventory = createHandler();
    public IncenseBlockEntity(BlockPos pPos, BlockState pState) {
        super(BlockEntityRegister.INCENSE.get(), pPos, pState);
    }

    int aroma;

    public int getAroma() {
        return aroma;
    }

    public Aroma getAromaFromId(){
        return Registries.AROMA_REGISTRY.getHolder(getAroma()).get().value();
    }

    public void setAroma(int aroma) {
        this.aroma = aroma;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.merge(this.inventory.serializeNBT());
        pTag.putInt("Aroma", aroma);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        inventory.deserializeNBT(pTag);
        aroma = pTag.getInt("Aroma");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState, IncenseBlockEntity entity) {
        if (pLevel.isClientSide) {
            // On client
            if (pState.getValue(IncenseBlock.LIT) && pLevel.random.nextFloat() < 0.025f) {

                pLevel.addParticle(ParticleRegister.INCENSE_PARTICLES.get(),
                        pPos.getX() + 0.5f,
                        pPos.getY() + 0.45f,
                        pPos.getZ() + 0.5f,
                        0,
                        0.01f,
                        0
                );
            }
        }else {
            // On server
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

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return true;
            }


            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!isItemValid(slot, stack)) {
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

}
