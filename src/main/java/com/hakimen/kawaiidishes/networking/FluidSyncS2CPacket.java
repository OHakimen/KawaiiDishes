package com.hakimen.kawaiidishes.networking;

import com.hakimen.kawaiidishes.block_entities.CoffeeMachineBlockEntity;
import com.hakimen.kawaiidishes.containers.CoffeeMachineContainer;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public class FluidSyncS2CPacket {

    public static void receive(Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender) {
        FluidVariant variant = FluidVariant.fromPacket(buf);
        long fluidLevel = buf.readLong();
        BlockPos position = buf.readBlockPos();

        if(client.level.getBlockEntity(position) instanceof CoffeeMachineBlockEntity blockEntity) {
            blockEntity.getWaterTank().variant = variant;
            blockEntity.getWaterTank().amount = fluidLevel;

            if(client.player.containerMenu instanceof CoffeeMachineContainer menu &&
                    menu.getBlockEntity().getBlockPos().equals(position)) {
                menu.setFluidVariant(variant);
                menu.setAmount(fluidLevel);
            }
        }
    }

}
