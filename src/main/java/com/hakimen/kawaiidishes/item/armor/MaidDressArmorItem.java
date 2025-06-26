package com.hakimen.kawaiidishes.item.armor;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.MaidDressArmorModel;
import com.hakimen.kawaiidishes.client.entity.renderers.MaidDressArmorRender;
import com.hakimen.kawaiidishes.item.IDyeableItem;
import com.hakimen.kawaiidishes.utils.ItemUtils;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import static com.hakimen.kawaiidishes.utils.item.ArmorUtils.applyEnchantmentEffects;

public class MaidDressArmorItem extends GeoArmorItem implements IAnimationPredicate<MaidDressArmorItem>, IDyeableItem {

    private static final String has_overlay = "HasOverlay";

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

    public MaidDressArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }


    @Override
    public boolean isFoil(ItemStack p_41453_) {
        return false;
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int slot, boolean bl) {
        if(ItemUtils.isItemOnArmorSlot(slot) && entity instanceof Player player){
            applyEnchantmentEffects(itemStack, level, player);
        }

        super.inventoryTick(itemStack, level, entity, slot, bl);
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
        if(hasOverlay(pStack)){
            pTooltipComponents.add(Component.translatable("item.kawaiidishes.overlayed").withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
        }
    }

    public boolean hasOverlay(ItemStack stack){
        CompoundTag decorData = stack.getOrCreateTag();
        return decorData.contains(has_overlay) && decorData.getBoolean(has_overlay);
    }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public HumanoidModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<LivingEntity> original) {
                if (this.renderer == null)
                    this.renderer = new MaidDressArmorRender(new MaidDressArmorModel(
                            new ResourceLocation(KawaiiDishes.MODID,"geo/maid_dress.geo.json"),   //
                            new ResourceLocation(KawaiiDishes.MODID,"textures/models/armor/maid_dress/maid_dress_overlay.png"),   //  Set resource locations
                            new ResourceLocation(KawaiiDishes.MODID,"animations/maid_dress.animation.json")    //
                    ), livingEntity.getItemBySlot(EquipmentSlot.CHEST));


                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        });
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        return renderProvider;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "maid_dress_controller", 0, this::animator));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public PlayState animator(AnimationState<MaidDressArmorItem> state) {
        if(state.getExtraData().get(DataTickets.ENTITY) instanceof Player player){
            if(player.isCrouching()){
                state.getController().setAnimation(RawAnimation.begin().then("on_shift", Animation.LoopType.PLAY_ONCE));
            }else{
                state.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.PLAY_ONCE));
            }
        }
        return PlayState.CONTINUE;
    }

}
