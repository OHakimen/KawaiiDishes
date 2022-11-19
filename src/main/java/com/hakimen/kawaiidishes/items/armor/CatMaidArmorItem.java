package com.hakimen.kawaiidishes.items.armor;

import com.hakimen.kawaiidishes.registry.EffectRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PiglinModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.network.protocol.status.ServerStatus;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

import java.util.ArrayList;

import static net.minecraft.client.Minecraft.getInstance;

public class CatMaidArmorItem extends GeoArmorItem implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    public String textureLocation;

    public CatMaidArmorItem(String textureName) {
        super(ArmorMaterials.catMaidDress, EquipmentSlot.CHEST, new Properties().tab(ItemRegister.cosmetics));
        textureLocation = textureName;
    }
    @Override
    public boolean isFoil(ItemStack pStack) {
        return false;
    }
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<CatMaidArmorItem>(this,"controller",0, this::predicate));
    }
    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if(player.getInventory().getArmor(EquipmentSlot.CHEST.getIndex()).getItem() instanceof CatMaidArmorItem){
            player.forceAddEffect(new MobEffectInstance(EffectRegister.nekoEffect.get(),14*20,0,false,false),player);
        }
        super.onArmorTick(stack, level, player);
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event){
        if((event.getExtraData().get(1) instanceof Player player && player.isCrouching())||(!(event.getExtraData().get(1) instanceof ArmorStand) && !(event.getExtraData().get(1) instanceof Player))){
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
