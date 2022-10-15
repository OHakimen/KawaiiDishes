package com.hakimen.kawaiidishes.client.entity;

import com.hakimen.kawaiidishes.items.armor.CatTailArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class CatTailArmorRenderer extends GeoArmorRenderer<CatTailArmorItem> {
    public CatTailArmorRenderer() {
        super(new CatTailArmorModel());
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
