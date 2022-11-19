package com.hakimen.kawaiidishes.client.block_entity_renderers;

import com.hakimen.kawaiidishes.blocks.CoffeeMachineBlock;
import com.hakimen.kawaiidishes.blocks.IceCreamMachineBlock;
import com.hakimen.kawaiidishes.blocks.block_entities.CoffeeMachineBlockEntity;
import com.hakimen.kawaiidishes.blocks.block_entities.IceCreamBlockEntity;
import com.hakimen.kawaiidishes.blocks.block_entities.IceCreamMachineBlockEntity;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;

public class IceCreamMachineRenderer implements BlockEntityRenderer<IceCreamMachineBlockEntity> {
    BlockEntityRendererProvider.Context context;

    public IceCreamMachineRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(IceCreamMachineBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        for (int i = 1; i < 4; i++) {
            pPoseStack.pushPose();
            pPoseStack.scale(0.25f,0.25f,0.25f);
            pPoseStack.translate(2f, 2.5f+(i/16f), 2f);
            pPoseStack.mulPose(Quaternion.fromXYZ(0, (i*4)/3.1415f,0));

            itemRenderer.renderStatic(null,
                    pBlockEntity.inventory.getStackInSlot(i),
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
