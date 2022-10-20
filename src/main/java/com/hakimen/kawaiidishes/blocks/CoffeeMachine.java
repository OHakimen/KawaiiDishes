package com.hakimen.kawaiidishes.blocks;

import com.hakimen.kawaiidishes.blocks.block_entities.CoffeeMachineBlockEntity;
import com.hakimen.kawaiidishes.blocks.block_entities.CoffeePressBlockEntity;
import com.hakimen.kawaiidishes.containers.CoffeeMachineContainer;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.*;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import javax.swing.*;

public class CoffeeMachine extends Block implements EntityBlock{
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public CoffeeMachine() {
        super(Properties.of(Material.STONE).strength(2f,2f)
                .sound(SoundType.METAL));
        registerDefaultState( getStateDefinition().any()
                .setValue(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide ? null
                : (level, pos, state, blockEntity) -> ((CoffeeMachineBlockEntity) blockEntity).tick(level,pos,state,(CoffeeMachineBlockEntity)blockEntity);
    }

    @Override
    @Deprecated
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {

        VoxelShape box = Block.box(0,0,0,16,2,16);
        box = Shapes.join(box, Block.box(1,0,1,15,14,15),BooleanOp.OR);
        box = Shapes.join(box,Block.box(0,14,0,16,16,16),BooleanOp.OR);
        return box;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> properties )
    {
        properties.add( FACING );
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


    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof CoffeeMachineBlockEntity) {
                NetworkHooks.openGui(((ServerPlayer)pPlayer), (CoffeeMachineBlockEntity)entity, pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CoffeeMachineBlockEntity(pPos,pState);
    }
}
