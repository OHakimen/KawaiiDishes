package com.hakimen.kawaiidishes.item.armor;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.ThighHighsArmorModel;
import com.hakimen.kawaiidishes.client.entity.renderers.ThighHighsArmorRender;
import com.hakimen.kawaiidishes.item.IDyeableItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
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

public class ThighHighsArmorItem extends GeoArmorItem implements IAnimationPredicate<ThighHighsArmorItem>, IDyeableItem {

    private static final String decoration = "Decoration";
    private static final List<Component> decorationNames = List.of(
            Component.translatable("item.kawaiidishes.thigh_highs.double_band"),
            Component.translatable("item.kawaiidishes.thigh_highs.full_band"),
            Component.translatable("item.kawaiidishes.thigh_highs.leg_clip"),
            Component.translatable("item.kawaiidishes.thigh_highs.bow")
    );

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public boolean isFoil(ItemStack p_41453_) {
        return false;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        applyEnchantmentEffects(stack, level, player);

        super.onArmorTick(stack,level,player);
    }
    public ThighHighsArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
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
        if (hasOverlay(pStack) && pStack.getOrCreateTag().getInt(decoration) > 0 && pStack.getOrCreateTag().getInt(decoration)-1 < decorationNames.size()) {
            pTooltipComponents.add(decorationNames.get(pStack.getOrCreateTag().getInt(decoration)-1));
        }

    }

    public boolean hasOverlay(ItemStack stack){
        CompoundTag decorData = stack.getOrCreateTag();
        return decorData.contains(decoration, Tag.TAG_INT) && decorData.getInt(decoration) > 0;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null)
                    this.renderer = new ThighHighsArmorRender(new ThighHighsArmorModel(
                            new ResourceLocation(KawaiiDishes.MODID,"geo/thigh_highs.geo.json"),   //
                            new ResourceLocation(KawaiiDishes.MODID,"textures/models/armor/thigh_highs/thigh_highs.png"),   //  Set resource locations
                            new ResourceLocation(KawaiiDishes.MODID,"")    //
                    ), livingEntity.getItemBySlot(EquipmentSlot.LEGS));


                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "thigh_high_controller", 20, this::animator));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public PlayState animator(AnimationState<ThighHighsArmorItem> state) {
        return null;
    }

}
