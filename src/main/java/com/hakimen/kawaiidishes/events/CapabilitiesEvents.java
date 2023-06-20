package com.hakimen.kawaiidishes.events;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.capabilities.PlayerTailWag;
import com.hakimen.kawaiidishes.capabilities.PlayerTailWagProvider;
import com.hakimen.kawaiidishes.networking.TailWagSyncS2CPacket;
import com.hakimen.kawaiidishes.registry.PacketRegister;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KawaiiDishes.modId)
public class CapabilitiesEvents {
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof Player){
            if(!event.getObject().getCapability(PlayerTailWagProvider.playerTailWag).isPresent()){
                event.addCapability(new ResourceLocation(KawaiiDishes.modId,"properties"), new PlayerTailWagProvider());
            }
        }
    }
    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerTailWagProvider.playerTailWag).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerTailWagProvider.playerTailWag).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerTailWag.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerTailWagProvider.playerTailWag).ifPresent(wag -> {
                    PacketRegister.sendToClients(new TailWagSyncS2CPacket(wag.isWagging(),player.getUUID()));
                });
            }
        }
    }
}
