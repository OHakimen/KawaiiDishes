package com.hakimen.kawaiidishes.client.screens;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.containers.BlenderContainer;
import com.hakimen.kawaiidishes.containers.IceCreamMakerContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class IceCreamMakerScreen extends AbstractContainerScreen<IceCreamMakerContainer> {
    private final ResourceLocation GUI = new ResourceLocation(KawaiiDishes.MODID, "textures/gui/ice_cream_maker_gui.png");


    public IceCreamMakerScreen(IceCreamMakerContainer container, Inventory inv, Component name) {
        super(container, inv, name);
    }


    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int pMouseX, int pMouseY) {

        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY - 1, 4210752, false);
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY + 2, 4210752, false);
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

        guiGraphics.blit(GUI, relX+47, relY+13, 0, 168, menu.getScaledProgress(),62);
    }
}
