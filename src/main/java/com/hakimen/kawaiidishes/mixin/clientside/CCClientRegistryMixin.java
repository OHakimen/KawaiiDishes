package com.hakimen.kawaiidishes.mixin.clientside;

import dan200.computercraft.ComputerCraft;
import dan200.computercraft.client.ClientRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.spongepowered.asm.mixin.*;

import static com.hakimen.kawaiidishes.integration.cc.OverlayUtils.overlays;

@Pseudo
@Mixin(value = ClientRegistry.class,remap = false)
public class CCClientRegistryMixin {


    @Shadow @Final private static String[] EXTRA_MODELS;

    /**
     * @author
     * @reason
     */
    @SubscribeEvent
    @Overwrite
    public static void registerModels( ModelEvent.RegisterAdditional event )
    {
        for(String model : EXTRA_MODELS )
        {
            event.register( new ResourceLocation(ComputerCraft.MOD_ID,model) );
        }
        for (ResourceLocation overlay : overlays.values() ){
            event.register(overlay);
        }
    }
}
