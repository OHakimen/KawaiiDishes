package com.hakimen.kawaiidishes.items.armor;

import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.GeckoLib;
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

public class ThighHighsArmorItem extends GeoArmorItem implements IAnimatable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public String textureLocation;

    public ThighHighsArmorItem(String textureName,EquipmentSlot slot) {
        super(ArmorMaterials.maidDress, slot, new Properties().tab(ItemRegister.cosmetics));
        textureLocation = textureName;
    }

    @Override
    public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
        if(wearer.getItemBySlot(EquipmentSlot.FEET).is(stack.getItem())){
            return true;
        }
        return super.canWalkOnPowderedSnow(stack, wearer);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {

        super.onArmorTick(stack, level, player);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return false;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<ThighHighsArmorItem>(this,"controller",0, this::predicate));
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event){
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
