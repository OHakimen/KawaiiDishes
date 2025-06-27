package com.hakimen.kawaiidishes.networking;

import com.hakimen.kawaiidishes.block_entities.CoffeeMachineBlockEntity;
import com.hakimen.kawaiidishes.containers.CoffeeMachineContainer;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;

public class FluidSyncS2CPacket {

    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        FluidVariant variant = FluidVariant.fromPacket(buf);
        long fluidLevel = buf.readLong();
        BlockPos position = buf.readBlockPos();

        if(client.world.getBlockEntity(position) instanceof CoffeeMachineBlockEntity blockEntity) {
            blockEntity.getWaterTank().variant = variant;
            blockEntity.getWaterTank().amount = fluidLevel;

            if(client.player.currentScreenHandler instanceof CoffeeMachineContainer menu &&
                    menu.getBlockEntity().getPos().equals(position)) {
                menu.setFluidVariant(variant);
                menu.setAmount(fluidLevel);
            }
        }
    }

}
