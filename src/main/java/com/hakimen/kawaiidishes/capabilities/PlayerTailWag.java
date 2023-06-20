package com.hakimen.kawaiidishes.capabilities;

import net.minecraft.nbt.CompoundTag;

public class PlayerTailWag {
    private boolean isWagging;

    public boolean isWagging() {
        return isWagging;
    }

    public void setWagging(boolean wagging) {
        isWagging = wagging;
    }

    public void saveNBT(CompoundTag tag){
        tag.putBoolean("wagging", isWagging);
    }

    public void loadNBT(CompoundTag tag){
        isWagging = tag.getBoolean("wagging");
    }

    public void copyFrom(PlayerTailWag source) {
        this.isWagging = source.isWagging;
    }
}
