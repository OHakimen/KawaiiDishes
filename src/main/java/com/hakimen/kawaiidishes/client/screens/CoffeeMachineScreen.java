package com.hakimen.kawaiidishes.client.screens;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.screens.renderers.FluidTankRenderer;
import com.hakimen.kawaiidishes.containers.CoffeeMachineContainer;
import com.hakimen.kawaiidishes.utils.MouseUtil;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.TooltipFlag;

import java.util.Optional;

public class CoffeeMachineScreen extends AbstractContainerScreen<CoffeeMachineContainer> {
    private final ResourceLocation GUI = new ResourceLocation(KawaiiDishes.MODID, "textures/gui/coffee_machine_gui.png");

    private FluidTankRenderer renderer;

    public CoffeeMachineScreen(CoffeeMachineContainer container, Inventory inv, Component name) {
        super(container, inv, name);
        assignFluidRenderer();
    }

    private void assignFluidRenderer() {
        renderer = new FluidTankRenderer(4000, false, 12, 52);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int pMouseX, int pMouseY) {

        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY - 1, 4210752, false);
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY + 2, 4210752, false);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        renderFluidAreaTooltips(guiGraphics, pMouseX, pMouseY, x, y);
    }

    private void renderFluidAreaTooltips(GuiGraphics guiGraphics, int pMouseX, int pMouseY, int x, int y) {
        if(isMouseAboveArea(pMouseX, pMouseY, x, y, 49, 18)) {
            guiGraphics.renderTooltip(this.font,renderer.getTooltip(menu.getFluidStack(), TooltipFlag.NORMAL), Optional.empty(), pMouseX - x, pMouseY - y);
        }
    }
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBg(guiGraphics,partialTicks,mouseX,mouseY);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }


    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(GUI, relX, relY, 0, 0, this.imageWidth, this.imageHeight + 2);

        renderer.render(guiGraphics.pose(), relX + 49, relY + 18, menu.getFluidStack());

        guiGraphics.blit(GUI, relX+64, relY+22, 0, 168, menu.getScaledProgress(),44);
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, renderer.getWidth(), renderer.getHeight());
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
    }
}
