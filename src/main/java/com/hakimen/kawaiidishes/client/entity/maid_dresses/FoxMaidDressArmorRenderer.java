package com.hakimen.kawaiidishes.client.entity.maid_dresses;

import com.hakimen.kawaiidishes.items.armor.FoxMaidArmorItem;
import com.hakimen.kawaiidishes.items.armor.MaidDressArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class FoxMaidDressArmorRenderer extends GeoArmorRenderer<FoxMaidArmorItem> {
    public FoxMaidDressArmorRenderer() {
        super(new FoxMaidDressArmorModel());
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
