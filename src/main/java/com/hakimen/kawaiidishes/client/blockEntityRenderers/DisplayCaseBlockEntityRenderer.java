package com.hakimen.kawaiidishes.client.blockEntityRenderers;

import com.hakimen.kawaiidishes.block.DirectionalBlockWithEntity;
import com.hakimen.kawaiidishes.block_entities.DisplayCaseBlockEntity;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import org.joml.Quaternionf;

public class DisplayCaseBlockEntityRenderer implements BlockEntityRenderer<DisplayCaseBlockEntity> {
    BlockEntityRendererFactory.Context context;
    public DisplayCaseBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        this.context = context;
    }

    @Override
    public void render(DisplayCaseBlockEntity pBlockEntity, float pPartialTick, MatrixStack pPoseStack, VertexConsumerProvider pBufferSource, int pPackedLight, int pPackedOverlay) {
        ItemRenderer renderer = MinecraftClient.getInstance().getItemRenderer();
        int slots = pBlockEntity.getInventory().size();
        for (int i = 0; i < slots; i++) {
            pPoseStack.push();
            ItemStack stack = pBlockEntity.getInventory().getStack(i);
            if(!stack.equals(ItemStack.EMPTY)){
                int renderCount = stack.getMaxCount() == 1 ? 1 : stack.getCount() / (stack.getMaxCount() / 4) + 1;
                int x,y;


                x = (i % 4) % 2;
                y = (i % 4) / 2;

                pPoseStack.scale(0.35f,0.35f,0.35f);
                BlockState state = pBlockEntity.getWorld().getBlockState(pBlockEntity.getPos());

                switch ((state.getBlock().equals(BlockRegister.DISPLAY_CASE.get()) ? state.get(DirectionalBlockWithEntity.FACING) : Direction.UP)){
                    case NORTH ->  {
                        pPoseStack.translate(1 + x,  (i >= 4 ? 0.5 : 1.85f), 1 + y);
                    }
                    case SOUTH -> {
                        pPoseStack.multiply(new Quaternionf().rotateXYZ(0,(float)(Math.toRadians(180)), 0),0,0,0);
                        pPoseStack.translate(x - 2,  (i >= 4 ? 0.5 : 1.85f),  y - 2);
                    }
                    case EAST -> {
                        pPoseStack.multiply(new Quaternionf().rotateXYZ(0,(float)(Math.toRadians(270)), 0),0,0,0);
                        pPoseStack.translate(x + 1,  (i >= 4 ? 0.5 : 1.85f),  y - 2);
                    }

                    case WEST -> {
                        pPoseStack.multiply(new Quaternionf().rotateXYZ(0,(float)(Math.toRadians(90)), 0),0,0,0);
                        pPoseStack.translate(x - 2,  (i >= 4 ? 0.5 : 1.85f),  y + 1);
                    }
                }
                pPoseStack.multiply(new Quaternionf().rotateXYZ((float)Math.PI/2.5f,0,0),0,0,0);
                for (int j = 0; j < renderCount; j++) {
                    pPoseStack.multiply(new Quaternionf().rotateXYZ(0,0, (float)(Math.sin(j + (x + y))/4f)),0,0,0);
                    renderer.renderItem(null,
                            stack,
                            ModelTransformationMode.FIXED,
                            false,
                            pPoseStack,
                            pBufferSource,
                            MinecraftClient.getInstance().world,
                            pPackedLight,
                            pPackedOverlay,
                            0);
                    pPoseStack.translate(0, 0f, -0.05f);
                }
            }

            pPoseStack.pop();
        }
    }
}
