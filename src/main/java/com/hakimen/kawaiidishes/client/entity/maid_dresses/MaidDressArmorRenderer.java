package com.hakimen.kawaiidishes.client.entity.maid_dresses;

import com.hakimen.kawaiidishes.items.armor.MaidDressArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class MaidDressArmorRenderer extends GeoArmorRenderer<MaidDressArmorItem> {
    public MaidDressArmorRenderer() {
        super(new MaidDressArmorModel());
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
