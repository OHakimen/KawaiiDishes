package com.hakimen.kawaiidishes.utils;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.EarsArmorModel;
import com.hakimen.kawaiidishes.item.armor.EarsArmorItem;
import com.hakimen.kawaiidishes.item.armor.IAnimationPredicate;
import mod.azure.azurelib.core.animation.Animation;
import mod.azure.azurelib.core.animation.RawAnimation;
import mod.azure.azurelib.core.object.PlayState;
import mod.azure.azurelib.model.GeoModel;

import java.util.HashMap;
import net.minecraft.util.Identifier;

public class EarUtils {

    static HashMap<AnimalType, GeoModel> earModels = new HashMap<>();

    static HashMap<AnimalType, Identifier> earOverlayTextures = new HashMap<>();

    static HashMap<AnimalType, Identifier> earSkinTexture = new HashMap<>();
    static HashMap<AnimalType, IAnimationPredicate<EarsArmorItem>> earAnimations = new HashMap<>();

    static {
    }

    public static HashMap<AnimalType, Identifier> getEarSkinTexture() {
        return earSkinTexture;
    }

    public static HashMap<AnimalType, GeoModel> getEarModels() {
        return earModels;
    }

    public static HashMap<AnimalType, Identifier> getEarOverlayTextures() {
        return earOverlayTextures;
    }

    public static HashMap<AnimalType, IAnimationPredicate<EarsArmorItem>> getEarAnimations() {
        return earAnimations;
    }

    public static void makeEars(AnimalType type, IAnimationPredicate<EarsArmorItem> state) {
        String typeName = type.name().toLowerCase();

        earModels.put(type, new EarsArmorModel(
                new Identifier(KawaiiDishes.MODID, "geo/ears/%s_ears.geo.json".formatted(typeName)),
                new Identifier(KawaiiDishes.MODID, "textures/models/armor/ears/%s_ears.png".formatted(typeName)),
                new Identifier(KawaiiDishes.MODID, "animations/ears/%s_ears.animation.json".formatted(typeName))));

        earOverlayTextures.put(type,
                new Identifier(KawaiiDishes.MODID, "textures/models/armor/ears/overlays/%s_ears.png".formatted(typeName)));

        earSkinTexture.put(type,
                new Identifier(KawaiiDishes.MODID, "textures/models/armor/ears/skin/%s_ears_skin.png".formatted(typeName)));

        earAnimations.put(type, state);
    }

    public static void makeEarsWithDefaultAnims(AnimalType type) {
        IAnimationPredicate<EarsArmorItem> animationPredicate = state -> {
            state.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        };
        makeEars(type, animationPredicate);
    }
}
