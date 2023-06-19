package com.hakimen.kawaiidishes.blocks;

import com.hakimen.kawaiidishes.blocks.block_entities.PlaceableFoodBlockEntity;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class MugWithCoffeeBlock extends MugBlock implements EntityBlock {
    public MugWithCoffeeBlock(){
        super(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1,1));
    }


    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(pPlayer.isCrouching()){
            var stack = this.asItem().getDefaultInstance();
            stack.getOrCreateTag();
            if(pLevel.getBlockEntity(pPos) instanceof PlaceableFoodBlockEntity entity){
                if(!entity.mainEffect.equals(new CompoundTag())){
                    stack.getOrCreateTag().put("mainEffect",entity.mainEffect);
                }
                if(!entity.secondaryEffect.equals(new CompoundTag())){
                    stack.getOrCreateTag().put("secondaryEffect",entity.secondaryEffect);
                }
            }
            pLevel.removeBlock(pPos,false);
            pPlayer.addItem(stack);
            return InteractionResult.SUCCESS;
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new PlaceableFoodBlockEntity(pPos,pState);
    }


    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        pLevel.getBlockEntity(pPos, BlockEntityRegister.placeableFood.get()).ifPresent((a) -> {
            a.load(pStack.getOrCreateTag());
        });
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        var stack = this.asItem().getDefaultInstance();
        stack.getOrCreateTag();
        if(level.getBlockEntity(pos) instanceof PlaceableFoodBlockEntity entity){
            if(!entity.mainEffect.equals(new CompoundTag())){
                stack.getOrCreateTag().put("mainEffect",entity.mainEffect);
            }
            if(!entity.secondaryEffect.equals(new CompoundTag())){
                stack.getOrCreateTag().put("secondaryEffect",entity.secondaryEffect);
            }
        }
        level.addFreshEntity(new ItemEntity(level,pos.getX(),pos.getY(),pos.getZ(),stack));
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }
}
