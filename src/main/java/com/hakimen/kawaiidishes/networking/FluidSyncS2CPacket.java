package com.hakimen.kawaiidishes.networking;

import com.hakimen.kawaiidishes.block_entities.CoffeeMachineBlockEntity;
import com.hakimen.kawaiidishes.containers.CoffeeMachineContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static com.hakimen.kawaiidishes.registry.PacketRegister.FLUID_SYNC_COFFEE_MACHINE;

public class FluidSyncS2CPacket implements CustomPacketPayload {
    private FluidStack fluid;
    private BlockPos pos;

    public FluidSyncS2CPacket(FluidStack fluidStack, BlockPos pos) {
        this.fluid = fluidStack;
        this.pos = pos;
    }

    public FluidSyncS2CPacket() {
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeFluidStack(fluid);
    }

    @Override
    public ResourceLocation id() {
        return FLUID_SYNC_COFFEE_MACHINE;
    }

    public static CustomPacketPayload toBuffer(FriendlyByteBuf buf) {
        return new FluidSyncS2CPacket(buf.readFluidStack(),buf.readBlockPos());
    }

    public static void handle(FluidSyncS2CPacket payload, IPayloadContext context){
        if(context.flow().equals(PacketFlow.CLIENTBOUND)){
            context.workHandler().execute(() -> {
                if(Minecraft.getInstance().level.getBlockEntity(payload.pos) instanceof CoffeeMachineBlockEntity blockEntity) {
                    blockEntity.setFluid(payload.fluid);

                    if(Minecraft.getInstance().player.containerMenu instanceof CoffeeMachineContainer menu &&
                            menu.getBlockEntity().getBlockPos().equals(payload.pos)) {
                        menu.setFluidStack(payload.fluid);
                    }
                }
            });
        }
    }
}
