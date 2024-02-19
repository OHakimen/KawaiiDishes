package com.hakimen.kawaiidishes.item.armor;

import com.hakimen.kawaiidishes.client.entity.renderers.HeadBandsWithEarsRender;
import com.hakimen.kawaiidishes.enchantments.CatAuraEnchant;
import com.hakimen.kawaiidishes.item.IFourColorDyeableItem;
import com.hakimen.kawaiidishes.registry.EnchantmentRegister;
import com.hakimen.kawaiidishes.utils.AnimalType;
import com.hakimen.kawaiidishes.utils.HeadBandsWithEarsUtils;
import com.hakimen.kawaiidishes.utils.item.EnchantUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Consumer;

import static com.hakimen.kawaiidishes.utils.item.ArmorUtils.applyEnchantmentEffects;

public class HeadBandWithEarsArmorItem extends GeoArmorItem implements IFourColorDyeableItem, IAnimationPredicate<HeadBandWithEarsArmorItem>{

    public static final String hasPrimaryOverlay = "HasPrimaryOverlay";
    public static final String hasSecondaryOverlay = "HasSecondaryOverlay";

    AnimalType earsType;


    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public HeadBandWithEarsArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties, AnimalType tailType) {
        super(pMaterial, pType, pProperties);
        this.earsType = tailType;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if((hasPrimaryBaseColor(pStack) || hasPrimaryOverlayColor(pStack) || hasSecondaryBaseColor(pStack) || hasSecondaryOverlay(pStack)) && !pIsAdvanced.isAdvanced()){
            pTooltipComponents.add(Component.translatable("item.dyed").withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
        }else if((hasPrimaryBaseColor(pStack) || hasPrimaryOverlayColor(pStack) || hasSecondaryBaseColor(pStack) || hasSecondaryOverlay(pStack)) && !pIsAdvanced.isCreative()){
            if(hasPrimaryBaseColor(pStack)){
                pTooltipComponents.add(Component.translatable("item.kawaiidishes.head_band_color", "0x"+Integer.toString(getPrimaryBaseColor(pStack),16).toUpperCase()).withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
            }
            if(hasPrimaryOverlayColor(pStack)){
                pTooltipComponents.add(Component.translatable("item.kawaiidishes.head_band_decoration_color", "0x"+Integer.toString(getPrimaryOverlayColor(pStack),16).toUpperCase()).withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
            }
            if(hasSecondaryBaseColor(pStack)){
                pTooltipComponents.add(Component.translatable("item.kawaiidishes.ears_color", "0x"+Integer.toString(getSecondaryBaseColor(pStack),16).toUpperCase()).withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
            }
            if(hasSecondaryOverlay(pStack)){
                pTooltipComponents.add(Component.translatable("item.kawaiidishes.ears_decoration_color", "0x"+Integer.toString(getSecondaryOverlayColor(pStack),16).toUpperCase()).withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
            }
        }else{
            pTooltipComponents.add(Component.translatable("item.kawaiidishes.dyeable").withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
        }
    }

    @Override
    public boolean isFoil(ItemStack p_41453_) {
        return false;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        applyEnchantmentEffects(stack, level, player);

        super.onArmorTick(stack,level,player);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null)
                    this.renderer = new HeadBandsWithEarsRender(HeadBandsWithEarsUtils.getEaredHeadBandsModels().get(earsType), livingEntity.getItemBySlot(EquipmentSlot.HEAD));


                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        });
    }

    public AnimalType getEarsType() {
        return earsType;
    }

    @Override
    public boolean hasPrimaryOverlay(ItemStack stack) {
        return stack.getOrCreateTag().contains(hasPrimaryOverlay) && stack.getOrCreateTag().getBoolean(hasPrimaryOverlay);
    }

    @Override
    public boolean hasSecondaryOverlay(ItemStack stack) {
        return stack.getOrCreateTag().contains(hasSecondaryOverlay) && stack.getOrCreateTag().getBoolean(hasSecondaryOverlay);
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
