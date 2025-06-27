package com.hakimen.kawaiidishes.item.armor;

import mod.azure.azurelib.core.animatable.GeoAnimatable;
import mod.azure.azurelib.core.animation.AnimationState;
import mod.azure.azurelib.core.object.PlayState;

public interface IAnimationPredicate<T extends GeoAnimatable> {
    PlayState animator(AnimationState<T> state);
}
