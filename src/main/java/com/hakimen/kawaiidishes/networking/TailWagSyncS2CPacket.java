package com.hakimen.kawaiidishes.networking;

import com.hakimen.kawaiidishes.client.data.ClientTailWagData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class TailWagSyncS2CPacket {

    private final boolean isWagging;
    private final UUID uuid;
    public TailWagSyncS2CPacket(boolean isWagging, UUID uuid) {
        this.isWagging = isWagging;
        this.uuid = uuid;
    }

    public TailWagSyncS2CPacket(FriendlyByteBuf buff){
        this.isWagging = buff.readBoolean();
        this.uuid = buff.readUUID();
    }

    public void toBytes(FriendlyByteBuf buff){
        buff.writeBoolean(this.isWagging);
        buff.writeUUID(this.uuid);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(()->{
            ClientTailWagData.setState(uuid,isWagging);
        });
        return true;
    }
}
