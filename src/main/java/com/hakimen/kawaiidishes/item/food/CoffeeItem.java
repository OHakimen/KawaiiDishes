package com.hakimen.kawaiidishes.item.food;

import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class CoffeeItem extends BlockItem {

    MobEffectInstance[] mobEffects;

    public CoffeeItem(Block pBlock, int nutrition, float saturationMod, MobEffectInstance... effects) {
        super(pBlock, new Item.Properties().food(
                ((Supplier<FoodProperties>) () -> {
                    FoodProperties.Builder builder = new FoodProperties.Builder()
                            .nutrition(nutrition)
                            .saturationMod(saturationMod)
                            .alwaysEat();

                    for (MobEffectInstance effect : effects) {
                        builder.effect(() -> effect, 1f);
                    }

                    return builder.build();
                }).get()
        ));
        mobEffects = effects;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pComponents, TooltipFlag pTooltipFlag) {

        if (!Arrays.asList(mobEffects).isEmpty()) {
            {
                for (MobEffectInstance mobeffectinstance : mobEffects) {
                    MutableComponent mutablecomponent = Component.translatable(mobeffectinstance.getDescriptionId());
                    MobEffect mobeffect = mobeffectinstance.getEffect();

                    if (mobeffectinstance.getAmplifier() > 0) {
                        mutablecomponent = Component.translatable(
                                "potion.withAmplifier", mutablecomponent, Component.translatable("potion.potency." + mobeffectinstance.getAmplifier())
                        );
                    }

                    if (!mobeffectinstance.endsWithin(20)) {
                        mutablecomponent = Component.translatable("potion.withDuration", mutablecomponent, MobEffectUtil.formatDuration(mobeffectinstance, 1f,1f));
                    }

                    pComponents.add(mutablecomponent.withStyle(mobeffect.getCategory().getTooltipFormatting()));
                }
            }
        }
        super.appendHoverText(pStack, pLevel, pComponents, pTooltipFlag);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof Player player) {
            if(player.getInventory().hasAnyMatching(item -> item.is(ItemRegister.MUG.get()) && item.getCount() < item.getMaxStackSize()) || player.getInventory().getFreeSlot() != -1){
                player.addItem(ItemRegister.MUG.get().getDefaultInstance());
            }else if (player.getInventory().getFreeSlot() == -1) {
                level.addFreshEntity(new ItemEntity(level,
                        player.getX() + 0.5 + Mth.nextDouble(level.random, -0.25, 0.25),
                        player.getY() + 0.5 + Mth.nextDouble(level.random, -0.25, 0.25),
                        player.getZ() + 0.5 + Mth.nextDouble(level.random, -0.25, 0.25),
                        ItemRegister.MUG.get().getDefaultInstance()));
            }
        }

        return super.finishUsingItem(itemStack, level, livingEntity);
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    protected boolean canPlace(BlockPlaceContext p_40611_, BlockState p_40612_) {
        return p_40611_.getPlayer().isCrouching() && super.canPlace(p_40611_, p_40612_);
    }
}
