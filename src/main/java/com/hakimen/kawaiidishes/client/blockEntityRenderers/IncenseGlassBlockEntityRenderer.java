package com.hakimen.kawaiidishes.client.blockEntityRenderers;

import com.hakimen.kawaiidishes.aromas.DecorativeAroma;
import com.hakimen.kawaiidishes.aromas.PotionAroma;
import com.hakimen.kawaiidishes.block.IncenseBlock;
import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.custom.types.Aroma;
import com.hakimen.kawaiidishes.utils.ColorUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtil;

public class IncenseGlassBlockEntityRenderer implements BlockEntityRenderer<IncenseBlockEntity> {

    BlockEntityRendererFactory.Context context;

    public IncenseGlassBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        this.context = context;
    }


    @Override
    public void render(IncenseBlockEntity incenseBlockEntity, float partialTicks, MatrixStack pPoseStack, VertexConsumerProvider pBuffers, int packedLight, int overlay) {

        BlockRenderManager blockRenderer = MinecraftClient.getInstance().getBlockRenderManager();

        if(incenseBlockEntity != null) {
            BlockState selfState = incenseBlockEntity.getCachedState();
            Aroma aroma = incenseBlockEntity.getAromaFromId();

            boolean shouldRender = incenseBlockEntity.getAroma() == 0 ? selfState.get(IncenseBlock.LIT) : true;

            if (shouldRender) {

                pPoseStack.push();
                pPoseStack.translate(0.372f, 0.05f, 0.372f);
                pPoseStack.scale(0.25f, 0.25f, 0.25f);

                var renderType = RenderLayer.getCutout();

                ItemStack stack = incenseBlockEntity.getInventory().getResource().toStack((int) incenseBlockEntity.getInventory().amount);

                int color = 0xffffff;

                if (aroma instanceof DecorativeAroma) {
                    color = stack.getItem() instanceof DyeItem dyeItem ? dyeItem.getColor().getFireworkColor() : 0;
                } else if (aroma instanceof PotionAroma) {
                    color = PotionUtil.getColor(stack);
                } else {
                    color = aroma.getColor();
                }

                var renderColors = ColorUtils.getColorsFromHex(color);

                BlockState state = Blocks.ACACIA_LEAVES.getDefaultState();


                for (int i = 0; i < 2; i++) {
                    BakedModel bakedmodel = blockRenderer.getModel(state);

                    blockRenderer.getModelRenderer().render(
                            pPoseStack.peek(),
                            pBuffers.getBuffer(renderType != null ? renderType : RenderLayers.getItemLayer(stack,false)),
                            state,
                            bakedmodel,
                            renderColors[0],
                            renderColors[1],
                            renderColors[2],
                            packedLight,
                            overlay
                    );
                    state = Blocks.OAK_LEAVES.getDefaultState();
                }

                pPoseStack.pop();
            }
        }
    }

}
