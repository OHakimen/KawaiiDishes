package com.hakimen.kawaiidishes.item.armor;

import com.hakimen.kawaiidishes.client.entity.renderers.MaidDressesWithTailArmorRender;
import com.hakimen.kawaiidishes.item.IFourColorDyeableItem;
import com.hakimen.kawaiidishes.utils.AnimalType;
import com.hakimen.kawaiidishes.utils.ItemUtils;
import com.hakimen.kawaiidishes.utils.MaidDressesWithTailUtils;
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

public class MaidDressesWithTailArmorItem extends GeoArmorItem implements IFourColorDyeableItem, IAnimationPredicate<MaidDressesWithTailArmorItem>{

    public static final String hasPrimaryOverlay = "HasPrimaryOverlay";
    public static final String hasSecondaryOverlay = "HasSecondaryOverlay";

    AnimalType tailType;

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

    public MaidDressesWithTailArmorItem(ArmorMaterial pMaterial, Type pType, Settings pProperties, AnimalType tailType) {
        super(pMaterial, pType, pProperties);
        this.tailType = tailType;
    }

    @Override
    public void appendTooltip(ItemStack pStack, @Nullable World pLevel, List<Text> pTooltipComponents, TooltipContext pIsAdvanced) {
        if((hasPrimaryBaseColor(pStack) || hasPrimaryOverlayColor(pStack) || hasSecondaryBaseColor(pStack) || hasSecondaryOverlay(pStack)) && !pIsAdvanced.isAdvanced()){
            pTooltipComponents.add(Text.translatable("item.dyed").fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
        }else if((hasPrimaryBaseColor(pStack) || hasPrimaryOverlayColor(pStack) || hasSecondaryBaseColor(pStack) || hasSecondaryOverlay(pStack)) && !pIsAdvanced.isCreative()){
            if(hasPrimaryBaseColor(pStack)){
                pTooltipComponents.add(Text.translatable("item.kawaiidishes.dress_color", "0x"+Integer.toString(getPrimaryBaseColor(pStack),16).toUpperCase()).fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
            }
            if(hasPrimaryOverlayColor(pStack)){
                pTooltipComponents.add(Text.translatable("item.kawaiidishes.dress_decoration_color", "0x"+Integer.toString(getPrimaryOverlayColor(pStack),16).toUpperCase()).fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
            }
            if(hasSecondaryBaseColor(pStack)){
                pTooltipComponents.add(Text.translatable("item.kawaiidishes.tail_color", "0x"+Integer.toString(getSecondaryBaseColor(pStack),16).toUpperCase()).fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
            }
            if(hasSecondaryOverlay(pStack)){
                pTooltipComponents.add(Text.translatable("item.kawaiidishes.tail_decoration_color", "0x"+Integer.toString(getSecondaryOverlayColor(pStack),16).toUpperCase()).fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
            }
        }else{
            pTooltipComponents.add(Text.translatable("item.kawaiidishes.dyeable").fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
        }
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        return renderProvider;
    }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public BipedEntityModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, BipedEntityModel<LivingEntity> original) {
                if (this.renderer == null)
                    this.renderer = new MaidDressesWithTailArmorRender(MaidDressesWithTailUtils.getTailedDressesModels().get(tailType), livingEntity.getEquippedStack(EquipmentSlot.CHEST));


                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        });
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

    public AnimalType getTailType() {
        return tailType;
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
        controllers.add(new AnimationController<>(this, "dress_controller", 0, this::animator));
        controllers.add(new AnimationController<>(this, "tail_controller", 2, MaidDressesWithTailUtils.getTailedDressesAnimation().get(tailType)::animator));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public PlayState animator(AnimationState<MaidDressesWithTailArmorItem> state) {
        if(state.getExtraData().get(DataTickets.ENTITY) instanceof PlayerEntity player){
            if(player.isInSneakingPose()){
                state.getController().setAnimation(RawAnimation.begin().then("dress_on_shift", Animation.LoopType.PLAY_ONCE));
            }else{
                state.getController().setAnimation(RawAnimation.begin().then("dress_idle", Animation.LoopType.PLAY_ONCE));
            }
        }
        return PlayState.CONTINUE;
    }
}
