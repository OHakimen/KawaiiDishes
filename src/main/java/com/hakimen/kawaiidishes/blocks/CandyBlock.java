package com.hakimen.kawaiidishes.blocks;

import com.hakimen.kawaiidishes.blocks.block_entities.IceCreamBlockEntity;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class CandyBlock extends Block {

    public CandyBlock(){
        super(Properties.of(Material.CAKE)
                .strength(.25f,0)
                .isSuffocating((p_61036_, p_61037_, p_61038_) -> false));
    }


    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(pPlayer.isCrouching()){
            var stack = this.asItem().getDefaultInstance();
            stack.getOrCreateTag();
            pLevel.removeBlock(pPos,false);
            pPlayer.addItem(stack);
            return InteractionResult.SUCCESS;
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        var stack = this.asItem().getDefaultInstance();
        level.addFreshEntity(new ItemEntity(level,pos.getX(),pos.getY(),pos.getZ(),stack));
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

}
