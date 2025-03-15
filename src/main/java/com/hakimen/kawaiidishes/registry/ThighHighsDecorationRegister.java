package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.custom.Recorder;
import com.hakimen.kawaiidishes.custom.Registries;
import com.hakimen.kawaiidishes.custom.types.ThighHighDecoration;
import java.util.function.Supplier;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ThighHighsDecorationRegister {
    public static final Recorder<ThighHighDecoration> DECORATIONS = new Recorder<>(Registries.THIGH_HIGH_DECORATIONS, KawaiiDishes.MODID);

    public static final Supplier<ThighHighDecoration> NONE = DECORATIONS.register("none", () ->
            new ThighHighDecoration(Text.translatable("thighhighsdecoration.kawaiidishes.none"),
                    new Identifier(KawaiiDishes.MODID, "item/thigh_highs/no_decoration"),
                    new Identifier(KawaiiDishes.MODID,"textures/models/armor/none.png")));

    public static final Supplier<ThighHighDecoration> DOUBLE_BANDS = DECORATIONS.register("double_band", () ->
            new ThighHighDecoration(Text.translatable("thighhighsdecoration.kawaiidishes.double_band"),
                    new Identifier(KawaiiDishes.MODID, "item/thigh_highs/double_band"),
                    new Identifier(KawaiiDishes.MODID,"textures/models/armor/thigh_highs/double_band.png")));

    public static final Supplier<ThighHighDecoration> BOW = DECORATIONS.register("bow", () ->
            new ThighHighDecoration(Text.translatable("thighhighsdecoration.kawaiidishes.bow"),
                    new Identifier(KawaiiDishes.MODID, "item/thigh_highs/bow"),
                    new Identifier(KawaiiDishes.MODID,"textures/models/armor/thigh_highs/bow.png")));

    public static final Supplier<ThighHighDecoration> LEG_CLIP = DECORATIONS.register("leg_clip", () ->
            new ThighHighDecoration(Text.translatable("thighhighsdecoration.kawaiidishes.leg_clip"),
                    new Identifier(KawaiiDishes.MODID, "item/thigh_highs/leg_clip"),
                    new Identifier(KawaiiDishes.MODID,"textures/models/armor/thigh_highs/leg_clip.png"),
                    new Identifier(KawaiiDishes.MODID,"textures/models/armor/thigh_highs/leg_clip_clip.png")));

    public static final Supplier<ThighHighDecoration> FULL_BANDS = DECORATIONS.register("full_band", () ->
            new ThighHighDecoration(Text.translatable("thighhighsdecoration.kawaiidishes.full_band"),
                    new Identifier(KawaiiDishes.MODID, "item/thigh_highs/full_band"),
                    new Identifier(KawaiiDishes.MODID,"textures/models/armor/thigh_highs/full_band.png")));
    public static void register(){
        //This does nothing but bootstrap the class
    }
}
