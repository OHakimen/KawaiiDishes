package com.hakimen.kawaiidishes.block;

import com.hakimen.kawaiidishes.block_entities.BlenderBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BlenderBlock extends DirectionalBlockWithEntity {

    public BlenderBlock() {
        super(Settings.copy(Blocks.DIRT)
                .strength(1.0F, 6.0F)
                .sounds(BlockSoundGroup.METAL)
                .requiresTool()
        );

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
        return getDefaultState().with( FACING, placement.getHorizontalPlayerFacing().getOpposite() );
    }

    @Override
    public void onStateReplaced(BlockState pState, World pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.isOf(pNewState.getBlock())) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if (blockentity instanceof BlenderBlockEntity blender) {
                for (int i = 0; i < blender.getInventory().size(); i++) {
                    dropStack(pLevel,pPos,blender.getInventory().getStack(i));
                }
            }
            super.onStateReplaced(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState pState, BlockView pBlockGetter, BlockPos pPos, ShapeContext pCollisionContext) {
        VoxelShape box = Block.createCuboidShape(4,0,4,12,6,12);
        box = VoxelShapes.combineAndSimplify(box, Block.createCuboidShape(4.25,6,4.25,11.75,16,11.75), BooleanBiFunction.OR);
        return box;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClient ? null
                : (level, pos, state, blockEntity) -> ((BlenderBlockEntity) blockEntity).tick(level,pos,state,(BlenderBlockEntity)blockEntity);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pPos, BlockState pState) {
        return new BlenderBlockEntity(pPos,pState);
    }

    @Override
    public ActionResult onUse(BlockState pState, World pLevel, BlockPos pPos, PlayerEntity pPlayer, Hand pHand, BlockHitResult pHit) {
        if (!pLevel.isClient()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof BlenderBlockEntity) {
                pPlayer.openHandledScreen((BlenderBlockEntity)entity);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return ActionResult.SUCCESS;
    }
}
