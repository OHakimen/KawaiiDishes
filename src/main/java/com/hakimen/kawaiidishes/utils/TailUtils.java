package com.hakimen.kawaiidishes.utils;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.TailArmorModel;
import com.hakimen.kawaiidishes.item.armor.IAnimationPredicate;
import com.hakimen.kawaiidishes.item.armor.TailArmorItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import software.bernie.geckolib.animation.Animation;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;

import java.util.HashMap;

public class TailUtils {

    static HashMap<AnimalType, GeoModel> tailModels = new HashMap<>();

    static HashMap<AnimalType, ResourceLocation> tailOverlayTextures = new HashMap<>();

    static HashMap<AnimalType, IAnimationPredicate<TailArmorItem>> tailAnimations = new HashMap<>();

    public static HashMap<AnimalType, ResourceLocation> getTailOverlayTextures() {
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
                ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "geo/tails/%s_tail.geo.json".formatted(typeName)),
                ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "textures/models/armor/tails/%s_tail.png".formatted(typeName)),
                ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "animations/tails/%s_tail.animation.json".formatted(typeName))));

        tailOverlayTextures.put(type,
                ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "textures/models/armor/tails/overlays/%s_tail.png".formatted(typeName)));

        tailAnimations.put(type, state);
    }

    public static void makeTailWithDefaultAnims(AnimalType type) {
        IAnimationPredicate<TailArmorItem> animationPredicate =  state -> {
            if(state.getExtraData().get(DataTickets.ENTITY) instanceof Player player && player.isCrouching()){
                state.getController().setAnimation(RawAnimation.begin().then("wag", Animation.LoopType.LOOP));
            }else{
                state.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.HOLD_ON_LAST_FRAME));
            }
            return PlayState.CONTINUE;
        };
        makeTail(type, animationPredicate);
    }


}
