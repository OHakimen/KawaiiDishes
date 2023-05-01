package com.hakimen.kawaiidishes.items.armor;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.util.TriConsumer;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.function.Function;

public class GenericGeoArmorItem extends GeoArmorItem implements IAnimatable {

    public ResourceLocation textureLocation;
    public ResourceLocation modelLocation;
    public ResourceLocation animationLocation;

    public Function<AnimationEvent<GenericGeoArmorItem>, PlayState> animationControlling;
    public TriConsumer<ItemStack,Level,Player> armorTick;

    public boolean canStandOnPowderSnow;
    public boolean makesPiglinsNeutral;

    public GenericGeoArmorItem setCanStandOnPowderSnow(boolean canStandOnPowderSnow) {
        this.canStandOnPowderSnow = canStandOnPowderSnow;
        return this;
    }

    public GenericGeoArmorItem setMakesPiglinsNeutral(boolean makesPiglinsNeutral) {
        this.makesPiglinsNeutral = makesPiglinsNeutral;
        return this;
    }

    @Override
    public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
        return canStandOnPowderSnow;
    }

    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
        return makesPiglinsNeutral;
    }

    private AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public GenericGeoArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder, String textureLocation, String modelLocation, String animationLocation, Function<AnimationEvent<GenericGeoArmorItem>,PlayState> animationControlling, TriConsumer<ItemStack, Level, Player> armorTick) {
        super(materialIn, slot, builder.tab(ItemRegister.cosmetics));
        this.textureLocation = new ResourceLocation(KawaiiDishes.modId,  "textures/models/armor/"+textureLocation);
        this.modelLocation = new ResourceLocation(KawaiiDishes.modId,    "geo/"+modelLocation);
        this.animationLocation = new ResourceLocation(KawaiiDishes.modId,"animations/"+animationLocation);
        this.animationControlling = animationControlling;
        this.armorTick = armorTick;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<GenericGeoArmorItem>(this,"controller",0, (a)-> animationControlling.apply(a)));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        armorTick.accept(stack,level,player);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return false;
    }
}
