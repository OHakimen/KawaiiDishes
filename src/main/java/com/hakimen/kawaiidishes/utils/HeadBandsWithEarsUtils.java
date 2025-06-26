package com.hakimen.kawaiidishes.utils;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.HeadBandsWithEarsModel;
import com.hakimen.kawaiidishes.item.armor.HeadBandWithEarsArmorItem;
import com.hakimen.kawaiidishes.item.armor.IAnimationPredicate;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.model.GeoModel;

import java.util.HashMap;
import java.util.function.Supplier;
import net.minecraft.resources.ResourceLocation;

public class HeadBandsWithEarsUtils {
    static HashMap<AnimalType, GeoModel> earedHeadBandsModels = new HashMap<>();
    static HashMap<AnimalType, ResourceLocation> earedHeadBandsEarsOverlay = new HashMap<>();
    static HashMap<AnimalType, ResourceLocation> earedHeadBandsEarsBase = new HashMap<>();
    static HashMap<AnimalType, ResourceLocation> earedHeadBandsEarsSkin = new HashMap<>();
    static HashMap<AnimalType, IAnimationPredicate<HeadBandWithEarsArmorItem>> earedHeadBandsAnimations = new HashMap<>();

    static HashMap<AnimalType, Supplier<HeadBandWithEarsArmorItem>> earedHeadBandsItems = new HashMap<>();


    public static HashMap<AnimalType, GeoModel> getEaredHeadBandsModels() {
        return earedHeadBandsModels;
    }

    public static HashMap<AnimalType, ResourceLocation> getEaredHeadBandsEarsOverlay() {
        return earedHeadBandsEarsOverlay;
    }

    public static HashMap<AnimalType, ResourceLocation> getEaredHeadBandsEarsBase() {
        return earedHeadBandsEarsBase;
    }

    public static HashMap<AnimalType, IAnimationPredicate<HeadBandWithEarsArmorItem>> getEaredHeadBandsAnimations() {
        return earedHeadBandsAnimations;
    }

    public static HashMap<AnimalType, ResourceLocation> getEaredHeadBandsEarsSkin() {
        return earedHeadBandsEarsSkin;
    }

    public static HashMap<AnimalType, Supplier<HeadBandWithEarsArmorItem>> getEaredHeadBandsItems() {
        return earedHeadBandsItems;
    }

    public static void makeHeadbandWithEars(AnimalType type, IAnimationPredicate<HeadBandWithEarsArmorItem> state, Supplier<HeadBandWithEarsArmorItem> item) {

        earedHeadBandsItems.put(type, item);

        String typeName = type.name().toLowerCase();

        earedHeadBandsModels.put(type, new HeadBandsWithEarsModel(
                new ResourceLocation(KawaiiDishes.MODID, "geo/head_bands_with_ears/head_band_%s_ears.geo.json".formatted(typeName)),
                new ResourceLocation(KawaiiDishes.MODID, "textures/models/armor/head_bands_with_ears/head_bands/%s/head_band.png".formatted(typeName)),
                new ResourceLocation(KawaiiDishes.MODID, "animations/ears/%s_ears.animation.json".formatted(typeName))
        ));

        earedHeadBandsEarsBase.put(type, new ResourceLocation(KawaiiDishes.MODID, "textures/models/armor/head_bands_with_ears/ears_base/%s_ears.png".formatted(typeName)));
        earedHeadBandsEarsOverlay.put(type, new ResourceLocation(KawaiiDishes.MODID, "textures/models/armor/head_bands_with_ears/ears_overlays/%s_ears.png".formatted(typeName)));
        earedHeadBandsEarsSkin.put(type, new ResourceLocation(KawaiiDishes.MODID, "textures/models/armor/head_bands_with_ears/ears_skin/%s_ears.png".formatted(typeName)));

        earedHeadBandsAnimations.put(type, state);
    }

    public static void makeHeadbandWithEarsDefaultAnims(AnimalType type,Supplier<HeadBandWithEarsArmorItem> item) {
        IAnimationPredicate<HeadBandWithEarsArmorItem> animation = state -> {
            state.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        };

        makeHeadbandWithEars(type, animation, item);
    }
}
