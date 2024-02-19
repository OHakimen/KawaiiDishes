package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.networking.FluidSyncS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

@Mod.EventBusSubscriber(modid = KawaiiDishes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PacketRegister {

    public static ResourceLocation FLUID_SYNC_COFFEE_MACHINE = new ResourceLocation(KawaiiDishes.MODID, "coffee_machine_tank_sync");

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlerEvent event) {
        final IPayloadRegistrar registrar = event.registrar(KawaiiDishes.MODID)
                .versioned("1.2.3")
                .optional();


        registrar.play(FLUID_SYNC_COFFEE_MACHINE, FluidSyncS2CPacket::toBuffer, (customPacketPayload, playPayloadContext) -> {
            FluidSyncS2CPacket.handle((FluidSyncS2CPacket) customPacketPayload, playPayloadContext);
        });
    }
}
