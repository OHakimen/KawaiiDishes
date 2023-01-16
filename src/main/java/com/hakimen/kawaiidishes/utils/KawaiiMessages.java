package com.hakimen.kawaiidishes.utils;

import com.hakimen.kawaiidishes.config.KawaiiDishesClientConfig;
import com.hakimen.kawaiidishes.config.KawaiiDishesCommonConfig;
import com.hakimen.kawaiidishes.items.CatEars;
import com.hakimen.kawaiidishes.items.armor.CatMaidArmorItem;
import com.hakimen.kawaiidishes.items.armor.MaidDressArmorItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.w3c.dom.Text;

import java.util.ArrayList;
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
        Random r = (Random) target.getRandom();
        var msg = Component.literal("<").append(entity.getDisplayName()).append("> ");
        int option = r.nextInt(0,messages.length);
        var value = messages[option];
        msg.append(value);
        target.displayClientMessage(msg,false);

    }

}
