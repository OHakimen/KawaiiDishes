package com.hakimen.kawaiidishes.utils;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.MaidDressesWithTailArmorModel;
import com.hakimen.kawaiidishes.item.armor.IAnimationPredicate;
import com.hakimen.kawaiidishes.item.armor.MaidDressesWithTailArmorItem;
import com.hakimen.kawaiidishes.item.armor.TailArmorItem;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.model.GeoModel;

import java.util.HashMap;
import java.util.function.Supplier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class MaidDressesWithTailUtils {
    static HashMap<AnimalType, GeoModel> tailedDressesModels = new HashMap<>();
    static HashMap<AnimalType, Identifier> tailedDressesTailOverlay = new HashMap<>();
    static HashMap<AnimalType, Identifier> tailedDressesTailBase = new HashMap<>();
    static HashMap<AnimalType, IAnimationPredicate<MaidDressesWithTailArmorItem>> tailedDressesAnimation = new HashMap<>();

    static HashMap<AnimalType, Supplier<MaidDressesWithTailArmorItem>> tailedDressItems = new HashMap<>();
    static HashMap<AnimalType, Supplier<TailArmorItem>> tailItems = new HashMap<>();


    public static HashMap<AnimalType, GeoModel> getTailedDressesModels() {
        return tailedDressesModels;
    }

    public static HashMap<AnimalType, Identifier> getTailedDressesTailOverlay() {
        return tailedDressesTailOverlay;
    }

    public static HashMap<AnimalType, IAnimationPredicate<MaidDressesWithTailArmorItem>> getTailedDressesAnimation() {
        return tailedDressesAnimation;
    }

    public static HashMap<AnimalType, Identifier> getTailedDressesTailBase() {
        return tailedDressesTailBase;
    }

    public static HashMap<AnimalType, Supplier<MaidDressesWithTailArmorItem>> getTailedDressItems() {
        return tailedDressItems;
    }

    public static void makeDressWithTailWithDefaultAnims(AnimalType type,Supplier<MaidDressesWithTailArmorItem> item){

        IAnimationPredicate<MaidDressesWithTailArmorItem> animation = state -> {
            if (state.getExtraData().get(DataTickets.ENTITY) instanceof PlayerEntity player && player.isInSneakingPose()) {
                state.getController().setAnimation(RawAnimation.begin().then("tail_wag", Animation.LoopType.LOOP));
            } else {
                state.getController().setAnimation(RawAnimation.begin().then("tail_idle", Animation.LoopType.HOLD_ON_LAST_FRAME));
            }
            return PlayState.CONTINUE;
        };

        makeDressWithTail(type, animation, item);
    }

    public static void makeDressWithTail(AnimalType type, IAnimationPredicate<MaidDressesWithTailArmorItem> state, Supplier<MaidDressesWithTailArmorItem> item){
        tailedDressItems.put(type, item);

        String typeName = type.name().toLowerCase();
        tailedDressesModels.put(type, new MaidDressesWithTailArmorModel(
                new Identifier(KawaiiDishes.MODID, "geo/maid_dresses_with_tail/maid_dress_%s_tail.geo.json".formatted(typeName)),
                new Identifier(KawaiiDishes.MODID, "textures/models/armor/maid_dresses_with_tail/dress/%s/maid_dress.png".formatted(typeName)),
                new Identifier(KawaiiDishes.MODID, "animations/maid_dresses_with_tail/maid_dress_%s_tail.animation.json".formatted(typeName))
        ));

        tailedDressesTailOverlay.put(type, new Identifier(KawaiiDishes.MODID,"textures/models/armor/maid_dresses_with_tail/tail_overlays/%s_tail.png".formatted(typeName)));
        tailedDressesTailBase.put(type,  new Identifier(KawaiiDishes.MODID,"textures/models/armor/maid_dresses_with_tail/tail_base/%s_tail.png".formatted(typeName)));

        tailedDressesAnimation.put(type, state);
    }

}
