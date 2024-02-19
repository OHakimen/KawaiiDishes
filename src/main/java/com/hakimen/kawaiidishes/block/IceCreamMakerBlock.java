package com.hakimen.kawaiidishes.block;

import com.hakimen.kawaiidishes.block_entities.IceCreamMakerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class IceCreamMakerBlock extends DirectionalBlockWithEntity {

    public IceCreamMakerBlock() {
        super(Properties.ofFullCopy(Blocks.DIRT)
                .strength(1.0F, 6.0F)
                .sound(SoundType.METAL)
                .noOcclusion()
                .isSuffocating((p_61036_, p_61037_, p_61038_) -> false)
                .isViewBlocking((p_61036_, p_61037_, p_61038_) -> false)
                .requiresCorrectToolForDrops()
        );

        registerDefaultState( getStateDefinition().any()
                .setValue(FACING, Direction.NORTH));

    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> properties )
    {
        properties.add( FACING );
    }

    @Override
    @Deprecated
    public BlockState mirror( BlockState state, Mirror mirrorIn )
    {
        return state.rotate( mirrorIn.getRotation( state.getValue( FACING ) ) );
    }

    @Override
    @Deprecated
    public BlockState rotate( BlockState state, Rotation rot )
    {
        return state.setValue( FACING, rot.rotate( state.getValue( FACING ) ) );
    }


    @javax.annotation.Nullable
    @Override
    public BlockState getStateForPlacement( BlockPlaceContext placement )
    {
        return defaultBlockState().setValue( FACING, placement.getHorizontalDirection().getOpposite() );
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if (blockentity instanceof IceCreamMakerBlockEntity iceCreamMachine) {
                for (int i = 0; i < iceCreamMachine.getInventory().getSlots(); i++) {
                    popResource(pLevel,pPos,iceCreamMachine.getInventory().getStackInSlot(i));
                }
            }
            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pBlockGetter, BlockPos pPos, CollisionContext pCollisionContext) {
        VoxelShape box = Block.box(0,0,0,16,2,16);

        switch (pState.getValue(FACING)){
            case SOUTH -> {
                box = Shapes.join(box,  Block.box(0,0,0,16,16,9), BooleanOp.OR);
            }
            case NORTH -> {
                box = Shapes.join(box,  Block.box(0,0,7,16,16,16), BooleanOp.OR);
            }
            case EAST -> {
                box = Shapes.join(box,  Block.box(0,0,0,9,16,16), BooleanOp.OR);
            }
            case WEST -> {
                box = Shapes.join(box,  Block.box(7,0,0,16,16,16), BooleanOp.OR);
            }
        }

        return box;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide ? null
                : (level, pos, state, blockEntity) -> ((IceCreamMakerBlockEntity) blockEntity).tick(level,pos,state,(IceCreamMakerBlockEntity)blockEntity);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new IceCreamMakerBlockEntity(pPos,pState);
    }


    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof IceCreamMakerBlockEntity) {
                pPlayer.openMenu((IceCreamMakerBlockEntity)entity, pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return InteractionResult.SUCCESS;
    }
}
