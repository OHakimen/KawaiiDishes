package com.hakimen.kawaiidishes.block;

import com.hakimen.kawaiidishes.block_entities.IceCreamMakerBlockEntity;
import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.registry.AromaRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import static com.hakimen.kawaiidishes.datagen.ItemTagDataGen.CAN_EXTINGUISH_INCENSE;
import static com.hakimen.kawaiidishes.datagen.ItemTagDataGen.CAN_IGNITE_INCENSE;

public class IncenseBlock extends DirectionalBlockWithEntity{

    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    public IncenseBlock() {
        super(Properties.ofFullCopy(Blocks.GLASS)
                .isViewBlocking((blockState, blockGetter, blockPos) -> false)
                .isSuffocating((blockState, blockGetter, blockPos) -> false)
                .lightLevel(value -> value.getValue(LIT) ? 7 : 0)
        );


        registerDefaultState( getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(LIT, false));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> properties )
    {
        properties.add( FACING ).add(LIT);
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
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new IncenseBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return (level, pos, state, blockEntity) -> ((IncenseBlockEntity) blockEntity).tick(level,pos,state,(IncenseBlockEntity)blockEntity);
    }


    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return Block.box(5,0,5,11,6,11);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pBlockHitResult) {

        ItemStack holdStack = pPlayer.getItemInHand(pHand);

        IncenseBlockEntity entity = (IncenseBlockEntity) pLevel.getBlockEntity(pPos);

        if(holdStack.is(CAN_EXTINGUISH_INCENSE) || holdStack.is(CAN_IGNITE_INCENSE)){
            BlockState state = pState;
            if(holdStack.is(CAN_EXTINGUISH_INCENSE) && pState.getValue(LIT)){
                state = pState.setValue(LIT, false);
                pLevel.playSound(pPlayer, pPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1f,1f);
            }else if(holdStack.is(CAN_IGNITE_INCENSE) && !pState.getValue(LIT)){
                state = pState.setValue(LIT, true);
                pLevel.playSound(pPlayer, pPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1f,1f);
            }

            if(holdStack.isDamageableItem()){
               if(holdStack.is(CAN_IGNITE_INCENSE) && !pState.getValue(LIT)){
                   holdStack.setDamageValue(holdStack.getDamageValue() + 1);
                   if(holdStack.getDamageValue() == holdStack.getMaxDamage()){
                       pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_BREAK, SoundSource.BLOCKS, 1f,1f);
                       holdStack.shrink(1);
                   }
               }else if(holdStack.is(CAN_EXTINGUISH_INCENSE) && pState.getValue(LIT)){
                   holdStack.setDamageValue(holdStack.getDamageValue() + 1);
                   if(holdStack.getDamageValue() == holdStack.getMaxDamage()){
                       pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_BREAK, SoundSource.BLOCKS, 1f,1f);
                       holdStack.shrink(1);
                   }
               }
            }else if(!holdStack.getCraftingRemainingItem().equals(ItemStack.EMPTY)){
                if(holdStack.is(CAN_IGNITE_INCENSE) && !pState.getValue(LIT)) {
                    var stack = holdStack.getCraftingRemainingItem();
                    if(stack.getItem() != Items.BUCKET){
                        holdStack.shrink(1);
                        pPlayer.addItem(stack);
                    }
                }
                else if(holdStack.is(CAN_EXTINGUISH_INCENSE) && pState.getValue(LIT)){
                    var stack = holdStack.getCraftingRemainingItem();
                    if(stack.getItem() != Items.BUCKET){
                        holdStack.shrink(1);
                        pPlayer.addItem(stack);
                    }
                }
            }else{
                if(holdStack.is(CAN_IGNITE_INCENSE) && !pState.getValue(LIT)){
                    holdStack.shrink(1);
                }else if(holdStack.is(CAN_EXTINGUISH_INCENSE) && pState.getValue(LIT)){
                    holdStack.shrink(1);
                }
            }


            pLevel.setBlock(pPos,state, Block.UPDATE_ALL);
            return InteractionResult.SUCCESS;

        } else if(!pState.getValue(LIT) && AromaRegister.isValidStack(holdStack) && entity.getInventory().getStackInSlot(0).isEmpty()){
            entity.getInventory().insertItem(0, holdStack.copy(), false);
            entity.setAroma(AromaRegister.getAromaId(holdStack));
            entity.setChanged();
            holdStack.shrink(1);
            pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f ,0.75f + (pLevel.getRandom().nextFloat() / 2f) * pLevel.getRandom().nextInt(-1,2));
            return InteractionResult.SUCCESS;

        } else if(!pState.getValue(LIT) && !entity.getInventory().getStackInSlot(0).isEmpty() && holdStack.isEmpty()){
            ItemStack stack = entity.getInventory().extractItem(0,1,false);
            entity.setAroma(0);
            if(pPlayer.getInventory().hasAnyMatching(item -> item.is(stack.getItem()) && item.getCount() < item.getMaxStackSize()) || pPlayer.getInventory().getFreeSlot() != -1){
                pPlayer.addItem(stack);
            }else if (pPlayer.getInventory().getFreeSlot() == -1) {
                popResource(pLevel, pPos, stack);
            }
            pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f ,0.75f + (pLevel.getRandom().nextFloat() / 2f) * pLevel.getRandom().nextInt(-1,2));
            entity.setChanged();
            return InteractionResult.SUCCESS;
        }


        return InteractionResult.FAIL;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        IncenseBlockEntity entity = (IncenseBlockEntity) level.getBlockEntity(pos);

        popResource(level,pos,entity.getInventory().getStackInSlot(0));

        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }
}
