package com.hakimen.kawaiidishes.integration.cc;

import com.hakimen.kawaiidishes.KawaiiDishes;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;

public class OverlayUtils {
    public static final HashMap<String, ResourceLocation> overlays = new HashMap<>();

    static {
        overlays.put("maid", new ResourceLocation(KawaiiDishes.modId, "block/integration/turtles/maid_overlay"));

        overlays.put("white bunny", new ResourceLocation(KawaiiDishes.modId, "block/integration/turtles/white_bunny_overlay"));
        overlays.put("black bunny", new ResourceLocation(KawaiiDishes.modId, "block/integration/turtles/black_bunny_overlay"));
        overlays.put("caramel bunny", new ResourceLocation(KawaiiDishes.modId, "block/integration/turtles/caramel_bunny_overlay"));

        overlays.put("white cat", new ResourceLocation(KawaiiDishes.modId, "block/integration/turtles/white_cat_overlay"));
        overlays.put("black cat", new ResourceLocation(KawaiiDishes.modId, "block/integration/turtles/black_cat_overlay"));
        overlays.put("caramel cat", new ResourceLocation(KawaiiDishes.modId, "block/integration/turtles/caramel_cat_overlay"));

        overlays.put("white fox", new ResourceLocation(KawaiiDishes.modId, "block/integration/turtles/white_fox_overlay"));
        overlays.put("black fox", new ResourceLocation(KawaiiDishes.modId, "block/integration/turtles/black_fox_overlay"));
        overlays.put("red fox", new ResourceLocation(KawaiiDishes.modId, "block/integration/turtles/red_fox_overlay"));
        overlays.put("brown fox", new ResourceLocation(KawaiiDishes.modId, "block/integration/turtles/brown_fox_overlay"));
    }

    ;
}
