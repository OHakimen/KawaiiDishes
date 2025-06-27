package com.hakimen.kawaiidishes.utils;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.TailArmorModel;
import com.hakimen.kawaiidishes.item.armor.IAnimationPredicate;
import com.hakimen.kawaiidishes.item.armor.TailArmorItem;
import mod.azure.azurelib.constant.DataTickets;
import mod.azure.azurelib.core.animation.Animation;
import mod.azure.azurelib.core.animation.RawAnimation;
import mod.azure.azurelib.core.object.PlayState;
import mod.azure.azurelib.model.GeoModel;

import java.util.HashMap;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class TailUtils {

    static HashMap<AnimalType, GeoModel> tailModels = new HashMap<>();

    static HashMap<AnimalType, Identifier> tailOverlayTextures = new HashMap<>();

    static HashMap<AnimalType, IAnimationPredicate<TailArmorItem>> tailAnimations = new HashMap<>();

    public static HashMap<AnimalType, Identifier> getTailOverlayTextures() {
        return tailOverlayTextures;
    }

    public static HashMap<AnimalType, GeoModel> getTailModels() {
        return tailModels;
    }

    public static HashMap<AnimalType, IAnimationPredicate<TailArmorItem>> getTailAnimations() {
        return tailAnimations;
    }

    public static void makeTail(AnimalType type, IAnimationPredicate<TailArmorItem> state) {
        String typeName = type.name().toLowerCase();

        tailModels.put(type, new TailArmorModel(
                new Identifier(KawaiiDishes.MODID, "geo/tails/%s_tail.geo.json".formatted(typeName)),
                new Identifier(KawaiiDishes.MODID, "textures/models/armor/tails/%s_tail.png".formatted(typeName)),
                new Identifier(KawaiiDishes.MODID, "animations/tails/%s_tail.animation.json".formatted(typeName))));

        tailOverlayTextures.put(type,
                new Identifier(KawaiiDishes.MODID, "textures/models/armor/tails/overlays/%s_tail.png".formatted(typeName)));

        tailAnimations.put(type, state);
    }

    public static void makeTailWithDefaultAnims(AnimalType type) {
        IAnimationPredicate<TailArmorItem> animationPredicate =  state -> {
            if(state.getExtraData().get(DataTickets.ENTITY) instanceof PlayerEntity player && player.isInSneakingPose()){
                state.getController().setAnimation(RawAnimation.begin().then("wag", Animation.LoopType.LOOP));
            }else{
                state.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.HOLD_ON_LAST_FRAME));
            }
            return PlayState.CONTINUE;
        };
        makeTail(type, animationPredicate);
    }


}
