package com.hakimen.kawaiidishes.blocks;

import com.hakimen.kawaiidishes.entity.SittableEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StoolBlock extends Block {
    public StoolBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(1,1));
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

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        return SittableEntity.sitDown(pPlayer, pLevel, pPos);
    }
}
