package com.hakimen.kawaiidishes.utils;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.config.KawaiiDishesCommonConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.Random;

public class KawaiiMessages {


    public static void sendMessage(LivingEntity entity, Player target){
        Random r = KawaiiDishes.RANDOM;
        var msg = Component.literal("<").append(entity.getDisplayName()).append("> ");
        int option = r.nextInt(0,KawaiiDishesCommonConfig.messages.get().size());
        var value = KawaiiDishesCommonConfig.messages.get().get(option);
        msg.append(value);
        if(KawaiiDishesCommonConfig.shouldSendMessage.get()) {
            target.displayClientMessage(msg, false);
        }
    }

}
