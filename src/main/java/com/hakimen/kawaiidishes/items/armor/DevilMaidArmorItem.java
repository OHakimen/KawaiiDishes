package com.hakimen.kawaiidishes.items.armor;

import com.hakimen.kawaiidishes.items.BunnyEars;
import com.hakimen.kawaiidishes.items.BunnyHeadband;
import com.hakimen.kawaiidishes.registry.EffectRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class DevilMaidArmorItem extends GeoArmorItem implements IAnimatable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public String textureLocation;

    public DevilMaidArmorItem(String textureName, Item dress) {
        super(ArmorMaterials.tailedDress, EquipmentSlot.CHEST, new Properties().tab(ItemRegister.cosmetics)
                .craftRemainder(dress));
        textureLocation = textureName;
    }
    @Override
    public boolean isFoil(ItemStack pStack) {
        return false;
    }
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<DevilMaidArmorItem>(this,"controller",0, this::predicate));
    }
    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if(player.getInventory().getArmor(EquipmentSlot.CHEST.getIndex()).getItem() instanceof DevilMaidArmorItem){
            player.forceAddEffect(new MobEffectInstance(EffectRegister.kawaiiEffect.get(),14*20,0,false,false),player);
        }
        super.onArmorTick(stack, level, player);
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event){
        if((event.getExtraData().get(1) instanceof Player player && player.isCrouching())||(!(event.getExtraData().get(1) instanceof ArmorStand) && !(event.getExtraData().get(1) instanceof Player))){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("tail_wag", ILoopType.EDefaultLoopTypes.LOOP));
        }else{
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
