package com.hakimen.kawaiidishes.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GlassCupBlock extends Block  {

    public GlassCupBlock(Properties properties) {
        super(properties);
    }

    public GlassCupBlock(){
        super(Properties.of(Material.STONE)
                .strength(1,1)
                .isSuffocating((p_61036_, p_61037_, p_61038_) -> false));
    }


    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Shapes.join(
                Shapes.join(
                        Block.box(5.0D, 3.0D, 5.0D, 11.0D, 13.0D, 11.0D),
                        Block.box(5.0D, 0.0D, 5.0D, 11.0D, 1.0D, 11.0D),
                        BooleanOp.OR),
                Block.box(7d,1d,7d,9d,3d,9d), BooleanOp.OR);
    }

}
