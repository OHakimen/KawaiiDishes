package com.hakimen.kawaiidishes.blocks;

import com.hakimen.kawaiidishes.blocks.block_entities.StoolBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class StoolBlock extends Block  implements EntityBlock {
    public StoolBlock() {
        super(BlockBehaviour.Properties.of(Material.WOOD).strength(1,1));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        var finalShape = Block.box(2.0D, 8.0D, 2.0D, 14.0D, 10.0D, 14.0D);
        finalShape = Shapes.join(finalShape,Block.box(7.0D, 0.0D, 2.0D, 9.0D, 2.0D, 8.0D), BooleanOp.OR);
        finalShape = Shapes.join(finalShape,Block.box(2.0D, 0.0D, 7.0D, 8.0D, 2.0D, 9.0D), BooleanOp.OR);
        finalShape = Shapes.join(finalShape,Block.box(7.0D, 0.0D, 8.0D, 9.0D, 2.0D, 14.0D), BooleanOp.OR);
        finalShape = Shapes.join(finalShape,Block.box(9.0D, 0.0D, 7.0D, 14.0D, 2.0D, 9.0D), BooleanOp.OR);
        finalShape = Shapes.join(finalShape,Block.box(6.5D, 0D, 6.5D, 9.5D, 10D, 9.5D), BooleanOp.OR);

        return finalShape;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new StoolBlockEntity(pPos,pState);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        final var stool = (StoolBlockEntity) pLevel.getBlockEntity(pPos);
        if (!pLevel.isClientSide() && !pPlayer.isShiftKeyDown()) {
            stool.getOrCreateSeat();
            final boolean success = pPlayer.startRiding(stool.seat);
            if (success) {
                stool.setChanged();
            }
            return success ? InteractionResult.SUCCESS : InteractionResult.PASS;
        }

        return InteractionResult.PASS;
    }
}
