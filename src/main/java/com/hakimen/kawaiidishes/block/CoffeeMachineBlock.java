package com.hakimen.kawaiidishes.block;

import com.hakimen.kawaiidishes.block_entities.CoffeeMachineBlockEntity;
import com.hakimen.kawaiidishes.containers.CoffeeMachineContainer;
import com.hakimen.kawaiidishes.registry.ContainerRegister;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.fabricmc.fabric.mixin.screenhandler.NamedScreenHandlerFactoryMixin;
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
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CoffeeMachineBlock extends DirectionalBlockWithEntity{
    public CoffeeMachineBlock() {
        super(Settings.copy(Blocks.DIRT)
                .strength(1.0F, 6.0F)
                .sounds(BlockSoundGroup.METAL)
                .requiresTool());

        setDefaultState( getStateManager().getDefaultState()
                .with(FACING, Direction.NORTH));

    }


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClient ? null
                : (level, pos, state, blockEntity) -> ((CoffeeMachineBlockEntity) blockEntity).tick(level,pos,state,(CoffeeMachineBlockEntity)blockEntity);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState pState, BlockView pLevel, BlockPos pPos, ShapeContext pContext) {

        VoxelShape box = Block.createCuboidShape(0,0,0,16,16,16);

        switch (pState.get(FACING)){
            case SOUTH -> box = Block.createCuboidShape(2,0,0,14,16,15);
            case NORTH -> box = Block.createCuboidShape(2,0,1,14,16,16);
            case EAST -> box = Block.createCuboidShape(0,0,2,15,16,14);
            case WEST -> box = Block.createCuboidShape(1,0,2,16,16,14);
        }
        return box;
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
            if (blockentity instanceof CoffeeMachineBlockEntity coffeeMachine) {
                for (int i = 0; i < coffeeMachine.getInventory().size(); i++) {
                    dropStack(pLevel,pPos,coffeeMachine.getInventory().getStack(i));
                }
            }
            super.onStateReplaced(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    @Override
    public ActionResult onUse(BlockState pState, World pLevel, BlockPos pPos, PlayerEntity pPlayer, Hand pHand, BlockHitResult pHit) {
        if (!pLevel.isClient()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof CoffeeMachineBlockEntity coffeeMachineBlockEntity) {

                pPlayer.openHandledScreen(coffeeMachineBlockEntity);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return ActionResult.SUCCESS;
    }
    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pPos, BlockState pState) {
        return new CoffeeMachineBlockEntity(pPos,pState);
    }
}
