package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.registry.EffectRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.HashMap;

public class LangSupplier extends LanguageProvider {
    public LangSupplier(DataGenerator gen, String locale) {
        super(gen, KawaiiDishes.modId, locale);
    }

    @Override
    protected void addTranslations() {
        HashMap<String,String> dressNames = new HashMap<String,String>();
                dressNames.put("blue_maid_dress","Blue Maid Dress");
                dressNames.put("brown_maid_dress","Brown Maid Dress");
                dressNames.put("cyan_maid_dress","Cyan Maid Dress");
                dressNames.put("gray_maid_dress","Gray Maid Dress");
                dressNames.put("green_maid_dress","Green Maid Dress");
                dressNames.put("light_blue_maid_dress","Light Blue Maid Dress");
                dressNames.put("light_gray_maid_dress","Light Gray Maid Dress");
                dressNames.put("lime_maid_dress","Lime Maid Dress");
                dressNames.put("magenta_maid_dress","Magenta Maid Dress");
                dressNames.put("orange_maid_dress","Orange Maid Dress");
                dressNames.put("pink_maid_dress","Pink Maid Dress");
                dressNames.put("red_maid_dress","Red Maid Dress");
                dressNames.put("white_maid_dress","White Maid Dress");
                dressNames.put("black_maid_dress","Black Maid Dress");
                dressNames.put("yellow_maid_dress","Yellow Maid Dress");
                dressNames.put("purple_maid_dress","Purple Maid Dress");
                
        for (var items:ItemRegister.ITEMS.getEntries().stream().toList()) {
            for (int i = 0; i < dressNames.keySet().size(); i++) {
                if(items.get().getRegistryName().getPath().equals(dressNames.keySet().stream().toArray()[i].toString()+"_cat_tail_black")){
                    add(items.get(),dressNames.get(items.get().getRegistryName().getPath().replace("_cat_tail_black",""))+" Black Tailed");
                }
                if(items.get().getRegistryName().getPath().equals(dressNames.keySet().stream().toArray()[i].toString()+"_cat_tail_caramel")){
                    add(items.get(),dressNames.get(items.get().getRegistryName().getPath().replace("_cat_tail_caramel",""))+" Caramel Tailed");
                }
                if(items.get().getRegistryName().getPath().equals(dressNames.keySet().stream().toArray()[i].toString()+"_cat_tail_white")){
                    add(items.get(),dressNames.get(items.get().getRegistryName().getPath().replace("_cat_tail_white",""))+" White Tailed");
                }
                if(items.get().getRegistryName().getPath().equals(dressNames.keySet().stream().toArray()[i].toString())){
                    add(items.get(),dressNames.get(items.get().getRegistryName().getPath()));
                }
            }
            if(items.get().getRegistryName().getPath().endsWith("_coffee")){
                add(items.get(),setFirstLetterUpperCase(items.get().getRegistryName().getPath().replace("_coffee",""))+ " Coffee");
            }
        }

        add(ItemRegister.coffeeFruit.get(),"Coffee Fruit");
        add(ItemRegister.roastedCoffeeBeans.get(),"Roasted Coffee Beans");
        add(ItemRegister.driedCoffeeBeans.get(),"Dried Coffee Beans");

        add(ItemRegister.mug.get(),"Mug");

        add(ItemRegister.caramelCatTail.get(),"Caramel Cat Tail");
        add(ItemRegister.whiteCatTail.get(),"White Cat Tail");
        add(ItemRegister.blackCatTail.get(),"Black Cat Tail");

        add(ItemRegister.caramelCatEars.get(),"Caramel Cat Ears");
        add(ItemRegister.blackCatEars.get(),"Black Cat Ears");
        add(ItemRegister.whiteCatEars.get(),"White Cat Ears");

        add(ItemRegister.blackThighHighs.get(),"Black Thigh Highs");
        add(ItemRegister.whiteThighHighs.get(),"White Thigh Highs");

        add(ItemRegister.blackThighHighsShoes.get(),"Dark Brown Shoes");
        add(ItemRegister.whiteThighHighsShoes.get(),"Brown Shoes");

        add("itemGroup.kawaiidishes.foods", "Kawaii's Foods");
        add("itemGroup.kawaiidishes.blocks", "Kawaii's Blocks");
        add("itemGroup.kawaiidishes.cosmetics", "Kawaii's Cosmetics");

        add(EffectRegister.kawaiiEffect.get(),"Kawaii OuO");
    }

    public String setFirstLetterUpperCase(String string){
        var builtString = "";
        for (String word:string.split(" ")) {
            builtString = word.substring(0,1).toUpperCase() + word.substring(1);
        }
        return builtString;
    }
}
