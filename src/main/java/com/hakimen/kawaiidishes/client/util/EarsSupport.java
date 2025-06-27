package com.hakimen.kawaiidishes.client.util;

import com.unascribed.ears.api.features.EarsFeatures;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

/**
 * @author Ampflower
 **/
public final class EarsSupport {
	public static final boolean enabled = FabricLoader.getInstance().isModLoaded("ears");

	private static final float stockChestSize = 15f * MathHelper.RADIANS_PER_DEGREE;

	public static float getChestSize(Entity entity) {
		if (EarsSupport.enabled && entity instanceof PlayerEntity player) {
			final var features = EarsFeatures.getById(player.getUuid());

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
