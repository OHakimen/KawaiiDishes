package com.hakimen.kawaiidishes.client.entity;

import com.hakimen.kawaiidishes.items.armor.MaidDressArmorItem;
import com.hakimen.kawaiidishes.items.armor.ThighHighsArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ThighHighsArmorRenderer extends GeoArmorRenderer<ThighHighsArmorItem> {
    public ThighHighsArmorRenderer() {
        super(new ThighHighsArmorModel());
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
