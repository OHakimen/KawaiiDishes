package com.hakimen.kawaiidishes.utils;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.MaidDressesWithTailArmorModel;
import com.hakimen.kawaiidishes.item.armor.IAnimationPredicate;
import com.hakimen.kawaiidishes.item.armor.MaidDressesWithTailArmorItem;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.model.GeoModel;

import java.util.HashMap;

public class MaidDressesWithTailUtils {
    static HashMap<AnimalType, GeoModel> tailedDressesModels = new HashMap<>();
    static HashMap<AnimalType, ResourceLocation> tailedDressesTailOverlay = new HashMap<>();
    static HashMap<AnimalType, ResourceLocation> tailedDressesTailBase = new HashMap<>();
    static HashMap<AnimalType, IAnimationPredicate<MaidDressesWithTailArmorItem>> tailedDressesAnimation = new HashMap<>();

    static HashMap<AnimalType, DeferredHolder<Item,MaidDressesWithTailArmorItem>> tailedDressItems = new HashMap<>();


    public static HashMap<AnimalType, GeoModel> getTailedDressesModels() {
        return tailedDressesModels;
    }

    public static HashMap<AnimalType, ResourceLocation> getTailedDressesTailOverlay() {
        return tailedDressesTailOverlay;
    }

    public static HashMap<AnimalType, IAnimationPredicate<MaidDressesWithTailArmorItem>> getTailedDressesAnimation() {
        return tailedDressesAnimation;
    }

    public static HashMap<AnimalType, ResourceLocation> getTailedDressesTailBase() {
        return tailedDressesTailBase;
    }

    public static HashMap<AnimalType, DeferredHolder<Item, MaidDressesWithTailArmorItem>> getTailedDressItems() {
        return tailedDressItems;
    }

    public static void makeDressWithTailWithDefaultAnims(AnimalType type, DeferredHolder<Item, MaidDressesWithTailArmorItem> item){

        IAnimationPredicate<MaidDressesWithTailArmorItem> animation = state -> {
            if (state.getExtraData().get(DataTickets.ENTITY) instanceof Player player && player.isCrouching()) {
                state.getController().setAnimation(RawAnimation.begin().then("tail_wag", Animation.LoopType.LOOP));
            } else {
                state.getController().setAnimation(RawAnimation.begin().then("tail_idle", Animation.LoopType.HOLD_ON_LAST_FRAME));
            }
            return PlayState.CONTINUE;
        };

        makeDressWithTail(type, animation, item);
    }

    public static void makeDressWithTail(AnimalType type, IAnimationPredicate<MaidDressesWithTailArmorItem> state, DeferredHolder<Item, MaidDressesWithTailArmorItem> item){
        tailedDressItems.put(type, item);

        String typeName = type.name().toLowerCase();
        tailedDressesModels.put(type, new MaidDressesWithTailArmorModel(
                new ResourceLocation(KawaiiDishes.MODID, "geo/maid_dresses_with_tail/maid_dress_%s_tail.geo.json".formatted(typeName)),
                new ResourceLocation(KawaiiDishes.MODID, "textures/models/armor/maid_dresses_with_tail/dress/%s/maid_dress.png".formatted(typeName)),
                new ResourceLocation(KawaiiDishes.MODID, "animations/maid_dresses_with_tail/maid_dress_%s_tail.animation.json".formatted(typeName))
        ));

        tailedDressesTailOverlay.put(type, new ResourceLocation(KawaiiDishes.MODID,"textures/models/armor/maid_dresses_with_tail/tail_overlays/%s_tail.png".formatted(typeName)));
        tailedDressesTailBase.put(type,  new ResourceLocation(KawaiiDishes.MODID,"textures/models/armor/maid_dresses_with_tail/tail_base/%s_tail.png".formatted(typeName)));

        tailedDressesAnimation.put(type, state);
    }

}
