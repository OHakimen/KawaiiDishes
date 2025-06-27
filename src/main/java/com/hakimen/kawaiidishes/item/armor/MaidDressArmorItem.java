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
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import static com.hakimen.kawaiidishes.utils.item.ArmorUtils.applyEnchantmentEffects;

public class MaidDressArmorItem extends GeoArmorItem implements IAnimationPredicate<MaidDressArmorItem>, IDyeableItem {

    private static final String has_overlay = "HasOverlay";

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

    public MaidDressArmorItem(ArmorMaterial pMaterial, Type pType, Settings pProperties) {
        super(pMaterial, pType, pProperties);
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
    public void appendTooltip(ItemStack pStack, @Nullable World pLevel, List<Text> pTooltipComponents, TooltipContext pIsAdvanced) {
        if((hasBaseColor(pStack) || hasOverlayColor(pStack)) && !pIsAdvanced.isAdvanced() ){
            pTooltipComponents.add(Text.translatable("item.dyed").fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
        }else if((hasBaseColor(pStack) || hasOverlayColor(pStack)) && !pIsAdvanced.isCreative()){
            if(hasBaseColor(pStack)){
                pTooltipComponents.add(Text.translatable("item.kawaiidishes.base_dye", "0x"+Integer.toString(getBaseColor(pStack),16).toUpperCase()).fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
            }
            if(hasOverlayColor(pStack)){
                pTooltipComponents.add(Text.translatable("item.kawaiidishes.overlay_dye", "0x"+Integer.toString(getOverlayColor(pStack),16).toUpperCase()).fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
            }
        }else{
            pTooltipComponents.add(Text.translatable("item.kawaiidishes.dyeable").fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
        }
        if(hasOverlay(pStack)){
            pTooltipComponents.add(Text.translatable("item.kawaiidishes.overlayed").fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
        }
    }

    public boolean hasOverlay(ItemStack stack){
        NbtCompound decorData = stack.getOrCreateNbt();
        return decorData.contains(has_overlay) && decorData.getBoolean(has_overlay);
    }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public BipedEntityModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, BipedEntityModel<LivingEntity> original) {
                if (this.renderer == null)
                    this.renderer = new MaidDressArmorRender(new MaidDressArmorModel(
                            new Identifier(KawaiiDishes.MODID,"geo/maid_dress.geo.json"),   //
                            new Identifier(KawaiiDishes.MODID,"textures/models/armor/maid_dress/maid_dress_overlay.png"),   //  Set resource locations
                            new Identifier(KawaiiDishes.MODID,"animations/maid_dress.animation.json")    //
                    ), livingEntity.getEquippedStack(EquipmentSlot.CHEST));


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
        if(state.getExtraData().get(DataTickets.ENTITY) instanceof PlayerEntity player){
            if(player.isInSneakingPose()){
                state.getController().setAnimation(RawAnimation.begin().then("on_shift", Animation.LoopType.PLAY_ONCE));
            }else{
                state.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.PLAY_ONCE));
            }
        }
        return PlayState.CONTINUE;
    }

}
