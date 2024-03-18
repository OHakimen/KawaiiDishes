package com.hakimen.kawaiidishes.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CoffeeMugBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public CoffeeMugBlock(Properties props) {
        super(props);

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
        return defaultBlockState().setValue( FACING, placement.getHorizontalDirection() );
    }


    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        VoxelShape shape = Block.box(5,0,5,11,9,11);
        return shape;
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos.below()).isSolid();

    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pOtherState, LevelAccessor pLevel, BlockPos pPrimaryPos, BlockPos pSecondaryPos) {
        return pDirection == Direction.DOWN && !pState.canSurvive(pLevel, pPrimaryPos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(pState, pDirection, pOtherState, pLevel, pPrimaryPos, pSecondaryPos);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pInteractionHand, BlockHitResult pBlockHitResult) {

        if(pPlayer.isCrouching()){
            pPlayer.addItem(getCloneItemStack(pState, pBlockHitResult, pLevel, pPos, pPlayer));
            pLevel.setBlockAndUpdate(pPos,Blocks.AIR.defaultBlockState());
            pPlayer.playSound(SoundEvents.ITEM_PICKUP, 1f, 0.75f + pLevel.getRandom().nextFloat()/2f);
            return InteractionResult.SUCCESS;
        }

        return super.use(pState, pLevel, pPos, pPlayer, pInteractionHand, pBlockHitResult);
    }
}
