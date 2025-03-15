package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.custom.Recorder;
import com.hakimen.kawaiidishes.enchantments.AutoEquipCurse;
import com.hakimen.kawaiidishes.enchantments.BunnyHasteEnchantment;
import com.hakimen.kawaiidishes.enchantments.CatAuraEnchant;
import com.hakimen.kawaiidishes.enchantments.FoxAptitudeEnchant;
import java.util.function.Supplier;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;

public class EnchantmentRegister {
    public static final Recorder<Enchantment> ENCHANTMENTS = new Recorder<>(Registries.ENCHANTMENT, KawaiiDishes.MODID);

    public static final Supplier<CatAuraEnchant> CAT_AURA = ENCHANTMENTS.register("cat_aura", CatAuraEnchant::new);
    public static final Supplier<FoxAptitudeEnchant> FOX_APTITUDE = ENCHANTMENTS.register("fox_aptitude", FoxAptitudeEnchant::new);
    public static final Supplier<BunnyHasteEnchantment> BUNNY_HASTE = ENCHANTMENTS.register("bunny_haste", BunnyHasteEnchantment::new);
    public static final Supplier<AutoEquipCurse> AUTO_EQUIP_CURSE = ENCHANTMENTS.register("curse_of_auto_equip", AutoEquipCurse::new);

    public static void register(){
        //Bootstrap
    }
}
