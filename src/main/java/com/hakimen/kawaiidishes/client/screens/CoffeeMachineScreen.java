package com.hakimen.kawaiidishes.client.screens;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.containers.CoffeeMachineContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CoffeeMachineScreen extends AbstractContainerScreen<CoffeeMachineContainer>{

    private final ResourceLocation GUI = new ResourceLocation(KawaiiDishes.modId, "textures/gui/coffee_machine_gui.png");

    public CoffeeMachineScreen(CoffeeMachineContainer container, Inventory inv, Component name) {
        super(container, inv, name);
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.renderBg(matrixStack,partialTicks,mouseX,mouseY);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }


    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
        this.blit(matrixStack, relX+89, relY+35, 176, 0,menu.getScaledProgress(),20);

    }
}

