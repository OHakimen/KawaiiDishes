package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.custom.Recorder;
import com.hakimen.kawaiidishes.custom.Registries;
import com.hakimen.kawaiidishes.custom.types.ThighHighDecoration;
import java.util.function.Supplier;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;


public class ThighHighsDecorationRegister {
    public static final Recorder<ThighHighDecoration> DECORATIONS = new Recorder<>(Registries.THIGH_HIGH_DECORATIONS, KawaiiDishes.MODID);

    public static final Supplier<ThighHighDecoration> NONE = DECORATIONS.register("none", () ->
            new ThighHighDecoration(Component.translatable("thighhighsdecoration.kawaiidishes.none"),
                    new ResourceLocation(KawaiiDishes.MODID, "item/thigh_highs/no_decoration"),
                    new ResourceLocation(KawaiiDishes.MODID,"textures/models/armor/none.png")));

    public static final Supplier<ThighHighDecoration> DOUBLE_BANDS = DECORATIONS.register("double_band", () ->
            new ThighHighDecoration(Component.translatable("thighhighsdecoration.kawaiidishes.double_band"),
                    new ResourceLocation(KawaiiDishes.MODID, "item/thigh_highs/double_band"),
                    new ResourceLocation(KawaiiDishes.MODID,"textures/models/armor/thigh_highs/double_band.png")));

    public static final Supplier<ThighHighDecoration> BOW = DECORATIONS.register("bow", () ->
            new ThighHighDecoration(Component.translatable("thighhighsdecoration.kawaiidishes.bow"),
                    new ResourceLocation(KawaiiDishes.MODID, "item/thigh_highs/bow"),
                    new ResourceLocation(KawaiiDishes.MODID,"textures/models/armor/thigh_highs/bow.png")));

    public static final Supplier<ThighHighDecoration> LEG_CLIP = DECORATIONS.register("leg_clip", () ->
            new ThighHighDecoration(Component.translatable("thighhighsdecoration.kawaiidishes.leg_clip"),
                    new ResourceLocation(KawaiiDishes.MODID, "item/thigh_highs/leg_clip"),
                    new ResourceLocation(KawaiiDishes.MODID,"textures/models/armor/thigh_highs/leg_clip.png"),
                    new ResourceLocation(KawaiiDishes.MODID,"textures/models/armor/thigh_highs/leg_clip_clip.png")));

    public static final Supplier<ThighHighDecoration> FULL_BANDS = DECORATIONS.register("full_band", () ->
            new ThighHighDecoration(Component.translatable("thighhighsdecoration.kawaiidishes.full_band"),
                    new ResourceLocation(KawaiiDishes.MODID, "item/thigh_highs/full_band"),
                    new ResourceLocation(KawaiiDishes.MODID,"textures/models/armor/thigh_highs/full_band.png")));
    public static void register(){
        //This does nothing but bootstrap the class
    }
}
