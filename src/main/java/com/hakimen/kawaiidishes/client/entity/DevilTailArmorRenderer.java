package com.hakimen.kawaiidishes.client.entity;

import com.hakimen.kawaiidishes.items.armor.BunnyTailArmorItem;
import com.hakimen.kawaiidishes.items.armor.DevilTailArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class DevilTailArmorRenderer extends GeoArmorRenderer<DevilTailArmorItem> {
    public DevilTailArmorRenderer() {
        super(new DevilTailArmorModel());
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
