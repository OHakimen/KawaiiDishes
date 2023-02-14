package com.hakimen.kawaiidishes.utils;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.config.KawaiiDishesCommonConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.Random;

public class KawaiiMessages {


    public static void sendMessage(LivingEntity entity, Player target){
        String[] messages = new String[]{
                "You look so kawaii !!",
                "OuO",
                "Cute <3",
                "You look nice today",
                "Hi Cutie !",
                "Hey sweetie!"
        };
        Random r = KawaiiDishes.RANDOM;
        var msg = Component.literal("<").append(entity.getDisplayName()).append("> ");
        int option = r.nextInt(0,messages.length);
        var value = messages[option];
        msg.append(value);
        if(KawaiiDishesCommonConfig.shouldSendMessage.get()) {
            target.displayClientMessage(msg, false);
        }
    }

}
