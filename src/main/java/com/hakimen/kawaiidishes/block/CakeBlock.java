package com.hakimen.kawaiidishes.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CakeBlock extends Block {

    public static final IntegerProperty SLICES = IntegerProperty.create("slices", 1,4);

    public CakeBlock(Properties props) {
        super(props
                .isSuffocating((pState, pBlockGetter,pPos) -> false)
        );
        this.registerDefaultState(this.stateDefinition.any().setValue(SLICES, 4));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> properties) {
        properties.add( SLICES );
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDir, BlockState pState1, LevelAccessor pLevelAccessor, BlockPos pPos, BlockPos pPos1) {

        if(pDir == Direction.DOWN && !pState.canSurvive(pLevelAccessor, pPos) && pState.getValue(SLICES) == 4){
            popResource((Level) pLevelAccessor,pPos,new ItemStack(this));
        }

        return pDir == Direction.DOWN && !pState.canSurvive(pLevelAccessor, pPos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(pState, pDir, pState1, pLevelAccessor, pPos, pPos1);
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevelReader, BlockPos pPos) {
        return pLevelReader.getBlockState(pPos.below()).isSolid() && !(pLevelReader.getBlockState(pPos.below()).getBlock() instanceof CakeBlock);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pBlockGetter, BlockPos pPos, CollisionContext pCollisionContext) {
        switch (pState.getValue(SLICES)){
            case 1 -> {
                return Block.box(8,0,8,15,4,15);
            }
            case 2 -> {
                return Block.box(8,0,1,15,4,15);
            }
            case 3 -> {
                return Shapes.join(
                        Block.box(1,0,8,15,4,15),
                        Block.box(8,0,1,15,4,8),
                        BooleanOp.OR);
            }
            case 4 -> {
                return Block.box(1,0,1,15,4,15);
            }
        }

        return super.getShape(pState,pBlockGetter,pPos,pCollisionContext);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pBlockHitResult) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        if (pLevel.isClientSide) {
            if (eat(pLevel, pPos, pState, pPlayer).consumesAction()) {
                return InteractionResult.SUCCESS;
            }

            if (itemstack.isEmpty()) {
                return InteractionResult.CONSUME;
            }
        }

        return eat(pLevel, pPos, pState, pPlayer);
    }

    @Override
    public void destroy(LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
        if(pState.getValue(SLICES) == 4){
            popResource((Level) pLevel,pPos,new ItemStack(this));
        }
        super.destroy(pLevel, pPos, pState);
    }


    protected static InteractionResult eat(LevelAccessor pLevelAccessor, BlockPos pPos, BlockState pState, Player pPlayer) {
        if (!pPlayer.canEat(false)) {
            return InteractionResult.PASS;
        } else {
            pPlayer.awardStat(Stats.EAT_CAKE_SLICE);
            pPlayer.getFoodData().eat(3, 1f);
            int i = pState.getValue(SLICES);
            pLevelAccessor.gameEvent(pPlayer, GameEvent.EAT, pPos);
            pLevelAccessor.playSound(pPlayer, pPos, SoundEvents.GENERIC_EAT, SoundSource.BLOCKS, 1f,1f);
            if (i > 1) {
                pLevelAccessor.setBlock(pPos, pState.setValue(SLICES, i - 1), UPDATE_ALL);
            } else {
                pLevelAccessor.removeBlock(pPos, false);
                pLevelAccessor.gameEvent(pPlayer, GameEvent.BLOCK_DESTROY, pPos);
            }

            return InteractionResult.SUCCESS;
        }
    }
}
