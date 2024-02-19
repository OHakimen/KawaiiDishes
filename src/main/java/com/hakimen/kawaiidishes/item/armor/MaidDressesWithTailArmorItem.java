package com.hakimen.kawaiidishes.item.armor;

import com.hakimen.kawaiidishes.client.entity.renderers.MaidDressesWithTailArmorRender;
import com.hakimen.kawaiidishes.enchantments.CatAuraEnchant;
import com.hakimen.kawaiidishes.item.IFourColorDyeableItem;
import com.hakimen.kawaiidishes.registry.EnchantmentRegister;
import com.hakimen.kawaiidishes.utils.AnimalType;
import com.hakimen.kawaiidishes.utils.MaidDressesWithTailUtils;
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
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Consumer;

import static com.hakimen.kawaiidishes.utils.item.ArmorUtils.applyEnchantmentEffects;

public class MaidDressesWithTailArmorItem extends GeoArmorItem implements IFourColorDyeableItem, IAnimationPredicate<MaidDressesWithTailArmorItem>{

    public static final String hasPrimaryOverlay = "HasPrimaryOverlay";
    public static final String hasSecondaryOverlay = "HasSecondaryOverlay";

    AnimalType tailType;

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public MaidDressesWithTailArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties, AnimalType tailType) {
        super(pMaterial, pType, pProperties);
        this.tailType = tailType;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if((hasPrimaryBaseColor(pStack) || hasPrimaryOverlayColor(pStack) || hasSecondaryBaseColor(pStack) || hasSecondaryOverlay(pStack)) && !pIsAdvanced.isAdvanced()){
            pTooltipComponents.add(Component.translatable("item.dyed").withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
        }else if((hasPrimaryBaseColor(pStack) || hasPrimaryOverlayColor(pStack) || hasSecondaryBaseColor(pStack) || hasSecondaryOverlay(pStack)) && !pIsAdvanced.isCreative()){
            if(hasPrimaryBaseColor(pStack)){
                pTooltipComponents.add(Component.translatable("item.kawaiidishes.dress_color", "0x"+Integer.toString(getPrimaryBaseColor(pStack),16).toUpperCase()).withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
            }
            if(hasPrimaryOverlayColor(pStack)){
                pTooltipComponents.add(Component.translatable("item.kawaiidishes.dress_decoration_color", "0x"+Integer.toString(getPrimaryOverlayColor(pStack),16).toUpperCase()).withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
            }
            if(hasSecondaryBaseColor(pStack)){
                pTooltipComponents.add(Component.translatable("item.kawaiidishes.tail_color", "0x"+Integer.toString(getSecondaryBaseColor(pStack),16).toUpperCase()).withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
            }
            if(hasSecondaryOverlay(pStack)){
                pTooltipComponents.add(Component.translatable("item.kawaiidishes.tail_decoration_color", "0x"+Integer.toString(getSecondaryOverlayColor(pStack),16).toUpperCase()).withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
            }
        }else{
            pTooltipComponents.add(Component.translatable("item.kawaiidishes.dyeable").withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
        }
    }
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null)
                    this.renderer = new MaidDressesWithTailArmorRender(MaidDressesWithTailUtils.getTailedDressesModels().get(tailType), livingEntity.getItemBySlot(EquipmentSlot.CHEST));


                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        });
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

    public AnimalType getTailType() {
        return tailType;
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
        controllers.add(new AnimationController<>(this, "dress_controller", 0, this::animator));
        controllers.add(new AnimationController<>(this, "tail_controller", 2, MaidDressesWithTailUtils.getTailedDressesAnimation().get(tailType)::animator));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public PlayState animator(AnimationState<MaidDressesWithTailArmorItem> state) {
        if(state.getExtraData().get(DataTickets.ENTITY) instanceof Player player){
            if(player.isCrouching()){
                state.getController().setAnimation(RawAnimation.begin().then("dress_on_shift", Animation.LoopType.PLAY_ONCE));
            }else{
                state.getController().setAnimation(RawAnimation.begin().then("dress_idle", Animation.LoopType.PLAY_ONCE));
            }
        }
        return PlayState.CONTINUE;
    }
}
