package com.hakimen.kawaiidishes.client.entity;

import com.hakimen.kawaiidishes.items.armor.BunnyTailArmorItem;
import com.hakimen.kawaiidishes.items.armor.CatTailArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class BunnyTailArmorRenderer extends GeoArmorRenderer<BunnyTailArmorItem> {
    public BunnyTailArmorRenderer() {
        super(new BunnyTailArmorModel());
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
