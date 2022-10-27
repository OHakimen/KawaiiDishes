package com.hakimen.kawaiidishes.blocks;

import com.hakimen.kawaiidishes.blocks.block_entities.CoffeePressBlockEntity;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class CoffeePressBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty PRESSED = BooleanProperty.create("pressed");
    public CoffeePressBlock() {
        super(Properties.of(Material.STONE).strength(2f,2f)
                .sound(SoundType.GLASS));
        registerDefaultState( getStateDefinition().any()
                .setValue(FACING, Direction.NORTH )
                .setValue(PRESSED, false));
    }



    @Override
    @Deprecated
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Block.box(4.5f,0,4.5f,11.5,11,11.5);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> properties )
    {
        properties.add( FACING );
        properties.add( PRESSED );
    }

    @Nonnull
    @Override
    @Deprecated
    public BlockState mirror( BlockState state, Mirror mirrorIn )
    {
        return state.rotate( mirrorIn.getRotation( state.getValue( FACING ) ) );
    }
    @Nonnull
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

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CoffeePressBlockEntity(pPos,pState);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if(level.getBlockEntity(pos) instanceof CoffeePressBlockEntity blockEntity){
            for (int i = 0; i < blockEntity.inventory.getSlots(); i++) {
                level.addFreshEntity(new ItemEntity(level,pos.getX(),pos.getY(),pos.getZ(),blockEntity.inventory.getStackInSlot(i)));
            }
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if(blockEntity instanceof CoffeePressBlockEntity coffeePress){
            ItemStack currentItemInHand = pPlayer.getItemInHand(pHand).copy();
            if(currentItemInHand.equals(ItemStack.EMPTY)){
                if(pPlayer.isCrouching()){
                    for (int i = coffeePress.inventory.getSlots()-1; i > -1; i--) {
                        if(coffeePress.inventory.getStackInSlot(i) != ItemStack.EMPTY){
                            pPlayer.addItem(coffeePress.inventory.extractItem(i,1,false));
                            break;
                        }
                    }
                }else if(CoffeePressBlockEntity.hasRecipe(coffeePress)){
                    CoffeePressBlockEntity.craft(coffeePress);
                }
            }else if(currentItemInHand.getItem().equals(ItemRegister.mug.get()) && coffeePress.coffeeGotMade){
                if(coffeePress.coffeeMade.getCount() >0){
                    pPlayer.addItem(coffeePress.coffeeMade);
                }else{
                    coffeePress.coffeeGotMade = false;
                    pLevel.setBlockAndUpdate(coffeePress.getBlockPos(),coffeePress.getBlockState().setValue(
                            CoffeePressBlock.PRESSED,coffeePress.coffeeGotMade
                    ));

                }
                pPlayer.getItemInHand(pHand).shrink(1);

            }else if(coffeePress.inventory.getStackInSlot(0).getCount() < 3){
                var stack = currentItemInHand.copy();
                stack.setCount(1);
                for (int i = 0; i < coffeePress.inventory.getSlots(); i++) {
                    if(coffeePress.inventory.getStackInSlot(i) == ItemStack.EMPTY){
                        coffeePress.inventory.insertItem(i,stack,false);
                        pPlayer.getItemInHand(pHand).shrink(1);
                        break;
                    }
                }

            }
        }


        return InteractionResult.SUCCESS;
    }
}
