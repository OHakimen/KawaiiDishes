package com.hakimen.kawaiidishes.enchantments;


import com.hakimen.kawaiidishes.configs.ServerConfig;
import com.hakimen.kawaiidishes.item.armor.EarsArmorItem;
import com.hakimen.kawaiidishes.item.armor.HeadBandWithEarsArmorItem;
import com.hakimen.kawaiidishes.item.armor.MaidDressesWithTailArmorItem;
import com.hakimen.kawaiidishes.item.armor.TailArmorItem;
import com.hakimen.kawaiidishes.registry.EnchantmentRegister;
import com.hakimen.kawaiidishes.utils.AnimalType;
import com.hakimen.kawaiidishes.utils.item.EnchantUtils;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class CatAuraEnchant extends Enchantment {
    public CatAuraEnchant() {
        super(Rarity.RARE, EnchantmentTarget.ARMOR, new EquipmentSlot[]{
                EquipmentSlot.CHEST,
                EquipmentSlot.HEAD
        });

    }

    public static void applySelf(ItemStack stack, World level, PlayerEntity player) {

        ItemStack headItem = player.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack chestItem = player.getEquippedStack(EquipmentSlot.CHEST);

        boolean isHeadItemValid =
                (headItem.getItem() instanceof HeadBandWithEarsArmorItem headBandWithEarsArmorItem && headBandWithEarsArmorItem.getEarsType().equals(AnimalType.CAT)) ||
                        (headItem.getItem() instanceof EarsArmorItem earsArmorItem && earsArmorItem.getEarsType().equals(AnimalType.CAT));

        boolean isChestItemValid = (chestItem.getItem() instanceof MaidDressesWithTailArmorItem maidDressesWithTailArmorItem && maidDressesWithTailArmorItem.getTailType().equals(AnimalType.CAT)) ||
                (chestItem.getItem() instanceof TailArmorItem tailArmorItem && tailArmorItem.getTailType().equals(AnimalType.CAT));

        if (isHeadItemValid && isChestItemValid) {
            int amp = Math.round((EnchantUtils.getEnchantLevel(headItem, EnchantmentRegister.CAT_AURA.get()) + EnchantUtils.getEnchantLevel(chestItem, EnchantmentRegister.CAT_AURA.get()))/2f);

            List<LivingEntity> toKnockback = new ArrayList<>();

            //Get creepers TODO: config again
            toKnockback.addAll(level.getEntitiesByType(EntityType.CREEPER, new Box(player.getSteppingPos()).expand(ServerConfig.catsAuraAmplifier.get() * amp), (creeper -> true)));

            //Get Phantoms TODO: config again
            toKnockback.addAll(level.getEntitiesByType(EntityType.PHANTOM, new Box(player.getSteppingPos()).expand(ServerConfig.catsAuraAmplifier.get() * amp), (phantom -> true)));

            toKnockback.forEach(mob -> {
                mob.takeKnockback(
                        mob instanceof PhantomEntity ? 0.4f : 0.25f,
                        (player.getX() - mob.getX()),
                        (player.getZ() - mob.getZ())
                );
            });
        }
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        Item item = stack.getItem();

        // Check for cat tails
        if (item instanceof MaidDressesWithTailArmorItem maidDressesWithTailArmorItem) {
            return maidDressesWithTailArmorItem.getTailType().equals(AnimalType.CAT);
        } else if (item instanceof TailArmorItem tailArmorItem) {
            return tailArmorItem.getTailType().equals(AnimalType.CAT);
        }

        //Check for cat ears
        if (item instanceof HeadBandWithEarsArmorItem headBandWithEarsArmorItem) {
            return headBandWithEarsArmorItem.getEarsType().equals(AnimalType.CAT);
        } else if (item instanceof EarsArmorItem earsArmorItem) {
            return earsArmorItem.getEarsType().equals(AnimalType.CAT);
        }

        return super.isAcceptableItem(stack);
    }

    @Override
    protected boolean canAccept(Enchantment enchantment) {
        return super.canAccept(enchantment);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
