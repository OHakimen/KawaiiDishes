package com.hakimen.kawaiidishes.utils;

import com.hakimen.kawaiidishes.items.CatEars;
import com.hakimen.kawaiidishes.items.armor.CatMaidArmorItem;
import com.hakimen.kawaiidishes.items.armor.MaidDressArmorItem;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class KawaiiMessages {
    public static ArrayList<String> defaultMessages = new ArrayList<>();
    public static ArrayList<String> catEarsMessages = new ArrayList<>();
    public static ArrayList<String> maidCostumeMessages = new ArrayList<>();

    public static ArrayList<String> catEarsMaidCostumeMessages = new ArrayList<>();

    static {
        defaultMessages.add("You look so kawaii !!");
        defaultMessages.add("OuO");
        defaultMessages.add("Cute <3");
        defaultMessages.add("You look nice today");
        defaultMessages.add("Hi Cutie !");
        defaultMessages.add("Look here little one, pose for me! *chik*");
        defaultMessages.add("Hey sweetie!");
        defaultMessages.add("Hewwo, %user% :3");

        catEarsMessages.add("It's so cute neko-chan, Nyant - ta!");
        catEarsMessages.add("Plweaaaase! Take a picture with me!");
        catEarsMessages.add("They seem like so... UwU!!!");
        catEarsMessages.add("OMG, CAT EARS");
        catEarsMessages.add("Wait... they are wearing cat ears !");
        catEarsMessages.add("Come back, %user%-chan :3");
        catEarsMessages.add("OwO");

        maidCostumeMessages.add("No way, a cute maid is here >w<");

        catEarsMaidCostumeMessages.add("Omg a maid, if only they where with cat ea..., NO WAY THEY HAVE CAT EARS");
    }

    public static void sendMessage(LivingEntity entity, Player target){

        boolean hasCatEars,hasMaidDress,hasCatMaidDress;
        Random r = target.getRandom();
        hasCatEars = target.getInventory().getArmor(EquipmentSlot.HEAD.getIndex()).getItem() instanceof CatEars;
        hasMaidDress = target.getInventory().getArmor(EquipmentSlot.CHEST.getIndex()).getItem() instanceof MaidDressArmorItem;
        hasCatMaidDress = target.getInventory().getArmor(EquipmentSlot.CHEST.getIndex()).getItem() instanceof CatMaidArmorItem;
        var msg = new TextComponent("<").append(entity.getDisplayName()).append("> ");
        int size;
        var value = "";
        if(hasCatMaidDress || hasCatEars){
            size = catEarsMaidCostumeMessages.size();
            int option = r.nextInt(0,size);
            value = catEarsMaidCostumeMessages.get(option);
        }else if(hasMaidDress){
            size = maidCostumeMessages.size();
            int option = r.nextInt(0,size);
            value = maidCostumeMessages.get(option);
        }else if(hasCatEars){
            size = catEarsMessages.size();
            int option = r.nextInt(0,size);
            value = catEarsMessages.get(option);
        }else{
            size = defaultMessages.size();
            int option = r.nextInt(0,size);
            value = defaultMessages.get(option);
        }
        if(r.nextFloat(0,1) < 0.75){
            int option = r.nextInt(0,defaultMessages.size());
            value = defaultMessages.get(option);
        }

        if(value != ""){
            if(value.contains("%user%")){
                value = value.replace("%user%",target.getDisplayName().getString());
            }
        }
        msg.append(value);
        target.sendMessage(msg,entity.getUUID());

    }

}
