package com.hakimen.kawaiidishes.block;

import com.hakimen.kawaiidishes.block_entities.BlenderBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
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

public class BlenderBlock extends DirectionalBlockWithEntity {

    public BlenderBlock() {
        super(Properties.copy(Blocks.DIRT)
                .strength(1.0F, 6.0F)
                .sound(SoundType.METAL)
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

    @Override
    public BlockState getStateForPlacement( BlockPlaceContext placement )
    {
        return defaultBlockState().setValue( FACING, placement.getHorizontalDirection().getOpposite() );
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if (blockentity instanceof BlenderBlockEntity blender) {
                for (int i = 0; i < blender.getInventory().getContainerSize(); i++) {
                    popResource(pLevel,pPos,blender.getInventory().getItem(i));
                }
            }
            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pBlockGetter, BlockPos pPos, CollisionContext pCollisionContext) {
        VoxelShape box = Block.box(4,0,4,12,6,12);
        box = Shapes.join(box, Block.box(4.25,6,4.25,11.75,16,11.75), BooleanOp.OR);
        return box;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide ? null
                : (level, pos, state, blockEntity) -> ((BlenderBlockEntity) blockEntity).tick(level,pos,state,(BlenderBlockEntity)blockEntity);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new BlenderBlockEntity(pPos,pState);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof BlenderBlockEntity) {
                pPlayer.openMenu((BlenderBlockEntity)entity);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return InteractionResult.SUCCESS;
    }
}
