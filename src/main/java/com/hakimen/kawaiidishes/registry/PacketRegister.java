package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.networking.FluidSyncS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.resources.ResourceLocation;

public class PacketRegister {

    public static final ResourceLocation FLUID_SYNC = new ResourceLocation(KawaiiDishes.MODID, "coffee_machine_tank_sync");
    public static void registerClient2ServerPackets(){

    }

    public static void registerServer2ClientPackets(){
        ClientPlayNetworking.registerGlobalReceiver(FLUID_SYNC, FluidSyncS2CPacket::receive);
    }
}
