package com.hakimen.kawaiidishes.client.entity.maid_dresses;

import com.hakimen.kawaiidishes.items.armor.CatMaidArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class CatMaidDressArmorRenderer extends GeoArmorRenderer<CatMaidArmorItem> {
    public CatMaidDressArmorRenderer() {
        super(new CatMaidDressArmorModel());
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
