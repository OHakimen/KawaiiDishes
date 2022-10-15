package com.hakimen.kawaiidishes.items.armor;

import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

public class CatTailArmorItem extends GeoArmorItem implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    public String textureLocation;

    public CatTailArmorItem(String textureName) {
        super(ArmorMaterials.catTail, EquipmentSlot.CHEST, new Properties().tab(ItemRegister.cosmetics));
        textureLocation = textureName;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<CatTailArmorItem>(this,"controller",20, this::predicate));
    }
    @Override
    public boolean isFoil(ItemStack pStack) {
        return false;
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event){
        if(event.getExtraData().get(1) instanceof Player player && player.isCrouching()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("move", true));
        }else{
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
