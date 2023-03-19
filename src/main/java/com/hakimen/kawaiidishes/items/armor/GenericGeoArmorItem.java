package com.hakimen.kawaiidishes.items.armor;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.GenericGeoRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.apache.logging.log4j.util.TriConsumer;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;
import java.util.function.Function;

public class GenericGeoArmorItem extends ArmorItem implements GeoItem {

    public ResourceLocation textureLocation;
    public ResourceLocation modelLocation;
    public ResourceLocation animationLocation;

    public Function<AnimationState<GenericGeoArmorItem>,PlayState> animationControlling;
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

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public GenericGeoArmorItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties, String textureLocation, String modelLocation, String animationLocation, Function<AnimationState<GenericGeoArmorItem>,PlayState> animationControlling, TriConsumer<ItemStack, Level, Player> armorTick) {
        super(pMaterial, pSlot, pProperties);
        this.textureLocation = new ResourceLocation(KawaiiDishes.modId,  "textures/models/armor/"+textureLocation);
        this.modelLocation = new ResourceLocation(KawaiiDishes.modId,    "geo/"+modelLocation);
        this.animationLocation = new ResourceLocation(KawaiiDishes.modId,"animations/"+animationLocation);
        this.animationControlling = animationControlling;
        this.armorTick = armorTick;
    }

    @Override
    public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
        return canStandOnPowderSnow;
    }

    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
        return makesPiglinsNeutral;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null)
                    this.renderer = new GenericGeoRenderer(modelLocation);

                // This prepares our GeoArmorRenderer for the current render frame.
                // These parameters may be null however, so we don't do anything further with them
                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);

                return this.renderer;
            }
        });
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return false;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<GenericGeoArmorItem>(this,"controller",20, this::predicate));
    }

    private PlayState predicate(AnimationState<GenericGeoArmorItem> state) {
        return animationControlling.apply(state);
    }


    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        armorTick.accept(stack,level,player);
        super.onArmorTick(stack, level, player);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
