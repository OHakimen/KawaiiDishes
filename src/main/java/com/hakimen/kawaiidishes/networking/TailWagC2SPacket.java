package com.hakimen.kawaiidishes.networking;

import com.hakimen.kawaiidishes.capabilities.PlayerTailWagProvider;
import com.hakimen.kawaiidishes.registry.PacketRegister;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TailWagC2SPacket {
    public TailWagC2SPacket() {
    }

    public TailWagC2SPacket(FriendlyByteBuf buff){

    }

    public void toBytes(FriendlyByteBuf buff){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(()->{
            ServerPlayer player = context.getSender();
            player.getCapability(PlayerTailWagProvider.playerTailWag).ifPresent(wag ->{
                wag.setWagging(!wag.isWagging());
                PacketRegister.sendToClients(new TailWagSyncS2CPacket(wag.isWagging(),player.getUUID()));
            });
        });
        return true;
    }
}
