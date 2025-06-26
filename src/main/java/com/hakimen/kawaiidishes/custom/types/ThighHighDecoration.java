package com.hakimen.kawaiidishes.custom.types;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ThighHighDecoration {
    Component name;
    ResourceLocation overlayModel;
    ResourceLocation texture;
    ResourceLocation extraTexture;

    public ThighHighDecoration(Component name, ResourceLocation overlayModel, ResourceLocation texture) {
        this.overlayModel = overlayModel;
        this.name = name;
        this.texture = texture;
        this.extraTexture = null;
    }

    public ThighHighDecoration(Component name, ResourceLocation overlayModel, ResourceLocation texture, ResourceLocation extraTexture) {
        this.overlayModel = overlayModel;
        this.name = name;
        this.texture = texture;
        this.extraTexture = extraTexture;
    }


    public Component getName() {
        return name;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public ResourceLocation getExtraTexture() {
        return extraTexture;
    }

    public ResourceLocation getOverlayModel() {
        return overlayModel;
    }
}
