package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.networking.FluidSyncS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

public class PacketRegister {

    public static final Identifier FLUID_SYNC = new Identifier(KawaiiDishes.MODID, "coffee_machine_tank_sync");
    public static void registerClient2ServerPackets(){

    }

    public static void registerServer2ClientPackets(){
        ClientPlayNetworking.registerGlobalReceiver(FLUID_SYNC, FluidSyncS2CPacket::receive);
    }
}
