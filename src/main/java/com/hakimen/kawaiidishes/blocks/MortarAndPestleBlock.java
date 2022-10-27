package com.hakimen.kawaiidishes.blocks;

import com.hakimen.kawaiidishes.blocks.block_entities.MortarAndPestleBlockEntity;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class MortarAndPestleBlock extends BaseEntityBlock {
    public MortarAndPestleBlock() {
        super(BlockBehaviour.Properties.of(Material.STONE)
                .strength(2, 2)
                .noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Shapes.create(0.25d, 0d, 0.25d, 0.75d, 0.25d, 0.75d);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return (pLevel1, pPos, pState1, pBlockEntity) -> {
            if (pBlockEntity instanceof MortarAndPestleBlockEntity entity) {
                entity.tick(pLevel, pPos, pState1, entity);
            }
        };
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.getBlockEntity(pPos) instanceof MortarAndPestleBlockEntity entity) {
            if (pPlayer.getMainHandItem().is(Items.AIR.asItem())) {
                if (entity.inventory.getStackInSlot(0) != ItemStack.EMPTY && pPlayer.isCrouching()) {
                    pPlayer.addItem(entity.inventory.extractItem(0, 1, false));
                    entity.setChanged();
                    return InteractionResult.SUCCESS;
                } else {
                    entity.isUsing = true;
                    entity.usingTime = 200;
                    entity.setChanged();
                    return InteractionResult.SUCCESS;
                }
            } else if (!pPlayer.isCrouching()) {
                if (entity.inventory.getStackInSlot(0) == ItemStack.EMPTY) {
                    var stack = pPlayer.getItemInHand(pHand).copy();
                    stack.setCount(1);
                    entity.inventory.insertItem(0, stack, false);
                    pPlayer.getItemInHand(pHand).shrink(1);
                    entity.setChanged();
                    return InteractionResult.SUCCESS;
                }
            }
            entity.setChanged();
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new MortarAndPestleBlockEntity(pPos, pState);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
}
