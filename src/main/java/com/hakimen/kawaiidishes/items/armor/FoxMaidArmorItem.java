package com.hakimen.kawaiidishes.items.armor;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class FoxMaidArmorItem extends MaidDressArmorItem {
    private AnimationFactory factory = new AnimationFactory(this);

    public FoxMaidArmorItem(String textureName) {
        super(textureName);
    }
    @Override
    public boolean isFoil(ItemStack pStack) {
        return false;
    }
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<FoxMaidArmorItem>(this,"controller",0, this::predicate));
    }
    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        super.onArmorTick(stack, level, player);
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event){
        if(event.getExtraData().get(1) instanceof Player player && player.isCrouching()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("move", true));
        }else{
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        }return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
