package com.hakimen.kawaiidishes.mixins;

import com.hakimen.kawaiidishes.item.armor.TailArmorItem;
import com.unascribed.ears.common.render.IndirectEarsRenderDelegate;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets = "com.unascribed.ears.EarsFeatureRenderer$1")
public class EarsTailCompatMixin {
    @Inject(method = "isWearingChestplate", at = @At("HEAD"), cancellable = true)
    public void compat(CallbackInfoReturnable<Boolean> cir) {
        IndirectEarsRenderDelegate delegate = (IndirectEarsRenderDelegate) (Object) this;
        LocalPlayer player = (LocalPlayer) delegate.getPeer();
        if (player.getInventory().getArmor(2).getItem() instanceof TailArmorItem) {
            cir.setReturnValue(false);
        }
    }
}
