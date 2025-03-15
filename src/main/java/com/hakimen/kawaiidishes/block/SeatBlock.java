package com.hakimen.kawaiidishes.block;


import com.hakimen.kawaiidishes.block_entities.SeatBlockEntity;
import com.hakimen.kawaiidishes.entity.SeatEntity;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SeatBlock extends BlockWithEntity {
    public SeatBlock(Settings properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pPos, BlockState pState) {
        return new SeatBlockEntity(pPos,pState);
    }

    @Override
    public void onPlaced(World pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pEntity, ItemStack pStack) {
        pLevel.getBlockEntity(pPos, BlockEntityRegister.SEAT.get()).ifPresent(seat -> seat.fromItem(pStack));
        super.onPlaced(pLevel, pPos, pState, pEntity, pStack);
    }

    @Override
    public void onBreak(World level, BlockPos blockPos, BlockState blockState, PlayerEntity player) {
        if(!player.isCreative()){
            ItemStack stack = ItemRegister.SEAT.get().getDefaultStack();
            SeatBlockEntity blockEntity = level.getBlockEntity(blockPos,BlockEntityRegister.SEAT.get()).get();
            blockEntity.saveToItem(blockEntity,stack);
            dropStack(level,blockPos,stack);
        }
        super.onBreak(level, blockPos, blockState, player);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState p_60555_, BlockView p_60556_, BlockPos p_60557_, ShapeContext p_60558_) {
        VoxelShape shape = Block.createCuboidShape(3,0,3,13,2,13);
        shape = VoxelShapes.combineAndSimplify(shape, Block.createCuboidShape(6,2,6,10,9,10), BooleanBiFunction.OR);
        shape = VoxelShapes.combineAndSimplify(shape, Block.createCuboidShape(1,9,1,15,11,15), BooleanBiFunction.OR);
        return shape;
    }

    @Override
    public ItemStack getPickStack(BlockView blockGetter, BlockPos blockPos, BlockState blockState) {
        ItemStack stack = ItemRegister.SEAT.get().getDefaultStack();
        blockGetter.getBlockEntity(blockPos,BlockEntityRegister.SEAT.get()).get().saveToItem(blockGetter.getBlockEntity(blockPos,BlockEntityRegister.SEAT.get()).get(),stack);
        return stack;
    }

    @Override
    public ActionResult onUse(BlockState pState, World pLevel, BlockPos pPos, PlayerEntity pPlayer, Hand pHand, BlockHitResult pHitResult) {
        return SeatEntity.sitDown(pPlayer, pLevel, pPos);
    }
}
