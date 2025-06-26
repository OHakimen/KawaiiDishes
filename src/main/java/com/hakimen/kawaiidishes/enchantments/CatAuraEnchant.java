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
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class CatAuraEnchant extends Enchantment {
    public CatAuraEnchant() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR, new EquipmentSlot[]{
                EquipmentSlot.CHEST,
                EquipmentSlot.HEAD
        });

    }

    public static void applySelf(ItemStack stack, Level level, Player player) {

        ItemStack headItem = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chestItem = player.getItemBySlot(EquipmentSlot.CHEST);

        boolean isHeadItemValid =
                (headItem.getItem() instanceof HeadBandWithEarsArmorItem headBandWithEarsArmorItem && headBandWithEarsArmorItem.getEarsType().equals(AnimalType.CAT)) ||
                        (headItem.getItem() instanceof EarsArmorItem earsArmorItem && earsArmorItem.getEarsType().equals(AnimalType.CAT));

        boolean isChestItemValid = (chestItem.getItem() instanceof MaidDressesWithTailArmorItem maidDressesWithTailArmorItem && maidDressesWithTailArmorItem.getTailType().equals(AnimalType.CAT)) ||
                (chestItem.getItem() instanceof TailArmorItem tailArmorItem && tailArmorItem.getTailType().equals(AnimalType.CAT));

        if (isHeadItemValid && isChestItemValid) {
            int amp = Math.round((EnchantUtils.getEnchantLevel(headItem, EnchantmentRegister.CAT_AURA.get()) + EnchantUtils.getEnchantLevel(chestItem, EnchantmentRegister.CAT_AURA.get()))/2f);

            List<LivingEntity> toKnockback = new ArrayList<>();

            //Get creepers TODO: config again
            toKnockback.addAll(level.getEntities(EntityType.CREEPER, new AABB(player.getOnPos()).inflate(ServerConfig.catsAuraAmplifier.get() * amp), (creeper -> true)));

            //Get Phantoms TODO: config again
            toKnockback.addAll(level.getEntities(EntityType.PHANTOM, new AABB(player.getOnPos()).inflate(ServerConfig.catsAuraAmplifier.get() * amp), (phantom -> true)));

            toKnockback.forEach(mob -> {
                mob.knockback(
                        mob instanceof Phantom ? 0.4f : 0.25f,
                        (player.getX() - mob.getX()),
                        (player.getZ() - mob.getZ())
                );
            });
        }
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
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

        return super.canEnchant(stack);
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchantment) {
        return super.checkCompatibility(enchantment);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
