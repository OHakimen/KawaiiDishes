package com.hakimen.kawaiidishes.client.entity.maid_dresses;

import com.hakimen.kawaiidishes.items.armor.BunnyMaidArmorItem;
import com.hakimen.kawaiidishes.items.armor.CatMaidArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class BunnyMaidDressArmorRenderer extends GeoArmorRenderer<BunnyMaidArmorItem> {
    public BunnyMaidDressArmorRenderer() {
        super(new BunnyMaidDressArmorModel());
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
