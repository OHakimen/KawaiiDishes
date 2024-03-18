package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.aromas.*;
import com.hakimen.kawaiidishes.custom.Registries;
import com.hakimen.kawaiidishes.custom.type.Aroma;
import com.hakimen.kawaiidishes.datagen.ItemTagDataGen;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AromaRegister {
    public static final DeferredRegister<Aroma> AROMAS = DeferredRegister.create(Registries.AROMA_REGISTRY, KawaiiDishes.MODID);

    public static final DeferredHolder<Aroma,Aroma> INVALID = AROMAS.register("invalid", () -> new Aroma(null, 0));

    public static final DeferredHolder<Aroma,PacifyAroma> PACIFY_AROMA = AROMAS.register("pacify", () -> new PacifyAroma(
            ItemTagDataGen.PACIFY_AROMA_INCENSE,
            0xb775f0
    ));

    public static final DeferredHolder<Aroma,CalmingAroma> CALMING_AROMA = AROMAS.register("calming", () -> new CalmingAroma(
            ItemTagDataGen.CALMING_AROMA_INCENSE,
            0x6f96d9
    ));

    public static final DeferredHolder<Aroma,HastyAroma> HASTY_AROMA = AROMAS.register("hasty", () -> new HastyAroma(
            ItemTagDataGen.HASTY_AROMA_INCENSE,
            0xff974d
    ));

    public static final DeferredHolder<Aroma,PowerfulAroma> POWERFUL_AROMA = AROMAS.register("powerful", () -> new PowerfulAroma(
            ItemTagDataGen.POWERFUL_AROMA_INCENSE,
            0xf75145
    ));

    public static final DeferredHolder<Aroma,StimulatingAroma> STIMULATING_AROMA = AROMAS.register("stimulating", () -> new StimulatingAroma(
            ItemTagDataGen.STIMULATING_AROMA_INCENSE,
            0xe85f96
    ));

    public static final DeferredHolder<Aroma,CursedAroma> CURSED_AROMA = AROMAS.register("cursed", () -> new CursedAroma(
            ItemTagDataGen.CURSED_AROMA_INCENSE,
            0x303030
    ));

    public static final DeferredHolder<Aroma,PotionAroma> POTION_AROMA = AROMAS.register("potion", () -> new PotionAroma(
            ItemTagDataGen.POTION_AROMA_INCENSE,
            0
    ));

    public static final DeferredHolder<Aroma,Aroma> DECORATIVE_AROMA = AROMAS.register("decorative", () -> new DecorativeAroma(
            Tags.Items.DYES,
            0
    ));

    public static boolean isValidStack(ItemStack stack){
        for(Aroma aroma : AROMAS.getRegistry().get()){
            if(aroma != INVALID.get()){
                if(stack.is(aroma.getItems())){
                    return true;
                }
            }
        }
        return false;
    }

    public static int getAromaId(ItemStack stack){
        for(Aroma aroma : AROMAS.getRegistry().get()){
            if(aroma != INVALID.get()){
                if(stack.is(aroma.getItems())){
                    return Registries.AROMA_REGISTRY.getId(aroma);
                }
            }
        }
        return 0;
    }

    public static void register(IEventBus bus){
        AROMAS.register(bus);
    }
}
