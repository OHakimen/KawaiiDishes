package com.hakimen.kawaiidishes.block;

import com.hakimen.kawaiidishes.block_entities.DisplayCaseBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DisplayCaseBlock extends DirectionalBlockWithEntity {

    public DisplayCaseBlock() {
        super(Settings.copy(Blocks.GLASS)
                .strength(1.0F, 6.0F)
                .nonOpaque()
                .suffocates((p_61036_, p_61037_, p_61038_) -> false)
                .blockVision((p_61036_, p_61037_, p_61038_) -> false)
                .requiresTool());

        setDefaultState(getStateManager().getDefaultState()
                .with(FACING, Direction.NORTH));

    }

    @Override
    public boolean isSideInvisible(BlockState p_53972_, BlockState p_53973_, Direction p_53974_) {
        return p_53973_.isOf(this) || super.isSideInvisible(p_53972_, p_53973_, p_53974_);
    }

    @Override
    public VoxelShape getCameraCollisionShape(BlockState p_60479_, BlockView p_60480_, BlockPos p_60481_, ShapeContext p_60482_) {
        return VoxelShapes.empty();
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> properties) {
        properties.add(FACING);
    }

    @Override
    @Deprecated
    public BlockState mirror(BlockState state, BlockMirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.get(FACING)));
    }

    @Override
    @Deprecated
    public BlockState rotate(BlockState state, BlockRotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext placement) {
        return getDefaultState().with(FACING, placement.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public void onStateReplaced(BlockState pState, World pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.isOf(pNewState.getBlock())) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if (blockentity instanceof DisplayCaseBlockEntity coffeeMachine) {
                for (int i = 0; i < coffeeMachine.getInventory().size(); i++) {
                    dropStack(pLevel, pPos, coffeeMachine.getInventory().getStack(i));
                }
            }
            super.onStateReplaced(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClient ? null
                : (level, pos, state, blockEntity) -> ((DisplayCaseBlockEntity) blockEntity).tick(level, pos, state, (DisplayCaseBlockEntity) blockEntity);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pPos, BlockState pState) {
        return new DisplayCaseBlockEntity(pPos, pState);
    }

    @Override
    public ActionResult onUse(BlockState pState, World pLevel, BlockPos pPos, PlayerEntity pPlayer, Hand pHand, BlockHitResult pHit) {
        if (!pLevel.isClient() && pHit.getSide().equals(pState.get(DirectionalBlockWithEntity.FACING).getOpposite())) {

            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if (entity instanceof DisplayCaseBlockEntity) {
                pPlayer.openHandledScreen((DisplayCaseBlockEntity) entity);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return ActionResult.SUCCESS;
    }
}
