package com.hakimen.kawaiidishes.client.init;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.item.armor.MaidDressArmorItem;
import com.hakimen.kawaiidishes.item.armor.MaidDressesWithTailArmorItem;
import com.hakimen.kawaiidishes.item.armor.TailArmorItem;
import com.unascribed.ears.api.EarsFeatureType;
import com.unascribed.ears.api.EarsStateType;
import com.unascribed.ears.api.OverrideResult;
import com.unascribed.ears.api.registry.EarsInhibitorRegistry;
import com.unascribed.ears.api.registry.EarsStateOverriderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRenderEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EarsCompatibilityInitializer {
    static boolean isMaidOutfit(Item item) {
        return (item instanceof MaidDressesWithTailArmorItem || item instanceof MaidDressArmorItem);
    }

    public static void init() {
        if (FabricLoader.getInstance().isModLoaded("ears")) {
            EarsInhibitorRegistry.register(KawaiiDishes.MODID, (type, peer) -> {
                PlayerEntity player = (PlayerEntity) peer;
                for (ItemStack slot : player.getArmorItems()) {
                    Item item = slot.getItem();
                    if ((item instanceof TailArmorItem || item instanceof MaidDressesWithTailArmorItem) && type == EarsFeatureType.TAIL) {
                        return true;
                    }

                }

                return false;
            });

            EarsStateOverriderRegistry.register(KawaiiDishes.MODID, (type, peer) -> {
                PlayerEntity player = (PlayerEntity) peer;
                for (ItemStack slot : player.getArmorItems()) {
                    Item item = slot.getItem();
                    if ((item instanceof TailArmorItem || isMaidOutfit(item)) && type == EarsStateType.WEARING_CHESTPLATE) {
                        return OverrideResult.FALSE;
                    }
                }

                return OverrideResult.DEFAULT;
            });
        }
    }
}
