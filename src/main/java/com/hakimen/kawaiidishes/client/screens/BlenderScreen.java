package com.hakimen.kawaiidishes.client.screens;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.containers.BlenderContainer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BlenderScreen extends HandledScreen<BlenderContainer> {
    private final Identifier GUI = new Identifier(KawaiiDishes.MODID, "textures/gui/blender_gui.png");


    public BlenderScreen(BlenderContainer container, PlayerInventory inv, Text name) {
        super(container, inv, name);
    }


    @Override
    protected void drawForeground(DrawContext guiGraphics, int pMouseX, int pMouseY) {

        guiGraphics.drawText(this.textRenderer, this.title, this.titleX, this.titleY - 1, 4210752, false);
        guiGraphics.drawText(this.textRenderer, this.playerInventoryTitle, this.playerInventoryTitleX, this.playerInventoryTitleY + 2, 4210752, false);
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


        guiGraphics.drawTexture(GUI, relX+74, relY+18, 0, 168, handler.getScaledProgress(),44);
    }
}
