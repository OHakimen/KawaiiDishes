package com.hakimen.kawaiidishes.block;

import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.registry.AromaRegister;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
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
import org.spongepowered.asm.service.ITransformer;

import static com.hakimen.kawaiidishes.datagen.ItemTagDataGen.CAN_EXTINGUISH_INCENSE;
import static com.hakimen.kawaiidishes.datagen.ItemTagDataGen.CAN_IGNITE_INCENSE;

public class IncenseBlock extends DirectionalBlockWithEntity{

    public static final BooleanProperty LIT = BooleanProperty.of("lit");

    public IncenseBlock() {
        super(Settings.copy(Blocks.GLASS)
                .blockVision((blockState, blockGetter, blockPos) -> false)
                .suffocates((blockState, blockGetter, blockPos) -> false)
                .requiresTool()
                .luminance(value -> value.get(LIT) ? 7 : 0)
        );


        setDefaultState( getStateManager().getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(LIT, false));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> properties )
    {
        properties.add( FACING ).add(LIT);
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
    public @Nullable BlockEntity createBlockEntity(BlockPos pPos, BlockState pState) {
        return new IncenseBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return (level, pos, state, blockEntity) -> ((IncenseBlockEntity) blockEntity).tick(level,pos,state,(IncenseBlockEntity)blockEntity);
    }


    @Override
    public VoxelShape getOutlineShape(BlockState p_60555_, BlockView p_60556_, BlockPos p_60557_, ShapeContext p_60558_) {
        return Block.createCuboidShape(5,0,5,11,6,11);
    }

    @Override
    public ActionResult onUse(BlockState pState, World pLevel, BlockPos pPos, PlayerEntity pPlayer, Hand pHand, BlockHitResult pBlockHitResult) {

        ItemStack holdStack = pPlayer.getStackInHand(pHand);

        IncenseBlockEntity entity = (IncenseBlockEntity) pLevel.getBlockEntity(pPos);

        if(holdStack.isIn(CAN_EXTINGUISH_INCENSE) || holdStack.isIn(CAN_IGNITE_INCENSE)){
            BlockState state = pState;
            if(holdStack.isIn(CAN_EXTINGUISH_INCENSE) && pState.get(LIT)){
                state = pState.with(LIT, false);
                pLevel.playSound(pPlayer, pPos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1f,1f);
            }else if(holdStack.isIn(CAN_IGNITE_INCENSE) && !pState.get(LIT)){
                state = pState.with(LIT, true);
                pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1f,1f);
            }

            if(holdStack.isDamageable()){
               if(holdStack.isIn(CAN_IGNITE_INCENSE) && !pState.get(LIT)){
                   holdStack.setDamage(holdStack.getDamage() + 1);
                   if(holdStack.getDamage() == holdStack.getMaxDamage()){
                       pLevel.playSound(pPlayer, pPos, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 1f,1f);
                       holdStack.decrement(1);
                   }
               }else if(holdStack.isIn(CAN_EXTINGUISH_INCENSE) && pState.get(LIT)){
                   holdStack.setDamage(holdStack.getDamage() + 1);
                   if(holdStack.getDamage() == holdStack.getMaxDamage()){
                       pLevel.playSound(pPlayer, pPos, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 1f,1f);
                       holdStack.decrement(1);
                   }
               }
            }else if(!holdStack.getRecipeRemainder().equals(ItemStack.EMPTY)){
                if(holdStack.isIn(CAN_IGNITE_INCENSE) && !pState.get(LIT)) {
                    var stack = holdStack.getRecipeRemainder();
                    if(stack.getItem() != Items.BUCKET){
                        holdStack.decrement(1);
                        pPlayer.giveItemStack(stack);
                    }
                }
                else if(holdStack.isIn(CAN_EXTINGUISH_INCENSE) && pState.get(LIT)){
                    var stack = holdStack.getRecipeRemainder();
                    if(stack.getItem() != Items.BUCKET){
                        holdStack.decrement(1);
                        pPlayer.giveItemStack(stack);
                    }
                }
            }else{
                if(holdStack.isIn(CAN_IGNITE_INCENSE) && !pState.get(LIT)){
                    holdStack.decrement(1);
                }else if(holdStack.isIn(CAN_EXTINGUISH_INCENSE) && pState.get(LIT)){
                    holdStack.decrement(1);
                }
            }
            pLevel.setBlockState(pPos,state, Block.NOTIFY_ALL);
            return ActionResult.SUCCESS;

        } else if(!pState.get(LIT) && AromaRegister.isValidStack(holdStack)){

            try(Transaction tx = Transaction.openOuter()){
                if(entity.getInventory().amount == 0){
                var stack = holdStack.copy();

                entity.getInventory().insert(ItemVariant.of(stack), stack.getCount(), tx);

                entity.setAroma(AromaRegister.getAromaId(holdStack));
                entity.markDirty();
                    holdStack.decrement(1);
                    pLevel.playSound(pPlayer, pPos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f ,0.75f + (pLevel.getRandom().nextFloat() / 2f) * pLevel.getRandom().nextBetweenExclusive(-1,2));
                }
                tx.commit();
            }
            return ActionResult.SUCCESS;
        } else if(!pState.get(LIT) && !entity.getInventory().getResource().toStack((int)entity.getInventory().amount).isEmpty() && holdStack.isEmpty()){
            try(Transaction tx = Transaction.openOuter()){
                ItemStack stack = entity.getInventory().variant.toStack((int)entity.getInventory().amount);
                if(stack.getCount() == 1){
                    entity.getInventory().extract(entity.getInventory().variant,1,tx);

                    entity.setAroma(0);

                    if(pPlayer.getInventory().containsAny(item -> item.isOf(stack.getItem()) && item.getCount() < item.getMaxCount()) || pPlayer.getInventory().getEmptySlot() != -1){
                        pPlayer.giveItemStack(stack);
                    }else if (pPlayer.getInventory().getEmptySlot() == -1) {
                        dropStack(pLevel, pPos, stack);
                    }
                    pLevel.playSound(pPlayer, pPos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f ,0.75f + (pLevel.getRandom().nextFloat() / 2f) * pLevel.getRandom().nextBetweenExclusive(-1,2));

                }
                entity.markDirty();
                tx.commit();
            }
            return ActionResult.SUCCESS;
        }


        return ActionResult.FAIL;
    }

    @Override
    public void onBreak(World level, BlockPos blockPos, BlockState blockState, PlayerEntity player) {
        IncenseBlockEntity entity = (IncenseBlockEntity) level.getBlockEntity(blockPos);

        ItemStack stack = entity.getInventory().getResource().toStack((int)entity.getInventory().amount);
        dropStack(level,blockPos,stack);
        super.onBreak(level, blockPos, blockState, player);
    }
}
