package com.hakimen.kawaiidishes.item.armor;

import com.hakimen.kawaiidishes.client.entity.renderers.HeadBandsWithEarsRender;
import com.hakimen.kawaiidishes.item.IFourColorDyeableItem;
import com.hakimen.kawaiidishes.utils.AnimalType;
import com.hakimen.kawaiidishes.utils.HeadBandsWithEarsUtils;
import com.hakimen.kawaiidishes.utils.ItemUtils;
import org.jetbrains.annotations.Nullable;
import mod.azure.azurelib.animatable.GeoItem;
import mod.azure.azurelib.animatable.client.RenderProvider;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.animation.AnimationState;
import mod.azure.azurelib.core.object.PlayState;
import mod.azure.azurelib.renderer.GeoArmorRenderer;
import mod.azure.azurelib.util.AzureLibUtil;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import static com.hakimen.kawaiidishes.utils.item.ArmorUtils.applyEnchantmentEffects;

public class HeadBandWithEarsArmorItem extends GeoArmorItem implements IFourColorDyeableItem, IAnimationPredicate<HeadBandWithEarsArmorItem>{

    public static final String hasPrimaryOverlay = "HasPrimaryOverlay";
    public static final String hasSecondaryOverlay = "HasSecondaryOverlay";

    AnimalType earsType;

    private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

    public HeadBandWithEarsArmorItem(ArmorMaterial pMaterial, Type pType, Settings pProperties, AnimalType tailType) {
        super(pMaterial, pType, pProperties);
        this.earsType = tailType;
    }

    @Override
    public void appendTooltip(ItemStack pStack, @Nullable World pLevel, List<Text> pTooltipComponents, TooltipContext pIsAdvanced) {
        if((hasPrimaryBaseColor(pStack) || hasPrimaryOverlayColor(pStack) || hasSecondaryBaseColor(pStack) || hasSecondaryOverlay(pStack)) && !pIsAdvanced.isAdvanced()){
            pTooltipComponents.add(Text.translatable("item.dyed").fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
        }else if((hasPrimaryBaseColor(pStack) || hasPrimaryOverlayColor(pStack) || hasSecondaryBaseColor(pStack) || hasSecondaryOverlay(pStack)) && !pIsAdvanced.isCreative()){
            if(hasPrimaryBaseColor(pStack)){
                pTooltipComponents.add(Text.translatable("item.kawaiidishes.head_band_color", "0x"+Integer.toString(getPrimaryBaseColor(pStack),16).toUpperCase()).fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
            }
            if(hasPrimaryOverlayColor(pStack)){
                pTooltipComponents.add(Text.translatable("item.kawaiidishes.head_band_decoration_color", "0x"+Integer.toString(getPrimaryOverlayColor(pStack),16).toUpperCase()).fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
            }
            if(hasSecondaryBaseColor(pStack)){
                pTooltipComponents.add(Text.translatable("item.kawaiidishes.ears_color", "0x"+Integer.toString(getSecondaryBaseColor(pStack),16).toUpperCase()).fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
            }
            if(hasSecondaryOverlay(pStack)){
                pTooltipComponents.add(Text.translatable("item.kawaiidishes.ears_decoration_color", "0x"+Integer.toString(getSecondaryOverlayColor(pStack),16).toUpperCase()).fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
            }
        }else{

            pTooltipComponents.add(Text.translatable("item.kawaiidishes.dyeable").fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
        }
    }

    @Override
    public boolean hasGlint(ItemStack p_41453_) {
        return false;
    }

    @Override
    public void inventoryTick(ItemStack itemStack, World level, Entity entity, int slot, boolean bl) {
        if(ItemUtils.isItemOnArmorSlot(slot) && entity instanceof PlayerEntity player){
            applyEnchantmentEffects(itemStack, level, player);
        }
        super.inventoryTick(itemStack, level, entity, slot, bl);
    }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public BipedEntityModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, BipedEntityModel<LivingEntity> original) {
                if (this.renderer == null)
                    this.renderer = new HeadBandsWithEarsRender(HeadBandsWithEarsUtils.getEaredHeadBandsModels().get(earsType), livingEntity.getEquippedStack(EquipmentSlot.HEAD));


                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        });
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        return renderProvider;
    }

    public AnimalType getEarsType() {
        return earsType;
    }

    @Override
    public boolean hasPrimaryOverlay(ItemStack stack) {
        return stack.getOrCreateNbt().contains(hasPrimaryOverlay) && stack.getOrCreateNbt().getBoolean(hasPrimaryOverlay);
    }

    @Override
    public boolean hasSecondaryOverlay(ItemStack stack) {
        return stack.getOrCreateNbt().contains(hasSecondaryOverlay) && stack.getOrCreateNbt().getBoolean(hasSecondaryOverlay);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "head_band_controller", 0, this::animator));
        controllers.add(new AnimationController<>(this, "ears_controller", 0, HeadBandsWithEarsUtils.getEaredHeadBandsAnimations().get(earsType)::animator));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public PlayState animator(AnimationState<HeadBandWithEarsArmorItem> state) {
        return PlayState.CONTINUE;
    }
}
