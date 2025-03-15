package com.hakimen.kawaiidishes.item.armor;

import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;

public interface IAnimationPredicate<T extends GeoAnimatable> {
    PlayState animator(AnimationState<T> state);
}
