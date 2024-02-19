package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.enchantments.AutoEquipCurse;
import com.hakimen.kawaiidishes.enchantments.BunnyHasteEnchantment;
import com.hakimen.kawaiidishes.enchantments.CatAuraEnchant;
import com.hakimen.kawaiidishes.enchantments.FoxAptitudeEnchant;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EnchantmentRegister {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(Registries.ENCHANTMENT, KawaiiDishes.MODID);
    public static final DeferredHolder<Enchantment, CatAuraEnchant> CAT_AURA = ENCHANTMENTS.register("cat_aura", CatAuraEnchant::new);
    public static final DeferredHolder<Enchantment, FoxAptitudeEnchant> FOX_APTITUDE = ENCHANTMENTS.register("fox_aptitude", FoxAptitudeEnchant::new);
    public static final DeferredHolder<Enchantment, BunnyHasteEnchantment> BUNNY_HASTE = ENCHANTMENTS.register("bunny_haste", BunnyHasteEnchantment::new);
    public static final DeferredHolder<Enchantment, AutoEquipCurse> AUTO_EQUIP_CURSE = ENCHANTMENTS.register("curse_of_auto_equip", AutoEquipCurse::new);

    public static void register(IEventBus bus){
        ENCHANTMENTS.register(bus);
    }
}
