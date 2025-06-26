package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.aromas.*;
import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.custom.Recorder;
import com.hakimen.kawaiidishes.custom.Registries;
import com.hakimen.kawaiidishes.custom.types.Aroma;
import com.hakimen.kawaiidishes.datagen.ItemTagDataGen;
import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.world.item.ItemStack;
import java.util.function.Supplier;

public class AromaRegister {
    public static final Recorder<Aroma> AROMAS = new Recorder<>(Registries.AROMAS, KawaiiDishes.MODID);

    public static final Supplier<Aroma> INVALID = AROMAS.register("invalid", () -> new Aroma(null, 0));

    public static final Supplier<PacifyAroma> PACIFY_AROMA = AROMAS.register("pacify", () -> new PacifyAroma(
            ItemTagDataGen.PACIFY_AROMA_INCENSE,
            0xb775f0
    ));

    public static final Supplier<CalmingAroma> CALMING_AROMA = AROMAS.register("calming", () -> new CalmingAroma(
            ItemTagDataGen.CALMING_AROMA_INCENSE,
            0x6f96d9
    ));

    public static final Supplier<HastyAroma> HASTY_AROMA = AROMAS.register("hasty", () -> new HastyAroma(
            ItemTagDataGen.HASTY_AROMA_INCENSE,
            0xff974d
    ));

    public static final Supplier<PowerfulAroma> POWERFUL_AROMA = AROMAS.register("powerful", () -> new PowerfulAroma(
            ItemTagDataGen.POWERFUL_AROMA_INCENSE,
            0xf75145
    ));

    public static final Supplier<StimulatingAroma> STIMULATING_AROMA = AROMAS.register("stimulating", () -> new StimulatingAroma(
            ItemTagDataGen.STIMULATING_AROMA_INCENSE,
            0xe85f96
    ));

    public static final Supplier<CursedAroma> CURSED_AROMA = AROMAS.register("cursed", () -> new CursedAroma(
            ItemTagDataGen.CURSED_AROMA_INCENSE,
            0x303030
    ));

    public static final Supplier<PotionAroma> POTION_AROMA = AROMAS.register("potion", () -> new PotionAroma(
            ItemTagDataGen.POTION_AROMA_INCENSE,
            0
    ));

    public static final Supplier<Aroma> DECORATIVE_AROMA = AROMAS.register("decorative", () -> new DecorativeAroma(
            ConventionalItemTags.DYES,
            0
    ));

    public static boolean isValidStack(ItemStack stack){
        for(Aroma aroma : AROMAS.getRegistry()){
            if(aroma != INVALID.get()){
                if(stack.is(aroma.getItems())){
                    return true;
                }
            }
        }
        return false;
    }

    public static int getAromaId(ItemStack stack){
        for(Aroma aroma : AROMAS.getRegistry()){
            if(aroma != INVALID.get()){
                if(stack.is(aroma.getItems())){
                    return Registries.AROMAS.getId(aroma);
                }
            }
        }
        return 0;
    }

    public static void register(){
        //Bootstrap
    }
}
