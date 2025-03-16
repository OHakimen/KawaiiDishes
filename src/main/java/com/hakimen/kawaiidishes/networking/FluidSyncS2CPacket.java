//package com.hakimen.kawaiidishes.networking;
//
//import com.hakimen.kawaiidishes.KawaiiDishes;
//import com.hakimen.kawaiidishes.block_entities.CoffeeMachineBlockEntity;
//import com.hakimen.kawaiidishes.containers.CoffeeMachineContainer;
//import net.minecraft.client.Minecraft;
//import net.minecraft.core.BlockPos;
//import net.minecraft.network.RegistryFriendlyByteBuf;
//import net.minecraft.network.codec.StreamCodec;
//import net.minecraft.network.protocol.PacketFlow;
//import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
//import net.minecraft.resources.ResourceLocation;
//import net.neoforged.neoforge.fluids.FluidStack;
//import net.neoforged.neoforge.network.handling.IPayloadContext;
//
//public record FluidSyncS2CPacket(FluidStack fluid, BlockPos pos) implements CustomPacketPayload {
//    public static final CustomPacketPayload.Type<FluidSyncS2CPacket> TYPE = new CustomPacketPayload.Type<FluidSyncS2CPacket>(ResourceLocation.fromNamespaceAndPath(KawaiiDishes.MODID, "fluid_sync_s2c"));
//
//    public static final StreamCodec<RegistryFriendlyByteBuf, FluidSyncS2CPacket> STREAM_CODEC = StreamCodec.composite(
//            FluidStack.STREAM_CODEC,
//            FluidSyncS2CPacket::fluid,
//            BlockPos.STREAM_CODEC,
//            FluidSyncS2CPacket::pos,
//            FluidSyncS2CPacket::new
//    );
//
//    public static void handle(FluidSyncS2CPacket payload, IPayloadContext context) {
//        if (context.flow().equals(PacketFlow.CLIENTBOUND)) {
//            context.enqueueWork(() -> {
//                if (Minecraft.getInstance().level.getBlockEntity(payload.pos) instanceof CoffeeMachineBlockEntity blockEntity) {
//                    blockEntity.setFluid(payload.fluid);
//
//                    if (Minecraft.getInstance().player.containerMenu instanceof CoffeeMachineContainer menu &&
//                        menu.getBlockEntity().getBlockPos().equals(payload.pos)) {
//                        menu.setFluidStack(payload.fluid);
//                    }
//                }
//            });
//        }
//    }
//
//    @Override
//    public Type<? extends CustomPacketPayload> type() {
//        return TYPE;
//    }
//}

// TODO: packets