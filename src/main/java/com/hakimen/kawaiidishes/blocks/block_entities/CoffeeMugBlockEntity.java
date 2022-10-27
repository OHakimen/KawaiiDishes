package com.hakimen.kawaiidishes.blocks.block_entities;

import com.hakimen.kawaiidishes.items.Drink;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CoffeeMugBlockEntity extends BlockEntity {

    public CompoundTag mainEffect = new CompoundTag();
    public CompoundTag secondaryEffect = new CompoundTag();
    public CoffeeMugBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(BlockEntityRegister.coffeeMug.get(), pWorldPosition, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("mainEffect",mainEffect);
        pTag.put("secondaryEffect",secondaryEffect);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        mainEffect = pTag.getCompound("mainEffect") != null ? pTag.getCompound("mainEffect") : new CompoundTag() ;
        secondaryEffect = pTag.getCompound("secondaryEffect") != null ? pTag.getCompound("secondaryEffect") : new CompoundTag() ;
        super.load(pTag);
    }

}
