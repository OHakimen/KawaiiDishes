package com.hakimen.kawaiidishes.client.entity.blockEntityRenderers;

import com.hakimen.kawaiidishes.block.IncenseBlock;
import com.hakimen.kawaiidishes.block_entities.DisplayCaseBlockEntity;
import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.client.screens.renderers.FluidTankRenderer;
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
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
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
            IncenseBlockEntity.Aromas aroma = incenseBlockEntity.getAroma();

            boolean shouldRender = aroma.equals(IncenseBlockEntity.Aromas.INVALID) ? selfState.getValue(IncenseBlock.LIT) : true;

            if (shouldRender) {
                pPoseStack.pushPose();
                pPoseStack.translate(0.372f, 0.05f, 0.372f);
                pPoseStack.scale(0.25f, 0.25f, 0.25f);

                var renderType = RenderType.cutout();

                int color = 0xffffff;


                ItemStack stack = incenseBlockEntity.getInventory().getStackInSlot(0);

                switch (aroma) {
                    case DecorativeAroma -> {
                        color = DyeColor.getColor(stack).getFireworkColor();
                    }
                    case PotionAroma -> {
                        color = PotionUtils.getColor(stack);
                    }
                    default -> {
                        color = aroma.color;
                    }
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
