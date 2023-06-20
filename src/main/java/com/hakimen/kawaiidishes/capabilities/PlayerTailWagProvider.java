package com.hakimen.kawaiidishes.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerTailWagProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerTailWag> playerTailWag = CapabilityManager.get(new CapabilityToken<PlayerTailWag>() {});

    private PlayerTailWag tailWag = null;
    private final LazyOptional<PlayerTailWag> optional = LazyOptional.of(this::createPlayerTailWag);

    private PlayerTailWag createPlayerTailWag() {
        if(this.tailWag == null){
            this.tailWag = new PlayerTailWag();
        }
        return this.tailWag;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == playerTailWag){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerTailWag().saveNBT(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerTailWag().loadNBT(nbt);
    }
}
