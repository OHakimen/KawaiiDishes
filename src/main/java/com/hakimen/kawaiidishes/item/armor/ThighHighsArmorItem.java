package com.hakimen.kawaiidishes.item.armor;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.ThighHighsArmorModel;
import com.hakimen.kawaiidishes.client.entity.renderers.ThighHighsArmorRender;
import com.hakimen.kawaiidishes.custom.Registries;
import com.hakimen.kawaiidishes.item.IDyeableItem;
import com.hakimen.kawaiidishes.utils.ItemUtils;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
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
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import static com.hakimen.kawaiidishes.utils.item.ArmorUtils.applyEnchantmentEffects;

public class ThighHighsArmorItem extends GeoArmorItem implements IAnimationPredicate<ThighHighsArmorItem>, IDyeableItem {

    private static final String decoration = "Decoration";
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);
    @Override
    public boolean hasGlint(ItemStack p_41453_) {
        return false;
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        return renderProvider;
    }

    @Override
    public void inventoryTick(ItemStack itemStack, World level, Entity entity, int slot, boolean bl) {
        if(ItemUtils.isItemOnArmorSlot(slot) && entity instanceof PlayerEntity player){
            applyEnchantmentEffects(itemStack, level, player);
        }
        super.inventoryTick(itemStack, level, entity, slot, bl);
    }

    public ThighHighsArmorItem(ArmorMaterial pMaterial, Type pType, Settings pProperties) {
        super(pMaterial, pType, pProperties);
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
        if (hasOverlay(pStack) && pStack.getOrCreateNbt().getInt(decoration) > 0 && pStack.getOrCreateNbt().getInt(decoration)-1 < Registries.THIGH_HIGH_DECORATIONS.size()) {
            pTooltipComponents.add(Registries.THIGH_HIGH_DECORATIONS.getEntry(pStack.getOrCreateNbt().getInt(decoration)).get().value().getName());
        }

        if(hasOverlay(pStack)){
            pTooltipComponents.add(Text.translatable("item.kawaiidishes.overlayed").fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
        }

    }

    public boolean hasOverlay(ItemStack stack){
        NbtCompound decorData = stack.getOrCreateNbt();
        return decorData.contains(decoration, NbtElement.INT_TYPE) && decorData.getInt(decoration) > 0;
    }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public BipedEntityModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, BipedEntityModel<LivingEntity> original) {
                if (this.renderer == null)
                    this.renderer = new ThighHighsArmorRender(new ThighHighsArmorModel(
                            new Identifier(KawaiiDishes.MODID,"geo/thigh_highs.geo.json"),   //
                            new Identifier(KawaiiDishes.MODID,"textures/models/armor/thigh_highs/thigh_highs.png"),   //  Set resource locations
                            new Identifier(KawaiiDishes.MODID,"")    //
                    ), livingEntity.getEquippedStack(EquipmentSlot.LEGS));


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
