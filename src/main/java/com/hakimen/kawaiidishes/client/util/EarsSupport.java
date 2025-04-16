package com.hakimen.kawaiidishes.client.util;

import com.unascribed.ears.api.features.EarsFeatures;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

/**
 * @author Ampflower
 **/
public final class EarsSupport {
	public static final boolean enabled = FabricLoader.getInstance().isModLoaded("ears");

	private static final float stockChestSize = 15f * Mth.DEG_TO_RAD;

	public static float getChestSize(Entity entity) {
		if (EarsSupport.enabled && entity instanceof Player player) {
			final var features = EarsFeatures.getById(player.getUUID());

			if (features == null) {
				return stockChestSize;
			} else {
				return Math.max(features.chestSize, stockChestSize);
			}
		} else {
			return stockChestSize;
		}
	}
}
