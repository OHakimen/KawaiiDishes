package com.hakimen.kawaiidishes.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class MugWithCoffee extends MugBlock{
    public MugWithCoffee(){
        super(BlockBehaviour.Properties.of(Material.STONE).strength(1,1).dynamicShape().randomTicks());
    }
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }
    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        super.randomTick(pState, pLevel, pPos, pRandom);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(pPlayer.isCrouching()){
            pLevel.removeBlock(pPos,false);
            pPlayer.addItem(this.asItem().getDefaultInstance());
            return InteractionResult.SUCCESS;
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
}
