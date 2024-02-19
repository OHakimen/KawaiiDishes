package com.hakimen.kawaiidishes.item.armor;

import com.hakimen.kawaiidishes.client.entity.renderers.EarsArmorRender;
import com.hakimen.kawaiidishes.enchantments.CatAuraEnchant;
import com.hakimen.kawaiidishes.item.IDyeableItem;
import com.hakimen.kawaiidishes.registry.EnchantmentRegister;
import com.hakimen.kawaiidishes.utils.AnimalType;
import com.hakimen.kawaiidishes.utils.EarUtils;
import com.hakimen.kawaiidishes.utils.item.EnchantUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.nbt.CompoundTag;
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
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Consumer;

import static com.hakimen.kawaiidishes.utils.item.ArmorUtils.applyEnchantmentEffects;

public class EarsArmorItem extends GeoArmorItem implements IDyeableItem {


    private static final String has_overlay = "HasOverlay";
    AnimalType earsType;
    boolean overlayable;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public boolean hasOverlay(ItemStack stack){
        CompoundTag decorData = stack.getOrCreateTag();
        return overlayable && decorData.contains(has_overlay) && decorData.getBoolean(has_overlay);
    }

    public EarsArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties, AnimalType earType, boolean overlayable) {
        super(pMaterial, pType, pProperties);
        this.earsType = earType;
        this.overlayable = overlayable;
    }


    public AnimalType getEarsType() {
        return earsType;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if((hasBaseColor(pStack) || hasOverlayColor(pStack)) && !pIsAdvanced.isAdvanced() ){
            pTooltipComponents.add(Component.translatable("item.dyed").withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
        }else if((hasBaseColor(pStack) || hasOverlayColor(pStack)) && !pIsAdvanced.isCreative()){
            if(hasBaseColor(pStack)){
                pTooltipComponents.add(Component.translatable("item.kawaiidishes.base_dye", "0x"+Integer.toString(getBaseColor(pStack),16).toUpperCase()).withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
            }
            if(hasOverlayColor(pStack)){
                pTooltipComponents.add(Component.translatable("item.kawaiidishes.overlay_dye", "0x"+Integer.toString(getOverlayColor(pStack),16).toUpperCase()).withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
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

        super.onArmorTick(stack, level, player);
    }


    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null)
                    this.renderer = new EarsArmorRender(EarUtils.getEarModels().get(earsType));

                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "ear_controller", 0, (state -> EarUtils.getEarAnimations().get(earsType).animator(state))));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
