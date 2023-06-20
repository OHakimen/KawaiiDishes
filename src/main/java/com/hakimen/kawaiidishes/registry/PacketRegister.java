package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.networking.TailWagC2SPacket;
import com.hakimen.kawaiidishes.networking.TailWagSyncS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketRegister {
    private static SimpleChannel INSTANCE;

    private static int idPacket = 0;

    private static int id(){
        return idPacket++;
    }

    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(KawaiiDishes.modId, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(TailWagC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(TailWagC2SPacket::new)
                .encoder(TailWagC2SPacket::toBytes)
                .consumerMainThread(TailWagC2SPacket::handle)
                .add();

        net.messageBuilder(TailWagSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(TailWagSyncS2CPacket::new)
                .encoder(TailWagSyncS2CPacket::toBytes)
                .consumerMainThread(TailWagSyncS2CPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG msg){
        INSTANCE.sendToServer(msg);
    }

    public static <MSG> void sendToClient(MSG msg, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(()-> player), msg);
    }

    public static <MSG> void sendToClients(MSG msg){
        INSTANCE.send(PacketDistributor.ALL.noArg(), msg);
    }
}
