package com.hakimen.kawaiidishes.items.armor;

import com.hakimen.kawaiidishes.registry.EffectRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.AABB;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

import java.util.List;

public class MaidDressArmorItem extends GeoArmorItem implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    public String textureLocation;

    public MaidDressArmorItem(String textureName) {
        super(ArmorMaterials.maidDress, EquipmentSlot.CHEST, new Properties().tab(ItemRegister.cosmetics));
        textureLocation = textureName;
    }


    @Override
    public boolean isFoil(ItemStack pStack) {
        return false;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if(player.getInventory().getArmor(EquipmentSlot.CHEST.getIndex()).getItem() instanceof MaidDressArmorItem){
            player.forceAddEffect(new MobEffectInstance(EffectRegister.kawaiiEffect.get(),14*20,0,false,false),player);
        }
        super.onArmorTick(stack, level, player);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<MaidDressArmorItem>(this,"controller",0, this::predicate));
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event){
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
