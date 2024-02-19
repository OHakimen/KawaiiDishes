package com.hakimen.kawaiidishes.item.armor;

import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;

public interface IAnimationPredicate<T extends GeoAnimatable> {
    PlayState animator(AnimationState<T> state);
}
