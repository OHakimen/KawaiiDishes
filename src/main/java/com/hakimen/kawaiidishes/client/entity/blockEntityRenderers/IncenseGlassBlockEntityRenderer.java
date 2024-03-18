package com.hakimen.kawaiidishes.client.entity.blockEntityRenderers;

import com.hakimen.kawaiidishes.aromas.DecorativeAroma;
import com.hakimen.kawaiidishes.aromas.PotionAroma;
import com.hakimen.kawaiidishes.block.IncenseBlock;
import com.hakimen.kawaiidishes.block_entities.DisplayCaseBlockEntity;
import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.client.screens.renderers.FluidTankRenderer;
import com.hakimen.kawaiidishes.custom.type.Aroma;
import com.hakimen.kawaiidishes.utils.ColorUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;

import java.util.Arrays;

public class IncenseGlassBlockEntityRenderer implements BlockEntityRenderer<IncenseBlockEntity> {

    BlockEntityRendererProvider.Context context;

    public IncenseGlassBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }


    @Override
    public void render(IncenseBlockEntity incenseBlockEntity, float partialTicks, PoseStack pPoseStack, MultiBufferSource pBuffers, int packedLight, int overlay) {

        BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();

        if(incenseBlockEntity != null) {
            BlockState selfState = incenseBlockEntity.getBlockState();
            Aroma aroma = incenseBlockEntity.getAromaFromId();

            boolean shouldRender = incenseBlockEntity.getAroma() == 0 ? selfState.getValue(IncenseBlock.LIT) : true;

            if (shouldRender) {
                pPoseStack.pushPose();
                pPoseStack.translate(0.372f, 0.05f, 0.372f);
                pPoseStack.scale(0.25f, 0.25f, 0.25f);

                var renderType = RenderType.cutout();

                ItemStack stack = incenseBlockEntity.getInventory().getStackInSlot(0);

                int color = 0xffffff;

                if (aroma instanceof DecorativeAroma) {
                    color = stack.getItem() instanceof DyeItem dyeItem ? dyeItem.getDyeColor().getFireworkColor() : 0;
                } else if (aroma instanceof PotionAroma) {
                    color = PotionUtils.getColor(stack);
                } else {
                    color = aroma.getColor();
                }

                var renderColors = ColorUtils.getColorsFromHex(color);

                BlockState state = Blocks.ACACIA_LEAVES.defaultBlockState();


                for (int i = 0; i < 2; i++) {
                    BakedModel bakedmodel = blockRenderer.getBlockModel(state);

                    blockRenderer.getModelRenderer().renderModel(
                            pPoseStack.last(),
                            pBuffers.getBuffer(renderType != null ? renderType : net.neoforged.neoforge.client.RenderTypeHelper.getEntityRenderType(renderType, false)),
                            state,
                            bakedmodel,
                            renderColors[0],
                            renderColors[1],
                            renderColors[2],
                            packedLight,
                            overlay,
                            ModelData.EMPTY,
                            renderType
                    );
                    state = Blocks.OAK_LEAVES.defaultBlockState();
                }

                pPoseStack.popPose();
            }
        }
    }

}
