package com.hakimen.kawaiidishes.item.food;

import com.hakimen.kawaiidishes.registry.ItemRegister;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CoffeeItem extends BlockItem {

    StatusEffectInstance[] mobEffects;
    public CoffeeItem(Block pBlock, int nutrition, float saturationMod, StatusEffectInstance... effects) {
        super(pBlock, new Item.Settings().food(
                ((Supplier<FoodComponent>) () -> {
                    FoodComponent.Builder builder = new FoodComponent.Builder()
                            .hunger(nutrition)
                            .saturationModifier(saturationMod)
                            .alwaysEdible();

                    for (StatusEffectInstance effect : effects) {
                        builder.statusEffect(effect, 1f);
                    }

                    return builder.build();
                }).get()
        ));
        mobEffects = effects;
    }
    @Override
    public void appendTooltip(ItemStack pStack, @Nullable World pLevel, List<Text> pComponents, TooltipContext pTooltipFlag) {

        if (!Arrays.asList(mobEffects).isEmpty()) {
            {
                for (StatusEffectInstance mobeffectinstance : mobEffects) {
                    MutableText mutablecomponent = Text.translatable(mobeffectinstance.getTranslationKey());
                    StatusEffect mobeffect = mobeffectinstance.getEffectType();

                    if (mobeffectinstance.getAmplifier() > 0) {
                        mutablecomponent = Text.translatable(
                                "potion.withAmplifier", mutablecomponent, Text.translatable("potion.potency." + mobeffectinstance.getAmplifier())
                        );
                    }

                    if (!mobeffectinstance.isDurationBelow(20)) {
                        mutablecomponent = Text.translatable("potion.withDuration", mutablecomponent, StatusEffectUtil.getDurationText(mobeffectinstance, 1f));
                    }

                    pComponents.add(mutablecomponent.formatted(mobeffect.getCategory().getFormatting()));
                }
            }
        }
        super.appendTooltip(pStack, pLevel, pComponents, pTooltipFlag);
    }

    @Override
    public ItemStack finishUsing(ItemStack itemStack, World level, LivingEntity livingEntity) {
        if (livingEntity instanceof PlayerEntity player) {
            if(player.getInventory().containsAny(item -> item.isOf(ItemRegister.MUG.get()) && item.getCount() < item.getMaxCount()) || player.getInventory().getEmptySlot() != -1){
                player.giveItemStack(ItemRegister.MUG.get().getDefaultStack());
            }else if (player.getInventory().getEmptySlot() == -1) {
                level.spawnEntity(new ItemEntity(level,
                        player.getX() + 0.5 + MathHelper.nextDouble(level.random, -0.25, 0.25),
                        player.getY() + 0.5 + MathHelper.nextDouble(level.random, -0.25, 0.25),
                        player.getZ() + 0.5 + MathHelper.nextDouble(level.random, -0.25, 0.25),
                        ItemRegister.MUG.get().getDefaultStack()));
            }
        }

        return super.finishUsing(itemStack, level, livingEntity);
    }

    @Override
    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    @Override
    protected boolean canPlace(ItemPlacementContext blockPlaceContext, BlockState p_40612_) {
        return blockPlaceContext.getPlayer().isInSneakingPose() && super.canPlace(blockPlaceContext, p_40612_);
    }
}
