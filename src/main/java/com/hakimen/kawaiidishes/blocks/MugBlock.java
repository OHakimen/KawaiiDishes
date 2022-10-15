package com.hakimen.kawaiidishes.blocks;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.crafting.conditions.FalseCondition;

public class MugBlock extends Block  {

    public MugBlock(Properties properties) {
        super(properties);
    }

    public MugBlock(){
        super(BlockBehaviour.Properties.of(Material.STONE)
                .strength(1,1)
                .dynamicShape().isSuffocating((p_61036_, p_61037_, p_61038_) -> false));
    }


    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Vec3 vec3 = pState.getOffset(pLevel, pPos);
        return Block.box(6.0D, 0.0D, 6.0D, 11.0D, 7.0D, 11.0D).move(vec3.x, vec3.y, vec3.z);
    }

    public BlockBehaviour.OffsetType getOffsetType() {
        return BlockBehaviour.OffsetType.XZ;
    }

}
