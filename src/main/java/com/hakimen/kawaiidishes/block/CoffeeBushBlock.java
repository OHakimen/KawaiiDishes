package com.hakimen.kawaiidishes.block;

import com.hakimen.kawaiidishes.registry.ItemRegister;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class CoffeeBushBlock extends PlantBlock implements Fertilizable {

    public static final IntProperty AGE = Properties.AGE_3;
    private static final VoxelShape TINY_BUSH_SHAPE = Block.createCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
    private static final VoxelShape BUSH_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public CoffeeBushBlock(Settings pProperties) {
        super(pProperties);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, Integer.valueOf(0)));
    }


    protected void appendProperties(StateManager.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }


    @Override
    public boolean isFertilizable(WorldView pLevelReader, BlockPos pPos, BlockState pState, boolean bl) {
        return pState.get(AGE) < 3;
    }

    @Override
    public boolean canGrow(World pLevel, Random pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public void grow(ServerWorld pLevel, Random pRandom, BlockPos pPos, BlockState pState) {
        int i = Math.min(3, pState.get(AGE) + 1);
        pLevel.setBlockState(pPos, pState.with(AGE, Integer.valueOf(i)), 2);
    }

    @Override
    public boolean hasRandomTicks(BlockState pState) {
        return pState.get(AGE) < 3;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState pState, BlockView pLevel, BlockPos pPos, ShapeContext pContext) {
        if (pState.get(AGE) == 0) {
            return TINY_BUSH_SHAPE;
        } else {
            return pState.get(AGE) < 3 ? BUSH_SHAPE : super.getOutlineShape(pState, pLevel, pPos, pContext);
        }
    }

    @Override
    public void randomTick(BlockState pState, ServerWorld pLevel, BlockPos pPos, Random pRandom) {
        int i = pState.get(AGE);
        if (i < 3 && pLevel.getBaseLightLevel(pPos.up(), 0) >= 9) {
            BlockState blockstate = pState.with(AGE, Integer.valueOf(i + 1));
            pLevel.setBlockState(pPos, blockstate, 2);
            pLevel.emitGameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Emitter.of(blockstate));
        }
    }

    public ActionResult onUse(BlockState pState, World pLevel, BlockPos pPos, PlayerEntity pPlayer, Hand pHand, BlockHitResult pHit) {
        int currentAge = pState.get(AGE);
        if ((currentAge != 3) && pPlayer.getStackInHand(pHand).isOf(Items.BONE_MEAL)) {
            return ActionResult.PASS;
        } else if (currentAge == 3) {
            int itemCount = 1 + pLevel.random.nextInt(4);
            dropStack(pLevel, pPos, new ItemStack(ItemRegister.COFFEE_BERRIES.get(), itemCount));
            pLevel.playSound(null, pPos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);
            BlockState blockstate = pState.with(AGE, 1);
            pLevel.setBlockState(pPos, blockstate, 2);
            pLevel.emitGameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Emitter.of(pPlayer, blockstate));
            return ActionResult.success(pLevel.isClient);
        } else {
            return super.onUse(pState, pLevel, pPos, pPlayer, pHand, pHit);
        }
    }

    @Override
    public void afterBreak(World level, PlayerEntity player, BlockPos blockPos, BlockState blockState, @Nullable BlockEntity blockEntity, ItemStack itemStack) {
        int age = blockState.get(AGE);
        if(age == 3){
            int itemCount = 1 + level.random.nextInt(4);
            dropStack(level, blockPos, new ItemStack(ItemRegister.COFFEE_BERRIES.get(), itemCount));
        }

        super.afterBreak(level, player, blockPos, blockState, blockEntity, itemStack);
    }
}
