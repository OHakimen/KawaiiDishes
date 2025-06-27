package com.hakimen.kawaiidishes.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

public class CakeBlock extends Block {

    public static final IntProperty SLICES = IntProperty.of("slices", 1,4);

    public CakeBlock(Settings props) {
        super(props
                .suffocates((pState, pBlockGetter,pPos) -> false)
        );
        this.setDefaultState(this.stateManager.getDefaultState().with(SLICES, 4));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> properties) {
        properties.add( SLICES );
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState pState, Direction pDir, BlockState pState1, WorldAccess pLevelAccessor, BlockPos pPos, BlockPos pPos1) {

        if(pDir == Direction.DOWN && !pState.canPlaceAt(pLevelAccessor, pPos) && pState.get(SLICES) == 4){
            dropStack((World) pLevelAccessor,pPos,new ItemStack(this));
        }

        return pDir == Direction.DOWN && !pState.canPlaceAt(pLevelAccessor, pPos)
                ? Blocks.AIR.getDefaultState()
                : super.getStateForNeighborUpdate(pState, pDir, pState1, pLevelAccessor, pPos, pPos1);
    }

    @Override
    public boolean canPlaceAt(BlockState pState, WorldView pLevelReader, BlockPos pPos) {
        return pLevelReader.getBlockState(pPos.down()).isSolid() && !(pLevelReader.getBlockState(pPos.down()).getBlock() instanceof CakeBlock);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState pState, BlockView pBlockGetter, BlockPos pPos, ShapeContext pCollisionContext) {
        switch (pState.get(SLICES)){
            case 1 -> {
                return Block.createCuboidShape(8,0,8,15,4,15);
            }
            case 2 -> {
                return Block.createCuboidShape(8,0,1,15,4,15);
            }
            case 3 -> {
                return VoxelShapes.combineAndSimplify(
                        Block.createCuboidShape(1,0,8,15,4,15),
                        Block.createCuboidShape(8,0,1,15,4,8),
                        BooleanBiFunction.OR);
            }
            case 4 -> {
                return Block.createCuboidShape(1,0,1,15,4,15);
            }
        }

        return super.getOutlineShape(pState,pBlockGetter,pPos,pCollisionContext);
    }

    @Override
    public ActionResult onUse(BlockState pState, World pLevel, BlockPos pPos, PlayerEntity pPlayer, Hand pHand, BlockHitResult pBlockHitResult) {
        ItemStack itemstack = pPlayer.getStackInHand(pHand);

        if (pLevel.isClient) {
            if (eat(pLevel, pPos, pState, pPlayer).isAccepted()) {
                return ActionResult.SUCCESS;
            }

            if (itemstack.isEmpty()) {
                return ActionResult.CONSUME;
            }
        }

        return eat(pLevel, pPos, pState, pPlayer);
    }

    @Override
    public void onBroken(WorldAccess pLevel, BlockPos pPos, BlockState pState) {
        if(pState.get(SLICES) == 4){
            dropStack((World) pLevel,pPos,new ItemStack(this));
        }
        super.onBroken(pLevel, pPos, pState);
    }


    protected static ActionResult eat(WorldAccess pLevelAccessor, BlockPos pPos, BlockState pState, PlayerEntity pPlayer) {
        if (!pPlayer.canConsume(false)) {
            return ActionResult.PASS;
        } else {
            pPlayer.incrementStat(Stats.EAT_CAKE_SLICE);
            pPlayer.getHungerManager().add(3, 1f);
            int i = pState.get(SLICES);
            pLevelAccessor.emitGameEvent(pPlayer, GameEvent.EAT, pPos);
            pLevelAccessor.playSound(pPlayer, pPos, SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.BLOCKS, 1f,1f);
            if (i > 1) {
                pLevelAccessor.setBlockState(pPos, pState.with(SLICES, i - 1), NOTIFY_ALL);
            } else {
                pLevelAccessor.removeBlock(pPos, false);
                pLevelAccessor.emitGameEvent(pPlayer, GameEvent.BLOCK_DESTROY, pPos);
            }

            return ActionResult.SUCCESS;
        }
    }
}
