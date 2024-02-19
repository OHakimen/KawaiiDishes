package com.hakimen.kawaiidishes.block;


import com.hakimen.kawaiidishes.block_entities.SeatBlockEntity;
import com.hakimen.kawaiidishes.entity.SeatEntity;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class SeatBlock extends BlockWithEntity {
    public SeatBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SeatBlockEntity(pPos,pState);
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pEntity, ItemStack pStack) {
        pLevel.getBlockEntity(pPos, BlockEntityRegister.SEAT.get()).ifPresent(seat -> seat.fromItem(pStack));
        super.setPlacedBy(pLevel, pPos, pState, pEntity, pStack);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {

        if(willHarvest){
            ItemStack stack = ItemRegister.SEAT.get().getDefaultInstance();
            SeatBlockEntity blockEntity = level.getBlockEntity(pos,BlockEntityRegister.SEAT.get()).get();
            blockEntity.saveToItem(blockEntity,stack);
            popResource(level,pos,stack);
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        VoxelShape shape = Block.box(3,0,3,13,2,13);
        shape = Shapes.join(shape, Block.box(6,2,6,10,9,10), BooleanOp.OR);
        shape = Shapes.join(shape, Block.box(1,9,1,15,11,15), BooleanOp.OR);
        return shape;
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        ItemStack stack = ItemRegister.SEAT.get().getDefaultInstance();
        level.getBlockEntity(pos,BlockEntityRegister.SEAT.get()).get().saveToItem(level.getBlockEntity(pos,BlockEntityRegister.SEAT.get()).get(),stack);
        return stack;
    }


    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        return SeatEntity.sitDown(pPlayer, pLevel, pPos);
    }
}
