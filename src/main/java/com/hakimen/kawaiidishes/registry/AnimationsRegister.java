package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.items.armor.GenericGeoArmorItem;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

import java.util.function.Function;

public class AnimationsRegister {
    public static final Function<AnimationEvent<GenericGeoArmorItem>, PlayState> NULL = (event) -> {

        return PlayState.CONTINUE;
    };

    public static final Function<AnimationEvent<GenericGeoArmorItem>,PlayState> catTail = (event) -> {
        if((event.getExtraData().get(1) instanceof Player player && player.isCrouching())||(!(event.getExtraData().get(1) instanceof ArmorStand) && !(event.getExtraData().get(1) instanceof Player))){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("move", ILoopType.EDefaultLoopTypes.LOOP));
        }else{
            event.getController().setAnimation( new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
        }
        return PlayState.CONTINUE;
    };

    public static final Function<AnimationEvent<GenericGeoArmorItem>,PlayState> foxTail = (event) -> {

        if((event.getExtraData().get(1) instanceof Player player && player.isCrouching())||(!(event.getExtraData().get(1) instanceof ArmorStand) && !(event.getExtraData().get(1) instanceof Player))){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("move", ILoopType.EDefaultLoopTypes.LOOP));
        }else{
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
        }
        return PlayState.CONTINUE;
    };

    public static final Function<AnimationEvent<GenericGeoArmorItem>, PlayState> devilTail = (event) -> {

        if((event.getExtraData().get(1) instanceof Player player && player.isCrouching())||(!(event.getExtraData().get(1) instanceof ArmorStand) && !(event.getExtraData().get(1) instanceof Player))){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("tail_wag", ILoopType.EDefaultLoopTypes.LOOP));
        }else{
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
        }

        return PlayState.CONTINUE;
    };
}
