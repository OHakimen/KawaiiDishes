package com.hakimen.kawaiidishes.client.entity;

import com.hakimen.kawaiidishes.items.armor.CatTailArmorItem;
import com.hakimen.kawaiidishes.items.armor.FoxTailArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class FoxTailArmorRenderer extends GeoArmorRenderer<FoxTailArmorItem> {
    public FoxTailArmorRenderer() {
        super(new FoxTailArmorModel());
        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorLeftLeg";
        this.leftLegBone = "armorRightLeg";
        this.rightBootBone = "armorLeftBoot";
        this.leftBootBone = "armorRightBoot";
    }
}
