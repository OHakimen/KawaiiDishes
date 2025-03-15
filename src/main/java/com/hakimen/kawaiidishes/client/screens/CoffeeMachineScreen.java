package com.hakimen.kawaiidishes.client.screens;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.screens.renderers.FluidStackRenderer;
import com.hakimen.kawaiidishes.containers.CoffeeMachineContainer;
import com.hakimen.kawaiidishes.utils.FluidStack;
import com.hakimen.kawaiidishes.utils.MouseUtil;
import java.util.Optional;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CoffeeMachineScreen extends HandledScreen<CoffeeMachineContainer> {
    private final Identifier GUI = new Identifier(KawaiiDishes.MODID, "textures/gui/coffee_machine_gui.png");

    private FluidStackRenderer renderer;

    public CoffeeMachineScreen(CoffeeMachineContainer container, PlayerInventory inv, Text name) {
        super(container, inv, name);
        assignFluidRenderer();
    }

    private void assignFluidRenderer() {
        renderer = new FluidStackRenderer(4000, false, 12, 52);
    }

    @Override
    protected void drawForeground(DrawContext guiGraphics, int pMouseX, int pMouseY) {

        guiGraphics.drawText(this.textRenderer, this.title, this.titleX, this.titleY - 1, 4210752, false);
        guiGraphics.drawText(this.textRenderer, this.playerInventoryTitle, this.playerInventoryTitleX, this.playerInventoryTitleY + 2, 4210752, false);

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        renderFluidAreaTooltips(guiGraphics, pMouseX, pMouseY, x, y);
    }

    private void renderFluidAreaTooltips(DrawContext guiGraphics, int pMouseX, int pMouseY, int x, int y) {
        if(isMouseAboveArea(pMouseX, pMouseY, x, y, 49, 18)) {
            guiGraphics.drawTooltip(this.textRenderer,renderer.getTooltip(new FluidStack(handler.getFluidVariant(), handler.getBlockEntity().getWaterTank().getAmount()), TooltipContext.BASIC), Optional.empty(), pMouseX - x, pMouseY - y);
        }
    }
    public void render(DrawContext guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.drawBackground(guiGraphics,partialTicks,mouseX,mouseY);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.drawMouseoverTooltip(guiGraphics, mouseX, mouseY);
    }


    @Override
    protected void drawBackground(DrawContext guiGraphics, float partialTicks, int mouseX, int mouseY) {
        int relX = (this.width - this.backgroundWidth) / 2;
        int relY = (this.height - this.backgroundHeight) / 2;
        guiGraphics.drawTexture(GUI, relX, relY, 0, 0, this.backgroundWidth, this.backgroundHeight + 2);

        FluidStack stack =  new FluidStack(handler.getFluidVariant(), handler.getBlockEntity().getWaterTank().getAmount());

        renderer.drawFluid(guiGraphics, stack,relX + 49, relY + 18);

        guiGraphics.drawTexture(GUI, relX+64, relY+22, 0, 168, handler.getScaledProgress(),44);
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, renderer.getWidth(), renderer.getHeight());
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
    }
}
