package com.hakimen.kawaiidishes.events;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.networking.TailWagC2SPacket;
import com.hakimen.kawaiidishes.registry.KeybindRegister;
import com.hakimen.kawaiidishes.registry.PacketRegister;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = KawaiiDishes.modId, value = Dist.CLIENT)
    public static class ClientForgeEvents{
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event){
            event.register(KeybindRegister.tailWagKey);
        }
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event){
            if(KeybindRegister.tailWagKey.consumeClick()){
                PacketRegister.sendToServer(new TailWagC2SPacket());
            }
        }
    }
    @Mod.EventBusSubscriber(modid = KawaiiDishes.modId, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeybindRegister.tailWagKey);
        }
    }
}
