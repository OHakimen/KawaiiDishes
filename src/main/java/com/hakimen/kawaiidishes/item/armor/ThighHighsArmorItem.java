package com.hakimen.kawaiidishes.item.armor;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.client.entity.models.ThighHighsArmorModel;
import com.hakimen.kawaiidishes.client.entity.renderers.ThighHighsArmorRender;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.component.DataComponents;
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
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable; import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.hakimen.kawaiidishes.utils.item.ArmorUtils.applyEnchantmentEffects;

public class ThighHighsArmorItem extends GeoArmorItem implements IAnimationPredicate<ThighHighsArmorItem> {

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
        super(pMaterial, pType, pProperties.component(DataComponents.CUSTOM_DATA, CustomData.of(
                ((Supplier<CompoundTag>)() -> {
                    CompoundTag tag = new CompoundTag();
                    tag.putInt("Decoration", 0);
                    return tag;
                }).get()
        )));
    }


    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoArmorRenderer<?> renderer;

            @Override
                        public @Nullable <T extends LivingEntity> HumanoidModel<?> getGeoArmorRenderer(@Nullable T livingEntity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot, @Nullable HumanoidModel<T> original) {
                if (this.renderer == null)
                    this.renderer = new ThighHighsArmorRender(new ThighHighsArmorModel(
                            ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID,"geo/thigh_highs.geo.json"),   //
                            ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID,"textures/models/armor/thigh_highs/thigh_highs.png"),   //  Set resource locations
                            ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID,"")    //
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
