package com.hakimen.kawaiidishes.client.entity.blocks;

import com.hakimen.kawaiidishes.blocks.CoffeeMachineBlock;
import com.hakimen.kawaiidishes.blocks.MortarAndPestleBlock;
import com.hakimen.kawaiidishes.blocks.block_entities.MortarAndPestleBlockEntity;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Quaternion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

import java.util.Objects;

public class MortarAndPestleRenderer extends GeoBlockRenderer<MortarAndPestleBlockEntity> {
    public MortarAndPestleRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new MortarAndPestleBlockModel());
    }




    @Override
    public void render(BlockEntity tile, float partialTicks, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int overlay) {
        MortarAndPestleBlockEntity mortar = (MortarAndPestleBlockEntity)tile;

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        pPoseStack.pushPose();
        var itemStack = mortar.inventory.getStackInSlot(0);

        pPoseStack.translate(0.5, 0.15, 0.5);
        pPoseStack.scale(0.25f, 0.25f, 0.25f);
        pPoseStack.mulPose(Quaternion.fromXYZ(3.1415f / 2f, 0, 0));

        itemRenderer.renderStatic(null,
                itemStack,
                ItemTransforms.TransformType.FIXED,
                false,
                pPoseStack,
                pBufferSource,
                Minecraft.getInstance().level,
                pPackedLight,
                overlay,
                0);
        pPoseStack.popPose();
        super.render(mortar, partialTicks, pPoseStack, pBufferSource, pPackedLight);
    }

    @Override
    public RenderType getRenderType(MortarAndPestleBlockEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
