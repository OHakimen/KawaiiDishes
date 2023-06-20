package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.client.data.ClientTailWagData;
import com.hakimen.kawaiidishes.items.armor.GenericGeoArmorItem;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

import java.util.function.Function;

public class AnimationsRegister {
    public static final Function<AnimationState<GenericGeoArmorItem>, PlayState> NULL = (event) -> {

        return PlayState.CONTINUE;
    };

    public static final Function<AnimationState<GenericGeoArmorItem>, PlayState> catTail = (event) -> {
        if((event.getExtraData().get(DataTickets.ENTITY) instanceof Player player && ClientTailWagData.getState(player.getUUID())) || (!(event.getExtraData().get(DataTickets.ENTITY) instanceof ArmorStand) && !(event.getExtraData().get(DataTickets.ENTITY) instanceof Player))){
            event.getController().setAnimation(RawAnimation.begin().then("move", Animation.LoopType.LOOP));
        } else {
            event.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;
    };

    public static final Function<AnimationState<GenericGeoArmorItem>, PlayState> foxTail = (event) -> {

        if((event.getExtraData().get(DataTickets.ENTITY) instanceof Player player && ClientTailWagData.getState(player.getUUID())) || (!(event.getExtraData().get(DataTickets.ENTITY) instanceof ArmorStand) && !(event.getExtraData().get(DataTickets.ENTITY) instanceof Player))){
            event.getController().setAnimation(RawAnimation.begin().then("move", Animation.LoopType.LOOP));
        }  else {
            event.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;
    };

    public static final Function<AnimationState<GenericGeoArmorItem>, PlayState> devilTail = (event) -> {
        if((event.getExtraData().get(DataTickets.ENTITY) instanceof Player player && ClientTailWagData.getState(player.getUUID())) || (!(event.getExtraData().get(DataTickets.ENTITY) instanceof ArmorStand) && !(event.getExtraData().get(DataTickets.ENTITY) instanceof Player))){
            event.getController().setAnimation(RawAnimation.begin().then("tail_wag", Animation.LoopType.LOOP));
        }  else {
            event.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;
    };

}
