package com.hakimen.kawaiidishes.custom.types;

import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ThighHighDecoration {
    Text name;
    Identifier overlayModel;
    Identifier texture;
    Identifier extraTexture;

    public ThighHighDecoration(Text name, Identifier overlayModel, Identifier texture) {
        this.overlayModel = overlayModel;
        this.name = name;
        this.texture = texture;
        this.extraTexture = null;
    }

    public ThighHighDecoration(Text name, Identifier overlayModel, Identifier texture, Identifier extraTexture) {
        this.overlayModel = overlayModel;
        this.name = name;
        this.texture = texture;
        this.extraTexture = extraTexture;
    }


    public Text getName() {
        return name;
    }

    public Identifier getTexture() {
        return texture;
    }

    public Identifier getExtraTexture() {
        return extraTexture;
    }

    public Identifier getOverlayModel() {
        return overlayModel;
    }
}
