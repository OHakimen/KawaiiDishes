package com.hakimen.kawaiidishes.items.armor;

import com.hakimen.kawaiidishes.items.ears.BunnyEars;
import com.hakimen.kawaiidishes.items.headbands.BunnyHeadband;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class BunnyTailArmorItem extends GeoArmorItem implements IAnimatable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public String textureLocation;

    public BunnyTailArmorItem(String textureName) {
        super(ArmorMaterials.tail, EquipmentSlot.CHEST, new Properties().tab(ItemRegister.cosmetics));
        textureLocation = textureName;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<BunnyTailArmorItem>(this,"controller",20, this::predicate));
    }
    @Override
    public boolean isFoil(ItemStack pStack) {
        return false;
    }
    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        var head = player.getItemBySlot(EquipmentSlot.HEAD);
        if(head.getItem() instanceof BunnyHeadband || head.getItem() instanceof BunnyEars){
            player.forceAddEffect(new MobEffectInstance(MobEffects.JUMP,14*20,1,false,false),player);
            player.forceAddEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,14*20,1,false,false),player);
            player.fallDistance = 0;
        }
        super.onArmorTick(stack, level, player);
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event){
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
