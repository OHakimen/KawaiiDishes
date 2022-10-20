package com.hakimen.kawaiidishes.client.block_entity_renderers;

import com.hakimen.kawaiidishes.blocks.block_entities.CoffeePressBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;

public class CoffeePressRenderer  implements BlockEntityRenderer<CoffeePressBlockEntity> {
    BlockEntityRendererProvider.Context context;
    public CoffeePressRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(CoffeePressBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        for (int i = 0; i < pBlockEntity.inventory.getSlots(); i++) {
            pPoseStack.pushPose();
            var itemStack = pBlockEntity.inventory.getStackInSlot(i);
            pPoseStack.translate(0.5, .35f+(i/16f), 0.5f);
            pPoseStack.scale(0.25f,0.25f,0.25f);
            pPoseStack.mulPose(Quaternion.fromXYZ(0,(float)(Math.sin(i)*2),0));
            itemRenderer.renderStatic(null,
                    itemStack,
                    ItemTransforms.TransformType.FIXED,
                    false,
                    pPoseStack,
                    pBufferSource,
                    Minecraft.getInstance().level,
                    pPackedLight,
                    pPackedOverlay,
                    0);
            pPoseStack.popPose();
        }
    }
}
