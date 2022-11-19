package com.hakimen.kawaiidishes.items;

import com.hakimen.kawaiidishes.blocks.IceCreamBlock;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IceCream extends BlockItem {


    public IceCream(IceCreamBlock pBlock, int nutrition, float saturation) {
        super(pBlock, new Properties().food(new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation).fast().build())
                .tab(ItemRegister.foods).stacksTo(16).craftRemainder(
                        ItemRegister.glassCup.get()
                ));
    }


    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
        super.onUsingTick(stack, player, count);
    }

    CompoundTag mainEffect;
    CompoundTag secondaryEffect;

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {

        if(pLivingEntity instanceof Player p){
            p.addItem(ItemRegister.glassCup.get().getDefaultInstance());
        }
        loadItemData(pStack);

        if(!mainEffect.equals(new CompoundTag())){
            pLivingEntity.addEffect(MobEffectInstance.load(mainEffect));
        }
        if(!secondaryEffect.equals(new CompoundTag())){
            pLivingEntity.addEffect(MobEffectInstance.load(secondaryEffect));
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }

    public void loadItemData(ItemStack pStack){
        mainEffect = pStack.getOrCreateTag().getCompound("mainEffect");
        secondaryEffect = pStack.getOrCreateTag().getCompound("secondaryEffect");
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        if(!pStack.getOrCreateTag().getCompound("mainEffect").equals(new CompoundTag())){
            var instance = MobEffectInstance.load(pStack.getOrCreateTag().getCompound("mainEffect"));
            var mainEffect = new TranslatableComponent(instance.getDescriptionId());
            mainEffect.append(" ").append(new TranslatableComponent("enchantment.level." + (instance.getAmplifier() + 1)));
            mainEffect.append(" ("+ MobEffectUtil.formatDuration(instance,1f)+")");
            if(!instance.getEffect().isBeneficial())
                mainEffect.setStyle(Style.EMPTY.withColor(0xDD4444));
            else
                mainEffect.setStyle(Style.EMPTY.withColor(0x4455FF));
            pTooltip.add(mainEffect);
        }
        if(!pStack.getOrCreateTag().getCompound("secondaryEffect").equals(new CompoundTag())){
            var instance = MobEffectInstance.load(pStack.getOrCreateTag().getCompound("secondaryEffect"));
            var mainEffect = new TranslatableComponent(instance.getDescriptionId());
            mainEffect.append(" ").append(new TranslatableComponent("enchantment.level." + (instance.getAmplifier() + 1)));
            mainEffect.append(" ("+ MobEffectUtil.formatDuration(instance,1f)+")");
            if(!instance.getEffect().isBeneficial())
                mainEffect.setStyle(Style.EMPTY.withColor(0xDD4444));
            else
                mainEffect.setStyle(Style.EMPTY.withColor(0x4455FF));
            pTooltip.add(mainEffect);
        }

        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }

    @Override
    public void onDestroyed(ItemEntity pItemEntity) {
        pItemEntity.getItem().getOrCreateTag().put("mainEffect",mainEffect);
        pItemEntity.getItem().getOrCreateTag().put("secondaryEffect",secondaryEffect);
        super.onDestroyed(pItemEntity);
    }

    @Override
    protected boolean canPlace(BlockPlaceContext pContext, BlockState pState) {
        if(pContext.getPlayer().isShiftKeyDown()){
            return true;
        }
        return false;
    }

    @Override
    public SoundEvent getEatingSound() {
        return super.getDrinkingSound();
    }
}
