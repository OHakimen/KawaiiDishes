package com.hakimen.kawaiidishes.client.screens.renderers;

import com.google.common.base.Preconditions;
import com.hakimen.kawaiidishes.utils.FluidStack;
import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.impl.client.itemgroup.FabricCreativeGuiComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.material.Fluids;
import org.joml.Matrix3dStack;
import org.joml.Matrix4f;
import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import static net.minecraft.world.inventory.InventoryMenu.BLOCK_ATLAS;

public class FluidStackRenderer implements IIngredientRenderer<FluidStack> {
    private static final NumberFormat nf = NumberFormat.getIntegerInstance();
    public final long capacityMb;
    private final TooltipMode tooltipMode;
    private final int width;
    private final int height;

    enum TooltipMode {
        SHOW_AMOUNT,
        SHOW_AMOUNT_AND_CAPACITY,
        ITEM_LIST
    }

    public FluidStackRenderer() {
        this(FluidStack.convertDropletsToMb(FluidConstants.BUCKET), TooltipMode.SHOW_AMOUNT_AND_CAPACITY, 16, 16);
    }

    public FluidStackRenderer(long capacityMb, boolean showCapacity, int width, int height) {
        this(capacityMb, showCapacity ? TooltipMode.SHOW_AMOUNT_AND_CAPACITY : TooltipMode.SHOW_AMOUNT, width, height);
    }

    @SuppressWarnings("DeprecatedIsStillUsed")
    @Deprecated
    public FluidStackRenderer(int capacityMb, boolean showCapacity, int width, int height) {
        this(capacityMb, showCapacity ? TooltipMode.SHOW_AMOUNT_AND_CAPACITY : TooltipMode.SHOW_AMOUNT, width, height);
    }

    private FluidStackRenderer(long capacityMb, TooltipMode tooltipMode, int width, int height) {
        Preconditions.checkArgument(capacityMb > 0, "capacity must be > 0");
        Preconditions.checkArgument(width > 0, "width must be > 0");
        Preconditions.checkArgument(height > 0, "height must be > 0");
        this.capacityMb = capacityMb;
        this.tooltipMode = tooltipMode;
        this.width = width;
        this.height = height;
    }

    /*
     * METHOD FROM https://github.com/TechReborn/TechReborn
     * UNDER MIT LICENSE: https://github.com/TechReborn/TechReborn/blob/1.19/LICENSE.md
     */
    public void drawFluid(GuiGraphics context, FluidStack fluidStorage, int x, int y) {
        if (fluidStorage.getFluidVariant().getFluid() == Fluids.EMPTY) {
            return;
        }
        RenderSystem.setShaderTexture(0, BLOCK_ATLAS);
        y += height;
        final TextureAtlasSprite sprite = FluidVariantRendering.getSprite(fluidStorage.fluidVariant);
        int color = FluidVariantRendering.getColor(fluidStorage.fluidVariant);


        int drawHeight = (int) Math.ceil(FluidStack.convertDropletsToMb(fluidStorage.amount) / (capacityMb * 1F) * height);
        int iconHeight = sprite.getY();
        int offsetHeight = drawHeight;

        RenderSystem.setShaderColor((color >> 16 & 255) / 255.0F, (float) (color >> 8 & 255) / 255.0F, (float) (color & 255) / 255.0F, 1F);


        int iteration = 0;
        while (offsetHeight != 0) {
            final int curHeight = Math.min(offsetHeight, iconHeight);

            context.blit(x, y - offsetHeight, 0, width, curHeight, sprite);
            offsetHeight -= curHeight;
            iteration++;

            if (iteration > 50) {
                break;
            }
        }
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);

        RenderSystem.setShaderTexture(0, FluidRenderHandlerRegistry.INSTANCE.get(fluidStorage.fluidVariant.getFluid())
                .getFluidSprites(net.minecraft.client.Minecraft.getInstance().level, null, fluidStorage.fluidVariant.getFluid().defaultFluidState())[0].atlasLocation());
    }

    public List<net.minecraft.network.chat.Component> getTooltip(FluidStack fluidStack, TooltipFlag tooltipFlag) {
        List<net.minecraft.network.chat.Component> tooltip = new ArrayList<>();
        FluidVariant fluidType = fluidStack.getFluidVariant();
        if (fluidType == null) {
            return tooltip;
        }

        MutableComponent displayName = net.minecraft.network.chat.Component.translatable("block." + BuiltInRegistries.FLUID.getKey(fluidStack.fluidVariant.getObject()).toLanguageKey());
        tooltip.add(displayName);

        long amount = fluidStack.getAmount();
        if (tooltipMode == TooltipMode.SHOW_AMOUNT_AND_CAPACITY) {
            MutableComponent amountString = net.minecraft.network.chat.Component.translatable("kawaiidishes.tooltip.liquid.amount_with_capacity", nf.format(FluidStack.convertDropletsToMb(amount)), nf.format(capacityMb));
            tooltip.add(amountString.withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
        } else if (tooltipMode == TooltipMode.SHOW_AMOUNT) {
            MutableComponent amountString = net.minecraft.network.chat.Component.translatable("kawaiidishes.tooltip.liquid.amount", nf.format(FluidStack.convertDropletsToMb(amount)));
            tooltip.add(amountString.withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
        }

        return tooltip;
    }




    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}