package com.hakimen.kawaiidishes.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class CoffeeMugBlock extends Block {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public CoffeeMugBlock(Settings props) {
        super(props);

        setDefaultState( getStateManager().getDefaultState()
                .with(FACING, Direction.NORTH));
    }
    protected void appendProperties(StateManager.Builder<Block, BlockState> properties )
    {
        properties.add( FACING );
    }

    @Override
    @Deprecated
    public BlockState mirror( BlockState state, BlockMirror mirrorIn )
    {
        return state.rotate( mirrorIn.getRotation( state.get( FACING ) ) );
    }

    @Override
    @Deprecated
    public BlockState rotate( BlockState state, BlockRotation rot )
    {
        return state.with( FACING, rot.rotate( state.get( FACING ) ) );
    }

    @Override
    public BlockState getPlacementState( ItemPlacementContext placement )
    {
        return getDefaultState().with( FACING, placement.getHorizontalPlayerFacing() );
    }


    @Override
    public VoxelShape getOutlineShape(BlockState pState, BlockView pLevel, BlockPos pPos, ShapeContext pContext) {
        VoxelShape shape = Block.createCuboidShape(5,0,5,11,9,11);
        return shape;
    }

    @Override
    public boolean canPlaceAt(BlockState pState, WorldView pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos.down()).isSolid();

    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState pState, Direction pDirection, BlockState pOtherState, WorldAccess pLevel, BlockPos pPrimaryPos, BlockPos pSecondaryPos) {
        return pDirection == Direction.DOWN && !pState.canPlaceAt(pLevel, pPrimaryPos)
                ? Blocks.AIR.getDefaultState()
                : super.getStateForNeighborUpdate(pState, pDirection, pOtherState, pLevel, pPrimaryPos, pSecondaryPos);
    }

    @Override
    public ActionResult onUse(BlockState pState, World pLevel, BlockPos pPos, PlayerEntity pPlayer, Hand pInteractionHand, BlockHitResult pBlockHitResult) {

        if(pPlayer.isInSneakingPose()){
            pPlayer.giveItemStack(getPickStack(pLevel, pPos, pState));
            pLevel.setBlockState(pPos,Blocks.AIR.getDefaultState());
            pPlayer.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 1f, 0.75f + pLevel.getRandom().nextFloat()/2f);
            return ActionResult.SUCCESS;
        }

        return super.onUse(pState, pLevel, pPos, pPlayer, pInteractionHand, pBlockHitResult);
    }
}
